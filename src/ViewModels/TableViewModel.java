package ViewModels;

import DbWcfServiceReference.Attribute;
import DbWcfServiceReference.IDbWcfService;
import DbWcfServiceReference.Row;
import DbWcfServiceReference.TableDto;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

public class TableViewModel implements Initializable
{
    private final String _dbName;
    private final String _tableName;
    private final IDbWcfService _dbService;

    public TableView tableView;

    public TableViewModel(String dbName, String tableName, IDbWcfService dbService)
    {
        this._dbName = dbName;
        this._tableName = tableName;
        this._dbService = dbService;
    }

    private TableDto getTable()
    {
        TableDto table = null;
        try
        {
            table = this._dbService.getTable(this._dbName, this._tableName);
        }
        catch (RemoteException ex)
        {
            System.out.println(ex);
        }
        finally
        {
            return table;
        }
    }

    private void updateTableView(TableDto table)
    {
        tableView.getColumns().clear();
        tableView.getItems().clear();

        Attribute[] attributes = table.getAttributes();

        for (int i = 0; i < attributes.length; i++)
        {
            TableColumn<Row, String> column = new TableColumn<>(attributes[i].getName());

            final int index = i;
            column.setCellValueFactory((p) -> new SimpleStringProperty(p.getValue().getValue()[index]));

            tableView.getColumns().add(column);
        }

        tableView.setItems(FXCollections.observableArrayList(table.getRows()));
    }

    private void refresh()
    {
        TableDto table = this.getTable();
        if (table == null)
        {
            System.out.println("Error while getting table.");
            return;
        }

        this.updateTableView(table);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        refresh();
    }

    public void refresh(ActionEvent actionEvent)
    {
        refresh();
    }
}

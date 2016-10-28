package ViewModels;

import DbWcfServiceReference.Attribute;
import DbWcfServiceReference.IDbWcfService;
import DbWcfServiceReference.Row;
import DbWcfServiceReference.TableDto;
import Utils.StageUtils;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.controlsfx.control.CheckComboBox;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.List;
import java.util.ResourceBundle;

public class TableViewModel implements Initializable
{
    private final String _dbName;
    private final String _tableName;
    private final IDbWcfService _dbService;

    public TableView tableView;
    public CheckComboBox<String> checkComboBox;

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

    private TableDto getTableProjection(List<String> attributes)
    {
        TableDto table = null;
        try
        {
            table = this._dbService
                    .getTableProjection(this._dbName, this._tableName, attributes.toArray(new String[0]));
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

        tableView.getItems().addAll(table.getRows());
    }

    private void updateCheckComboBox(TableDto table)
    {
        this.checkComboBox.getCheckModel().clearChecks();
        this.checkComboBox.getItems().clear();

        for (Attribute attribute : table.getAttributes())
        {
            this.checkComboBox.getItems().add(attribute.getName());
        }
    }

    private void refresh()
    {
        TableDto table = this.getTable();
        if (table == null)
        {
            StageUtils.openErrorDialog("Error occurred while getting table.");
            return;
        }

        this.updateCheckComboBox(table);
        this.updateTableView(table);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        ObservableList<String> checkedItems = checkComboBox.getCheckModel().getCheckedItems();
        checkedItems.addListener((ListChangeListener<? super String>) observable ->
        {
            performTableProjection(checkedItems);
        });

        refresh();
    }

    private void performTableProjection(List<String> checkedItems)
    {
        TableDto table = (checkedItems.size() > 0) ? this.getTableProjection(checkedItems) : this.getTable();
        if (table == null)
        {
            StageUtils.openErrorDialog("Error occurred while getting table projection.");
            return;
        }

        this.updateTableView(table);
    }

    public void refresh(ActionEvent actionEvent)
    {
        refresh();
    }
}

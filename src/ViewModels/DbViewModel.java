package ViewModels;

import DbWcfServiceReference.DatabaseDto;
import DbWcfServiceReference.IDbWcfService;
import Utils.StageUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

public class DbViewModel implements Initializable
{
    private final String _dbName;
    private final IDbWcfService _dbService;
    private final ObservableList<String> _tablesNames;

    public ListView tablesNamesListView;

    public DbViewModel(String dbName, IDbWcfService dbService)
    {
        this._dbName = dbName;
        this._dbService = dbService;

        this._tablesNames = FXCollections.observableArrayList();
    }

    private DatabaseDto getDatabase()
    {
        DatabaseDto databaseDto = null;
        try
        {
            databaseDto = this._dbService.getDatabase(this._dbName);
        }
        catch (RemoteException ex)
        {
            System.out.println(ex);
        }
        finally
        {
            return databaseDto;
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        tablesNamesListView.setItems(this._tablesNames);

        this.refresh();
    }

    public void refresh(ActionEvent actionEvent)
    {
        this.refresh();
    }

    private void refresh()
    {
        this._tablesNames.clear();

        DatabaseDto database = this.getDatabase();
        if (database == null)
        {
            System.out.println("Error while getting database.");
            return;
        }

        this._tablesNames.addAll(database.getTableNames());
    }

    public void onMouseClicked(MouseEvent mouseEvent)
    {
        if (mouseEvent.getClickCount() == 2 && mouseEvent.getButton() == MouseButton.PRIMARY)
        {
            String tableName = (String) tablesNamesListView.getSelectionModel().getSelectedItem();
            if (tableName != null)
            {
                this.openTableWindow(tableName);
            }
        }
    }

    private void openTableWindow(String tableName)
    {
        TableViewModel viewModel = new TableViewModel(this._dbName, tableName, this._dbService);
        Stage currentStage = (Stage) tablesNamesListView.getScene().getWindow();

        StageUtils.openNewStage(String.format("Database: %s", tableName), viewModel,
                getClass().getClassLoader().getResource("Views/TableView.fxml"), currentStage);
    }
}

package ViewModels;

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

import java.net.URL;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class MainViewModel implements Initializable
{
    private final ObservableList<String> _dbNames;
    private final IDbWcfService _dbService;

    public ListView dbNamesListView;

    public MainViewModel(IDbWcfService dbService)
    {
        this._dbService = dbService;

        this._dbNames = FXCollections.observableArrayList();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        dbNamesListView.setItems(this._dbNames);

        this.refresh();
    }

    public void refresh(ActionEvent actionEvent)
    {
        this.refresh();
    }

    private void refresh()
    {
        this._dbNames.clear();

        List<String> dbNamesList = this.getDbNamesList();
        if (dbNamesList == null)
        {
            StageUtils.openErrorDialog("Error occurred while getting databases names.");
            return;
        }

        this._dbNames.addAll(dbNamesList);
    }

    private List<String> getDbNamesList()
    {
        List<String> dbNames = null;
        try
        {
            dbNames = Arrays.asList(this._dbService.getDatabasesNames());
        }
        catch (RemoteException e)
        {
            e.printStackTrace();
        }
        finally
        {
            return dbNames;
        }
    }

    public void onMouseClicked(MouseEvent mouseEvent)
    {
        if (mouseEvent.getClickCount() == 2 && mouseEvent.getButton() == MouseButton.PRIMARY)
        {
            String dbName = (String) dbNamesListView.getSelectionModel().getSelectedItem();
            if (dbName != null)
            {
                this.openDbWindow(dbName);
            }
        }
    }

    private void openDbWindow(String dbName)
    {
        DbViewModel viewModel = new DbViewModel(dbName, this._dbService);
        Stage currentStage = (Stage) dbNamesListView.getScene().getWindow();

        StageUtils.openNewStage(String.format("Database: %s", dbName), viewModel,
                getClass().getClassLoader().getResource("Views/DbView.fxml"), currentStage);
    }
}

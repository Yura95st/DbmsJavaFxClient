package ViewModels;

import DbWcfServiceReference.DbWcfService;
import DbWcfServiceReference.DbWcfServiceLocator;
import DbWcfServiceReference.IDbWcfService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.io.Console;
import java.net.URL;
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
    }

    public void refresh(ActionEvent actionEvent)
    {
        this._dbNames.clear();

        DbWcfServiceLocator dbServiceLocator = new DbWcfServiceLocator();

        try
        {
            IDbWcfService dbService = dbServiceLocator.getBasicHttpBinding_IDbWcfService();
            String[] dbNames = dbService.getDatabasesNames();

            this._dbNames.addAll(dbNames);
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }
}

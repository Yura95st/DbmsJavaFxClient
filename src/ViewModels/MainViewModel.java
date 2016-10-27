package ViewModels;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class MainViewModel
{
    private final ObservableList<String> _dbNames;

    public ListView dbNamesListView;

    public MainViewModel()
    {
        this._dbNames = FXCollections.observableArrayList(
                "Single", "Double", "Suite", "Family App");
    }

    @FXML
    private void initialize()
    {
        dbNamesListView.setItems(this._dbNames);
    }
}

import DbWcfServiceReference.DbWcfService;
import DbWcfServiceReference.DbWcfServiceLocator;
import DbWcfServiceReference.IDbWcfService;
import Utils.StageUtils;
import ViewModels.MainViewModel;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application
{
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        DbWcfService dbServiceLocator = new DbWcfServiceLocator();
        IDbWcfService dbService = dbServiceLocator.getBasicHttpBinding_IDbWcfService();

        MainViewModel viewModel = new MainViewModel(dbService);

        StageUtils.openNewStage("DbmsJavaFxClient", viewModel, getClass().getResource("Views/MainView.fxml"), null);
    }


    public static void main(String[] args)
    {
        launch(args);
    }
}

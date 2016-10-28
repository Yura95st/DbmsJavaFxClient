package Utils;


import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;

public class StageUtils
{
    public static void openNewStage(String title, Object controller, URL location, Stage parentStage)
    {
        FXMLLoader loader = new FXMLLoader(location);
        loader.setController(controller);

        Parent root;
        try
        {
            root = loader.load();

            Stage stage = new Stage();
            stage.setTitle(title);
            stage.setScene(new Scene(root, 960, 480));
            stage.initModality(Modality.APPLICATION_MODAL);

            if (parentStage != null)
            {
                int delta = 20;
                stage.setX(parentStage.getX() + delta);
                stage.setY(parentStage.getY() + delta);
            }

            stage.show();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}

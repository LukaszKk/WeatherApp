package app;

import app.controllers.AppController;
import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application
{
    @Override
    public void start( Stage stage ) throws Exception
    {
        AppController controller = new AppController();
        controller.loadView(stage, "rootView");
    }

    public static void main( String[] args )
    {
        launch(args);
    }
}

package Application;

import Application.mainWindow.MainView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MyApp extends Application {

    public void begin() {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setHeight(500);
        stage.setWidth(500);
        stage.setTitle("Maldenbrot set");
        MainView mainView = new MainView();
        stage.setScene(new Scene(mainView));
        stage.show();
        mainView.cartesianCoordinateSystemView.updateCenter();
    }
}

package Application.mainWindow;

import Support.ActionButton;
import Support.Coordinates;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ToolBar;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class MainView extends VBox {
    public final CartesianCoordinateSystemView cartesianCoordinateSystemView = new CartesianCoordinateSystemView();
    public final ToolBar mainToolBar = new ToolBar();
    private Coordinates mouseClickCoordinates;
    private Coordinates oldCenterCoordinates;
    EventHandler<ActionEvent> setCenter = e -> {
        cartesianCoordinateSystemView.updateCenter();
    };

    EventHandler<MouseEvent> mouseClicked = e -> {
        if (e.getButton() == MouseButton.PRIMARY) {
            mouseClickCoordinates = new Coordinates(e.getX(), e.getY());
            oldCenterCoordinates = cartesianCoordinateSystemView.getCenter();
        }
    };

    EventHandler<MouseEvent> mouseMoved = e -> {
        if (e.isPrimaryButtonDown())
            cartesianCoordinateSystemView.setCenter(new Coordinates(oldCenterCoordinates.getX() - mouseClickCoordinates.getX() + e.getX(),
                    oldCenterCoordinates.getY() - mouseClickCoordinates.getY() + e.getY()));
    };


    public MainView() {
        mainToolBarInit();
        cartesianCoordinateSystemView.prefHeightProperty().bind(heightProperty());
        cartesianCoordinateSystemView.prefWidthProperty().bind(widthProperty());
        cartesianCoordinateSystemView.setOnMouseDragged(mouseMoved);
        cartesianCoordinateSystemView.setOnMousePressed(mouseClicked);
        getChildren().addAll(cartesianCoordinateSystemView, mainToolBar);
    }

    private void mainToolBarInit() {
        mainToolBar.getItems().addAll(new ActionButton("Update center", setCenter));
    }
}

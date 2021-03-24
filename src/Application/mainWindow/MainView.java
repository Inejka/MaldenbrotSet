package Application.mainWindow;

import Support.ActionButton;
import Support.Coordinates;
import Support.ScientificNotation;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ToolBar;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.input.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.math.BigDecimal;

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
            mouseClickCoordinates = new Coordinates(String.valueOf(e.getX()), String.valueOf(e.getY()));
            oldCenterCoordinates = cartesianCoordinateSystemView.getCenter();
        }
    };

    EventHandler<MouseEvent> mouseMoved = e -> {
        if (e.isPrimaryButtonDown())
            cartesianCoordinateSystemView.setCenter(new Coordinates(oldCenterCoordinates.getX().subtract(mouseClickCoordinates.getX()).add(ScientificNotation.valueOf(e.getX())),
                    oldCenterCoordinates.getY().subtract(mouseClickCoordinates.getY()).add(ScientificNotation.valueOf(e.getY()))));
    };

    EventHandler<ScrollEvent> scroll = e -> {
        /*System.out.println(cartesianCoordinateSystemView.getRelativeCoordinates(new Coordinates(e.getX(),e.getY())).getX());
        if(e.getDeltaY()>0)
        cartesianCoordinateSystemView.zoom(new Coordinates(e.getX(), e.getY()));
        else
            cartesianCoordinateSystemView.unZoom(new Coordinates(e.getX(), e.getY()));*/
    };

    EventHandler<KeyEvent> keyPressed = e -> {
     /*   if (e.getCode() == KeyCode.D) {
            cartesianCoordinateSystemView.moveCenter(cartesianCoordinateSystemView.gerUnRelativeCoordinates(new Coordinates(-1, 0)));
        }
        if(e.getCode()==KeyCode.W){
            cartesianCoordinateSystemView.moveCenter(cartesianCoordinateSystemView.gerUnRelativeCoordinates(new Coordinates(0, -1)));
        }*/
    };


    public MainView() {
        mainToolBarInit();
        cartesianCoordinateSystemView.prefHeightProperty().bind(heightProperty());
        cartesianCoordinateSystemView.prefWidthProperty().bind(widthProperty());
        cartesianCoordinateSystemView.setOnMouseDragged(mouseMoved);
        cartesianCoordinateSystemView.setOnMousePressed(mouseClicked);
        cartesianCoordinateSystemView.setOnScroll(scroll);
        setOnKeyPressed(keyPressed);
        getChildren().addAll(cartesianCoordinateSystemView, mainToolBar);
    }

    private void mainToolBarInit() {
        mainToolBar.getItems().addAll(new ActionButton("Update center", setCenter));
    }
}

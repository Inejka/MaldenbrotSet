package Application.mainWindow;

import Support.AnotherThread;
import Support.Coordinates;
import javafx.concurrent.Task;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CartesianCoordinateSystemView extends Pane {

    ImageView imageView = new ImageView();
    CartesianCoordinateSystem cartesianCoordinateSystem = new CartesianCoordinateSystem();
    Painter painter = new Painter();

    public CartesianCoordinateSystemView() {
        getChildren().add(imageView);
        imageView.setFitHeight(1);
        cartesianCoordinateSystem.height = 1;
        cartesianCoordinateSystem.width = 1;
        imageView.setFitWidth(1);
        widthProperty().addListener((observableValue, number, t1) -> {
            imageView.setFitWidth(t1.doubleValue());
            cartesianCoordinateSystem.width = t1.intValue();
            updateUI();

        });
        heightProperty().addListener((observableValue, number, t1) -> {
            imageView.setFitHeight(t1.doubleValue());
            cartesianCoordinateSystem.height = t1.intValue();
            updateUI();
        });
    }

    public void updateUI() {
        AnotherThread task = new AnotherThread(painter, cartesianCoordinateSystem, imageView);
        task.run();
    }

    public void updateCenter() {
        cartesianCoordinateSystem.updateCenter();
        updateUI();
    }

    public Coordinates getCenter() {
        return cartesianCoordinateSystem.center;
    }

    public void setCenter(Coordinates coordinates) {
        cartesianCoordinateSystem.setCenter(coordinates);
        updateUI();
    }


/*    private WritableImage writeImage() {
        List<Coordinates> kostil = new LinkedList<>();
        int width = (int) Math.round(imageView.getFitWidth()), height = (int) Math.round(imageView.getFitHeight());
        WritableImage toReturn = new WritableImage(width, height);
        PixelWriter writer = toReturn.getPixelWriter();
        for (int i = 0; i < width; i++)
            for (int j = 0; j < height; j++) {
                Coordinates normalCoordinates = getRelativeCoordinates(new Coordinates(i, j));
                writer.setColor(i, j, Color.WHITE);
                if (normalCoordinates.getX().compareTo(BigDecimal.ZERO) == 0) writer.setColor(i, j, Color.BLACK);
                if (normalCoordinates.getY().compareTo(BigDecimal.ZERO) == 0) writer.setColor(i, j, Color.BLACK);
                //  int color = isMald(normalCoordinates);
                // if (color == 100) writer.setColor(i, j, Color.BLACK);
                // else writer.setColor(i, j, Color.color(color / 100.0, color / 100.0, color / 100.0));
                //if (Math.abs(normalCoordinates.getY()) < currentStep.getY() &&
                //       normalCoordinates.getX() % 10.0 == 0) kostil.add(new Coordinates(i, j));
                // if (i == (int) center.getY()) writer.setColor(i, j, Color.BLACK);
                //if (j == (int) center.getX()) writer.setColor(i, j, Color.BLACK);
            }
        //setVerticalStick(kostil, writer);
        return toReturn;
    }*/


}

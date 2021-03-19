package Application.mainWindow;

import Support.Coordinates;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Group;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.util.LinkedList;
import java.util.List;

public class CartesianCoordinateSystemView extends Pane {

    ImageView imageView = new ImageView();

    Coordinates currentStep = new Coordinates(0.1, 0.1);
    Coordinates center = new Coordinates(0, 0);
    double scale = 1.1;

    public CartesianCoordinateSystemView() {
        getChildren().add(imageView);
        imageView.setFitHeight(1);
        imageView.setFitWidth(1);
        widthProperty().addListener((observableValue, number, t1) -> {
            imageView.setFitWidth(t1.doubleValue());
            updateUI();

        });
        heightProperty().addListener((observableValue, number, t1) -> {
            imageView.setFitHeight(t1.doubleValue());
            updateUI();
        });
    }

    public void updateCenter() {
        center.setX((Math.round(imageView.getFitWidth())) / 2.0);
        center.setY((Math.round(imageView.getFitHeight())) / 2.0);
        updateUI();
    }

    public Coordinates getCenter() {
        return center;
    }

    public void setCenter(Coordinates center) {
        this.center = center;
        updateUI();
    }

    public void moveCenter(Coordinates delta) {
        center.setX(delta.getX());
        center.setY(delta.getY());
        updateUI();
    }

    public void setCenter(double X, double Y) {
        center.setX(X);
        center.setY(Y);
    }

    private void updateUI() {
        imageView.setImage(writeImage());
    }

    private WritableImage writeImage() {
        List<Coordinates> kostil = new LinkedList<>();
        int width = (int) Math.round(imageView.getFitWidth()), height = (int) Math.round(imageView.getFitHeight());
        WritableImage toReturn = new WritableImage(width, height);
        PixelWriter writer = toReturn.getPixelWriter();
        for (int i = 0; i < width; i++)
            for (int j = 0; j < height; j++) {
                Coordinates normalCoordinates = getRelativeCoordinates(new Coordinates(i, j));
                writer.setColor(i, j, Color.WHITE);
                if (Math.abs(normalCoordinates.getX()) < currentStep.getX()) writer.setColor(i, j, Color.BLACK);
                if (Math.abs(normalCoordinates.getY()) < currentStep.getY()) writer.setColor(i, j, Color.BLACK);
                int color = isMald(normalCoordinates);
                if (color == 100) writer.setColor(i, j, Color.BLACK);
                else writer.setColor(i, j, Color.color(color / 100.0, color / 100.0, color / 100.0));
                //if (Math.abs(normalCoordinates.getY()) < currentStep.getY() &&
                //       normalCoordinates.getX() % 10.0 == 0) kostil.add(new Coordinates(i, j));
                // if (i == (int) center.getY()) writer.setColor(i, j, Color.BLACK);
                //if (j == (int) center.getX()) writer.setColor(i, j, Color.BLACK);
            }
        setVerticalStick(kostil, writer);
        return toReturn;
    }

    private int isMald(Coordinates normalCoordinates) {
        Coordinates start = new Coordinates(0, 0);
        for (int i = 0; i < 100; i++) {
            Coordinates copy = new Coordinates(start.getX(), start.getY());
            start.setX(copy.getX() * copy.getX() - copy.getY() * copy.getY() + normalCoordinates.getX());
            start.setY((copy.getX() + copy.getX()) * copy.getY() + normalCoordinates.getY());
            if ((start.getY() * start.getY() + start.getX() * start.getX()) > 4) return i;
        }
        return 100;
    }

    public void zoom(Coordinates mousePos) {
        setCenter(center.getX() * scale + mousePos.getX() * (1 - scale),
                center.getY() * scale + mousePos.getY() * (1 - scale));
        currentStep.setX(currentStep.getX() / scale);
        currentStep.setY(currentStep.getY() / scale);
        updateUI();
    }

    public void unZoom(Coordinates mousePos) {
        setCenter((mousePos.getX()*(scale-1)+center.getX())/scale,
                (center.getY()+mousePos.getY()*(scale-1))/scale);
        currentStep.setX(currentStep.getX() * scale);
        currentStep.setY(currentStep.getY() * scale);

        updateUI();
    }

    public Coordinates getRelativeCoordinates(Coordinates coordinates) {
        return new Coordinates(currentStep.getX() * (coordinates.getX() - center.getX())
                , currentStep.getY() * (center.getY() - coordinates.getY()));
    }

    public Coordinates gerUnRelativeCoordinates(Coordinates coordinates) {
        return new Coordinates(coordinates.getX() / currentStep.getX() + center.getX(), center.getY() - coordinates.getY() / currentStep.getY());
    }

    private void setVerticalStick(List<Coordinates> coordinatesList, PixelWriter writer) {
        for (Coordinates coordinates : coordinatesList)
            for (int i = -10; i < 11; i++)
                if (coordinates.getX() >= 0 && (coordinates.getY() + i) >= 0 && coordinates.getX() < imageView.getFitWidth()
                        && (coordinates.getY() + i) < imageView.getFitHeight())
                    writer.setColor((int) coordinates.getX(), (int) coordinates.getY() + i, Color.BLACK);
    }
}

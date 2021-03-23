package Application.mainWindow;

import Support.Coordinates;
import Support.ThreadTest;

import java.math.BigDecimal;
import java.util.ArrayList;

public class CartesianCoordinateSystem {

    public int width = 1;
    public int height = 1;

    Coordinates currentStep = new Coordinates(0.1, 0.1);
    Coordinates center = new Coordinates(0, 0);
    BigDecimal scale = BigDecimal.valueOf(1.1);

    public void updateCenter() {
        center.setX((Math.round(width)) / 2.0);
        center.setY((Math.round(height)) / 2.0);
    }

    public Coordinates getCenter() {
        return center;
    }

    public void setCenter(Coordinates center) {
        this.center = center;
    }

    public void moveCenter(Coordinates delta) {
        center.setX(delta.getX());
        center.setY(delta.getY());
    }

    public void setCenter(double X, double Y) {
        center.setX(X);
        center.setY(Y);
    }

    public Coordinates[][] getMatr() throws InterruptedException {
        Coordinates[][] toReturn = new Coordinates[width][height];
        ThreadTest test1 = new ThreadTest(toReturn, 0, 0, width / 2, height / 2, currentStep, center);
        ThreadTest test2 = new ThreadTest(toReturn, width / 2, 0, width-1, height / 2, currentStep, center);
        ThreadTest test3 = new ThreadTest(toReturn, 0, height / 2, width / 2, height-1, currentStep, center);
        ThreadTest test4 = new ThreadTest(toReturn, width / 2, height / 2, width-1, height-1, currentStep, center);
        test1.start();
        test2.start();
        test3.start();
        test4.start();
        test1.join();
        test2.join();
        test3.join();
        test4.join();
        return toReturn;
    }


    /*private int isMald(Coordinates normalCoordinates) {
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
    }*/

    public Coordinates getRelativeCoordinates(Coordinates coordinates) {
        return new Coordinates(currentStep.getX().multiply(coordinates.getX().subtract(center.getX()))
                , currentStep.getY().multiply(center.getY().subtract(coordinates.getY())));
    }

   /* public Coordinates gerUnRelativeCoordinates(Coordinates coordinates) {
        return new Coordinates(coordinates.getX() / currentStep.getX() + center.getX(), center.getY() - coordinates.getY() / currentStep.getY());
    }

    private void setVerticalStick(List<Coordinates> coordinatesList, PixelWriter writer) {
        for (Coordinates coordinates : coordinatesList)
            for (int i = -10; i < 11; i++)
                if (coordinates.getX() >= 0 && (coordinates.getY() + i) >= 0 && coordinates.getX() < imageView.getFitWidth()
                        && (coordinates.getY() + i) < imageView.getFitHeight())
                    writer.setColor((int) coordinates.getX(), (int) coordinates.getY() + i, Color.BLACK);
    }*/
}

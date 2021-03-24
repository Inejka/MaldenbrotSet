package Support;

import java.util.ArrayList;

public class ThreadTest extends Thread {

    private Coordinates[][] coordinates;
    int iS, jS, iE, jE;
    Coordinates currentStep, center;

    @Override
    public void run() {
        for (int i = iS; i <= iE; i++)
            for (int j = jS; j <= jE; j++)
                coordinates[i][j] = getRelativeCoordinates(new Coordinates(String.valueOf(i), String.valueOf(j)));
    }

    public ThreadTest(Coordinates[][] coordinates, int iS, int jS, int iE, int jE, Coordinates currentStep, Coordinates center) {
        this.coordinates = coordinates;
        this.iS = iS;
        this.jS = jS;
        this.iE = iE;
        this.jE = jE;
        this.currentStep = currentStep;
        this.center = center;
    }

    public Coordinates getRelativeCoordinates(Coordinates coordinates) {
        return new Coordinates(currentStep.getX().multiply(coordinates.getX().subtract(center.getX()))
                , currentStep.getY().multiply(center.getY().subtract(coordinates.getY())));
    }
}

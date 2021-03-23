package Support;

import Application.mainWindow.CartesianCoordinateSystem;
import Application.mainWindow.Painter;
import javafx.concurrent.Task;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.Date;


public class AnotherThread extends Task<Void> {
    Painter painter;
    CartesianCoordinateSystem cartesianCoordinateSystem;
    ImageView imageView;

    public AnotherThread(Painter painter, CartesianCoordinateSystem cartesianCoordinateSystem, ImageView imageView) {
        this.painter = painter;
        this.cartesianCoordinateSystem = cartesianCoordinateSystem;
        this.imageView = imageView;
    }


    @Override
    protected Void call() throws Exception {
        try {
            System.out.println("start");
            Coordinates[][] a;
            System.out.println(new Date().getTime());
            a = cartesianCoordinateSystem.getMatr();
            System.out.println(new Date().getTime());
            imageView.setImage(painter.paint(a));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}

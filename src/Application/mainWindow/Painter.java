package Application.mainWindow;

import Support.Coordinates;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

import javax.imageio.ImageWriter;
import java.math.BigDecimal;
import java.util.ArrayList;

public class Painter {
    public WritableImage paint(Coordinates[][] coordinates) {
        int width = coordinates.length, height = coordinates[0].length;
        WritableImage toReturn = new WritableImage(width, height);
        PixelWriter writer = toReturn.getPixelWriter();
        for (int i = 0; i < width; i++)
            for (int j = 0; j < height; j++) {
                writer.setColor(i, j, Color.WHITE);
                if (coordinates[i][j].getX().compareTo(BigDecimal.ZERO) == 0)
                    writer.setColor(i, j, Color.BLACK);
                if (coordinates[i][j].getY().compareTo(BigDecimal.ZERO) == 0)
                    writer.setColor(i, j, Color.BLACK);
            }
        return toReturn;
    }
}

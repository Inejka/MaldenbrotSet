package Support;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class ActionButton extends Button {
    public ActionButton(String name, EventHandler<ActionEvent> event) {
        super(name);
        setOnAction(event);
    }
}

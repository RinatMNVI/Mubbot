package pages;

import views.buttons.Button;

import java.util.ArrayList;

/**
 * Created by mrina on 12.10.2018.
 */
public interface PageInterface {

    void setCommand(String command);

    ArrayList<Button> addButtonContent();
}

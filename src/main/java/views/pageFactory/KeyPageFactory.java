package views.pageFactory;

import views.buttons.Button;
import views.markups.Markup;
import views.markups.ReplyMarkup;

import java.util.ArrayList;

/**
 * Created by mrina on 20.09.2018.
 */
public class KeyPageFactory implements Page{

    private String title;
    private ArrayList<Button> buttons;

    public KeyPageFactory(String title, ArrayList<Button> buttons){
        this.title = title;
        this.buttons = buttons;
    }

    @Override
    public String createTitle() {
        return title;
    }

    @Override
    public Markup createMarkup() {
        return new ReplyMarkup(buttons);
    }
}

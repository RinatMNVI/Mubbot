package views.pageFactory;

import views.buttons.Button;
import views.markups.InlineMarkup;
import views.markups.Markup;

import java.util.ArrayList;

/**
 * Created by mrina on 20.09.2018.
 */
public class InlinePageFactory implements Page{

    private String title;
    private ArrayList<Button> buttons;
    private int columnCount;

    public InlinePageFactory(String title, ArrayList<Button> buttons,int columnCount){
        this.title = title;
        this.columnCount = columnCount;
        this.buttons = buttons;
    }

    @Override
    public String createTitle() {
        return title;
    }

    @Override
    public Markup createMarkup() {
        return new InlineMarkup(buttons,columnCount);
    }

}

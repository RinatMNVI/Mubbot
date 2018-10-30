package views.pageFactory;

import views.markups.Markup;

/**
 * Created by mrina on 20.09.2018.
 */
public class PageFactory implements Page{

    private String title;

    public PageFactory(String title){
        this.title = title;
    }

    @Override
    public String createTitle() {
        return title;
    }

    @Override
    public Markup createMarkup() {
        return null;
    }
}

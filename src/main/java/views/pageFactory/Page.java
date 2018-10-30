package views.pageFactory;

import views.markups.Markup;

/**
 * Created by mrina on 20.09.2018.
 */
public interface Page {

    public abstract String createTitle();
    public abstract Markup createMarkup();
}

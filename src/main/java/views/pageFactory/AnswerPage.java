package views.pageFactory;

import views.markups.Markup;

/**
 * Created by mrina on 20.09.2018.
 */
public class AnswerPage {

    private Markup markup;
    private String title;

    public AnswerPage(){}

    public AnswerPage(Page page){
        markup = page.createMarkup();
        title = page.createTitle();
    }

    public Markup getMarkup() {
        return markup;
    }

    public void setMarkup(Markup markup) {
        this.markup = markup;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

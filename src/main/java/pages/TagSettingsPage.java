package pages;

import views.buttons.Button;
import views.buttons.InlineButton;
import views.pageFactory.AnswerPage;
import views.pageFactory.DefaultPage;
import views.pageFactory.InlinePageFactory;

import java.util.ArrayList;

/**
 * Created by mrina on 13.10.2018.
 */
public class TagSettingsPage extends DefaultPage{


    public TagSettingsPage(){
        commands.add(DefaultPages.TAG_SETTINGS);

        content = "Выберите функцию";

        page = new AnswerPage(new InlinePageFactory(content, addButtonContent(),2));
    }

    @Override
    public DefaultPage setCommand(String command) {
        return this;
    }

    @Override
    public ArrayList<Button> addButtonContent() {
        ArrayList<Button> inlineButtons = new ArrayList<>();
        inlineButtons.add(new InlineButton("Удалить", "del_tag"));
        return inlineButtons;
    }
}

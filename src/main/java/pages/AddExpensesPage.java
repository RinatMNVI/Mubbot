package pages;

import config.DataSingl;
import db.User;
import db.domain.MubUser;
import views.buttons.Button;
import views.buttons.InlineButton;
import views.pageFactory.AnswerPage;
import views.pageFactory.DefaultPage;
import views.pageFactory.InlinePageFactory;

import java.util.ArrayList;

/**
 * Created by mrina on 12.10.2018.
 */
public class AddExpensesPage extends DefaultPage {

    public AddExpensesPage(User user){
        this.user = user;
        commands.add(DefaultPages.ADD_EXPENSES);

        content = "Выберите Тег";

        page = new AnswerPage(new InlinePageFactory(content, addButtonContent(),3));
    }

    @Override
    public DefaultPage setCommand(String command) {
        return this;
    }

    @Override
    public ArrayList<Button> addButtonContent() {
        ArrayList<Button> inlineButtons = new ArrayList<>();
        if (user.getTagsList() != null && !user.getTagsList().isEmpty())
            user.getTagsList().forEach( tags ->
                    inlineButtons.add(new InlineButton(tags.getName()))
            );
        inlineButtons.add(new InlineButton("+"));
        return inlineButtons;
    }
}

package pages;

import config.DataSingl;
import db.User;
import db.domain.MubUser;
import db.service.UserService;
import views.buttons.Button;
import views.buttons.InlineButton;
import views.pageFactory.AnswerPage;
import views.pageFactory.DefaultPage;
import views.pageFactory.PageFactory;

import java.util.ArrayList;

/**
 * Created by mrina on 12.10.2018.
 */
public class AddTagPage extends DefaultPage {

    public AddTagPage(User user){
        this.user = user;

        commands.add("+");

        content = "Введите тег";

        page = new AnswerPage(new PageFactory(content));
    }


    @Override
    public DefaultPage setCommand(String command) {
        user.setCurrentCommand(command);
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

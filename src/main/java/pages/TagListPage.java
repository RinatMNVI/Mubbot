package pages;

import config.DataSingl;
import db.User;
import db.domain.MubUser;
import db.domain.Tags;
import views.buttons.Button;
import views.buttons.InlineButton;
import views.pageFactory.AnswerPage;
import views.pageFactory.DefaultPage;
import views.pageFactory.InlinePageFactory;

import java.util.ArrayList;

/**
 * Created by mrina on 12.10.2018.
 */
public class TagListPage extends DefaultPage {

    public TagListPage(User user){
        this.user = user;

        commands.add(DefaultPages.TAG_LIST);

        content = "Список тегов";

        page = new AnswerPage(new InlinePageFactory(content, addButtonContent(),2));
    }


    @Override
    public DefaultPage setCommand(String command) {
        return this;
    }

    @Override
    public ArrayList<Button> addButtonContent() {
        ArrayList<Button> inlineButtons = new ArrayList<>();
        if (user.getTagsList() != null && !user.getTagsList().isEmpty()) {
            for (Tags tags : user.getTagsList()) {
                inlineButtons.add(new InlineButton(tags.getName(), tags.getName() + "_set"));
            }
        }
        return inlineButtons;
    }
}

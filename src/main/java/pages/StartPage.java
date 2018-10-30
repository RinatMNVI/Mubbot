package pages;

import views.buttons.Button;
import views.buttons.ReplyButton;
import views.pageFactory.AnswerPage;
import views.pageFactory.DefaultPage;
import views.pageFactory.KeyPageFactory;

import java.util.ArrayList;

import static pages.DefaultPages.getButtonList;

/**
 * Created by mrina on 12.10.2018.
 */
public class StartPage extends DefaultPage {

    public StartPage(){
        commands.add("/start");

        content = "\n\nПривет я Mubbot" +
                "\nЯ помогу тебе с организацией своих расходов" +
                "\nВыбери действие\n\n";

        page = new AnswerPage(new KeyPageFactory(content,addButtonContent()));
    }

    @Override
    public DefaultPage setCommand(String command) {
        return this;
    }

    @Override
    public ArrayList<Button> addButtonContent() {
        ArrayList<Button> replyButtons = new ArrayList<>();
        return getButtonList(new ReplyButton(DefaultPages.SHOW_EXPENSES)
                ,new ReplyButton(DefaultPages.TAG_LIST)
                ,new ReplyButton(DefaultPages.ADD_EXPENSES));
    }

}

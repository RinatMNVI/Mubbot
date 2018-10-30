package views.pageFactory;

import db.User;
import db.domain.MubUser;
import views.buttons.Button;

import java.util.ArrayList;

/**
 * Created by mrina on 12.10.2018.
 */
public abstract class DefaultPage  {
    protected ArrayList<String> commands = new ArrayList<>();
    protected AnswerPage page;
    protected String content;
    protected User user;


    public abstract DefaultPage setCommand(String command);

    public abstract ArrayList<Button> addButtonContent();

    public ArrayList<String> getCommands() {
        return commands;
    }

    public AnswerPage getPage() {
        return page;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}

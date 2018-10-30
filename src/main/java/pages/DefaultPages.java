package pages;

import answers.Answer;
import answers.EditAnswer;
import answers.SendAnswer;
import db.User;
import db.domain.MubUser;
import views.buttons.Button;
import views.pageFactory.AnswerPage;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by mrina on 26.09.2018.
 */
public class DefaultPages{

    public static final String ADD_EXPENSES = "Добавить";
    public static final String SHOW_EXPENSES = "Показать расходы";
    public static final String TAG_LIST = "Список тегов";
    public static final String TAG_SETTINGS = "tag_settings";
    public static final String DETALIZATION = "Детализация";
    public static final String INSERT_SUM = "Введите сумму";

    protected User user;

    public DefaultPages(User user){
        this.user = user;
    }

    public static boolean isNumb(String data){
        try{
            Integer.parseInt(data);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public static ArrayList<Button> getButtonList(Button...arg){
        return new ArrayList<Button>(Arrays.asList(arg));
    }

    public Answer getDefPage(String command, Long chatId){
        AnswerPage answerPage = findDef(command);
        if (answerPage != null)
            return new Answer(new SendAnswer(answerPage, chatId));
        return null;
    }

    public Answer getDefPage(String command, Long chatId, Integer callback){
        AnswerPage answerPage = findDef(command);
        if (answerPage != null)
            return new Answer(new EditAnswer(answerPage, chatId, callback));
        return null;
    }

    public AnswerPage findDef(String command){
        AnswerPage answerPage = null;
        switch (command){
            case "/start": answerPage = new StartPage().setCommand(command).getPage(); break;
            case TAG_LIST: answerPage = new TagListPage(user).setCommand(command).getPage(); break;
            case "+": answerPage = new AddTagPage(user).setCommand(command).getPage(); break;
            case SHOW_EXPENSES: answerPage = new ShowExpensesPage(user).setCommand(command).getPage(); break;
            case TAG_SETTINGS: answerPage = new TagSettingsPage().setCommand(command).getPage(); break;
            case ADD_EXPENSES: answerPage = new AddExpensesPage(user).setCommand(command).getPage(); break;
        }
        return answerPage;
    }
}

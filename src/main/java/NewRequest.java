import answers.Answer;
import config.DataSingl;
import db.User;
import db.service.UserService;
import org.telegram.telegrambots.api.objects.CallbackQuery;
import org.telegram.telegrambots.api.objects.Update;

import java.util.LinkedList;

/**
 * Created by mrina on 13.10.2018.
 */
public class NewRequest extends Thread{

    private LinkedList<Answer> answers;
    private String messageText;
    private Long chatId;
    private Integer userId;
    private String userName;
    private boolean isCallback;
    private CallbackQuery callBack;
    private String data;
    private Bot bot;
    private User user;

    public NewRequest(Update update, boolean isCallback, Bot bot){
        this.isCallback = isCallback;
        this.bot = bot;

        if (!isCallback){
            userId = update.getMessage().getFrom().getId();
            userName = update.getMessage().getFrom().getFirstName();
            chatId = update.getMessage().getChatId();
            messageText = update.getMessage().getText();
        }else {
            callBack = update.getCallbackQuery();
            data = callBack.getData();
            chatId = callBack.getMessage().getChatId();
            userId = callBack.getFrom().getId();
            userName = callBack.getFrom().getFirstName();
        }

        this.start();
    }


    public void run(){

        user = DataSingl.newInstance().getUser(userId, userName);

        if (!isCallback){
            answers = new AnswerController(messageText,chatId, user);
        }else {
            answers = new AnswerController(data,chatId,callBack, user);
        }

        if (answers.size() > 0)
            bot.send(answers);
    }
}

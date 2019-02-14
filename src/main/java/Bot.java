import answers.Answer;
import config.BotConfig;
import config.DataSingl;
import db.service.UserService;
import org.telegram.telegrambots.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.api.objects.CallbackQuery;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.util.LinkedList;


/**
 * Created by mrina on 15.09.2018.
 */

public class Bot extends TelegramLongPollingBot{

    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            new NewRequest(update,false, this);
        } else if (update.hasCallbackQuery()){
            new NewRequest(update,true, this);
        }
    }


    public synchronized void send(LinkedList<Answer> answers){
        for (Answer answer: answers){
            if (answer.getAnswerType() instanceof SendMessage) {
                try {
                    sendMessage((SendMessage) answer.getAnswerType());
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
            else if (answer.getAnswerType() instanceof AnswerCallbackQuery)
                try {
                    answerCallbackQuery((AnswerCallbackQuery) answer.getAnswerType());
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }

            else if (answer.getAnswerType() instanceof EditMessageText)
                try {
                    execute((EditMessageText) answer.getAnswerType());
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
    }

    public String getBotUsername() {
        return BotConfig.BOT_NAME;
    }

    public String getBotToken() {
        System.out.println(1111);
        System.out.println(2222);
        return BotConfig.BOT_TOKEN;
    }

}

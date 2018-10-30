package answers;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import views.pageFactory.AnswerPage;

/**
 * Created by mrina on 20.09.2018.
 */
public class SendAnswer extends SendMessage implements AnswerType {

    public SendAnswer(AnswerPage answerPage, Long chatId){
        super(chatId,answerPage.getTitle());
        this.enableMarkdown(true);
        try {
            this.setReplyMarkup((InlineKeyboardMarkup) answerPage.getMarkup());
        }catch (Exception e){
            this.setReplyMarkup((ReplyKeyboardMarkup) answerPage.getMarkup());
        }
    }

    public SendAnswer(String title, Long chatId){
        super(chatId,title);
        this.enableMarkdown(true);
    }
}

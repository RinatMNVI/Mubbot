package answers;

import org.telegram.telegrambots.api.methods.updatingmessages.EditMessageText;
import views.pageFactory.AnswerPage;
import views.markups.InlineMarkup;

/**
 * Created by mrina on 20.09.2018.
 */
public class EditAnswer extends EditMessageText implements AnswerType {

    public EditAnswer(AnswerPage answerPage, Long chatId,Integer messageId){
        super();
        this.setChatId(chatId)
                .setMessageId(messageId)
                .setText(answerPage.getTitle())
                .setReplyMarkup((InlineMarkup)answerPage.getMarkup());
    }

    public EditAnswer(AnswerPage answerPage, String chatId,Integer messageId){
        super();
        this.setChatId(chatId)
                .setMessageId(messageId)
                .setText(answerPage.getTitle())
                .setReplyMarkup((InlineMarkup)answerPage.getMarkup());
    }
}

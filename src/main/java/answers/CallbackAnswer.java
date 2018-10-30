package answers;

import org.telegram.telegrambots.api.methods.AnswerCallbackQuery;

/**
 * Created by mrina on 20.09.2018.
 */
public class CallbackAnswer extends AnswerCallbackQuery implements AnswerType {

    public CallbackAnswer(String id){
        super();
        setCallbackQueryId(id);
    }
}

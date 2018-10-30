import answers.Answer;
import answers.CallbackAnswer;
import answers.EditAnswer;
import answers.SendAnswer;
import db.User;
import db.domain.Expenses;
import db.domain.Tags;
import db.service.ExpensesService;
import db.service.TagService;
import org.telegram.telegrambots.api.objects.CallbackQuery;
import pages.DefaultPages;
import pages.ShowExpensesPage;
import pages.TagSettingsPage;
import views.pageFactory.AnswerPage;
import views.pageFactory.PageFactory;

import java.util.LinkedList;

/**
 * Created by mrina on 20.09.2018.
 */
public class AnswerController extends LinkedList<Answer> {

    private String text;
    private Long chatId;
    private CallbackQuery callback;

    private String currentTag;
    private String currentCommand;

    private User user;

    private DefaultPages defPages;

    public AnswerController(String text, Long chatId, User user){
        super();
        this.text = text;
        this.currentCommand = user.getCurrentCommand();
        this.currentTag = user.getCurrentTag();
        this.user = user;
        this.chatId = chatId;
        defPages = new DefaultPages(user);
        answer();
    }

    public AnswerController(String text, Long chatId, CallbackQuery callback, User user){
        super();
        this.text = text;
        this.user = user;
        this.currentCommand = user.getCurrentCommand();
        this.currentTag = user.getCurrentTag();
        this.chatId = chatId;
        this.callback = callback;
        defPages = new DefaultPages(user);
        callbackAnswer();
    }


    public void answer(){
        if (currentCommand == null){
            Answer answer = defPages.getDefPage(text,chatId);
            if (answer != null)
                add(answer);
        } else {
            switch (currentCommand){
                case "+":
                    new TagService().saveTag(new Tags(text, user.getUser()), user);
                    add(new Answer(new SendAnswer("Тег успешно добавлен", chatId)));
                    break;
                case DefaultPages.INSERT_SUM: {
                    String sum = "", comment = "";
                    if (text.contains(":")) {
                        sum = text.substring(0, text.indexOf(":")).trim();
                        comment = text.substring(text.indexOf(":") + 1, text.length()).trim();
                    } else
                        sum = text.trim();
                    if (DefaultPages.isNumb(sum)) {
                        new ExpensesService().saveExpenses(
                                new Expenses(
                                        user.getTag(currentTag),
                                        Integer.parseInt(sum),
                                        user.getUser(),
                                        comment));
                        add(new Answer(new SendAnswer("Расход успешно добавлен", chatId)));
                        add(defPages.getDefPage(DefaultPages.ADD_EXPENSES, chatId));
                    } else {
                        add(new Answer(new SendAnswer("Ошибка ввода!\n\nПовторите попытку", chatId)));
                    }
                    break;
                }
            }
            user.clearTagAndCommand();
        }
    }

    public void callbackAnswer(){

        add(new Answer(new CallbackAnswer(callback.getId())));

        Answer answer = defPages.getDefPage(text, chatId, callback.getMessage().getMessageId());
        if (answer != null){
            add(answer);
        } else {
            switch (text) {
                case DefaultPages.DETALIZATION:
                    add(new Answer(new EditAnswer(new AnswerPage(
                            new PageFactory(ShowExpensesPage.detailsExp(user))),
                            chatId,
                            callback.getMessage().getMessageId()))); break;
                default:
                    if (user.isTagExist(text)) {
                        user.setCurrentTag(text);
                        user.setCurrentCommand(DefaultPages.INSERT_SUM);
                        add(new Answer(new EditAnswer(
                                new AnswerPage(new PageFactory("Тег: " + text + "\n" + DefaultPages.INSERT_SUM)),
                                chatId,
                                callback.getMessage().getMessageId())));

                    }
            }

            if (text.contains("_set")){

                user.setCurrentTag(text.substring(0,text.indexOf("_")));
                TagSettingsPage tSP = new TagSettingsPage();
                tSP.getPage().setTitle("Тег: " + text.substring(0,text.indexOf("_")) + "\n"
                        + tSP.getContent()
                        + "\nПри удалении будет удалена вся информация о теге");
                add(new Answer(new EditAnswer(tSP.getPage(), chatId, callback.getMessage().getMessageId())));

            } else if(text.equals("del_tag")){

                new TagService().deleteTag(user, user.getTag(currentTag));
                add(new Answer(new EditAnswer(new AnswerPage(new PageFactory("Тег удален")), chatId, callback.getMessage().getMessageId())));
                user.clearTagAndCommand();

            } else if(text.equals("detl")){
                add(new Answer(new EditAnswer(new AnswerPage(
                        new PageFactory(ShowExpensesPage.detailsExp(user))),
                        chatId,
                        callback.getMessage().getMessageId())));
            }
        }
    }
}

package pages;

import config.DataSingl;
import db.domain.Tags;

/**
 * Created by mrina on 15.09.2018.
 */
public class Command {

    public static final String ADD_EXPENSES = "Добавить";
    public static final String ADD_BUDGET = "Бюджет";
    public static final String ADD_TAG = "Добавить тег";
    public static final String SHOW_EXPENSES = "Показать расходы";
    public static final String ANALYSIS = "Анализ";
    public static final String TAG_LIST = "Список тегов";
    public static final String TAG_SETTINGS = "tag_settings";

    public static final String INSERT_SUM = "Введите сумму";

    private static Command instance;

    public static Command newInstance(){
        if (instance == null)
            instance = new Command();
        return instance;
    }

    private String currentCommand = null;
    private Tags currentTag = null;

    private Command(){

    }

    public void deleteComAndTag(){ currentCommand = null; currentTag = null;}

    public String getCurrentCommand() {
        return currentCommand;
    }

    public void setCurrentCommand(String currentCommand) {
        this.currentCommand = currentCommand;
    }

    public Tags getCurrentTag() {
        return currentTag;
    }

    public void setCurrentTag(Tags currentTag) {
        this.currentTag = currentTag;
    }

    public void setCurrentTagFromUser(String currentTag) {
        this.currentTag = DataSingl.newInstance().getMainUser().getTagsSet()
                        .stream().filter(tags -> tags.getName().equals(currentTag)).findAny().get();
    }
}

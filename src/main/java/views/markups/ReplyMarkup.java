package views.markups;

import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;
import views.buttons.Button;
import views.buttons.ReplyButton;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by mrina on 20.09.2018.
 */
public class ReplyMarkup extends ReplyKeyboardMarkup implements Markup{

    public ReplyMarkup(ArrayList<Button> keyButtons){
        super();
        List<KeyboardRow> list = new LinkedList<>();

        for (Button key: keyButtons){
            KeyboardRow row = new KeyboardRow();
            row.add((ReplyButton)key);
            list.add(row);
        }

        this.setKeyboard(list);
    }
}

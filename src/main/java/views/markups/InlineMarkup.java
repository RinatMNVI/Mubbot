package views.markups;

import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import views.buttons.Button;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mrina on 20.09.2018.
 */
public class InlineMarkup extends InlineKeyboardMarkup implements Markup{

    private int maxColumnCount = 3;

    public InlineMarkup(ArrayList<Button> inlineButtons, int columnCount){
        super();
        this.maxColumnCount = columnCount;
        List<List<InlineKeyboardButton>> buttonBlock = new ArrayList<List<InlineKeyboardButton>>();
        ArrayList<InlineKeyboardButton> row = new ArrayList<InlineKeyboardButton>();
        for (int i = 0, j = 0, r = maxColumnCount; i < inlineButtons.size(); i++,j++){
            if (j >= r) {
                buttonBlock.add(row);
                row = new ArrayList<InlineKeyboardButton>();
                j = 0;
            }
            row.add((InlineKeyboardButton) inlineButtons.get(i));
        }
        buttonBlock.add(row);
        this.setKeyboard(buttonBlock);
    }

}

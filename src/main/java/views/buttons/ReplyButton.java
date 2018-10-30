package views.buttons;

import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardButton;

/**
 * Created by mrina on 20.09.2018.
 */
public class ReplyButton extends KeyboardButton implements Button{

    public ReplyButton(String title){
        super(title);
    }
}

package views.buttons;

import org.telegram.telegrambots.api.objects.replykeyboard.buttons.InlineKeyboardButton;

/**
 * Created by mrina on 20.09.2018.
 */
public class InlineButton extends InlineKeyboardButton implements Button {

    private String name;
    private String action;

    public InlineButton(String name){
        super();
        this.name = name;
        this.action = name;
        init();
    }

    public InlineButton(String name, String action){
        super();
        this.name = name;
        this.action = action;
        init();
    }
    private void init(){
        this.setText(name);
        this.setCallbackData(action);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}

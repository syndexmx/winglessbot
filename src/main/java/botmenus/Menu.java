package botmenus;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;

public interface Menu {

    public ReplyKeyboardMarkup prepareKeyboard();

}

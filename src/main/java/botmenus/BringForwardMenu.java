package botmenus;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

public class BringForwardMenu implements Menu{

    public ReplyKeyboardMarkup prepareKeyboard() {
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        keyboardMarkup.setSelective(true);
        keyboardMarkup.setResizeKeyboard(true);
        keyboardMarkup.setOneTimeKeyboard(true);
        List<KeyboardRow> keyboard = new ArrayList<>();
        KeyboardRow keyboardRow = new KeyboardRow();
        keyboardRow = new KeyboardRow();
        keyboardRow.add(". Крыло");
        keyboardRow.add(": Крыло2");
        keyboardRow.add("? Сомнение");
        keyboardRow.add("! Утвердить");
        keyboardRow.add("& Отозвать");
        keyboard.add(keyboardRow);
        keyboardRow = new KeyboardRow();
        keyboardRow.add("/a Все");
        keyboardRow.add("/s Решенные");
        keyboardRow.add("/u Нерешенные");
        keyboard.add(keyboardRow);
        keyboardRow = new KeyboardRow();
        keyboardRow.add("/d Сомнительные");
        keyboardRow.add("/c Выдача");
        keyboardRow.add("/start Имя");
        keyboard.add(keyboardRow);

        keyboardMarkup.setKeyboard(keyboard);
        return keyboardMarkup;
    }
}

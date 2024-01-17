package botmenus;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

import static winglesspieces.WinglessService.collectUnsolvedList;

public class UnsolvedMenu implements Menu {


    public ReplyKeyboardMarkup prepareKeyboard() {
        List<List<String>> doubleListOfMenuItems = collectUnsolvedList();
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        keyboardMarkup.setSelective(true);
        keyboardMarkup.setResizeKeyboard(true);
        keyboardMarkup.setOneTimeKeyboard(true);
        List<KeyboardRow> keyboard = new ArrayList<>();
        KeyboardRow keyboardRow = new KeyboardRow();
        keyboardRow.add("/a Все");
        keyboardRow.add("/s Решенные");
        keyboardRow.add("/u Нерешенные");
        keyboard.add(keyboardRow);
        for (List<String> innerList : doubleListOfMenuItems) {
            keyboardRow = new KeyboardRow();
            for (String menuItem : innerList) {
                keyboardRow.add(menuItem);
            }
            keyboard.add(keyboardRow);
        }
        keyboardRow = new KeyboardRow();
        keyboardRow.add("/d Сомнительные");
        keyboardRow.add("/c Выдача");
        keyboardRow.add("/add Добавить");
        keyboard.add(keyboardRow);
        keyboardMarkup.setKeyboard(keyboard);
        return keyboardMarkup;
    }

}

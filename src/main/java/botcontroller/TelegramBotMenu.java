package botcontroller;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

import static winglesspieces.WinglessService.collectDoubleList;

public class TelegramBotMenu {


    public static ReplyKeyboardMarkup prepareKeyboard(){
        List<List<String>> doubleListOfMenuItems = collectDoubleList();
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        keyboardMarkup.setSelective(true);
        keyboardMarkup.setResizeKeyboard(true);
        keyboardMarkup.setOneTimeKeyboard(true);
        List<KeyboardRow> keyboard = new ArrayList<>();
        KeyboardRow keyboardRow = new KeyboardRow();
        keyboardRow.add("/a Все");
        keyboardRow.add("/s Решенные");
        keyboardRow.add("/u Нерешенные");
        keyboardRow.add("/c Выдача");
        keyboard.add(keyboardRow);
        for (List<String> innerList : doubleListOfMenuItems) {
            keyboardRow = new KeyboardRow();
            for (String menuItem : innerList) {
                keyboardRow.add(menuItem);
            }
            keyboard.add(keyboardRow);
        }
        keyboardMarkup.setKeyboard(keyboard);
        return keyboardMarkup;
    }

}

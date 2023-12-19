import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import botcontroller.TelegramBotController;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, TelegramApiException {

            new TelegramBotController(args[0], args[1]);


    }
}

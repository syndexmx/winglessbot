import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import botcontroller.TelegramBotController;

import java.io.IOException;

import static winglesspieces.WinglessService.pullUpdate;

public class Main {
    public static void main(String[] args) throws IOException, TelegramApiException {

            if (args.length==0) {
                System.out.print("*** This Telegram bot need two parameters: ***");
                System.out.print("TELEGRAM_BOT_NAME TELEGRAM_BOT_TOKEN");
                System.out.print("they can ne acquired from @BotFather");
            }
            new TelegramBotController(args[0], args[1]);
            pullUpdate();

    }
}

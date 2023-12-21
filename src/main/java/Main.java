import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import botcontroller.TelegramBotController;

import javax.swing.*;
import java.io.IOException;
import java.time.LocalDateTime;

import static winglesspieces.WinglessService.pullUpdate;
import javax.swing.JOptionPane;

public class Main {
    public static void main(String[] args) throws IOException, TelegramApiException {
        String telegramBotsName;
        String telegramBotsToken;
        if (args.length==0) {
                telegramBotsName = JOptionPane.showInputDialog("Enter bot's name");
                telegramBotsToken = JOptionPane.showInputDialog("Enter bot's token");
            } else {
                telegramBotsName = args[0];
                telegramBotsToken = args[1];
            }
            new TelegramBotController(telegramBotsName, telegramBotsToken);
            pullUpdate();
            System.out.println(LocalDateTime.now().toString() + "The bot is supposed to be on-line now ...");

    }
}

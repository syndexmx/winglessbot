import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import botcontroller.TelegramBotController;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

import static winglesspieces.WinglessService.pullUpdate;
import static winglesspieces.WinglessService.setWinglessPiecesBaseDat;

import javax.swing.JOptionPane;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Main {
    public static void main(String[] args) throws IOException, TelegramApiException {
        String telegramBotsName;
        String telegramBotsToken;
        if (args.length==0) {
                telegramBotsName = JOptionPane.showInputDialog("Enter bot's name");
                telegramBotsToken = JOptionPane.showInputDialog("Enter bot's token");

                JFileChooser fileChooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("Wingless pieces keeper file","dat");
                fileChooser.setFileFilter(filter);
                fileChooser.setCurrentDirectory(new File("."));
                fileChooser.setSelectedFile(new File("winglesspiecesbase.dat"));
                int result = fileChooser.showSaveDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    setWinglessPiecesBaseDat(fileChooser.getSelectedFile().getAbsolutePath());
                }

            } else {
                telegramBotsName = args[0];
                telegramBotsToken = args[1];
            }
            new TelegramBotController(telegramBotsName, telegramBotsToken);
            pullUpdate();
            System.out.println(LocalDateTime.now().toString() + "The bot is supposed to be on-line now ...");

    }
}

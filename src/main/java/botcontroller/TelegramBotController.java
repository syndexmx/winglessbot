package botcontroller;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import services.CollectiveNotifier;
import services.IndividualService;
import services.UserRepository;

import java.io.IOException;

import static services.UserRepository.getMenu;


public class TelegramBotController extends TelegramLongPollingBot {
    final String BOT_NAME;
    final String BOT_TOKEN;


    public TelegramBotController(String botName, String botToken) throws TelegramApiException {
        BOT_NAME = botToken;
        BOT_TOKEN = botToken;
        TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
        botsApi.registerBot(this);
        CollectiveNotifier.settController(this);
    }

    @Override
    public void onUpdateReceived(Update update)  {
        if (update.hasMessage() && update.getMessage().hasText()){
            String userFullCommand = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();
            IndividualService iService = UserRepository.getIndividualService(chatId);
            try {
                iService.parseCommand(this, userFullCommand, chatId);
                BotLogger.botLog( " Request from #"+chatId+": "+userFullCommand);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }


    public void sendMessage (String m, long chatId) {
        // How to from  https://github.com/rubenlagus/TelegramBots/wiki/Getting-Started
        SendMessage message = new SendMessage(); // Create a SendMessage object with mandatory fields
        message.setChatId(chatId);
        message.setText(m);
        message.setReplyMarkup(getMenu(chatId).prepareKeyboard());
        // message.setParseMode("markdown");

        try {
            execute(message); // Call method to send the message
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return BOT_NAME;
    }

    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }

}

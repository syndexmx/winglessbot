package botcontroller;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import services.IndividualService;
import services.UserRepository;

public class TelegramBotController extends TelegramLongPollingBot {
    final String BOT_NAME;
    final String BOT_TOKEN;


    public TelegramBotController(String botName, String botToken) throws TelegramApiException {
        BOT_NAME = botToken;
        BOT_TOKEN = botToken;
        TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
        botsApi.registerBot(this);
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()){
            String userFullCommand = update.getMessage().getText().toLowerCase();
            long chatId = update.getMessage().getChatId();

            System.out.println("Request from #"+chatId+": "+userFullCommand);

            IndividualService iService = UserRepository.getIndividualService(chatId);
            iService.parseCommand(this, userFullCommand, chatId);

        }
    }


    public void sendMessage (String m, long chatId){
        // How to from  https://github.com/rubenlagus/TelegramBots/wiki/Getting-Started
        SendMessage message = new SendMessage(); // Create a SendMessage object with mandatory fields
        message.setChatId(chatId);
        message.setText( m );
        message.setParseMode("markdown");

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

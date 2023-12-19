package services.servicestates;

import botcontroller.TelegramBotController;
import services.ServiceState;

public class AboutState implements ServiceState {

    final String PRESENTATION_TEXT = """
            *Бескрылый* бот. Ассистент по бескрылкам\s
            \s
            _Чтобы узнать список команд введите:_\s
            /help  \s
            """;

    final String TITLE_PICTURE = "https://artincontext.org/wp-content/uploads/2021/11/Nike-of-Samothrace.jpg";

    @Override
    public ServiceState processRequest(TelegramBotController tController, String command, long chatId) {
        return new GeneralState();
    }

    @Override
    public ServiceState onEnter(TelegramBotController tController, String s, long chatId) {
        tController.sendMessage(PRESENTATION_TEXT +"\n\n"+TITLE_PICTURE, chatId);
        return new GeneralState();
    }
}

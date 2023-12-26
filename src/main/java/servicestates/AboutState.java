package servicestates;

import botmenus.MainMenu;
import botcontroller.TelegramBotController;

import static services.UserRepository.setMenu;

public class AboutState implements ServiceState {

    final String PRESENTATION_TEXT = """
            *Бескрылый* бот. Ассистент по бескрылкам\s
            \s
            Для управления используйте вкнопки меню внизу
            """;

    final String TITLE_PICTURE = "https://artincontext.org/wp-content/uploads/2021/11/Nike-of-Samothrace.jpg";

    @Override
    public ServiceState processRequest(TelegramBotController tController, String command, long chatId) {
        return new GeneralState();
    }

    @Override
    public ServiceState onEnter(TelegramBotController tController, String s, long chatId) {
        setMenu(chatId, new MainMenu());
        tController.sendMessage(PRESENTATION_TEXT +"\n\n"+TITLE_PICTURE, chatId);
        return new GeneralState();
    }
}

package servicestates;

import botmenus.DoubtfulMenu;
import botcontroller.TelegramBotController;

import static services.UserRepository.setMenu;
import static winglesspieces.WinglessService.fetchDoubtful;

public class ShowDoubtfulState implements ServiceState {

    final String INTRO_TEXT = """
            Под сомнением:\s

            """;

    @Override
    public ServiceState processRequest(TelegramBotController tController, String command, long chatId) {

        return new GeneralState();
    }

    @Override
    public ServiceState onEnter(TelegramBotController tController, String s, long chatId) {
        setMenu(chatId, new DoubtfulMenu());
        tController.sendMessage(INTRO_TEXT + fetchDoubtful(), chatId);
        return new GeneralState();
    }
}
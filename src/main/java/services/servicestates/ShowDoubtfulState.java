package services.servicestates;

import botcontroller.DoubtfulMenu;
import botcontroller.TelegramBotController;
import services.ServiceState;

import static services.UserRepository.setMenu;
import static winglesspieces.WinglessService.fetchDoubtful;
import static winglesspieces.WinglessService.fetchSolvedTasks;

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
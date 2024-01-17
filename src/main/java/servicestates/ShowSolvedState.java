package servicestates;

import botmenus.SolvedMenu;
import botcontroller.TelegramBotController;

import static services.UserRepository.setMenu;
import static winglesspieces.WinglessService.fetchSolvedTasks;

public class ShowSolvedState implements ServiceState {

    final String INTRO_TEXT = """
            Решены:\s

            """;

    @Override
    public ServiceState processRequest(TelegramBotController tController, String command, long chatId) {
        return new GeneralState();
    }

    @Override
    public ServiceState onEnter(TelegramBotController tController, String s, long chatId) {
        setMenu(chatId, new SolvedMenu());
        tController.sendMessage(INTRO_TEXT + fetchSolvedTasks(), chatId);
        return new GeneralState();
    }
}
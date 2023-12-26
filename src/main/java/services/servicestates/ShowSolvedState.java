package services.servicestates;

import botcontroller.SolvedMenu;
import botcontroller.TelegramBotController;
import botcontroller.UnsolvedMenu;
import services.ServiceState;

import static services.UserRepository.setMenu;
import static winglesspieces.WinglessService.fetchSolvedTasks;
import static winglesspieces.WinglessService.fetchUnsolvedTasks;

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
        tController.sendMessage(INTRO_TEXT+fetchSolvedTasks(), chatId);
        return new GeneralState();
    }
}
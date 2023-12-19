package services.servicestates;

import botcontroller.TelegramBotController;
import services.ServiceState;

import static winglesspieces.WinglessService.fetchSolvedTasks;
import static winglesspieces.WinglessService.fetchUnsolvedTasks;

public class ShowSolvedState implements ServiceState {

    final String INTRO_TEXT = """
            *Бескрылый* бот. Решены:\s

            """;

    @Override
    public ServiceState processRequest(TelegramBotController tController, String command, long chatId) {

        return new GeneralState();
    }

    @Override
    public ServiceState onEnter(TelegramBotController tController, String s, long chatId) {
        tController.sendMessage(INTRO_TEXT+fetchSolvedTasks(), chatId);
        return new GeneralState();
    }
}
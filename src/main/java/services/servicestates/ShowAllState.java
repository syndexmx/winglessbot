package services.servicestates;

import botcontroller.TelegramBotController;
import services.ServiceState;

import static winglesspieces.WinglessService.fetchAllTasks;

public class ShowAllState implements ServiceState {

    final String INTRO_TEXT = """
            *Бескрылый* бот. Все задания Тура.\s

            """;

    @Override
    public ServiceState processRequest(TelegramBotController tController, String command, long chatId) {

        return new GeneralState();
    }

    @Override
    public ServiceState onEnter(TelegramBotController tController, String s, long chatId) {
        tController.sendMessage(INTRO_TEXT+fetchAllTasks(), chatId);
        return new GeneralState();
    }
}

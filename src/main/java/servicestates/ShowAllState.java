package servicestates;

import botmenus.MainMenu;
import botcontroller.TelegramBotController;

import static services.UserRepository.setMenu;
import static winglesspieces.WinglessService.fetchAllTasks;

public class ShowAllState implements ServiceState {

    final String INTRO_TEXT = """
            Все задания Тура.\s

            """;

    @Override
    public ServiceState processRequest(TelegramBotController tController, String command, long chatId) {

        return new GeneralState();
    }

    @Override
    public ServiceState onEnter(TelegramBotController tController, String s, long chatId) {
        setMenu(chatId, new MainMenu());
        tController.sendMessage(INTRO_TEXT+fetchAllTasks(), chatId);
        return new GeneralState();
    }
}

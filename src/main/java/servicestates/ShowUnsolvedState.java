package servicestates;

import botcontroller.TelegramBotController;
import botmenus.UnsolvedMenu;

import static services.UserRepository.setMenu;
import static winglesspieces.WinglessService.fetchUnsolvedTasks;

public class ShowUnsolvedState implements ServiceState {

    final String INTRO_TEXT = """
            Не решены:\s

            """;

    @Override
    public ServiceState processRequest(TelegramBotController tController, String command, long chatId) {

        return new GeneralState();
    }

    @Override
    public ServiceState onEnter(TelegramBotController tController, String s, long chatId) {
        setMenu(chatId, new UnsolvedMenu());
        tController.sendMessage(INTRO_TEXT+fetchUnsolvedTasks(), chatId);
        return new GeneralState();
    }
}

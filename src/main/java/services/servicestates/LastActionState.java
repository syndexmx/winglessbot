package services.servicestates;

import botcontroller.MainMenu;
import botcontroller.TelegramBotController;
import services.ServiceState;

import static botcontroller.BotLogger.getLastAction;
import static services.UserRepository.setMenu;
import static winglesspieces.WinglessService.fetchAllTasks;

public class LastActionState  implements ServiceState {

    @Override
    public ServiceState processRequest(TelegramBotController tController, String command, long chatId) {

        return new GeneralState();
    }

    @Override
    public ServiceState onEnter(TelegramBotController tController, String s, long chatId) {
        setMenu(chatId, new MainMenu());
        tController.sendMessage(getLastAction(), chatId);
        return new GeneralState();
    }
}

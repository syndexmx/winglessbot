package services.servicestates;

import botcontroller.MainMenu;
import botcontroller.TelegramBotController;
import services.ServiceState;

import static botcontroller.BotLogger.getLastAction;
import static botcontroller.BotLogger.getLastActions;
import static services.UserRepository.setMenu;

public class LastActionsState  implements ServiceState {

    @Override
    public ServiceState processRequest(TelegramBotController tController, String command, long chatId) {

        return new GeneralState();
    }

    @Override
    public ServiceState onEnter(TelegramBotController tController, String s, long chatId) {
        setMenu(chatId, new MainMenu());
        tController.sendMessage(getLastActions(), chatId);
        return new GeneralState();
    }
}

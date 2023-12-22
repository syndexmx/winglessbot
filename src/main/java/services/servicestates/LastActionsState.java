package services.servicestates;

import botcontroller.TelegramBotController;
import services.ServiceState;

import static botcontroller.BotLogger.getLastAction;
import static botcontroller.BotLogger.getLastActions;

public class LastActionsState  implements ServiceState {

    @Override
    public ServiceState processRequest(TelegramBotController tController, String command, long chatId) {

        return new GeneralState();
    }

    @Override
    public ServiceState onEnter(TelegramBotController tController, String s, long chatId) {
        tController.sendMessage(getLastActions(), chatId);
        return new GeneralState();
    }
}

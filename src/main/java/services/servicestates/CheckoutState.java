package services.servicestates;

import botcontroller.TelegramBotController;
import services.ServiceState;

import static winglesspieces.WinglessService.fetchSolvedTasks;

public class CheckoutState  implements ServiceState {


    @Override
    public ServiceState processRequest(TelegramBotController tController, String command, long chatId) {
        return new GeneralState();
    }

    @Override
    public ServiceState onEnter(TelegramBotController tController, String s, long chatId) {
        tController.sendMessage(fetchSolvedTasks(), chatId);
        return new GeneralState();
    }
}
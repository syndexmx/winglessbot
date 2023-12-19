package services.servicestates;

import botcontroller.TelegramBotController;
import services.ServiceState;

import static services.ServiceStateSwitcher.switchToMonoState;
import static services.ServiceStateSwitcher.switchToState;

public class GeneralState implements ServiceState {



    @Override
    public ServiceState processRequest(TelegramBotController tController, String request, long chatId) {
        char ch = request.charAt(0);
        switch (ch) {
            case ('/') -> {
                return switchToState(tController, request, chatId);
            }
            case ('#') -> {
                return switchToMonoState(tController, request, chatId);
            }
            default -> {
                return this;
            }
        }
        //return this;
    }

    @Override
    public ServiceState onEnter(TelegramBotController tController, String s, long chatId) {
        return new GeneralState();
    }


}

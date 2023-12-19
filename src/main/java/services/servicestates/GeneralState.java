package services.servicestates;

import botcontroller.TelegramBotController;
import services.ServiceState;

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
                tController.sendMessage("Ввод ответа", chatId);
                //TODO : Обработать полученный ответ
                return this;
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

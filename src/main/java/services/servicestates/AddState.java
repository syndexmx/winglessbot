package services.servicestates;

import botcontroller.TelegramBotController;
import services.ServiceState;

public class AddState implements ServiceState {

    @Override
    public ServiceState processRequest(TelegramBotController tController, String command, long chatId) {

        //TODO : Обработать полученные бескрылки
        tController.sendMessage("Добавление выполнено", chatId);
        return new GeneralState();
    }

    @Override
    public ServiceState onEnter(TelegramBotController tController, String s, long chatId) {
        tController.sendMessage("Добавление Бескрылок. Добавляйте бескрылки: ", chatId);
        return new AddState();
    }
}

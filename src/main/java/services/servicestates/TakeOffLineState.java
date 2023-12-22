package services.servicestates;

import botcontroller.TelegramBotController;
import services.ServiceState;

import static services.UserRepository.getAllActivatedUsers;

public class TakeOffLineState implements ServiceState {

    @Override
    public ServiceState processRequest(TelegramBotController tController, String command, long chatId) {
        return new GeneralState();
    }

    @Override
    public ServiceState onEnter(TelegramBotController tController, String s, long chatId) {
        tController.sendMessage("Going off line.", chatId);
        for (Long longInteger : getAllActivatedUsers() ){
            tController.sendMessage("Я, Бот должен временно попрощаться.. До встречи!", longInteger);
        }
        System.exit(0);
        return new GeneralState();
    }
}

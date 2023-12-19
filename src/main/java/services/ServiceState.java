package services;

import botcontroller.TelegramBotController;

public interface ServiceState {

    public ServiceState processRequest(TelegramBotController tController, String command, long chatId);

    public ServiceState onEnter(TelegramBotController tController, String s, long chatId);

}

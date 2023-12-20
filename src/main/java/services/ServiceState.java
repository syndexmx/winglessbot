package services;

import botcontroller.TelegramBotController;

import java.io.IOException;

public interface ServiceState {

    public ServiceState processRequest(TelegramBotController tController, String command, long chatId) throws IOException;

    public ServiceState onEnter(TelegramBotController tController, String s, long chatId);

}

package services;

import botcontroller.TelegramBotController;
import services.servicestates.GeneralState;

import java.io.IOException;

public class IndividualService {

    private ServiceState state = new GeneralState();


    public void setState(ServiceState state) {
        this.state = state;
    }

    public void parseCommand(TelegramBotController tController, String request, long chatId) throws IOException {
        setState(state.processRequest(tController, request, chatId));

    }





}

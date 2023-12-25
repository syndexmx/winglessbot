package services;

import botcontroller.TelegramBotController;
import services.servicestates.GeneralState;

import java.io.IOException;

public class IndividualService {

    private ServiceState state = new GeneralState();

    private String alias = "Anonymous";


    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public void setState(ServiceState state) {
        this.state = state;
    }

    public void parseCommand(TelegramBotController tController, String request, long chatId) throws IOException {
        setState(state.processRequest(tController, request, chatId));

    }





}

package services;

import botcontroller.MainMenu;
import botcontroller.Menu;
import botcontroller.TelegramBotController;
import services.servicestates.GeneralState;

import java.io.IOException;

public class IndividualService {

    private ServiceState state = new GeneralState();

    private Menu menu = new MainMenu();

    private String alias = "Anonymous";


    public String getAlias() {
        return alias;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public Menu getMenu() {
        return menu;
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

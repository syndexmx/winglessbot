package services;

import botcontroller.TelegramBotController;
import services.ServiceState;
import services.servicestates.*;

public class ServiceStateSwitcher {

    public static ServiceState switchToState(TelegramBotController tController, String s, long chatId){
        ServiceState state;
        switch (s){
            case ("/all") -> {
                state = new ShowAllState();
            }
            case ("/solved") -> {
                state = new ShowSolvedState();
            }
            case ("/unsolved") -> {
                state = new ShowUnsolvedState();
            }
            case ("/add") -> {
                state = new AddState();
            }
            case ("/help") -> {
                state = new InstructionState();
            }
            case ("/about") -> {
                state = new AboutState();
            }

            case ("/adminshelp") -> {
                state = new AdminsInstructionState();
            }
            default -> {
                return new AboutState();
            }
        }

        state = state.onEnter(tController, s, chatId);
        return state;
    }


}

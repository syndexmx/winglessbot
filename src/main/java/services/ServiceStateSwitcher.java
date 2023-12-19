package services;

import botcontroller.TelegramBotController;
import services.ServiceState;
import services.servicestates.AboutState;
import services.servicestates.AddState;
import services.servicestates.InstructionState;

public class ServiceStateSwitcher {

    public static ServiceState switchToState(TelegramBotController tController, String s, long chatId){
        ServiceState state;
        switch (s){
            case ("/add") -> {
                state = new AddState();
            }
            case ("/help") -> {
                state = new InstructionState();
            }
            case ("/about") -> {
                state = new AboutState();
            }
            default -> {
                return new AboutState();
            }
        }

        state = state.onEnter(tController, s, chatId);
        return state;
    }


}

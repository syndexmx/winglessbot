package services;

import botcontroller.TelegramBotController;
import services.ServiceState;
import services.servicestates.*;

public class ServiceStateSwitcher {

    public static ServiceState switchToState(TelegramBotController tController, String s, long chatId){
        ServiceState state;
        String[] instructions = s.split("\n")[0].split(" ");
        String instruction = instructions[0];
        switch (instruction){
            case ("/start") -> {
                state = new StartingState();
            }
            case ("/a") -> {
                state = new ShowAllState();
            }
            case ("/all") -> {
                state = new ShowAllState();
            }
            case ("/s") -> {
                state = new ShowSolvedState();
            }
            case ("/solved") -> {
                state = new ShowSolvedState();
            }
            case ("/u") -> {
                state = new ShowUnsolvedState();
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
            case ("/c") -> {
                state = new CheckoutState();
            }
            case ("/checkout") -> {
                state = new CheckoutState();
            }
            case ("/adminshelp") -> {
                state = new AdminsInstructionState();
            }
            case ("/takeoffline") -> {
                state = new TakeOffLineState();
            }
            case ("/last") -> {
                state = new LastActionState();
            }
            case ("/latest") -> {
                state = new LastActionsState();
            }
            default -> {
                return new AboutState();
            }
        }
        state = state.onEnter(tController, s, chatId);
        return state;
    }

    public static ServiceState switchToMonoState(TelegramBotController tController, String s, long chatId) {
        ServiceState state;
        String numberString = s.split("\n")[0]
                .split(" ")[0];
        state = new BringForwardState();
        state = state.onEnter(tController, numberString, chatId);
        return state;
    }

    public static ServiceState switchToPresentation(TelegramBotController tController, String s, long chatId) {
        ServiceState state;
        state = new StartingState();
        state = state.onEnter(tController, "", chatId);
        return state;
    }

}

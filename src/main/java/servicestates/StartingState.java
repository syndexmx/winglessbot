package servicestates;

import botmenus.MainMenu;
import botcontroller.TelegramBotController;

import java.io.IOException;

import static services.ServiceStateSwitcher.switchToMonoState;
import static services.ServiceStateSwitcher.switchToState;
import static services.UserRepository.setAlias;
import static services.UserRepository.setMenu;

public class StartingState implements ServiceState {

    final String PROMPT_TEXT = "Представьтесь, пожалуйста, для удобства: ";

    @Override
    public ServiceState processRequest(TelegramBotController tController, String input, long chatId) throws IOException {
        char ch = input.charAt(0);
        switch (ch) {
            case ('/') -> {
                return switchToState(tController, input, chatId);
            }
            case ('#') -> {
                return switchToMonoState(tController, input, chatId);
            }
            default -> {
                setAlias(chatId, input);
                tController.sendMessage("Доброго дня, "+input, chatId);
                return switchToState(tController, "/general", chatId);
            }
        }
    }

    @Override
    public ServiceState onEnter(TelegramBotController tController, String input, long chatId) {
        setMenu(chatId, new MainMenu());
        tController.sendMessage( PROMPT_TEXT, chatId);
        return this;
    }
}
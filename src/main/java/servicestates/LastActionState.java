package servicestates;

import botmenus.MainMenu;
import botcontroller.TelegramBotController;

import static botcontroller.BotLogger.getLastAction;
import static services.UserRepository.setMenu;

public class LastActionState  implements ServiceState {

    @Override
    public ServiceState processRequest(TelegramBotController tController, String command, long chatId) {

        return new GeneralState();
    }

    @Override
    public ServiceState onEnter(TelegramBotController tController, String s, long chatId) {
        setMenu(chatId, new MainMenu());
        tController.sendMessage(getLastAction(), chatId);
        return new GeneralState();
    }
}

package servicestates;

import botmenus.MainMenu;
import botcontroller.TelegramBotController;

import static services.UserRepository.setMenu;
import static winglesspieces.WinglessService.fetchSolutions;

public class CheckoutState  implements ServiceState {


    @Override
    public ServiceState processRequest(TelegramBotController tController, String command, long chatId) {
        return new GeneralState();
    }

    @Override
    public ServiceState onEnter(TelegramBotController tController, String s, long chatId) {
        setMenu(chatId, new MainMenu());
        tController.sendMessage(fetchSolutions()+"\n", chatId);
        return new GeneralState();
    }
}
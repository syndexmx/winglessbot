package services.servicestates;

import botcontroller.MainMenu;
import botcontroller.TelegramBotController;
import services.ServiceState;

import static services.UserRepository.setMenu;
import static winglesspieces.WinglessService.fetchSolutions;
import static winglesspieces.WinglessService.fetchSolvedTasks;

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
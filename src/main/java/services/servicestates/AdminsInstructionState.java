package services.servicestates;

import botcontroller.MainMenu;
import botcontroller.TelegramBotController;
import services.ServiceState;

import static services.UserRepository.setMenu;

public class AdminsInstructionState implements ServiceState {

    final String INSTRUCTION_TEXT = """
            *Бекрылый* бот. Администраторская помощь\s
            \s
            _Администраторские команды:_\s
            * /takeoffline *      :выключить \s
            * /latest *             :последнее действие \s
            * /last *             :последнее действие \s
            """;

    @Override
    public ServiceState processRequest(TelegramBotController tController, String command, long chatId) {

        return new GeneralState();
    }

    @Override
    public ServiceState onEnter(TelegramBotController tController, String s, long chatId) {
        setMenu(chatId, new MainMenu());
        tController.sendMessage(INSTRUCTION_TEXT, chatId);
        return new GeneralState();
    }
}

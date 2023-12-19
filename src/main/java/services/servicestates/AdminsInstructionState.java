package services.servicestates;

import botcontroller.TelegramBotController;
import services.ServiceState;

public class AdminsInstructionState implements ServiceState {

    final String INSTRUCTION_TEXT = """
            *Бекрылый* бот. Администраторская помощь\s
            \s
            _Администраторские команды:_\s
            * /nop*      :пустая команда \s
            """;

    @Override
    public ServiceState processRequest(TelegramBotController tController, String command, long chatId) {

        return new GeneralState();
    }

    @Override
    public ServiceState onEnter(TelegramBotController tController, String s, long chatId) {
        tController.sendMessage(INSTRUCTION_TEXT, chatId);
        return new GeneralState();
    }
}

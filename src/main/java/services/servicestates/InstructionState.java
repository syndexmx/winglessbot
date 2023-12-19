package services.servicestates;

import botcontroller.TelegramBotController;
import services.ServiceState;

public class InstructionState  implements ServiceState {

    final String INSTRUCTION_TEXT = """
            *Бекрылый* бот. Ассистент по бескрылкам\s

            _Возможные команды:_\s
            * /about*    :о боте \s
            * /help*     :помощь = список команд \s
            * /add*      :добавить бескрылок \s
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

package services.servicestates;

import botcontroller.TelegramBotController;
import services.ServiceState;

import static winglesspieces.WinglessService.fetchAllTasks;

public class InstructionState  implements ServiceState {

    final String INSTRUCTION_TEXT = """
            *Бескрылый* бот. Ассистент по бескрылкам\s

            Возможные команды:\s
            /about      :о боте \s
            /help       :помощь = список команд \s
            /all        :показать все задания в базе \s
            /solved     :показать решенные \s
            /unsolved   :показать нерешенные \s
            /add        :добавить бескрылок \s
            /checkout   :блок ответов \s
            #1          :вывести бескрылку №1 \s
                после вывода бескрылки вводится ответ или новая команда \s 
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

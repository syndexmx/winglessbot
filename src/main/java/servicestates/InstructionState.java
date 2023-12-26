package servicestates;

import botmenus.MainMenu;
import botcontroller.TelegramBotController;

import static services.UserRepository.setMenu;

public class InstructionState  implements ServiceState {

    final String INSTRUCTION_TEXT = """
            *Бескрылый* бот. Ассистент по бескрылкам\s

            Возможные команды:\s
            Для управления используйте вкнопки меню внизу
            Или отправьте сообщение всем пользователям бота \s
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

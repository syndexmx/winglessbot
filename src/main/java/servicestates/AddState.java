package servicestates;

import botmenus.MainMenu;
import botcontroller.TelegramBotController;
import winglesspieces.WinglessService;

import java.io.IOException;

import static services.UserRepository.setMenu;

public class AddState implements ServiceState {

    final String PROMPT_TEXT = """
           Добавление Бескрылок. \s
           Можно добавлять блоком формата:\s
           ===========\s
           \s
           7. ЗАГОЛОВОК /опционально/ \s
           Трата там     \s
           Тра ля лям,   \s
           Па рам, парам \s
           [...].        \s
           \s
           8. \s
           Нанана нана на на нана. \s
           Манана нана Мана на,   \s
           Пана нана на нанана: \s
           Па: ра[...]на.         \s
           \s
           ===========\s
           Добавляйте: \s
           """;

    @Override
    public ServiceState processRequest(TelegramBotController tController, String input, long chatId) throws IOException {
        int addedCount = WinglessService.addNewPortion(input);
        setMenu(chatId, new MainMenu());
        tController.sendMessage("Добавлено бескрылок: " + addedCount, chatId);
        return new GeneralState();
    }

    @Override
    public ServiceState onEnter(TelegramBotController tController, String s, long chatId) {
        tController.sendMessage(PROMPT_TEXT, chatId);
        return new AddState();
    }
}

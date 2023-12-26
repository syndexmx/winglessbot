package services.servicestates;

import botcontroller.BringForwardMenu;
import botcontroller.MainMenu;
import botcontroller.SolvedMenu;
import botcontroller.TelegramBotController;
import services.ServiceState;
import services.UserRepository;

import java.io.IOException;

import static services.CollectiveNotifier.notyfyAllUsers;
import static services.ServiceStateSwitcher.switchToMonoState;
import static services.ServiceStateSwitcher.switchToState;
import static services.UserRepository.setMenu;
import static winglesspieces.WinglessService.*;

public class BringForwardState implements ServiceState {

    private int winglessPieceIndex;

    final String PROMPT_TEXT = """
            Модерация бескрылки:  \s
            . -ввести ответ на бескрылку (команда 'точка'),\s
            & -отозвать ответ,\s
            ? -пометить сомнительным,\s
            ! -подтвердить \s
            \s
            """;
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
            case ('&') -> {
                withdrawSolution(winglessPieceIndex);
                tController.sendMessage("Ответ отозван", chatId);
                return new GeneralState();
            }
            case ('?') -> {
                makeDoubtfull(winglessPieceIndex);
                tController.sendMessage("Ответ помечен сомнительным", chatId);
                return new GeneralState();
            }
            case ('!') -> {
                makeSure(winglessPieceIndex);
                tController.sendMessage("Ответ помечен как верный", chatId);
                return new GeneralState();
            }
            case ('.') -> {
                tController.sendMessage("Введите Ответ на бескрылку (или новую команду, начинающуюся с / или #):", chatId);
                return new AnswerState(winglessPieceIndex);
            }
            default -> {
                setMenu(chatId, new MainMenu());
                notyfyAllUsers(UserRepository.getAlias(chatId) +":\n"+ input);
                return new GeneralState();
            }
        }
    }

    @Override
    public ServiceState onEnter(TelegramBotController tController, String input, long chatId) {
        int number = Integer.parseInt(input.substring(1));
        winglessPieceIndex = number;
        String winglessPieceContent =
                "Бескрылка #"+ number + ":\n\n" + getWinglessPieceByNumber(number)+"\n";
        setMenu(chatId, new BringForwardMenu());
        tController.sendMessage( winglessPieceContent , chatId);
        return this;
    }
}
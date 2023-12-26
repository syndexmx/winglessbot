package services.servicestates;

import botcontroller.MainMenu;
import botcontroller.TelegramBotController;
import services.ServiceState;
import services.UserRepository;

import java.io.IOException;

import static services.ServiceStateSwitcher.switchToMonoState;
import static services.ServiceStateSwitcher.switchToState;
import static services.UserRepository.setMenu;
import static winglesspieces.WinglessService.*;

public class Answer2State implements ServiceState {

    private int winglessPieceIndex;

    final String PROMPT_TEXT = """
            Введите ОТВЕТ на бескрылку или новую команду,\s
            модерация:  & -отозвать ответ, ? -пометить сомнительным, ! -подтвердить)
            \s
            \s Ответ:
            """;

    public Answer2State(int winglessPieceIndex) {
        this.winglessPieceIndex = winglessPieceIndex;
    }

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
                setMenu(chatId, new MainMenu());
                tController.sendMessage("Ответ отозван", chatId);
                return new GeneralState();
            }
            case ('?') -> {
                makeDoubtfull(winglessPieceIndex);
                setMenu(chatId, new MainMenu());
                tController.sendMessage("Ответ помечен сомнительным", chatId);
                return new GeneralState();
            }
            case ('!') -> {
                makeSure(winglessPieceIndex);
                setMenu(chatId, new MainMenu());
                tController.sendMessage("Ответ помечен как верный", chatId);
                return new GeneralState();
            }
            default -> {
                registerASecondSolution(winglessPieceIndex, input, UserRepository.getAlias(chatId));
                setMenu(chatId, new MainMenu());
                tController.sendMessage("Ответ на бескрылку #"+winglessPieceIndex+" принят", chatId);
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
        tController.sendMessage(winglessPieceContent + PROMPT_TEXT, chatId);
        return this;
    }
}
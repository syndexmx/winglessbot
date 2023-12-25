package services.servicestates;

import botcontroller.TelegramBotController;
import services.ServiceState;
import services.UserRepository;

import java.io.IOException;

import static services.ServiceStateSwitcher.switchToMonoState;
import static services.ServiceStateSwitcher.switchToState;
import static winglesspieces.WinglessService.*;

public class AnswerState implements ServiceState {

    private int winglessPieceIndex;

    final String PROMPT_TEXT = """
            Введите ОТВЕТ на бескрылку (или новую команду, начинающуюся с / или # ,
            либо модерация бескрылки:  & -отозвать ответ, ? -пометить сомнительным, ! -подтвердить )
            \s
            \s Ответ:
            """;

    public AnswerState(int winglessPieceIndex) {
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
            default -> {
                registerASolution(winglessPieceIndex, input, UserRepository.getAlias(chatId));
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
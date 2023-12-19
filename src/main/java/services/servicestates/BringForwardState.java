package services.servicestates;

import botcontroller.TelegramBotController;
import services.ServiceState;

import static services.ServiceStateSwitcher.switchToMonoState;
import static services.ServiceStateSwitcher.switchToState;
import static winglesspieces.WinglessService.getWinglessPieceByNumber;
import static winglesspieces.WinglessService.registerASolution;

public class BringForwardState implements ServiceState {

    private int winglessPieceIndex;

    final String PROMPT_TEXT = "\n Введите ответ на бескрылку (или новую команду, начинающуюся с / или #):";
    @Override
    public ServiceState processRequest(TelegramBotController tController, String input, long chatId) {
        char ch = input.charAt(0);
        switch (ch) {
            case ('/') -> {
                return switchToState(tController, input, chatId);
            }
            case ('#') -> {
                return switchToMonoState(tController, input, chatId);
            }
            default -> {
                registerASolution(winglessPieceIndex, input);
                tController.sendMessage("Ответ на бескрылку #"+winglessPieceIndex+" принят", chatId);
                return new GeneralState();
            }
        }
    }

    @Override
    public ServiceState onEnter(TelegramBotController tController, String input, long chatId) {
        int number = Integer.parseInt(input.substring(1));
        winglessPieceIndex = number;
        String winglessPieceContent = "Бескрылка #"+ number + ":\n\n" + getWinglessPieceByNumber(number)+"\n";
        tController.sendMessage(winglessPieceContent + PROMPT_TEXT, chatId);
        return this;
    }
}
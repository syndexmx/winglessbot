package services;

import botcontroller.TelegramBotController;

import static services.UserRepository.getAllActivatedUsers;

public class CollectiveNotifier {


    static TelegramBotController tController;

    public static void settController(TelegramBotController tController) {
        CollectiveNotifier.tController = tController;
    }

    public static void notyfyAllUsers(String text) {
        for (Long longInteger : getAllActivatedUsers()) {
            tController.sendMessage(text, longInteger);
        }
    }

}

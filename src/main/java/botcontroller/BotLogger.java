package botcontroller;

import java.time.LocalDateTime;
import java.util.Deque;
import java.util.LinkedList;

public class BotLogger {

    static String lastAction = "";

    static Deque<String> logQue = new LinkedList<>();

    static int LOG_LINE_LENGTH = 30;

    public static void botLog(String text) {
        String timeStamp = LocalDateTime.now().toString();
        timeStamp = timeStamp.substring(0,timeStamp.indexOf("."));
        String fullLogLine = timeStamp + " " + text;
        lastAction = fullLogLine;
        logQue.add(fullLogLine);
        System.out.println(fullLogLine);
        if (logQue.size() > LOG_LINE_LENGTH){
            logQue.poll();
        }
    }

    public static String getLastAction() {
        return lastAction;
    }

    public static String getLastActions() {
        StringBuilder sb = new StringBuilder();
        for (String s : logQue){
            sb.append(s + "\n");
        }
        return sb.toString();
    }

}

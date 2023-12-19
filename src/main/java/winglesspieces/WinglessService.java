package winglesspieces;

import org.apache.commons.lang3.StringUtils;

import java.util.*;

import static org.apache.commons.lang3.StringUtils.split;

public class WinglessService {

    static Map<Integer, WinglessPiece> winglessBase = new TreeMap<>();

    public static int addNewPortion(String s){
        String ampersandSplit = StringUtils.replace(s, "\n\n","\n&");
        String[] splitS = split(ampersandSplit,"&");
        int addedCount = 0;
        for (String string : splitS){
            if (string.contains(".")){
                String supposedNumberString =string.substring(0, string.indexOf("."));
                int number = Integer.parseInt(supposedNumberString);
                winglessBase.put(number, new WinglessPiece(string));
                addedCount++;
            }
        }
        return addedCount;
    }

    public static String fetchAllTasks(){
        StringBuilder sb = new StringBuilder();
        for (WinglessPiece winglessPiece : winglessBase.values()){
            sb.append( winglessPiece.getTask()+"\n");
        }
        sb.append("\n Всего: "+ winglessBase.size()+" \n");
        return sb.toString();
    }

    public static String fetchSolvedTasks(){
        int count = 0;
        StringBuilder sb = new StringBuilder();
        for (WinglessPiece winglessPiece : winglessBase.values()){
            if (winglessPiece.isSolved()) {
                count++;
                sb.append(winglessPiece.getTask() + "\n\n");
                sb.append("# "+winglessPiece.getSolution());
                if (!winglessPiece.isSure()){
                    sb.append("\n ? ? ?");
                }
                sb.append("\n\n");
            }
        }
        sb.append("\n Итого решено: "+ count +" \n");
        return sb.toString();
    }

    public static String fetchUnsolvedTasks(){
        int count=0;
        StringBuilder sb = new StringBuilder();
        for (WinglessPiece winglessPiece : winglessBase.values()){
            if (!winglessPiece.isSolved()) {
                sb.append(winglessPiece.getTask() + "\n");
                count++;
            }
        }
        sb.append("\n Итого нерешенных: "+ count+" \n");
        return sb.toString();
    }

}

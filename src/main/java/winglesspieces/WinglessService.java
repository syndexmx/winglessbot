package winglesspieces;

import org.apache.commons.lang3.StringUtils;
import services.CollectiveNotifier;

import java.io.*;
import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

import static org.apache.commons.lang3.StringUtils.split;

public class WinglessService {

    static Map<Integer, WinglessPiece> winglessBase = new TreeMap<>();
    static ReentrantLock winglessBaseLock = new ReentrantLock();
    static String winglessPiecesBaseDat = "winglesspiecesbase.dat";

    private static void pushUpdate() throws IOException {
        winglessBaseLock.lock();
        {
            // Push data to file
            try (
                FileOutputStream fOS = new FileOutputStream("winglesspiecesbase.dat");
                ObjectOutputStream oOS = new ObjectOutputStream(fOS);
            ) {
                oOS.writeObject(winglessBase);

                oOS.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        winglessBaseLock.unlock();
    }

    public static void pullUpdate() throws IOException {
        winglessBaseLock.lock();
        {
            try (
                    FileInputStream fIS = new FileInputStream(winglessPiecesBaseDat);
                    ObjectInputStream oIS = new ObjectInputStream(fIS);
            ) {
                winglessBase.clear();
                winglessBase = (Map) oIS.readObject();
                oIS.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

        }
        winglessBaseLock.unlock();
    }


    public static String getWinglessPieceByNumber(int number){
        WinglessPiece currentPiece = winglessBase.get(number);
        StringBuilder sb = new StringBuilder();
        sb.append(currentPiece.getComplete());
        sb.append("\n");
        sb.append(currentPiece.getSolution());
        sb.append("\n");
        return sb.toString();
    }

    public static void registerASolution(int number, String answer, String actor) throws IOException {
        WinglessPiece currentPiece = winglessBase.get(number);
        currentPiece.setSolution(answer);
        currentPiece.setSolved(true);
        pushUpdate();
        CollectiveNotifier
                .notyfyAllUsers( "Бескрылка #"+ number +" \n\n"
                        + currentPiece.getComplete() + " \n\n *** решена ***\n" + actor );
    }

    public static void withdrawSolution(int number) throws IOException {
        WinglessPiece currentPiece = winglessBase.get(number);
        currentPiece.setSolution("");
        currentPiece.setSolved(false);
        pushUpdate();
        CollectiveNotifier
                .notyfyAllUsers( "Бескрылка #"+ number +" \n\n"
                        + currentPiece.getComplete() + " \n\n *** Решение отозвано ***");
    }

    public static void makeSure(int number) throws IOException {
        WinglessPiece currentPiece = winglessBase.get(number);
        currentPiece.setSure(true);
        pushUpdate();
        CollectiveNotifier
                .notyfyAllUsers( "Бескрылка #"+ number +" \n\n"
                        + currentPiece.getComplete() + " \n\n *** ответ утвержден ***");
    }

    public static void makeDoubtfull(int number) throws IOException {
        WinglessPiece currentPiece = winglessBase.get(number);
        currentPiece.setSure(false);
        pushUpdate();
        CollectiveNotifier
                .notyfyAllUsers( "Бескрылка #"+ number +" \n\n"
                        + currentPiece.getComplete() + " \n\n *** ответ помечен как сомнительный ***");
    }

    public static int addNewPortion(String s) throws IOException {
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
        pushUpdate();
        return addedCount;
    }


    public static String fetchAllTasks(){
        int count=0;
        StringBuilder sb = new StringBuilder();
        for (WinglessPiece winglessPiece : winglessBase.values()){
            if (true) {
                sb.append(winglessPiece.getTask() + "\n\n");
                count++;
            }
        }
        sb.append("\n Итого: "+ count+" \n");
        return sb.toString();
    }

    public static String fetchSolvedTasks(){
        int count = 0;
        StringBuilder sb = new StringBuilder();
        for (WinglessPiece winglessPiece : winglessBase.values()){
            if (winglessPiece.isSolved()) {
                count++;
                sb.append(winglessPiece.getComplete() + "\n");
                if (!winglessPiece.isSure()){
                    sb.append("? ? ? "+ winglessPiece.getSolution() +" ? ? ?  \n");
                }
                sb.append("\n\n");
            }
        }
        sb.append("\n Итого решено: "+ count +" \n");
        return sb.toString();
    }

    public static String fetchDoubtful(){
        int count = 0;
        StringBuilder sb = new StringBuilder();
        for (WinglessPiece winglessPiece : winglessBase.values()){
            if (winglessPiece.isSolved() && !winglessPiece.isSure()) {
                count++;
                sb.append(winglessPiece.getComplete() + "\n");
                if (!winglessPiece.isSure()){
                    sb.append("? ? ? "+ winglessPiece.getSolution() +" ? ? ?  \n");
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
                sb.append(winglessPiece.getTask() + "\n\n");
                count++;
            }
        }
        sb.append("\n Итого нерешенных: "+ count+" \n");
        return sb.toString();
    }

    public static String fetchSolutions(){
        int count = 0;
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Integer, WinglessPiece> winglessPieceEntry : winglessBase.entrySet()){
            if (winglessPieceEntry.getValue().isSolved()) {
                sb.append("#");
                sb.append(winglessPieceEntry.getKey());
                sb.append(". ");
                sb.append(winglessPieceEntry.getValue().getSolution());
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    public static List<List<String>> collectDoubleList(){
        List<List<String>> outerList = new ArrayList<>();
        List<String> innerList = new ArrayList<>();
        int count = 0;
        for ( Integer index  : winglessBase.keySet()){
            String buttonMarkup = "#" + index;
            innerList.add(buttonMarkup);
            count ++;
            if (count % 8  == 0){
                outerList.add(innerList);
                innerList =  new ArrayList<>();
            }

        }
        if (count % 8 != 0){
            outerList.add(innerList);
        }
        return outerList;
    }

    public static List<List<String>> collectUnsolvedList(){
        List<List<String>> outerList = new ArrayList<>();
        List<String> innerList = new ArrayList<>();
        int count = 0;
        for ( Integer index  : winglessBase.keySet()){
            if (!winglessBase.get(index).isSolved()) {
                String buttonMarkup = "#" + index;
                innerList.add(buttonMarkup);
                count++;
                if (count % 8 == 0) {
                    outerList.add(innerList);
                    innerList = new ArrayList<>();
                }
            }
        }
        if (count % 8 != 0){
            outerList.add(innerList);
        }
        return outerList;
    }

    public static List<List<String>> collectDoubtfulList(){
        List<List<String>> outerList = new ArrayList<>();
        List<String> innerList = new ArrayList<>();
        int count = 0;
        for ( Integer index  : winglessBase.keySet()){
            if (winglessBase.get(index).isSolved() &&
                    !winglessBase.get(index).isSure()) {
                String buttonMarkup = "#" + index;
                innerList.add(buttonMarkup);
                count++;
                if (count % 8 == 0) {
                    outerList.add(innerList);
                    innerList = new ArrayList<>();
                }
            }
        }
        if (count % 8 != 0){
            outerList.add(innerList);
        }
        return outerList;
    }

    public static List<List<String>> collectSolvedList(){
        List<List<String>> outerList = new ArrayList<>();
        List<String> innerList = new ArrayList<>();
        int count = 0;
        for ( Integer index  : winglessBase.keySet()){
            if (winglessBase.get(index).isSolved()) {
                String buttonMarkup = "#" + index;
                innerList.add(buttonMarkup);
                count++;
                if (count % 8 == 0) {
                    outerList.add(innerList);
                    innerList = new ArrayList<>();
                }
            }
        }
        if (count % 8 != 0){
            outerList.add(innerList);
        }
        return outerList;
    }


    public static void setWinglessPiecesBaseDat(String winglessPiecesBaseDat) {
        WinglessService.winglessPiecesBaseDat = winglessPiecesBaseDat;
    }

}

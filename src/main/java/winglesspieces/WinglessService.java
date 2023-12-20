package winglesspieces;

import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

import static org.apache.commons.lang3.StringUtils.split;

public class WinglessService {

    static Map<Integer, WinglessPiece> winglessBase = new TreeMap<>();
    static ReentrantLock winglessBaseLock = new ReentrantLock();

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
                    FileInputStream fIS = new FileInputStream("winglesspiecesbase.dat");
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

    public static void registerASolution(int number, String answer) throws IOException {
        WinglessPiece currentPiece = winglessBase.get(number);
        currentPiece.setSolution(answer);
        currentPiece.setSolved(true);
        pushUpdate();
    }

    public static void withdrawSolution(int number) throws IOException {
        WinglessPiece currentPiece = winglessBase.get(number);
        currentPiece.setSolution("");
        currentPiece.setSolved(false);
        pushUpdate();
    }

    public static void makeSure(int number) throws IOException {
        WinglessPiece currentPiece = winglessBase.get(number);
        currentPiece.setSure(true);
        pushUpdate();
    }

    public static void makeDoubtfull(int number) throws IOException {
        WinglessPiece currentPiece = winglessBase.get(number);
        currentPiece.setSure(false);
        pushUpdate();
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
        StringBuilder sb = new StringBuilder();
        for (WinglessPiece winglessPiece : winglessBase.values()){
            sb.append( winglessPiece.getComplete()+"\n\n");
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
            String buttonMarkup = "#"+index+" ";
//            if (winglessBase.get(index).isSolved()){
//                if (winglessBase.get(index).isSure()){
//                    buttonMarkup = buttonMarkup +"+";
//                } else {
//                    buttonMarkup = buttonMarkup +"?";
//                }
//            } else {
//                buttonMarkup = buttonMarkup +"-";
//            }
            innerList.add(buttonMarkup);
            count ++;
            if (count % 10  == 0){
                outerList.add(innerList);
                innerList =  new ArrayList<>();
            }

        }
        if (count % 10 != 0){
            outerList.add(innerList);
        }
        return outerList;
    }

}

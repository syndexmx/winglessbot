package winglesspieces;

import java.io.Serializable;

public class WinglessPiece implements Serializable {


    private String task;

    private String complete="";

    private String solution="";

    private boolean solved=false;

    private boolean sure=true;


    public WinglessPiece(String task) {
        this.task = task;
        this.complete = task;
    }

    public boolean isSolved() {
        return solved;
    }

    public String getTask() {
        return task;
    }

    public String getSolution() {
        return solution;
    }

    public String getComplete() {
        return complete;
    }

    public boolean isSure() {
        return sure;
    }

    public void setSolution(String solution) {
        this.solution = solution;
        String primary =  this.task;
        String prefix = primary.substring(0, primary.indexOf("[")+1);
        String suffix = primary.substring(primary.indexOf("]")+1);
        this.complete = prefix + solution + suffix;
    }

    public void setSolved(boolean solved) {
        this.solved = solved;
    }

}

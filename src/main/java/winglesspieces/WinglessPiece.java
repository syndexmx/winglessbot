package winglesspieces;

import java.io.Serializable;

public class WinglessPiece implements Serializable {


    private String task;

    private String complete="";

    private String solution="";

    private boolean doubleWinged = false;
    private String secondSolution="";

    private boolean solved=false;

    private boolean solvedFirst=false;

    private boolean solvedSecond=false;

    private boolean sure=true;


    public WinglessPiece(String task) {
        this.task = task;
        this.complete = task;
        if (task.indexOf("[")>=0){
            if ( task.substring(task.indexOf("]")).indexOf("[")>=0){
                doubleWinged = true;
            } else {
                doubleWinged = false;
            }
        }
    }

    public boolean isSolved() {
        return solved;
    }

    public String getTask() {
        return task;
    }

    public String getSolution() {
        if (doubleWinged) return solution + ";\n" + secondSolution;
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
        String suffix = primary.substring(primary.indexOf("]"));
        if (doubleWinged){
            String secondPrefix = suffix.substring(0, suffix.indexOf("[")+1);
            String secondSuffix = suffix.substring(suffix.substring(1).indexOf("]"));
            suffix = secondPrefix + secondSolution + secondSuffix;
        }
        this.complete = prefix + this.solution + suffix;
        this.solvedFirst = true;
        this.solved = (!doubleWinged & solvedFirst) | (solvedFirst & solvedSecond) ;
    }

    public void setSecondSolution(String secondSolution) {
        this.secondSolution = secondSolution;
        String primary =  this.task;
        String prefix = primary.substring(0, primary.indexOf("[")+1);
        String suffix = primary.substring(primary.indexOf("]"));
        if (doubleWinged){
            String secondPrefix = suffix.substring(0, suffix.indexOf("[")+1);
            String secondSuffix = suffix.substring(suffix.substring(1).indexOf("]"));
            suffix = secondPrefix + this.secondSolution + secondSuffix;
        }
        this.complete = prefix + this.solution + suffix;
        this.solvedSecond = true;
        this.solved = (!doubleWinged & solvedFirst) | (solvedFirst & solvedSecond) ;
    }


    public void setSolved(boolean solved) {
        this.solved = solved;
    }

    public void setSure(boolean sure) {
        this.sure = sure;
    }



}

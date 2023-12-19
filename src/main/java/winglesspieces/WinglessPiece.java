package winglesspieces;

public class WinglessPiece {


    private String task;

    private String solution="";
    private boolean solved=false;

    private boolean sure=true;


    public WinglessPiece(String task) {
        this.task = task;
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

    public boolean isSure() {
        return sure;
    }

}

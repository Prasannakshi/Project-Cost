/**
 * Created by Prasannakshi on 3/28/2018.
 */
public class wordnode {
    private String word;
    private int cost;

    public wordnode(String dest, int c) {
        this.word = dest;
        this.cost = c;
    }

    public int getCost() {
        return cost;
    }

    public String getWord() {
        return word;
    }
}

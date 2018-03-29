import java.util.*;

/**
 * Created by Prasannakshi on 3/28/2018.
 */
public class Undirectedgraph {
    private final Map<String, Set<wordnode>> graph = new HashMap<String, Set<wordnode>>();
    private final List<Integer> pathcost = new ArrayList<>();
    private final Set<String> visited = new HashSet<>();

    public boolean addword(String newword){
        if(graph.containsKey(newword)){
            return false;
        }

        graph.put(newword, new HashSet<wordnode>());
        return true;
    }

    public void addedge(String start, String end, int c) {
        if (!graph.containsKey(start)) {
            addword(start);
        }
        wordnode dest = new wordnode(end, c);
        graph.get(start).add(dest);
    }

    public void calculate(String source, String target, int cost){
        if(graph.containsKey(source)) {
            visited.add(source);
        }

        if(source.equals(target)){
            pathcost.add(cost);
        } else if(graph.containsKey(source)){
            for (wordnode w: graph.get(source)){
                String newsource = w.getWord();
                if(!visited.contains(newsource)) {
                    calculate(newsource, target, cost + w.getCost());
                }
            }
        }
    }

    public int findcost() {
        if(pathcost.size()==0){
            return -1;
        }
        int minVal = Collections.min(pathcost);
        return minVal;
    }
}

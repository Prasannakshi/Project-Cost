import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Stream;

/**
 * Created by Prasannakshi on 3/28/2018.
 */
public class cost {
    private static final String[] dictionary = {"HEALTH","HEATH","HEATS","HENTS","HENDS","HANDS","HONKS","HELLS","HEANS"};
    private static final Undirectedgraph graph = new Undirectedgraph();

    public static void main(String args[]){
        final cost cost = new cost();
        final Scanner sc = new Scanner(System.in);
        final String cost_of_each = sc.nextLine();
        final String startword = sc.nextLine();
        final String endword = sc.nextLine();

        String[] costs = cost_of_each.split(" ");
        int[] get_costs = Stream.of(costs).mapToInt(Integer::parseInt).toArray();
        travel(get_costs);
        graph.calculate(startword, endword, 0);
        int finalcost = graph.findcost();
        System.out.println("Cost for transforming source to target word is : " + finalcost);

    }

    private static void travel(int[] get_costs) {
        int length = dictionary.length;
        for(int i=0;i<length;i++){
            for(int j=0;j<length;j++){
                int cost=0, edge_distance=0, m=dictionary[i].length(), n=dictionary[j].length();
                if(i!=j && m>=3 && n>=3){
                    if(isAnagram(dictionary[i], dictionary[j])){
                        cost = 1;
                        edge_distance = get_costs[3];

                    } else {
                        int[][] cost_matrix = new int[m+1][n+1];
                        cost = levenshtein(get_costs, cost_matrix, dictionary[i], dictionary[j]);
                        edge_distance = cost_matrix[m][n];
                    }
                }
                if(cost==1){
                    graph.addedge(dictionary[i],dictionary[j],edge_distance);
                }
            }
        }
    }

    private static int levenshtein(int[] operation, int[][] cost, String s, String t) {

        int m = s.length(), n=t.length();
        int[] currentRow = new int[n+1];

        for(int i = 1; i <= m; i++){
            cost[i][0] = cost[i-1][0]+operation[1];
        }
        for(int i = 1; i <= n; i++) {
            cost[0][i] = cost[0][i - 1] + operation[0];
            currentRow[i] = currentRow[i-1]+1;
        }

        for(int i = 0; i < m; i++) {
            int[] previous = Arrays.copyOf(currentRow, currentRow.length);
            currentRow[0] = previous[0]+1;
            for(int j = 0; j < n; j++) {
                if(s.charAt(i) == t.charAt(j)) {
                    cost[i + 1][j + 1] = cost[i][j];
                    currentRow[j + 1] = previous[j];
                } else {
                    int a = cost[i][j] + operation[2];
                    int b = cost[i][j + 1] + operation[1];
                    int c = cost[i + 1][j] + operation[0];
                    cost[i + 1][j + 1] = a < b ? (a < c ? a : c) : (b < c ? b : c);
                    int substi = previous[j]+1, add = currentRow[j]+1, sub = previous[j+1]+1;
                    currentRow[j+1] = add < sub ? (add < substi ? add : substi) : (sub < substi ? sub : substi);
                }
            }
        }
        return currentRow[t.length()];
    }

    private static boolean isAnagram(String s, String t) {
        if(s==null || t==null)
            return false;
        if(s.length()!=t.length())
            return false;
        int[] arr = new int[26];
        for(int i=0; i<s.length(); i++){
            arr[s.charAt(i)-'A']++;
            arr[t.charAt(i)-'A']--;
        }
        for(int i: arr){
            if(i!=0)
                return false;
        }
        return true;
    }
}

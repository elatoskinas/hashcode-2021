package data;

import java.util.*;

/**
 * Problem instance
*/
public class Problem {
    public Map<Integer, Node> graph;
    public Map<String, Integer> streetCosts;
    public Map<Integer, List<String>> cars;
    public int bonusPoints;
    public int duration;

    public Problem() {
        graph = new HashMap<Integer, Node>();
        streetCosts = new HashMap<String, Integer>();
        cars = new HashMap<Integer, List<String>>();
    }
}

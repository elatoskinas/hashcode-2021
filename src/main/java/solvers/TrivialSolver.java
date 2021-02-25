package solvers;

import data.Problem;
import data.TrafficLight;
import data.Solution;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Solver that always returns empty solution.
 * Use to smoke-test if the App works correctly.
 * Input:
 * public Map<Integer, Node> graph;
    public Map<String, Integer> streetCosts;
    public Map<Integer, List<String>> cars;
    public int bonusPoints;
    public int duration;

    Output:
    // TrafficLight -> { streetName, trafficLightDuration }
    // Map<Integer, OrderedList<TrafficLight>>

 */
public class TrivialSolver implements Solver {

    @Override
    public Solution solve(Problem problem) {
        
        HashMap<Integer, List<TrafficLight>> map = new HashMap<>();
        List<TrafficLight> lights = new ArrayList<>();
        lights.add(new TrafficLight("ABC", 1));
        map.put(1, lights);
        return new Solution(map);
    }

}

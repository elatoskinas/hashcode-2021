package solvers;

import data.Node;
import data.Problem;
import data.TrafficLight;
import data.Solution;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
public class ProblemSolver implements Solver {

    @Override
    public Solution solve(Problem problem) {

        Map<String, Integer> streetNamesCovered = new HashMap<>();
        Map<String, Integer> streetDistances = new HashMap<>();

        // 1. Filter cars by path length <= D
        // Map.Entry<Integer, List<String>>
        for (var car : problem.cars.entrySet()) {
            List<String> path = car.getValue();

            // Impossible to move car to destination in the timeframe. Ignore!
            if (path.size() > problem.duration) {
                continue;
            }

            long totalPathCost = 0;
            Set<String> carStreetNames = new HashSet<>();

            //  2. Check what streets we want to explore (traverse every car, collect street names)
            // TODO: Ignore car if path too long
            for (String street : path) {
                long pathCost = problem.streetCosts.get(street);
                totalPathCost += pathCost;
                carStreetNames.add(street);
            }

            // Impossible to get car to the path due to path too long check
            if (totalPathCost <= problem.duration) {
                for (String street : carStreetNames) {
                    streetNamesCovered.put(street, streetNamesCovered.getOrDefault(street, 0) + 1);
                }

                for (int i = 0; i < path.size(); ++i) {
                    streetDistances.put(path.get(i),
                                        Math.min(i, streetDistances.getOrDefault(path.get(i), Integer.MAX_VALUE)));
                }
            }
        }

        HashMap<Integer, List<TrafficLight>> map = new HashMap<>();

        // 3. Update all traffic lights
        for (var entry : problem.graph.entrySet()) {
            Node node = entry.getValue();

            for (var edgeEntry : node.edges.entrySet()) {
                String streetName = edgeEntry.getKey();

                // Street was visited
                if (streetNamesCovered.containsKey(streetName)) {
                    Node coveredIntersection = edgeEntry.getValue();
                    List<TrafficLight> intersectionLights = map.getOrDefault(coveredIntersection.id, new ArrayList<>());

                    int streetCost = problem.streetCosts.get(streetName);
                    int frequency = Math.max(streetNamesCovered.get(streetName) - (streetCost / 3), 1);

                    intersectionLights.add(new TrafficLight(streetName, frequency));
                    map.put(coveredIntersection.id, intersectionLights);
                }
            }
        }

        // 4. Normalize frequencies
        for (var entry : map.entrySet()) {
            double MAGIC_CONSTANT = 10.0;
            double divisionFactor = Math.sqrt(entry.getValue().size() * 1.0) * MAGIC_CONSTANT;

            for (int index = 0; index < entry.getValue().size(); ++index) {
                entry.getValue().get(index).duration = Math.max(Math.min((int) (entry.getValue().get(index).duration / divisionFactor), 10), 1);
            }

            // Sort by shortest (inital) distances to streets
            // [active traffic lights that are closest to any given car]
            entry.getValue().sort(new Comparator<TrafficLight>(){
                @Override
                public int compare(TrafficLight t1, TrafficLight t2) {
                    return Integer.compare(streetDistances.get(t1.streetname), streetDistances.get(t2.streetname));
                }
            });
        }

        return new Solution(map);
    }

}

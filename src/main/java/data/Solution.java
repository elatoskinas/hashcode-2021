package data;

import java.util.*;

/**
 * Solution instance
 */
public class Solution {
    // TrafficLight -> { streetName, trafficLightDuration }
    // Map<Integer, OrderedList<TrafficLight>>

    public Map<Integer, List<TrafficLight>> output;

    public Solution() {
        output = new HashMap<>();
    }

    public Solution(Map<Integer, List<TrafficLight>> output) {
        this.output = output;
    }
}

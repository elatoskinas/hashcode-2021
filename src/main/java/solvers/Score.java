package solvers;

import data.*;
import java.util.*;

public class Score {
    /**
     * Calculates the score of the given solution
     * A score is awarded for each car that  nishes its path before the end of the simulation. 
     * For a car that  nishes its path at time T, the awarded score is (F) points 
     * ( xed award for  nishing the path) and additionally (D - T): one point for each second le  when the car  nished the path.
        In other words: if a car  nishes at time T it scores
        ● F + (D – T)points ifT≤D,
        ● or 0 points otherwise.
        The score for the solution is the sum of scores for all cars.
     */
    public static Double score(Solution solution, Problem problem) {
        //public Map<Integer, List<String>> cars;
        
        return 0.0;
    }
}

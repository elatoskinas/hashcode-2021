package solvers;

import data.Problem;
import data.Solution;

/**
 * Solver that always returns empty solution.
 * Use to smoke-test if the App works correctly.
 */
public class TrivialSolver implements Solver {

    @Override
    public Solution solve(Problem problem) {
        return new Solution();
    }

}

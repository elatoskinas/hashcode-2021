package solvers;

import data.Problem;
import data.Solution;

/**
 * Interface that allows to input a problem to get a solution.
 */
public interface Solver {
    /**
     * @param problem Problem to solve
     * @return Solution to the problem
     */
    Solution solve(Problem problem);
}

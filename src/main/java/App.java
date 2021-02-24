import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import data.Problem;
import data.Solution;
import solvers.Score;
import solvers.Solver;
import solvers.TrivialSolver;

public class App {

  // Input files to read from
  static String[] inputFileNames = {
      "sample.txt"
  };

  static Solver solver = new TrivialSolver();

  public static void main(String[] args) throws Exception {
    double totalScore = 0L;
    double totalTime = 0L;

    for (String inputFileName : inputFileNames) {
      Long time = System.currentTimeMillis();

      System.out.println("Parsing input for: " + inputFileName);
      Problem problem = parseInput("input/" + inputFileName);

      System.out.println("Solving");
      Solution solution = solver.solve(problem);

      double score = Score.score(solution);
      totalScore += score;

      System.out.println("Writing solution");
      writeResult("output/" + inputFileName + ".out", solution);

      long timeElapsed = System.currentTimeMillis() - time;
      totalTime += timeElapsed;

      System.out.println("Score: " + score);
      System.out.println("Time: " + timeElapsed);
    }

    System.out.println("Total score: " + totalScore);
    System.out.println("Total time: " + totalTime);
  }

  private static Problem parseInput(String inputFileName) {
    try {
      File inputFile = new File(inputFileName);
      Scanner inputReader = new Scanner(inputFile);

      // PARSING LOGIC GOES HERE
      // inputReader.nextInt(); // Integer
      // inputReader.next(); // String
      // inputReader.nextLine(); // String (line)

      inputReader.close();
      return new Problem();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

    return null;
  }

  private static void writeResult(String outputFileName, Solution solution) {
    try {
      FileWriter solutionWriter = new FileWriter(outputFileName);

      // SOLUTION WRITING LOGIC GOES HERE
      // myWriter.write();

      solutionWriter.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}

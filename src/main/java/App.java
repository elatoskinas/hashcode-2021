import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import data.Node;
import data.Problem;
import data.Solution;
import data.TrafficLight;
import solvers.Score;
import solvers.Solver;
import solvers.ProblemSolver;

public class App {

  // Input files to read from
  static String[] inputFileNames = {
      "a.txt",
      "b.txt",
      "c.txt",
      "d.txt",
      "e.txt",
      "f.txt"
  };

  static Solver solver = new ProblemSolver();

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

      Problem problem = new Problem();

      problem.duration = inputReader.nextInt();
      int intersections = inputReader.nextInt();
      int streets = inputReader.nextInt();
      int cars = inputReader.nextInt();
      problem.bonusPoints = inputReader.nextInt();


      // TODO: initialize nodes from intersections (0 -> #intersections -1)

      // Construct all nodes
      for (int i = 0; i < intersections; ++i) {
          Node node = new Node(i);
          problem.graph.put(i, node);
      }

      // Edges
      for (int i = 0; i < streets; ++i) {
          int start = inputReader.nextInt();
          int end = inputReader.nextInt();
          String streetName = inputReader.next();
          int travelDuration = inputReader.nextInt();

          // Add edge
          Node startNode = problem.graph.get(start);
          Node endNode = problem.graph.get(end);
          startNode.edges.put(streetName, endNode);

          // Update edge cost
          problem.streetCosts.put(streetName, travelDuration);
      }

      // Cars
      for (int i = 0; i < cars; ++i) {
          int pathLength = inputReader.nextInt();

          List<String> pathList = new LinkedList<>();
          for (int p = 0; p < pathLength; ++p) {
              String street = inputReader.next();
              pathList.add(street);
          }

          problem.cars.put(i, pathList);
      }

      inputReader.close();
      return problem;
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

    return null;
  }

  private static void writeResult(String outputFileName, Solution solution) {
    try {
      FileWriter solutionWriter = new FileWriter(outputFileName);

      solutionWriter.write(solution.output.size() + "\n");

      for (var entry : solution.output.entrySet()) {
          solutionWriter.write(entry.getKey() + "\n" + entry.getValue().size() + "\n");

          for (TrafficLight light : entry.getValue()) {
            solutionWriter.write(light.streetname + " " + light.duration + "\n");
          }
      }

      solutionWriter.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}

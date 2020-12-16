/*
Joshua Taylor
jostaylor@chapman.edu

Simulation

This class creates the functionality for running Simulations
A Simulation object runs an integer amount of games and collects stats for all those games
*/

public class Simulation{
  // Declare variables
  private int numGames;
  private Game[] allGames;

  // Variables displayed for final statistics
  private double avgNumBattles;
  private double avgNumWars;
  private double avgNumDoubleWars;
  private int maxNumBattles;
  private int minNumBattles;
  private int maxNumWars;
  private int minNumWars;

  /**
  * Constructor that initializes the var for how many games will be simulated
  * as well as creating an array of size numGames
  * @param int numGames
  */
  public Simulation(int numGames){
    this.numGames = numGames;
    this.allGames = new Game[numGames];
  }

  /**
  * A method that creates and inserts a new Game object into an array (allGames) and invokes the play method for each game
  */
  public void simulate(){
    for (int i = 0; i < this.numGames; i++){
      this.allGames[i] = new Game();
      this.allGames[i].play();
    }
  }

  public void calculate(){
    // Declare variables
    double totalBattles = 0.0;
    double totalWars = 0.0;
    double totalDoubleWars = 0.0;

    // Iterates through every simulated game
    for (int i = 0; i < this.numGames; i++){
      // Adds each game stat to total stats
      totalBattles += allGames[i].getNumBattles();
      totalWars += allGames[i].getNumWars();
      totalDoubleWars += allGames[i].getNumDoubleWars();

      // Updates max and min stat variables

      // initializes variables for the first simulated game
      if (i == 0){
        this.maxNumBattles = allGames[i].getNumBattles();
        this.minNumBattles = allGames[i].getNumBattles();
        this.minNumWars = allGames[i].getNumWars();
        this.maxNumWars = allGames[i].getNumWars();
      }
      // Checks for record max numBattles
      if (this.maxNumBattles < allGames[i].getNumBattles()){
        this.maxNumBattles = allGames[i].getNumBattles();
      }
      // Checks for record min numBattles
      else if (this.minNumBattles > allGames[i].getNumBattles()){
        this.minNumBattles = allGames[i].getNumBattles();
      }
      // Checks for record max numWars
      if (this.maxNumWars < allGames[i].getNumWars()){
        this.maxNumWars = allGames[i].getNumWars();
      }
      // Checks for record min numWars
      else if (this.minNumWars > allGames[i].getNumWars()){
        this.minNumWars = allGames[i].getNumWars();
      }
    }
    // Divides totals by numGames to calculate average
    this.avgNumBattles = (double)(totalBattles / numGames);
    this.avgNumWars = (double)(totalWars / numGames);
    this.avgNumDoubleWars = (double)(totalDoubleWars / numGames);
  }

  public void report(){
    System.out.println("Number of games simulated: " + this.numGames);
    System.out.println("Average number of battles: " + this.avgNumBattles);
    System.out.println("Average number of wars: " + this.avgNumWars);
    System.out.println("Average number of double wars: " + this.avgNumDoubleWars);
    System.out.println("Maximum amount of battles: " + this.maxNumBattles);
    System.out.println("Minimum amount of battles: " + this.minNumBattles);
    System.out.println("Maximum amount of wars: " + this.maxNumWars);
    System.out.println("Minimum amount of wars: " + this.minNumWars);
  }

  public static void main(String[] args) {
    // Convert string argument into integer using wrapper class
    int amountOfGames = Integer.parseInt(args[0]);
    // Simulates games
    Simulation sim = new Simulation(amountOfGames);
    System.out.println("Simulating " + amountOfGames + " games...");
    sim.simulate();
    sim.calculate();
    sim.report();
  }
}

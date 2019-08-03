import java.util.Scanner;
import java.util.Collections;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.HashMap;
import java.util.*;

public class CriticRank {
  public static void main(String[] args) {

    long startTime = System.nanoTime();

    Scanner myScanner = new Scanner(System.in);

    String currentRequest;
    boolean criticStatus = true; // true if on critic1, false if on criti2
    LinkedList<Movie> critic1List = new LinkedList<Movie>();
    LinkedList<Movie> critic2List = new LinkedList<Movie>();

    int critic1Index = 0; // ranking in respect to critic 1
    HashMap<String, Integer> critic1Lookup = new HashMap<String, Integer>();
    HashMap<String, Boolean> critic1FiveStarLookup = new HashMap<String, Boolean>();

    // iterate through each line, parse and store all relevant data
    while (myScanner.hasNextLine()) {
      currentRequest = myScanner.nextLine();
      // this is a critic update line
      if (currentRequest.contains("Critic 1")) {
          criticStatus = true;
          continue;
      }
      if (currentRequest.contains("Critic 2")) {
          criticStatus = false;
          continue;
      }

      // turn the entry into a Movie object
      String[] movieParts = currentRequest.split("/");
      int movieRating = Integer.parseInt(movieParts[0]);
      String movieName = movieParts[1];
      

      if(criticStatus){ // add a movie to critic 1's list
        Movie parsedC1Movie = new Movie(movieRating, movieName, critic1Index);
        critic1Lookup.put(movieName, critic1Index);
        critic1Index = critic1Index + 1;
        critic1List.add(parsedC1Movie);
        if(movieRating >= 5){
          critic1FiveStarLookup.put(movieName, true);
        }
      }

      if(!criticStatus){ // add a movie to critic 2's list
        int c1Index = critic1Lookup.get(movieName);
        Movie parsedC2Movie = new Movie(movieRating, movieName, c1Index);
        critic2List.add(parsedC2Movie);
      }

    } 
    
    // System.out.println("Critic 1:");
    // for (Movie M: critic1List){
    //   System.out.println("Name: " + M.name);
    //   System.out.println("Rating: " + Integer.toString(M.starRating));
    //   System.out.println("Rank: " + M.ranking);
    // }

    // System.out.println("\n");
    // System.out.println("Critic 2:");
    // for (Movie M: critic2List){
    //   System.out.println("Name: " + M.name);
    //   System.out.println("Rating: " + Integer.toString(M.starRating));
    //   System.out.println("Rank: " + M.ranking);
    // }

    
    System.out.println("Elasped:" + (System.nanoTime() - startTime) / 1000000000.0);    
  }
}

class Movie {
  int starRating;
  String name;
  int ranking; 

  Movie(int starRating, String name, int ranking){
    this.starRating = starRating;
    this.name = name;
    this.ranking = ranking;
  }
}

class Examples
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

public class Edge {
  GamePiece fromNode;
  GamePiece toNode;
  int weight;

  Edge(GamePiece fromNode, GamePiece toNode, int weight) {
    this.fromNode = fromNode;
    this.toNode = toNode;
    this.weight = weight;
  }
}

// Comparator to compare the weight of two edges
class CompareWeight implements Comparator<Edge> {

  public int compare(Edge edge1, Edge edge2) {
    return edge1.weight - edge2.weight;
  }
}

// Represents a graph of gamepieces and edges 
class Graph implements Iterable<GamePiece> {

  // A hashmap that represents where each gamepiece is represented by in the MST
  HashMap<GamePiece, GamePiece> representatives;

  // Total number of gamepieces
  ArrayList<GamePiece> allNodes;

  // total number of edges in the MST
  ArrayList<Edge> mstEdges;
  ArrayList<Edge> allEdges;

  int width;
  int height;
  int numNodes;

  Graph(ArrayList<ArrayList<GamePiece>> board) {
    this.width = board.size();
    this.height = board.get(0).size();
    this.allNodes = this.genNodes(this.width * this.height);
    this.numNodes = this.width * this.height;
    this.representatives = this.initReps(this.allNodes);
    this.allEdges = this.genEdges(this.allNodes);
  }

  ArrayList<GamePiece> genNodes(int total) {
    ArrayList<GamePiece> allNodes = new ArrayList<GamePiece>();

    for (int i = 0; i < total; i++) {
      this.allNodes.add(new GamePiece());
    }

    return allNodes;
  }

  // generates an arraylist of the total
  // possible number of edges for each gamepiece
  ArrayList<Edge> genEdges(ArrayList<GamePiece> pieces) {
    ArrayList<Edge> edges = new ArrayList<Edge>();
    for (int i = 0; i < this.numNodes; i++) {
      GamePiece piece = this.allNodes.get(i);
      for (GamePiece neighbor : piece.neighbors.values()) {
        if (neighbor != null) {
          edges.add(new Edge(piece, neighbor, new Random().nextInt(200)));
        }
      }
    }
    return edges;
  }

  // initializes the initial representatives for each gamepiece in this case each
  // gamepiece represents itself
  HashMap<GamePiece, GamePiece> initReps(ArrayList<GamePiece> pieces) {
    HashMap<GamePiece, GamePiece> reps = new HashMap<GamePiece, GamePiece>();

    for (GamePiece piece : pieces) {
      reps.put(piece, piece);
    }

    return reps;
  }

  @Override
  public Iterator<GamePiece> iterator() {
    // TODO Auto-generated method stub
    return null;
  }

}


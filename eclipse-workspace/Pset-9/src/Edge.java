class Edge {
  GamePiece fromNode;
  GamePiece toNode;
  int weight;

  Edge(GamePiece fromNode, GamePiece toNode, int weight) {
    this.fromNode = fromNode;
    this.toNode = toNode;
    this.weight = weight;
  }

  void createConnections() {
    if (this.fromNode.row == this.toNode.row) {
      this.fromNode.right = true;
      this.toNode.left = true;
    }
    else {
      this.fromNode.bottom = true;
      this.toNode.top = true;
    }
  }
}

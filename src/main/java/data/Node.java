package data;

import java.util.HashMap;
import java.util.Map;

public class Node {
    public final int id;
    public Map<String, Node> edges;

    public Node(int id) {
        this.id = id;
        this.edges = new HashMap<>();
    }
}
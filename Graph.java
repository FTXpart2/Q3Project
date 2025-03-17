import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;

public class Graph<E> {
    private MyHashMap<E, MyHashMap<E, Integer>> graph;

    public Graph() {
        graph = new MyHashMap<>();
    }

    public void addVertex(E vertex) {
        if (!graph.containsKey(vertex)) {
            graph.put(vertex, new MyHashMap<>());
        }
    }

    public void addEdge(E a, E b, int weight) {
        if (!(graph.containsKey(a) || graph.containsKey(b))) {
            return;
        }

        graph.get(a).put(b, weight);
        graph.get(b).put(a, weight);
    }

    public void removeVertex(E vertex) {
        if (graph.containsKey(vertex)) {
            for (E key : graph.get(vertex).keySet()) {
                graph.get(key).remove(vertex);
            }
            graph.remove(vertex);
        }
    }

    public int getAdjWeight(E a, E b) {
        if (!(graph.containsKey(a) && graph.containsKey(b))) {
            return -1;
        }

        if (!graph.get(a).containsKey(b)) {
            return -1;
        }

        return graph.get(a).get(b);
    }

    public Set<E> getVertices() {
        return graph.keySet();
    }

    public Set<E> getNeighbors(E vertex) {
        if (graph.containsKey(vertex)) {
            return graph.get(vertex).keySet();
        }
        return new MyHashSet<E>();
    }

    public String toString() {
        String str = "";

        for (E key : graph.keySet()) {
            str += key + " -> ";

            for (E key2 : graph.get(key).keySet()) {
                str += key2 + ":" + graph.get(key).get(key2) + " ";
            }
            str += "\n";
        }

        return str;
    }

    public ArrayList<E> getPath(E a, E b) {
        if (!(graph.containsKey(a) && graph.containsKey(b))) {
            return null;
        }

        MyHashMap<E, Integer> distance = new MyHashMap<>();
        MyHashMap<E, E> prev = new MyHashMap<>();
        MyHashSet<E> visited = new MyHashSet<>();
        MyHashSet<E> unvisited = new MyHashSet<>();

        E current = a;
        distance.put(current, 0);
        prev.put(current, null);
        unvisited.add(current);

        while (unvisited.size() > 0) {
            visited.add(current);
            unvisited.remove(current);

            for (E vertex : graph.get(current).keySet()) {
                if (!visited.contains(vertex)) {
                    unvisited.add(vertex);
                    int path = distance.get(current) + graph.get(current).get(vertex);
                    if (!distance.containsKey(vertex) || path < distance.get(vertex)) {
                        distance.put(vertex, path);
                        prev.put(vertex, current);
                    }
                }
            }

            int min = Integer.MAX_VALUE;
            for (E vertex : unvisited) {
                if (distance.containsKey(vertex) && distance.get(vertex) < min) {
                    min = distance.get(vertex);
                    current = vertex;
                }
            }
        }

        ArrayList<E> path = new ArrayList<>();
        while (prev.get(b) != null) {
            path.add(b);
            b = prev.get(b);
        }
        path.add(a);

        Collections.reverse(path);

        return path;
    }
}
import javax.swing.JFrame;

public class Runner{

    public static void main(String[] args) {
        Graph<Location> graph = new Graph<>();
        // Add locations and edges to the graph
        Location berlin = new Location("Berlin", "BER", 800, 400);
        Location munich = new Location("Munich", "MUC", 900, 700);
        Location hamburg = new Location("Hamburg", "HAM", 700, 200);
        // Add more locations here...

        graph.addVertex(berlin);
        graph.addVertex(munich);
        graph.addVertex(hamburg);
        // Add more vertices here...

        graph.addEdge(berlin, munich, 500);
        graph.addEdge(berlin, hamburg, 300);
        graph.addEdge(munich, hamburg, 600);
        // Add more edges here...

        JFrame frame = new JFrame("Graph Display");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1920, 1080);
        frame.add(new Screen(graph));
        frame.pack();
        frame.setVisible(true);
    }
}
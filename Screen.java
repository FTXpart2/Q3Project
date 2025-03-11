import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.HashMap;
import java.util.Map;

public class Screen extends JPanel {
    private BufferedImage image;
    private Graph<Location> graph;
    private Map<Location, Point> positions;

    public Screen(Graph<Location> graph) {
        this.graph = graph;
        this.positions = new HashMap<>();
        try {
            image = ImageIO.read(new File("germany.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        setPreferredSize(new Dimension(1920, 1080));
        initializePositions();
    }

    private void initializePositions() {
        // Example positions for locations (you can customize this)
        positions.put(new Location("Berlin", "BER", 300, 200), new Point(300, 200));
        positions.put(new Location("Munich", "MUC", 200, 400), new Point(200, 400));
        positions.put(new Location("Hamburg", "HAM", 150, 125), new Point(150, 125));
        // Add more locations here...
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null) {
            g.drawImage(image, 0, 0, this);
        }
        drawGraph(g);
    }

    private void drawGraph(Graphics g) {
        // Draw vertices
        for (Location location : graph.getVertices()) {
            Point p = positions.get(location);
            if (p != null) {
                g.fillOval(p.x - 5, p.y - 5, 10, 10);
                g.drawString(location.getAbbv(), p.x + 5, p.y - 5);
            }
        }

        // Draw edges
        for (Location location : graph.getVertices()) {
            Point p1 = positions.get(location);
            if (p1 != null) {
                for (Location neighbor : graph.getNeighbors(location)) {
                    Point p2 = positions.get(neighbor);
                    if (p2 != null) {
                        g.drawLine(p1.x, p1.y, p2.x, p2.y);
                        int midX = (p1.x + p2.x) / 2;
                        int midY = (p1.y + p2.y) / 2;
                        g.drawString(String.valueOf(graph.getAdjWeight(location, neighbor)), midX, midY);
                    }
                }
            }
        }
    }

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

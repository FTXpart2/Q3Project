import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class Screen extends JPanel {
    private BufferedImage image;
    private Graph<Location> graph;
    private Map<Location, Point> positions;
    private List<Location> path;
    private int totalDistance;
    private Map<String, String> roadNames; // New map to store road names

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
        this.path = null;
        this.totalDistance = 0;
        this.roadNames = new HashMap<>();
        initializeRoadNames();
    }

    public void setPath(List<Location> path, int totalDistance) {
        this.path = path;
        this.totalDistance = totalDistance;
        repaint();
    }

    private void initializePositions() {
        // Example positions for locations (you can customize this)
        positions.put(new Location("Berlin", "BER", 300, 190), new Point(300, 200));
        positions.put(new Location("Munich", "MUC", 200, 470), new Point(200, 470));
        positions.put(new Location("Hamburg", "HAM", 180, 125), new Point(180, 125));
        positions.put(new Location("Nuremburg", "NUM", 200, 375), new Point(200, 375));
        positions.put(new Location("Stuttgart", "STU", 130, 450), new Point(130, 450));
        positions.put(new Location("Frankfurt", "FRA", 110, 350), new Point(110, 350));
        positions.put(new Location("Dusseldorf", "DUS", 60, 275), new Point(60, 275));
        positions.put(new Location("Cologne", "COL", 80, 285), new Point(80, 285));
        positions.put(new Location("Dresden", "DRS", 315, 290), new Point(315, 290));
        positions.put(new Location("Leipzig", "LEI", 280, 275), new Point(280, 275));
        positions.put(new Location("Hannover", "HAN", 200, 185), new Point(200, 185));
        positions.put(new Location("Bremen", "BRE", 170, 155), new Point(170, 155));
        positions.put(new Location("Dortmund", "DOR", 90, 260), new Point(90, 260));
        positions.put(new Location("Essen", "ESS", 65, 240), new Point(65, 240));
        positions.put(new Location("Duisburg", "DUI", 35, 260), new Point(35, 260));
        positions.put(new Location("Bochum", "BOC", 30, 280), new Point(30, 280));
        positions.put(new Location("Wuppertal", "WUP", 45, 300), new Point(45, 300));
        positions.put(new Location("Bielefeld", "BIE", 170, 200), new Point(170, 200));
        positions.put(new Location("Bonn", "BON", 100, 300), new Point(100, 300));
        positions.put(new Location("Munster", "MUN", 105, 245), new Point(105, 245));
        // Add more locations here...
    }

    private void initializeRoadNames() {
        // Example road names (you can customize this)
        roadNames.put("BER-MUC", "Highway 101");
        roadNames.put("MUC-BER", "Highway 101");
        roadNames.put("BER-HAM", "Highway 102");
        roadNames.put("HAM-BER", "Highway 102");
        roadNames.put("MUC-HAM", "Highway 103");
        roadNames.put("HAM-MUC", "Highway 103");
        roadNames.put("HAM-NUM", "Highway 104");
        roadNames.put("NUM-HAM", "Highway 104");
        // Add more road names here...
    }

    public String getRoadName(String roadKey) {
        return roadNames.getOrDefault(roadKey, "");
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null) {
            g.drawImage(image, 0, 0, this);
        }
        drawGraph(g);
        if (path != null) {
            drawPath(g);
        }
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
                        String roadKey = location.getAbbv() + "-" + neighbor.getAbbv();
                        String roadName = roadNames.getOrDefault(roadKey, "");
                        g.drawString(roadName, midX, midY + 15); // Display road name
                    }
                }
            }
        }
    }

    private void drawPath(Graphics g) {
        g.setColor(Color.RED);
        for (int i = 0; i < path.size() - 1; i++) {
            Location start = path.get(i);
            Location end = path.get(i + 1);
            Point p1 = positions.get(start);
            Point p2 = positions.get(end);
            if (p1 != null && p2 != null) {
                g.drawLine(p1.x, p1.y, p2.x, p2.y);
                int midX = (p1.x + p2.x) / 2;
                int midY = (p1.y + p2.y) / 2;
                String roadKey = start.getAbbv() + "-" + end.getAbbv();
                String roadName = roadNames.getOrDefault(roadKey, "Unknown Road");
                g.drawString(roadName, midX, midY -15); // Display road name
            }
        }
    }
}



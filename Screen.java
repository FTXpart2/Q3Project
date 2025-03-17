import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Screen extends JPanel {
    private BufferedImage image;
    private Graph<Location> graph;
    private MyHashMap<Location, Point> positions;
    private List<Location> path;
    private int totalDistance;
    private MyHashMap<String, String> roadNames; // New map to store road names

    private JTextField startField;
    private JTextField endField;
    private JButton findPathButton;
    private JTextArea directionsArea;

    public Screen(Graph<Location> graph) {
        this.graph = graph;
        this.positions = new MyHashMap<>();
        try {
            image = ImageIO.read(new File("germany.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        setPreferredSize(new Dimension(1920, 1080));
        initializePositions();
        this.path = null;
        this.totalDistance = 0;
        this.roadNames = new MyHashMap<>();
        initializeRoadNames();
        initializeControls();
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
        positions.put(new Location("Duisburg", "DUI", 35, 240), new Point(35, 240));
        positions.put(new Location("Bochum", "BOC", 30, 270), new Point(30, 270));
        positions.put(new Location("Wuppertal", "WUP", 45, 300), new Point(45, 300));
        positions.put(new Location("Bielefeld", "BIE", 170, 200), new Point(170, 200));
        positions.put(new Location("Bonn", "BON", 100, 300), new Point(100, 300));
        positions.put(new Location("Munster", "MUN", 105, 245), new Point(105, 245));
        // Add more locations here...
    }

    private void initializeRoadNames() {
        // Example road names (you can customize this)
        roadNames.put("BER-LEI", "Highway 101");
        roadNames.put("LEI-BER", "Highway 101");
        roadNames.put("BER-HAN", "Highway 102");
        roadNames.put("HAN-BER", "Highway 102");
        roadNames.put("LEI-NUM", "Highway 103");
        roadNames.put("NUM-LEI", "Highway 103");
        roadNames.put("LEI-DRS", "Highway 104");
        roadNames.put("DRS-LEI", "Highway 104");
        roadNames.put("LEI-HAN", "Highway 105");
        roadNames.put("HAN-LEI", "Highway 105");
        roadNames.put("NUM-MUC", "Highway 106");
        roadNames.put("MUC-NUM", "Highway 106");
        roadNames.put("NUM-STU", "Highway 107");
        roadNames.put("STU-NUM", "Highway 107");
        roadNames.put("STU-MUC", "Highway 108");
        roadNames.put("MUC-STU", "Highway 108");
        roadNames.put("HAN-BRE", "Highway 109");
        roadNames.put("BRE-HAN", "Highway 109");
        roadNames.put("BRE-HAM", "Highway 110");
        roadNames.put("HAM-BRE", "Highway 110");
        roadNames.put("BRE-BIE", "Highway 111");
        roadNames.put("BIE-BRE", "Highway 111");
        roadNames.put("FRA-NUM", "Highway 112");
        roadNames.put("NUM-FRA", "Highway 112");
        roadNames.put("FRA-STU", "Highway 113");
        roadNames.put("STU-FRA", "Highway 113");
        roadNames.put("FRA-BON", "Highway 114");
        roadNames.put("BON-FRA", "Highway 114");
        roadNames.put("BON-COL", "Highway 115");
        roadNames.put("COL-BON", "Highway 115");
        roadNames.put("COL-DUS", "Highway 116");
        roadNames.put("DUS-COL", "Highway 116");
        roadNames.put("BON-WUP", "Highway 117");
        roadNames.put("WUP-BON", "Highway 117");
        roadNames.put("BOC-DUS", "Highway 118");
        roadNames.put("DUS-BOC", "Highway 118");
        roadNames.put("DUS-ESS", "Highway 119");
        roadNames.put("ESS-DUS", "Highway 119");
        roadNames.put("ESS-DUI", "Highway 120");
        roadNames.put("DUI-ESS", "Highway 120");
        roadNames.put("ESS-DOR", "Highway 121");
        roadNames.put("DOR-ESS", "Highway 121");
        roadNames.put("DOR-MUN", "Highway 122");
        roadNames.put("MUN-DOR", "Highway 122");
        roadNames.put("MUN-BIE", "Highway 123");
        roadNames.put("BIE-MUN", "Highway 123");
        roadNames.put("FRA-HAN", "Highway 124");
        roadNames.put("HAN-FRA", "Highway 124");


        // Add more road names here...
    }

    private void initializeControls() {
        setLayout(new BorderLayout());

        JPanel controlPanel = new JPanel();
        startField = new JTextField(5);
        endField = new JTextField(5);
        findPathButton = new JButton("Find Path");
        directionsArea = new JTextArea(10, 30);
        directionsArea.setEditable(false);

        controlPanel.add(new JLabel("Start:"));
        controlPanel.add(startField);
        controlPanel.add(new JLabel("End:"));
        controlPanel.add(endField);
        controlPanel.add(findPathButton);
        controlPanel.add(new JScrollPane(directionsArea));

        add(controlPanel, BorderLayout.EAST);

        findPathButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String startAbbv = startField.getText().toUpperCase();
                String endAbbv = endField.getText().toUpperCase();

                Location start = null, end = null;
                for (Location loc : graph.getVertices()) {
                    if (loc.getAbbv().equals(startAbbv)) {
                        start = loc;
                    }
                    if (loc.getAbbv().equals(endAbbv)) {
                        end = loc;
                    }
                }

                if (start != null && end != null) {
                    List<Location> path = graph.getPath(start, end);
                    int totalDistance = 0;
                    StringBuilder directions = new StringBuilder("Directions:\n");
                    for (int i = 0; i < path.size() - 1; i++) {
                        Location from = path.get(i);
                        Location to = path.get(i + 1);
                        int distance = graph.getAdjWeight(from, to);
                        totalDistance += distance;
                        String roadKey = from.getAbbv() + "-" + to.getAbbv();
                        String roadName = getRoadName(roadKey); // Get road name from Screen
                        directions.append("Take ").append(from.getName()).append(" to ").append(to.getName())
                                  .append(" via ").append(roadName).append(" - ").append(distance).append(" miles.\n");
                    }
                    directions.append("The total distance is ").append(totalDistance).append(" miles.");
                    directionsArea.setText(directions.toString());
                    setPath(path, totalDistance);
                } else {
                    directionsArea.setText("Invalid start or end location.");
                }
            }
        });
    }

    public String getRoadName(String roadKey) {
        return roadNames.get(roadKey);
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
        Font originalFont = g.getFont();
        g.setFont(new Font("Arial", Font.PLAIN, 10)); // Set smaller font size
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
                        String roadName = roadNames.get(roadKey);
                       // Display road name
                    }
                }
            }
        }
        g.setFont(originalFont); // Reset to original font
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
                String roadName = roadNames.get(roadKey);
                g.drawString(roadName, midX, midY -15); // Display road name
            }
        }
    }
}



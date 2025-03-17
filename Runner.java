import javax.swing.JFrame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Scanner;

public class Runner {

    public static void main(String[] args) {
        Graph<Location> graph = new Graph<>();
        // Add locations and edges to the graph
        Location berlin = new Location("Berlin", "BER", 800, 400);
        Location munich = new Location("Munich", "MUC", 900, 700);
        Location hamburg = new Location("Hamburg", "HAM", 700, 200);
        Location nuremburg = new Location("Nuremburg", "NUM", 850, 600);
        Location stuttgart = new Location("Stuttgart", "STU", 800, 650);
        Location frankfurt = new Location("Frankfurt", "FRA", 750, 550);
        Location dusseldorf = new Location("Dusseldorf", "DUS", 650, 500);
        Location cologne = new Location("Cologne", "COL", 640, 520);
        Location dresden = new Location("Dresden", "DRS", 900, 450);
        Location leipzig = new Location("Leipzig", "LEI", 850, 450);
        Location hannover = new Location("Hannover", "HAN", 750, 350);
        Location bremen = new Location("Bremen", "BRE", 700, 300);
        Location dortmund = new Location("Dortmund", "DOR", 670, 480);
        Location essen = new Location("Essen", "ESS", 660, 490);
        Location duisburg = new Location("Duisburg", "DUI", 655, 495);
        Location bochum = new Location("Bochum", "BOC", 665, 485);
        Location wuppertal = new Location("Wuppertal", "WUP", 660, 510);
        Location bielefeld = new Location("Bielefeld", "BIE", 700, 450);
        Location bonn = new Location("Bonn", "BON", 640, 530);
        Location munster = new Location("Munster", "MUN", 680, 460);
        // Add more locations here...

        graph.addVertex(berlin);
        graph.addVertex(munich);
        graph.addVertex(hamburg);
        graph.addVertex(nuremburg);
        graph.addVertex(stuttgart);
        graph.addVertex(frankfurt);
        graph.addVertex(dusseldorf);
        graph.addVertex(cologne);
        graph.addVertex(dresden);
        graph.addVertex(leipzig);
        graph.addVertex(hannover);
        graph.addVertex(bremen);
        graph.addVertex(dortmund);
        graph.addVertex(essen);
        graph.addVertex(duisburg);
        graph.addVertex(bochum);
        graph.addVertex(wuppertal);
        graph.addVertex(bielefeld);
        graph.addVertex(bonn);
        graph.addVertex(munster);

        // Add more vertices here...

        graph.addEdge(hamburg, bremen, 60);
        graph.addEdge(bremen, hannover, 100);
        graph.addEdge(bremen, bielefeld, 150);
        graph.addEdge(hannover, berlin, 200);
        graph.addEdge(hannover, leipzig, 200);
        graph.addEdge(berlin, leipzig, 150);
        graph.addEdge(leipzig, dresden, 100);
        graph.addEdge(leipzig, nuremburg, 200);
        graph.addEdge(nuremburg, munich, 150);
        graph.addEdge(nuremburg, stuttgart, 200);
        graph.addEdge(stuttgart, munich, 100);
        graph.addEdge(stuttgart, frankfurt, 100);
        graph.addEdge(nuremburg, frankfurt, 150);
        graph.addEdge(frankfurt, bonn, 100);
        graph.addEdge(bonn, cologne, 20);
        graph.addEdge(cologne, dusseldorf, 20);
        graph.addEdge(bonn, wuppertal, 50);
        graph.addEdge(cologne, dusseldorf, 20);
        graph.addEdge(dusseldorf, bochum, 10);
        graph.addEdge(dusseldorf, essen, 10);
        graph.addEdge(essen, duisburg, 10);
        graph.addEdge(essen, dortmund, 20);
        graph.addEdge(dortmund, munster, 20);
        graph.addEdge(munster, bielefeld, 200);
        graph.addEdge(frankfurt, hannover, 400);
        
        // Add more edges here...

        JFrame frame = new JFrame("Graph Display");
        Screen screen = new Screen(graph);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1920, 1080);
        frame.setLayout(new BorderLayout());
        frame.add(screen, BorderLayout.CENTER);

        // Add JTextArea to display cities and abbreviations
        JTextArea cityListArea = new JTextArea(20, 30);
        cityListArea.setEditable(false);
        StringBuilder cityList = new StringBuilder("Cities and Abbreviations:\n");
        for (Location loc : graph.getVertices()) {
            cityList.append(loc.getName()).append(" (").append(loc.getAbbv()).append(")\n");
        }
        cityListArea.setText(cityList.toString());
        frame.add(new JScrollPane(cityListArea), BorderLayout.WEST);

        frame.pack();
        frame.setVisible(true);
    }
}
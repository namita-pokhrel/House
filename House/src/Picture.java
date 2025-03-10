import javax.swing.*;
import java.awt.*;

/**
 * The {@code Picture} class creates a graphical representation of a house and its surroundings.
 * It includes components like the sky, ground, trees, house, and animated smoke.
 */
public class Picture extends JPanel {

    private static int smokeY = 110; // Starting position for smoke animation

    public Picture() {
        Timer timer = new Timer(50, e -> repaint()); // Repaints every 50 milliseconds
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Scenery.paint(g);
        House.paint(g);
        g.setColor(Color.BLACK);
        g.drawString("Namita Pokhrel", 450, 450);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("House Project");
        Picture picture = new Picture();
        frame.add(picture);
        frame.setSize(600, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    // Scenery-related components
    public static class Scenery {
        public static void paint(Graphics g) {
            Sky.paint(g);
            Ground.paint(g);
            Tree.paint(g, 100, 300); // Left tree
            Tree.paint(g, 490, 300); // Right tree
        }
    }

    public static class Sky {
        public static void paint(Graphics g) {
            g.setColor(Color.CYAN);
            g.fillRect(0, 0, 600, 350); // Fill upper half for the sky
        }
    }

    public static class Ground {
        public static void paint(Graphics g) {
            g.setColor(Color.WHITE);
            g.fillRect(0, 350, 600, 250); // Fill bottom half for the ground
        }
    }

    public static class Tree {
        public static void paint(Graphics g, int x, int y) {
            // Tree trunk
            g.setColor(new Color(101, 67, 33));
            g.fillRect(x - 10, y, 20, 50);

            // Draw tree leaves using triangles
            IsosTriangle.paint(g, x, y, 60, 40, Color.GREEN);
            IsosTriangle.paint(g, x, y - 10, 50, 35, Color.GREEN);
            IsosTriangle.paint(g, x, y - 20, 40, 30, Color.GREEN);
            IsosTriangle.paint(g, x, y - 30, 30, 25, Color.GREEN);
        }
    }

    // House-related components
    public static class House {
        public static void paint(Graphics g) {
            // House body
            g.setColor(Color.BLUE);
            g.fillRect(200, 200, 200, 150); // The house frame

            // Chimney
            g.setColor(Color.DARK_GRAY);
            g.fillRect(240, 140, 20, 40);

            // Roof
            IsosTriangle.paint(g, 300, 150, 220, 50, Color.ORANGE);

            // Smoke 
            g.setColor(Color.LIGHT_GRAY);
            g.fillOval(240, smokeY, 20, 20); // First puff of smoke
            g.fillOval(235, smokeY - 15, 30, 30); // Second puff of smoke
            g.fillOval(230, smokeY - 30, 40, 40); // Third puff of smoke
            updateSmoke(); // Update smoke position for animation

            // Windows
            Window.paint(g, 220, 220, 30, 40); // Top-left window
            Window.paint(g, 350, 220, 30, 40); // Top-right window
            Window.paint(g, 220, 290, 40, 40); // Bottom-left window
            Window.paint(g, 340, 290, 40, 40); // Bottom-right window
            Window.paintRound(g, 280, 220, 40, 40); // Round window

            // Door
            Door.paint(g, 280, 290, 40, 60);
        }

        private static void updateSmoke() {
            smokeY -= 1;
            if (smokeY < 50) {
                smokeY = 110;
            }
        }
    }

    public static class Door {
        public static void paint(Graphics g, int x, int y, int width, int height) {
            g.setColor(Color.ORANGE);
            g.fillRect(x, y, width, height);
            g.setColor(Color.BLACK);
            g.drawRect(x, y, width, height); // Border

            // Handle
            g.setColor(Color.GRAY);
            g.fillOval(x + width - 15, y + height / 2 - 5, 10, 10);
        }
    }

    public static class Window {
        public static void paint(Graphics g, int x, int y, int width, int height) {
            g.setColor(Color.WHITE);
            g.fillRect(x, y, width, height);
            g.setColor(Color.BLACK);
            g.drawRect(x, y, width, height); // Border
            g.drawLine(x, y + height / 2, x + width, y + height / 2); // Horizontal line
            g.drawLine(x + width / 2, y, x + width / 2, y + height); // Vertical line
        }

        public static void paintRound(Graphics g, int x, int y, int width, int height) {
            g.setColor(Color.WHITE);
            g.fillOval(x, y, width, height);
            g.setColor(Color.BLACK);
            g.drawOval(x, y, width, height); // Border
            g.drawLine(x + width / 2, y, x + width / 2, y + height); // Vertical line
            g.drawLine(x, y + height / 2, x + width, y + height / 2); // Horizontal line
        }
    }

    public static class IsosTriangle {
        public static void paint(Graphics g, int x, int y, int base, int height, Color color) {
            g.setColor(color);
            int[] xPoints = {x, x - base / 2, x + base / 2};
            int[] yPoints = {y, y + height, y + height};
            g.fillPolygon(xPoints, yPoints, 3);
        }
    }
}
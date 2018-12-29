//Created By: Sathvik Kuthuru

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Shape;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JComponent;
import javax.swing.JFrame;

public class Game extends JComponent implements Runnable, MouseListener {

	// Colors of the Pieces
	static Color[] colors = { Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW, Color.CYAN };
	// Keep track of how many pieces have been placed
	int numPlaced = 0;
	// Moving Sequence Text variable
	static int labelX = 0;
	// Number of Players
	int numPlayers = 5;
	// Variable to check for piece placement validity
	boolean valid = false;
	// Check if the mouse has been clicked
	boolean clicked = false;
	// Mouse Position
	int mouseX, mouseY;
	// Store Pieces in 2D Array
	Point[][] points = new Point[12][12];

	// Constructor
	public Game() {
		Scanner scan = new Scanner(System.in);
		numPlayers = scan.nextInt();
		Thread t = new Thread(this);
		t.start();
	}

	public void paint(Graphics g) {

		// Get the image for the board
		String fileLoc = "C:\\Users\\Sathvik\\Desktop\\Coding\\Sequence\\src\\sequenceboard.png";
		Image board = Toolkit.getDefaultToolkit().getImage(fileLoc);
		// Height and width of the board
		int height = 600, width = 500;
		
		// Cast to Graphics2D and save it before rotating
		Graphics2D g2d = (Graphics2D) g;
		AffineTransform initialTransform = g2d.getTransform();
		
		// Rotate Board by 90 Degrees
		g2d.translate(width, 0);
		g2d.rotate(Math.PI / 2);
		g2d.drawImage(board, 0, 0, height, width, this);
		g2d.setTransform(initialTransform);
		g2d.translate(0, 0);
		
		// Moving Text
		g2d.setColor(Color.CYAN);
		g2d.drawString("SEQUENCE", labelX, 30);
		labelX++;
		if (labelX > 515)
			labelX = 0;
		
		// Draw all the Pieces
		for (Point[] r : points) {
			for (Point p : r) {
				if (p == null)
					continue;
				g2d.setColor(p.c);
				Ellipse2D.Double currPoint = new Ellipse2D.Double(p.x, p.y, 20, 20);
				g2d.fill(currPoint);
			}
		}
		
		// Display which Player has to go
		g2d.setColor(Color.YELLOW);
		g2d.setFont(new Font("TimesRoman", Font.PLAIN, 30));
		g2d.drawString("Player To Go:", 30, 630);
		g2d.setColor(colors[numPlaced % numPlayers]);
		g2d.fill(new Ellipse2D.Double(250, 600, 40, 40));
		
		// Draw the sequences that have been made
		ArrayList<int[]> m1 = madeSequenceHorizontal();
		ArrayList<int[]> m2 = madeSequenceVertical();
		ArrayList<int[]> m3 = madeSequenceDiagonalDown();
		ArrayList<int[]> m4 = madeSequenceDiagonalUp();
		// Horizontal
		if (m1 != null) {
			for (int[] n : m1) {
				Point a = points[n[0]][n[1]];
				Point b = points[n[0]][n[1] + 4];
				Shape l = new Line2D.Double(a.x + 10, a.y + 10, b.x + 10, b.y + 10);
				g2d.setStroke(new BasicStroke(10));
				g2d.setColor(a.c);
				g2d.draw(l);
			}
		}
		// Vertical
		if (m2 != null) {
			for (int[] n : m2) {
				Point a = points[n[0]][n[1]];
				Point b = points[n[0] + 4][n[1]];
				Shape l = new Line2D.Double(a.x + 10, a.y + 10, b.x + 10, b.y + 10);
				g2d.setStroke(new BasicStroke(10));
				g2d.setColor(a.c);
				g2d.draw(l);
			}
		}
		
		// Diagonal Down
		if (m3 != null) {
			System.out.println(3);
			for (int[] n : m3) {
				Point a = points[n[0]][n[1]];
				Point b = points[n[0] + 4][n[1] + 4];
				Shape l = new Line2D.Double(a.x + 10, a.y + 10, b.x + 10, b.y + 10);
				g2d.setStroke(new BasicStroke(10));
				g2d.setColor(a.c);
				g2d.draw(l);
			}
		}
		// Diagonal Up
		if (m4 != null) {
			for (int[] n : m4) {
				Point a = points[n[0]][n[1]];
				Point b = points[n[0] - 4][n[1] + 4];
				Shape l = new Line2D.Double(a.x + 10, a.y + 10, b.x + 10, b.y + 10);
				g2d.setStroke(new BasicStroke(10));
				g2d.setColor(a.c);
				g2d.draw(l);
			}
		}
		
		
		// If a player hasn't clicked, go onto the next iteration
		if (!clicked)
			return;
		
		// Display that the place clicked on isn't valid
		if (!valid) {
			g2d.setFont(new Font("TimesRoman", Font.PLAIN, 20));
			g2d.setColor(Color.CYAN);
			g2d.drawString("You can't place", 320, 620);
			g2d.drawString("that there!", 320, 640);
			return;
		}

		// Set Position of Points[mouseX][mouseY] = true
		double[] pointLoc = fixPoint(mouseX, mouseY);
		points[pixelToArray(pointLoc[1], 93, 52.5)][pixelToArray(pointLoc[0], 75, 40)] = new Point(pointLoc[0] - 18,
				pointLoc[1] - 40, colors[numPlaced % numPlayers]);
		numPlaced++;
		
		// Set clicked false for next iteration
		clicked = false;

	}

	//Method to check if position is valid or not
	boolean isValid(int x, int y) {
		// Outside Board
		if (x < 57 || x > 455 || y < 66 || y > 593)
			return false;
		// Four Corners
		if (x <= 94 || x >= 416) {
			if (y <= 115 || y >= 544)
				return false;
		}
		// Place has already been filled
		double[] fixed = fixPoint(x, y);
		if (points[pixelToArray(fixed[1], 93, 52.5)][pixelToArray(fixed[0], 75, 40)] != null)
			return false;
		
		// It is a valid position
		return true;
	}

	// Draw A line through 5 horizontal adjacent locations that contain pieces of the same color
	ArrayList<int[]> madeSequenceHorizontal() {
		ArrayList<int[]> res = new ArrayList<>();
		// Iterate through all the rows
		for (int i = 0; i < points.length; i++) {
			// Iterate through all the columns
			for (int j = 0; j < points[0].length - 4; j++) {
				if (points[i][j] == null)
					continue;
				boolean b = true;
				// Iterate through the five positions based on the j
				for (int k = j + 1; k <= j + 4; k++) {
					Point first = points[i][k];
					Point second = points[i][k - 1];
					// Check if two adjacent positions have been filled and are the same color
					if (first == null || second == null || !first.c.equals(second.c)) {
						b = false;
						break;
					}
				}
				// Add this to the List of sequences
				if (b)
					res.add(new int[] { i, j });
			}
		}
		// Either return the List of sequences or null if their are none
		return res.size() == 0 ? null : res;
	}

	// Same as madeSequenceHorizontal() except going vertical
	ArrayList<int[]> madeSequenceVertical() {
		ArrayList<int[]> res = new ArrayList<>();
		// Iterate through Columns first and then Rows
		for (int j = 0; j < points[0].length; j++) {
			for (int i = 0; i < points.length - 4; i++) {
				if (points[i][j] == null)
					continue;
				boolean b = true;
				for (int k = i + 1; k <= i + 4; k++) {
					Point first = points[k][j];
					Point second = points[k - 1][j];
					if (first == null || second == null || !first.c.equals(second.c)) {
						b = false;
						break;
					}
				}
				if (b)
					res.add(new int[] { i, j });
			}
		}
		return res.size() == 0 ? null : res;
	}
	
	ArrayList<int[]> madeSequenceDiagonalDown() {
		ArrayList<int[]> res = new ArrayList<>();
		// Iterate through Columns first and then Rows
		for (int i = 0; i < points.length-4; i++) {
			for (int j = 0; j < points[0].length - 4; j++) {
				if (points[i][j] == null)
					continue;
				boolean b = true;
				int count = j+1;
				for (int k = i + 1; k <= i + 4; k++) {
					Point first = points[k][count];
					Point second = points[k - 1][count-1];
					if (first == null || second == null || !first.c.equals(second.c)) {
						b = false;
						break;
					}
					count++;
				}
				if (b)
					res.add(new int[] { i, j });
			}
		}
		return res.size() == 0 ? null : res;
	}
	
	ArrayList<int[]> madeSequenceDiagonalUp() {
		ArrayList<int[]> res = new ArrayList<>();
		// Iterate through Columns first and then Rows
		for (int i = points.length-1; i >= 4; i--) {
			for (int j = 0; j < points[0].length - 4; j++) {
				if (points[i][j] == null)
					continue;
				boolean b = true;
				int count = j+1;
				for (int k = i - 1; k >= i - 4; k--) {
					Point first = points[k][count];
					Point second = points[k + 1][count-1];
					if (first == null || second == null || !first.c.equals(second.c)) {
						b = false;
						break;
					}
					count++;
				}
				if (b)
					res.add(new int[] { i, j });
			}
		}
		return res.size() == 0 ? null : res;
	}

	// Convert (x, y) position in Jframe into indices 
	static int pixelToArray(double n, double a, double b) {
		return (int) ((n - a) / b);
	}

	// Adjust (x, y) position so that it fits into center of card
	static double[] fixPoint(int x, int y) {
		double a = 75;
		double b = 93;
		while (a < x - 20)
			a += 40;
		while (b < y - 27)
			b += 52.5;
		// System.out.println(a + " " +b);
		return new double[] { Math.min(a, 435), Math.min(b, 565.5) };
	}

	// Setup JFrame
	public static void main(String[] args) {
		Game in = new Game();
		JFrame frame = new JFrame("SATHVIK!");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(515, 700);
		frame.setVisible(true);
		frame.add(in);
		frame.addMouseListener(in);
		frame.getContentPane().setBackground(Color.BLACK);
	}

	// Custom Point Class that holds x, y, and color
	static class Point {
		double x;
		double y;
		Color c;

		public Point(double x, double y, Color c) {
			this.x = x;
			this.y = y;
			this.c = c;
		}
	}

	// Constantly run  the Thread
	@Override
	public void run() {
		while (true) {
			repaint();
			try {
				Thread.sleep(30);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	//Handle when mosue is clicked
	@Override
	public void mousePressed(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
		clicked = true;
		if (isValid(mouseX, mouseY))
			valid = true;
		else
			valid = false;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}

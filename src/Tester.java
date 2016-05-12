import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;

@SuppressWarnings("serial")
public class Tester extends PApplet {
	PrintWriter textfile;
	PrintWriter textfile2;
	Scanner s = new Scanner(System.in);
	ArrayList<PImage> images;
	PImage current_image, img;

	public void setup() {
		size(600, 600);
		getPImagesFromPdf("d://testdoc2.pdf", images);

	}

	public void draw() {
		background(255);
		if (images.size() > 0) {
			current_image = images.get(i);
			image(current_image, 0, 0);
		}

	}

	public void makeAnswersUppercase(ArrayList<String> p) {
		for (int a = 0; a < p.size(); a++) {
			String s = p.get(a).toUpperCase();
			p.set(a, s);
		}
	}

	// returns an array list of answers given a pimage
	public ArrayList<String> getStudentAnswers(PImage p) {
		ArrayList<String> answers = new ArrayList<String>();
		p.loadPixels();
		// create rectangles with corners at the x and y
		for (int y = 223; y < 900; y = y + 49) {
			for (int x = 222; x < 555; x = x + 71) {
				Rectangle rect = new Rectangle(x, y, 35, 15, 0, this);
				rectangles.add(rect);
				pixelateRectangle(x, y, rect);
			}
			pixelCount = getAnswersFromRow(rectangles);
			answers.add(getAnswer(pixelCount));
			removePixels(pixelCount);
			removeRectangles(rectangles);
		}
		return answers;
	}

	public void removePixels(ArrayList<Integer> p) {
		for (int a = p.size() - 1; a >= 0; a--) {
			p.remove(a);
		}
	}

	// remove all the rectangles in the Rectangle array list
	public void removeRectangles(ArrayList<Rectangle> r) {
		for (int a = r.size() - 1; a >= 0; a--) {
			r.remove(a);
		}
	}

	private void getAnswersFromFirstDocument() {
		correctanswers = getStudentAnswers(images.get(0));
		System.out.println("The correct answers are: " + correctanswers);
	}

	private void getAnswersManually() {
		System.out.println("How many questions do you want?");
		int nq = s.nextInt();
		System.out.println("Enter the answer for each question.");
		for (int a = 1; a <= nq; a++) {
			System.out.print(a + ": ");
			String ans = s.next();
			while (!ans.equals("A") && !ans.equals("a") && !ans.equals("B")
					&& !ans.equals("b") && !ans.equals("C") && !ans.equals("c")
					&& !ans.equals("D") && !ans.equals("d") && !ans.equals("E")
					&& !ans.equals("e")) {
				System.out
						.println("Invalid answer. Please re-enter the answer to the question.");
				System.out.print(a + ": ");
				ans = s.next();
			}
			correctanswers.add(ans);
		}
		makeAnswersUppercase(correctanswers);
		System.out.println("The correct answers are: " + correctanswers);
	}

	public int getWrongAnswerNum(ArrayList<String> sanswers,
			ArrayList<String> correctAnswers) {
		int wrongAnswers = 0;
		if (sanswers.size() != correctAnswers.size()) {
			System.out
					.println("There is a different number of student answers and teacher answers, we only check till the end of the"
							+ "teachers answers");
		}
		for (int i = 0; i < correctAnswers.size(); i++) {
			if (!(sanswers.get(i).equals(correctAnswers.get(i)))) {
				wrongAnswers++;
			}
		}
		return wrongAnswers;
	}

	public double getPercentage(double a, double b) {
		return (a / b);
	}

	public String getGrade(double percent) {
		if (percent >= 0.9)
			return "A";
		else if (percent >= 0.8 && percent < 0.9)
			return "B";
		else if (percent >= 0.7 && percent < 0.8)
			return "C";
		else if (percent >= 0.6 && percent < 0.7)
			return "D";
		else
			return "F";
	}

	// get answer of the row of answers the code is searching
	public ArrayList<Integer> getAnswersFromRow(ArrayList<Rectangle> rect) {
		ArrayList<Integer> p = new ArrayList<Integer>();
		for (int z = 0; z < rect.size(); z++) {
			int pixels = 0;
			Rectangle r = rect.get(z);
			for (int u = r.getX(); u <= r.getX() + r.getWidth(); u++) {
				for (int w = r.getY(); w <= r.getY() + r.getHeight(); w++) {
					int loc = getLoc(u, w);
					float pixelvalue = brightness(current_image.pixels[loc]);
					if (pixelvalue < 150) {
						pixels++;
					}
				}
			}
			p.add(pixels);
		}
		return p;
	}

	// make all pixels either white or black based on their current color
	public void pixelateRectangle(int c, int d, Rectangle r) {
		for (int a = c; a < c + r.width; a++) {
			for (int b = d; b < d + r.height; b++) {
				int loc = getLoc(a, b); // 1d pixel location
				if (brightness(current_image.pixels[loc]) > 150) {
					current_image.pixels[loc] = color(255);
				} else {
					current_image.pixels[loc] = color(0);
				}
			}
		}
	}

	// get the answer of a number by finding answer with most black pixels
	public String getAnswer(ArrayList<Integer> list) {
		int place = 0;
		int largest = list.get(0);
		for (int i = 1; i < 5; i++) {
			if (list.get(i) > largest) {
				largest = list.get(i);
				place = i;
			}
		}
		return findLetterAnswer(place);
	}

	public String findLetterAnswer(int letter) {
		if (letter == 0)
			return "A";
		else if (letter == 1)
			return "B";
		else if (letter == 2)
			return "C";
		else if (letter == 3)
			return "D";
		else if (letter == 4)
			return "E";
		return "W";
	}

	// get 1d pixel location of a pixel given an x and y coordinate
	public int getLoc(int x, int y) {
		return x + y * current_image.width;
	}

	public void mouseReleased() {
		i = (i + 1) % images.size(); // increment current image
	}

	// Loads pdf file from path, converts each page to a PImage and stores them
	// sequentially
	// in the images ArrayList.
	public void getPImagesFromPdf(String path, ArrayList<PImage> images) {
		PDDocument pdf = null;
		try {
			pdf = PDDocument.load(path);
		} catch (IOException e) {
			System.out.println("Couldn't load pdf");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

		@SuppressWarnings("unchecked")
		List<PDPage> pages = pdf.getDocumentCatalog().getAllPages();

		for (PDPage page : pages) {
			try {
				BufferedImage image = page.convertToImage();

				PImage img = new PImage(image.getWidth(), image.getHeight(),
						PConstants.ARGB);
				image.getRGB(0, 0, img.width, img.height, img.pixels, 0,
						img.width);
				img.updatePixels();

				images.add(img);
				System.out.println("Adding page " + images.size());
			} catch (IOException e) {
				System.out.println("problem converting to image");
				e.printStackTrace();
			}
		}

		try {
			pdf.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
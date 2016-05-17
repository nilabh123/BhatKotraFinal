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
	ArrayList<PImage> images;
	PImage current_image, img;
	int i;

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
import java.util.ArrayList;

import processing.core.PImage;
//Implements the methods coded in the Scanner and Tester classes
public class Implement {
	public static void main(String[] args){
		ArrayList<PImage> images1 = new ArrayList<PImage>();
		
		Tester t= new Tester();
		t.getPImagesFromPdf("assets/omrtest.pdf", images1);
		images1.get(0).loadPixels();
		
		System.out.println("Total number of points: 100");
		
		for(int i=1; i<images1.size(); i++){
			images1.get(i).loadPixels();
			images1.get(i).updatePixels();
			
			Scanner s = new Scanner(images1.get(0).pixels,images1.get(i).pixels );
			System.out.println("Student " + i + " " + s.getScore());
		}
	}
}

import java.util.ArrayList;

import processing.core.PImage;

public class Implement {
	public static void main(String[] args){
		ArrayList<PImage> images1 = new ArrayList<PImage>();
		
		Tester t= new Tester();
		t.getPImagesFromPdf("D:/workspace/OmrFiles/omrtest.pdf", images1);
		images1.get(0).loadPixels();
		for(int i=1; i<images1.size(); i++){
			images1.get(i).loadPixels();
			Scanner s = new Scanner(images1.get(0).pixels,images1.get(i).pixels );
			System.out.println(s.getScore());
		}
	}

}

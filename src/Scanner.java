import processing.core.PImage;

public class Scanner {
	PImage student;
	PImage key;
	
	public Scanner(PImage key, PImage student){
		this.key = key;
		this.student = student;
	}
	
	public int[] convertImage(PImage image){
		
	}
	
	public int numRight(int[] answers, int[] student){
		int sum=0;
		if(answers.length==student.length){
			for(int i=0; i< answers.length; i++){
				if(answers[i]==student[i]) sum++;
			}
		}
		
		return sum;
	}
	
	public int getScore(){
		return 0;
	}
	
	public ArrayList<Integer> getStudentAnswers(PImage p) {
		p.loadPixels();
		for (int y = 223; y < 900; y = y + 49) {
			for (int x = 222; x < 555; x = x + 71) {
				if(isBlack(x,y))
			}
		}
	}
		
	public boolean isBlack(int x,int y){
		for(int i=x-10; i<x+10; i++ ){
			for(int  j=y-10; j< y+10; j++){
				if()
			}
		}
	}
	

}


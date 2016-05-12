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
		return 
	}

}

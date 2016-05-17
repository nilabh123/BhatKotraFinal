import java.util.ArrayList;

import processing.core.PImage;

public class Scanner {
	
	int[] student;
	int[] key;
	int scantron_length;
	
	public Scanner(int[] key, int[] student){
		this.key = key;
		this.student = student;
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
	
	public ArrayList<Integer> getAnswers(int[] pic) {
		ArrayList<Integer> a = new ArrayList<Integer>;
		for (int y = 223; y < 900; y = y + 49) {
			int max = Integer.MIN_VALUE;
			int count=0;
			int maxcount=0;
			for (int x = 222; x < 555; x = x + 71) {
				count++;
				if(Blackness(x,y,pic)>max){ 
					maxcount =count; 
					max = Blackness(x,y,pic);
				}
			}
			a.add(maxcount);
		}
	}
		
	public int Blackness(int x,int y, int[] image){
		int sum=0;
		for(int i=x-10; i<x+10; i++ ){
			for(int  j=y-10; j< y+10; j++){
				sum+=image[(i*scantron_length) + j];
			}
		}
		return sum/400;
	}
	
	
	

}


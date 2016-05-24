import java.util.ArrayList;

import processing.core.PImage;

public class Scanner {
	
	int[] student;
	int[] key;
	int scantron_length;
	
	public Scanner(int[] key, int[] student){
		this.key = key;
		this.student = student;
		scantron_length= 1224;
	}

	
	public int numRight(ArrayList<Integer> answers, ArrayList<Integer> student){
		int sum=0;
		if(answers.size()==student.size()){
			for(int i=0; i< answers.size(); i++){
				if(answers.get(i)==student.get(i)) sum++;
			}
		}
		
		return sum;
	}
	
	public int getTotalQuestions() {
		ArrayList<Integer> answers = getAnswers(key);
		return answers.size();
	}
	
	public int getScore(){
		ArrayList<Integer> answers = getAnswers(key);
		ArrayList<Integer> testtaker = getAnswers(student);
		int score = numRight(answers, testtaker);
		return score;
	}
	
	public ArrayList<Integer> getAnswers(int[] pic) {
		ArrayList<Integer> a = new ArrayList<Integer>();

		for (int y = 479; y < 1388; y = y+ 40) {
			int max = Integer.MIN_VALUE;
			int count=0;
			int maxcount=0;
			for (int x = 134; x <=294; x = x + 40) {
				count++;
				if(Blackness(x,y,pic)>max){ 
					maxcount =count; 
					max = Blackness(x,y,pic);
				}
			}
			a.add(maxcount);
		}
		
		for (int y = 479; y < 1388; y = y+ 35) {
			int max = Integer.MIN_VALUE;
			int count=0;
			int maxcount=0;
			for (int x = 420; x <=580; x = x + 35) {
				count++;
				if(Blackness(x,y,pic)>max){ 
					maxcount =count; 
					max = Blackness(x,y,pic);
				}
			}
			a.add(maxcount);
		}
		
		
		
		for (int y = 479; y < 1388; y = y+ 35) {
			int max = Integer.MIN_VALUE;
			int count=0;
			int maxcount=0;
			for (int x = 700; x <=860; x = x + 35) {
				count++;
				if(Blackness(x,y,pic)>max){ 
					maxcount =count; 
					max = Blackness(x,y,pic);
				}
			}
			a.add(maxcount);
		}
		
		for (int y = 479; y < 1388; y = y+ 35) {
			int max = Integer.MIN_VALUE;
			int count=0;
			int maxcount=0;
			for (int x = 980; x <=1140; x = x + 35) {
				count++;
				if(Blackness(x,y,pic)>max){ 
					maxcount =count; 
					max = Blackness(x,y,pic);
				}
			}
			a.add(maxcount);
			
		}
		return a;
	}
		
	public int Blackness(int x,int y, int[] image){
		int sum=0;
		for(int i=x-2; i<=x+2; i++ ){
			for(int  j=y-2; j<= y+2; j++){
				sum+=image[(i*scantron_length) + j];
			}
		}
		return sum / 25;
	}
	
	
	

}


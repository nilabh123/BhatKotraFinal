
public class Implement {
	public static void main(String[] args){
		Tester t= new Tester();
		t.images.get(0).loadPixels();
		for(int i=1; i<t.images.size(); i++){
			t.images.get(i).loadPixels();
			Scanner s = new Scanner(t.images.get(0).pixels,t.images.get(i).pixels );
			System.out.println(s.getScore());
		}
	}

}

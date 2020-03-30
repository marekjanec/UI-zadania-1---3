package package_1;

import java.util.Arrays;

public class Testy {
	public Testy() {
		
	}
	
	public void test1() {
		int sirkaPlochy = 3;
		int vyskaPlochy = 2;
		Riesenie riesenie = new Riesenie();
		
		//riesenie.riesenie(sirkaPlochy, vyskaPlochy, Arrays.asList(0,2,3,4,5,1), Arrays.asList(1,2,3,4,5,0));
		//riesenie.riesenie(sirkaPlochy, vyskaPlochy, Arrays.asList(0,3,4,2,5,1), Arrays.asList(1,2,3,4,5,0));
		riesenie.riesenie(sirkaPlochy, vyskaPlochy, Arrays.asList(0,1,2,3,4,5), Arrays.asList(3,4,5,0,1,2));
	}
	
	public void test2() {
		int sirkaPlochy = 4;
		int vyskaPlochy = 2;
		Riesenie riesenie = new Riesenie();
		
		//riesenie.riesenie(sirkaPlochy, vyskaPlochy, Arrays.asList(0,1,2,3,4,5,6,7), Arrays.asList(0,1,2,3,7,6,5,4));
		riesenie.riesenie(sirkaPlochy, vyskaPlochy, Arrays.asList(0,1,2,3,4,5,6,7), Arrays.asList(3,2,5,4,7,6,1,0));
	}
	
	public void test3() {
		int sirkaPlochy = 3;
		int vyskaPlochy = 3;	
		Riesenie riesenie = new Riesenie();
		
		//riesenie.riesenie(sirkaPlochy, vyskaPlochy, Arrays.asList(5,2,3,4,0,6,7,8,1), Arrays.asList(1,2,3,4,5,6,7,8,0));
		//riesenie.riesenie(sirkaPlochy, vyskaPlochy, Arrays.asList(1,2,3,4,5,6,7,8,0), Arrays.asList(8,0,6,5,4,7,2,3,1));
		riesenie.riesenie(sirkaPlochy, vyskaPlochy, Arrays.asList(1,2,3,4,5,6,7,8,0), Arrays.asList(0,8,7,6,5,4,3,2,1));
		
	}
	
	public void test4() {
		int sirkaPlochy = 5;
		int vyskaPlochy = 2;
		Riesenie riesenie = new Riesenie();
		
		//riesenie.riesenie(sirkaPlochy, vyskaPlochy, Arrays.asList(6,7,8,9,0,5,4,3,2,1), Arrays.asList(0,2,3,4,5,1,9,8,7,6));
		riesenie.riesenie(sirkaPlochy, vyskaPlochy, Arrays.asList(0,1,2,3,4,5,6,7,8,9), Arrays.asList(4,3,2,6,1,9,8,7,5,0));
	}
	
	public void test5() {
		int sirkaPlochy = 4;
		int vyskaPlochy = 3;	
		Riesenie riesenie = new Riesenie();
		
		//riesenie.riesenie(sirkaPlochy, vyskaPlochy, Arrays.asList(5,2,3,4,0,9,10,11,6,7,8,1), Arrays.asList(1,2,3,4,5,6,7,8,9,10,11,0));
		riesenie.riesenie(sirkaPlochy, vyskaPlochy, Arrays.asList(0,1,2,3,4,5,6,7,8,9,10,11), Arrays.asList(3,2,1,0,11,6,5,4,7,10,9,8));
	}
	
	public void test6() {
		int sirkaPlochy = 6;
		int vyskaPlochy = 2;	
		Riesenie riesenie = new Riesenie();
		
		//riesenie.riesenie(sirkaPlochy, vyskaPlochy, Arrays.asList(0,1,2,3,4,5,6,7,8,9,10,11), Arrays.asList(1,2,3,4,5,11,0,6,7,8,9,10));
		riesenie.riesenie(sirkaPlochy, vyskaPlochy, Arrays.asList(0,1,2,3,4,5,6,7,8,9,10,11), Arrays.asList(2,3,4,5,9,11,1,0,6,7,8,10));
		//do hlbky 30 nepretecie
		//riesenie.riesenie(sirkaPlochy, vyskaPlochy, Arrays.asList(0,1,2,3,4,5,6,7,8,9,10,11), Arrays.asList(3,4,5,9,11,10,2,1,6,0,7,8));
		//riesenie.riesenie(sirkaPlochy, vyskaPlochy, Arrays.asList(0,1,2,3,4,5,6,7,8,9,10,11), Arrays.asList(4,5,9,11,10,8,3,2,1,6,7,0));
		//riesenie.riesenie(sirkaPlochy, vyskaPlochy, Arrays.asList(0,1,2,3,4,5,6,7,8,9,10,11), Arrays.asList(4,9,11,10,6,8,3,5,2,0,1,7));
		//riesenie.riesenie(sirkaPlochy, vyskaPlochy, Arrays.asList(0,1,2,3,4,5,6,7,8,9,10,11), Arrays.asList(11,5,0,10,6,8,4,3,9,2,1,7));
		//riesenie.riesenie(sirkaPlochy, vyskaPlochy, Arrays.asList(0,1,2,3,4,5,6,7,8,9,10,11), Arrays.asList(11,5,10,2,6,7,4,0,3,9,8,1));
		
		}
}

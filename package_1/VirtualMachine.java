package package_1;

import java.util.ArrayList;
import java.util.List;

public class VirtualMachine {

	List<String> workMemory = new ArrayList<String>();
	List<String> movesOnMap = new ArrayList<String>();
	Map map;
	Finder finder;
	int workingIndex = 0;
	int oneGoldPrice = 500;
	int startFitness = 499;
	
	//index hladaca pokladu
	int indexHeight = 0;
	int indexWidth = 0;
	
	int indexHeightForPrint = 0;
	int indexWidthForPrint = 0;
	
	
	
	public VirtualMachine(Finder finder, Map map) {
		// TODO Auto-generated constructor stub
		this.map = new Map(map);
		//this.map.mapSetOn(indexHeight, indexWidth, "O");
		this.workMemory.addAll(finder.memory);
		this.finder = finder;
		
	}

	
	public boolean findGold() {
		this.finder.fitness = startFitness;
		for(int i = 0; i < 500; i++) {
			//this.finder.fitness--;
			//zapise pohyb aky sa vykonal
			moveFinder(this.workMemory.get(workingIndex));
			
			//podmienka splnena ak zostal na mape po vykonani pohybov
			if(instructionExeciutor(this.workMemory.get(workingIndex))) {
				//ak po vykonani pohybov uz nie je ziadne zlato returne true
				if(this.map.gold.isEmpty()) {
					return true;
				}
			}else {
				//zostal na mape ale nenasiel este vsetko zlato
				return false;
			}
		}
		//returne false lebo po 500 instrukciach nenaslo riesenie
		return false;
	}
	
	public boolean findGoldPrint() {
		this.finder.fitness = startFitness;
		for(int i = 0; i < 500; i++) {
			//this.finder.fitness--;
			//zapise pohyb aky sa vykonal
			moveFinder(this.workMemory.get(workingIndex));
			//podmienka splnena ak zostal na mape po vykonani pohybov
			if(instructionExeciutorPrint(this.workMemory.get(workingIndex))) {
				
				//ak po vykonani pohybov uz nie je ziadne zlato returne true
				if(this.map.gold.isEmpty()) {
					return true;
				}
			}else {
				//po vykoani pohybov isiel mimo mapy tak returne fasle
				return false;
			}
		}
		//returne false lebo po 500 instrukciach nenaslo riesenie
		return false;
	}
	
	public void increment(String input) {
		StringBuilder str = new StringBuilder(input);
		
		for(int i = (input.length()-1); i >=0; i--) {
			if(str.charAt(i) == '1') {
				str.setCharAt(i, '0');
			}else {
				str.setCharAt(i, '1');
				break;
			}	
		}
		//inkrementne instrukciu na adrese na ktoru ukazuje
		this.workMemory.set(getDecimalIndex(input), str.toString()); 
		//posunie sa na dalsiu instrukciu
		indexCalculator();
	}
	
	public void decrement(String input) {
		StringBuilder str = new StringBuilder(input);
		
		for(int i = (input.length()-1); i >=0; i--) {
			if(str.charAt(i) == '0') {
				str.setCharAt(i, '1');
			}else {
				str.setCharAt(i, '0');
				break;
			}	
		}
		//dekrementne instrukciu na adrese na ktoru ukazuje
		this.workMemory.set(getDecimalIndex(input), str.toString());
		//posunie sa na dalsiu instrukciu
		indexCalculator();
	}
	
	public void jump(String input) {
		this.workingIndex = getDecimalIndex(input);
	}
	
	public boolean print() {
		//vykona vypis a ide na adresu na ktoru ukazuje
		//System.out.println(this.movesOnMap);
		//posunie sa na dalsiu instrukciu
		indexCalculator();
		//vykona pohyby na mape, ked isiel mimo vrati false
		return moveExeciutor();
	}
	
	//print pre vypis cesty
	public boolean printPrint() {
		//vykona vypis a ide na adresu na ktoru ukazuje
		//System.out.println(this.movesOnMap);
		//posunie sa na dalsiu instrukciu
		indexCalculator();
		//vykona pohyby na mape, ked isiel mimo vrati false
		return moveExeciutorPrint();
	}
	
	public int getDecimalIndex(String input) {
		String str = input.substring(2);
		int index = Integer.parseInt(str, 2);
		return index;
	}
	
	public void moveFinder(String input){
		String str = input.substring(6, 8);
		int move = Integer.parseInt(str, 2);
		//00 = hore
		if(move == 0) {
			movesOnMap.add("U"); 
		}
		//01 = dole
		if(move == 1) {
			movesOnMap.add("D");	
		}
		//10 = vlavo
		if(move == 2) {
			movesOnMap.add("L");
		}
		//11 = vpravo
		if(move == 3) {
			movesOnMap.add("R");
		}
	}
	
	public boolean moveExeciutor() {
		for(int i = 0; i < this.movesOnMap.size(); i++) {
			
			//za kazdy pohyb sa mu odcita z fitness
			this.finder.fitness--;
			
			//00 = hore
			checkForGold();
			if(this.movesOnMap.get(i).equals("U")) {
				 if(this.indexHeight == 0) {
					 return false;
				 }
				 this.indexHeight--;
			}
			//01 = dole
			if(this.movesOnMap.get(i).equals("D")) {
				 if(this.indexHeight == (this.map.height - 1)) {
					 return false;
				 }	
				 this.indexHeight++;
			}
			//10 = vlavo
			if(this.movesOnMap.get(i).equals("L")) {
				if(this.indexWidth == 0) {
					 return false;
				}
				this.indexWidth--;
			}
			//11 = vpravo
			if(this.movesOnMap.get(i).equals("R")) {
				if(this.indexWidth == (this.map.width - 1)) {
					 return false;
				}
				this.indexWidth++;
			}
			checkForGold();
		}
		
		//za kazdy pohub sa mu odcita z fitness
		//this.finder.fitness--;
		/*
		 * returne true ked zostalo na mape
		 * ak islo mimo mapu returne false
		 */
		return true;
	}
	
	public boolean moveExeciutorPrint() {
		checkForGold();
		this.map.mapSetOn(this.indexHeight, this.indexWidth, Integer.toString(this.finder.fitness/500));
		for(int i = 0; i < this.movesOnMap.size(); i++) {
			
			//za kazdy pohyb sa mu odcita z fitness
			this.finder.fitness--;
			
			//vypis mapy
			System.out.println();
			System.out.println("Fitness: " + Integer.toString(this.finder.fitness) + " Gold Found: " + Integer.toString(this.finder.fitness/500));
			System.out.println("Gold left: " + this.map.gold.size());
			this.map.printfMap();
			this.map.mapSetOn(this.indexHeight, this.indexWidth, " ");
			
			
			checkForGold();
			//00 = hore
			if(this.movesOnMap.get(i).equals("U")) {
				 if(this.indexHeight == 0) {
					 return false;
				 }
				 this.indexHeight--;
			}
			//01 = dole
			if(this.movesOnMap.get(i).equals("D")) {
				 if(this.indexHeight == (this.map.height - 1)) {
					 return false;
				 }	
				 this.indexHeight++;
			}
			//10 = vlavo
			if(this.movesOnMap.get(i).equals("L")) {
				if(this.indexWidth == 0) {
					 return false;
				}
				this.indexWidth--;
			}
			//11 = vpravo
			if(this.movesOnMap.get(i).equals("R")) {
				if(this.indexWidth == (this.map.width - 1)) {
					 return false;
				}
				this.indexWidth++;
			}
			checkForGold();
			this.map.mapSetOn(this.indexHeight, this.indexWidth, Integer.toString(this.finder.fitness/500));
		}
		//za kazdy pohyb sa mu odcita z fitness
		//this.finder.fitness--;
		/*
		 * returne true ked zostalo na mape
		 * ak islo mimo mapu returne false
		 */
		
		return true;
	}
		
	public void checkForGold() {
		for(int goldIndex = 0; goldIndex < this.map.gold.size(); goldIndex++) {
			ArrayList<Integer> oneGold = this.map.gold.get(goldIndex);
			if(this.indexHeight == oneGold.get(0) && this.indexWidth == oneGold.get(1)) {
				this.finder.fitness += this.oneGoldPrice;
				//System.out.println("Naslo poklad! " + oneGold);
				this.map.gold.remove(goldIndex);
			}
		}
		
	}
	
	public boolean instructionExeciutor(String input) {
		String str = input.substring(0, 2);
		int move = Integer.parseInt(str, 2);
		//00 = hore
		if(move == 0) {
			//System.out.println("inkrement\tUP\t" + this.finder.fitness + "\t" + this.workingIndex); 
			increment(input);
		}
		//01 = dole
		if(move == 1) {
			//System.out.println("dekrement\tDOWN\t" + this.finder.fitness + "\t" + this.workingIndex);	
			decrement(input);
		}
		//10 = vlavo
		if(move == 2) {
			//System.out.println("jump\t\tLEFT\t" + this.finder.fitness + "\t" + this.workingIndex);
			jump(input);
		}
		//11 = vpravo
		if(move == 3) {
			//System.out.println("print\t\tRIGHT\t" + this.finder.fitness + "\t" + this.workingIndex);
			//return false ak ide mimo mapy
			//returne true zozstal na mape
			return print();
		}
		//po vykonani instrukcie return false
		//true moze returnut len print a to v pripade ze po vykonani pohybov zostal na mape
		return false;
	}
	
	public boolean instructionExeciutorPrint(String input) {
		String str = input.substring(0, 2);
		int move = Integer.parseInt(str, 2);
		//00 = hore
		if(move == 0) {
			//System.out.println("inkrement\tUP\t" + this.finder.fitness + "\t" + this.workingIndex); 
			increment(input);
		}
		//01 = dole
		if(move == 1) {
			//System.out.println("dekrement\tDOWN\t" + this.finder.fitness + "\t" + this.workingIndex);	
			decrement(input);
		}
		//10 = vlavo
		if(move == 2) {
			//System.out.println("jump\t\tLEFT\t" + this.finder.fitness + "\t" + this.workingIndex);
			jump(input);
		}
		//11 = vpravo
		if(move == 3) {
			//System.out.println("print\t\tRIGHT\t" + this.finder.fitness + "\t" + this.workingIndex);
			//return false ak ide mimo mapy
			return printPrint();
		}
		return false;
	}
	
	public void indexCalculator() {
		if(this.workingIndex == (this.workMemory.size()-1)) {
			this.workingIndex = 0;
		}else {
			this.workingIndex++;
		}
	}
	
	
	
	
	
}

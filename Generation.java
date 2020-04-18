package package_1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class Generation {
	List<Finder> generation = new ArrayList<Finder>();
	List<Finder> newGeneration = new ArrayList<Finder>();
	Map map;
	int sizeOfGeneration;
	//pocet elitarnych finderov ktory idu do novej generacie
	int numberOfElitFinders = 10;
	
	//pocet instrukcii/genov ktore znova vygenerujem/zmutujem
	int powerOfMutation = 10;
	
	//pomer v ako dostane novy jedinec geny od prveho rodica
	//rodicia budu odovzdavat pesne polovicu svojich genov
	int breedingValue = 32;
	
	//z x vyberam najlepsieho na parenie
	int loteryForBreeding = 5;
	
	//pomer sposobov tvorby novych jedincov
	int newGenerationParts = 9;
	
	public Generation(Map map, int sizeOfGeneration) {
		// TODO Auto-generated constructor stub
		this.map = new Map(map);
		this.sizeOfGeneration = sizeOfGeneration;
		
		//vygenerovanie random finderov
		generationInit();
	}
	
	public void generationInit() {
		Finder finder;
		for(int i = 0; i < this.sizeOfGeneration; i++) {
			finder = new Finder();
			this.generation.add(finder);
		}
	}
	
	public boolean runVirtualMachines() {
		VirtualMachine virtualMachine;
		for(int i = 0; i < this.sizeOfGeneration; i++) {
			//spustenie virtualMachine pre kazdeho z generacie
			virtualMachine = new VirtualMachine(this.generation.get(i), this.map);
			//return true len v pripade ze naslo vsetko zlato a finder zostal na mape
			if(virtualMachine.findGold()) {
				return true;
			}
		}
		//vytriedenie findrov podla fitness
		Collections.sort(this.generation);
		//printGeneration();
		//this.generation.sort(c);
		
		//return fasle lebo finder isiel mimo mapu alebo vykonal 500 instrukcii
		return false;
		
	}
	
	public void newGenerationCreator(boolean elite, boolean mutation, boolean breeding) {
		
		//elitarizmus
		if(elite) {
			//System.out.println("e");
			transferOfElite();
		}else {
			this.newGenerationParts = 10;
		}
		//parenie a mutacia potomka
		if(mutation && breeding) {
			//System.out.println("mb");
			breedingOfGenerationWithMutation();
		}
		//mutacie
		if(mutation && !breeding) {
			//System.out.println("m");
			mutationOfElite();
		}
		//parenie jedincov
		if(breeding && !mutation) {
			//System.out.println("b");
			breedingOfGeneration();
		}
		if(!breeding && !mutation) {
			System.out.println("NOPE, YOU CAN`T HAVE EVERYWHERE FALSE");
			return;
		}
		
		this.generation.clear();
		this.generation.addAll(this.newGeneration);
		this.newGeneration.clear();
	}
	
	//presunutie elitnych x do novej generacie
	public void transferOfElite() {
		for(int i = 0; i < this.numberOfElitFinders; i++) {
			this.generation.get(i).fitness = 0;
			this.newGeneration.add(this.generation.get(i));
		}	
	}
	
	public void mutationOfElite() {
		//podiel z celej genracie
		for(int i = 0; i < this.newGenerationParts; i++) {
			//pocet zmutovanych potomkov z x elitnych rodicov
			for(int j = 0; j < this.numberOfElitFinders; j++) {
				this.generation.get(i).fitness = 0;
				//zmutovany jedinec
				this.newGeneration.add(mutation(this.generation.get(j)));
			}
		}
	}
	
	public Finder mutation(Finder inputFinder) {
		Random rand = new Random();
		Finder finder = new Finder(inputFinder);
		int indexRandomInstruction;
		
		//x krat zmeni nahodny gen/instrukciu v jedincovy
		for(int i = 0; i < this.powerOfMutation; i++) {
			indexRandomInstruction = rand.nextInt(finder.memorySize);
			
			finder.memory.set(indexRandomInstruction, finder.instruction());
		}
		finder.fitness = 0;
		return finder;
	}
	
	
	/*
	 * class SortByFitness implements Comparator<Finder> { 
	    public int compare(Finder a, Finder b) { 
	        return b.fitness - a.fitness; 
	    }
	}
	 */
	
	public void printGeneration() {
		for(int i = 0; i < this.sizeOfGeneration; i++) {
			System.out.println(i + ". " + this.generation.get(i).fitness);
		}
	}

	public Finder ruletteForBreeding() {
		
		Random rand = new Random();
		int maxFitness = 0;
		int indexOfMax = 0;
		
		for(int i = 0; i < this.loteryForBreeding; i++) {
			int index = rand.nextInt(generation.get(0).memorySize);
			if(maxFitness < this.generation.get(index).fitness) {
				maxFitness = this.generation.get(index).fitness;
				indexOfMax = index;
			}
		}
		return this.generation.get(indexOfMax);
	}
	
	public Finder breeding() {
		Finder mom = ruletteForBreeding();
		Finder dad = ruletteForBreeding();
		List<String> childMemory = new ArrayList<String>();
		Finder child;
		
		//maminkine geny
		for(int i = 0; i < this.breedingValue; i++) {
			childMemory.add(mom.memory.get(i));
		}
		
		//tatove geny
		for(int i = this.breedingValue; i < this.generation.get(0).memorySize; i++) {
			childMemory.add(dad.memory.get(i));
		}
		
		child = new Finder(childMemory);
		
		return child;
	}
	
	public Finder breedingWithMutation() {
		Finder mom = ruletteForBreeding();
		Finder dad = ruletteForBreeding();
		List<String> childMemory = new ArrayList<String>();
		Finder child;
		
		//maminkine geny
		for(int i = 0; i < this.breedingValue; i++) {
			childMemory.add(mom.memory.get(i));
		}
		
		//tatove geny
		for(int i = this.breedingValue; i < this.generation.get(0).memorySize; i++) {
			childMemory.add(dad.memory.get(i));
		}
		
		child = new Finder(childMemory);
		
		//returne zmutoaneho retarda
		return mutation(child);
	}

	public void breedingOfGeneration() {
		//podiel z celej genracie
		for(int i = 0; i < (this.newGenerationParts * this.numberOfElitFinders); i++) {
			//vysulozeny jedinec
			this.newGeneration.add(breeding());
		}
	}
	
	public void breedingOfGenerationWithMutation() {
		//podiel z celej genracie
		for(int i = 0; i < (this.newGenerationParts * this.numberOfElitFinders); i++) {
			//vysulozeny jedinec
			this.newGeneration.add(breedingWithMutation());
		}
	}
	
	public Finder heavyMutation(Finder inputFinder) {
			Random rand = new Random();
			Finder finder = new Finder(inputFinder);
			int indexRandomInstruction;
			
			//x krat zmeni nahodny gen/instrukciu v jedincovy
			for(int i = 0; i < (this.powerOfMutation * 10); i++) {
				indexRandomInstruction = rand.nextInt(finder.memorySize);
				
				finder.memory.set(indexRandomInstruction, finder.instruction());
			}
			finder.fitness = 0;
			return finder;
		
	}
	
}

package package_1;

public class StartClass {

	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int mapHeight = 4;
		int mapWidth = 4;
		int numberOfGold = 10;
		int sizeOfGeneration = 100;
		int numberOfGenerations = 100000;
		Map map = new Map(mapHeight, mapWidth, numberOfGold);
		
		boolean elitOn = true;
		boolean mutationOn = true;
		boolean breedingOn = true;
		
		boolean elitOff = false;
		boolean mutationOff = false;
		boolean breedingOff = false;
		
		System.out.println("Start map");
		map.printfMap();
		
		
		/*
		 * mapa, top 10 elitnych, mutacie, parenie, pocet generacii, velkost generacie
		 */
		Evolution evolution = new Evolution(map, elitOn, mutationOn, breedingOn, numberOfGenerations, sizeOfGeneration);
		
		evolution.runEvolution();
		
		evolution.printBest();
		
		/*
		VirtualMachine virtualMachine;
		Generation generation = new Generation(map, sizeOfGeneration);
		
		generation.runVirtualMachines();
	
		 
		
		generation.newGenerationCreator(elitOn, mutationOn, breedingOn);
		System.out.println(generation.runVirtualMachines());
		
		generation.printGeneration();
		generation.generation.get(0).fitness = 0;
		
		virtualMachine = new VirtualMachine(generation.generation.get(0), map);
		virtualMachine.findGoldPrint();
		*/
		/*
		Finder finder = new Finder();
		VirtualMachine virtualMachine = new VirtualMachine(finder, map);
		finder.fitness = 0;
		System.out.println(virtualMachine.findGoldPrint());
		*/
	}

}

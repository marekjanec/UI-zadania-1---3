package package_1;

public class Evolution {
	Map map;
	boolean elit;
	boolean mutation;
	boolean breeding;
	int numberOfGenerations;
	int sizeOfGeneration;
	Generation generation;
	VirtualMachine virtualMachine;
	int lastGeneration;
	
	public Evolution(Map map, boolean elit, boolean mutation, boolean breeding, int numberOfGenerations, int sizeOfGeneration) {
		// TODO Auto-generated constructor stub
		this.map = new Map(map);
		this.elit = elit;
		this.mutation = mutation;
		this.breeding = breeding;
		this.numberOfGenerations = numberOfGenerations;
		this.sizeOfGeneration = sizeOfGeneration;
		this.generation = new Generation(map, this.sizeOfGeneration);
	}

	public void runEvolution() {
		//ak neplati znamena ze naslo vsetko zlato
		this.generation.runVirtualMachines();
		
		
		for(int i = 1; i < this.numberOfGenerations;i++){
			this.lastGeneration = i;
			this.generation.newGenerationCreator(this.elit, this.mutation, this.breeding);
			//System.out.println(this.generation.runVirtualMachines());
			
			//ukonci hladanie len ked naslo vsetko zlato a hladac je stale na mape
			//this.generation.runVirtualMachines();
			this.generation.runVirtualMachines();
			
			//System.out.println("Generacion: " + i);
			//this.generation.printGeneration();
			//printBest();
			
			//this.generation.printGeneration();
			
			
			/*
			 * 	System.out.println("Naslo riesenie! generacia cislo: " + i);
				printBest();
				return;
			}
			System.out.println("generacia cislo: " + i);
			 */
			
		}
	}
	
	public void printBest() {
		this.generation.generation.get(0).fitness = 0;
		System.out.println("Generacia: " + this.lastGeneration);
		virtualMachine = new VirtualMachine(generation.generation.get(0), map);
		virtualMachine.findGoldPrint();
		System.out.println(virtualMachine.finder.fitness);
	}
	
}

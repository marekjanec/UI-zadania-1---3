package package_1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Finder implements Comparable{
	List<String> memory = new ArrayList<String>();
	int fitness = 0;
	int memorySize = 64;
	int instructionSize = 8;
	int indexHeight;
	int indexWidth;
	
	public Finder() {
		// TODO Auto-generated constructor stub
		memmoryInitRandom();
	}
	
	public Finder(Finder finder) {
		// TODO Auto-generated constructor stub
		this.memory.addAll(finder.memory);
	}
	
	public Finder(List<String> memory) {
		// TODO Auto-generated constructor stub
		this.memory.addAll(memory);
	}

	public void memmoryInitRandom(){
		for(int index = 0; index < memorySize; index++) {
			this.memory.add(instruction());
		}
	}
	
	public String instruction() {
		List<String> zeroOneList = new ArrayList<String>();
		zeroOneList.add("1");
		zeroOneList.add("0");
		
		StringBuilder str = new StringBuilder();
		Random rand = new Random();
		
		for(int indexString = 0; indexString < instructionSize; indexString++) {
			int rand_index = rand.nextInt(2);
			String c = zeroOneList.get(rand_index);
			str.append(c);
		}
		
		return str.toString();
	}

	@Override
    public int compareTo(Object object) {
        int objectFitness = ((Finder)object).fitness;
        return objectFitness - this.fitness;
    }

	
}

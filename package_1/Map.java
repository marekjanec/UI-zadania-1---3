package package_1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Map {
	int width;
	int height;
	int numberOfGold;
	List<ArrayList<String>> map = new ArrayList<ArrayList<String>>();
	List<ArrayList<Integer>> gold = new ArrayList<ArrayList<Integer>>();
	
	public Map(int height, int width, int numberOfGold) {
		// TODO Auto-generated constructor stub
		this.height = height;
		this.width = width;
		this.numberOfGold = numberOfGold;
		init();
	}
	
	public Map(Map imputMap) {
		// TODO Auto-generated constructor stub
		this.height = imputMap.height;
		this.width = imputMap.width;
		this.numberOfGold = imputMap.numberOfGold;
		mapCopyMaker(imputMap);
		this.gold.addAll(imputMap.gold);
	}

	public void init() {
		//vytvorenie pozicii zlata
		
		goldList();
		
		//inicializacia mapy na blak spacy
		this.map = new ArrayList<ArrayList<String>>();
		ArrayList<String> line = new ArrayList<String>();
		for(int height = 0; height < this.height; height++) {
			line = new ArrayList<String>();
			for(int width = 0; width < this.width; width++) {
				line.add(" ");
			}
			this.map.add(line);
		}
		//vlozenie pokladu do mapy
		for(int goldIndex = 0; goldIndex < this.gold.size(); goldIndex++) {
			ArrayList<Integer> oneGold = this.gold.get(goldIndex);
			mapSetOn(oneGold.get(0), oneGold.get(1), "X");
		}
	}
	
	public void goldList() {
		//random umiestnenie zlata
		Random rand = new Random();
		ArrayList<Integer> goldLineRow = new ArrayList<Integer>(); 
		this.gold = new ArrayList<ArrayList<Integer>>();
		
		
		int height = rand.nextInt(this.height);
		int width = rand.nextInt(this.width);
		goldLineRow.add(height);
		goldLineRow.add(width);
		this.gold.add(goldLineRow);
		
		for(int gold = 1; gold < numberOfGold;gold++) {
			goldLineRow = new ArrayList<Integer>();
			
			height = rand.nextInt(this.height);
			width = rand.nextInt(this.width);
			
			//generuje random XY indexy kym nenajde este nevygenerovany
			while(checkForSameGold(height, width)) {
				height = rand.nextInt(this.height);
				width = rand.nextInt(this.width);
			}
			
			goldLineRow.add(height);
			goldLineRow.add(width);
			this.gold.add(goldLineRow);
		}
	}
	
	//kontrola ci uz nie je na rovnakej pozicii zlato	
	public boolean checkForSameGold(int height, int width) {
		for(int i = 0; i < this.gold.size(); i++) {
			ArrayList<Integer> oneGold = this.gold.get(i);
			if(oneGold.get(0) == height && oneGold.get(1) == width) {
				return true;
			}	
		}
		return false;
	}
	
	//vypis mapy
	public void printfMap() {
		ArrayList<String> line = new ArrayList<String>();
		for(int height = 0; height < this.height; height++) {
			line = this.map.get(height);
			for(int width = 0; width < this.width; width++) {
				System.out.print("|" + line.get(width));
			}
			System.out.print("|\n");
		}	
	}
	
	public void mapSetOn(int height, int width, String element) {
		ArrayList<String> line = this.map.get(height);
		line.set(width, element);
	}
	
	public String mapGetOn(int height, int width) {
		ArrayList<String> line = this.map.get(height);
		return line.get(width);
	}
	
	public void mapCopyMaker(Map inputMap) {
		this.map = new ArrayList<ArrayList<String>>();
		ArrayList<String> line = new ArrayList<String>();
		for(int height = 0; height < this.height; height++) {
			line = new ArrayList<String>();
			List<String> copyLine = inputMap.map.get(height);
			for(int width = 0; width < this.width; width++) {
				line.add(copyLine.get(width));
			}
			this.map.add(line);
		}
		//vlozenie pokladu do mapy
		for(int goldIndex = 0; goldIndex < this.gold.size(); goldIndex++) {
			ArrayList<Integer> oneGold = this.gold.get(goldIndex);
			mapSetOn(oneGold.get(0), oneGold.get(1), "X");
		}
	}
	
}

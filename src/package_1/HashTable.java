package package_1;

import java.util.ArrayList;
import java.util.List;

public class HashTable {
	public List<Integer> hashList = new ArrayList<Integer>();
	public int velkostHashList = 11;
	public int primarnyHashKluc = 11;
	public int sekundarntHashKluc = 7;
	
	public HashTable() {
		this.initHashList();
	}
	
	public void initHashList() {
		//System.out.println("Inicializacia hash listu na -1");
		for(int i = 0; i < this.velkostHashList; i++) {
			this.hashList.add(-1);
		}	
	} 
	
	public void vlozenieDoHashTabulky(long klucStavuLong) {
		int klucStavu = (int)klucStavuLong;
		int indexPrvy;
		int indexDruhy;
		
		if(klucStavu < 0) {
			System.out.println("PRUSER!!! long nejde na int PRUSER!!!");
		}
		
		indexPrvy = klucStavu % this.primarnyHashKluc;
		//System.out.println("indexPrvy: " + indexPrvy);
		if(this.hashList.get(indexPrvy) != -1) {
			if(this.hashList.get(indexPrvy) == klucStavu) {
				//System.out.println("Stav uz je v tabulke!");
				return;
			}
			indexDruhy = klucStavu % 7;
			//System.out.println("IndexDruhy: " + indexDruhy);
			int i;
			for(i = 1; i < 6; i++) {
				if((indexPrvy +(i * indexDruhy)) < this.velkostHashList &&
						this.hashList.get(indexPrvy + (i * indexDruhy)) == -1) {
					this.hashList.set(indexPrvy + (i * indexDruhy), klucStavu);
					return;
					//System.out.println("indexPrvy + indexDruhy = " + (indexPrvy + indexDruhy));
				}
				if(this.hashList.get(indexPrvy + (i * indexDruhy)) == klucStavu) {
						//System.out.println("Stav uz je v tabulke!");
						return;
				}
			}
		novaHeshTabulka();
			
		}
		else {
			indexPrvy = klucStavu % this.primarnyHashKluc;
			this.hashList.set(indexPrvy, klucStavu);
		
			//System.out.println("Stav vlozeny na " + indexPrvy);
			this.vypisHashListu(this.hashList);
			System.out.println();
		}
	}
	
	public boolean jeKlucPrvocislo(int kluc) {
		if (kluc % 2 == 0)
			return false;
		for (int i = 3; i * i <= kluc; i += 2) {
			if (kluc % i == 0)
				return false;
		}
		return true;
	}
	
	public int novyKluc(double koeficient) {
		
		for (int i = (int)(this.primarnyHashKluc * koeficient); true; i++) {
			if (jeKlucPrvocislo((int)i)) {
				return i;
			}
		}
	}
	
	public void novaHeshTabulka() {
		//System.out.println("Novy kluc sa vytvoril\npovodny: " + this.hashKluc);
		this.primarnyHashKluc = novyKluc(2);
		this.sekundarntHashKluc = novyKluc(1.5);
		//System.out.println("novy: " + this.hashKluc);
		//System.out.println("Inicializacia noveho hash listu na -1");
		
		List<Integer> novaHeshTabulka = new ArrayList<Integer>();
		
		for(int i = 0; i < this.primarnyHashKluc; i++) {
			novaHeshTabulka.add(-1);
		}
		
		for(int i = 0; i < this.velkostHashList; i++) {
			if(this.hashList.get(i) != -1) {
				novaHeshTabulka.set((this.hashList.get(i) % this.primarnyHashKluc), this.hashList.get(i));
			}
		}
		this.velkostHashList = this.primarnyHashKluc;
		
		this.hashList = novaHeshTabulka;
	}
	
	public void vypisHashListu(List<Integer> list) {
			System.out.println(list);
	}
	
	public boolean jeVTabulke(long klucStavuLong) {
	int klucStavu = (int)klucStavuLong;
	int indexPrvy = klucStavu % this.primarnyHashKluc;
	int indexDruhy = klucStavu % this.sekundarntHashKluc;
	
	if(this.hashList.get(indexPrvy) == klucStavu) {
		//System.out.println("Stav " + klucStavu + " je v hashTabulke");
		return true;
	}
	int i;
	for(i = 1; i < 6; i++) {
		if((indexPrvy +(i * indexDruhy)) >= this.velkostHashList) {
			return false;
		}
		if((indexPrvy +(i * indexDruhy)) < this.velkostHashList &&
				this.hashList.get(indexPrvy + (i * indexDruhy)) == -1) {
			
			return false;
			//System.out.println("indexPrvy + indexDruhy = " + (indexPrvy + indexDruhy));
		}
		if(this.hashList.get(indexPrvy + (i * indexDruhy)) == klucStavuLong) {
				//System.out.println("Stav uz je v tabulke!");
				return true;
		}
		//System.out.println("Stav " + klucStavu + " nie je v hashTabulke");
	}
	return false;
}

	public void mazanieKlucaVHashTabulke(long klucStavuLong) {
		int klucStavu = (int)klucStavuLong;
		int indexPrvy = klucStavu % this.primarnyHashKluc;
		int indexDruhy = klucStavu % this.sekundarntHashKluc;
		
		if(this.hashList.get(indexPrvy) == klucStavu) {
			this.hashList.set(indexPrvy, -1);
			//System.out.println("Kluc na " + indexPrvy + " bol vymazany!");
			//this.vypisHashListu(this.hashList);
			//System.out.println();
			return;
		}
		
		int i;
		for(i = 1; i < 6; i++) {
			if(this.hashList.get(indexPrvy + (i * indexDruhy)) == klucStavuLong) {
				this.hashList.set((indexPrvy + indexDruhy), -1);
			}
		}
	}
}
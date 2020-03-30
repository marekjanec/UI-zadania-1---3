package package_1;

import java.util.ArrayList;
import java.util.List;

public class HashTable {
	public List<Long> hashList = new ArrayList<Long>();
	public long velkostHashList = 11;
	public long primarnyHashKluc = 11;
	public long sekundarntHashKluc = 7;
	int doubleHashPosunutie = 5;
	
	public HashTable() {
		this.initHashList();
	}
	
	public void initHashList() {
		//System.out.println("Inicializacia hash listu na -1");
		for(int i = 0; i < this.velkostHashList; i++) {
			this.hashList.add((long) -1);
		}	
	} 
	
	public void vlozenieDoHashTabulky(long klucStavu) {
		//System.out.println(klucStavu);
		//int klucStavu = (int)klucStavuLong;
		int indexPrvy;
		int indexDruhy;
		
		if(klucStavu < (long)0) {
			System.out.println("Kluc je privelky");
			return;
		}
		if(klucStavu == (long)0) {
			System.out.println("vlozenieDoHashTabulky: nemoze byt kluc nula!");
			return;
		}
		
		indexPrvy = (int) (klucStavu % this.primarnyHashKluc);
		//System.out.println("indexPrvy: " + indexPrvy);
		if(this.hashList.get(indexPrvy) != (long)-1) {
			if(this.hashList.get(indexPrvy) == klucStavu) {
				//System.out.println("Stav uz je v tabulke!");
				return;
			}
			indexDruhy = (int) (klucStavu % this.sekundarntHashKluc);
			//System.out.println("IndexDruhy: " + indexDruhy);
			int i;
			for(i = 1; i < doubleHashPosunutie; i++) {
				if((indexPrvy +(i * indexDruhy)) >= this.velkostHashList) {
					break;
				}
				if((indexPrvy +(i * indexDruhy)) < this.velkostHashList &&
						this.hashList.get(indexPrvy + (i * indexDruhy)) == (long)-1) {
					this.hashList.set(indexPrvy + (i * indexDruhy), klucStavu);
					return;
					//System.out.println("indexPrvy + indexDruhy = " + (indexPrvy + indexDruhy));
				}
				if(this.hashList.get(indexPrvy + (i * indexDruhy)) == klucStavu) {
						//System.out.println("Stav uz je v tabulke!");
						return;
				}
			}
			//System.out.println(this.hashList);
			novaHeshTabulka();
			//System.out.println(this.hashList);
			vlozenieDoHashTabulky(klucStavu);
		}
		else {
			indexPrvy = (int) (klucStavu % this.primarnyHashKluc);
			this.hashList.set(indexPrvy, klucStavu);
		
			//System.out.println("Stav vlozeny na " + indexPrvy);
			//this.vypisHashListu(this.hashList);
			//System.out.println();
		}
	}
	
	public void vlozenieDoHashTabulky(long klucStavu, List<Long> novyHashList) {
		//int klucStavu = (int)klucStavuLong;
		int indexPrvy;
		int indexDruhy;
		
		if(klucStavu < (long)0) {
			System.out.println("Kluc je privelky");
			return;
		}
		if(klucStavu == (long)0) {
			System.out.println("vlozenieDoHashTabulky: nemoze byt kluc nula!");
			return;
		}
		
		indexPrvy = (int) (klucStavu % this.primarnyHashKluc);
		//System.out.println("indexPrvy: " + indexPrvy);
		if(novyHashList.get(indexPrvy) != (long)-1) {
			if(novyHashList.get(indexPrvy) == klucStavu) {
				//System.out.println("Stav uz je v tabulke!");
				return;
			}
			indexDruhy = (int) (klucStavu % this.sekundarntHashKluc);
			//System.out.println("IndexDruhy: " + indexDruhy);
			int i;
			for(i = 1; i < doubleHashPosunutie; i++) {
				if((indexPrvy +(i * indexDruhy)) >= this.velkostHashList) {
					System.out.println("Pretieklo kvoli indexu");
					break;
				}
				if((indexPrvy +(i * indexDruhy)) < this.velkostHashList &&
						novyHashList.get(indexPrvy + (i * indexDruhy)) == (long)-1) {
					novyHashList.set(indexPrvy + (i * indexDruhy), klucStavu);
					return;
					//System.out.println("indexPrvy + indexDruhy = " + (indexPrvy + indexDruhy));
				}
				if(novyHashList.get(indexPrvy + (i * indexDruhy)) == klucStavu) {
						//System.out.println("Stav uz je v tabulke!");
						return;
				}
			}
		//novaHeshTabulka();
		//vlozenieDoHashTabulky(klucStavu);
		}
		else {
			indexPrvy = (int) (klucStavu % this.primarnyHashKluc);
			novyHashList.set(indexPrvy, klucStavu);
		
			//System.out.println("Stav vlozeny na " + indexPrvy);
			//this.vypisHashListu(this.hashList);
			//System.out.println();
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
		//System.out.println("prehashuvavam");
		//System.out.println("Novy kluc sa vytvoril\npovodny: " + this.primarnyHashKluc);
		this.primarnyHashKluc = novyKluc(2);
		this.sekundarntHashKluc = novyKluc(1);
		//System.out.println("novy: " + this.primarnyHashKluc);
		//System.out.println("Inicializacia noveho hash listu na -1");
		
		List<Long> novaHeshTabulka = new ArrayList<Long>();
		
		for(int i = 0; i < this.primarnyHashKluc; i++) {
			novaHeshTabulka.add((long) -1);
		}
		
		for(int i = 0; i < this.velkostHashList; i++) {
			if(this.hashList.get(i) != -1) {
				vlozenieDoHashTabulky(this.hashList.get(i), novaHeshTabulka);
			}
		}
		this.velkostHashList = this.primarnyHashKluc;
		
		this.hashList = novaHeshTabulka;
	}
	
	public void vypisHashListu(List<Long> list) {
			System.out.println(list);
	}
	
	public boolean jeVTabulke(long klucStavu) {
	//int klucStavu = (int)klucStavuLong;
	int indexPrvy = (int) (klucStavu % this.primarnyHashKluc);
	int indexDruhy = (int) (klucStavu % this.sekundarntHashKluc);
	
	if(klucStavu == 0) {
		System.out.println("jeVTabulke: nemoze byt kluc nula!");
		return false;
	}
	
	if(this.hashList.get(indexPrvy) == klucStavu) {
		//System.out.println("Stav " + klucStavu + " je v hashTabulke");
		return true;
	}
	int i;
	for(i = 1; i < doubleHashPosunutie; i++) {
		if((indexPrvy +(i * indexDruhy)) >= this.velkostHashList) {
			return false;
		}
		
		if(this.hashList.get(indexPrvy + (i * indexDruhy)) == klucStavu) {
			//System.out.println("indexPrvy + indexDruhy = " + (indexPrvy + indexDruhy));
			//System.out.println("Stav uz je v tabulke!");
			return true;
		}
		//System.out.println("Stav " + klucStavu + " nie je v hashTabulke");
	}
	return false;
}

	public boolean mazanieKlucaVHashTabulke(long klucStavu) {
		if(klucStavu < (long)0) {
			System.out.println("Kluc je zaporny!");
			return false;
		}
		if(klucStavu == (long)0) {
			System.out.println("mazanieKlucaVHashTabulke: nemoze byt kluc nula!");
			return false;
		}
		
		int indexPrvy = (int) (klucStavu % this.primarnyHashKluc);
		int indexDruhy = (int) (klucStavu % this.sekundarntHashKluc);
		
		if(this.hashList.get(indexPrvy) == klucStavu) {
			this.hashList.set(indexPrvy, (long) -1);
			return true;
		}
		
		int i;
		for(i = 1; i < doubleHashPosunutie; i++) {
			if(indexPrvy + (i * indexDruhy) >= this.primarnyHashKluc) {
				return false;
			}
			if(this.hashList.get((indexPrvy + (i * indexDruhy))) == klucStavu) {
				this.hashList.set(((indexPrvy + indexDruhy)), (long) -1);
				return true;
			}
		}
		return false;
	}
}
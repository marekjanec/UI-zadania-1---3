package package_1;

import java.util.ArrayList;
import java.util.List;

public class Stav{
	public List<Integer> list = new ArrayList<Integer>();
	public String operator = "null";
	public int sirkaTabulky = 0;
	public int vyskaTabulky = 0;
	
	public Stav() {
	}
	
	public Stav(int sirkaTabulky, int vyskaTabulky) {
		this.sirkaTabulky = sirkaTabulky;
		this.vyskaTabulky = vyskaTabulky;
	}
	
	public Stav(List<Integer> list, int sirkaTabulky, int vyskaTabulky) {
		this.list.addAll(list);
		this.sirkaTabulky = sirkaTabulky;
		this.vyskaTabulky = vyskaTabulky;
	}
	
	public Stav(List<Integer> list, int sirkaTabulky, int vyskaTabulky, String operator) {
		this.list.addAll(list);
		this.sirkaTabulky = sirkaTabulky;
		this.vyskaTabulky = vyskaTabulky;
		this.operator = operator;
	}
	
	public Stav(List<Integer> list, String operator) {
		this.list.addAll(list);
		this.operator = operator;
	}

	public void vypisStav() {
		//System.out.println("Sirka: " + this.sirkaTabulky + " Vyska: " + this.vyskaTabulky);
		for(int index = 0; index < this.list.size(); index++) {
			if((index+1)% this.sirkaTabulky == 0) {
				System.out.print(this.list.get(index) + "\n");
			}else {
				System.out.print(this.list.get(index) + " ");
			}
		}
	}
	
	public boolean hore() {
		int indexNuly = 0;
		int indexPolicka = 0;
		
		//posuvanie hore len ak je nula/volne policko na idexoch od 0 po 5
		if(this.list.indexOf(0) >= 0 && this.list.indexOf(0) <= ((this.list.size() - 1) - this.sirkaTabulky)) { //nula musi byt na inom indexe ako je posledny riadok
			//System.out.println("\nPosunul som hore!");
			this.operator = "hore";
			//this.vypisStav();
			//System.out.println();
			
			indexNuly = this.list.indexOf(0);
			indexPolicka = this.list.indexOf(0) + this.sirkaTabulky;	//toto musi byt pocet stlpcov
			
			this.list.set(indexNuly, this.list.get(indexPolicka));
			
			this.list.set(indexPolicka, 0);
			//this.vypisStav();
			return true;
		}else {
			//System.out.println("\nNeda sa posunut hore!");
			return false;
		}
	}
	
	public boolean dole() {
		int indexNuly = 0;
		int indexPolicka = 0;
		
		//posuvanie dole len ak je nula/volne policko na indexoch 3 az 8
		if(this.list.indexOf(0) >= this.sirkaTabulky && this.list.indexOf(0) <= (this.list.size()-1)) { //mula musi byt na inom indexe ako je prvy riadok
			//System.out.println("\nPosunul som dole!");
			this.operator = "dole";
			//this.vypisStav();
			//System.out.println();
			
			indexNuly = this.list.indexOf(0);
			indexPolicka = this.list.indexOf(0) - this.sirkaTabulky;	//posuvanie podla poctu stlpcov
			
			this.list.set(indexNuly, this.list.get(indexPolicka));
			
			this.list.set(indexPolicka, 0);
			//this.vypisStav();
			return true;
		}else {
			//System.out.println("\nNeda sa posunut dole!");
			return false;
		}
	}
	
	public boolean vlavo() {
		int indexNuly = 0;
		int indexPolicka = 0;
		
		//posunie vlavo ak je nula/prazdne policko na indexe s mod3 0 alebo 1
		if(this.list.indexOf(0) % this.sirkaTabulky != (this.sirkaTabulky-1)) { //modulo poctu stlpcov musi byt insie ako modulo posledneho stlpca
			//System.out.println("\nPosunul som vlalo!");
			this.operator = "vlavo";
			//this.vypisStav();
			//System.out.println();
			
			indexNuly = this.list.indexOf(0);
			indexPolicka = this.list.indexOf(0) + 1;	//toto sa nemeni asi
			
			this.list.set(indexNuly, this.list.get(indexPolicka));
			
			this.list.set(indexPolicka, 0);
			//this.vypisStav();
			return true;
		}else {
			//System.out.println("\nNeda sa posunut vlavo!");
			return false;
		}
	}
	
	public boolean vpravo() {
		int indexNuly = 0;
		int indexPolicka = 0;
		
		//posuniec vpravo len ak je nula/prazdne policko na indexe s mod.srikaTabulky 0
		if(this.list.indexOf(0) % this.sirkaTabulky != 0) {	
			//System.out.println("\nPosunul som vpravo!");
			this.operator = "vpravo";
			//this.vypisStav();
			//System.out.println();
			
			indexNuly = this.list.indexOf(0);
			indexPolicka = this.list.indexOf(0) - 1;	
			
			this.list.set(indexNuly, this.list.get(indexPolicka));
			
			this.list.set(indexPolicka, 0);
			//this.vypisStav();
			return true;
		}else {
			//System.out.println("\nNeda sa posunut vpravo!");
			return false;
		}
	}
	
	public long stavNaInteger() {
		long kluc = 0;
		
		if(this.list.get(0) == 0) {
			for(int i = 1; i < this.list.size(); i++) {
				kluc *= 10;
				kluc += this.list.get(i);
			}
		}else {
			for(int i = 0; i < this.list.size(); i++) {
				kluc *= 10;
				kluc += this.list.get(i);
			}
		}
		return kluc;
	}
	
	public boolean jeStavRiesitelny() {
		int pomocnik = 0;
		for(int i = 0; i < this.list.size(); i++) {
			if(this.list.get(i) != 0) {
				for(int j = (i + 1); j < this.list.size(); j++) {
					if(this.list.get(i) > this.list.get(j)) {
						pomocnik++;
					}
				}
			}
		}
		if(pomocnik % 2 == 0) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean testStavu() {
		if(jeStavRiesitelny()) {
			System.out.println("je riesiltelny");
			return true;
		}else {
			System.out.println("nie jeriesilteny");
			return false;
		}
	}


	@Override
    public boolean equals(Object obj) {
        if(obj instanceof Stav){
        	Stav druhy = (Stav)obj;
            if(this.list == druhy.list){
                return true;
            }
            else{
                return false;
            }
        }
        else{
            return false;
        }
    }
}

package package_1;

import java.util.LinkedList;
import java.util.Queue;

public class Prehladavanie{
	public Stav pociatocnyStav;
	public Stav koncovyStav;
	public HashTable hashTabulka = new HashTable();
	//hash tabulku na from budem brat zo vstupu aby som mal spolocnu pre oba smery
	//public HashTable hashFront = new HashTable();
	public StromovyUzol<Stav> stromStavov;
	//toto je na ot abz som prechadzal strom stavov postupne do sirky
	public Queue<StromovyUzol> haldaUzlovStromu = new LinkedList<>();
	
	public Prehladavanie() {

	}
	
	public Prehladavanie(Stav pociatocnyStav) {
		this.pociatocnyStav = pociatocnyStav;
		//koncovy stav nepotrebujem lebo bude zadany ako pociatocny stav pre hladanie v opacnom smere
		//this.koncovyStav = koncovyStav;
		hashTabulka.vlozenieDoHashTabulky(this.pociatocnyStav.stavNaInteger());
		stromStavov = new StromovyUzol(pociatocnyStav);
		haldaUzlovStromu.add(stromStavov);
	}
	
	public void hladanieRiasenia(StromovyUzol<Stav> uzol, HashTable frontHashTabulka, int sirkaTabulky, int vyskaTabulky, boolean nasloRiesenie) {
		VytvaranieStavov noveStavy = new VytvaranieStavov();
		
		haldaUzlovStromu.remove(uzol);
		noveStavy.novyStav(uzol, hashTabulka, frontHashTabulka, sirkaTabulky, vyskaTabulky, nasloRiesenie);
		//pridavam do haldy deti uzlu
		for(StromovyUzol s: uzol.dieta) {
			haldaUzlovStromu.add(s);
		}
		
		StromovyUzol vrchnyUzolHaldy = haldaUzlovStromu.peek(); 
		if(vrchnyUzolHaldy != null) {
			hladanieRiasenia(vrchnyUzolHaldy, frontHashTabulka, sirkaTabulky, vyskaTabulky, nasloRiesenie);
		}else {
			System.out.println("Prehladalo cely stavovy priestor");
		}
	}

	
	
}

package package_1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Start  implements Runnable {
	public boolean nasloRiesenie = false;
	int sirkaTabulky = 3;
	int vyskaTabulky = 3;
	
	HashTable frontHashTabulka;
	
	List<Integer> listPStav = new ArrayList<Integer>();
	
	//List<Integer> listKStav = new ArrayList<Integer>();
	
	Stav pociatocnyStav;
	//Stav koncovyStav;
	
	StromovyUzol<Stav> stromStartCiel;
	//StromovyUzol<Stav> stromCielStart;
	
	Prehladavanie prehladavanieStartCiel;
	//Prehladavanie prehladavanieCielStart;
	
	public Start(List<Integer> list, HashTable frontHashTabulka, boolean nasloRiesenie) {
		this.nasloRiesenie = nasloRiesenie;
		frontHashTabulka = frontHashTabulka;
		listPStav.addAll(list);
		//listPStav.addAll(Arrays.asList(1,3,0,4,2,6,7,5,8));
		//listKStav.addAll(Arrays.asList(1,2,3,4,5,6,7,8,0));
		this.pociatocnyStav = new Stav(listPStav, sirkaTabulky, vyskaTabulky);
		//this.koncovyStav = new Stav(listKStav, sirkaTabulky , vyskaTabulky);
		this.stromStartCiel = new StromovyUzol(pociatocnyStav);
		//this.stromCielStart = new StromovyUzol(koncovyStav);
		this.prehladavanieStartCiel = new Prehladavanie(pociatocnyStav);
		//this.prehladavanieCielStart = new Prehladavanie(koncovyStav);
	}
	
	@Override
	public void run() {
		prehladavanieStartCiel.hladanieRiasenia(stromStartCiel, frontHashTabulka, sirkaTabulky, vyskaTabulky, nasloRiesenie);
		//prehladavanieCielStart.hladanieRiasenia(stromCielStart, frontHashTabulka, sirkaTabulky, vyskaTabulky, nasloRiesenie);
	}
}

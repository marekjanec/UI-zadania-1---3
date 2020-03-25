package package_1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("kontrolne hovno");
		
		boolean nasloRiesenie = false;
		
		HashTable frontHashTabulka = new HashTable();
		
		Start startVyhladavaniaStartCiel = new Start(Arrays.asList(1,3,0,4,2,6,7,5,8), frontHashTabulka, nasloRiesenie);
		Start startVyhladavaniaCielStart = new Start(Arrays.asList(1,2,3,4,5,6,7,8,0), frontHashTabulka, nasloRiesenie);

        Thread threadStartCiel = new Thread(startVyhladavaniaStartCiel);
        Thread threadCielStart = new Thread(startVyhladavaniaCielStart);
        
        threadStartCiel.start();
        threadCielStart.start();
		
        
		
		/*
		int sirkaTabulky = 3;
		int vyskaTabulky = 3;
		
		List<Integer> listPStav = new ArrayList<Integer>();
		listPStav.addAll(Arrays.asList(1,3,0,4,2,6,7,5,8));
		List<Integer> listKStav = new ArrayList<Integer>();
		listKStav.addAll(Arrays.asList(1,2,3,4,5,6,7,8,0));
		
		Stav pociatocnyStav = new Stav(listPStav, sirkaTabulky, vyskaTabulky);
		Stav koncovyStav = new Stav(listKStav, sirkaTabulky , vyskaTabulky);
		
		//System.out.println("Kluc: " + pociatocnyStav.stavNaInteger());
		//System.out.println("Kluc: " + koncovyStav.stavNaInteger());
		//System.out.println();
		
		Prehladavanie prehladavanieStartCiel = new Prehladavanie(pociatocnyStav);
		Prehladavanie prehladavanieCielStart = new Prehladavanie(koncovyStav);
		
		Thread threadStartCiel = new Thread(prehladavanieStartCiel);
		Thread threadCielStart = new Thread(prehladavanieCielStart);
		
		
		pociatocnyStav.vypisStav();
		System.out.println();
		koncovyStav.vypisStav();
		System.out.println();
		
		HashTable frontObochSmerovPrehladavania = new HashTable();
		*/
		//HashTable hashTabulka = new HashTable();
		
		//frontObochSmerovPrehladavania.vlozenieDoHashTabulky(koncovyStav.stavNaInteger());
		
		//inicializovanie stromu s pociatocnzm stavom
		//StromovyUzol<Stav> strom = new StromovyUzol(pociatocnyStav);
		//hashTabulka.vlozenieDoHashTabulky(pociatocnyStav.stavNaInteger());
		//frontObochSmerovPrehladavania.vlozenieDoHashTabulky(pociatocnyStav.stavNaInteger());
		
		//Stav priebeznyStav = new Stav(strom.getStav().list, sirkaTabulky, vyskaTabulky);
		//frontObochSmerovPrehladavania.mazanieKlucaVHashTabulke(pociatocnyStav.stavNaInteger());
		
		/*
		 * potrebujem spravit aby mi to prechadzalo strom
		 * strom musim premenovat ako rodica, lebo vzdy to bude rodic v opakujucej sa funkcii strom = rodic
		 */
		
		
		//toto je na ot abz som prechadzal strom stavov postupne do sirky
		//Queue<Stav> haldaStavov = new LinkedList<>();
		
		/*
		VytvaranieStavov vytvaranieStavov = new VytvaranieStavov();
		System.out.println("\n---------------------------------------------------------------------------\n");
		vytvaranieStavov.novyUzol(strom, hashTabulka, frontObochSmerovPrehladavania, sirkaTabulky, vyskaTabulky);
		System.out.println("\n---------------------------------------------------------------------------\n");
		System.out.println("\nXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX\n");
		vypisUzlov(strom);
		System.out.println("\nXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX\n");
		System.out.println("\n---------------------------------------------------------------------------\n");
		vytvaranieStavov.novyUzol(strom.getDieta().get(0), hashTabulka, frontObochSmerovPrehladavania, sirkaTabulky, vyskaTabulky);
		System.out.println("\n---------------------------------------------------------------------------\n");
		System.out.println("\nXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX\n");
		vypisUzlov(strom);
		System.out.println("\nXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX\n");
		System.out.println("\n---------------------------------------------------------------------------\n");
		vytvaranieStavov.novyUzol(strom.getDieta().get(1), hashTabulka, frontObochSmerovPrehladavania, sirkaTabulky, vyskaTabulky);
		System.out.println("\n---------------------------------------------------------------------------\n");
		System.out.println("\nXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX\n");
		vypisUzlov(strom);
		System.out.println("\nXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX\n");
		
		*/
		/*
		hashTabulka.vypisHashListu(hashTabulka.hashList);
		hashTabulka.vlozenieDoHashTabulky(pociatocnyStav.stavNaInteger());
		hashTabulka.vlozenieDoHashTabulky(koncovyStav.stavNaInteger());
		hashTabulka.vypisHashListu(hashTabulka.hashList);
		hashTabulka.vlozenieDoHashTabulky(pociatocnyStav.stavNaInteger());
		hashTabulka.vypisHashListu(hashTabulka.hashList);
		hashTabulka.vypisHashListu(hashTabulka.hashList);
		hashTabulka.vlozenieDoHashTabulky(0);

		//hashTabulka.vlozenieDoHashTabulky(0);
		hashTabulka.vlozenieDoHashTabulky(1);
		hashTabulka.vlozenieDoHashTabulky(2);
		hashTabulka.vlozenieDoHashTabulky(3);

		hashTabulka.vypisHashListu(hashTabulka.hashList);
		hashTabulka.mazanieKlucaVHashTabulke(0);

		hashTabulka.vypisHashListu(hashTabulka.hashList);
		hashTabulka.vlozenieDoHashTabulky(11);

		hashTabulka.vypisHashListu(hashTabulka.hashList);
		
		hashTabulka.vypisHashListu(hashTabulka.hashList);
		hashTabulka.vlozenieDoHashTabulky(5);
		hashTabulka.vlozenieDoHashTabulky(7);
		hashTabulka.vlozenieDoHashTabulky(8);
		hashTabulka.vlozenieDoHashTabulky(9);
		hashTabulka.vlozenieDoHashTabulky(10);
		hashTabulka.vypisHashListu(hashTabulka.hashList);
		hashTabulka.vlozenieDoHashTabulky(11);
		hashTabulka.vypisHashListu(hashTabulka.hashList);
		hashTabulka.vlozenieDoHashTabulky(22);

		hashTabulka.vypisHashListu(hashTabulka.hashList);
		hashTabulka.vlozenieDoHashTabulky(33);
		hashTabulka.vypisHashListu(hashTabulka.hashList);
		hashTabulka.jeVTabulke(pociatocnyStav.stavNaInteger());
		hashTabulka.jeVTabulke(11);
		*/
		/*
		priebeznyStav.hore();
		priebeznyStav.hore();
		priebeznyStav.hore();
		
		priebeznyStav.dole();
		priebeznyStav.dole();
		priebeznyStav.dole();
		priebeznyStav.vlavo();
		priebeznyStav.vlavo();
		priebeznyStav.vpravo();
		priebeznyStav.vpravo();
		
		priebeznyStav.vpravo();
		*/
	}
	

	
	
	public static void vypisUzlov(StromovyUzol<Stav> uzol){

		System.out.println("RODIC");
		((Stav) uzol.getStav()).vypisStav();

		
		if(uzol.getDieta() != null) {
			
			for(int u = 0; u < uzol.getDieta().size(); u++) {
				System.out.println("DETI: " + u);
				((Stav) uzol.getDieta().get(u).getStav()).vypisStav();
			}
			System.out.println("wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww");
		}
		if(uzol.getDieta() != null) {
			for(int u = 0; u < uzol.getDieta().size(); u++) {
				vypisUzlov(uzol.dieta.get(u));
			}
		}
			
		
	}
}

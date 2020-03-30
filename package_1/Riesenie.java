package package_1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Riesenie {

	public Riesenie() {
		
	}
	
	public void riesenie(int sirkaPlochy, int vyskaPlochy, List<Integer> vstup1 , List<Integer> vstup2) {

		boolean nasloRiesenie = false;
		
		Stav pociatocnyStav1 = new Stav(vstup1, sirkaPlochy, vyskaPlochy);
		Stav pociatocnyStav2 = new Stav(vstup2, sirkaPlochy, vyskaPlochy);
		
		HashTable frontHashTabulka = new HashTable();
		HashTable hashTabulka1 = new HashTable();
		HashTable hashTabulka2 = new HashTable();
		
		Queue<StromovyUzol<Stav>> haldaUzlovStromu1 = new LinkedList<>();
		Queue<StromovyUzol<Stav>> haldaUzlovStromu2 = new LinkedList<>();
		
		StromovyUzol<Stav> stromStavov1 = new StromovyUzol<Stav>(pociatocnyStav1);
		StromovyUzol<Stav> stromStavov2 = new StromovyUzol<Stav>(pociatocnyStav2);
		
		stromStavov1.stav.vypisStav();
		System.out.println();
		stromStavov2.stav.vypisStav();
		System.out.println();
		
		haldaUzlovStromu1.add(stromStavov1);
		haldaUzlovStromu2.add(stromStavov2);
		
		hashTabulka1.vlozenieDoHashTabulky(pociatocnyStav1.stavNaInteger());
		hashTabulka2.vlozenieDoHashTabulky(pociatocnyStav2.stavNaInteger());
		
		frontHashTabulka.vlozenieDoHashTabulky(pociatocnyStav1.stavNaInteger());
		frontHashTabulka.vlozenieDoHashTabulky(pociatocnyStav2.stavNaInteger());
	
		
		
		//if(pociatocnyStav1.testStavu() && pociatocnyStav2.testStavu()) {
		hladanieRiesenia(stromStavov1, stromStavov2, haldaUzlovStromu1, haldaUzlovStromu2, frontHashTabulka, hashTabulka1, hashTabulka2, sirkaPlochy, vyskaPlochy, nasloRiesenie);
		//}
		
		
	}
	
	public static void hladanieRiesenia(StromovyUzol<Stav> uzol1, StromovyUzol<Stav> uzol2,
			Queue<StromovyUzol<Stav>> haldaUzlovStromu1, Queue<StromovyUzol<Stav>> haldaUzlovStromu2,
			HashTable frontHashTabulka, HashTable hashTabulka1, HashTable hashTabulka2,
			int sirkaTabulky, int vyskaTabulky, boolean nasloRiesenie) {
		
		VytvaranieStavov noveStavy1 = new VytvaranieStavov();
		VytvaranieStavov noveStavy2 = new VytvaranieStavov();
		
		//mazem y front hash tabulky lebo uz nie je list
		frontHashTabulka.mazanieKlucaVHashTabulke(uzol1.stav.stavNaInteger());
		haldaUzlovStromu1.poll();
		noveStavy1.novyStav(uzol1, hashTabulka1, frontHashTabulka, sirkaTabulky, vyskaTabulky, nasloRiesenie);
		
		for(StromovyUzol<Stav> s: uzol1.dieta) {
			if(frontHashTabulka.jeVTabulke(s.stav.stavNaInteger())) {
				
				//najdenie zhodneho uzla pre vypis cety
				while(!haldaUzlovStromu2.peek().equals(s)) {
					//naslo uzol
					if(haldaUzlovStromu2.peek().stav.list.equals(s.stav.list)) {
						break;
					}
					//nenaslo uzlo, posuva sa dalej
					else {
						haldaUzlovStromu2.remove();
					}
				}
				System.out.println("\nStav stretu");
				haldaUzlovStromu2.peek().stav.vypisStav();
				System.out.println("\nvypis uzslo/stavov");
				vypisUzlovOdRodica(s.rodic);
				vypisUzlovOdListu(haldaUzlovStromu2.peek());
				nasloRiesenie = true;
				return;
			}else {
				
				
				haldaUzlovStromu1.add(s);
				frontHashTabulka.vlozenieDoHashTabulky(s.stav.stavNaInteger());
				
			}
		}
		
		//mazem y front hash tabulky lebo uz nie je list
		frontHashTabulka.mazanieKlucaVHashTabulke(uzol2.stav.stavNaInteger());
		haldaUzlovStromu2.poll();
		noveStavy2.novyStav(uzol2, hashTabulka2, frontHashTabulka, sirkaTabulky, vyskaTabulky, nasloRiesenie);
		
		for(StromovyUzol<Stav> s: uzol2.dieta) {
			if(frontHashTabulka.jeVTabulke(s.stav.stavNaInteger())) {
				while(!haldaUzlovStromu1.peek().equals(s)) {
					if(haldaUzlovStromu1.peek().stav.list.equals(s.stav.list)) {
						break;
					}else {
						haldaUzlovStromu1.remove();
					}
				}
				System.out.println("\nStav stretu");
				haldaUzlovStromu1.peek().stav.vypisStav();
				System.out.println("\nvypis uzslo/stavov");
				vypisUzlovOdRodica(haldaUzlovStromu1.peek());
				vypisUzlovOdListu(s.rodic);
				nasloRiesenie = true;
				return;
			}else {
				haldaUzlovStromu2.add(s);
				frontHashTabulka.vlozenieDoHashTabulky(s.stav.stavNaInteger());
			}
		}

		if(!nasloRiesenie) {
			StromovyUzol<Stav> vrchnyUzolHaldy1 = haldaUzlovStromu1.peek();
			StromovyUzol<Stav> vrchnyUzolHaldy2 = haldaUzlovStromu2.peek();
			
			hladanieRiesenia(vrchnyUzolHaldy1, vrchnyUzolHaldy2, haldaUzlovStromu1, haldaUzlovStromu2, frontHashTabulka, hashTabulka1, hashTabulka2, sirkaTabulky, vyskaTabulky, nasloRiesenie);
		}
	}
	
	public static void vypisUzlovOdRodica(StromovyUzol<Stav> uzol) {
		if(uzol.rodic != null) {
			vypisUzlovOdRodica(uzol.getRodic());
		}
		
		uzol.stav.vypisStav();
		System.out.println();
	}
	
	public static void vypisUzlovOdListu(StromovyUzol<Stav> uzol) {
		uzol.stav.vypisStav();
		System.out.println();
		
		if(uzol.rodic != null) {
			vypisUzlovOdListu(uzol.getRodic());
		}
	}
}


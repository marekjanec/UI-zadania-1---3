package package_1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("kontrolne hovno");
		
		int sirkaPlochy = 2;
		int vyskaPlochy = 4;
		
		boolean nasloRiesenie = false;
		
		Stav pociatocnyStav1 = new Stav(Arrays.asList(3,1,0,2,5,4,7,6), sirkaPlochy, vyskaPlochy);
		Stav pociatocnyStav2 = new Stav(Arrays.asList(1,2,3,4,5,6,7,0), sirkaPlochy, vyskaPlochy);
		
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
		
		if(pociatocnyStav1.testStavu()) {
			hladanieRiesenia(stromStavov1, stromStavov2, haldaUzlovStromu1, haldaUzlovStromu2, frontHashTabulka, hashTabulka1, hashTabulka2, sirkaPlochy, vyskaPlochy, nasloRiesenie);
		}

		/*
		 * if(pociatocnyStav1.testStavu() && pociatocnyStav2.testStavu()) {
			hladanieRiesenia(stromStavov1, stromStavov2, haldaUzlovStromu1, haldaUzlovStromu2, frontHashTabulka, hashTabulka1, hashTabulka2, 3, 3, nasloRiesenie);
		}
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
	
	public static void vypisUzlovOdRodica(StromovyUzol<Stav> uzol) {
		
		if(uzol.rodic != null) {
			vypisUzlovOdRodica(uzol.getRodic());
		}
		System.out.println("od Rodica");
		uzol.stav.vypisStav();
		System.out.println();
	}
	
	public static void vypisUzlovOdListu(StromovyUzol<Stav> uzol) {
		System.out.println("Od Listu");
		uzol.stav.vypisStav();
		System.out.println();
		
		if(uzol.rodic != null) {
			vypisUzlovOdListu(uzol.getRodic());
		}
		
		
		
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
				System.out.println("naslo vo front hash tabulke spolocny stav:");
				s.stav.vypisStav();
				
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
				System.out.println("\nstav z druhej haldy");
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
				s.stav.vypisStav();
				System.out.println("naslo vo front hash tabulke");
				while(!haldaUzlovStromu1.peek().equals(s)) {
					//haldaUzlovStromu1.peek().stav.vypisStav();
					if(haldaUzlovStromu1.peek().stav.list.equals(s.stav.list)) {
						break;
					}else {
						haldaUzlovStromu1.remove();
					}
				}
				System.out.println("\nstav z druhej haldy");
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

}























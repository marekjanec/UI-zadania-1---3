package package_1;

public class VytvaranieStavov {
	Stav priebeznyStav;
	
	public VytvaranieStavov() {
		
	}

	//ako vstup do funkcie na vytvaranie novych stavou ide povodny/rodicovsky stav/uzol
	public void novyStav(StromovyUzol<Stav> uzol, HashTable hashTabulka, HashTable frontHashTabulka, int sirkaTabulky, int vyskaTabulky, boolean nasloRiesenie) {
		
		priebeznyStav = new Stav(uzol.getStav().list, sirkaTabulky, vyskaTabulky);
		
		//podmienka ktora kontroluje aby sa nespravil reverzny tah alebo ak je to prvy tah
		if(!uzol.getStav().opretor.equals("dole") || uzol.getStav().opretor.equals("null")) {
			//kotrola ci sa da posunut policko hore
			if(priebeznyStav.hore()) {
				//kotrola ci uz dany stav existuje, ak nie tak ho ulozi, inak ide dalej
				if(!hashTabulka.jeVTabulke(priebeznyStav.stavNaInteger())) {
					hashTabulka.vlozenieDoHashTabulky(priebeznyStav.stavNaInteger());
					//kontrola ci dany stav nie je zhodny so stavom z frontu, ak je naslo riesenie, inak prida novy stav do frontHashTabulky
					if(frontHashTabulka.jeVTabulke(priebeznyStav.stavNaInteger())) {
						//tuto by to malo ukoncit vsetko vyhladavanie a ukocit vsetky thready
						System.out.println("\nNaslo zhodu v fromtHashTabulke");
						nasloRiesenie = true;
						return;
					}else {
						//vkladam stav do front hash tabulky
						frontHashTabulka.vlozenieDoHashTabulky(priebeznyStav.stavNaInteger());
						//vkladam stav do stromu
						StromovyUzol<Stav> uzolHore = new StromovyUzol(priebeznyStav);
						uzolHore.setRodic(uzol);
						uzol.addDieta(uzolHore);
					}	
				}
			}
		}
		priebeznyStav = new Stav(uzol.getStav().list, sirkaTabulky, vyskaTabulky);
		
		//podmienka ktora kontroluje aby sa nespravil reverzny tah alebo ak je to prvy tah
		if(!uzol.getStav().opretor.equals("hore") || uzol.getStav().opretor.equals("null")) {
			//kotrola ci sa da posunut policko dole
			if(priebeznyStav.dole()) {
				//kotrola ci uz dany stav existuje, ak nie tak ho ulozi, inak ide dalej
				if(!hashTabulka.jeVTabulke(priebeznyStav.stavNaInteger())) {
					hashTabulka.vlozenieDoHashTabulky(priebeznyStav.stavNaInteger());
					//kontrola ci dany stav nie je zhodny so stavom z frontu, ak je naslo riesenie, inak prida novy stav do frontHashTabulky
					if(frontHashTabulka.jeVTabulke(priebeznyStav.stavNaInteger())) {
						//tuto by to malo ukoncit vsetko vyhladavanie a ukocit vsetky thready
						System.out.println("\nNaslo zhodu v fromtHashTabulke");
						nasloRiesenie = true;
						return;
					}else {
						//vkladam stav do front hash tabulky
						frontHashTabulka.vlozenieDoHashTabulky(priebeznyStav.stavNaInteger());
						//vkladam stav do stromu
						StromovyUzol<Stav> uzolDole = new StromovyUzol(priebeznyStav);
						uzolDole.setRodic(uzol);
						uzol.addDieta(uzolDole);
					}	
				}
			}
		}
		priebeznyStav = new Stav(uzol.getStav().list, sirkaTabulky, vyskaTabulky);
		
		//podmienka ktora kontroluje aby sa nespravil reverzny tah alebo ak je to prvy tah
		if(!uzol.getStav().opretor.equals("vlavo") || uzol.getStav().opretor.equals("null")) {
			//kotrola ci sa da posunut policko vpravo
			if(priebeznyStav.vpravo()) {
				//kotrola ci uz dany stav existuje, ak nie tak ho ulozi, inak ide dalej
				if(!hashTabulka.jeVTabulke(priebeznyStav.stavNaInteger())) {
					hashTabulka.vlozenieDoHashTabulky(priebeznyStav.stavNaInteger());
					//kontrola ci dany stav nie je zhodny so stavom z frontu, ak je naslo riesenie, inak prida novy stav do frontHashTabulky
					if(frontHashTabulka.jeVTabulke(priebeznyStav.stavNaInteger())) {
						//tuto by to malo ukoncit vsetko vyhladavanie a ukocit vsetky thready
						System.out.println("\nNaslo zhodu v fromtHashTabulke");
						nasloRiesenie = true;
						return;
					}else {
						//vkladam stav do front hash tabulky
						frontHashTabulka.vlozenieDoHashTabulky(priebeznyStav.stavNaInteger());
						//vkladam stav do stromu
						StromovyUzol<Stav> uzolVpravo = new StromovyUzol(priebeznyStav);
						uzolVpravo.setRodic(uzol);
						uzol.addDieta(uzolVpravo);
					}	
				}
			}
		}
		priebeznyStav = new Stav(uzol.getStav().list, sirkaTabulky, vyskaTabulky);
		
		//podmienka ktora kontroluje aby sa nespravil reverzny tah alebo ak je to prvy tah
		if(!uzol.getStav().opretor.equals("vpravo") || uzol.getStav().opretor.equals("null")) {
			//kotrola ci sa da posunut policko vlavo
			if(priebeznyStav.vlavo()) {
				//kotrola ci uz dany stav existuje, ak nie tak ho ulozi, inak ide dalej
				if(!hashTabulka.jeVTabulke(priebeznyStav.stavNaInteger())) {
					hashTabulka.vlozenieDoHashTabulky(priebeznyStav.stavNaInteger());
					//kontrola ci dany stav nie je zhodny so stavom z frontu, ak je naslo riesenie, inak prida novy stav do frontHashTabulky
					if(frontHashTabulka.jeVTabulke(priebeznyStav.stavNaInteger())) {
						//tuto by to malo ukoncit vsetko vyhladavanie a ukocit vsetky thready
						System.out.println("\nNaslo zhodu v fromtHashTabulke");
						nasloRiesenie = true;
						return;
					}else {
						//vkladam stav do front hash tabulky
						frontHashTabulka.vlozenieDoHashTabulky(priebeznyStav.stavNaInteger());
						//vkladam stav do stromu
						StromovyUzol<Stav> uzolVlavo = new StromovyUzol(priebeznyStav);
						uzolVlavo.setRodic(uzol);
						uzol.addDieta(uzolVlavo);
					}	
				}
			}
		}
		
		//System.out.println("\nPovodny stav");
		//uzol.getStav().vypisStav();
		//System.out.println("\nnove stavy");
		//for(StromovyUzol s : uzol.getDieta()) {
		//	((Stav)s.getStav()).vypisStav();
		//	System.out.println();
		//}
	}
}


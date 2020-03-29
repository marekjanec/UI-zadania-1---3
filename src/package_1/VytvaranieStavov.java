package package_1;

public class VytvaranieStavov {
	Stav priebeznyStavHore;
	Stav priebeznyStavDole;
	Stav priebeznyStavVpravo;
	Stav priebeznyStavVlavo;
	
	public VytvaranieStavov() {
		
	}

	//ako vstup do funkcie na vytvaranie novych stavou ide povodny/rodicovsky stav/uzol
	public void novyStav(StromovyUzol<Stav> uzol, HashTable hashTabulka, HashTable frontHashTabulka, int sirkaTabulky, int vyskaTabulky, boolean nasloRiesenie) {
		
		this.priebeznyStavHore = new Stav(uzol.stav.list, sirkaTabulky, vyskaTabulky);
		frontHashTabulka.mazanieKlucaVHashTabulke(uzol.stav.stavNaInteger());
		//System.out.println("Stav vymazany z frontu");
		//uzol.stav.vypisStav();
		//System.out.println();
		
		//podmienka ktora kontroluje aby sa nespravil reverzny tah alebo ak je to prvy tah
		if(!uzol.getStav().operator.equals("dole") || uzol.getStav().operator.equals("null")) {
			//kotrola ci sa da posunut policko hore
			if(priebeznyStavHore.hore()) {
				//kotrola ci uz dany stav existuje, ak nie tak ho ulozi, inak ide dalej
				if(!hashTabulka.jeVTabulke(priebeznyStavHore.stavNaInteger())) {
					hashTabulka.vlozenieDoHashTabulky(priebeznyStavHore.stavNaInteger());
					
					//vkladam stav do stromu
					StromovyUzol<Stav> uzolHore = new StromovyUzol<Stav>(priebeznyStavHore);
					uzolHore.setRodic(uzol);
					uzol.addDieta(uzolHore);
					
				}
			}
		}
		this.priebeznyStavDole = new Stav(uzol.stav.list, sirkaTabulky, vyskaTabulky);
		
		//podmienka ktora kontroluje aby sa nespravil reverzny tah alebo ak je to prvy tah
		if(!uzol.getStav().operator.equals("hore") || uzol.getStav().operator.equals("null")) {
			//kotrola ci sa da posunut policko dole
			if(priebeznyStavDole.dole()) {
				//kotrola ci uz dany stav existuje, ak nie tak ho ulozi, inak ide dalej
				if(!hashTabulka.jeVTabulke(priebeznyStavDole.stavNaInteger())) {
					hashTabulka.vlozenieDoHashTabulky(priebeznyStavDole.stavNaInteger());
					
					//vkladam stav do stromu
					StromovyUzol<Stav> uzolDole = new StromovyUzol<Stav>(priebeznyStavDole);
					uzolDole.setRodic(uzol);
					uzol.addDieta(uzolDole);
						
				}
			}
		}
		this.priebeznyStavVpravo = new Stav(uzol.stav.list, sirkaTabulky, vyskaTabulky);
		
		//podmienka ktora kontroluje aby sa nespravil reverzny tah alebo ak je to prvy tah
		if(!uzol.getStav().operator.equals("vlavo") || uzol.getStav().operator.equals("null")) {
			//kotrola ci sa da posunut policko vpravo
			if(priebeznyStavVpravo.vpravo()) {
				//kotrola ci uz dany stav existuje, ak nie tak ho ulozi, inak ide dalej
				if(!hashTabulka.jeVTabulke(priebeznyStavVpravo.stavNaInteger())) {
					hashTabulka.vlozenieDoHashTabulky(priebeznyStavVpravo.stavNaInteger());
					
					//vkladam stav do stromu
					StromovyUzol<Stav> uzolVpravo = new StromovyUzol<Stav>(priebeznyStavVpravo);
					uzolVpravo.setRodic(uzol);
					uzol.addDieta(uzolVpravo);
						
				}
			}
		}
		this.priebeznyStavVlavo = new Stav(uzol.stav.list, sirkaTabulky, vyskaTabulky);
		
		//podmienka ktora kontroluje aby sa nespravil reverzny tah alebo ak je to prvy tah
		if(!uzol.getStav().operator.equals("vpravo") || uzol.getStav().operator.equals("null")) {
			//kotrola ci sa da posunut policko vlavo
			if(priebeznyStavVlavo.vlavo()) {
				//kotrola ci uz dany stav existuje, ak nie tak ho ulozi, inak ide dalej
				if(!hashTabulka.jeVTabulke(priebeznyStavVlavo.stavNaInteger())) {
					hashTabulka.vlozenieDoHashTabulky(priebeznyStavVlavo.stavNaInteger());
					
					//vkladam stav do stromu
					StromovyUzol<Stav> uzolVlavo = new StromovyUzol<Stav>(priebeznyStavVlavo);
					uzolVlavo.setRodic(uzol);
					uzol.addDieta(uzolVlavo);
					
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


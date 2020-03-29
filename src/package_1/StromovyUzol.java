package package_1;

import java.util.ArrayList;
import java.util.List;
/*prevzaty kod https://stackoverflow.com/questions/19330731/tree-implementation-in-java-root-parents-and-children*/

public class StromovyUzol<T>{
    public T stav = null;
    public List<StromovyUzol<T>> dieta = new ArrayList<>();
    public StromovyUzol rodic = null;

    //konstruktor uzla
    public StromovyUzol(T stav) {
        this.stav = stav;
    }

    //pridanie existujuceho dietata uz do uzlu
    public void addDieta(StromovyUzol dieta) {
    	dieta.setRodic(this);
        this.dieta.add(dieta);
    }

    //vytvorenie noveho dietata aj s novym stavom
    public void addDieta(T stav) {
    	StromovyUzol<T> newDieta = new StromovyUzol<>(stav);
        this.addDieta(newDieta);
    }

    //pridanie zoznamu deti jednemu rodicovy
    public void addDetiRodicovi(List<StromovyUzol<T>> dieta) {
        for(StromovyUzol t : dieta) {
            t.setRodic(this);
        }
        this.dieta.addAll(dieta);
    }

    //vrati zoznam deti uzla
    public List<StromovyUzol<T>> getDieta() {
        return dieta;
    }

    //vrati stav utzla
    public T getStav() {
        return stav;
    }

    //nastavenie stavu uzla
    public void setStav(T stav) {
        this.stav = stav;
    }

    //nastavenie rodica uzlu
    public void setRodic(StromovyUzol<T> rodic) {
        this.rodic = rodic;
    }

    //vrati rodica uzlu
    public StromovyUzol getRodic() {
        return rodic;
    }
    
	@Override
    public boolean equals(Object obj) {
        if(obj instanceof StromovyUzol<?>){
        	StromovyUzol<?> druhy = (StromovyUzol<?>)obj;
            if(this.stav == druhy.stav){
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






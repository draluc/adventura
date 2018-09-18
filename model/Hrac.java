/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kodovani: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package model;
import java.util.*;


/**
 * Instance třídy Hrac představují ...
 *
 * @author (jméno autora)
 * @version (číslo verze nebo datum)
 */
public class Hrac
{
    // == Datové atributy (statické i instancí) =====================
    private List<Item> ruce = new ArrayList<>();
    private Item[] batoh = new Item [1];
    private GamePlan plan; 

    // == Konstruktory a tovární metody =============================
    public Hrac(GamePlan plan) {
        this.plan = plan;       
    }
    
    /**
     * Metoda <b>ulozDoBAtohu</b> představuje zpracování příkazu pro
     * uložení předmětu do batohu. Metoda nejprve ověří, že má v ruce 
     * jen jeden předmět, aby jej mohl vložit do batohu. poté ověří zda
     * nemá prázdné ruce a zda není batoh plný. Poté vloží předmět z rukou
     * do batohu.
     * 
     * @return text informující o výsledku zpracování
     */
    public String ulozDoBatohu ()
    {
        if (ruce.size() == 2) {
            return "mám plné ruce, uvolni jednu ruku";
        }
        
        if (ruce.size() == 0) {
            return "nemám nic v ruce";
        }
        
        if (batoh.length == 1){
            return "batoh je plný, vyprázni ho";
        }
           
        batoh[0] = ruce.get(0);
        
        return "uloženo do batohu";
    }
    
    /**
     * Metoda <b>vezmi</b> představuje zpracování příkazu pro
     * sebrání předmětu a ponechání si ho v roukou. Metoda nejprve ověří,
     * že má v ruce prázdné a zda předmět existuje.
     * Poté předmět sebere.
     * 
     * @return text informující o výsledku zpracování
     */
    public String vezmi(Item item ){
                if (ruce.size() == 2) {
            return "mám plné ruce, vyprázdni je";
        }
        
        if (item == null) {
            return "předmět neexisuje";
        }
        ruce.add(item);
        
        return "máš " +item.getName()+ " v ruce";
    }
    
    /**
     * Metoda <b>vyndejZBatohu</b> představuje zpracování příkazu pro
     * vyndání předmětu z batohu a ponechání si ho v roukou. Metoda nejprve ověří,
     * zda nemáte plné ruce a následně ověří jestli máte vůbec něco v batohu.
     * Poté předmět vyndá z batohu.
     * 
     * @return text informující o výsledku zpracování
     */
    public String vyndejZBatohu ()
    {
        if (ruce.size() > 0) {
            return "mám plné ruce, uvolni jednu ruku";
        }
                     
        if (batoh.length == 0){
            return "batoh nic není";
        }
           
        ruce.add(batoh[0]);
        
        return "vyndano z batohu";
    }

    /**
     * Metoda <b>pouzijPredmet</b> představuje zpracování příkazu pro
     * použití předmětu předmětu jako je flétma ektvar klíče. Metoda nejprve ověří,
     * zda nemáte prázdné ruce nebo zda nemáte v rukách více předmětů.
     * Poté předmět použije.
     * 
     * @return text informující o výsledku zpracování
     */
    public String pouzijPredmet () {
        if (ruce.size() == 0) {
            return "mám prázdné ruce, vezmi předmět";
        } 
        
        if (ruce.size() == 2) {
            return "více předmětů nemůže být použito najednou";
        }
        boolean vysledek = false ;
        for (Vchod vchod: plan.getCurrentLocation().getExitLocations()){
            if (vchod.odemkni(ruce.get(0))){
                vysledek = true;
            }
            
            
        }
       
        return vysledek? "odemknuto": "předmět nevaužit";
        
    }
    
        /**
     * Metoda <b>polozPredmet</b> představuje zpracování příkazu pro
     * položení předmětu z rokou. Metoda nejprve ověří,zda máte v ruce
     * nějaký předmět, nebo zda předmět držíte v rukou.
     * Poté předmět položíte.
     * 
     * @return text informující o výsledku zpracování
     */
    public String polozPredmet(String nazevPredmetu) {
        if (ruce.size() == 0) {
            return "nemám žádný předmět k položení" ;
        }
        
        Item item = ruce.stream()
        .filter(predmet -> predmet.getName().equals(nazevPredmetu))
        .findFirst().orElse(null);
        
        if(item == null) {
            return "předmět nedržíte v rukách";
        }
        
        ruce.remove(item);
        plan.getCurrentLocation().addItem(item);
        
        return "předmět položen";
    }
  
}

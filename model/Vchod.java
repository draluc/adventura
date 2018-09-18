/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kodovani: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package model;



/**
 * Instance třídy Vchod představují ...
 *
 * @author (jméno autora)
 * @version (číslo verze nebo datum)
 */
public class Vchod
{
    // == Datové atributy (statické i instancí) =====================
    private Location mistnost;
    private boolean jeOtevren;
    private Item klic;
    // == Konstruktory a tovární metody =============================
     public Vchod(Location mistnost, boolean jeOtevren, Item klic) {
        this.mistnost = mistnost;
        this.jeOtevren = jeOtevren;
        this.klic = klic;
    }
    
    public Location getLocation() {
        return this.mistnost;
    }

    
    public boolean getJeOtevren() {
        return this.jeOtevren;
    }
    
    public boolean odemkni(Item item) {
        if (klic == null){
            return true;
        }
        if (item == null){
            return false;
        }
        if (item.equals(klic)) {
            jeOtevren = true; 
            return true;
             }
        return false;
    }
    // == Nesoukromé metody (instancí i třídy) ======================

    // == Soukromé metody (instancí i třídy) ========================

}

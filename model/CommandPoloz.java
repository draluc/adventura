/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kodovani: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package model;
import java.util.*;

/**
 * Instance třídy CommandPouzij představují ...
 *
 * @author (jméno autora)
 * @version (číslo verze nebo datum)
 */
public class CommandPoloz implements ICommand
{
    private static final String NAME = "poloz";
    private Hrac hrac;
    private GamePlan plan;
    
   /**
    * Konstruktor třídy.
    *
    * @param    plan herní plán, ve kterém se bude ve hře "chodit"
    * @param    hrac, hrac který hru hraje (postavička)
    */
   public CommandPoloz(GamePlan plan, Hrac hrac) {
        this.plan = plan;
        this.hrac = hrac;
    }
    
    /**
     * Metoda <b>process</b> představuje zpracování odkazu na "hrac"
     * pro položemí předmětu z rukou (tento příkaz je již zpracovaný 
     * v "hrac"). Ověří se, zda  nemáte více předmětů v ruce.
     * @param  parameters  pole parametrů zadaných hráčem na příkazovou řádku
     * @return text informující o výsledku zpracování
     */
    public String process(String... parameters) {
              if (parameters.length < 1) {
            return "Nevím, co položit.";
        }
        
        if (parameters.length > 1) {
            return "Více předmětů najednou položit neumím.";
        }
        
        String name = parameters[0];
        
        return this.hrac.polozPredmet(name);
    }

    /**
     * Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *
     * @return    název příkazu
     */
    public String getName() {
        return NAME;
    }
}

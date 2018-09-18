/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kodovani: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package model;



/**
 * Instance třídy CommandVyndejZBatohu představují ...
 *
 * @author (jméno autora)
 * @version (číslo verze nebo datum)
 */
public class CommandVyndejZBatohu implements ICommand
{
    private static final String NAME = "vyndej";
    private Hrac hrac;
    private GamePlan plan;
  
   /**
    * Konstruktor třídy.
    *
    * @param    plan herní plán, ve kterém se bude ve hře "chodit"
    * @param    hrac, hrac který hru hraje (postavička)
    */
    public CommandVyndejZBatohu(GamePlan plan, Hrac hrac) {
        this.plan = plan;
        this.hrac = hrac;
    }
    
    /**
     * Metoda <b>process</b> představuje zpracování odkazu na "hrac"
     * pro vyndání předmětu z batohu (tento příkaz je již zpracovaný 
     * v "hrac").
     * @param  parameters  pole parametrů zadaných hráčem na příkazovou řádku
     * @return text informující o výsledku zpracování
     */
    public String process(String... parameters) {
        return this.hrac.vyndejZBatohu();
        
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


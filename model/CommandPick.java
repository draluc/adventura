package model;


/**
 * Třída <b>CommandPick</b> představuje příkaz pro zvednutí
 * předmětu.
 *
 * @author     Jan Říha, ...
 * @version    LS 2017/2018
 */
public class CommandPick implements ICommand
{
    private static final String NAME = "seber";
    private Hrac hrac;
    private GamePlan plan;
    
   /**
    * Konstruktor třídy.
    *
    * @param    plan herní plán, ve kterém se bude ve hře "chodit"
    */
    public CommandPick(GamePlan plan, Hrac hrac) {
        this.plan = plan;
        this.hrac = hrac;
    }
    
    /**
     * Metoda <b>process</b> představuje zpracování příkazu pro
     * sebrání předmětu. Metoda nejprve ověří, že byla zavolána
     * s jedním parametrem <i>(který představuje název předmětu)</i>.
     * Následně ověří, že daný předmět je v aktuální lokaci.
     * Následně ověří, že daný předmět je přenositelný. Následně
     * ověří, že je možné ho vložit do batohu <i>(že v batohu je
     * volné místo)</i>. Pokud jsou všechny podmínky splněné,
     * předmět je odebrán z lokace a umístěn do batohu.
     * 
     * @param  parameters  pole parametrů zadaných hráčem na příkazovou řádku
     * @return text informující o výsledku zpracování
     */
    public String process(String... parameters) {
        if (parameters.length < 1) {
            return "Nevím, co mám sebrat.";
        }
        
        if (parameters.length > 1) {
            return "Více předmětů najednou sebrat neumím.";
        }
        
        String name = parameters[0];
        Location curLocation = plan.getCurrentLocation();
        
        if (!curLocation.containsItem(name)) {
            return "Předmět " + name + " tady není.";
        }
        
        Item item = curLocation.removeItem(name);
        if (!item.isMoveable()) {
            curLocation.addItem(item);
            return "Předmět " + name + " fakt neuneseš.";
        }
        
        return this.hrac.vezmi(item);
                
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

/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package model;

/**
 * Třída CommandMove implementuje pro hru příkaz jdi.
 * Tato třída je součástí jednoduché textové hry.
 *
 * @author     Jarmila Pavlíčková, Luboš Pavlíček, Jan Říha
 * @version    LS 2017/2018
 */
public class CommandMove implements ICommand {
    private static final String NAME = "jdi";
    private GamePlan plan;

   /**
    * Konstruktor třídy.
    *
    * @param    plan herní plán, ve kterém se bude ve hře "chodit"
    */
    public CommandMove(GamePlan plan) {
        this.plan = plan;
    }

    /**
     * Provádí příkaz "jdi". Zkouší se vyjít do zadané lokace. Pokud lokace
     * existuje, vstoupí se do nového lokace. Pokud zadaná sousední lokace
     * (východ) není, vypíše se chybové hlášení.
     *
     * @param     parameters jako parametr obsahuje jméno lokace (východu), do kterého se má jít.
     * @return    zpráva, kterou vypíše hra hráči
     */ 
    @Override
    public String process(String... parameters) {
        if (parameters.length == 0) {
            // pokud chybí druhé slovo (sousední lokace), tak ....
            return "Kam mám jít? Musíš zadat jméno východu";
        }

        String smer = parameters[0];

        // zkoušíme přejít do sousední lokace
        Vchod exitLocation = plan.getCurrentLocation().getExitLocation(smer);

        if (exitLocation == null) {
            return "Tam se odsud jít nedá!";
        }
        
        if (!exitLocation.getJeOtevren()){
            return "zatím nelze projít";
        }
                    
            plan.setCurrentLocation(exitLocation.getLocation());
            return exitLocation.getLocation().getFullDescription();
        
    }

    /**
     * Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *
     * @return    název příkazu
     */
    @Override
    public String getName() {
        return NAME;
    }

}

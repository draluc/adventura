/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package model;

/**
 * Třída CommandHelp implementuje pro hru příkaz napoveda.
 * Tato třída je součástí jednoduché textové hry.
 *
 * @author     Jarmila Pavlíčková, Luboš Pavlíček, Jan Říha
 * @version    LS 2017/2018
 */
public class CommandHelp implements ICommand {

    private static final String NAME = "napoveda";
    private ListOfCommands listOfCommands;

   /**
    * Konstruktor třídy.
    *
    * @param    listOfCommands seznam příkazů, které je možné ve hře použít, aby je nápověda mohla zobrazit uživateli.
    */    
    public CommandHelp(ListOfCommands listOfCommands) {
        this.listOfCommands = listOfCommands;
    }

    /**
     * Vrací základní nápovědu po zadání příkazu "napoveda". Nyní se vypisuje
     * vcelku primitivní zpráva a seznam dostupných příkazů.
     *
     * @return    napoveda ke hre
     */
    @Override
    public String process(String... parameters) {
        return "Tvým úkolem je dostat se do svého pokoje. \n"
        + "musíš projít halou, kde uspíš bájného medvěda, \n"
        + "poté musíš ze třídy přinést knížku kouzel sevému učiteli do kabinetu,\n"
        + "který ti za ní dá lektvar na omámení klíčníka před vstupem do pokojů.\n"
        + "\n"
        + "Můžeš zadat tyto příkazy:\n"
        + listOfCommands.getCommandNames();
    }

     /**
     * Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání).
     *  
     * @return    název příkazu
     */
    @Override
      public String getName() {
        return NAME;
     }

}

/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package model;

/**
 * Třída implementující toto rozhraní bude ve hře zpracovávat jeden konkrétní příkaz.
 * Toto rozhraní je součástí jednoduché textové hry.
 *
 * @author     Jarmila Pavlíčková, Jan Říha
 * @version    LS 2017/2018
 */
public interface ICommand {

    /**
     * Metoda pro provedení příkazu ve hře.
     * Počet parametrů je závislý na konkrétním příkazu,
     * např. příkazy konec a napoveda nemají parametry
     * příkazy jdi, seber, polož mají jeden parametr
     * příkaz pouzij může mít dva parametry.
     *
     * @param parameters počet parametrů závisí na konkrétním příkazu.
     * @return    výsledek zpracování příkazu (informace pro hráče, které se vypíšou na konzoli)
     */
    String process(String... parameters);
    
    /**
     * Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *
     * @return    název příkazu
     */
    String getName();

}

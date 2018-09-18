/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package model;

/**
 * Rozhraní které musí implementovat hra, je na ně navázáno uživatelské rozhraní.
 *
 * @author     Michael Kolling, Luboš Pavlíček, Jarmila Pavlíčková, Jan Říha
 * @version    LS 2017/2018
 */
public interface IGame
{
    /**
     * Vrátí úvodní zprávu pro hráče.
     *
     * @return    vrací se řetězec, který se má vypsat na obrazovku
     */
    String getProlog();

    /**
     * Vrátí závěrečnou zprávu pro hráče.
     *
     * @return    vrací se řetězec, který se má vypsat na obrazovku
     */
    String getEpilog();

    /**
     * Vrací informaci o tom, zda hra již skončila, je jedno zda výhrou, prohrou nebo příkazem konec.
     *
     * @return    vrací true, pokud hra skončila
     */
    boolean isGameOver();

    /**
     * Metoda zpracuje řetězec uvedený jako parametr, rozdělí ho na slovo příkazu a další parametry.
     * Pak otestuje zda příkaz je klíčovým slovem  např. jdi.
     * Pokud ano spustí samotné provádění příkazu.
     *
     * @param     line text, který zadal uživatel jako příkaz do hry.
     * @return    vrací se řetězec, který se má vypsat na obrazovku
     */
    String processCommand(String line);

    /**
     * Metoda vrátí odkaz na herní plán, je využita hlavně v testech,
     * kde se jejím prostřednictvím získává aktualní místnost hry.
     *
     * @return    odkaz na herní plán
     */
    GamePlan getGamePlan();

}

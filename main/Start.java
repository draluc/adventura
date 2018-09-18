/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */

package main;

import model.Game;
import model.IGame;
import textui.TextUI;

/**
 * Třída Start je hlavní třídou projektu, který představuje jednoduchou
 * textovou adventuru určenou k dalším úpravám a rozšiřování.
 *
 * @author     Jarmila Pavlíčková, Jan Říha
 * @version    LS 2017/2018
 */
public final class Start
{
    /**
     * Metoda, prostřednictvím níž se spouští celá aplikace. Pokud
     * je aplikace spuštěna s parametrem, předpokládá se, že se jedná
     * o cestu k souboru, ze kterého se bude načítat scénář hry.
     *
     * @param args parametry příkazového řádku
     */
    public static void main(String[] args)
    {
        IGame game = new Game();
        TextUI ui = new TextUI(game);

        if (args.length > 0) {
            ui.play(args[0]);
        } else {
            ui.play();
        }
    }

    private Start() {}
}

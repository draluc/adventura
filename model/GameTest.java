package model;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/*******************************************************************************
 * Testovací třída HraTest slouží ke komplexnímu otestování
 * třídy Hra.
 *
 * @author     Jarmila Pavlíčková, Jan Říha
 * @version    LS 2017/2018
 */
public class GameTest {
    private Game game;

    /***************************************************************************
     * Metoda se provede před spuštěním každé testovací metody. Používá se
     * k vytvoření tzv. přípravku (fixture), což jsou datové atributy (objekty),
     * s nimiž budou testovací metody pracovat.
     */
    @Before
    public void setUp() {
        game = new Game();
    }

    /***************************************************************************
     * Testuje průběh hry, po zavolání každěho příkazu testuje, zda hra končí
     * a v jaké aktuální místnosti se hráč nachází.
     * Při dalším rozšiřování hry doporučujeme testovat i jaké věci nebo osoby
     * jsou v místnosti a jaké věci jsou v batohu hráče.
     */
    @Test
    public void testPlayerWin() {
        assertEquals("domeček", game.getGamePlan().getCurrentLocation().getName());
        game.processCommand("jdi les");
        assertEquals("les", game.getGamePlan().getCurrentLocation().getName());
        assertEquals(false, game.isGameOver());
        game.processCommand("jdi hluboký_les");
        assertEquals("hluboký_les", game.getGamePlan().getCurrentLocation().getName());
        assertEquals(false, game.isGameOver());
        game.processCommand("jdi chaloupka");
        assertEquals("chaloupka", game.getGamePlan().getCurrentLocation().getName());
        assertEquals(true, game.isGameOver());
    }

    /***************************************************************************
     * Testuje, zda zavolání příkazu <b>konec</b> skutečně ukončí hru.
     */
    @Test
    public void testPlayerQuit() {
        assertEquals("domeček", game.getGamePlan().getCurrentLocation().getName());
        game.processCommand("jdi les");
        assertEquals("les", game.getGamePlan().getCurrentLocation().getName());
        assertEquals(false, game.isGameOver());
        game.processCommand("konec");
        assertEquals(true, game.isGameOver());
    }

}

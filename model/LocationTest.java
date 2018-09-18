package model;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/*******************************************************************************
 * Testovací třída LokaceTest slouží ke komplexnímu otestování
 * třídy Lokace.
 *
 * @author     Jarmila Pavlíčková, Jan Říha
 * @version    LS 2017/2018
 */
public class LocationTest
{

    /**
     * Testuje, zda jsou správně nastaveny průchody mezi prostory hry. Prostory
     * nemusí odpovídat vlastní hře. 
     */
    @Test
    public  void testExits() {
        Location location1 = new Location("hala", "vstupní hala budovy VŠE na Jižním městě");
        Location location2 = new Location("bufet", "bufet, kam si můžete zajít na svačinku");
        location1.addExit(location2);
        location2.addExit(location1);
        assertEquals(location2, location1.getExitLocation("bufet"));
        assertEquals(null, location2.getExitLocation("pokoj"));
    }

    /**
     * Testuje, zda správně funguje práce s předměty v lokaci. Nastavení
     * nemusí odpovídat vlastní hře. 
     */
    @Test
    public  void testItems() {
        Location location = new Location("hala", "vstupní hala budovy VŠE na Jižním městě");
        Item item1 = new Item("rum", "láhev rumu", true);
        Item item2 = new Item("strom", "starý strom", false);

        location.addItem(item1);
        location.addItem(item2);
        assertTrue(location.containsItem(item1.getName()));
        assertTrue(location.containsItem(item2.getName()));

        assertEquals(item1, location.removeItem(item1.getName()));
        assertNull(location.removeItem(item1.getName()));
        assertFalse(location.containsItem(item1.getName()));
        assertTrue(location.containsItem(item2.getName()));
    }    

}

/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package model;

/**
 * Class HerniPlan - třída představující mapu a stav adventury.
 * 
 * Tato třída inicializuje prvky ze kterých se hra skládá:
 * vytváří všechny lokace, propojuje je vzájemně pomocí východů 
 * a pamatuje si aktuální lokaci, ve které se hráč právě nachází.
 *
 * @author     Michael Kolling, Luboš Pavlíček, Jarmila Pavlíčková, Jan Říha
 * @version    LS 2017/2018
 */
public class GamePlan {
    private static final String FINAL_LOCATION = "pokoj";

    private Location currentLocation;

    /**
     * Konstruktor který vytváří jednotlivé lokace a propojuje je pomocí východů.
     */
    public GamePlan() {
        prepareWorldMap();
    }

    /**
     * Vytváří jednotlivé lokace a propojuje je pomocí východů.
     * Jako výchozí aktuální lokaci nastaví domeček.
     */
    private void prepareWorldMap() {
        // vytvářejí se předměty
        Item fletna = new Item("flétna", "flétna na uspání velkého medvěda", true);
        Item stul = new Item("stůl", "Velký dubový stůl", false);
        Item kniha = new Item("kniha", "Kniha tvého učitele lektvarů.", true);
        Item zidle = new Item("židle", "Židle na které je položená kniha.", false);
        Item policka = new Item("polička", "Polička na ktéré má učitel odložené lektvary.", false);
        Item lektvar = new Item("lektvar", "Lektvar, kterým omámíš učitele.", true);
        Item klice = new Item("klíče", "Klíče které sebereš klíčníkovi a kterými se dostaneš do pokoje", true);
        
        
        // vytvářejí se jednotlivé lokace
        Location hala = new Location("hala","vstupní hala ve které se nachází bájný medvěd");
        Location pokoj = new Location(FINAL_LOCATION, "pokoj, ve kterém bydlíš");
        Location trida = new Location("třída","třída kde s vyučují lektvary");
        Location kabinet = new Location("kabinet","kabinet kde sídlí učitel lektvarů");
        Location pruchod = new Location("průchod","průchod do pokoje, kde hlídá dveřník");
        Location chodba = new Location("chodba","chodba, která spojuje třídu kabinet a průchod do pokoje");
        
        // přiřazují se průchody mezi lokacemi (sousedící lokace)
        hala.addExit(chodba, false, fletna);
        chodba.addExit(trida);
        chodba.addExit(kabinet);
        chodba.addExit(pruchod, false, lektvar);
        trida.addExit(chodba);
        kabinet.addExit(chodba);
        pruchod.addExit(chodba);
        pruchod.addExit(pokoj, false, klice);

        // hra začíná v hale
        currentLocation = hala;

        
        
        
        

        // vkládají se předměty do jednotlivých lokací
        hala.addItem(stul);
        hala.addItem(fletna);
        
        trida.addItem(zidle);
        trida.addItem(kniha);
        
        kabinet.addItem(policka);
        kabinet.addItem(lektvar);
        
        pruchod.addItem(klice);
    }

    /**
     * Metoda vrací odkaz na aktuální lokaci, ve které se hráč právě nachází.
     *
     * @return    aktuální lokace
     */
    public Location getCurrentLocation() {
        return currentLocation;
    }

    /**
     * Metoda nastaví aktuální lokaci, používá se nejčastěji při přechodu mezi lokacemi
     *
     * @param    location nová aktuální lokace
     */
    public void setCurrentLocation(Location location) {
       currentLocation = location;
    }
    
    public boolean isVictorious() {
        return currentLocation.getName().equals(FINAL_LOCATION);
    }

}

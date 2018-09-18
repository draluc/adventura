/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package model;

import java.util.*;

/**
 * Trida Lokace - popisuje jednotlivé lokace (místnosti) hry
 *
 * Tato třída je součástí jednoduché textové hry.
 *
 * "Lokace" reprezentuje jedno místo (místnost, prostor, ...) ve scénáři hry.
 * Lokace může mít sousední lokace připojené přes východy. Pro každý východ
 * si lokace ukládá odkaz na sousedící lokace.
 *
 * @author     Michael Kolling, Luboš Pavlíček, Jarmila Pavlíčková, Jan Říha
 * @version    LS 2017/2018
 */
public class Location {

    private String name;
    private String description;
    private Set<Vchod> exits;   // obsahuje sousední lokace
    private Map<String, Item> items;   // obsahuje předměty v lokaci
    
    
    /**
     * Vytvoření lokace se zadaným popisem, např. "kuchyň", "hala", "trávník
     * před domem"
     *
     * @param    name nazev lokace, jednoznačný identifikátor, jedno slovo nebo víceslovný název bez mezer
     * @param    description Popis lokace
     */
    public Location(String name, String description) {
        this.name = name;
        this.description = description;
        exits = new HashSet<>();
        items = new HashMap<>();
       
    }
    
    
    
    /**
     * Definuje východ z lokace (sousední/vedlejsi lokace). Vzhledem k tomu,
     * že je použit Set pro uložení východů, může být sousední lokace uvedena
     * pouze jednou (tj. nelze mít dvoje dveře do stejné sousední lokace).
     * Druhé zadání stejné lokace tiše přepíše předchozí zadání (neobjeví
     * se žádné chybové hlášení). Lze zadat též cestu ze do sebe sama.
     *
     * @param    location lokace, která sousedi s aktualní lokací.
     *
     */
    public void addExit(Location location) {
        exits.add(new Vchod(location, true, null));
    }

    
    /**
     * Definuje východ z lokace (sousední/vedlejsi lokace). Vzhledem k tomu,
     * že je použit Set pro uložení východů, může být sousední lokace uvedena
     * pouze jednou (tj. nelze mít dvoje dveře do stejné sousední lokace).
     * Druhé zadání stejné lokace tiše přepíše předchozí zadání (neobjeví
     * se žádné chybové hlášení). Lze zadat též cestu ze do sebe sama.
     *
     * @param    location lokace, která sousedi s aktualní lokací.
     *
     */
    public void addExit(Location location,boolean jeOtevren, Item item) {
        exits.add(new Vchod(location, jeOtevren, item ));
    }

    /**
     * Metoda equals pro porovnání dvou lokací. Překrývá se metoda equals ze
     * třídy Object. Dvě lokace jsou shodné, pokud mají stejný název. Tato
     * metoda je důležitá z hlediska správného fungování seznamu východů (Set).
     *
     * Bližší popis metody equals je u třídy Object.
     *
     * @param     o object, který se má porovnávat s aktuálním
     * @return    hodnotu true, pokud má zadaná lokace stejný název, jinak false
     */  
    @Override
    public boolean equals(Object o) {
        // porovnáváme zda se nejedná o dva odkazy na stejnou instanci
        if (this == o) {
            return true;
        }

        // porovnáváme jakého typu je parametr 
        if (!(o instanceof Location)) {
            return false;    // pokud parametr není typu Lokace, vrátíme false
        }

        // přetypujeme parametr na typ Lokace 
        Location location = (Location) o;

        //metoda equals třídy java.util.Objects porovná hodnoty obou názvů. 
        //Vrátí true pro stejné názvy a i v případě, že jsou oba názvy null,
        //jinak vrátí false.

        return (java.util.Objects.equals(this.name, location.name));
    }

    /**
     * metoda hashCode vraci ciselny identifikator instance, ktery se pouziva
     * pro optimalizaci ukladani v dynamickych datovych strukturach. Pri
     * prekryti metody equals je potreba prekryt i metodu hashCode. Podrobny
     * popis pravidel pro vytvareni metody hashCode je u metody hashCode ve
     * tride Object.
     */
    @Override
    public int hashCode() {
        int result = 3;
        int nameHash = java.util.Objects.hashCode(this.name);
        result = 37 * result + nameHash;
        return result;
    }
      

    /**
     * Vrací název lokace (byl zadán při vytváření lokace jako parametr
     * konstruktoru)
     *
     * @return    název lokace
     */
    public String getName() {
        return name;
    }

    /**
     * Vrací "dlouhý" popis lokace, který může vypadat následovně: Jsi v
     * mistnosti/lokaci vstupni hala budovy VSE na Jiznim meste. vychody:
     * chodba bufet ucebna
     *
     * @return    dlouhý popis lokace
     */
    public String getFullDescription() {
        return "Jsi v mistnosti/lokaci " + description + ".\n"
                + getItemNames() + "\n"
                + getExitNames();
    }

    public String getItemNames() {
        String returnText = "predmety:";

        for (String itemName : items.keySet()) {
            returnText += " " + itemName;
        }

        return returnText;
    }
    
    /**
     * Vrací textový řetězec, který popisuje sousední východy, například:
     * "vychody: hala ".
     *
     * @return    popis východů - názvů sousedních lokací
     */
    private String getExitNames() {
        String returnText = "vychody:";
        for (Vchod exitLocation : exits) {
            returnText += " " + exitLocation.getLocation().getName();
        }
        return returnText;
    }

    /**
     * Vrací lokaci, která sousedí s aktuální lokací a jejíž název je zadán
     * jako parametr. Pokud lokace s udaným jménem nesousedí s aktuální
     * lokací, vrací se hodnota null.
     *
     * @param     locationName Jméno sousední lokace (východu)
     * @return    lokace, která se nachází za příslušným východem, nebo hodnota null, pokud lokace zadaného jména není sousedem.
     */
    public Vchod getExitLocation(String locationName) {
        return exits.stream()
                .filter(exit -> exit.getLocation().getName().equals(locationName))
                .findAny().orElse(null);
    }

    /**
     * Vrací kolekci obsahující lokace, se kterými ta lokace sousedí.
     * Takto získaný seznam sousedních lokací nelze upravovat (přidávat,
     * odebírat východy) protože z hlediska správného návrhu je to plně
     * záležitostí třídy Lokace.
     *
     * @return    nemodifikovatelná kolekce lokací (východů), se kterými tato lokace sousedí.
     */
    public Collection<Vchod> getExitLocations() {
        return Collections.unmodifiableCollection(exits);
    }
    
    public void addItem(Item item) {
        items.put(item.getName(), item);
    }
    
    public Item removeItem(String name) {
        return items.remove(name);
    }
    
    public boolean containsItem(String name) {
        return items.containsKey(name);
    }
    
   
}

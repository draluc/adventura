/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package model;

/**
 * Toto je hlavní třída logiky aplikace. Třída vytváří instanci třídy
 * GamePlan, která inicializuje lokace hry a vytváří seznam platných
 * příkazů a instance tříd provádějící jednotlivé příkazy.
 *
 * Vypisuje uvítací a ukončovací text hry. Také vyhodnocuje jednotlivé
 * příkazy zadané uživatelem.
 *
 * @author     Michael Kolling, Luboš Pavlíček, Jarmila Pavlíčková, Jan Říha
 * @version    LS 2017/2018
 */

public class Game implements IGame
{
    private ListOfCommands listOfCommands;
    private GamePlan gamePlan;
    private boolean gameOver = false;
    private Hrac hrac;
    /**
     * Vytváří hru a inicializuje lokace (prostřednictvím třídy GamePlan)
     * a seznam platných příkazů.
     */
    public Game()
    {
        gamePlan = new GamePlan();
        hrac = new Hrac(gamePlan);
        listOfCommands = new ListOfCommands();
        listOfCommands.insertCommand(new CommandHelp(listOfCommands));
        listOfCommands.insertCommand(new CommandMove(gamePlan));
        listOfCommands.insertCommand(new CommandTerminate(this));

        listOfCommands.insertCommand(new CommandPick(gamePlan, hrac));
        
        listOfCommands.insertCommand(new CommandPouzij(gamePlan, hrac));
        listOfCommands.insertCommand(new CommandRozhledniSe(gamePlan));
        listOfCommands.insertCommand(new CommandVlozDoBatohu(gamePlan, hrac));
        listOfCommands.insertCommand(new CommandPoloz(gamePlan, hrac));
        listOfCommands.insertCommand(new CommandVyndejZBatohu(gamePlan, hrac));
    }

    /**
     * Vrátí úvodní zprávu pro hráče.
     *
     * @return úvodní zprávu pro hráče
     */
    @Override
    public String getProlog()
    {
        return "Vítejte!\n" +
               "Toto je příběh o škole kouzel, jsi kouzelník a snažíš se dostat do svého pokoje.\n" +
               "Napište 'napoveda', pokud si nevíte rady, jak hrát dál.\n" +
               "\n" +
               gamePlan.getCurrentLocation().getFullDescription();
    }
    
    /**
     * Vrátí závěrečnou zprávu pro hráče.
     *
     * @return závěrečnou zprávu pro hráče
     */
    @Override
    public String getEpilog()
    {
        return "Dík, že jste si zahráli.  Ahoj.";
    }

    /**
     * Vrací true, pokud hra skončila.
     *
     * @return true, pokud hra již skončila; jinak false
     */
    @Override
    public boolean isGameOver()
    {
        return gameOver;
    }

    /**
     * Metoda zpracuje řetězec uvedený jako parametr, rozdělí ho na slovo
     * příkazu a další parametry. Pak otestuje zda příkaz je klíčovým slovem,
     * např. jdi. Pokud ano spustí samotné provádění příkazu.
     *
     * @param line  text, který zadal uživatel jako příkaz do hry
     * @return řetězec, který se má vypsat na obrazovku
     */
    @Override
    public String processCommand(String line)
    {
        String[] words = line.split("[ \t]+");
        String cmdWord = words[0];
        String[] parameters = new String[words.length - 1];

        for (int i = 0; i < parameters.length; i++) {
            parameters[i]= words[i+1];
        }

        String result = null;
        if (listOfCommands.checkCommand(cmdWord)) {
            ICommand command = listOfCommands.getCommand(cmdWord);
            result = command.process(parameters);
        } else {
            result = "Nevím, co tím myslíš. Tento příkaz neznám.";
        }

        if (gamePlan.isVictorious()) {
            gameOver = true;
        }

        return result;
    }

    /**
     * Nastaví, že nastal konec hry, metodu využívá třída CommandTerminate,
     * mohou ji použít i další implementace rozhraní ICommand.
     *
     * @param gameOver příznak, zda hra již skončila
     */
    void setGameOver(boolean gameOver)
    {
        this.gameOver = gameOver;
    }

    /**
     * Metoda vrátí odkaz na herní plán, je využita hlavně v testech,
     * kde se jejím prostřednictvím získává aktualní lokace hry.
     *
     * @return herní plán
     */
    @Override
    public GamePlan getGamePlan()
    {
        return gamePlan;
    }
    
    
}

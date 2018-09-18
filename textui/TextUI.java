/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */

package textui;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import model.IGame;

/**
 * Toto je třída uživatelského rozhraní aplikace Adventura. Třída vytváří
 * instanci třídy Game, která představuje logiku aplikace. Čte vstup zadaný
 * uživatelem a předává tento řetězec logice a vypisuje odpověď logiky na
 * konzoli.
 *
 * @author     Michael Kolling, Luboš Pavlíček, Jarmila Pavlíčková, Jan Říha
 * @version    LS 2017/2018
 */
public class TextUI
{
    private IGame game;

    /**
     * Vytváří nové textové rozhraní pro hru.
     *
     * @param game hra
     */
    public TextUI(IGame game)
    {
        this.game = game;
    }

    /**
     * Hlavní metoda hry. Vypíše úvodní text a pak opakuje čtení a zpracování
     * příkazu od hráče do konce hry <i>(dokud metoda isGameOver() z logiky
     * nevrátí hodnotu true)</i>. Nakonec vypíše text epilogu.
     */
    public void play()
    {
        System.out.println(game.getProlog() + "\n");

        // Základní cyklus programu - opakovaně se čtou příkazy a poté
        // se provádějí do konce hry.

        while (!game.isGameOver()) {
            String line = readLine();
            System.out.println(game.processCommand(line) + "\n");
        }

        System.out.println(game.getEpilog());
    }

    /**
     * Hlavní metoda hry. Vypíše úvodní text a pak načítá a zpracovává příkazy
     * ze souboru <i>(z připraveného scénáře)</i>, dokud není zpracován celý
     * soubor nebo dokud hra neskončí <i>(dokud metoda isGameOver() z logiky
     * nevrátí hodnotu true)</i>. Nakonec vypíše text epilogu.
     */
    public void play(String fileName) {
        System.out.println("Přehrávám příkazy ze souboru: " + fileName + "\n");

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            System.out.println(game.getProlog() + "\n");

            String line = reader.readLine();
            while(line != null && !game.isGameOver()) {
                System.out.println("* " + line + " *");
                System.out.println(game.processCommand(line) + "\n");
                line = reader.readLine();
            }

            System.out.println(game.getEpilog());
        } catch (FileNotFoundException e) {
            System.out.println("Soubor " + fileName + " nelze přečíst.");
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println("Došlo k chybě při čtení souboru " + fileName);
            System.out.println(e.getMessage());
        }
    }

    /**
     * Metoda přečte příkaz z příkazového řádku.
     *
     * @return vrací přečtený příkaz jako instanci třídy String
     */
    private String readLine()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.print("> ");
        return scanner.nextLine();
    }

}

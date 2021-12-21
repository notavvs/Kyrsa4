package com.company;

public class Player 
{
    private int playerID;
    private String name;
    private int winCount = 0;
    private int tieCount = 0;
    private int lossCount = 0;
    Hand hand;



    public Player(int playerID, String name, Hand hand)
    {
        this.playerID = playerID;
        this.name = name;
        this.hand = hand;
    }

    public void addWin()
    {
        winCount++;
    }

    public void addTie()
    {
        tieCount++;
    }

    public void addLoss()
    {
        lossCount++;
    }

    public void printResult()
    {
        System.out.println ("Игрок " + (playerID) + ": " + name + ": " + winCount + " победил(а), " + tieCount + " ничья и " + lossCount + " проиграл(а)");
        System.out.println("________________________________________________");
    }
    public String toString()
    {
        return "Игрок " + (playerID) + ": " + name;
    }
}

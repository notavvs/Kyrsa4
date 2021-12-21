package com.company;
import java.util.*;

public class Hand
{
    ArrayList<Card> hand = new ArrayList<>();
    private int total;

    public Hand ()
    {

    }

    public void addCard (Card card)
    {
        hand.add(card);
    }

    public void clearHand()
    {
        hand.clear();
    }

    public void currentHand ()
    {
        System.out.println("У вас: ");
        for (Card card : hand)
        {
            System.out.println(card.toString());
        }
        System.out.println("Ваша рука(" + currentTotal() +")");
    }

    public int currentTotal ()
    {
        total = 0;
        for (Card card : hand)
        {
            total += card.value();
        }
        return total;
    }

    public boolean checkBlackjack()
    {
        if (currentTotal() == 21)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean checkBust()
    {
        if (currentTotal()  > 21)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}

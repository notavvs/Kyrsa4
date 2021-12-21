package com.company;
import java.util.ArrayList;
import java.util.Collections;


public class Dealer 
{
    ArrayList<Card> deck = new ArrayList<>();
    Hand hand;
    private static Dealer dealer;

    private Dealer()
    {

    }

    public static Dealer getInstance()
    {
        if (dealer == null)
        {
            dealer = new Dealer();
        }
        return dealer;
    }

    public void setHand(Hand hand)
    {
        this.hand = hand;
    }

    public void createDeck()
    {
        for (Card.Suit suit : Card.Suit.values())
        {
            for (Card.Name name : Card.Name.values())
            {
                Card card = new Card(name, suit);
                deck.add(card);
            }
        }
        Collections.shuffle(deck);
    }

    public Card dealCard()
    {
        return deck.remove(0);
    }

    public void dealTable(ArrayList<Player> players)
    {
        for (Player player : players)
        {
            player.hand.clearHand();
            player.hand.addCard(dealCard());
            player.hand.addCard(dealCard());
        }
        dealer.hand.clearHand();
        dealer.hand.addCard(dealCard());
        dealer.hand.addCard(dealCard());
    }

    public void firstCard ()
    {
        System.out.println("Первая карта диллера " + hand.hand.get(0).toString());
    }

    public void fullHand()
    {
        System.out.println("У диллера: ");
        for (Card card :  hand.hand)
        {
            System.out.println(card.toString());
        }
        System.out.println("У диллера: " + hand.currentTotal());
    }

    public boolean checkDealerDraw()
    {
        hand.currentTotal();
        if (hand.currentTotal() < 17)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
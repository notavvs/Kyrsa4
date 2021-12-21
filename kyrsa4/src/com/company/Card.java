package com.company;
public class Card
{
    enum Suit
    {
      Черви, Буби, Крести, Пики
    };

    enum Name 
    {
     Туз (11), Два (2), Три (3), Четыре (4), Пять (5), Шесть (6), Семь (7), Восемь (8), Девять (9), Десять (10), Валет (10), Дама (10), Король (10);

      public final int worth;

        private Name (int worth)
        {
            this.worth = worth;
        }
    };

    private Name name;
    private Suit  suit;

    
    public Card(Name name, Suit suit)
    {
        this.name   = name;
        this.suit   = suit;
    }

    public int value ()
    {
        return name.worth;
    }

    public String toString()
    {
      return name +" "+ suit + " = " + name.worth;
    }
}
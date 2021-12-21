package com.company;
import java.util.*;

public class PlayBlackjack 
{
  static Scanner input = new Scanner(System.in);
  static ArrayList<Player> players = new ArrayList<>();
  static Dealer dealer = Dealer.getInstance();
  static Hand dealerHand = new Hand();
  private static int numOfPlayers;
  private static int numOfRounds;

  public static void main (String [] args)
  {
    setupGame();
    dealer.setHand(dealerHand);
    for (int i = 0; i < numOfRounds; i++)
    {
      playRound();

      System.out.println("Конец раунда " + (i + 1));
      System.out.println("________________________________________________");
      for (Player player : players)
      {
        player.printResult();
      }
    }
  }

  public static void playRound ()
  {
    dealer.createDeck();
    dealer.dealTable(players);
    dealer.firstCard();
    playerTurns();
    dealerTurn();
    checkWin();
  }

  public static void setupGame()
  {
    System.out.println("Бонжур!, сколько игроков?");
    do
    {
      numOfPlayers = input.nextInt();
    }
    while (numOfPlayers == 0);
    System.out.println("Сколько раундов хотите сыграть?");
    do
    {
      numOfRounds = input.nextInt();
    }
    while (numOfRounds == 0);

    for (int i = 0;  i < numOfPlayers; i++)
    {
      int playerID = i + 1;
      System.out.println("Игрок " + playerID + ", Ваше имя?");
      String name = input.next();
      Hand playerHand = new Hand();
      players.add(new Player(playerID, name, playerHand));
    }
  }

  public static void checkWin()
  {
    for (Player player : players)
    {
      int playerTotal = player.hand.currentTotal();
      int dealerTotal = dealer.hand.currentTotal();

      if (playerTotal < 22 && dealerTotal < 22 && playerTotal > dealerTotal)
      {
        System.out.println(player.toString() + ", Изи вин!");
        player.addWin();
      }
      else if (playerTotal < 22 && dealerTotal > 21)
      {
        System.out.println(player.toString() + ", Изи вин!");
        player.addWin();
      }
      else if (playerTotal < 22 && dealerTotal < 22 && playerTotal == dealerTotal)
      {
        System.out.println(player.toString() + ", Ничья");
        player.addTie();
      }
      else
      {
        System.out.println(player.toString() + ", Не повезло");
        player.addLoss();
      }
    }
  }

  public static void playerTurns()
  {
    for (Player player : players)
    {
      System.out.println(player.toString());
      player.hand.currentHand();
      while (player.hand.checkBlackjack() == false && player.hand.checkBust() == false)
      {
        System.out.println("Добавить или хватит?");
        System.out.println("1 - добавить || 2 - хватит.");
        int hitOrStand = input.nextInt();
        if (hitOrStand == 1)
        {
          player.hand.addCard(dealer.dealCard());
          player.hand.currentHand();
        }
        else
        {
          break;
        }
      }
      if (player.hand.checkBlackjack() == true)
      {
        System.out.println("Блек Джек! Больше добавлять нельзя");
      }
      if (player.hand.checkBust() == true)
      {
        System.out.println("Перебор!");
      }
    }
  }

  public static void dealerTurn()
  {
    dealer.fullHand();
    while (dealer.hand.checkBlackjack() == false && dealer.hand.checkBust() == false)
    {
      if (dealer.checkDealerDraw() == true)
      {
        System.out.println("Диллер добирает");
        dealer.hand.addCard(dealer.dealCard());
        dealer.fullHand();
      }
      else
      {
        System.out.println("Диллеру больше не надо");
        System.out.println("У диллера " + dealer.hand.currentTotal());
        break;
      }
    }
    if (dealer.hand.checkBlackjack() == true)
    {
      System.out.println("Блек Джек у диллера!");
    }
    if (dealer.hand.checkBust() == true)
    {
      System.out.println("Перебор у диллера!");
    }
  }
}

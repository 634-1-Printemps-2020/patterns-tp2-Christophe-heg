package materials;

import java.util.Random;

public class Coin {

  private CoinState coinState;
  private static Coin coin = new Coin();

  /* Le constructeur est private pour empêcher les clients de créer leurs propres instances */
  private Coin() {
  }

  /* Création éventuelle de l'unique instance et retour de cette unique instance */
  public static Coin getInstance() {
    if (coin == null) {coin = new Coin();}
    return coin;
  }

  /**
   * Change l'état de la pièce.
   * 50% de probabilité d'obtenir HEADS, 50% de probabilité d'obtenir TAILS
   */
  public void throwCoin() {

    Random r = new Random();

    int num = r.nextInt(2);
    if (num == 1){
      coinState = CoinState.HEADS;
    }
    else {
      coinState = CoinState.TAILS;
    }
  }

  public CoinState getState() {
    return coinState;
  }
}

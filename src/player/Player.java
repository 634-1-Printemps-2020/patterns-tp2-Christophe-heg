package player;

import materials.Coin;

public class Player {

  private int id;

  public Player(int id) {
    this.id = id;
  }

  public int getId(){
    return this.id;
  }

  public void play(Coin coin) {
    coin.throwCoin();
  }
  public String toString(){
    return "Player n° " + getId();
  }
}

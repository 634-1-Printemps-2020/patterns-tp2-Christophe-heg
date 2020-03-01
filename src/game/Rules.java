package game;

import materials.Coin;
import materials.CoinState;

import java.util.List;

public class Rules {

  private static Rules rules = new Rules();

  public static Rules getInstance() {
    if (rules == null) {rules = new Rules();}
    return rules;
  }

  /**
   * Cette méthode permet de déterminer si une suite d'états de pièce permet de gagner à une partie
   * @param states liste d'états pour un joueur
   * @return true si un joueur a gagné, false sinon
   */
  public boolean checkWin(List<CoinState> states) {
    int i = 0;
    int j = 1;
    int k = 2;

    for (CoinState c : states){
      if (states.get(i).equals(CoinState.TAILS) && states.get(j).equals(CoinState.TAILS) && states.get(k).equals(CoinState.TAILS)){
        return true;
      }
      if(k != states.size()-1){
        i++;
        j++;
        k++;
      }
    }
    return false;
  }
}

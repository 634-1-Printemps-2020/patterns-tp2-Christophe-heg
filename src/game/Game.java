package game;

import materials.Coin;
import materials.CoinState;
import player.Player;
import utils.Statistics;

import java.util.*;

public class Game {

    private Rules rules;
    private Coin coin;
    private Map<Player, List<CoinState>> history;

    public Game() {
        history = new HashMap<>();
        coin = Coin.getInstance();
        rules = Rules.getInstance();
    }

    /**
     * Ajouter un nouveau joueur au jeu
     *
     * @param player le nouveau joueur
     */
    public void addPlayer(Player player) {
        List<CoinState> lst = new ArrayList<>();
        history.put(player, lst);
    }

    /**
     * Faire joueur tous les joueurs et stocker chaque partie dans history
     */
    public void play() {
        for (Player p : history.keySet()){
            jouer(p);
        }
    }

    private void jouer(Player p){
        // 3 tirages minimum pour gagner
        for (int i = 1; i <= 3; i++){
            p.play(coin);
            getSpecificHistory(p).add(coin.getState());
        }

        while (!rules.checkWin(getSpecificHistory(p))) {
            p.play(coin);
            getSpecificHistory(p).add(coin.getState());
        }
    }

    /**
     * Calculer des statistiques de la partie précédente
     *
     * @return Statistics
     */
    public Statistics getStatistics() {
      return getStat();
    }

    private Statistics getStat(){

        float total = 0;
        int minCoup = 0;
        int maxCoup = 0;

        for (List<CoinState> lst :  history.values()){
            if (minCoup == 0 || lst.size()-1 < minCoup){
                minCoup = lst.size() -1;
            }
            if (lst.size()-1 > maxCoup){
                maxCoup = lst.size() -1;
            }
            total += lst.size()-1;

        }
        return new Statistics(total / history.size()-1, minCoup, maxCoup, (int)total);
    }

    /**
     * Obtenir l'historique de tous les joueurs de la partie précédente
     *
     * @return Map contenant chaque joueur et la liste des ses lancers
     */
    public Map<Player, List<CoinState>> getHistory() {
     return history;
    }
    
    /**
     * Obtenir l'historique d'un joueur spécifique
     *
     * @param player instance du joueur pour lequel on veut avoir l'historique
     * @return la liste des lancers d'un joueur
     */
    public List<CoinState> getSpecificHistory(Player player)
    {
        if (history.get(player) == null){
            for (Player p : history.keySet()) {
                if(p.getId() == player.getId()){
                    return history.get(p);
                }
            }
            return null;
        }
        return history.get(player);
    }
}

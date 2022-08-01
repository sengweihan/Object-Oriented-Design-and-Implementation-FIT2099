package game.systems;

import game.actors.Player;

public abstract class WalletSystem {
    private static Player player;

    /**
     * This method is used to set which player will have this wallet system.
     * @param player
     */
    public static void setPlayer(Player player){
        WalletSystem.player = player;
    }

    /**
     * This method is used to add the value into player's wallet when the player collected those coins.
     * @param value
     */
    public static void addWalletValue(int value){
        player.walletValue += value;

    }

    /**
     * This method is used to deduct player's wallet value when player is buying stuff from Toad.
     * @param value
     */
    public static void subtractWalletValue(int value){
        player.walletValue -= value;
    }

    /**
     * This method is used to retrieve the current player's wallet value
     * @return
     */
    public static int getWalletValue(){
        return player.walletValue;
    }
}

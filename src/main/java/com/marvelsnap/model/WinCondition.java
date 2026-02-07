package com.marvelsnap.model;

import java.util.List;

/**
 * Utility class to determine the winner.
 */
public class WinCondition {
    /**
     * Static method to determine winner. It calculates the power on each location to count the locations won of each player.
     * The player who won at least two locations, wins the game.
     * If there's a tie, it calculates total power on all locations and checks it.
     * @param locations the list of locations.
     * @param p1 player 1.
     * @param p2 player 2.
     * @return the winner or null if we have absolute tie.
     */
    public static Player determineWinner(final List<Location> locations, final Player p1, final Player p2) {
        int p1LocationsWon = 0;
        int p2LocationsWon = 0;
        int p1TotalPower = 0;
        int p2TotalPower = 0;

        /*Calculate power for each location */
        for(final Location loc : locations) {
            final int p1Power = loc.calculatePower(0);
            final int p2Power = loc.calculatePower(1);

            p1TotalPower += p1Power;
            p2TotalPower += p2Power;

            if(p1Power > p2Power) {
                p1LocationsWon++;
            } else if(p2Power > p1Power) {
                p2LocationsWon++;
            }
        }

        /*Calculate win on locations */
        if(p1LocationsWon > p2LocationsWon){
            return p1;
        } else if(p2LocationsWon > p1LocationsWon) {
            return p2;
        }

        /*Tie breaker */
        if(p1TotalPower > p2TotalPower) {
            return p1;
        } else if (p2TotalPower > p1TotalPower) {
            return p2;
        }

        /*Absolute tie */
        return null;
     }
}

package com.marvelsnap.model;

import java.util.List;

public class WinCondition {
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

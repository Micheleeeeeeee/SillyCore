package me.sillysock.SillyCore.API.Util;

import org.bukkit.Statistic;
import org.bukkit.entity.Player;

public class SillyPlayer implements ISillyPlayer {

    private final Player p;

    public SillyPlayer(final Player p) {
        this.p = p;
    }


    @Override
    public int getPlayTime() {
        return p.getStatistic(Statistic.PLAY_ONE_MINUTE);
    }

    @Override
    public void ban() {

    }

    @Override
    public void blacklist() {

    }

    @Override
    public void kick() {

    }

    @Override
    public void mute() {

    }

    @Override
    public void warn() {

    }

    public Player getP() {
        return p;
    }
}

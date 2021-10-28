package me.sillysock.SillyCore.API.Util;

import org.bukkit.entity.Player;

public interface ISillyPlayer {

    int getPlayTime();

    void ban();
    void blacklist();
    void kick();
    void mute();
    void warn();
}

package com.exorath.simpleffa;

import com.exorath.game.api.Game;
import com.exorath.game.api.GameProvider;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by TOON on 8/23/2015.
 */
public class SimpleFFA extends GameProvider {
    @Override
    public Game create() {
        return new FFAGame();
    }

}

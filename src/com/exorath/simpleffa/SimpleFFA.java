package com.exorath.simpleffa;

import org.bukkit.plugin.java.JavaPlugin;

import com.exorath.game.api.Game;
import com.exorath.game.api.GameProvider;

/**
 * Created by TOON on 8/23/2015.
 */
public class SimpleFFA extends JavaPlugin implements GameProvider {

    @Override
    public Game create() {
        Game game = new FFAGame();
        return game;
    }

}

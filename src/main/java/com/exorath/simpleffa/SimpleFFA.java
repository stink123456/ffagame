package com.exorath.simpleffa;

import org.bukkit.plugin.java.JavaPlugin;

import com.exorath.game.api.Game;
import com.exorath.game.api.GameProvider;

/**
 * Created by TOON on 8/23/2015.
 */
public class SimpleFFA extends JavaPlugin implements GameProvider {
    private static SimpleFFA instance;
    @Override
    public void onEnable() {
        instance = this;
    }

    @Override
    public Game create() {
        Game game = new FFAGame();
        return game;
    }
    public static SimpleFFA getInstance(){
        return instance;
    }

}

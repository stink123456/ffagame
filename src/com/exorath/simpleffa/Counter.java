package com.exorath.simpleffa;

import com.exorath.game.api.hud.HUDPriority;
import com.exorath.game.api.hud.HUDText;
import com.exorath.game.api.hud.locations.scoreboard.ScoreboardText;
import com.exorath.game.api.team.Team;
import org.bukkit.ChatColor;
import org.bukkit.scheduler.BukkitRunnable;

import java.text.DecimalFormat;

/**
 * Created by TOON on 10/2/2015.
 */
public class Counter extends BukkitRunnable{
    private static final String PREFIX = ChatColor.GREEN + "Current time: " + ChatColor.WHITE;
    private static final DecimalFormat FORMAT = new DecimalFormat("#.0");
    private static final int INTERVAL = 4;

    private Team team;
    private int ticks = 0;
    public Counter(Team team){
        this.team = team;
        start();
    }
    private void start(){
        team.getActivePlayers().forEach(p -> p.getHud().getScoreboard().addText("ffa_counter", new ScoreboardText(PREFIX + ticks,HUDPriority.MEDIUM)));
        runTaskTimer(SimpleFFA.getInstance(), 0, INTERVAL);
    }
    public void stop(){
        cancel();
        team.getActivePlayers().forEach(p -> p.getHud().getScoreboard().removeText("ffa_counter"));
    }
    @Override
    public void run(){
        ticks+= INTERVAL;

        team.getActivePlayers().forEach(p -> p.getHud().getScoreboard().getText("ffa_counter").setText(PREFIX + FORMAT.format(ticks/20f)));
    }
}

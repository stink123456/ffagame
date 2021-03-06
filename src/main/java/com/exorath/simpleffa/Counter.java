package com.exorath.simpleffa;

import java.text.DecimalFormat;

import com.exorath.game.api.hud.location.Scoreboard;
import org.bukkit.ChatColor;
import org.bukkit.scheduler.BukkitRunnable;

import com.exorath.game.api.hud.HUDPriority;
import com.exorath.game.api.player.GamePlayer;
import com.exorath.game.api.team.Team;

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
        team.getActivePlayers().forEach(p -> p.getHud().getScoreboard().addText("ffa_counter", new Scoreboard.ScoreboardText(PREFIX + ticks,HUDPriority.MEDIUM.get())));
        runTaskTimer(SimpleFFA.getInstance(), 0, INTERVAL);
    }
    public void stop(){
        cancel();
        team.getActivePlayers().forEach(p -> p.getHud().getScoreboard().removeText("ffa_counter"));
    }
    @Override
    public void run(){
        ticks+= INTERVAL;

        for(GamePlayer p : team.getActivePlayers())
            if(p.getHud().getScoreboard().containsText("ffa_counter"))
                p.getHud().getScoreboard().getText("ffa_counter").setText(PREFIX + FORMAT.format(ticks / 20f));
            else
                p.getHud().getScoreboard().addText("ffa_counter", new Scoreboard.ScoreboardText(PREFIX + ticks,HUDPriority.MEDIUM.get()));
    }
}

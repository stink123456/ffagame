package com.exorath.simpleffa;

import com.exorath.game.GameAPI;
import com.exorath.game.api.hud.effects.RainbowEffect;
import com.exorath.game.api.maps.GameMap;
import com.exorath.game.api.team.Team;
import com.exorath.game.api.type.minigame.MinigameStateManager;
import com.exorath.game.api.type.minigame.maps.MinigameMapManager;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;

import com.exorath.game.api.Game;
import com.exorath.game.api.GameListener;
import com.exorath.game.api.GameState;
import com.exorath.game.api.StopCause;
import com.exorath.game.api.events.GameStateChangedEvent;
import com.exorath.game.api.type.minigame.Minigame;
import com.exorath.game.api.hud.HUDManager;
import com.exorath.game.api.hud.HUDPriority;
import com.exorath.game.api.hud.HUDText;
import com.exorath.game.api.player.GamePlayer;
import com.exorath.game.api.team.TeamManager;
import org.bukkit.event.player.PlayerMoveEvent;

/**
 * Created by TOON on 8/23/2015.
 */
public class EventListener implements GameListener {

    private Minigame game;
    private Counter counter;
    public EventListener(Minigame game) {
        this.game = game;
    }

    @Override
    public void onGameStateChange(GameStateChangedEvent e) {
        if (e.getNewState() == GameState.INGAME) {
            HUDText text = new HUDText("Get to the end to achieve victory!", HUDPriority.MEDIUM);
            text.setEffect(new RainbowEffect(2, ChatColor.BOLD));
            game.getManager(HUDManager.class).getPublicHUD().addActionBar("ffa_rules", text, true);
            counter = new Counter(game.getManager(TeamManager.class).getTeam());

        } else if (e.getNewState() == GameState.FINISHING) {
            counter.stop();
        }
    }
    @Override
    public void onMove(PlayerMoveEvent e, Game game, GamePlayer player) {
        if(game.getState() != GameState.INGAME)
            return;
        GameMap map = game.getManager(MinigameMapManager.class).getCurrent();
        checkIfLava(player, map, e.getTo());
        checkIfFinish(player, map, e.getTo());
    }
    private void checkIfLava(GamePlayer player,GameMap map, Location loc){
        if(loc.getBlock().getType() != Material.STATIONARY_LAVA && loc.getBlock().getType() != Material.LAVA)
            return;
        Team team = game.getManager(TeamManager.class).getTeam(player);
        if(team == null)
            return;
        player.getBukkitPlayer().teleport(map.getSpawns(team.getName()).getSpawn(0).getLocation());
    }
    private void checkIfFinish(GamePlayer player, GameMap map, Location loc){
        int end1x = (int) map.getCustomData().getOrDefault("end1.x", 0);
        int end1z = (int) map.getCustomData().getOrDefault("end1.z", 0);
        int end2x = (int) map.getCustomData().getOrDefault("end2.x", 0);
        int end2z = (int) map.getCustomData().getOrDefault("end2.z", 0);
        if(loc.getX() < end1x)
            return;
        if(loc.getX() > end2x)
            return;
        if(loc.getZ() < end1z)
            return;
        if(loc.getZ() > end2z)
            return;
        GameAPI.printConsole("VICTORY!");
        game.getManager(MinigameStateManager.class).stop(StopCause.VICTORY);
    }
}

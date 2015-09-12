package com.exorath.simpleffa;

import com.exorath.game.GameAPI;
import com.exorath.game.api.Game;
import com.exorath.game.api.GameListener;
import com.exorath.game.api.GameState;
import com.exorath.game.api.StopCause;
import com.exorath.game.api.events.GamePlayerKillPlayerEvent;
import com.exorath.game.api.events.GameStateChangedEvent;
import com.exorath.game.api.gametype.minigame.Minigame;
import com.exorath.game.api.gametype.minigame.RepeatingMinigame;
import com.exorath.game.api.hud.HUDManager;
import com.exorath.game.api.hud.HUDPriority;
import com.exorath.game.api.hud.HUDText;
import com.exorath.game.api.player.GamePlayer;
import com.exorath.game.api.team.TeamManager;
import org.bukkit.Material;
import org.bukkit.event.block.BlockBreakEvent;

/**
 * Created by TOON on 8/23/2015.
 */
public class EventListener implements GameListener {
    private Minigame game;
    public EventListener(Minigame game){
        this.game = game;
    }
    @Override
    public void onBlockBreak(BlockBreakEvent event, Game game, GamePlayer player) {
        if(!this.game.equals(game))
            return;
        if(game.getState() != GameState.INGAME)
            return;
        if(event.getBlock().getType() == Material.DIAMOND_BLOCK)
            this.game.getStateManager().stop(StopCause.VICTORY);
    }

    //Finishes the game when there is a winner
    @Override
    public void onPlayerKillPlayer(GamePlayerKillPlayerEvent event) {
        if ( event.getGame().getManager( TeamManager.class ).getTeam().getActivePlayers().size() <= 1 )
            if(game.getState() == GameState.INGAME)
                game.getStateManager().stop(StopCause.VICTORY);
    }

    @Override
    public void onGameStateChange(GameStateChangedEvent e) {
        if(!e.getGame().equals(this.game))
            return;
        if(e.getNewState() == GameState.INGAME)
            game.getManager(HUDManager.class).getPublicHUD().addActionBar("ffa_rules",new HUDText("Destroy the diamond block to seize victory!", HUDPriority.MEDIUM));
        else if(e.getNewState() == GameState.FINISHING)
            game.getManager(HUDManager.class).getPublicHUD().removeActionBar("ffa_rules");
    }
}

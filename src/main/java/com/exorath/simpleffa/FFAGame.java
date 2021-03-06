package com.exorath.simpleffa;

import com.exorath.game.api.GameProperty;
import com.exorath.game.api.map.MapManager;
import com.exorath.game.api.player.PlayerManager;
import com.exorath.game.api.team.FreeForAllTeam;
import com.exorath.game.api.team.Team;
import com.exorath.game.api.team.TeamManager;
import com.exorath.game.api.team.TeamProperty;
import com.exorath.game.api.type.minigame.Minigame;
import com.exorath.game.api.type.minigame.map.MapSelection;
import com.exorath.game.api.type.minigame.map.MinigameMapManager;

/**
 * Created by TOON on 8/23/2015.
 * Random test to see what should be implemented of core
 */
public class FFAGame extends Minigame {

    public FFAGame() {
        super(g -> g.getManager(PlayerManager.class).getIngamePlayerCount() > 1);
        setName("Exorath DeathMatch");
        setDescription("Kill all other players in this free for all game to win.");
        getProperties().set(Minigame.MIN_PLAYERS, 1);
        getProperties().set(Minigame.MAX_DURATION, 20 * 300);//5 minutes
        getProperties().set(Minigame.ALLOW_SPECTATING, true);//TODO: Check if implemented
        getProperties().set(GameProperty.DROP_ITEMS, true);
        getProperties().set(GameProperty.HUNGER, false);
        setupTeams();
        setupMaps();

        addListener(new EventListener(this));//Implemented!
    }

    public void setupTeams() {
        Team team = new FreeForAllTeam();
        team.setName("Players");
        team.setMinTeamSize(1);//TODO: Check if implemented
        team.setMaxTeamSize(16);//TODO: Check if implemented
        team.getProperties().set(TeamProperty.DAMAGE_RECEIVE, false);
        getManager(TeamManager.class).addTeam(team);
    }

    public void setupMaps() {
        this.getManager(MinigameMapManager.class).setSelection(MapSelection.RANDOM);
        this.getManager(MapManager.class).addMap("mapName1");
        this.getManager(MapManager.class).addMap("mapName2");
        this.getManager(MapManager.class).addMap("mapName3");
    }

}

package com.exorath.simpleffa;

import com.exorath.game.api.GameProperty;
import com.exorath.game.api.action.DieAction;
import com.exorath.game.api.gametype.minigame.Minigame;
import com.exorath.game.api.gametype.minigame.maps.MapSelection;
import com.exorath.game.api.gametype.minigame.maps.MinigameMapManager;
import com.exorath.game.api.maps.MapManager;
import com.exorath.game.api.team.FreeForAllTeam;
import com.exorath.game.api.team.Team;
import com.exorath.game.api.team.TeamManager;

/**
 * Created by TOON on 8/23/2015.
 * Random test to see what should be implemented of core
 */
public class FFAGame extends Minigame {

    public FFAGame() {
        setName("Exorath DeathMatch");//Implemented!
        setDescription("Kill all other players in this free for all game to win.");//Implemented!
        getProperties().set(Minigame.MIN_PLAYERS, 1);
        getProperties().set(Minigame.MAX_DURATION, 20 * 180);//Implemented!
        getProperties().set(GameProperty.ALLOW_SPECTATING, true);//TODO: Check if implemented

        setupTeams();
        setupMaps();

        getActions().setDieAction(new DieAction.Spectate());

        addListener(new EventListener(this));//Implemented!
    }

    public void setupTeams() {
        Team team = new FreeForAllTeam();
        team.setName("Players");
        team.setMinTeamSize(1);//TODO: Check if implemented
        team.setMaxTeamSize(16);//TODO: Check if implemented
        getManager(TeamManager.class).addTeam(team);
    }

    /**
     * TODO: Confirm how Nick implemented maps
     */
    public void setupMaps() {
        this.getManager(MinigameMapManager.class).setSelection(MapSelection.VOTE);
        this.getManager(MapManager.class).addMap("mapName1");
        this.getManager(MapManager.class).addMap("mapName2");
        this.getManager(MapManager.class).addMap("mapName3");
    }

}

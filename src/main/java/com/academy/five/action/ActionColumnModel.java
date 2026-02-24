package com.academy.five.action;

import com.academy.four.dao.player.PlayerInfo;

public class ActionColumnModel {

    private final PlayerInfo playerInfo;

    public ActionColumnModel(PlayerInfo playerInfo) {
        this.playerInfo = playerInfo;
    }

    public PlayerInfo getPlayerInfo() {
        return playerInfo;
    }

    public Integer getId() {
        return playerInfo.getId();
    }

    public String getFullName() {
        return playerInfo.getFirstName() + " " + playerInfo.getLastName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ActionColumnModel that)) return false;

        return getId() != null && getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return getId() != null ? getId().hashCode() : 0;
    }
}

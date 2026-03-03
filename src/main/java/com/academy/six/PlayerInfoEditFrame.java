package com.academy.six;

import com.academy.four.dao.player.PlayerInfo;

import javax.swing.*;
import java.awt.*;
import java.util.function.Consumer;

public class PlayerInfoEditFrame extends JFrame {

    private final Consumer<PlayerInfo> onEditPlayerInfoFinishedConsumer;

    public PlayerInfoEditFrame(PlayerInfo playerInfo, Consumer<PlayerInfo> onEditPlayerInfoFinishedConsumer) {
        super("Edit Player Info");
        this.onEditPlayerInfoFinishedConsumer = onEditPlayerInfoFinishedConsumer;
        setMinimumSize(new Dimension(500, 300));
        setContentPane(new PlayerFormPanel(playerInfo, this::onEditPlayerInfoFinished));
    }

    private void onEditPlayerInfoFinished(PlayerInfo playerInfo) {
        onEditPlayerInfoFinishedConsumer.accept(playerInfo);//playerInfoTableModel::updatePlayerInfo
        dispose();//zatvaram prozor
    }
}

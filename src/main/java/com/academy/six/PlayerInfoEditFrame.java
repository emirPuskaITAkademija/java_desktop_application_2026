package com.academy.six;

import com.academy.four.dao.player.PlayerInfo;

import javax.swing.*;
import java.awt.*;
import java.util.function.Consumer;

public class PlayerInfoEditFrame extends JFrame {


    public PlayerInfoEditFrame(PlayerInfo playerInfo, Consumer<PlayerInfo> onEditPlayerInfoFinishedConsumer) {
        super("Edit Player Info");
        setMinimumSize(new Dimension(500, 300));
        setContentPane(new PlayerFormPanel(playerInfo, onEditPlayerInfoFinishedConsumer));
    }
}

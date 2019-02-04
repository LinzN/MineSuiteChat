/*
 * Copyright (C) 2018. MineGaming - All Rights Reserved
 * You may use, distribute and modify this code under the
 * terms of the LGPLv3 license, which unfortunately won't be
 * written for another century.
 *
 *  You should have received a copy of the LGPLv3 license with
 *  this file. If not, please write to: niklas.linz@enigmar.de
 *
 */

package de.linzn.mineSuite.chat;


import de.linzn.mineSuite.chat.commands.*;
import de.linzn.mineSuite.chat.listener.ChatListener;
import de.linzn.mineSuite.chat.listener.VoteListener;
import de.linzn.mineSuite.chat.socket.JClientChatListener;
import de.linzn.mineSuite.core.MineSuiteCorePlugin;
import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.plugin.java.JavaPlugin;

public class ChatPlugin extends JavaPlugin {
    private static ChatPlugin inst;
    private boolean isVotifier = false;

    public static ChatPlugin inst() {
        return inst;
    }

    @Override
    public void onEnable() {
        inst = this;
        if (this.getServer().getPluginManager().isPluginEnabled("Votifier")) {
            this.isVotifier = true;
        }
        getServer().getPluginManager().registerEvents(new ChatListener(), this);
        if (this.isVotifier)
            getServer().getPluginManager().registerEvents(new VoteListener(), this);

        MineSuiteCorePlugin.getInstance().getMineJSocketClient().jClientConnection1.registerIncomingDataListener("mineSuiteChat", new JClientChatListener());
        loadCommands();
    }

    @Override
    public void onDisable() {
    }

    public Chat getChat() {
        return MineSuiteCorePlugin.getChat();
    }

    public Economy getEconomy() {
        return MineSuiteCorePlugin.getEconomy();
    }


    public void loadCommands() {
        getCommand("msg").setExecutor(new PrivateMSG(this));
        getCommand("r").setExecutor(new PrivateReply(this));
        getCommand("tc").setExecutor(new StaffChat(this));
        getCommand("g").setExecutor(new GlobalChat(this));
        getCommand("afk").setExecutor(new Afk(this));
        getCommand("spy").setExecutor(new SocialSpy(this));
        getCommand("h").setExecutor(new TradeChat(this));
        getCommand("bc").setExecutor(new BroadcastChat(this));
        getCommand("tbc").setExecutor(new BroadcastTitle(this));
        getCommand("mail").setExecutor(new MailChat());
        if (this.isVotifier)
            getCommand("testvote").setExecutor(new TestVote(this));
    }
}

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
import de.linzn.mineSuite.chat.utils.VaultAccess;
import de.linzn.mineSuite.core.MineSuiteCorePlugin;
import net.milkbowl.vault.chat.Chat;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class ChatPlugin extends JavaPlugin {
    private static ChatPlugin inst;
    private Chat chat = null;
    private VaultAccess vault;

    public static ChatPlugin inst() {
        return inst;
    }

    @Override
    public void onEnable() {
        inst = this;
        getServer().getPluginManager().registerEvents(new ChatListener(), this);
        getServer().getPluginManager().registerEvents(new VoteListener(), this);
        MineSuiteCorePlugin.getInstance().getMineJSocketClient().jClientConnection1.registerIncomingDataListener("mineSuiteChat", new JClientChatListener());
        setupChat();
        vault = new VaultAccess();
        loadCommands();
    }

    @Override
    public void onDisable() {
    }

    public Chat getChat() {
        return this.chat;
    }

    public VaultAccess getVaultData() {
        return vault;
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
        getCommand("testvote").setExecutor(new TestVote(this));
    }

    private void setupChat() {
        RegisteredServiceProvider<Chat> chatProvider = getServer().getServicesManager()
                .getRegistration(net.milkbowl.vault.chat.Chat.class);
        if (chatProvider != null) {
            chat = chatProvider.getProvider();
            this.getLogger().info("Using Vault");
        }
    }
}

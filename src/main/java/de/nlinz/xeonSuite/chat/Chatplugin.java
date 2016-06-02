package de.nlinz.xeonSuite.chat;

import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import de.nlinz.xeonSuite.chat.commands.Afk;
import de.nlinz.xeonSuite.chat.commands.GlobalChat;
import de.nlinz.xeonSuite.chat.commands.GuildChat;
import de.nlinz.xeonSuite.chat.commands.PrivateMSG;
import de.nlinz.xeonSuite.chat.commands.PrivateReply;
import de.nlinz.xeonSuite.chat.commands.SocialSpy;
import de.nlinz.xeonSuite.chat.commands.StaffChat;
import de.nlinz.xeonSuite.chat.listener.BukkitSockChatListener;
import de.nlinz.xeonSuite.chat.listener.ChatListener;
import net.milkbowl.vault.chat.Chat;

public class Chatplugin extends JavaPlugin {
	private static Chatplugin inst;
	private Chat chat = null;
	private VaultListen vault;

	public void onEnable() {
		inst = this;
		getServer().getPluginManager().registerEvents(new ChatListener(), this);
		getServer().getPluginManager().registerEvents(new BukkitSockChatListener(), this);
		setupChat();
		vault = new VaultListen();
		loadCommands();
	}

	public void onDisable() {
	}

	public Chat getChat() {
		return this.chat;
	}

	public static Chatplugin inst() {
		return inst;
	}

	public VaultListen getVaultData() {
		return vault;
	}

	public void loadCommands() {
		getCommand("gc").setExecutor(new GuildChat(this));
		getCommand("msg").setExecutor(new PrivateMSG(this));
		getCommand("r").setExecutor(new PrivateReply(this));
		getCommand("tc").setExecutor(new StaffChat(this));
		getCommand("g").setExecutor(new GlobalChat(this));
		getCommand("afk").setExecutor(new Afk(this));
		getCommand("spy").setExecutor(new SocialSpy(this));
	}

	private void setupChat() {
		RegisteredServiceProvider<Chat> chatProvider = getServer().getServicesManager()
				.getRegistration(net.milkbowl.vault.chat.Chat.class);
		if (chatProvider != null) {
			chat = chatProvider.getProvider();
			this.getLogger().info("Using Vault for Chatprovider!");
		}
	}
}

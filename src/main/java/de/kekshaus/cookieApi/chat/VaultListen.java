package de.kekshaus.cookieApi.chat;

import org.bukkit.entity.Player;

import net.milkbowl.vault.chat.Chat;

public class VaultListen {

	public String getPrefix(Player player) {
		Chat chat = Chatplugin.inst().getChat();
		String group = chat.getPrimaryGroup(player);

		if (chat.getPlayerPrefix(player) != null) {
			return chat.getPlayerPrefix(player);
		} else if (chat.getGroupPrefix(player.getWorld(), group) != null) {
			return chat.getGroupPrefix(player.getWorld(), group);
		}

		return "";
	}

	public String getSuffix(Player player) {
		Chat chat = Chatplugin.inst().getChat();
		String group = chat.getPrimaryGroup(player);

		if (chat.getPlayerSuffix(player) != null) {
			return chat.getPlayerSuffix(player);
		} else if (chat.getGroupSuffix(player.getWorld(), group) != null) {
			return chat.getGroupSuffix(player.getWorld(), group);
		}

		return "";
	}

	public String getGroup(Player player) {
		Chat chat = Chatplugin.inst().getChat();
		return chat.getPrimaryGroup(player);
	}

}

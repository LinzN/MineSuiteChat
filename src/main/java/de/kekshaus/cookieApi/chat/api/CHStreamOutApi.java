package de.kekshaus.cookieApi.chat.api;

import de.keks.socket.bukkit.BukkitPlugin;
import de.keks.socket.core.Channel;
import de.kekshaus.cookieApi.chat.database.ChatHASHDB;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class CHStreamOutApi {

	public static void guildChat(String guild, String sender, String text) {
		ByteArrayOutputStream b = new ByteArrayOutputStream();
		DataOutputStream out = Channel.chatChannel(b);
		try {
			out.writeUTF("GuildChat");
			out.writeUTF(guild);
			out.writeUTF(sender);
			out.writeUTF(text);
		} catch (IOException e) {
			e.printStackTrace();
		}

		BukkitPlugin.instance().sendBytesOut(b);
	}

	public static void channelChat(String sender, String rawtext, String prefix, String suffix, String channel,
			String guild) {
		ByteArrayOutputStream b = new ByteArrayOutputStream();
		DataOutputStream out = Channel.chatChannel(b);
		try {
			out.writeUTF("ChannelChat");
			out.writeUTF(sender);
			out.writeUTF(rawtext);
			out.writeUTF(prefix);
			out.writeUTF(suffix);
			out.writeUTF(channel);
			out.writeUTF(guild);
		} catch (IOException e) {
			e.printStackTrace();
		}

		BukkitPlugin.instance().sendBytesOut(b);
	}

	public static void privateMsg(String sender, String receiver, String text, String prefix) {
		ByteArrayOutputStream b = new ByteArrayOutputStream();
		DataOutputStream out = Channel.chatChannel(b);
		try {
			out.writeUTF("PrivateMsg");
			out.writeUTF(sender);
			out.writeUTF(receiver);
			out.writeUTF(text);
			out.writeUTF(prefix);
		} catch (IOException e) {
			e.printStackTrace();
		}

		BukkitPlugin.instance().sendBytesOut(b);
	}

	public static void privateReply(String sender, String text, String prefix) {
		ByteArrayOutputStream b = new ByteArrayOutputStream();
		DataOutputStream out = Channel.chatChannel(b);
		try {
			out.writeUTF("PrivateReply");
			out.writeUTF(sender);
			out.writeUTF(text);
			out.writeUTF(prefix);
		} catch (IOException e) {
			e.printStackTrace();
		}

		BukkitPlugin.instance().sendBytesOut(b);
	}

	public static void channelSwitch(String sender, String channel) {
		ByteArrayOutputStream b = new ByteArrayOutputStream();
		DataOutputStream out = Channel.chatChannel(b);
		try {
			out.writeUTF("ChannelSwitch");
			out.writeUTF(sender);
			out.writeUTF(channel);
		} catch (IOException e) {
			e.printStackTrace();
		}

		BukkitPlugin.instance().sendBytesOut(b);
	}

	public static void setAfk(String sender, boolean value) {
		ChatHASHDB.setAfk(sender, value);
		ByteArrayOutputStream b = new ByteArrayOutputStream();
		DataOutputStream out = Channel.chatChannel(b);
		try {
			out.writeUTF("SetAfk");
			out.writeUTF(sender);
			out.writeBoolean(value);
		} catch (IOException e) {
			e.printStackTrace();
		}

		BukkitPlugin.instance().sendBytesOut(b);
	}

	public static void setSocialSpy(String sender) {
		ByteArrayOutputStream b = new ByteArrayOutputStream();
		DataOutputStream out = Channel.chatChannel(b);
		try {
			out.writeUTF("SocialSpy");
			out.writeUTF(sender);
		} catch (IOException e) {
			e.printStackTrace();
		}

		BukkitPlugin.instance().sendBytesOut(b);
	}

}

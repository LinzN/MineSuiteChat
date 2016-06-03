package de.nlinz.xeonSuite.chat.api;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import de.nlinz.javaSocket.client.api.XeonSocketClientManager;
import de.nlinz.xeonSuite.chat.database.ChatHASHDB;
import de.nlinz.xeonSuite.chat.listener.XeonChat;

public class CHStreamOutApi {

	public static void guildChat(String guild, String sender, String text) {
		ByteArrayOutputStream bytes = new ByteArrayOutputStream();
		DataOutputStream out = XeonSocketClientManager.createChannel(bytes, XeonChat.channelName);
		try {
			out.writeUTF("GuildChat");
			out.writeUTF(guild);
			out.writeUTF(sender);
			out.writeUTF(text);
		} catch (IOException e) {
			e.printStackTrace();
		}

		XeonSocketClientManager.sendData(bytes);
	}

	public static void channelChat(String sender, String rawtext, String prefix, String suffix, String channel,
			String guild) {
		ByteArrayOutputStream bytes = new ByteArrayOutputStream();
		DataOutputStream out = XeonSocketClientManager.createChannel(bytes, XeonChat.channelName);
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

		XeonSocketClientManager.sendData(bytes);
	}

	public static void privateMsg(String sender, String receiver, String text, String prefix) {
		ByteArrayOutputStream bytes = new ByteArrayOutputStream();
		DataOutputStream out = XeonSocketClientManager.createChannel(bytes, XeonChat.channelName);
		try {
			out.writeUTF("PrivateMsg");
			out.writeUTF(sender);
			out.writeUTF(receiver);
			out.writeUTF(text);
			out.writeUTF(prefix);
		} catch (IOException e) {
			e.printStackTrace();
		}

		XeonSocketClientManager.sendData(bytes);
	}

	public static void privateReply(String sender, String text, String prefix) {
		ByteArrayOutputStream bytes = new ByteArrayOutputStream();
		DataOutputStream out = XeonSocketClientManager.createChannel(bytes, XeonChat.channelName);
		try {
			out.writeUTF("PrivateReply");
			out.writeUTF(sender);
			out.writeUTF(text);
			out.writeUTF(prefix);
		} catch (IOException e) {
			e.printStackTrace();
		}

		XeonSocketClientManager.sendData(bytes);
	}

	public static void channelSwitch(String sender, String channel) {
		ByteArrayOutputStream bytes = new ByteArrayOutputStream();
		DataOutputStream out = XeonSocketClientManager.createChannel(bytes, XeonChat.channelName);
		try {
			out.writeUTF("ChannelSwitch");
			out.writeUTF(sender);
			out.writeUTF(channel);
		} catch (IOException e) {
			e.printStackTrace();
		}

		XeonSocketClientManager.sendData(bytes);
	}

	public static void setAfk(String sender, boolean value) {
		ChatHASHDB.setAfk(sender, value);
		ByteArrayOutputStream bytes = new ByteArrayOutputStream();
		DataOutputStream out = XeonSocketClientManager.createChannel(bytes, XeonChat.channelName);
		try {
			out.writeUTF("SetAfk");
			out.writeUTF(sender);
			out.writeBoolean(value);
		} catch (IOException e) {
			e.printStackTrace();
		}

		XeonSocketClientManager.sendData(bytes);
	}

	public static void setSocialSpy(String sender) {
		ByteArrayOutputStream bytes = new ByteArrayOutputStream();
		DataOutputStream out = XeonSocketClientManager.createChannel(bytes, XeonChat.channelName);
		try {
			out.writeUTF("SocialSpy");
			out.writeUTF(sender);
		} catch (IOException e) {
			e.printStackTrace();
		}

		XeonSocketClientManager.sendData(bytes);
	}

}

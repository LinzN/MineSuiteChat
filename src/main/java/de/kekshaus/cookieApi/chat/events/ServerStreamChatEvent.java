package de.kekshaus.cookieApi.chat.events;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import de.xHyveSoftware.socket.bukkit.api.annotation.Channel;
import de.xHyveSoftware.socket.bukkit.api.message.AbstractMessage;
import de.xHyveSoftware.socket.bukkit.api.message.Message;
import de.xHyveSoftware.socket.bukkit.packet.DefinedPacket;
import de.xHyveSoftware.socket.bukkit.util.Logger;

@Channel("ServerStreamChat")
public class ServerStreamChatEvent extends AbstractMessage {

	private byte[] bytes;

	public ServerStreamChatEvent() {
	}

	@Override
	public Message write(DataOutputStream outputStream) {
		try {
			DefinedPacket.writeArray(bytes, outputStream);
		} catch (IOException e) {
			Logger.error("Could not write Message :(");
		}

		return this;
	}

	@Override
	public Message read(DataInputStream inputStream) {
		try {
			bytes = DefinedPacket.readArray(inputStream);
		} catch (IOException e) {
			Logger.error("Could not read Message :(");
		}

		return this;
	}

	public void setBytes(byte[] bytes) {
		this.bytes = bytes;

	}

	public byte[] getData() {
		return this.bytes;

	}
}
package de.kekshaus.cookieApi.chat.events;

import java.io.ByteArrayOutputStream;

public class SendBungeeChatMessage implements Runnable {

	private final ByteArrayOutputStream bytes;

	public SendBungeeChatMessage(ByteArrayOutputStream bytes) {
		this.bytes = bytes;
	}

	public void run() {
		BungeeStreamChatEvent bungeeStreamEvent = new BungeeStreamChatEvent();
		bungeeStreamEvent.setBytes(bytes.toByteArray());
		bungeeStreamEvent.send();
	}

}
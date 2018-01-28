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

package de.linzn.mineSuite.chat.socket;

import de.linzn.mineSuite.core.MineSuiteCorePlugin;
import de.linzn.mineSuite.core.database.hashDatabase.ChatDataTable;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;


public class JClientChatOutput {

    public static void guildChat(String guild, String sender, String text) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        try {
            dataOutputStream.writeUTF("client_chat_guild-chat");
            dataOutputStream.writeUTF(guild);
            dataOutputStream.writeUTF(sender);
            dataOutputStream.writeUTF(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
        MineSuiteCorePlugin.getInstance().getMineJSocketClient().jClientConnection1.writeOutput("mineSuiteChat", byteArrayOutputStream.toByteArray());

    }

    public static void channelChat(String sender, String rawtext, String prefix, String suffix, String channel) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        try {
            dataOutputStream.writeUTF("client_chat_default-chat");
            dataOutputStream.writeUTF(sender);
            dataOutputStream.writeUTF(rawtext);
            dataOutputStream.writeUTF(prefix);
            dataOutputStream.writeUTF(suffix);
            dataOutputStream.writeUTF(channel);
        } catch (IOException e) {
            e.printStackTrace();
        }
        MineSuiteCorePlugin.getInstance().getMineJSocketClient().jClientConnection1.writeOutput("mineSuiteChat", byteArrayOutputStream.toByteArray());
    }

    public static void privateMsg(String sender, String receiver, String text, String prefix) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        try {
            dataOutputStream.writeUTF("client_chat_private-msg");
            dataOutputStream.writeUTF(sender);
            dataOutputStream.writeUTF(receiver);
            dataOutputStream.writeUTF(text);
            dataOutputStream.writeUTF(prefix);
        } catch (IOException e) {
            e.printStackTrace();
        }
        MineSuiteCorePlugin.getInstance().getMineJSocketClient().jClientConnection1.writeOutput("mineSuiteChat", byteArrayOutputStream.toByteArray());
    }

    public static void privateReply(String sender, String text, String prefix) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        try {
            dataOutputStream.writeUTF("client_chat_private-reply");
            dataOutputStream.writeUTF(sender);
            dataOutputStream.writeUTF(text);
            dataOutputStream.writeUTF(prefix);
        } catch (IOException e) {
            e.printStackTrace();
        }
        MineSuiteCorePlugin.getInstance().getMineJSocketClient().jClientConnection1.writeOutput("mineSuiteChat", byteArrayOutputStream.toByteArray());
    }

    public static void channelSwitch(String sender, String channel) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        try {
            dataOutputStream.writeUTF("client_chat_channel-switch");
            dataOutputStream.writeUTF(sender);
            dataOutputStream.writeUTF(channel);
        } catch (IOException e) {
            e.printStackTrace();
        }
        MineSuiteCorePlugin.getInstance().getMineJSocketClient().jClientConnection1.writeOutput("mineSuiteChat", byteArrayOutputStream.toByteArray());
    }

    public static void setAfk(String sender, boolean value) {
        ChatDataTable.setAfk(sender, value);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        try {
            dataOutputStream.writeUTF("client_chat_afk-switch");
            dataOutputStream.writeUTF(sender);
            dataOutputStream.writeBoolean(value);
        } catch (IOException e) {
            e.printStackTrace();
        }
        MineSuiteCorePlugin.getInstance().getMineJSocketClient().jClientConnection1.writeOutput("mineSuiteChat", byteArrayOutputStream.toByteArray());
    }

    public static void setSocialSpy(String sender) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        try {
            dataOutputStream.writeUTF("client_chat_spy-switch");
            dataOutputStream.writeUTF(sender);
        } catch (IOException e) {
            e.printStackTrace();
        }
        MineSuiteCorePlugin.getInstance().getMineJSocketClient().jClientConnection1.writeOutput("mineSuiteChat", byteArrayOutputStream.toByteArray());
    }

    public static void sendVote(String sender) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        try {
            dataOutputStream.writeUTF("client_chat-vote-informer");
            dataOutputStream.writeUTF(sender);
        } catch (IOException e) {
            e.printStackTrace();
        }
        MineSuiteCorePlugin.getInstance().getMineJSocketClient().jClientConnection1.writeOutput("mineSuiteChat", byteArrayOutputStream.toByteArray());
    }

}

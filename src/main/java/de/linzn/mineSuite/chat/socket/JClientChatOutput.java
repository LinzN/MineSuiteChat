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
import java.util.UUID;


public class JClientChatOutput {

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

    public static void chatTitel(String sender, String title, String subTitle, int time) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        try {
            dataOutputStream.writeUTF("client_data-title");
            dataOutputStream.writeUTF(title);
            dataOutputStream.writeUTF(subTitle);
            dataOutputStream.writeInt(time);
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

    public static void sendVote(String sender, double value) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        try {
            dataOutputStream.writeUTF("client_chat-vote-informer");
            dataOutputStream.writeUTF(sender);
            dataOutputStream.writeDouble(value);
        } catch (IOException e) {
            e.printStackTrace();
        }
        MineSuiteCorePlugin.getInstance().getMineJSocketClient().jClientConnection1.writeOutput("mineSuiteChat", byteArrayOutputStream.toByteArray());
    }

    public static void sendMail(UUID sender, String receiver, String text) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        try {
            dataOutputStream.writeUTF("client_chat-mail-send");
            dataOutputStream.writeUTF(sender.toString());
            dataOutputStream.writeUTF(receiver);
            dataOutputStream.writeUTF(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
        MineSuiteCorePlugin.getInstance().getMineJSocketClient().jClientConnection1.writeOutput("mineSuiteChat", byteArrayOutputStream.toByteArray());
    }

    public static void deleteMail(UUID actor, int mailID) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        try {
            dataOutputStream.writeUTF("client_chat-mail-delete");
            dataOutputStream.writeUTF(actor.toString());
            dataOutputStream.writeInt(mailID);
        } catch (IOException e) {
            e.printStackTrace();
        }
        MineSuiteCorePlugin.getInstance().getMineJSocketClient().jClientConnection1.writeOutput("mineSuiteChat", byteArrayOutputStream.toByteArray());
    }

    public static void showMail(UUID actor, int mailID) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        try {
            dataOutputStream.writeUTF("client_chat-mail-show");
            dataOutputStream.writeUTF(actor.toString());
            dataOutputStream.writeInt(mailID);
        } catch (IOException e) {
            e.printStackTrace();
        }
        MineSuiteCorePlugin.getInstance().getMineJSocketClient().jClientConnection1.writeOutput("mineSuiteChat", byteArrayOutputStream.toByteArray());
    }

    public static void listMails(UUID actor, int page) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        try {
            dataOutputStream.writeUTF("client_chat-mail-list");
            dataOutputStream.writeUTF(actor.toString());
            dataOutputStream.writeInt(page);
        } catch (IOException e) {
            e.printStackTrace();
        }
        MineSuiteCorePlugin.getInstance().getMineJSocketClient().jClientConnection1.writeOutput("mineSuiteChat", byteArrayOutputStream.toByteArray());
    }
}

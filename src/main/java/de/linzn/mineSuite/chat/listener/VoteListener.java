package de.linzn.mineSuite.chat.listener;

import com.vexsoftware.votifier.model.Vote;
import com.vexsoftware.votifier.model.VotifierEvent;
import de.linzn.mineSuite.chat.socket.JClientChatOutput;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;


public class VoteListener implements Listener {

    @EventHandler(priority = EventPriority.NORMAL)
    public void onVotifierEvent(VotifierEvent event) {
        Vote vote = event.getVote();
        JClientChatOutput.sendVote(vote.getUsername());
    }
}
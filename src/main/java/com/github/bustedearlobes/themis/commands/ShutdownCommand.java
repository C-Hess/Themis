package com.github.bustedearlobes.themis.commands;

import java.util.logging.Logger;
import java.util.regex.Matcher;

import com.github.bustedearlobes.themis.Themis;

import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.Message;

public class ShutdownCommand extends Command {
    private static final Logger LOG = Logger.getLogger("Themis");
    private static final String REGEX = "^shutdown$";
    
    public ShutdownCommand() {
        super(REGEX);
    }

    @Override
    public void onCall(Matcher fullCommand, Message message, JDA jda, Themis themis) {
        String discordName = message.getAuthor().getName() + "#" + message.getAuthor().getDiscriminator();
        if(discordName.equals(themis.getThemisOwner())) {
            LOG.info("Remote shutdown of themis initiated from " + discordName);
            message.getChannel().sendMessage("Shutting down remotely").complete();
            System.exit(0);
        }
    }

}
package com.github.bustedearlobes.themis.taskmanager;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import com.github.bustedearlobes.themis.music.GuildMusicManager;

import net.dv8tion.jda.core.entities.Guild;

public class MusicInactivityTask extends ScheduledTask {
    private static final Logger LOG = Logger.getLogger("Themis");
    private String guildID;
    
    public MusicInactivityTask(String guildID) {
        super(30, 0, TimeUnit.SECONDS, 0);
        this.guildID = guildID;
    }

    private static final long serialVersionUID = 1L;

    @Override
    public String getName() {
        return "Music Inactivity Task";
    }

    @Override
    protected void runTask() {
        Guild guild = getGuildById(guildID);
        GuildMusicManager musicManager = getThemis().getGlobalMusicManager().getGuildMusicManager(guild);
        if(guild.getAudioManager().isConnected()
                && guild.getAudioManager().getConnectedChannel().getMembers().size() == 1) {
            musicManager.getTrackScheduler().clear();
            musicManager.getAudioPlayer().stopTrack();
            guild.getAudioManager().closeAudioConnection();
            LOG.info("Closing audio connection in guild " + guild.getName() + " due to inactive channel");
        } else {
            getThemis().getTaskManager().addTask(new MusicInactivityTask(guild.getId()));
        }
    }

}
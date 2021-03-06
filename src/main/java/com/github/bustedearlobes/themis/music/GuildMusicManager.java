package com.github.bustedearlobes.themis.music;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;

import net.dv8tion.jda.api.entities.Guild;

public class GuildMusicManager {
    /**
     * Audio player for the guild.
     */
    private final AudioPlayer player;

    /**
     * Track scheduler for the player.
     */
    private final TrackScheduler scheduler;

    private final Guild guild;

    /**
     * Creates a player and a track scheduler.
     * 
     * @param manager
     *            Audio player manager to use for creating the player.
     */
    public GuildMusicManager(Guild guild, AudioPlayerManager manager) {
        player = manager.createPlayer();
        scheduler = new TrackScheduler(player);
        player.addListener(scheduler);
        this.guild = guild;
    }

    /**
     * @return Wrapper around AudioPlayer to use it as an AudioSendHandler.
     */
    public AudioPlayerSendHandler getSendHandler() {
        return new AudioPlayerSendHandler(player);
    }

    public Guild getGuild() {
        return guild;
    }

    public AudioPlayer getAudioPlayer() {
        return player;
    }

    public TrackScheduler getTrackScheduler() {
        return scheduler;
    }
}

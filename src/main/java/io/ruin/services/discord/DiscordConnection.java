package io.ruin.services.discord;

import net.dv8tion.jda.core.*;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.entities.ISnowflake;
import net.dv8tion.jda.core.entities.MessageEmbed;
import net.dv8tion.jda.core.events.Event;
import net.dv8tion.jda.core.events.ReadyEvent;
import net.dv8tion.jda.core.events.message.priv.PrivateMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.EventListener;

import javax.security.auth.login.LoginException;

public class DiscordConnection implements EventListener {

	private static JDA jda;
	private static DiscordConnection instance = new DiscordConnection();
	private static long myId;

	public static final long CHANNEL_PUNISHMENTS = 000L;

	public static void setup(String token) {
		try {
			jda = new JDABuilder(token).addEventListener(instance).build();
		} catch (LoginException e) {
			e.printStackTrace();
		}
	}


	@Override
	public void onEvent(Event event) {
		if (event instanceof ReadyEvent) {
			System.out.println("Discord ready.");

			myId = jda.getSelfUser().getIdLong();
			jda.getPresence().setPresence(OnlineStatus.ONLINE, Game.watching("people play Norse"));

			setupChannels();
		} else if (event instanceof PrivateMessageReceivedEvent) {
			PrivateMessageReceivedEvent pm = (PrivateMessageReceivedEvent) event;

			if (!isMe(pm.getAuthor())) {
				pm.getChannel().sendMessage("Hey there! :wave: I currently don't respond to messages (yet). I love you regardless though :blush:").submit();
			}
		} else {
			//System.out.println(event.getClass());
		}
	}

	public static void post(long channel, String title, String text) {
		MessageEmbed built = new EmbedBuilder().setTitle(title).setDescription(text).build();
		post(channel, built);
	}

	public static void post(long channel, MessageEmbed built) {
		try {
			jda.getTextChannelById(channel).sendMessage(built).submit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static boolean isMe(ISnowflake who) {
		return who != null && who.getIdLong() == myId;
	}

	private static void setupChannels() {
		jda.getTextChannels().forEach(textChannel -> {
			//System.out.println(textChannel.getTopic() + " => " + textChannel.getName() + " / " + textChannel.getIdLong());
		});
	}

}

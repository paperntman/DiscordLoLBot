package Bot.Commands;

import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;

import java.util.Random;

public class number {
    public number(SlashCommandEvent event) {
        event.reply(String.valueOf(new Random().nextInt(1, 100))).queue();
    }
}

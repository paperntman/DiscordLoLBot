package Bot.Commands;

import Bot.Main;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;

import java.util.ArrayList;
import java.util.Random;

public class champion {
    public champion(SlashCommandEvent event) {
        ArrayList<String> championNameList = new ArrayList<>();
        Main.champions.values().stream().forEach(a -> championNameList.add(String.valueOf(a)));
        event.reply(championNameList.get(new Random().nextInt(0, championNameList.size()))).queue();
    }
}

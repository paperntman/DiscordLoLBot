package Bot.Commands;

import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class lane {
    ArrayList<String> lanes = CommandManager.lanes;
    public lane(SlashCommandEvent event){
        if(lanes.isEmpty()) lanes.addAll(Arrays.asList("탑", "미드", "정글", "원딜", "서폿"));
        int random = new Random().nextInt(lanes.size());
        event.reply(lanes.get(random)).queue();
        lanes.remove(random);
    }
}

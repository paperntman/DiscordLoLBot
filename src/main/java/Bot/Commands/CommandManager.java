package Bot.Commands;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class CommandManager extends ListenerAdapter {
    private static List<CommandData> commandDataList = new ArrayList<>();

    public static void CommandManager(JDA jda) {
        commandDataList.add(new CommandData("mkch", "임시 채널을 생성합니다.")
                .addOption(OptionType.INTEGER, "max", "최대 인원 수", true)
                .addOption(OptionType.STRING, "title", "방의 제목입니다. 입력하지 않을 시 '임시'로 고정됩니다.", false));
        commandDataList.add(new CommandData("number", "1부터 100까지의 숫자를 얻습니다."));
        commandDataList.add(new CommandData("champion", "챔피언 하나의 이름을 불러옵니다."));
        for (Guild guild : jda.getGuilds()) {
            guild.retrieveCommands().complete().stream().filter(command -> command.getApplicationId().equals(jda.getSelfUser().getId())).forEach(command -> guild.deleteCommandById(command.getId()).complete());
            commandDataList.forEach(data -> guild.upsertCommand(data).queue());
        }
    }

    @Override
    public void onSlashCommand(@NotNull SlashCommandEvent event) {
        final String commandName = event.getName();
        if(commandDataList.stream().anyMatch(a -> a.getName().equalsIgnoreCase(commandName))){
            try {
                Class.forName("Bot.Commands."+event.getName()).getConstructor(SlashCommandEvent.class).newInstance(event);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

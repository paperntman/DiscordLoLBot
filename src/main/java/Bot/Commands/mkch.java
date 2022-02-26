package Bot.Commands;

import Bot.Config;
import Bot.Main;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class mkch {
    public mkch(SlashCommandEvent event) {
        final Guild guild = event.getGuild();
        final long max = event.getOption("max").getAsLong();
        if(max < 1){
            event.getChannel().sendMessage("제대로 된 숫자를 입력해주세요!").queue();
            return;
        }
        String name = event.getOption("title") == null ? "임시" : event.getOption("title").getAsString();
        final VoiceChannel voiceChannel = Main.jda.getGuildById(Config.get("guild")).createVoiceChannel(name).setUserlimit((int) max).complete();
        new VoiceChannelRemover(voiceChannel);
        event.reply("임시 채널을 생성하였습니다.").complete().deleteOriginal().queueAfter(5, TimeUnit.SECONDS);
    }

    private class VoiceChannelRemover {
        VoiceChannel voiceChannel;

        public VoiceChannelRemover(VoiceChannel voiceChannel) {
            this.voiceChannel = voiceChannel;
            Timer timer = new Timer();
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.MINUTE, 1);
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    while (!voiceChannel.getMembers().isEmpty());
                    voiceChannel.delete().queue();
                }
            }, calendar.getTime());
        }

    }
}

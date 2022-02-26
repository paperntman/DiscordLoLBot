package Bot;

import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Listener extends ListenerAdapter {

    final private static Logger logger = LoggerFactory.getLogger(Listener.class);

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        logger.info("{} sent {} at {}", event.getMember(), event.getMessage().getContentRaw(), event.getChannel().getId(), event.getGuild().getId());
    }

    @Override
    public void onReady(@NotNull ReadyEvent event) {
        logger.info("JDA ready");
    }
}
package net.tiimzee.piggyg.command.sys;

import static net.tiimzee.piggyg.resource.ResourceDirectory.ofSysSetting;

import java.io.File;
import java.io.FileWriter;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import static java.lang.System.out;

public class SetReportTimeout extends ListenerAdapter {
    
    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if (!event.getName().equals("setreporttimeout")) return;

        File reportTimeoutFile = new File(ofSysSetting(event.getGuild().getIdLong(), "reporttimeout.json"));

        try {
            if (event.getOption("type").getAsString().equals("s") || event.getOption("type").getAsString().equals("m") || event.getOption("type").getAsString().equals("h") || event.getOption("type").getAsString().equals("d")) {
                FileWriter writer = new FileWriter(reportTimeoutFile);
                writer.write(
                    "{\n" +
                    "  \"time\": " + event.getOption("time").getAsInt() + ",\n" +
                    "  \"timetype\": \"" + event.getOption("type").getAsString() + "\",\n" +
                    "}"
                );
                writer.close();
                event.reply("'Ight dawg, the timeout for reports been set").queue();
            } else {
                event.reply("""
                    Bozo, you need to select the right time type :man_facepalming:
                    Types are:
                    * s=Seconds
                    * m=Minutes
                    * h=Hours
                    * d=Days
                    """).queue();
            }
        } catch (Exception e) {
            out.println(e);
        }
    }
}

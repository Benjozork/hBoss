package me.benjozork.hboss.handler.internal;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * Created by Benjozork on 2016-04-26.
 */
public class CommandHandler implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!label.equalsIgnoreCase("hboss")) return false;
        else {
            if (args.length == 0) {
                sender.sendMessage(MessageHandler.getMessage("help_header"));
                sender.sendMessage(MessageHandler.getMessage("help_spawn"));
                sender.sendMessage(MessageHandler.getMessage("help_spawn_desc"));
                sender.sendMessage(MessageHandler.getMessage("help_set"));
                sender.sendMessage(MessageHandler.getMessage("help_set_desc"));
                sender.sendMessage(MessageHandler.getMessage("help_list"));
                sender.sendMessage(MessageHandler.getMessage("help_list_desc"));
                sender.sendMessage(MessageHandler.getMessage("help_types"));
                sender.sendMessage(MessageHandler.getMessage("help_types_desc"));
                sender.sendMessage(MessageHandler.getMessage("help_footer").replace("%pages%", "1/2"));
                return true;
            } else {
                for (char c : args[0].toCharArray()) {
                    if (Character.isDigit(c)) {
                        sender.sendMessage(MessageHandler.getMessage("help_header"));
                        sender.sendMessage(MessageHandler.getMessage("help_info"));
                        sender.sendMessage(MessageHandler.getMessage("help_info_desc"));
                        sender.sendMessage(MessageHandler.getMessage("help_effect_list"));
                        sender.sendMessage(MessageHandler.getMessage("help_effect_list_desc"));
                        sender.sendMessage(MessageHandler.getMessage("help_effect_add"));
                        sender.sendMessage(MessageHandler.getMessage("help_effect_add_desc"));
                        sender.sendMessage(MessageHandler.getMessage("help_effect_remove"));
                        sender.sendMessage(MessageHandler.getMessage("help_effect_remove_desc"));
                        sender.sendMessage(MessageHandler.getMessage("help_footer").replace("%pages%", "2/2"));
                    }
                }
            }
        }
        return false;
    }
}

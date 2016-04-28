package me.benjozork.hboss.handler.internal;

import me.benjozork.hboss.handler.SpawnHandler;
import me.benjozork.hboss.object.BossSpawner;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

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
                if (args[0].equalsIgnoreCase("spawn")) {
                    if (!(sender instanceof Player)) {
                        sender.sendMessage(MessageHandler.getMessage("must_be_player"));
                        return false;
                    }

                    Player player = (Player) sender;

                    EntityType type;
                    String name;
                    int healthMultiplier;

                    if (args.length < 5) notEnoughArgs(sender, "spawn", args.length, 5);

                    name = args[1];

                    try {
                        type = EntityType.valueOf(args[2]);
                    } catch (IllegalArgumentException e) {
                        sender.sendMessage(MessageHandler.getMessage("invalid_mob_type").replace("%string%", args[2]));
                        return false;
                    }

                    try {
                        healthMultiplier = Integer.parseInt(args[3]);
                    } catch (NumberFormatException e) {
                        sender.sendMessage(MessageHandler.getMessage("not_a_number").replace("%string%", args[3]));
                        return false;
                    }

                    BossSpawner s = new BossSpawner(name, player.getLocation(), type, healthMultiplier);
                    SpawnHandler.addSpawn(s);
                    return true;
                }

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

    private void notEnoughArgs(CommandSender sender, String spawn, int length, int i) {
        sender.sendMessage(MessageHandler.getMessage("not_enough_args")
                .replace("%command%", "spawn")
                .replace("%curr%", length + "")
                .replace("%req%", i + ""));
    }
}

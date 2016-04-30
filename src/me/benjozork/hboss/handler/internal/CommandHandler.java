package me.benjozork.hboss.handler.internal;

import me.benjozork.hboss.HBoss;
import me.benjozork.hboss.handler.SpawnHandler;
import me.benjozork.hboss.object.BossAttribute;
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

    private final HBoss main;

    public CommandHandler(HBoss main) {
        this.main = main;
    }
    
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
                if (args[0].equalsIgnoreCase("spawn")) { // Spawn subcommand
                    if (!(sender instanceof Player)) {
                        sender.sendMessage(MessageHandler.getMessage("must_be_player"));
                        return false;
                    }

                    Player player = (Player) sender;

                    EntityType type;
                    String name;
                    int healthMultiplier;

                    if (args.length < 4)  {
                        notEnoughArgs(sender, "spawn", args.length, 4);
                        return false;
                    }

                    name = args[1];

                    try {
                        type = EntityType.valueOf(args[2].toUpperCase());
                    } catch (IllegalArgumentException e) {
                        sender.sendMessage(MessageHandler.getMessage("invalid_mob_type").replace("%string%", args[2]));
                        return false;
                    }

                    try {
                        healthMultiplier = Integer.parseInt(args[3]);
                    } catch (NumberFormatException e) {
                        sender.sendMessage(MessageHandler.getMessage("not_a_number").replace("%string%", args[3]).replace("%arg%", "health multiplier"));
                        return false;
                    }

                    BossSpawner s = new BossSpawner(name, player.getLocation(), type, healthMultiplier);
                    SpawnHandler.addSpawn(s);

                    player.sendMessage(MessageHandler.getMessage("spawn_created").replace("%name%", name));
                    
                    return true;
                } else if (args[0].equalsIgnoreCase("list")) { // List subcommand
                    if (SpawnHandler.getSpawns().size() == 0) {
                        sender.sendMessage(MessageHandler.getMessage("spawn_list_none"));
                        return true;
                    }
                    sender.sendMessage(MessageHandler.getMessage("spawn_list_header").replace("%count%", SpawnHandler.getSpawns().size() + ""));
                    for (BossSpawner b : SpawnHandler.getSpawns()) {
                        sender.sendMessage(MessageHandler.getMessage("spawn_list_name").replace("%name%", b.getName()));
                        sender.sendMessage(MessageHandler.getMessage("spawn_list_info")
                                .replace("%type%", b.getType().toString().toLowerCase())
                                .replace("%healthMultiplier%", b.getHealthMultiplier() + ""));
                        String attributes = "";
                        if (b.getAttributes().size() == 0) attributes = MessageHandler.getMessage("spawn_no_attributes");
                        for (int i = 0; i < b.getAttributes().size(); i++) {
                            attributes += (i > b.getAttributes().size() - 1 ? b.getAttributes().get(i) + ", " : b.getAttributes().get(i) + ".");
                        }
                        sender.sendMessage(MessageHandler.getMessage("spawn_list_attributes").replace("%attributes%", attributes));
                    }
                    return true;
                } else if (args[0].equalsIgnoreCase("delete")) { // Delete subcommand
                    if (args.length < 2) {
                        notEnoughArgs(sender, "delete", args.length, 2);
                        return false;
                    }

                    try {
                        SpawnHandler.removeSpawn(args[1]);
                    } catch (IllegalArgumentException e) {
                        sender.sendMessage(MessageHandler.getMessage("invalid_spawn_name").replace("%string%", args[1]));
                        return  false;
                    }
                    sender.sendMessage(MessageHandler.getMessage("spawn_delete_deleted").replace("%name%", args[1]));
                } else if (args[0].equalsIgnoreCase("set")) { // Set subcommand
                    if (args.length == 3) { // Location only requires 3 args
                        if (args[1].equalsIgnoreCase("location")) {

                        }
                    }

                    if (args.length < 4) {
                        notEnoughArgs(sender, "set", args.length, 4);
                        return false;
                    }

                    if (args[1].equalsIgnoreCase("name")) { // Name param

                    } else if (args[1].equalsIgnoreCase("tpye")) { // Type param

                    } else if (args[1].equalsIgnoreCase("health_multiplier")) { // HM param

                    }
                }

                for (char c : args[0].toCharArray()) {
                    if (Character.isDigit(c)) {
                        sender.sendMessage(MessageHandler.getMessage("help_header"));
                        sender.sendMessage(MessageHandler.getMessage("help_delete"));
                        sender.sendMessage(MessageHandler.getMessage("help_delete_desc"));
                        sender.sendMessage(MessageHandler.getMessage("help_effect_list"));
                        sender.sendMessage(MessageHandler.getMessage("help_effect_list_desc"));
                        sender.sendMessage(MessageHandler.getMessage("help_effect_add"));
                        sender.sendMessage(MessageHandler.getMessage("help_effect_add_desc"));
                        sender.sendMessage(MessageHandler.getMessage("help_effect_remove"));
                        sender.sendMessage(MessageHandler.getMessage("help_effect_remove_desc"));
                        sender.sendMessage(MessageHandler.getMessage("help_footer").replace("%pages%", "2/2"));
                        return true;
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

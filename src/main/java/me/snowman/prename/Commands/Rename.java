package me.snowman.prename.Commands;

import me.snowman.prename.ItemRename;
import me.snowman.prename.Items;
import me.snowman.prename.Utils.MessageUtils;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;


public class Rename implements CommandExecutor {
    private ItemRename plugin = ItemRename.getPlugin(ItemRename.class);
    private static Economy econ = null;
    Items i = new Items();
    private MessageUtils msgUtils = new MessageUtils();


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        Inventory invren = Bukkit.createInventory(null, 9, msgUtils.colorize(plugin.getConfig().getString("Messages.RenameTitle")));
        Inventory invcol = Bukkit.createInventory(null, 9, msgUtils.colorize(plugin.getConfig().getString("Messages.ColorTitle")));

        List<String> lorerenametag = plugin.getConfig().getStringList("TagRenameLore");
        lorerenametag.replaceAll(string -> msgUtils.colorize(string));

        List<String> lorecolortag = plugin.getConfig().getStringList("TagColorLore");
        lorecolortag.replaceAll(string -> msgUtils.colorize(string));

        List<String> dyelore = plugin.getConfig().getStringList("DyeLore");
        dyelore.replaceAll(string -> msgUtils.colorize(string));

        List<String> lockedlore = plugin.getConfig().getStringList("LockedLore");
        lockedlore.replaceAll(string -> msgUtils.colorize(string));

        if (cmd.getName().equalsIgnoreCase("rename")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                invren.setItem(4, i.waiting());
                invren.setItem(0, i.empty());
                invren.setItem(2, i.empty());
                invren.setItem(6, i.empty());
                invren.setItem(5, i.empty());
                invren.setItem(8, i.empty());

                player.openInventory(invren);
                plugin.rename(player);
                return true;
            }
        }

        if (cmd.getName().equalsIgnoreCase("colorize")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                invcol.setItem(4, i.waiting());
                invcol.setItem(0, i.empty());
                invcol.setItem(2, i.empty());
                invcol.setItem(6, i.empty());
                invcol.setItem(5, i.empty());
                invcol.setItem(8, i.empty());

                player.openInventory(invcol);
                plugin.colorize(player);
            }
            return true;
        }

        if (args.length == 0 || args[0].equalsIgnoreCase("help")) {
            sender.sendMessage(msgUtils.colorize("&9&lItem rename commands\n&9/ir help &7- &fShows this\n&9/ir rename &7- &fRename an item using a colored tag\n&9/ir colorize &7- &fColor a tag using dyes\n&9/ir tag &7- &fGet a custom name tag\n&9/ir dye &7- &fGet a custom dye"));
            return true;
        }
        if (args[0].equalsIgnoreCase("rename")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                ItemStack glassu = new ItemStack(Material.matchMaterial(i.mat.get("glass_pane")), 1, (byte) 15);
                invren.setItem(4, i.waiting());
                invren.setItem(0, glassu);
                invren.setItem(2, glassu);
                invren.setItem(6, glassu);
                invren.setItem(5, glassu);
                invren.setItem(8, glassu);

                player.openInventory(invren);
                plugin.rename(player);
                return true;
            }
        }
        if (args[0].equalsIgnoreCase("colorize")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                ItemStack glassu = new ItemStack(Material.matchMaterial(i.mat.get("glass_pane")), 1, (byte) 15);
                invcol.setItem(4, i.waiting());
                invcol.setItem(0, glassu);
                invcol.setItem(2, glassu);
                invcol.setItem(6, glassu);
                invcol.setItem(5, glassu);
                invcol.setItem(8, glassu);

                player.openInventory(invcol);
                plugin.colorize(player);


            }
            return true;
        }

        if (args[0].equalsIgnoreCase("dye")) {

            if (args.length == 1) {
                sender.sendMessage(msgUtils.colorize(plugin.getConfig().getString("Messages.NoDye") + "\n&0BLACK&7, &4RED&7, &2DARKGREEN&7, &3BLUE&7, &5DARKPURPLE&7, &1DARKAQUA&7, &7GRAY, &8DARKGRAY&7, &dPINK&7, &aGREEN&7, &eYELLOW, &bAQUA&7, &6GOLD&7, &fWHITE&7, &r&lBOLD&7, &r&oITALIC&7, &8LOCKED"));
            }

            if (args.length == 2) {
                if (!(sender instanceof Player)) {
                    sender.sendMessage(msgUtils.colorize("&1The console can't have dyes :("));
                } else {
                    Player player = (Player) sender;
                    if (player.hasPermission("itemrename.dye")) {
                        if (args[1].equalsIgnoreCase("black")) {
                            player.getInventory().addItem(i.black());
                            player.sendMessage(msgUtils.colorize(plugin.getConfig().getString("Messages.DyeGive")).replace("%dye%", args[1]));
                        }
                        if (args[1].equalsIgnoreCase("red")) {
                            player.getInventory().addItem(i.red());
                            player.sendMessage(msgUtils.colorize(plugin.getConfig().getString("Messages.DyeGive")).replace("%dye%", args[1]));
                        }
                        if (args[1].equalsIgnoreCase("darkgreen")) {
                            player.getInventory().addItem(i.dgreen());
                            player.sendMessage(msgUtils.colorize(plugin.getConfig().getString("Messages.DyeGive")).replace("%dye%", args[1]));
                        }
                        if (args[1].equalsIgnoreCase("blue")) {
                            player.getInventory().addItem(i.blue());
                            player.sendMessage(msgUtils.colorize(plugin.getConfig().getString("Messages.DyeGive")).replace("%dye%", args[1]));
                        }
                        if (args[1].equalsIgnoreCase("darkpurple")) {
                            player.getInventory().addItem(i.dpurple());
                            player.sendMessage(msgUtils.colorize(plugin.getConfig().getString("Messages.DyeGive")).replace("%dye%", args[1]));
                        }
                        if (args[1].equalsIgnoreCase("darkaqua")) {
                            player.getInventory().addItem(i.daqua());
                            player.sendMessage(msgUtils.colorize(plugin.getConfig().getString("Messages.DyeGive")).replace("%dye%", args[1]));
                        }
                        if (args[1].equalsIgnoreCase("gray")) {
                            player.getInventory().addItem(i.gray());
                            player.sendMessage(msgUtils.colorize(plugin.getConfig().getString("Messages.DyeGive")).replace("%dye%", args[1]));
                        }
                        if (args[1].equalsIgnoreCase("darkgray")) {
                            player.getInventory().addItem(i.dgray());
                            player.sendMessage(msgUtils.colorize(plugin.getConfig().getString("Messages.DyeGive")).replace("%dye%", args[1]));
                        }
                        if (args[1].equalsIgnoreCase("pink")) {
                            player.getInventory().addItem(i.pink());
                            player.sendMessage(msgUtils.colorize(plugin.getConfig().getString("Messages.DyeGive")).replace("%dye%", args[1]));
                        }
                        if (args[1].equalsIgnoreCase("green")) {
                            player.getInventory().addItem(i.green());
                            player.sendMessage(msgUtils.colorize(plugin.getConfig().getString("Messages.DyeGive")).replace("%dye%", args[1]));
                        }
                        if (args[1].equalsIgnoreCase("yellow")) {
                            player.getInventory().addItem(i.yellow());
                            player.sendMessage(msgUtils.colorize(plugin.getConfig().getString("Messages.DyeGive")).replace("%dye%", args[1]));
                        }
                        if (args[1].equalsIgnoreCase("aqua")) {
                            player.getInventory().addItem(i.aqua());
                            player.sendMessage(msgUtils.colorize(plugin.getConfig().getString("Messages.DyeGive")).replace("%dye%", args[1]));
                        }
                        if (args[1].equalsIgnoreCase("gold")) {
                            player.getInventory().addItem(i.gold());
                            player.sendMessage(msgUtils.colorize(plugin.getConfig().getString("Messages.DyeGive")).replace("%dye%", args[1]));
                        }
                        if (args[1].equalsIgnoreCase("white")) {
                            player.getInventory().addItem(i.white());
                            player.sendMessage(msgUtils.colorize(plugin.getConfig().getString("Messages.DyeGive")).replace("%dye%", args[1]));
                        }
                        if (args[1].equalsIgnoreCase("bold")) {
                            player.getInventory().addItem(i.bold());
                            player.sendMessage(msgUtils.colorize(plugin.getConfig().getString("Messages.DyeGive")).replace("%dye%", args[1]));
                        }
                        if (args[1].equalsIgnoreCase("italic")) {
                            player.getInventory().addItem(i.italic());
                            player.sendMessage(msgUtils.colorize(plugin.getConfig().getString("Messages.DyeGive")).replace("%dye%", args[1]));
                        }
                        if (args[1].equalsIgnoreCase("locked")) {
                            player.getInventory().addItem(i.locked());
                            player.sendMessage(msgUtils.colorize(plugin.getConfig().getString("Messages.DyeGive")).replace("%dye%", args[1]));
                        }
                        if (args[1].equalsIgnoreCase("all")) {
                            player.getInventory().addItem(i.red());
                            player.getInventory().addItem(i.gold());
                            player.getInventory().addItem(i.yellow());
                            player.getInventory().addItem(i.green());
                            player.getInventory().addItem(i.dgreen());
                            player.getInventory().addItem(i.daqua());
                            player.getInventory().addItem(i.aqua());
                            player.getInventory().addItem(i.blue());
                            player.getInventory().addItem(i.dpurple());
                            player.getInventory().addItem(i.pink());
                            player.getInventory().addItem(i.white());
                            player.getInventory().addItem(i.gray());
                            player.getInventory().addItem(i.dgray());
                            player.getInventory().addItem(i.black());
                            player.getInventory().addItem(i.bold());
                            player.getInventory().addItem(i.italic());
                            player.getInventory().addItem(i.locked());
                            player.sendMessage(msgUtils.colorize(plugin.getConfig().getString("Messages.DyeGive")).replace("%dye%", args[1]));
                        }
                    } else {
                        sender.sendMessage(msgUtils.colorize(plugin.getConfig().getString("Messages.NoPerm")));
                    }
                }
            }
        }

        if (args[0].equalsIgnoreCase("tag")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage(msgUtils.colorize("&1The console can't have tags :("));
                return true;
            }
            Player player = (Player) sender;
            if (player.hasPermission("itemrename.tag")) {
                if (args.length >= 2) {
                    String name = "";
                    for (int i = 1; i < args.length; i++) {
                        //noinspection StringConcatenationInLoop
                        name = name + args[i] + " ";
                    }
                    System.out.println(name);
                    ItemStack tag = new ItemStack(Material.NAME_TAG, 1);
                    ItemMeta tagmeta = tag.getItemMeta();
                    lorecolortag.replaceAll(string -> msgUtils.colorize(string));
                    tagmeta.setLore(lorecolortag);
                    tagmeta.setDisplayName(msgUtils.colorize(name));
                    tag.setItemMeta(tagmeta);
                    player.getInventory().addItem(tag);
                    player.sendMessage(msgUtils.colorize(plugin.getConfig().getString("Messages.TagGive")));
                    return true;

                } else {

                    ItemStack tag = new ItemStack(Material.NAME_TAG, 1);
                    ItemMeta tagmeta = tag.getItemMeta();
                    lorecolortag.replaceAll(string -> msgUtils.colorize(string));
                    tagmeta.setLore(lorecolortag);
                    tagmeta.setDisplayName("Rename Tag");
                    tag.setItemMeta(tagmeta);
                    player.getInventory().addItem(tag);
                    player.sendMessage(msgUtils.colorize(plugin.getConfig().getString("Messages.TagGive")));
                    return true;
                }
            } else {
                sender.sendMessage(msgUtils.colorize(plugin.getConfig().getString("Messages.NoPerm")));
            }
        }


        if (args[0].equalsIgnoreCase("reload")) {
            if (sender.hasPermission("itemrename.reload")) {
                i.matInit();
                plugin.reloadConfig();
                sender.sendMessage(msgUtils.colorize(plugin.getConfig().getString("Messages.Reload")));
                return true;
            } else {
                sender.sendMessage(msgUtils.colorize(plugin.getConfig().getString("Messages.NoPerm")));
            }
        }
        return true;
    }
}


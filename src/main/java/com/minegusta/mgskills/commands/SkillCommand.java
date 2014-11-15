package com.minegusta.mgskills.commands;

import com.google.common.collect.Lists;
import com.minegusta.mgskills.Main;
import com.minegusta.mgskills.files.DetailedMPlayer;
import com.minegusta.mgskills.files.LoadToMap;
import com.minegusta.mgskills.files.RemoveFromMap;
import com.minegusta.mgskills.skills.SkillInfo;
import com.minegusta.mgskills.struct.ISkill;
import com.minegusta.mgskills.util.ExpTable;
import com.minegusta.mgskills.util.Skill;
import com.minegusta.mgskills.util.TempData;
import com.minegusta.mgskills.util.json.JsonFileUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.UUID;

public class SkillCommand implements CommandExecutor {
    private Player p;

    @Override
    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("skills") && s instanceof Player) {
            p = (Player) s;
            if (args.length == 1) {
                if (args[0].equalsIgnoreCase("show") || args[0].equalsIgnoreCase("s")) {
                    sendStats(p.getUniqueId());
                    return true;
                }
                if (args[0].equalsIgnoreCase("info") || args[0].equalsIgnoreCase("i")) {
                    sendList(SkillInfo.SKILLS.getInfo());
                    return true;
                }
                if (args[0].equalsIgnoreCase("toggle") || args[0].equalsIgnoreCase("t")) {
                    boolean show = TempData.getMPlayer(p).showExp();
                    if (show) {
                        sendString("Experience is no longer shown in chat when earned.");
                        sendString("Use " + ChatColor.GOLD + "/Skills Toggle" + ChatColor.YELLOW + " to re-enable this feature.");
                        TempData.getMPlayer(p).setShowExp(false);
                    } else {
                        sendString("Experience is now shown in chat when earned.");
                        sendString("Use " + ChatColor.GOLD + "/Skills Toggle" + ChatColor.YELLOW + " to disable this feature.");
                        TempData.getMPlayer(p).setShowExp(true);
                    }
                    return true;
                }
            }
            if (args.length == 2) {
                if (args[0].equalsIgnoreCase("show") || args[0].equalsIgnoreCase("s")) {
                    try {
                        Player oPlayer = Bukkit.getOfflinePlayer(args[1]).getPlayer();
                        if (sendStats(oPlayer.getUniqueId())) return true;
                        sendString("That player cannot be found! They might not exist or never played.");
                        return true;

                    } catch (Exception ignored) {
                        sendString("That player cannot be found! They might not exist or never played.");
                    }
                    return true;

                }

                if (args[0].equalsIgnoreCase("info") || args[0].equalsIgnoreCase("i")) {
                    try {
                        String skill = args[1];
                        sendInfoList(SkillInfo.valueOf(skill.toUpperCase()).getInfo(), skill);
                        return true;

                    } catch (Exception ignored) {
                        sendList(SkillInfo.SKILLS.getInfo());
                        return true;
                    }
                }
            }
            sendList(SkillInfo.HELP.getInfo());
        }
        return true;
    }

    private void sendList(String[] list) {
        p.sendMessage(ChatColor.YELLOW + "-=-=-=-=-=-=-=-=-=-=-=-=-");
        p.sendMessage(ChatColor.YELLOW + "-=-=-=-=" + ChatColor.RED + "Skills Help" + ChatColor.YELLOW + "=-=-=-=-");
        p.sendMessage(ChatColor.YELLOW + "-=-=-=-=-=-=-=-=-=-=-=-=-");
        for (String s : list) {
            p.sendMessage(ChatColor.LIGHT_PURPLE + s);
        }
    }

    private void sendInfoList(String[] list, String skill) {
        try {
            if (Skill.valueOf(skill.toUpperCase()).getSkillName() == null) {
                sendList(SkillInfo.SKILLS.getInfo());
                return;
            }
        } catch (Exception ignored) {
            sendList(SkillInfo.HELP.getInfo());
        }

        DetailedMPlayer mPlayer = TempData.getMPlayer(p);
        ISkill skillClass = Skill.valueOf(skill.toUpperCase()).getSkill();
        skillClass.insertMPlayer(mPlayer);
        List<String> list2 = Lists.newArrayList("Current Level: " + ChatColor.GREEN + skillClass.getLevel(), "Progress to next level: " + ChatColor.GREEN + skillClass.getExp() + ChatColor.YELLOW + "/" + ChatColor.DARK_GREEN + ExpTable.valueOf("L" + Integer.toString(skillClass.getLevel() + 1)).getExp(), skillClass.getSpecialBoost());

        p.sendMessage(ChatColor.YELLOW + "-=-=-=-=-=-=-=-=-=-=-=-=-");
        p.sendMessage("      " + ChatColor.RED + skillClass.getName() + " Info");
        p.sendMessage(ChatColor.YELLOW + "-=-=-=-=-=-=-=-=-=-=-=-=-");
        for (String s : list2) {
            p.sendMessage(ChatColor.LIGHT_PURPLE + s);
        }
        for (String s : list) {
            p.sendMessage(ChatColor.LIGHT_PURPLE + s);
        }
    }

    private boolean sendStats(UUID uuid) {
        if (uuid == null) return false;
        DetailedMPlayer mp;
        if (TempData.containsMPlayer(uuid.toString())) {
            mp = TempData.getMPlayer(Bukkit.getPlayer(uuid));
        } else {
            if (!JsonFileUtil.exists(Main.PLUGIN.getDataFolder().getPath() + "/players/", uuid.toString() + ".yml"))
                return false;
            new LoadToMap(Bukkit.getPlayer(uuid));
            mp = TempData.getMPlayer(Bukkit.getPlayer(uuid));
        }

        p.sendMessage(ChatColor.YELLOW + "-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
        p.sendMessage(ChatColor.RED + "           " + ChatColor.BOLD + Bukkit.getPlayer(uuid).getName() + "'s Skills");
        p.sendMessage("   ");
        p.sendMessage(ChatColor.GRAY + "  Skill            Level      Experience/NextLevel");
        p.sendMessage(ChatColor.YELLOW + "-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
        p.sendMessage(ChatColor.GOLD + "[" + ChatColor.LIGHT_PURPLE + "Fishing" + ChatColor.GOLD + "]" + "          " + ChatColor.GOLD + "[" + ChatColor.YELLOW + mp.getLevel(Skill.FISHING) + ChatColor.GOLD + "]" + "   " + ChatColor.GRAY + fitString(mp.getLevel(Skill.FISHING)) + ChatColor.GOLD + "[" + ChatColor.GREEN + mp.getExp(Skill.FISHING) + ChatColor.YELLOW + "/" + ChatColor.DARK_GREEN + ExpTable.valueOf("L" + Integer.toString(mp.getLevel(Skill.FISHING) + 1)).getExp() + ChatColor.GOLD + "]");
        p.sendMessage(ChatColor.GOLD + "[" + ChatColor.LIGHT_PURPLE + "Mining" + ChatColor.GOLD + "]" + "           " + ChatColor.GOLD + "[" + ChatColor.YELLOW + mp.getLevel(Skill.MINING) + ChatColor.GOLD + "]" + "   " + ChatColor.GRAY + fitString(mp.getLevel(Skill.MINING)) + ChatColor.GOLD + "[" + ChatColor.GREEN + mp.getExp(Skill.MINING) + ChatColor.YELLOW + "/" + ChatColor.DARK_GREEN + ExpTable.valueOf("L" + Integer.toString(mp.getLevel(Skill.MINING) + 1)).getExp() + ChatColor.GOLD + "]");
        p.sendMessage(ChatColor.GOLD + "[" + ChatColor.LIGHT_PURPLE + "Cooking" + ChatColor.GOLD + "]" + "         " + ChatColor.GOLD + "[" + ChatColor.YELLOW + mp.getLevel(Skill.COOKING) + ChatColor.GOLD + "]" + "   " + ChatColor.GRAY + fitString(mp.getLevel(Skill.COOKING)) + ChatColor.GOLD + "[" + ChatColor.GREEN + mp.getExp(Skill.COOKING) + ChatColor.YELLOW + "/" + ChatColor.DARK_GREEN + ExpTable.valueOf("L" + Integer.toString(mp.getLevel(Skill.COOKING) + 1)).getExp() + ChatColor.GOLD + "]");
        p.sendMessage(ChatColor.GOLD + "[" + ChatColor.LIGHT_PURPLE + "Summoning" + ChatColor.GOLD + "]" + "     " + ChatColor.GOLD + "[" + ChatColor.YELLOW + mp.getLevel(Skill.SUMMONING) + ChatColor.GOLD + "]" + "   " + ChatColor.GRAY + fitString(mp.getLevel(Skill.SUMMONING)) + ChatColor.GOLD + "[" + ChatColor.GREEN + mp.getExp(Skill.SUMMONING) + ChatColor.YELLOW + "/" + ChatColor.DARK_GREEN + ExpTable.valueOf("L" + Integer.toString(mp.getLevel(Skill.SUMMONING) + 1)).getExp() + ChatColor.GOLD + "]");
        p.sendMessage(ChatColor.GOLD + "[" + ChatColor.LIGHT_PURPLE + "Farming" + ChatColor.GOLD + "]" + "         " + ChatColor.GOLD + "[" + ChatColor.YELLOW + mp.getLevel(Skill.FARMING) + ChatColor.GOLD + "]" + "   " + ChatColor.GRAY + fitString(mp.getLevel(Skill.FARMING)) + ChatColor.GOLD + "[" + ChatColor.GREEN + mp.getExp(Skill.FARMING) + ChatColor.YELLOW + "/" + ChatColor.DARK_GREEN + ExpTable.valueOf("L" + Integer.toString(mp.getLevel(Skill.FARMING) + 1)).getExp() + ChatColor.GOLD + "]");
        p.sendMessage(ChatColor.GOLD + "[" + ChatColor.LIGHT_PURPLE + "Hunting" + ChatColor.GOLD + "]" + "         " + ChatColor.GOLD + "[" + ChatColor.YELLOW + mp.getLevel(Skill.HUNTING) + ChatColor.GOLD + "]" + "   " + ChatColor.GRAY + fitString(mp.getLevel(Skill.HUNTING)) + ChatColor.GOLD + "[" + ChatColor.GREEN + mp.getExp(Skill.HUNTING) + ChatColor.YELLOW + "/" + ChatColor.DARK_GREEN + ExpTable.valueOf("L" + Integer.toString(mp.getLevel(Skill.HUNTING) + 1)).getExp() + ChatColor.GOLD + "]");
        p.sendMessage(ChatColor.GOLD + "[" + ChatColor.LIGHT_PURPLE + "Woodcutting" + ChatColor.GOLD + "]" + "   " + ChatColor.GOLD + "[" + ChatColor.YELLOW + mp.getLevel(Skill.WOODCUTTING) + ChatColor.GOLD + "]" + "   " + ChatColor.GRAY + fitString(mp.getLevel(Skill.WOODCUTTING)) + ChatColor.GOLD + "[" + ChatColor.GREEN + mp.getExp(Skill.WOODCUTTING) + ChatColor.YELLOW + "/" + ChatColor.DARK_GREEN + ExpTable.valueOf("L" + Integer.toString(mp.getLevel(Skill.WOODCUTTING) + 1)).getExp() + ChatColor.GOLD + "]");
        p.sendMessage(ChatColor.GOLD + "[" + ChatColor.LIGHT_PURPLE + "Digging" + ChatColor.GOLD + "]" + "         " + ChatColor.GOLD + "[" + ChatColor.YELLOW + mp.getLevel(Skill.DIGGING) + ChatColor.GOLD + "]" + "   " + ChatColor.GRAY + fitString(mp.getLevel(Skill.DIGGING)) + ChatColor.GOLD + "[" + ChatColor.GREEN + mp.getExp(Skill.DIGGING) + ChatColor.YELLOW + "/" + ChatColor.DARK_GREEN + ExpTable.valueOf("L" + Integer.toString(mp.getLevel(Skill.DIGGING) + 1)).getExp() + ChatColor.GOLD + "]");
        p.sendMessage(ChatColor.GOLD + "[" + ChatColor.LIGHT_PURPLE + "Brewing" + ChatColor.GOLD + "]" + "        " + ChatColor.GOLD + "[" + ChatColor.YELLOW + mp.getLevel(Skill.BREWING) + ChatColor.GOLD + "]" + "   " + ChatColor.GRAY + fitString(mp.getLevel(Skill.BREWING)) + ChatColor.GOLD + "[" + ChatColor.GREEN + mp.getExp(Skill.BREWING) + ChatColor.YELLOW + "/" + ChatColor.DARK_GREEN + ExpTable.valueOf("L" + Integer.toString(mp.getLevel(Skill.BREWING) + 1)).getExp() + ChatColor.GOLD + "]");
        p.sendMessage(ChatColor.GOLD + "[" + ChatColor.LIGHT_PURPLE + "Healing" + ChatColor.GOLD + "]" + "         " + ChatColor.GOLD + "[" + ChatColor.YELLOW + mp.getLevel(Skill.HEALING) + ChatColor.GOLD + "]" + "   " + ChatColor.GRAY + fitString(mp.getLevel(Skill.HEALING)) + ChatColor.GOLD + "[" + ChatColor.GREEN + mp.getExp(Skill.HEALING) + ChatColor.YELLOW + "/" + ChatColor.DARK_GREEN + ExpTable.valueOf("L" + Integer.toString(mp.getLevel(Skill.HEALING) + 1)).getExp() + ChatColor.GOLD + "]");
        p.sendMessage(ChatColor.GOLD + "[" + ChatColor.LIGHT_PURPLE + "Exploration" + ChatColor.GOLD + "]" + "   " + ChatColor.GOLD + "[" + ChatColor.YELLOW + mp.getLevel(Skill.EXPLORATION) + ChatColor.GOLD + "]" + "   " + ChatColor.GRAY + fitString(mp.getLevel(Skill.EXPLORATION)) + ChatColor.GOLD + "[" + ChatColor.GREEN + mp.getExp(Skill.EXPLORATION) + ChatColor.YELLOW + "/" + ChatColor.DARK_GREEN + ExpTable.valueOf("L" + Integer.toString(mp.getLevel(Skill.EXPLORATION) + 1)).getExp() + ChatColor.GOLD + "]");
        p.sendMessage("   ");
        p.sendMessage(ChatColor.GOLD + "[" + ChatColor.LIGHT_PURPLE + "Total Level" + ChatColor.GOLD + "]" + "   " + ChatColor.GOLD + "[" + ChatColor.YELLOW + mp.getAll() + ChatColor.GOLD + "]");

        //Unload from map again IF PLAYER IS OFFLINE. IDIOT.
        if (!p.isOnline()) new RemoveFromMap(p);

        return true;
    }

    private String fitString(int i1) {
        int remove = Integer.toString(i1).length() * 2;
        String string = "            ";
        for (int i = 0; i < remove; i++) {
            string = string.replaceFirst(" ", "");
        }

        return string;
    }

    private void sendString(String s) {
        p.sendMessage(ChatColor.GOLD + "[" + ChatColor.LIGHT_PURPLE + "S" + ChatColor.GOLD + "] " + ChatColor.YELLOW + s);
    }
}

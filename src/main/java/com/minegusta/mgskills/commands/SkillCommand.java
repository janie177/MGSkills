package com.minegusta.mgskills.commands;

import com.google.common.collect.Lists;
import com.minegusta.mgskills.files.DetailedMPlayer;
import com.minegusta.mgskills.skills.SkillInfo;
import com.minegusta.mgskills.struct.ISkill;
import com.minegusta.mgskills.util.ExpTable;
import com.minegusta.mgskills.util.SkillFromString;
import com.minegusta.mgskills.util.TempData;
import com.minegusta.mgskills.util.YamlUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
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
                if (args[0].equalsIgnoreCase("show")) {
                    sendStats(p.getUniqueId());
                    return true;
                }

                if (args[0].equalsIgnoreCase("info")) {
                    sendList(SkillInfo.SKILLS.getInfo());
                    return true;
                }
            }
            if (args.length == 2) {
                if (args[0].equalsIgnoreCase("show")) {
                    try {
                        OfflinePlayer oPlayer = Bukkit.getOfflinePlayer(args[1]);
                        if (sendStats(oPlayer.getUniqueId())) return true;
                        sendString("That player cannot be found! They might not exist or never played.");
                        return true;

                    } catch (Exception ignored) {
                        sendString("That player cannot be found! They might not exist or never played.");
                    }
                    return true;

                }

                if (args[0].equalsIgnoreCase("info")) {
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
            if (SkillFromString.valueOf(skill.toLowerCase()).name() == null) {
                sendList(SkillInfo.SKILLS.getInfo());
                return;
            }
        } catch (Exception ignored) {
            sendList(SkillInfo.HELP.getInfo());
        }

        DetailedMPlayer mPlayer = TempData.pMap.get(p.getUniqueId());
        ISkill skillClass = SkillFromString.valueOf(skill.toLowerCase()).getSkill();
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
        if (TempData.pMap.containsKey(uuid)) {
            mp = TempData.pMap.get(uuid);
        } else {
            if (!YamlUtil.exists("/players/", uuid.toString() + ".yml")) return false;
            mp = new DetailedMPlayer(YamlUtil.getConfiguration("/players/", uuid.toString() + ".yml"), uuid);
        }

        p.sendMessage(ChatColor.YELLOW + "-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
        p.sendMessage(ChatColor.RED + "           " + ChatColor.BOLD + Bukkit.getPlayer(uuid).getName() + "'s Skills");
        p.sendMessage("   ");
        p.sendMessage(ChatColor.GRAY + "  Skill            Level      Experience/NextLevel");
        p.sendMessage(ChatColor.YELLOW + "-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
        p.sendMessage(ChatColor.GOLD + "[" + ChatColor.LIGHT_PURPLE + "Fishing" + ChatColor.GOLD + "]" + "          " + ChatColor.GOLD + "[" + ChatColor.YELLOW + mp.getFishingLevel() + ChatColor.GOLD + "]" + "   " + ChatColor.GRAY + fitString(mp.getFishingLevel()) + ChatColor.GOLD + "[" + ChatColor.GREEN + mp.getFishing() + ChatColor.YELLOW + "/" + ChatColor.DARK_GREEN + ExpTable.valueOf("L" + Integer.toString(mp.getFishingLevel() + 1)).getExp() + ChatColor.GOLD + "]");
        p.sendMessage(ChatColor.GOLD + "[" + ChatColor.LIGHT_PURPLE + "Mining" + ChatColor.GOLD + "]" + "           " + ChatColor.GOLD + "[" + ChatColor.YELLOW + mp.getMiningLevel() + ChatColor.GOLD + "]" + "   " + ChatColor.GRAY + fitString(mp.getMiningLevel()) + ChatColor.GOLD + "[" + ChatColor.GREEN + mp.getMining() + ChatColor.YELLOW + "/" + ChatColor.DARK_GREEN + ExpTable.valueOf("L" + Integer.toString(mp.getMiningLevel() + 1)).getExp() + ChatColor.GOLD + "]");
        p.sendMessage(ChatColor.GOLD + "[" + ChatColor.LIGHT_PURPLE + "Cooking" + ChatColor.GOLD + "]" + "         " + ChatColor.GOLD + "[" + ChatColor.YELLOW + mp.getCookingLevel() + ChatColor.GOLD + "]" + "   " + ChatColor.GRAY + fitString(mp.getCookingLevel()) + ChatColor.GOLD + "[" + ChatColor.GREEN + mp.getCooking() + ChatColor.YELLOW + "/" + ChatColor.DARK_GREEN + ExpTable.valueOf("L" + Integer.toString(mp.getCookingLevel() + 1)).getExp() + ChatColor.GOLD + "]");
        p.sendMessage(ChatColor.GOLD + "[" + ChatColor.LIGHT_PURPLE + "Summoning" + ChatColor.GOLD + "]" + "     " + ChatColor.GOLD + "[" + ChatColor.YELLOW + mp.getSummoningLevel() + ChatColor.GOLD + "]" + "   " + ChatColor.GRAY + fitString(mp.getSummoningLevel()) + ChatColor.GOLD + "[" + ChatColor.GREEN + mp.getSummoning() + ChatColor.YELLOW + "/" + ChatColor.DARK_GREEN + ExpTable.valueOf("L" + Integer.toString(mp.getSummoningLevel() + 1)).getExp() + ChatColor.GOLD + "]");
        p.sendMessage(ChatColor.GOLD + "[" + ChatColor.LIGHT_PURPLE + "Farming" + ChatColor.GOLD + "]" + "         " + ChatColor.GOLD + "[" + ChatColor.YELLOW + mp.getFarmingLevel() + ChatColor.GOLD + "]" + "   " + ChatColor.GRAY + fitString(mp.getFarmingLevel()) + ChatColor.GOLD + "[" + ChatColor.GREEN + mp.getFarming() + ChatColor.YELLOW + "/" + ChatColor.DARK_GREEN + ExpTable.valueOf("L" + Integer.toString(mp.getFarmingLevel() + 1)).getExp() + ChatColor.GOLD + "]");
        p.sendMessage(ChatColor.GOLD + "[" + ChatColor.LIGHT_PURPLE + "Hunting" + ChatColor.GOLD + "]" + "         " + ChatColor.GOLD + "[" + ChatColor.YELLOW + mp.getHuntingLevel() + ChatColor.GOLD + "]" + "   " + ChatColor.GRAY + fitString(mp.getHuntingLevel()) + ChatColor.GOLD + "[" + ChatColor.GREEN + mp.getHunting() + ChatColor.YELLOW + "/" + ChatColor.DARK_GREEN + ExpTable.valueOf("L" + Integer.toString(mp.getHuntingLevel() + 1)).getExp() + ChatColor.GOLD + "]");
        p.sendMessage(ChatColor.GOLD + "[" + ChatColor.LIGHT_PURPLE + "Woodcutting" + ChatColor.GOLD + "]" + "   " + ChatColor.GOLD + "[" + ChatColor.YELLOW + mp.getWoodcuttingLevel() + ChatColor.GOLD + "]" + "   " + ChatColor.GRAY + fitString(mp.getWoodcuttingLevel()) + ChatColor.GOLD + "[" + ChatColor.GREEN + mp.getWoodcutting() + ChatColor.YELLOW + "/" + ChatColor.DARK_GREEN + ExpTable.valueOf("L" + Integer.toString(mp.getWoodcuttingLevel() + 1)).getExp() + ChatColor.GOLD + "]");
        p.sendMessage(ChatColor.GOLD + "[" + ChatColor.LIGHT_PURPLE + "Digging" + ChatColor.GOLD + "]" + "         " + ChatColor.GOLD + "[" + ChatColor.YELLOW + mp.getDiggingLevel() + ChatColor.GOLD + "]" + "   " + ChatColor.GRAY + fitString(mp.getDiggingLevel()) + ChatColor.GOLD + "[" + ChatColor.GREEN + mp.getDigging() + ChatColor.YELLOW + "/" + ChatColor.DARK_GREEN + ExpTable.valueOf("L" + Integer.toString(mp.getDiggingLevel() + 1)).getExp() + ChatColor.GOLD + "]");
        p.sendMessage(ChatColor.GOLD + "[" + ChatColor.LIGHT_PURPLE + "Brewing" + ChatColor.GOLD + "]" + "        " + ChatColor.GOLD + "[" + ChatColor.YELLOW + mp.getBrewingLevel() + ChatColor.GOLD + "]" + "   " + ChatColor.GRAY + fitString(mp.getBrewingLevel()) + ChatColor.GOLD + "[" + ChatColor.GREEN + mp.getBrewing() + ChatColor.YELLOW + "/" + ChatColor.DARK_GREEN + ExpTable.valueOf("L" + Integer.toString(mp.getBrewingLevel() + 1)).getExp() + ChatColor.GOLD + "]");
        p.sendMessage(ChatColor.GOLD + "[" + ChatColor.LIGHT_PURPLE + "Healing" + ChatColor.GOLD + "]" + "         " + ChatColor.GOLD + "[" + ChatColor.YELLOW + mp.getHealingLevel() + ChatColor.GOLD + "]" + "   " + ChatColor.GRAY + fitString(mp.getHealingLevel()) + ChatColor.GOLD + "[" + ChatColor.GREEN + mp.getHealing() + ChatColor.YELLOW + "/" + ChatColor.DARK_GREEN + ExpTable.valueOf("L" + Integer.toString(mp.getHealingLevel() + 1)).getExp() + ChatColor.GOLD + "]");
        p.sendMessage(ChatColor.GOLD + "[" + ChatColor.LIGHT_PURPLE + "Exploration" + ChatColor.GOLD + "]" + "   " + ChatColor.GOLD + "[" + ChatColor.YELLOW + mp.getExplorationLevel() + ChatColor.GOLD + "]" + "   " + ChatColor.GRAY + fitString(mp.getExplorationLevel()) + ChatColor.GOLD + "[" + ChatColor.GREEN + mp.getExploration() + ChatColor.YELLOW + "/" + ChatColor.DARK_GREEN + ExpTable.valueOf("L" + Integer.toString(mp.getExplorationLevel() + 1)).getExp() + ChatColor.GOLD + "]");
        p.sendMessage("   ");
        p.sendMessage(ChatColor.GOLD + "[" + ChatColor.LIGHT_PURPLE + "Total Level" + ChatColor.GOLD + "]" + "   " + ChatColor.GOLD + "[" + ChatColor.YELLOW + mp.getAll() + ChatColor.GOLD + "]");
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
        p.sendMessage(ChatColor.RED + s);
    }
}

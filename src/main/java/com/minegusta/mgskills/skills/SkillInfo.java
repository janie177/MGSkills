package com.minegusta.mgskills.skills;

import org.bukkit.ChatColor;

public enum SkillInfo {
    SKILLS(new String[]{
            "Currently added skills:",
            ChatColor.GRAY + "Brewing, Cooking, Digging, Exploration",
            ChatColor.GRAY + "Farming, Fishing, Healing, Hunting",
            ChatColor.GRAY + "Mining, Magic, WoodCutting"
    }),
    HELP(new String[]{" - /Skills" + ChatColor.GRAY + " - Show this help menu.", " - /Skills Show" + ChatColor.GRAY + " - Show your own skills.", " - /Skills Show <Name>" + ChatColor.GRAY + " - Show the given players' skills.", " - /Skills info <Skill>" + ChatColor.GRAY + " - Show information for the given skill.", " - /HighScores" + ChatColor.GRAY + " - Show the top 10 players.", " - /<SkillName>" + ChatColor.GRAY + " - Display specific skill information.", " - /Skill Toggle" + ChatColor.GRAY + " - Show when you earn experience in the chat."}),
    FISHING(new String[]{
            ChatColor.GRAY + "Fishing is all about catching fish. Experience is given when you catch something.",
            ChatColor.GRAY + "Bonus experience is awarded when you catch loot.",
            ChatColor.GOLD + "Bonuses Per Level:",
            " - " + ChatColor.GREEN + "1: " + ChatColor.GRAY + "You can now catch tier 1 junk now.",
            " - " + ChatColor.GREEN + "20: " + ChatColor.GRAY + "You can now catch tier 2 loot.",
            " - " + ChatColor.GREEN + "40: " + ChatColor.GRAY + "You can now catch tier 3 loot.",
            " - " + ChatColor.GREEN + "60: " + ChatColor.GRAY + "You can now catch tier 4 loot.",
            " - " + ChatColor.GREEN + "68: " + ChatColor.GRAY + "You can now catch treasure maps.",
            " - " + ChatColor.GREEN + "80: " + ChatColor.GRAY + "You can now catch tier 5 loot.",
            " - " + ChatColor.GREEN + "100: " + ChatColor.GRAY + "You can now catch tier 6 loot.",
            ChatColor.GOLD + "Bonuses That Increase Per Level:",
            " - " + ChatColor.GRAY + "You gain " + ChatColor.RED + "1%" + ChatColor.GRAY + " chance for a double catch per level."
    }),
    MINING(new String[]{
            ChatColor.GRAY + "Mining experience is gained when mining stone or ores that break.",
            ChatColor.GRAY + "Ores like iron and gold do not give extra experience.",
            ChatColor.GRAY + "Smelting is also integrated in mining.",
            ChatColor.GRAY + "Your level is the % chance for bonus smelting loot.",
            ChatColor.GRAY + "You will only get 1 exp for mining ores with silk touch.",
            ChatColor.GRAY + "You start with a 0.5% chance to randomly find ore.",
            ChatColor.GOLD + "Bonuses Per Level:",
            " - " + ChatColor.GREEN + "1: " + ChatColor.GRAY + "You can randomly find coal.",
            " - " + ChatColor.GREEN + "10: " + ChatColor.GRAY + "You can randomly find quartz.",
            " - " + ChatColor.GREEN + "30: " + ChatColor.GRAY + "You can randomly find redstone.",
            " - " + ChatColor.GREEN + "40: " + ChatColor.GRAY + "You can randomly find gold.",
            " - " + ChatColor.GREEN + "55: " + ChatColor.GRAY + "You can randomly find iron.",
            " - " + ChatColor.GREEN + "75: " + ChatColor.GRAY + "You can randomly find lapis lazuli.",
            " - " + ChatColor.GREEN + "80: " + ChatColor.GRAY + "You can randomly find emeralds.",
            " - " + ChatColor.GREEN + "90: " + ChatColor.GRAY + "You can randomly find diamonds.",
            " - " + ChatColor.GREEN + "100: " + ChatColor.GRAY + "Your last torch never depletes.",
            ChatColor.GOLD + "Bonuses That Increase Per Level:",
            " - " + ChatColor.GRAY + "You gain " + ChatColor.RED + "0.05%" + ChatColor.GRAY + " chance for finding ore while mining stone."
    }),
    COOKING(new String[]{
            ChatColor.GRAY + "Cooking is trained by making all kinds of food.",
            ChatColor.GRAY + "Examples are cooking fish, or crafting cake.",
            ChatColor.GRAY + "When using a furnace, you have to stand near it.",
            ChatColor.GRAY + "Everyone in a radius of 15 from the furnace gets experience.",
            ChatColor.GOLD + "Bonuses Per Level:",
            " - " + ChatColor.GREEN + "25: " + ChatColor.GRAY + "Eating food fills half a food slot extra.",
            " - " + ChatColor.GREEN + "50: " + ChatColor.GRAY + "Eating food fills 2 extra food slots.",
            " - " + ChatColor.GREEN + "68: " + ChatColor.GRAY + "Eating cake gives you a speed boost.",
            " - " + ChatColor.GREEN + "75: " + ChatColor.GRAY + "Eating food fills 4 more food slots.",
            " - " + ChatColor.GREEN + "100: " + ChatColor.GRAY + "Eating any food fully fills your food bar.",
            ChatColor.GOLD + "Bonuses That Increase Per Level:",
            " - " + ChatColor.GRAY + "You gain " + ChatColor.RED + "1%" + ChatColor.GRAY + " chance to get a bonus item when cooking."
    }),
    FARMING(new String[]{
            ChatColor.GRAY + "'Get of me lawn yer pesky kids!' is a term you will often use as a farmer!",
            ChatColor.GRAY + "Plant crops, grow melons, shear sheep... there are lots of ways to gain experience.",
            ChatColor.GRAY + "Milking cows/Mooshrooms also awards experience.",
            ChatColor.GRAY + "Harvesting pumpkins does not give experience, though breaking a full grown stem does.",
            ChatColor.GRAY + "The double loot bonus per level count for: crops, carrots, potatoes, netherwarts and cocoa beans.",
            ChatColor.GOLD + "Bonuses Per Level:",
            " - " + ChatColor.GREEN + "10: " + ChatColor.GRAY + "Shearing sheep gives twice as much wool.",
            " - " + ChatColor.GREEN + "18: " + ChatColor.GRAY + "Milking cows now stacks the buckets.",
            " - " + ChatColor.GREEN + "34: " + ChatColor.GRAY + "Mushroom cows give twice as much soup when using a bowl.",
            " - " + ChatColor.GREEN + "44: " + ChatColor.GRAY + "Apples drop twice as much from leaves.",
            " - " + ChatColor.GREEN + "62: " + ChatColor.GRAY + "When breaking crops with a hoe, they automatically replant.",
            " - " + ChatColor.GREEN + "75: " + ChatColor.GRAY + "Saplings grow when right-clicking them.",
            " - " + ChatColor.GREEN + "100: " + ChatColor.GRAY + "When breaking leaves, there's a 0.25% chance they drop a golden apple.",
            ChatColor.GOLD + "Bonuses That Increase Per Level:",
            " - " + ChatColor.GRAY + "You gain " + ChatColor.RED + "2%" + ChatColor.GRAY + " chance for double harvest each level.",
            " - " + ChatColor.GRAY + "When this reaches 100%, the extra % becomes the chance for triple harvest."
    }),
    HUNTING(new String[]{
            ChatColor.GRAY + "Hunting involves killing innocent creatures, like sheep and creepers.",
            ChatColor.GRAY + "The harder/rarer a mob is, the more experience you get.",
            ChatColor.GRAY + "Bosses are an exception on double loot.",
            ChatColor.GRAY + "Looting swords will disable the triple loot boost.",
            ChatColor.GOLD + "Bonuses Per Level:",
            " - " + ChatColor.GREEN + "15: " + ChatColor.GRAY + "Sheep always drop more wool now.",
            " - " + ChatColor.GREEN + "22: " + ChatColor.GRAY + "When your wolf kills something, you gain experience.",
            " - " + ChatColor.GREEN + "38: " + ChatColor.GRAY + "All animals drop meat now.",
            " - " + ChatColor.GREEN + "50: " + ChatColor.GRAY + "All mobs drop double loot.",
            " - " + ChatColor.GREEN + "64: " + ChatColor.GRAY + "When killing something, you gain a little life back.",
            " - " + ChatColor.GREEN + "72: " + ChatColor.GRAY + "You can summon a wolf every 15 minutes using " + ChatColor.RED + "/Wolf" + ChatColor.LIGHT_PURPLE + ".",
            " - " + ChatColor.GREEN + "100: " + ChatColor.GRAY + "All mobs drop triple loot.",
            ChatColor.GOLD + "Bonuses That Increase Per Level:",
            " - " + ChatColor.GRAY + "Animals have a " + ChatColor.RED + "0.25%" + ChatColor.GRAY + " chance per level to re-spawn when killed."
    }),
    WOODCUTTING(new String[]{
            ChatColor.GRAY + "Woodcutting is all about collecting logs.",
            ChatColor.GRAY + "Unleash the lumberjack in you by chopping nothing but logs!",
            ChatColor.GRAY + "Experience and boosts are only gained when wielding an axe.",
            ChatColor.GOLD + "Bonuses Per Level:",
            " - " + ChatColor.GREEN + "20: " + ChatColor.GRAY + "You now have a speed boost while cutting logs.",
            " - " + ChatColor.GREEN + "40: " + ChatColor.GRAY + "Your speed boost is increased by 1.",
            " - " + ChatColor.GREEN + "45: " + ChatColor.GRAY + "You have a 0.33% chance to find bird nests.",
            " - " + ChatColor.GREEN + "60: " + ChatColor.GRAY + "Your speed boost is increased by 1.",
            " - " + ChatColor.GREEN + "72: " + ChatColor.GRAY + "You destroyed lots of bird nests while woodcutting.",
            "   " + ChatColor.GREEN + "     " + ChatColor.GRAY + "This made you the master of birds.",
            "   " + ChatColor.GREEN + "     " + ChatColor.GRAY + "Summon exploding bird minions using a",
            "   " + ChatColor.GREEN + "     " + ChatColor.GRAY + "blaze rod. This has a 20 second cool down.",
            " - " + ChatColor.GREEN + "80: " + ChatColor.GRAY + "Your speed boost is increased by 1.",
            " - " + ChatColor.GREEN + "100: " + ChatColor.GRAY + "Your speed boost is increased by 1.",
            ChatColor.GOLD + "Bonuses That Increase Per Level:",
            " - " + ChatColor.GRAY + "You gain " + ChatColor.RED + "1" + ChatColor.GRAY + " extra woodcutting speed every 20 levels."
    }),
    DIGGING(new String[]{
            ChatColor.GRAY + "Are you a true mole or maus? Get your shovel and prove it!",
            ChatColor.GRAY + "Dig dirt, gravel sand or any other light substances to gain experience.",
            ChatColor.GOLD + "Bonuses Per Level:",
            " - " + ChatColor.GREEN + "15: " + ChatColor.GRAY + "You have a 20% chance to get extra flint when digging gravel.",
            " - " + ChatColor.GREEN + "30: " + ChatColor.GRAY + "You can now find lost iron tools in the dirt.",
            " - " + ChatColor.GREEN + "60: " + ChatColor.GRAY + "You have a chance to dig up treasure maps.",
            " - " + ChatColor.GREEN + "76: " + ChatColor.GRAY + "Grass no longer becomes dirt when digging it.",
            " - " + ChatColor.GREEN + "100: " + ChatColor.GRAY + "Shovels will never break.",
            ChatColor.GOLD + "Bonuses That Increase Per Level:",
            " - " + ChatColor.GRAY + "You gain " + ChatColor.RED + "+ 1" + ChatColor.GRAY + " bonus experience for every 5 levels.",
            " - " + ChatColor.GRAY + "The chance to find treasure maps increases each level."
    }),
    MAGIC(new String[]{
            ChatColor.GRAY + "The arcane arts offer many advantages in pvp and pve.",
            ChatColor.GRAY + "Cast spells to gain experience and unlock more spells.",
            ChatColor.GRAY + "Spells cost mana to cast. Some spells require extra items.",
            ChatColor.GOLD + "Bonuses Per Level:",
            " - " + ChatColor.GREEN + "Use the /spells command to see a list of available spells.",
            ChatColor.GOLD + "Bonuses That Increase Per Level:",
            " - " + ChatColor.DARK_PURPLE + "You gain " + ChatColor.DARK_AQUA + "+ 1" + ChatColor.DARK_PURPLE + " mana per level."
    }),
    BREWING(new String[]{
            ChatColor.GRAY + "The art of potion making.",
            ChatColor.GRAY + "Brewing is an unique skill that allows you to experiment.",
            ChatColor.GRAY + "Brew potions and throw them to gain experience.",
            ChatColor.GRAY + "When you level up, you will be able to make custom brews.",
            ChatColor.GRAY + "Use the" + ChatColor.RED + " /alchemy " + ChatColor.GRAY + "command for a list of recipes.",
            ChatColor.GRAY + "Custom potions are made in a brewing lab. Make one by",
            ChatColor.GRAY + "placing a cauldron on a coal block.",
            ChatColor.GOLD + "Bonuses Per Level (do not apply to custom potions):",
            " - " + ChatColor.GREEN + "38: " + ChatColor.GRAY + "20% chance to get brewing ingredients back when brewing.",
            " - " + ChatColor.GREEN + "58: " + ChatColor.GRAY + "You take 50% less poison damage.",
            " - " + ChatColor.GREEN + "72: " + ChatColor.GRAY + "You can now stack potions by clicking them.",
            " - " + ChatColor.GREEN + "82: " + ChatColor.GRAY + "50% chance to brew two potions at once.",
            " - " + ChatColor.GREEN + "100: " + ChatColor.GRAY + "When drinking potions, effects last twice as long.",
            ChatColor.GOLD + "Bonuses That Increase Per Level:",
            " - " + ChatColor.GRAY + "You gain " + ChatColor.RED + "3%" + ChatColor.GRAY + " chance to clone potions on throwing them.",
    }),
    HEALING(new String[]{
            ChatColor.GRAY + "Healing is all about teamwork.",
            ChatColor.GRAY + "Heal other players and creatures by bandaging their wounds using paper.",
            ChatColor.GOLD + "Bonuses Per Level:",
            " - " + ChatColor.GREEN + "50: " + ChatColor.GRAY + "You can now heal beings in a radius of 2 around you.",
            " - " + ChatColor.GREEN + "63: " + ChatColor.GRAY + "People you heal will get a short protecting effect.",
            " - " + ChatColor.GREEN + "72: " + ChatColor.GRAY + "You now also heal yourself when healing another being.",
            " - " + ChatColor.GREEN + "80: " + ChatColor.GRAY + "Players you heal will get a short speed boost.",
            " - " + ChatColor.GREEN + "88: " + ChatColor.GRAY + "You can heal creatures in a radius of 3 around you.",
            " - " + ChatColor.GREEN + "95: " + ChatColor.GRAY + "You can heal creatures in a radius of 6 around you.",
            " - " + ChatColor.GREEN + "100: " + ChatColor.GRAY + "Left clicking the air with paper will cause a healing storm.",
            ChatColor.GOLD + "Bonuses That Increase Per Level:",
            " - " + ChatColor.GRAY + "You gain " + ChatColor.RED + "+ 1" + ChatColor.GRAY + " healing bonus every 5 levels.",
    }),
    EXPLORATION(new String[]{ChatColor.YELLOW + "This skill will be released in update 2!", ChatColor.YELLOW + "Send suggestions towards Jan!"});
    private String[] info;

    private SkillInfo(String[] info) {
        this.info = info;
    }

    public String[] getInfo() {
        return info;
    }
}

package com.minegusta.mgskills.listeners;

import com.minegusta.mgskills.Main;
import com.minegusta.mgskills.files.DetailedMPlayer;
import com.minegusta.mgskills.files.LoadToMap;
import com.minegusta.mgskills.files.RemoveFromMap;
import com.minegusta.mgskills.skills.brewing.PotionBounce;
import com.minegusta.mgskills.skills.brewing.PotionExperience;
import com.minegusta.mgskills.skills.brewing.custombrewing.BrewingData;
import com.minegusta.mgskills.skills.brewing.custombrewing.BrewingLab;
import com.minegusta.mgskills.skills.brewing.custombrewing.BrewingProcess;
import com.minegusta.mgskills.skills.cooking.CakeEatBoost;
import com.minegusta.mgskills.skills.cooking.CookingCraftExperience;
import com.minegusta.mgskills.skills.cooking.CookingSmeltExperience;
import com.minegusta.mgskills.skills.cooking.FoodBoost;
import com.minegusta.mgskills.skills.digging.DiggingBoost;
import com.minegusta.mgskills.skills.farming.FarmingInteractBlockExperience;
import com.minegusta.mgskills.skills.farming.FarmingInteractEntityExperience;
import com.minegusta.mgskills.skills.fishing.FishingExp;
import com.minegusta.mgskills.skills.fishing.FishingLoot;
import com.minegusta.mgskills.skills.healing.HealPlayer;
import com.minegusta.mgskills.skills.hunting.HuntingExperience;
import com.minegusta.mgskills.skills.mining.RandomOreBoost;
import com.minegusta.mgskills.skills.mining.SmeltingExperience;
import com.minegusta.mgskills.skills.woodcutting.BirdNestBoost;
import com.minegusta.mgskills.skills.woodcutting.SuicideChicken;
import com.minegusta.mgskills.treasuremaps.TreasureListener;
import com.minegusta.mgskills.util.*;
import com.minegusta.mgskills.util.checks.BlockUtil;
import com.minegusta.mgskills.util.checks.ExperienceUtil;
import com.minegusta.mgskills.util.checks.ItemUtil;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPistonExtendEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PotionSplashEvent;
import org.bukkit.event.inventory.*;
import org.bukkit.event.player.*;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class SkillListener implements Listener {
    @EventHandler(priority = EventPriority.MONITOR)
    public void onEvent(PlayerJoinEvent e) {
        //Always do this, no matter the world.

        new LoadToMap(e);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onEvent(PlayerQuitEvent e) {
        //Always do this, no matter the world.

        new RemoveFromMap(e);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onEvent(PlayerFishEvent e) {
        if (!worldCheck(e.getPlayer().getWorld()) || e.isCancelled()) return;

        Player p = e.getPlayer();
        DetailedMPlayer mp = TempData.getMPlayer(p);
        int level = mp.getLevel(Skill.FISHING);

        /** Fishing **/
        if (e.getState().equals(PlayerFishEvent.State.CAUGHT_FISH)) {
            if (e.getCaught() instanceof Item) {
                ItemStack is = ((Item) e.getCaught()).getItemStack();

                //Check for feeeesh
                if (is.getType().equals(Material.RAW_FISH)) {
                    int exp = FishingExp.getExp(is);

                    //Check for bonus loot
                    if (RandomNumber.get(4) == 1) {
                        SendMessage.send(p, "You found some random loot while fishing!");
                        p.getWorld().dropItemNaturally(p.getLocation(), FishingLoot.get(level));
                        exp = exp + 160;
                    }

                    //Treasure map
                    if (level > 67 && RandomNumber.get(510) == 1) {
                        exp = exp + 1000;
                        FishingLoot.giveTreasureMap(p);
                    }

                    //Feeeeesh
                    if (RandomNumber.get(100) > level) {
                        exp = exp + 50;
                        FishingLoot.doubleCatch(is);
                    }
                    mp.addExp(Skill.FISHING, exp);
                }
            }
        }
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onEvent(EntityDamageEvent e) {
        if (!worldCheck(e.getEntity().getWorld()) || e.isCancelled()) return;

        /** Poison potion brewing boost **/

        if (e.getEntity() instanceof Player && e.getCause() != null && e.getCause().equals(EntityDamageEvent.DamageCause.POISON)) {
            DetailedMPlayer mp = TempData.getMPlayer((Player) e.getEntity());
            int level = mp.getLevel(Skill.BREWING);
            String uuid = e.getEntity().getUniqueId().toString();

            if (level > 57) {
                if (TempData.poisonMap.containsKey(uuid)) {
                    TempData.poisonMap.remove(uuid);
                    e.setDamage(0);
                }
                else TempData.poisonMap.put(uuid, true);
            }
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onEvent(BlockBreakEvent e) {
        if (!worldCheck(e.getPlayer().getWorld()) || e.isCancelled()) return;

        //Data
        DetailedMPlayer mp = TempData.getMPlayer(e.getPlayer());
        int exp;
        int level = mp.getLevel(Skill.MINING);
        Player p = e.getPlayer();
        Block b = e.getBlock();

        /**Mining**/
        exp = ExperienceUtil.getMiningExp(e.getBlock().getType(), ItemUtil.hasSilkTouch(p.getItemInHand()));
        //Applying experience and random ore boost
        if (exp != 0) {
            if (b.getType().equals(Material.STONE) && RandomNumber.get(10000) < (50 + (level * 5))) {
                RandomOreBoost.drop(p, mp);
            }
            mp.addExp(Skill.MINING, exp);
        }
        //Torches at level 100+
        if (exp == 0 && b.getType().equals(Material.TORCH) && level > 99) {
            if (p.getInventory().contains(Material.TORCH)) b.setType(Material.AIR);
        }


        /**Woodcutting**/

        boolean isAxe = ItemUtil.isAxe(p.getItemInHand());
        level = mp.getLevel(Skill.WOODCUTTING);
        if (BlockUtil.isLog(b)) {
            //base exp
            mp.addExp(Skill.WOODCUTTING, 25);

            //Woodcutting speed boost
            if (level > 19 && isAxe) {
                int amplifier = level / 20;
                for (PotionEffect pe : p.getActivePotionEffects()) {
                    if (pe.getType().equals(PotionEffectType.FAST_DIGGING) && pe.getAmplifier() <= amplifier - 1) {
                        p.removePotionEffect(PotionEffectType.FAST_DIGGING);
                    }
                }
                p.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 30, (amplifier - 1)));
            }

            //Bird nest check
            if (level > 44 && isAxe && RandomNumber.get(300) == 1) {
                new BirdNestBoost(p, b).runNest();
                mp.addExp(Skill.WOODCUTTING, 100);
            }
        }

        /**Digging**/
        exp = ExperienceUtil.getDiggingExp(b);
        level = mp.getLevel(Skill.DIGGING);

        if (exp != 0) {
            //Add base exp
            mp.addExp(Skill.DIGGING, exp + level / 5);

            //Check for bonuses
            DiggingBoost.check(p, level, b);
        }

        /**Farming**/
        level = mp.getLevel(Skill.FARMING);
        exp = ExperienceUtil.getFarmingExp(b, ItemUtil.hasSilkTouch(p.getItemInHand()));

        //Applying exp and replant boost
        if (exp != 0) {
            //Try replanting
            boolean fullGrown = BlockUtil.tryReplant(level, ItemUtil.isHoe(p.getItemInHand()), b);

            //Boost the exp
            if (fullGrown) {
                if (RandomNumber.get(100) <= level * 2) {
                    exp = exp * 2;
                    for (ItemStack is : b.getDrops()) {
                        b.getWorld().dropItemNaturally(b.getLocation(), is);
                    }
                }

                if (level * 2 > 100) {
                    if (RandomNumber.get(100) <= (mp.getLevel(Skill.FARMING) * 2) - 100) {
                        for (ItemStack is : b.getDrops()) {
                            b.getWorld().dropItemNaturally(b.getLocation(), is);
                        }
                    }
                }
            }

            //apply exp
            mp.addExp(Skill.FARMING, exp);
        }

        //Apple boost
        if (BlockUtil.isLeaves(b) && level > 43 && RandomNumber.get(25) == 1) {
            if (RandomNumber.get(5) == 1 && level > 99) {
                b.getWorld().dropItemNaturally(b.getLocation(), new ItemStack(Material.GOLDEN_APPLE, 1));
            } else {
                b.getWorld().dropItemNaturally(b.getLocation(), new ItemStack(Material.APPLE, 1));
            }
        }

        /** Custom Brewing Check **/
        if (BrewingData.hasBrewingLab(b.getLocation())) {
            SendMessage.send(p, "You cancelled brewing a potion by breaking the cauldron.", "Some gasses escape and cause an explosion!");
            BrewingData.getBrew(b.getLocation()).cancelBrew();
            b.getWorld().createExplosion(b.getLocation(), 4, false);
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onEvent(BlockPlaceEvent e) {
        if (!worldCheck(e.getPlayer().getWorld()) || e.isCancelled()) return;

        DetailedMPlayer mp = TempData.getMPlayer(e.getPlayer());
        Block b = e.getBlock();

        //Infinite torch placement
        if (b.getType().equals(Material.TORCH) && mp.getLevel(Skill.MINING) > 99) {
            if (!e.getPlayer().getInventory().contains(Material.TORCH))
                e.getPlayer().getInventory().addItem(new ItemStack(Material.TORCH, 1));
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onEvent(PlayerInteractEvent e) {

        //Automatically cancelled for right click air. Make sure to check this seperately for the boosts.
        if (!worldCheck(e.getPlayer().getWorld())) return;

        //Data
        Player p = e.getPlayer();
        DetailedMPlayer mp = TempData.getMPlayer(p);
        ItemStack is = e.getPlayer().getItemInHand();
        Material m = null;
        if (e.hasBlock()) {
            m = e.getClickedBlock().getType();
        }
        Material hand = is.getType();

        //Skill checks

        /**treasure**/
        if (hand.equals(Material.MAP) && is.hasItemMeta() && is.getItemMeta().hasLore() && is.getItemMeta().getLore().size() == 6 && is.getItemMeta().getLore().get(5).equalsIgnoreCase(ChatColor.GRAY + "Rightclick map at location.")) {
            new TreasureListener(e);
        }

        /**Chicken boom**/
        new SuicideChicken(e);

        /**Cake food boost**/
        if (!e.isCancelled() && e.hasBlock() && (m.equals(Material.CAKE_BLOCK) || m.equals(Material.CAKE)) && mp.getLevel(Skill.COOKING) > 67 && mp.getPlayer().getFoodLevel() < 20) {
            new CakeEatBoost(e);
        }

        /** Healing Storm **/
        int level = mp.getLevel(Skill.HEALING);

        if (level > 99 && hand.equals(Material.PAPER) && e.getAction().equals(Action.LEFT_CLICK_AIR)) {
            int coolDownTime = 300; //Seconds

            if (!CoolDown.cooledDown(p.getUniqueId().toString(), TempData.healStormMap, coolDownTime)) {
                SendMessage.send(p, "You cannot call a healing storm yet!", "You have to wait another " + (coolDownTime - CoolDown.getRemainingTime(p.getUniqueId().toString(), TempData.healStormMap)) + " seconds.");
            } else {
                int exp = 500;

                CoolDown.newCooldown(p.getUniqueId().toString(), TempData.healStormMap);
                mp.addExp(Skill.HEALING, exp);
                SendMessage.send(p, "You launch a healing storm at your location!!");

                for (int i = 0; i <= 10 * 20; i++) {
                    final Player player = p;

                    if (i % 5 == 0) {
                        Bukkit.getScheduler().scheduleSyncDelayedTask(Main.PLUGIN, new Runnable() {
                            @Override
                            public void run() {
                                player.getWorld().spigot().playEffect(player.getLocation().add(0, 9, 0), Effect.HEART, 0, 0, 2, 1, 2, 1, 65, 15);
                                ThrownPotion potion = (ThrownPotion) player.getWorld().spawnEntity(player.getLocation().add(RandomNumber.get(3) - 2, 9, RandomNumber.get(3) - 2), EntityType.SPLASH_POTION);
                                potion.setItem(new ItemStack(Material.POTION, 1, (short) 16389));
                                potion.setShooter(player);
                            }
                        }, i);
                    }
                }
            }
        }

        /** Custom Potion brewing **/

        if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK) && m.equals(Material.CAULDRON)) {
            if (BrewingLab.isLab(e.getClickedBlock())) {
                if (BrewingData.hasBrewingLab(e.getClickedBlock().getLocation())) {
                    SendMessage.send(p, "This lab is busy. Opening/Breaking it would be unsafe.");
                } else {
                    Inventory inv = Bukkit.createInventory(null, 9, ChatColor.DARK_RED + "Brewing Lab");
                    p.openInventory(inv);
                    BrewingData.brewInvMap.put(p.getName(), e.getClickedBlock());
                }
            }


        }


        /**Farming**/
        level = mp.getLevel(Skill.FARMING);

        //Farming tree planting grow hand thing
        if (!e.isCancelled() && e.hasBlock() && e.getAction().equals(Action.RIGHT_CLICK_BLOCK) && e.getClickedBlock().getType().equals(Material.SAPLING) && level > 74) {
            if (FarmingInteractBlockExperience.makeTree(e.getClickedBlock())) {
                mp.addExp(Skill.FARMING, 48);
            }
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onEvent(InventoryCloseEvent e) {
        if (!worldCheck(e.getPlayer().getWorld())) return;

        Player p = (Player) e.getPlayer();
        String name = e.getPlayer().getName();
        Inventory inv = e.getInventory();

        /** Brewing skill **/
        if (BrewingData.brewInvMap.containsKey(name) && inv.getName().equals(ChatColor.DARK_RED + "Brewing Lab")) {
            Block b = BrewingData.brewInvMap.remove(name);
            int level = TempData.getMPlayer(p).getLevel(Skill.BREWING);

            int length = 0;
            for(ItemStack i : inv.getContents())
            {
                if(!(i == null) && !i.getType().equals(Material.AIR))length++;
            }

            int[][] is = new int[length][3];

            int count = 0;
            for (ItemStack i : inv.getContents())
            {
                if(!(i == null) && !i.getType().equals(Material.AIR)) {
                    is[count] = new int[]{i.getTypeId(), i.getAmount(), i.getDurability()};
                    count++;
                }
            }
            if (BrewingLab.isLab(b))
            {
                //Start brewing
                SendMessage.send(p, new BrewingProcess(b, is, level).start());
            }
        }
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onEvent(InventoryClickEvent e) {
        if (!worldCheck(e.getWhoClicked().getWorld())) return;

        /** Stacking Potions Brewing **/
        if (e.getCurrentItem() != null && e.getCursor() != null && e.getCursor().getType().equals(Material.POTION) && e.getCurrentItem().getType().equals(Material.POTION) && e.getCurrentItem().getDurability() == e.getCursor().getDurability()) {
            DetailedMPlayer mp = TempData.getMPlayer((Player) e.getWhoClicked());
            int level = mp.getLevel(Skill.BREWING);

            if (level > 71) {
                int amount = e.getCurrentItem().getAmount();
                int newAmount = amount + e.getCursor().getAmount();
                if (newAmount > 64) {
                    e.getCurrentItem().setAmount(newAmount - 64);
                    e.getCursor().setAmount(64);
                } else {
                    e.getCursor().setAmount(newAmount);
                    e.setCurrentItem(new ItemStack(Material.AIR));
                }
                ((Player) e.getWhoClicked()).updateInventory();
            }
        }

    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onEvent(BlockPistonExtendEvent e) {
        if (!worldCheck(e.getBlock().getWorld()) || e.isCancelled()) return;

        /** checking for broken Brewingstands **/
        Block b = e.getBlock();

        if (b.getType().equals(Material.CAULDRON_ITEM) && BrewingData.hasBrewingLab(b.getLocation())) {
            BrewingData.getBrew(b.getLocation()).cancelBrew();
            b.getWorld().createExplosion(b.getLocation(), 4, false);

        }

    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onEvent(FurnaceSmeltEvent e) {
        if (!worldCheck(e.getBlock().getWorld()) || e.isCancelled()) return;

        //Cooking
        new CookingSmeltExperience(e);

        //Mining
        new SmeltingExperience(e);

    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onEvent(CraftItemEvent e) {
        if (!worldCheck(e.getWhoClicked().getWorld()) || e.isCancelled()) return;
        new CookingCraftExperience(e);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onEvent(PlayerItemConsumeEvent e) {
        if (!worldCheck(e.getPlayer().getWorld()) || e.isCancelled()) return;
        new FoodBoost(e);

        /** Potions at level 100 **/

        DetailedMPlayer mp = TempData.getMPlayer(e.getPlayer());
        if (e.getItem().getType().equals(Material.POTION) && mp.getLevel(Skill.BREWING) > 99) {
            try {
                Potion pot = Potion.fromItemStack(e.getItem());
                for (PotionEffect effect : pot.getEffects()) {
                    for (PotionEffect effect2 : e.getPlayer().getActivePotionEffects()) {
                        if (effect.getType().equals(effect2.getType())) {
                            e.getPlayer().removePotionEffect(effect2.getType());
                        }
                    }
                    e.getPlayer().addPotionEffect(new PotionEffect(effect.getType(), effect.getDuration() * 2, effect.getAmplifier(), false));
                }

            } catch (Exception ignored) {
            }
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onEvent(EntityDeathEvent e) {
        if (!worldCheck(e.getEntity().getWorld())) return;
        new HuntingExperience(e);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onEvent(BrewEvent e) {
        if (!worldCheck(e.getBlock().getWorld()) || e.isCancelled()) return;

        /** Brewing skill experience normal potions **/

        ItemStack pot = null;
        int exp = 0;
        int amount = 0;

        for (ItemStack i : e.getContents().getContents()) {
            if (i != null && !i.getType().equals(Material.AIR) && i.getType().equals(Material.POTION)) {
                exp = PotionExperience.getExperience(i);
                pot = i;
                amount++;
            }
        }
        if (exp > 0)
        {
            Entity temp = e.getBlock().getWorld().spawnEntity(e.getBlock().getLocation(), EntityType.EXPERIENCE_ORB);

            boolean itemReturned = false;

            for (Entity ent : temp.getNearbyEntities(15, 15, 15)) {
                if (ent instanceof Player)
                {
                    Player p = (Player) ent;
                    int level = TempData.getMPlayer(p).getLevel(Skill.BREWING);
                    if (level > 81 && RandomNumber.get(2) == 1 && pot.getAmount() < 64) {
                        pot.setAmount(pot.getAmount() + 1);
                        exp = exp * 2;
                    }
                    TempData.getMPlayer(p).addExp(Skill.BREWING, exp * amount);

                    /** Ingredient returnal **/

                    if (!itemReturned && RandomNumber.get(5) == 1 && TempData.getMPlayer((Player) ent).getLevel(Skill.BREWING) > 37) {
                        itemReturned = true;
                        ItemStack stack = e.getContents().getIngredient();
                        stack.setAmount(1);
                        e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation().add(0, 1, 0), stack);
                    }
                }
            }
            temp.remove();
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onEvent(PotionSplashEvent e) {
        if (!worldCheck(e.getPotion().getWorld())) return;

        /** Brewing splash potion **/
        if (e.getPotion().getShooter() != null && e.getPotion().getShooter() instanceof Player) {
            Player p = (Player) e.getEntity().getShooter();

            TempData.getMPlayer(p).addExp(Skill.BREWING, 25);

            DetailedMPlayer mp = TempData.getMPlayer(p);
            int level = mp.getLevel(Skill.BREWING);

            PotionBounce.bounce(level, e.getPotion());
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onEvent(PlayerInteractEntityEvent e) {
        if (!worldCheck(e.getPlayer().getWorld()) || e.isCancelled()) return;

        Entity clicked = e.getRightClicked();
        EntityType type = clicked.getType();
        Material hand = e.getPlayer().getItemInHand().getType();
        Player p = e.getPlayer();
        DetailedMPlayer mp = TempData.getMPlayer(p);

        int exp = 0;

        /**Farming**/
        if (type.equals(EntityType.SHEEP) && hand.equals(Material.SHEARS)) {
            exp = FarmingInteractEntityExperience.woolBonus(e.getRightClicked());
        }


        if (type.equals(EntityType.MUSHROOM_COW) && hand.equals(Material.BOWL)) {
            exp = FarmingInteractEntityExperience.soupBonus(e.getPlayer());
        }


        if (type.equals(EntityType.COW) && hand.equals(Material.BUCKET)) {
            exp = FarmingInteractEntityExperience.milkBonus(e.getPlayer());
        }

        //Add experience

        if (exp != 0) {
            mp.addExp(Skill.FARMING, exp);
        }


        /** Healing Skill **/
        if (hand.equals(Material.PAPER) && clicked instanceof LivingEntity) {
            int coolDownTime = 35; //Seconds

            if (!CoolDown.cooledDown(p.getUniqueId().toString(), TempData.healMap, coolDownTime)) {
                SendMessage.send(p, "You cannot heal creatures yet!", "You have to wait another " + (coolDownTime - CoolDown.getRemainingTime(p.getUniqueId().toString(), TempData.healMap)) + " seconds.");
            } else {
                int level = mp.getLevel(Skill.HEALING);
                double amount = 1 + level / 5;
                int radius = 1;
                boolean healSelf = false;
                if (level > 49) radius = 2;
                if (level > 87) radius = 3;
                if (level > 94) radius = 6;
                if (level > 71) healSelf = true;

                boolean speed = level > 79;
                boolean protection = level > 62;

                exp = HealPlayer.healEntities(p, amount, radius, protection, speed, healSelf);
                CoolDown.newCooldown(p.getUniqueId().toString(), TempData.healMap);
                mp.addExp(Skill.HEALING, exp);
            }
        }
    }

    //World Check
    private boolean worldCheck(World world) {
        return WorldCheck.check(world.getName());
    }

}

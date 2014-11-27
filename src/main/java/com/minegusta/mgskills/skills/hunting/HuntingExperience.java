package com.minegusta.mgskills.skills.hunting;

import com.minegusta.mgskills.files.DetailedMPlayer;
import com.minegusta.mgskills.util.RandomNumber;
import com.minegusta.mgskills.util.Skill;
import com.minegusta.mgskills.util.TempData;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class HuntingExperience {
    private DetailedMPlayer mp;
    private Player killer;
    private Entity killed;
    private List<ItemStack> drops;
    private EntityType entityType;
    private int level;
    private ItemStack hand;
    private boolean animal = false;
    private boolean player = false;

    public HuntingExperience(EntityDeathEvent e) {
        this.entityType = e.getEntity().getType();
        this.killer = e.getEntity().getKiller();
        this.drops = e.getDrops();
        this.killed = e.getEntity();

        if (!isKilledByWolf() && !isKilledByPlayer()) return;

        this.mp = TempData.getMPlayer(killer);
        this.level = mp.getLevel(Skill.HUNTING);
        this.hand = killer.getItemInHand();


        giveExperience();
        applyBoost();
        healALittle();
    }

    //Checks

    private boolean isLootingSword() {
        return !hand.getType().equals(Material.AIR) && hand.containsEnchantment(Enchantment.LOOT_BONUS_MOBS);
    }

    private boolean isKilledByWolf() {
        if (killed.getLastDamageCause() == null) return false;
        if (killed.getLastDamageCause().getCause().equals(EntityDamageEvent.DamageCause.ENTITY_ATTACK)) {
            if (killed.getLastDamageCause() instanceof EntityDamageByEntityEvent) {
                if (((EntityDamageByEntityEvent) killed.getLastDamageCause()).getDamager() instanceof Wolf) {
                    Wolf wolf = (Wolf) ((EntityDamageByEntityEvent) killed.getLastDamageCause()).getDamager();
                    if (wolf.isTamed()) {
                        Player owner = (Player) wolf.getOwner();
                        if (owner.isOnline()) {
                            killer = owner;
                            return TempData.getMPlayer(killer).getLevel(Skill.HUNTING) > 21;
                        }
                    }
                }
            }
        }
        return false;
    }

    private boolean isKilledByPlayer() {
        return killer != null;
    }

    private int getExperience() {
        int exp;

        switch (entityType) {
            case ENDERMAN:
                exp = 16;
                break;
            case SILVERFISH:
                exp = 20;
                break;
            case BAT:
                animal = true;
                exp = 30;
                break;
            case PIG:
                animal = true;
                exp = 10;
                break;
            case SHEEP:
                animal = true;
                exp = 22;
                animal = true;
                break;
            case COW:
                animal = true;
                exp = 22;
                break;
            case CHICKEN:
                animal = true;
                exp = 22;
                break;
            case SQUID:
                exp = 30;
                animal = true;
                break;
            case MUSHROOM_COW:
                exp = 40;
                animal = true;
                break;
            case SNOWMAN:
                exp = 35;
                break;
            case OCELOT:
                exp = 45;
                animal = true;
                break;
            case PLAYER:
                exp = 80;
                player = true;
                break;
            case HORSE:
                exp = 50;
                animal = true;
                break;
            case MAGMA_CUBE:
                exp = 5;
                break;
            case WITHER:
                exp = 760;
                break;
            case IRON_GOLEM:
                exp = 110;
                break;
            case VILLAGER:
                exp = 35;
                animal = true;
                break;
            case CREEPER:
                exp = 30;
                break;
            case WITCH:
                exp = 50;
                break;
            case SPIDER:
                exp = 14;
                break;
            case CAVE_SPIDER:
                exp = 12;
                break;
            case PIG_ZOMBIE:
                exp = 16;
                break;
            case ZOMBIE:
                exp = 14;
                break;
            case BLAZE:
                exp = 30;
                break;
            case GHAST:
                exp = 60;
                break;
            case GIANT:
                exp = 90;
                break;
            case WOLF:
                exp = 35;
                animal = true;
                break;
            case SKELETON:
                exp = 14;
                break;
            case SLIME:
                exp = 5;
                break;
            default:
                exp = 0;
                break;
        }

        return exp * 3;
    }

    //Apply

    private void giveExperience() {
        mp.addExp(Skill.HUNTING, getExperience());
    }

    private void applyBoost() {
        if(player)return;
        if (animal && level > 37) {
            int amount = RandomNumber.get(2) - 1;
            if (amount > 1) {
                drops.add(new ItemStack(Material.RAW_BEEF, amount));
            }

            if (entityType.equals(EntityType.SHEEP)) drops.add(new ItemStack(Material.WOOL, 1));
        }
        if (level >= 50) dropDrops();
        if (level == 100 && !isLootingSword()) dropDrops();

        if (animal) {
            if (RandomNumber.get(10000) <= 25 * level) {
                Entity ent = killed.getWorld().spawnEntity(killed.getLocation(), entityType);
                LivingEntity le = (LivingEntity) ent;
                le.setCustomName(ChatColor.GREEN + "Reincarnated soul");
                le.setCustomNameVisible(true);
            }
        }
    }

    private void dropDrops() {
        for (ItemStack s : drops) {
            killed.getWorld().dropItemNaturally(killed.getLocation(), s);
        }
    }

    private void healALittle() {
        if (level > 63 && killer.getHealth() <= (killer.getMaxHealth() - 1.0))
            killer.setHealth(killer.getHealth() + 1.0);
    }
}

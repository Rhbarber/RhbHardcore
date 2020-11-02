package com.rhbarber.hardcore.listeners;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.inventory.ItemStack;

public class Listeners implements Listener
{
    @EventHandler
    public void creatureSpawn(CreatureSpawnEvent event)
    {
        if(event.getEntityType() == EntityType.CREEPER)
        {
            Creeper creeper = (Creeper) event.getEntity();
            creeper.setPowered(true);
        }

        if(event.getEntityType() == EntityType.ZOMBIE)
        {
            Zombie zombie = (Zombie) event.getEntity();
            zombie.getEquipment().setHelmet(new ItemStack(Material.NETHERITE_HELMET));
            zombie.getEquipment().setChestplate(new ItemStack(Material.NETHERITE_CHESTPLATE));
            zombie.getEquipment().setLeggings(new ItemStack(Material.NETHERITE_LEGGINGS));
            zombie.getEquipment().setBoots(new ItemStack(Material.NETHERITE_BOOTS));
            zombie.getEquipment().setItemInMainHand(new ItemStack(Material.NETHERITE_AXE));
        }

        if(event.getEntityType() == EntityType.SKELETON)
        {
            Skeleton skeleton = (Skeleton) event.getEntity();
            skeleton.getEquipment().setHelmet(new ItemStack(Material.NETHERITE_HELMET));
            skeleton.getEquipment().setChestplate(new ItemStack(Material.NETHERITE_CHESTPLATE));
            skeleton.getEquipment().setLeggings(new ItemStack(Material.NETHERITE_LEGGINGS));
            skeleton.getEquipment().setBoots(new ItemStack(Material.NETHERITE_BOOTS));

            ItemStack punchBow = new ItemStack(Material.BOW);
            punchBow.addEnchantment(Enchantment.ARROW_KNOCKBACK, 2);

            skeleton.getEquipment().setItemInMainHand(punchBow);
        }
    }
}

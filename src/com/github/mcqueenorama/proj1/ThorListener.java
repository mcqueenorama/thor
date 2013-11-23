package com.github.mcqueenorama.proj1;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.entity.Player;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventPriority;


/**
 * Handle events for all Player related events
 * @author self
 */
public class ThorListener implements Listener {
    private final proj1 plugin;

    public ThorListener(proj1 instance) {
        plugin = instance;
    }

    @EventHandler(priority=EventPriority.NORMAL)
    public void onPlayerJoin(PlayerJoinEvent event) {
        plugin.getLogger().info(event.getPlayer().getName() + " joined the server! :D");
    }
    
    @EventHandler(priority=EventPriority.NORMAL)
    public void onPlayerJoin(PlayerLoginEvent event) {
        plugin.getLogger().info(event.getPlayer().getName() + " joined the server! :D");
    }

    @EventHandler(priority=EventPriority.NORMAL)
    public void onPlayerQuit(PlayerQuitEvent event) {
        plugin.getLogger().info(event.getPlayer().getName() + " left the server! :'(");
    }

    @EventHandler(priority=EventPriority.NORMAL)
    public void onPlayerInteractBlock(PlayerInteractEvent event) {
        plugin.getLogger().info("QQQ:got an interaction event");
        Player player = event.getPlayer();
        
        if (player.getItemInHand().getType() == Material.DIAMOND_SWORD) {
        // Creates a bolt of lightning at a given location. In this case, that location is where the player is looking.
        // Can only create lightning up to 200 blocks away.
        player.getWorld().strikeLightning(player.getTargetBlock(null, 200).getLocation());
        }
    }
    

    @EventHandler(priority=EventPriority.NORMAL)
    public void onPlayerDamage(EntityDamageEvent event) {
        plugin.getLogger().info("QQQ:got a damage event");
        Entity entity = event.getEntity();
        
        if(entity instanceof Player) {
        	Player player = (Player)entity;

        	String playerName = player.getName();
            if (! playerName.isEmpty() && plugin.getImmortals(playerName)) {
            	plugin.getLogger().info("QQQ:setting health 20 for " + player.getName());
            	event.setCancelled(true);
                player.setHealth(20);
                //player.setDamage(0);
            }
        }

        
    }
}
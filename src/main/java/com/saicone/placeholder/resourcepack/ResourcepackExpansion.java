package com.saicone.placeholder.resourcepack;

import me.clip.placeholderapi.expansion.Cacheable;
import me.clip.placeholderapi.expansion.Cleanable;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerResourcePackStatusEvent;
import org.bukkit.packs.ResourcePack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ResourcepackExpansion extends PlaceholderExpansion implements Listener, Cacheable, Cleanable {

    private final Map<UUID, PlayerResourcePackStatusEvent.Status> states = new HashMap<>();

    public ResourcepackExpansion() {
        Bukkit.getPluginManager().registerEvents(this, getPlaceholderAPI());
    }

    @EventHandler
    public void onStatusUpdate(PlayerResourcePackStatusEvent event) {
        states.put(event.getPlayer().getUniqueId(), event.getStatus());
    }

    @Override
    public void clear() {
        // Using clear just to unregister listener, we should keep it to avoid problems with /papi reload
        HandlerList.unregisterAll(this);
    }

    @Override
    public void cleanup(Player player) {
        states.remove(player.getUniqueId());
    }

    @Override
    public @NotNull String getIdentifier() {
        return "resourcepack";
    }

    @Override
    public @NotNull String getAuthor() {
        return "Rubenicos";
    }

    @Override
    public @NotNull String getVersion() {
        return "${version}";
    }

    @Override
    public @Nullable String onRequest(OfflinePlayer player, @NotNull String params) {
        switch (params.toLowerCase()) {
            case "loaded":
                return String.valueOf(states.get(player.getUniqueId()) == PlayerResourcePackStatusEvent.Status.SUCCESSFULLY_LOADED);
            case "status":
                final PlayerResourcePackStatusEvent.Status status = states.get(player.getUniqueId());
                return status != null ? status.name() : "UNKNOWN";
            case "id":
                final ResourcePack pack = Bukkit.getServerResourcePack();
                return pack == null ? "null" : String.valueOf(pack.getId());
            case "url":
                return Bukkit.getResourcePack();
            case "hash":
                return Bukkit.getResourcePackHash();
            case "prompt":
                return Bukkit.getResourcePackPrompt();
            case "required":
                return String.valueOf(Bukkit.isResourcePackRequired());
            default:
                return "invalid params";
        }
    }
}

package fr.relibelly.manager;

import fr.relibelly.DeathBack;
import fr.relibelly.utils.LocationUtils;
import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.entity.Player;

@Getter
public class DeathBackManager {
    public void saveDeathLocation(Player player) {
        if (player.getLastDeathLocation() == null) {
            player.sendMessage("§eErreur: Vous n'êtes pas mort dernièrement...");
            return;
        }
        DeathBack.get().getConfig().set("Back.players." + player.getUniqueId().toString(), LocationUtils.loc2str(player.getLastDeathLocation(), player.getLastDeathLocation().getWorld()));
        DeathBack.get().saveConfig();
    }

    public void teleportToDeathLocation(Player player) {
        if (DeathBack.get().getConfig().getString("Back.players." + player.getUniqueId().toString()) == null) {
            return;
        }

        Location location = LocationUtils.str2loc(DeathBack.get().getConfig().getString("Back.players." + player.getUniqueId().toString()));
        player.teleport(location);
    }

    public boolean hasSavedDeathLocation(Player player) {
       return DeathBack.get().getConfig().getString("Back.players." + player.getUniqueId().toString()) != null;
    }
}

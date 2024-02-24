package fr.relibelly.commands;

import fr.relibelly.DeathBack;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DeathBackCMD extends Command {

    public DeathBackCMD() {
        super("death");
    }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§eErreur: Seulement un joueur peut executer cette commande.");
            return false;
        }

        if (args.length == 0) {
            sender.sendMessage("§eUtilisation: /death <save | go>");
            return false;
        }

        Player player = (Player) sender;
        if (args[0].equals("save")) {
            DeathBack.get().getDeathBackManager().saveDeathLocation(player);
            player.sendMessage("§aLa position de ta dernière mort a bien été sauvegardée ! §eUtilise §a/death go §epour y retourner !");
        }

        if (args[0].equals("go")) {
            if (DeathBack.get().getDeathBackManager().hasSavedDeathLocation(player)) {
                DeathBack.get().getDeathBackManager().teleportToDeathLocation(player);
                player.sendMessage("§aTu as bien été téléporter à ta dernière mort sauvegardée !");
            } else {
                player.sendMessage("§eErreur: Tu n'as pas de position de mort sauvgarder, sauvegarde ta dernière mort avec §a/death save");
            }
        }

        return false;
    }
}

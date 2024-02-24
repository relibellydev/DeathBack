package fr.relibelly;

import fr.relibelly.commands.DeathBackCMD;
import fr.relibelly.listeners.PlayerListener;
import fr.relibelly.manager.DeathBackManager;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandMap;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Field;

@Getter
public final class DeathBack extends JavaPlugin {

    private static DeathBack instance;
    private DeathBackManager deathBackManager;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        this.deathBackManager = new DeathBackManager();
        getServer().getPluginManager().registerEvents(new PlayerListener(), this);
        registerCommand("deathback", new DeathBackCMD());

    }

    @Override
    public void onDisable() {
    }


    public static DeathBack get() {
        return instance;
    }

    public void registerCommand(String commandName, Command commandClass) {
        try {
            Field bukkitCommandMap = Bukkit.getServer().getClass().getDeclaredField("commandMap");
            bukkitCommandMap.setAccessible(true);
            ((CommandMap) bukkitCommandMap.get(Bukkit.getServer())).register(commandName, commandClass);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

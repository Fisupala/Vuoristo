package me.fisupala.vuoristoCore;

import me.fisupala.vuoristoCore.commands.tpa.CommandTpa;
import me.fisupala.vuoristoCore.commands.tpa.CommandTpAccept;
import org.bukkit.plugin.java.JavaPlugin;

public final class VuoristoCore extends JavaPlugin {

    private CommandTpa commandTpa;

    @Override
    public void onEnable() {
        // Plugin startup logic
        commandTpa = new CommandTpa();
        getCommand("tpa").setExecutor(commandTpa);
        getCommand("tpaccept").setExecutor(new CommandTpAccept(commandTpa));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}

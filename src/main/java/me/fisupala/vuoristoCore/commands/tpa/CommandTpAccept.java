package me.fisupala.vuoristoCore.commands.tpa;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class CommandTpAccept implements CommandExecutor {

    private final CommandTpa commandTpa;

    public CommandTpAccept(CommandTpa commandTpa) {
        this.commandTpa = commandTpa;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Vain pelaajat voivat suorittaa komennon.");
            return true;
        }

        Player receiver = (Player) sender;

        Player requester = commandTpa.getTpaRequests().get(receiver);

        if (requester == null) {
            receiver.sendMessage(Component.text("Sinulla ei ole tämänhetkisiä teleporttipyyntöjä.").color(NamedTextColor.DARK_RED));
            return true;
        }

        commandTpa.getTpaRequests().remove(receiver);

        receiver.sendMessage(Component.text("Hyväksyit teleporttipyynnön. " + requester.getName() + " teleporttaa sinuun kolmen sekunnin päästä.").color(NamedTextColor.GREEN));
        requester.sendMessage(Component.text(receiver.getName() + " hyväksyi teleporttipyynnön. Sinut teleportataan hänen luokseen kolmen sekunnin päästä.").color(NamedTextColor.GREEN));

        new BukkitRunnable() {
            @Override
            public void run() {
                requester.teleport(receiver.getLocation());
            }
        }.runTaskLater(org.bukkit.Bukkit.getPluginManager().getPlugin("VuoristoCore"), 60);
        commandTpa.getTpaRequests().remove(requester);
        return true;
    }
}

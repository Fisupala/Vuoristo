package me.fisupala.vuoristoCore.commands.tpa;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class CommandTpa implements CommandExecutor {

    private final Map<Player, Player> tpaRequests = new HashMap<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Vain pelaajat voivat kayttaa tata komentoa");
            return true;
        }

        Player player = (Player) sender;

        if (args.length != 1) {
            return false;
        }

        Player target = Bukkit.getPlayer(args[0]);

        if (target == null || !target.isOnline()) {
            player.sendMessage(Component.text("Pelaajaa ei löydetty").color(NamedTextColor.DARK_RED));
            return true;
        }

        if (target == player) {
            player.sendMessage(Component.text("Et voi lähettää teleporttipyyntöä itsellesi").color(NamedTextColor.DARK_RED));
            return true;
        }

        tpaRequests.put(target, player);
        player.sendMessage(Component.text("Teleporttipyyntö lähetetty pelaajalle " + target.getName()).color(NamedTextColor.GREEN));
        target.sendMessage(Component.text(player.getName() + " lähetti sinulle teleporttipyynnön. Tee /tpaccept hyväksyeksesi se.").color(NamedTextColor.GREEN));
        return true;
    }

    public Map<Player, Player> getTpaRequests() {
        return tpaRequests;
    }
}

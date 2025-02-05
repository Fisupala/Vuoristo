package me.fisupala.vuoristoCore.commands;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CommandGm implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {
        if(!(sender instanceof Player player)) {
        return true;
        }

        Double gamemode = Double.valueOf(args[0]);

        if(gamemode.equals(1)) {
            player.setGameMode(GameMode.CREATIVE);
            player.sendMessage(Component.text("Pelimuodoksi asetettu creative").color(NamedTextColor.GREEN));
        }
        if(gamemode.equals(2)) {
            player.setGameMode(GameMode.CREATIVE);
            player.sendMessage(Component.text("Pelimuodoksi asetettu survival").color(NamedTextColor.GREEN));
        }
        if(gamemode.equals(3)) {
            player.setGameMode(GameMode.CREATIVE);
            player.sendMessage(Component.text("Pelimuodoksi asetettu spectator").color(NamedTextColor.GREEN));
        }
        return true;
    }
}

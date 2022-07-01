package net.mcreator.prophecystick.procedures;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.TextComponent;

import net.mcreator.prophecystick.network.ProphecyStickModVariables;

public class PSToggleModeProcedure {
    public static void execute(Entity entity) {
        if (entity == null)
            return;
        {
            double mode = entity.getCapability(ProphecyStickModVariables.PLAYER_VARIABLES_CAPABILITY, null)
                    .orElse(new ProphecyStickModVariables.PlayerVariables()).prophecy_stick_mode;

            mode = (int) mode << 1;
            if (mode > 64) {
                mode = 1;
            }

            double finalMode = mode;
            entity.getCapability(ProphecyStickModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
                capability.prophecy_stick_mode = finalMode;
                capability.syncPlayerVariables(entity);
            });

            String modeName = switch ((int) mode) {
                case 1 -> "coal";
                case 2 -> "copper";
                case 4 -> "redstone";
                case 8 -> "iron";
                case 16 -> "gold";
                case 32 -> "diamond";
                case 64 -> "emerald";
                default -> "The stick is glitching...";
            };

            if (entity instanceof Player _player && !_player.level.isClientSide()) {
                _player.displayClientMessage(new TextComponent(modeName), (false));
            }
        }
    }
}
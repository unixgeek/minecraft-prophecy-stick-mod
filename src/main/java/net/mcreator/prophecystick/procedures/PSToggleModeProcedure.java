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
				.orElse(new ProphecyStickModVariables.PlayerVariables())).prophecy_stick_mode);

			mode = (int) mode << 1;
            if (mode > 64) {
                mode = 1;
            }

			entity.getCapability(ProphecyStickModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.prophecy_stick_mode = mode;
				capability.syncPlayerVariables(entity);
			});

            String modeName = "";
			switch ((int) mode) {
                case 1:
                    modeName = "coal";
                    break;
                case 2:
                    modeName = "copper";
                    break;
                case 4:
                    modeName = "redstone";
                    break;
                case 8:
                    modeName = "iron";
                    break;
                case 16:
                    modeName = "gold";
                    break;
                case 32:
                    modeName = "diamond";
                    break;
                case 64:
                    modeName = "emerald";
                    break;
                default: 
                    modeName = "The stick is glitching...";
                    break;
            }
   
			if (entity instanceof Player _player && !_player.level.isClientSide()) {
				_player.displayClientMessage(new TextComponent(modeName), (false));
			}
		}



		}
	}
}

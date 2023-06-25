package net.mcreator.prophecystick.procedures;

import net.mcreator.prophecystick.network.ProphecyStickModVariables;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.core.BlockPos;

public class PSFindOreProcedure {
	private static final int RANGE = 100;
	
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;

		double mode = entity.getCapability(ProphecyStickModVariables.PLAYER_VARIABLES_CAPABILITY, null)
				.orElse(new ProphecyStickModVariables.PlayerVariables()).prophecy_stick_mode;

		Block targetBlock = switch ((int) mode) {
			case 1 -> Blocks.COAL_ORE;
			case 2 -> Blocks.COPPER_ORE;
			case 4 -> Blocks.REDSTONE_ORE;
			case 8 -> Blocks.IRON_ORE;
			case 16 -> Blocks.GOLD_ORE;
			case 32 -> Blocks.DIAMOND_ORE;
			case 64 -> Blocks.EMERALD_ORE;
			default -> throw new IllegalStateException("Unexpected value: " + (int) mode);
		};

        int bx = 0;
        int by = 0;
        int bz = 0;
		double sx;
		double sy;
		double sz;
		boolean found = false;
		sx = -3;
		for (int index0 = 0; index0 < RANGE; index0++) {
			sy = -3;
			for (int index1 = 0; index1 < RANGE; index1++) {
				sz = -3;
				for (int index2 = 0; index2 < RANGE; index2++) {
					if ((world.getBlockState(new BlockPos(x + sx, y + sy, z + sz))).getBlock() == targetBlock) {
						found = true;
						bx = Double.valueOf(x + sx).intValue();
						by = Double.valueOf(y + sy).intValue();
						bz = Double.valueOf(z + sz).intValue();
						continue;
					}
					sz = sz + 1;
				}
				if (found) {
					continue;
				}
				sy = sy + 1;
			}
			if (found) {
				continue;
			}
			sx = sx + 1;
		}
		if (found) {
			if (entity instanceof Player _player && !_player.level.isClientSide()) {
				_player.displayClientMessage(new TextComponent(bx + "," + by + "," + bz), (false));
			}
		}
	}
}


/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.prophecystick.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.item.Item;

import net.mcreator.prophecystick.item.ProphecyStickItem;
import net.mcreator.prophecystick.ProphecyStickMod;

public class ProphecyStickModItems {
	public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, ProphecyStickMod.MODID);
	public static final RegistryObject<Item> PROPHECY_STICK = REGISTRY.register("prophecy_stick", () -> new ProphecyStickItem());
}

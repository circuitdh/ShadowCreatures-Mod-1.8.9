package upcraftlp.shadowcreatures.util;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class KeyBindings {

	public static KeyBinding morph = new KeyBinding("keyBinding.morphkey.name", Keyboard.KEY_X, "category.shadowcreatures.name");
	public static KeyBinding throwSword = new KeyBinding("keyBinding.throwkey.name", Keyboard.KEY_R, "category.shadowcreatures.name");
	
	public static void initKeys()
	{
		MinecraftForge.EVENT_BUS.register(new KeyHandler());
		
		ClientRegistry.registerKeyBinding(morph);
		ClientRegistry.registerKeyBinding(throwSword);
	}
	
}

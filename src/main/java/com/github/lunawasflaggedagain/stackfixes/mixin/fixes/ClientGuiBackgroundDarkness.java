package com.github.lunawasflaggedagain.stackfixes.mixin.fixes;

import net.minecraft.client.gui.screen.inventory.menu.InventoryMenuScreen;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InventoryMenuScreen.class)
public class ClientGuiBackgroundDarkness {
	@Inject(method = "render(IIF)V", at = @At("RETURN"))
	public void stackFixes$render(CallbackInfo ci) {
		GL11.glDisable(2896);
	}
}

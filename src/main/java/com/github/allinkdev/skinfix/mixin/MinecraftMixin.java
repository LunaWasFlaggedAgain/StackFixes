package com.github.allinkdev.skinfix.mixin;

import com.github.allinkdev.skinfix.SkinFix;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public class MinecraftMixin {
	@Inject(method = "tick", at = @At(value = "TAIL"))
	private void tick(final CallbackInfo ci) {
		SkinFix.tick();
	}
}

package com.github.lunawasflaggedagain.stackfixes.mixin.deletions;

import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Minecraft.class)
public class ClientTimerHack {
	@Redirect(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Minecraft;initTimerHackThread()V"))
	public void stackFixes$init$initTimerHackThread(Minecraft instance) {
	}
}

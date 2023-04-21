package com.github.lunawasflaggedagain.stackfixes.mixin.deletions;

import net.minecraft.client.Minecraft;
import net.minecraft.util.snooper.Snoopable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public abstract class ClientReservedCrashMemory implements Snoopable {
	@Shadow
	public static byte[] MEMORY_RESERVED_FOR_CRASH = null;

	@Redirect(method = "cleanHeap", at = @At(value = "INVOKE", target = "Ljava/lang/System;gc()V"))
	public void stackFixes$cleanHeap$gc() {
		//
	}

	@Inject(method = "cleanHeap", at = @At(value = "FIELD", target = "Lnet/minecraft/client/Minecraft;MEMORY_RESERVED_FOR_CRASH:[B"))
	public void stackFixes$cleanHeap$PUTSTATIC(CallbackInfo ci) {
		MEMORY_RESERVED_FOR_CRASH = null;
	}

	@Inject(method = "cleanHeap()V", at = @At("TAIL"))
	public void stackFixes$cleanHeap(CallbackInfo ci) {
		System.gc();
	}
}

package com.github.lunawasflaggedagain.stackfixes.mixin.deletions;

import net.minecraft.client.Minecraft;
import net.minecraft.util.math.Box;
import net.minecraft.util.snooper.Snoopable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public abstract class ClientReservedCrashMemory implements Snoopable {
	@Shadow
	public static byte[] MEMORY_RESERVED_FOR_CRASH = null;

	@Inject(method = "cleanHeap()V", at = @At("HEAD"), cancellable = true)
	public void stackFixes$cleanHeap(CallbackInfo ci) {
		((Minecraft)(Object)this).worldRenderer.deleteGlLists();
		Box.getCache().clear();
		((Minecraft)(Object)this).setWorld(null);
		System.gc();
		ci.cancel();
	}
}

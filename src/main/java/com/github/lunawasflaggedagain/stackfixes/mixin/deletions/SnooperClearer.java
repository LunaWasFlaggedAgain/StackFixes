package com.github.lunawasflaggedagain.stackfixes.mixin.deletions;

import net.minecraft.util.snooper.Snooper;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;
import java.util.Timer;

@Mixin(Snooper.class)
public class SnooperClearer {
	@Shadow
	@Final
	private final Timer snooperTimer = null;
	@Shadow
	private Map snoopedDataMap = null;
	@Shadow
	@Final
	private final Object syncObject = null;

	@Inject(method = "stopSnooping", at = @At(value = "HEAD"), cancellable = true)
	public void stackFixes$stopSnooping(CallbackInfo ci) {
		ci.cancel();
	}

	@Inject(method = "addToSnoopedData", at = @At(value = "HEAD"), cancellable = true)
	public void stackFixes$addToSnoopedData(String key, Object data, CallbackInfo ci) {
		ci.cancel();
	}
}

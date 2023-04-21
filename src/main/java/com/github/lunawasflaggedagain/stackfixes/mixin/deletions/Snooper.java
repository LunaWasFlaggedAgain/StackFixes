package com.github.lunawasflaggedagain.stackfixes.mixin.deletions;

import net.minecraft.server.MinecraftServer;
import org.quiltmc.loader.api.minecraft.ClientOnly;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MinecraftServer.class)
public class Snooper {
	@Final
	@Shadow
	private final net.minecraft.util.snooper.Snooper snooper = null;

	@Redirect(method = "tick()V", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/snooper/Snooper;addCpuInfo()V"))
	public void stackFixes$addCpuInfo(net.minecraft.util.snooper.Snooper snooper) { }

	@Redirect(method = "tick()V", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/snooper/Snooper;isActive()Z"))
	public boolean stackFixes$isActive(net.minecraft.util.snooper.Snooper snooper) { return true; } // Returning true here prevents the call to startSnooping

	@Inject(method = "addSnooperInfo(Lnet/minecraft/util/snooper/Snooper;)V", at = @At("HEAD"), cancellable = true)
	public void stackFixes$addSnooperInfo(CallbackInfo ci) { ci.cancel(); }

	@Inject(method = "addSnooper(Lnet/minecraft/util/snooper/Snooper;)V", at = @At("HEAD"), cancellable = true)
	public void stackFixes$addSnooper(CallbackInfo ci) { ci.cancel(); }

	@Inject(method = "isSnooperEnabled", at = @At("HEAD"), cancellable = true)
	public void stackFixes$isSnooperEnabled(CallbackInfoReturnable<Boolean> ci) { ci.setReturnValue(false); }

	@ClientOnly
	@Inject(method = "getSnooper", at = @At("HEAD"), cancellable = true)
	public void stackFixes$getSnooper(CallbackInfoReturnable<net.minecraft.util.snooper.Snooper> ci) { ci.setReturnValue(null); }
}

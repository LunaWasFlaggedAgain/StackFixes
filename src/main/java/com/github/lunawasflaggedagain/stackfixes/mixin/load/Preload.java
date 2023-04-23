package com.github.lunawasflaggedagain.stackfixes.mixin.load;

import com.github.lunawasflaggedagain.stackfixes.StackFixes;
import net.minecraft.client.Minecraft;
import net.minecraft.client.Session;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.File;
import java.net.Proxy;

// FIXME: Remove when preloads in Ornithe are figured out
@Mixin(Minecraft.class)
public class Preload {
	@Inject(method = "<init>", at = @At(value = "FIELD", target = "Lnet/minecraft/client/Minecraft;INSTANCE:Lnet/minecraft/client/Minecraft;", opcode = Opcodes.PUTSTATIC))
	public void stackFixes$INSTANCE(Session width, int height, int fullscreen, boolean demo, boolean runDir, File assetsDir, File resourcePacksDir, File proxy, Proxy gameVersion, String par10, CallbackInfo ci) throws NoSuchFieldException, IllegalAccessException {
		StackFixes.preload();
	}
}

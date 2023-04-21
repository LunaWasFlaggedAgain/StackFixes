package com.github.lunawasflaggedagain.stackfixes.mixin.accessor;

import net.minecraft.client.gui.screen.ConfirmScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(ConfirmScreen.class)
public interface ConfirmScreenAccessor {
	@Accessor
	String getConfirmText();

	@Accessor
	String getAbortText();
}

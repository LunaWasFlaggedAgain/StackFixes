package com.github.lunawasflaggedagain.stackfixes.mixin.accessor;

import net.minecraft.client.gui.screen.ConfirmChatLinkScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(ConfirmChatLinkScreen.class)
public interface ConfirmChatLinkScreenAccessor extends ConfirmScreenAccessor {
	@Accessor
	String getCopy();
}

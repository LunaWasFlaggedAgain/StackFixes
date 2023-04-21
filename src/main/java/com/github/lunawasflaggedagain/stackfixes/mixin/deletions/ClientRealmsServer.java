package com.github.lunawasflaggedagain.stackfixes.mixin.deletions;

import net.minecraft.client.gui.screen.TitleScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(TitleScreen.class)
public class ClientRealmsServer {
	@Shadow
	private boolean realmsEnabled = false;
}

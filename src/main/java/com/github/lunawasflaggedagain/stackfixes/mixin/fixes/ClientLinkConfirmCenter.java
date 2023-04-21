package com.github.lunawasflaggedagain.stackfixes.mixin.fixes;

import com.github.lunawasflaggedagain.stackfixes.mixin.accessor.ConfirmChatLinkScreenAccessor;
import net.minecraft.client.gui.screen.ConfirmChatLinkScreen;
import net.minecraft.client.gui.screen.ConfirmScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ConfirmChatLinkScreen.class)
public abstract class ClientLinkConfirmCenter extends ConfirmScreen {
	protected ClientLinkConfirmCenter(Screen parent, String title, String description, int id) {
		super(parent, title, description, id);
	}

	@SuppressWarnings("unchecked")
	@Inject(method = "init()V", at = @At("HEAD"), cancellable = true)
	public void stackFixes$cleanHeap(CallbackInfo ci) {
		ConfirmChatLinkScreen screen = ((ConfirmChatLinkScreen) (Object) this);
		ConfirmChatLinkScreenAccessor accessor = (ConfirmChatLinkScreenAccessor) screen;

		this.buttons.add(new ButtonWidget(0, this.titleWidth / 2 - 155, this.height / 6 + 96, 100, 20, accessor.getConfirmText()));
		this.buttons.add(new ButtonWidget(2, this.titleWidth / 2 - 50, this.height / 6 + 96, 100, 20, accessor.getCopy()));
		this.buttons.add(new ButtonWidget(1, this.titleWidth / 2 + 55, this.height / 6 + 96, 100, 20, accessor.getAbortText()));

		ci.cancel();
	}
}

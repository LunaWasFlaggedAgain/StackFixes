package com.github.lunawasflaggedagain.stackfixes.mixin.deletions;

import net.minecraft.client.gui.screen.options.OptionsScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.List;

@Mixin(OptionsScreen.class)
public class ClientSnooperButton {
	@Redirect(method = "init()V", at = @At(value = "INVOKE", target = "Ljava/util/List;add(Ljava/lang/Object;)Z"))
	public boolean stackFixes$add(List<Object> list, Object object) {
		if (((ButtonWidget)object).id == 104) return true;
		return list.add(object);
	}

}

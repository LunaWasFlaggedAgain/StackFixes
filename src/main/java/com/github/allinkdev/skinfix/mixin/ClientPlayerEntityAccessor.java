package com.github.allinkdev.skinfix.mixin;

import net.minecraft.client.entity.living.player.ClientPlayerEntity;
import net.minecraft.resource.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(ClientPlayerEntity.class)
public interface ClientPlayerEntityAccessor {
	@Accessor
	Identifier getSkinTextureId();

	@Accessor
	Identifier getCapeTextureId();
}

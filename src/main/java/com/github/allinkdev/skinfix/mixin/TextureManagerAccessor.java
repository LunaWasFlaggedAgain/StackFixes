package com.github.allinkdev.skinfix.mixin;

import net.minecraft.client.texture.Texture;
import net.minecraft.client.texture.TextureManager;
import net.minecraft.resource.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Map;

@Mixin(TextureManager.class)
public interface TextureManagerAccessor {
	@Accessor
	Map<Identifier, Texture> getTextures();
}

package com.github.allinkdev.skinfix.mixin;

import com.github.allinkdev.skinfix.SkinRequestThread;
import net.minecraft.client.entity.living.player.ClientPlayerEntity;
import net.minecraft.client.render.DownloadedSkinParser;
import net.minecraft.client.texture.PlayerSkinTexture;
import net.minecraft.resource.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.io.PrintStream;

import static net.minecraft.client.entity.living.player.ClientPlayerEntity.STEVE_TEXTURE;
import static net.minecraft.client.entity.living.player.ClientPlayerEntity.registerTexture;

@Mixin(ClientPlayerEntity.class)
public abstract class ClientPlayerEntityMixin {
	private void scheduleSkinDownload() {
		SkinRequestThread.scheduleFor((ClientPlayerEntity) (Object) this);
	}

	@Redirect(method = "registerTextures", at = @At(value = "INVOKE", target = "Ljava/io/PrintStream;println(Ljava/lang/String;)V"))
	private void registerTextures$println(final PrintStream instance, final String output) {
		// Hush little baby, don't say a word...
	}

	@Redirect(method = "registerTextures", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/entity/living/player/ClientPlayerEntity;registerSkinTexture(Lnet/minecraft/resource/Identifier;Ljava/lang/String;)Lnet/minecraft/client/texture/PlayerSkinTexture;"))
	private PlayerSkinTexture registerTextures$registerSkinTexture(final Identifier id, final String playerName) {
		this.scheduleSkinDownload();

		return registerTexture(id, "https://textures.minecraft.net/texture/1a4af718455d4aab528e7a61f86fa25e6a369d1768dcb13f7df319a713eb810b", STEVE_TEXTURE, new DownloadedSkinParser());
	}

	@Redirect(method = "registerTextures", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/entity/living/player/ClientPlayerEntity;registerCapeTexture(Lnet/minecraft/resource/Identifier;Ljava/lang/String;)Lnet/minecraft/client/texture/PlayerSkinTexture;"))
	private PlayerSkinTexture registerTextures$registerCapeTexture(final Identifier id, final String playerName) {
		return new PlayerSkinTexture(null, null, null);
	}
}

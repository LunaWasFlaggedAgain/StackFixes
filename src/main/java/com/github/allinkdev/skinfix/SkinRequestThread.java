package com.github.allinkdev.skinfix;

import com.github.allinkdev.skinfix.api.property.ProfileProperty;
import com.github.allinkdev.skinfix.api.property.TexturesProperty;
import com.github.allinkdev.skinfix.api.request.impl.MojIdToSkinRequest;
import com.github.allinkdev.skinfix.api.request.impl.UsernameToMojIdRequest;
import com.github.allinkdev.skinfix.api.response.impl.MojIdToSkinResponse;
import com.github.allinkdev.skinfix.api.response.impl.UsernameToMojIdResponse;
import com.github.allinkdev.skinfix.mixin.ClientPlayerEntityAccessor;
import net.minecraft.client.entity.living.player.ClientPlayerEntity;
import net.minecraft.client.render.DownloadedSkinParser;
import net.minecraft.resource.Identifier;

import java.util.Optional;

public final class SkinRequestThread extends Thread {
	private final ClientPlayerEntity player;
	private final String username;

	SkinRequestThread(final ClientPlayerEntity player) {
		this.player = player;
		this.username = player.getName();
	}

	public static void scheduleFor(final ClientPlayerEntity player) {
		final SkinRequestThread skinRequestThread = new SkinRequestThread(player);

		SkinFix.EXECUTOR.submit(skinRequestThread);
	}

	private void actuallyRun() throws Exception {
		final UsernameToMojIdRequest uuidRequest = new UsernameToMojIdRequest(this.username);
		final UsernameToMojIdResponse uuidResponse = uuidRequest.send();
		final String mojId = uuidResponse.getMojId();
		final MojIdToSkinRequest skinRequest = new MojIdToSkinRequest(mojId);
		final MojIdToSkinResponse skinResponse = skinRequest.send();
		final ProfileProperty[] properties = skinResponse.getProperties();
		final Optional<TexturesProperty> texturesPropertyOptional = TexturesProperty.findIn(properties);

		if (!texturesPropertyOptional.isPresent()) {
			return;
		}

		final TexturesProperty texturesProperty = texturesPropertyOptional.get();
		final TexturesProperty.Value value = texturesProperty.getValue();
		final ClientPlayerEntityAccessor playerAccessor = (ClientPlayerEntityAccessor) player;
		final Optional<TexturesProperty.Value.Content> skinOptional = value.getSkin();
		final Optional<TexturesProperty.Value.Content> capeOptional = value.getCape();

		if (!skinOptional.isPresent() && !capeOptional.isPresent()) {
			return;
		}

		if (skinOptional.isPresent()) {
			final TexturesProperty.Value.Content skin = skinOptional.get();
			final String url = skin.getUrl();
			final Identifier identifier = playerAccessor.getSkinTextureId();

			SkinFix.schedule(() -> {
				SkinFix.unregister(identifier);
				ClientPlayerEntity.registerTexture(identifier, url, ClientPlayerEntity.STEVE_TEXTURE, new DownloadedSkinParser());
			});
		}

		if (capeOptional.isPresent()) {
			final TexturesProperty.Value.Content cape = capeOptional.get();
			final String url = cape.getUrl();
			final Identifier identifier = playerAccessor.getCapeTextureId();

			SkinFix.schedule(() -> {
				SkinFix.unregister(identifier);
				ClientPlayerEntity.registerTexture(identifier, url, null, null);
			});
		}
	}

	@Override
	public void run() {
		try {
			this.actuallyRun();
		} catch (Exception ignored) {
		}
	}
}

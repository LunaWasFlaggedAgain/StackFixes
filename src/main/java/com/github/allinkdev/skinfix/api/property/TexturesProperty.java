package com.github.allinkdev.skinfix.api.property;

import com.github.allinkdev.skinfix.SkinFix;
import com.google.gson.annotations.SerializedName;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;
import java.util.Optional;

public final class TexturesProperty {

	private static final Base64.Decoder DECODER = Base64.getDecoder();

	private final long timestamp;
	@SerializedName("profileId")
	private final String mojId;
	@SerializedName("profileName")
	private final String username;
	@SerializedName("textures")
	private final Value value;

	public TexturesProperty(final long timestamp, final String mojId, final String username, final Value value) {
		this.timestamp = timestamp;
		this.mojId = mojId;
		this.username = username;
		this.value = value;
	}

	private static TexturesProperty from(final String base64) {
		final byte[] jsonBytes = DECODER.decode(base64);
		final String jsonString = new String(jsonBytes, StandardCharsets.UTF_8);

		return SkinFix.GSON.fromJson(jsonString, TexturesProperty.class);
	}

	public static Optional<TexturesProperty> findIn(final ProfileProperty[] properties) {
		final Optional<ProfileProperty> propertyOptional = Arrays.stream(properties)
			.filter(p -> p.getName().equals("textures"))
			.findFirst();

		if (!propertyOptional.isPresent()) {
			return Optional.empty();
		}

		final ProfileProperty property = propertyOptional.get();
		final String propertyValue = property.getValue();
		final TexturesProperty texturesProperty = from(propertyValue);

		return Optional.of(texturesProperty);
	}

	public long getTimestamp() {
		return this.timestamp;
	}

	public String getMojId() {
		return this.mojId;
	}

	public String getUsername() {
		return this.username;
	}

	public Value getValue() {
		return this.value;
	}

	public static class Value {
		@SerializedName("SKIN")
		private final Content skin;
		@SerializedName("cape")
		private final Content cape;

		public Value(final Content skin, final Content cape) {
			this.skin = skin;
			this.cape = cape;
		}

		public Optional<Content> getSkin() {
			return Optional.ofNullable(this.skin);
		}

		public Optional<Content> getCape() {
			return Optional.ofNullable(this.cape);
		}

		public static class Content {
			private final String url;

			public Content(final String url) {
				this.url = url;
			}

			public String getUrl() {
				return this.url;
			}
		}
	}
}

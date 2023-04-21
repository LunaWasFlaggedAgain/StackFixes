package com.github.allinkdev.skinfix.api.response.impl;

import com.github.allinkdev.skinfix.api.property.ProfileProperty;
import com.github.allinkdev.skinfix.api.response.Response;
import com.google.gson.annotations.SerializedName;

public final class MojIdToSkinResponse extends Response {
	@SerializedName("id")
	private final String mojId;
	@SerializedName("name")
	private final String username;
	private final ProfileProperty[] properties;

	public MojIdToSkinResponse(final String mojId, final String username, final ProfileProperty[] properties) {
		this.mojId = mojId;
		this.username = username;
		this.properties = properties;
	}

	public String getMojId() {
		return this.mojId;
	}

	public String getUsername() {
		return this.username;
	}

	public ProfileProperty[] getProperties() {
		return this.properties;
	}
}

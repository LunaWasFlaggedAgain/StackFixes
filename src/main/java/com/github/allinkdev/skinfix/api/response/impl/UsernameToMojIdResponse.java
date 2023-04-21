package com.github.allinkdev.skinfix.api.response.impl;

import com.github.allinkdev.skinfix.api.response.Response;
import com.google.gson.annotations.SerializedName;

public final class UsernameToMojIdResponse extends Response {
	@SerializedName("name")
	private final String username;
	@SerializedName("id")
	private final String mojId;
	private final boolean legacy;
	private final boolean demo;

	public UsernameToMojIdResponse(final String username, final String mojId, final boolean legacy, final boolean demo) {
		this.username = username;
		this.mojId = mojId;
		this.legacy = legacy;
		this.demo = demo;
	}

	public String getUsername() {
		return this.username;
	}

	public String getMojId() {
		return this.mojId;
	}

	public boolean isLegacy() {
		return this.legacy;
	}

	public boolean isDemo() {
		return this.demo;
	}
}

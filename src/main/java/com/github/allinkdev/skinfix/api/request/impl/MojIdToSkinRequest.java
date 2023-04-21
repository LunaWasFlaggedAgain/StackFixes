package com.github.allinkdev.skinfix.api.request.impl;

import com.github.allinkdev.skinfix.api.request.Request;
import com.github.allinkdev.skinfix.api.response.impl.MojIdToSkinResponse;

import java.io.IOException;

public final class MojIdToSkinRequest extends Request<MojIdToSkinResponse> {
	private static final String URL = "https://sessionserver.mojang.com/session/minecraft/profile/";
	private final String mojId;

	public MojIdToSkinRequest(final String mojId) {
		super(MojIdToSkinResponse.class);

		this.mojId = mojId;
	}

	@Override
	public MojIdToSkinResponse send() throws IOException {
		return this.send(URL + this.mojId);
	}
}

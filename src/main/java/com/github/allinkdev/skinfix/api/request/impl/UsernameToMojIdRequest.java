package com.github.allinkdev.skinfix.api.request.impl;

import com.github.allinkdev.skinfix.api.request.Request;
import com.github.allinkdev.skinfix.api.response.impl.UsernameToMojIdResponse;
import net.minecraft.text.StringUtils;

import java.io.IOException;

public final class UsernameToMojIdRequest extends Request<UsernameToMojIdResponse> {
	private static final String URL = "https://api.mojang.com/users/profiles/minecraft/";
	private final String username;

	public UsernameToMojIdRequest(final String username) {
		super(UsernameToMojIdResponse.class);

		this.username = StringUtils.stripFormatting(username);
	}

	@Override
	public UsernameToMojIdResponse send() throws IOException {
		return this.send(URL + this.username);
	}
}

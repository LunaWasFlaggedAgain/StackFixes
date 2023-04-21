package com.github.allinkdev.skinfix.api.response;

import com.github.allinkdev.skinfix.SkinFix;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public abstract class Response {
	private static <T> T from(final Class<? extends T> clazz, final InputStream inputStream) {
		final InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

		return SkinFix.GSON.fromJson(inputStreamReader, clazz);
	}

	public static <T> T from(final Class<? extends T> clazz, final HttpsURLConnection httpsURLConnection) throws IOException {
		final InputStream inputStream = httpsURLConnection.getInputStream();

		return from(clazz, inputStream);
	}
}

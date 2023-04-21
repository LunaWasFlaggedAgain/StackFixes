package com.github.allinkdev.skinfix.api.request;

import com.github.allinkdev.skinfix.api.response.Response;
import net.minecraft.client.Minecraft;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;

public abstract class Request<R extends Response> {
	private final Class<R> responseClass;

	protected Request(final Class<R> responseClass) {
		this.responseClass = responseClass;
	}

	public abstract R send() throws IOException;

	protected R send(final String urlStr) throws IOException {
		// Make sure to respect proxy settings!
		final Minecraft minecraft = Minecraft.getInstance();
		final Proxy selectedProxy = minecraft.getNetworkProxy();

		final URL url = new URL(urlStr);
		final URLConnection urlConnection = url.openConnection(selectedProxy);
		final HttpsURLConnection httpsURLConnection = (HttpsURLConnection) urlConnection;

		httpsURLConnection.setDoInput(true);

		return Response.from(responseClass, httpsURLConnection);
	}
}

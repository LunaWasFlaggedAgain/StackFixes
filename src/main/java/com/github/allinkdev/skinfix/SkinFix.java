package com.github.allinkdev.skinfix;

import com.github.allinkdev.skinfix.mixin.TextureManagerAccessor;
import com.google.gson.Gson;
import net.minecraft.client.Minecraft;
import net.minecraft.client.texture.Texture;
import net.minecraft.client.texture.TextureManager;
import net.minecraft.resource.Identifier;

import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class SkinFix {
	public static final Gson GSON = new Gson();
	public static final ExecutorService EXECUTOR = Executors.newCachedThreadPool();
	private static final Queue<Runnable> TO_EXECUTE_ON_MAIN_THREAD = new ConcurrentLinkedQueue<>();

	public static void schedule(final Runnable runnable) {
		TO_EXECUTE_ON_MAIN_THREAD.offer(runnable);
	}

	public static void unregister(final Identifier identifier) {
		final Minecraft minecraft = Minecraft.getInstance();
		final TextureManager textureManager = minecraft.getTextureManager();
		final TextureManagerAccessor accessor = (TextureManagerAccessor) textureManager;
		final Map<Identifier, Texture> textureMap = accessor.getTextures();

		textureMap.remove(identifier);
	}

	public static void tick() {
		if (TO_EXECUTE_ON_MAIN_THREAD.isEmpty()) {
			return;
		}

		final Runnable runnable = TO_EXECUTE_ON_MAIN_THREAD.poll();
		runnable.run();
	}
}

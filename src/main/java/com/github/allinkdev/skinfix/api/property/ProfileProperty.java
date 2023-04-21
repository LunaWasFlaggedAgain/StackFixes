package com.github.allinkdev.skinfix.api.property;

public final class ProfileProperty {
	private final String name;
	private final String value;

	public ProfileProperty(final String name, final String value) {
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return this.name;
	}

	public String getValue() {
		return this.value;
	}
}

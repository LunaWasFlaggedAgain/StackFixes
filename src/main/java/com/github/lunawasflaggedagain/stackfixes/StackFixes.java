package com.github.lunawasflaggedagain.stackfixes;

import com.github.lunawasflaggedagain.stackfixes.logging.WrappedPrintStream;

import java.io.PrintStream;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class StackFixes {
	private static final Class<Field> FIELD_CLASS = Field.class;
	private static final Class<System> SYSTEM_CLASS = System.class;

	private static void wrapPrintStream(String name) throws NoSuchFieldException, IllegalAccessException {
		Field field = SYSTEM_CLASS.getDeclaredField(name);
		Field modifierFieldField = FIELD_CLASS.getDeclaredField("modifiers");

		modifierFieldField.setAccessible(true);

		int modifierFieldValue = field.getModifiers();
		int mutatedModifierFieldValue = modifierFieldValue & ~Modifier.FINAL;

		modifierFieldField.setInt(field, mutatedModifierFieldValue);

		PrintStream value = (PrintStream) field.get(null);
		final WrappedPrintStream wrappedPrintStream = WrappedPrintStream.from(value, name);

		field.set(null, wrappedPrintStream);
		modifierFieldField.setInt(field, modifierFieldValue);
	}

	public static void preload() throws NoSuchFieldException, IllegalAccessException {
		wrapPrintStream("out");
		wrapPrintStream("err");
	}
}

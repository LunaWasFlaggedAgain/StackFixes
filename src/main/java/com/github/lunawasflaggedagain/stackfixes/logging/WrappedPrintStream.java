package com.github.lunawasflaggedagain.stackfixes.logging;

import net.minecraft.util.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.OutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;

public class WrappedPrintStream extends PrintStream {
	private final Logger logger;

	public static WrappedPrintStream from(PrintStream printStream, String loggerName) throws NoSuchFieldException, IllegalAccessException {
		Class<? extends PrintStream> printStreamClass = printStream.getClass();
		Field outField = printStreamClass.getSuperclass().getDeclaredField("out");

		outField.setAccessible(true);
		OutputStream value = (OutputStream) outField.get(printStream);

		return new WrappedPrintStream(value, loggerName);
	}

	WrappedPrintStream(@NotNull OutputStream out, String loggerName) {
		super(out);

		this.logger = new Logger("std" + loggerName, " [STD" + loggerName.toUpperCase() + "]", "std" + loggerName + ".log");
	}

	@Override
	public void println(@Nullable String x) {
		if (x.isEmpty()) {
			return;
		}

		this.logger.info(x);
	}

	@Override
	public void print(boolean b) {
		this.logObject(b);
	}

	private void logObject(final Object object) {
		String string = String.valueOf(object);

		this.logger.info(string);
	}

	@Override
	public void print(char c) {
		this.logObject(c);
	}

	@Override
	public void print(int i) {
		this.logObject(i);
	}

	@Override
	public void print(long l) {
		this.logObject(l);
	}

	@Override
	public void print(float f) {
		this.logObject(f);
	}

	@Override
	public void print(double d) {
		this.logObject(d);
	}

	@Override
	public void print(@NotNull char[] s) {
		if (s.length == 0) {
			return;
		}

		this.logObject(s);
	}

	@Override
	public void print(@Nullable String s) {
		if (s == null) {
			return;
		}

		if (s.isEmpty()) {
			return;
		}

		this.logObject(s);
	}

	@Override
	public void print(@Nullable Object obj) {
		if (obj == null) {
			return;
		}

		this.logObject(obj);
	}

	@Override
	public void println() {
		this.logger.info("");
	}

	@Override
	public void println(boolean x) {
		this.logObject(x);
	}

	@Override
	public void println(char x) {
		if (x == ' ' || x == '\r') {
			return;
		}

		this.logObject(x);
	}

	@Override
	public void println(int x) {
		this.logObject(x);
	}

	@Override
	public void println(long x) {
		this.logObject(x);
	}

	@Override
	public void println(float x) {
		this.logObject(x);
	}

	@Override
	public void println(double x) {
		this.logObject(x);
	}

	@Override
	public void println(@NotNull char[] x) {
		if (x.length == 0) {
			return;
		}

		this.logObject(x);
	}

	@Override
	public void println(@Nullable Object x) {
		this.logObject(x);
	}
}

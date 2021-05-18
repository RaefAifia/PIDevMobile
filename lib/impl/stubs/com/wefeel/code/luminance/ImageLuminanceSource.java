package com.wefeel.code.luminance;


public class ImageLuminanceSource extends com.google.zxing.LuminanceSource {

	public ImageLuminanceSource(com.codename1.ui.Image img) {
	}

	@java.lang.Override
	public byte[] getRow(int y, byte[] row) {
	}

	@java.lang.Override
	public byte[] getMatrix() {
	}

	@java.lang.Override
	public boolean isCropSupported() {
	}

	@java.lang.Override
	public ImageLuminanceSource crop(int left, int top, int width, int height) {
	}

	@java.lang.Override
	public boolean isRotateSupported() {
	}

	@java.lang.Override
	public ImageLuminanceSource rotateCounterClockwise() {
	}

	public static ImageLuminanceSource createLuminanceSourceFromJpeg(byte[] jpeg) {
	}
}

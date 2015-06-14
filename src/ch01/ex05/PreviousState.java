package ch01.ex05;

public class PreviousState {
	private String fontFamily;
	private int fontSize;
	private Colors textColor;
	private Colors backgroundColor;

	public String getFontFamily() {
		return fontFamily;
	}

	public void setFontFamily(String fontFamily) {
		this.fontFamily = fontFamily;
	}

	public int getFontSize() {
		return fontSize;
	}

	public void setFontSize(int fontSize) {
		this.fontSize = fontSize;
	}

	public Colors getTextColor() {
		return textColor;
	}

	public void setTextColor(Colors textColor) {
		this.textColor = textColor;
	}

	public Colors getBackgroundColor() {
		return backgroundColor;
	}

	public void setBackgroundColor(Colors backgroundColor) {
		this.backgroundColor = backgroundColor;
	}
}

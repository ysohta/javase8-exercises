package ch01.ex05;

import java.awt.Color;

public enum Colors {
	BLACK {
		@Override
		public Color get() {
			return Color.BLACK;
		}
	},
	BLUE {
		@Override
		public Color get() {
			return Color.BLUE;
		}
	},
	CYAN {
		@Override
		public Color get() {
			return Color.CYAN;
		}
	},
	DARK_GRAY {
		@Override
		public Color get() {
			return Color.DARK_GRAY;
		}
	},
	GRAY {
		@Override
		public Color get() {
			return Color.GRAY;
		}
	},
	GREEN {
		@Override
		public Color get() {
			return Color.GREEN;
		}
	},
	LIGHT_GRAY {
		@Override
		public Color get() {
			return Color.LIGHT_GRAY;
		}
	},
	MAGENTA {
		@Override
		public Color get() {
			return Color.MAGENTA;
		}
	},
	ORANGE {
		@Override
		public Color get() {
			return Color.ORANGE;
		}
	},
	PINK {
		@Override
		public Color get() {
			return Color.PINK;
		}
	},
	RED {
		@Override
		public Color get() {
			return Color.RED;
		}
	},
	WHITE {
		@Override
		public Color get() {
			return Color.WHITE;
		}
	},
	YELLOW {
		@Override
		public Color get() {
			return Color.YELLOW;
		}
	};

	public abstract Color get();
	
	public static Colors convert(Color color){
		if(color ==Color.BLACK){
			return BLACK;
		}else if(color ==Color.BLUE){
			return BLUE;
		}else if(color ==Color.CYAN){
			return CYAN;
		}else if(color ==Color.DARK_GRAY){
			return DARK_GRAY;
		}else if(color ==Color.GREEN){
			return GREEN;
		}else if(color ==Color.LIGHT_GRAY){
			return LIGHT_GRAY;
		}else if(color ==Color.MAGENTA){
			return MAGENTA;
		}else if(color ==Color.ORANGE){
			return ORANGE;
		}else if(color ==Color.PINK){
			return PINK;
		}else if(color ==Color.RED){
			return RED;
		}else if(color ==Color.WHITE){
			return WHITE;
		}else if(color ==Color.YELLOW){
			return YELLOW;
		}else {
			throw new IllegalArgumentException("Can not convert the color");
		}
	}
}

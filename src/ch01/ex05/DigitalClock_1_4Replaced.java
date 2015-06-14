package ch01.ex05;

import java.awt.Font;
import java.awt.Frame;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Timer;
import java.util.TimerTask;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

public class DigitalClock_1_4Replaced extends Frame {

	public static final String TITLE = "Clock";
	public static final String MENU_VIEW = "Menu";
	public static final String MENU_SAVE = "Save";
	public static final String MENU_PROP = "Properties";
	public static final String MENU_CLOSE = "Close";

	public static final int DEFAULT_LOCATION_X = 100;
	public static final int DEFAULT_LOCATION_Y = 100;
	public static final String DEFAULT_FONT_FAMILY = Font.DIALOG;
	public static final int DEFAULT_FONT_STYLE = Font.PLAIN;
	public static final int DEFAULT_MENU_FONTSIZE = 12;
	public static final int TIME_SPAN = 1000;
	public static int[] fontSizeList = { 36, 72, 144, 288 };
	public static final Colors DEFAULT_TEXT_COLOR = Colors.BLACK;
	public static final Colors DEFAULT_BACKGROUND_COLOR = Colors.WHITE;

	private static final String KEY_LOCATION_X = "x";
	private static final String KEY_LOCATION_Y = "y";
	private static final String KEY_FONT_FAMILY = "FontFamily";
	private static final String KEY_FONT_SIZE = "FontSize";
	private static final String KEY_TEXT_COLOR = "TextColor";
	private static final String KEY_BACKGROUND_COLOR = "BackgroundColor";

	private Timer timer;
	private Display display;
	private MenuBar menuBar;
	private DialogMenu dialog;
	private Preferences prefs;

	public static void main(String[] args) {
		new DigitalClock_1_4(TITLE);
	}

	public DigitalClock_1_4Replaced(String title) throws HeadlessException {
		super(title);
		initialize();
		prefs = Preferences.userNodeForPackage(this.getClass());
		load();
	}

	public void save() {
		try {
			prefs.putInt(KEY_LOCATION_X, getLocation().x);
			prefs.putInt(KEY_LOCATION_Y, getLocation().y);
			prefs.put(KEY_FONT_FAMILY, getFontFamily());
			prefs.putInt(KEY_FONT_SIZE, getFontSize());
			prefs.put(KEY_TEXT_COLOR, getTextColor().toString());
			prefs.put(KEY_BACKGROUND_COLOR, getBackgroundColor().toString());
			prefs.flush();
		} catch (BackingStoreException ex) {
			ex.printStackTrace();
		}
	}

	public void load() {
		setLocation(prefs.getInt(KEY_LOCATION_X, DEFAULT_LOCATION_X), prefs
				.getInt(KEY_LOCATION_Y, DEFAULT_LOCATION_Y));
		setFontFamily(prefs.get(KEY_FONT_FAMILY, DEFAULT_FONT_FAMILY));
		setFontSize(prefs.getInt(KEY_FONT_SIZE, fontSizeList[0]));
		setTextColor(Colors.valueOf(prefs.get(KEY_TEXT_COLOR,
				DEFAULT_TEXT_COLOR.toString())));
		setBackgroundColor(Colors.valueOf(prefs.get(KEY_BACKGROUND_COLOR,
				DEFAULT_BACKGROUND_COLOR.toString())));
	}

	public String getFontFamily() {
		Font font = display.getFont();
		return font.getFamily();
	}

	public void setFontFamily(String fontfamily) {
		Font font = display.getFont();
		display.setFont(new Font(fontfamily, font.getStyle(), font.getSize()));
		display.repaint();
	}

	public int getFontSize() {
		Font font = display.getFont();
		return font.getSize();
	}

	public void setFontSize(int fontSize) {
		Font font = display.getFont();
		display.setFont(new Font(font.getFamily(), font.getStyle(), fontSize));
		display.repaint();
	}

	public Colors getTextColor() {
		Colors c = Colors.convert(display.getTextColor());
		return c;
	}

	public void setTextColor(Colors c) {
		display.setTextColor(c.get());
		display.repaint();
	}

	public Colors getBackgroundColor() {
		Colors c = Colors.convert(display.getBackground());
		return c;
	}

	public void setBackgroundColor(Colors c) {
		display.setBackground(c.get());
		display.repaint();
	}

	public void clearDialog() {
		dialog = null;
	}
	


	protected final void initialize() {
		setVisible(true);
		setLocation(DEFAULT_LOCATION_X, DEFAULT_LOCATION_Y);
		setSize(1, 1);
		setResizable(false);

		initTimer();

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				;
			}
		});

		display = new Display(this);
		display.setFont(new Font(DEFAULT_FONT_FAMILY, DEFAULT_FONT_STYLE,
				fontSizeList[0]));
		display.setSize(100, 100);
		add(display);

		createMenu();
		dialog = new DialogMenu(this);
	}

	private void initTimer() {
		timer = new Timer();
		timer.schedule(new TimerTask() {
			public void run() {
				if (display != null) {
					display.repaint(); 
				}
			}
		}, 0, TIME_SPAN);
	}

	private void createMenu() {
		menuBar = new MenuBar();
		menuBar.setFont(new Font(DEFAULT_FONT_FAMILY, DEFAULT_FONT_STYLE,
				DEFAULT_MENU_FONTSIZE));
		setMenuBar(menuBar);
		Menu menuView = new Menu(MENU_VIEW);
		menuBar.add(menuView);

		// MENU Save
		MenuItem save = new MenuItem(MENU_SAVE);
		menuView.add(save);
		save.addActionListener((e) -> save());	// replaced

		menuView.addSeparator();

		// MENU Properties
		MenuItem prop = new MenuItem(MENU_PROP);
		menuView.add(prop);
		prop.addActionListener((e) -> dialog.show());	// replaced
		
		menuView.addSeparator();

		// MENU Close
		MenuItem menuClose = new MenuItem(MENU_CLOSE);
		menuClose.addActionListener((e) -> System.exit(0));	// replaced
		menuView.add(menuClose);
	}
}

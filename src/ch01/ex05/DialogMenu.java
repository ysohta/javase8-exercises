package ch01.ex05;

import java.awt.Button;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Label;
import java.awt.List;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

@SuppressWarnings("serial")
public class DialogMenu extends Dialog {

	public static final String MENU_VIEW = "Menu";
	public static final String MENU_FONT_FAMILY = "Font Family";
	public static final String MENU_FONT_SIZE = "Font Size";
	public static final String MENU_TEXT_COLOR = "Text Color";
	public static final String MENU_BACKGROUND_COLOR = "Background Color";
	public static final String MENU_CLOSE = "Close";

	public static final String DEFAULT_FONT_FAMILY = Font.DIALOG;
	public static final int DEFAULT_FONT_STYLE = Font.PLAIN;
	public static final int DEFAULT_MENU_FONTSIZE = 12;
	public static final int TIME_SPAN = 1000;
	public static int[] fontSizeList = { 36, 72, 144, 288 };

	public static final int POSITION_X = 50;
	public static final int POSITION_Y = 50;
	public static final int DEFAULT_WIDTH = 300;
	public static final int DEFAULT_HEIGHT = 400;

	private GridBagLayout gbl = new GridBagLayout();
	private List listTextColor = new List();
	private List listBackColor = new List();
	private List listFontSize = new List();
	private List listFontFamily = new List();
	private DigitalClock_1_4 owner;
	private PreviousState previous;

	public DialogMenu(Frame owner) {
		super(owner);
		this.owner = (DigitalClock_1_4) owner;

		setTitle(DigitalClock_1_4.MENU_PROP);
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		setResizable(false);
		setLocation(owner.getLocation().x + POSITION_X, owner.getLocation().y
				+ POSITION_Y);

		setupButtons();
		setupLists();

		previous = new PreviousState();

		setMenuFontFamily();
		setMenuFontSize();
		setMenuColorRelated();
	}

	@Override
	public Insets getInsets() {
		return new Insets(30, 10, 10, 10);
	}

	private void setupLists() {
		setLayout(gbl);
		addComponent(new Label(MENU_FONT_FAMILY), 0, 0, 1, 1,
				GridBagConstraints.EAST);
		addComponent(listFontFamily, 1, 0, 2, 1, GridBagConstraints.WEST,
				GridBagConstraints.HORIZONTAL);

		addComponent(new Label(MENU_FONT_SIZE), 0, 1, 1, 1,
				GridBagConstraints.EAST);
		addComponent(listFontSize, 1, 1, 2, 1, GridBagConstraints.WEST,
				GridBagConstraints.HORIZONTAL);

		addComponent(new Label(MENU_TEXT_COLOR), 0, 2, 1, 1,
				GridBagConstraints.EAST);
		addComponent(listTextColor, 1, 2, 2, 1, GridBagConstraints.WEST,
				GridBagConstraints.HORIZONTAL);

		addComponent(new Label(MENU_BACKGROUND_COLOR), 0, 3, 1, 1,
				GridBagConstraints.EAST);
		addComponent(listBackColor, 1, 3, 2, 1, GridBagConstraints.WEST,
				GridBagConstraints.HORIZONTAL);

		listFontFamily.addItemListener(new FontFamilyItemListener());
		listFontSize.addItemListener(new FontSizeItemListener());
		listTextColor.addItemListener(new TextColorItemListener());
		listBackColor.addItemListener(new BackgroundColorItemListener());
	}

	private void setupButtons() {
		Panel panel = new Panel();
		panel.setLayout(new FlowLayout(FlowLayout.RIGHT));

		Button btnOK = new Button("OK");
		btnOK.addActionListener(new OKActionListner());
		panel.add(btnOK);

		Button btnCancel = new Button("Cancel");
		btnCancel.addActionListener(new CancelActionListner());
		panel.add(btnCancel);

		addComponent(panel, 2, 4, 1, 1, GridBagConstraints.EAST);

		// �E�B���h�E�����ۂ̃C�x���g��ݒ�
		addWindowListener(new CloseActionListner());
	}

	void addComponent(Component c, int x, int y, int w, int h, int anchor) {
		addComponent(c, x, y, w, h, anchor, GridBagConstraints.NONE);
	}

	void addComponent(Component c, int x, int y, int w, int h, int anchor,
			int fill) {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = fill;
		gbc.anchor = anchor;
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.gridwidth = w;
		gbc.gridheight = h;
		gbc.weightx = 90.0;
		gbc.weighty = 90.0;
		gbl.setConstraints(c, gbc);
		add(c);
	}

	/**
	 * �F�Ɋ֌W���郁�j���[��ݒ肷��
	 * */
	private void setMenuColorRelated() {

		Colors colorText = owner.getTextColor();
		Colors colorBack = owner.getBackgroundColor();

		previous.setTextColor(colorText);
		previous.setBackgroundColor(colorBack);

		for (final Colors colors : Colors.values()) {
			listTextColor.add(colors.name());
			listBackColor.add(colors.name());
		}

		listTextColor.select(colorText.ordinal());
		listBackColor.select(colorBack.ordinal());
	}

	/**
	 * �t�H���g�T�C�Y�Ɋւ��郁�j���[��ݒ肷��
	 * */
	private void setMenuFontSize() {
		int fontSizeNow = owner.getFontSize();
		previous.setFontSize(fontSizeNow);

		for (int i = 0; i < fontSizeList.length; i++) {
			listFontSize.add(Integer.toString(fontSizeList[i]));
			if (fontSizeList[i] == fontSizeNow) {
				listFontSize.select(i);
			}
		}
	}

	/**
	 * �t�H���g�Ɋւ��郁�j���[��ݒ肷��
	 * */
	private void setMenuFontFamily() {
		// �t�H���g�̃��X�g���擾
		GraphicsEnvironment env = GraphicsEnvironment
				.getLocalGraphicsEnvironment();
		String[] fonts = env.getAvailableFontFamilyNames();

		String fontFamily = owner.getFontFamily();
		previous.setFontFamily(fontFamily);

		for (int i = 0; i < fonts.length; i++) {
			listFontFamily.add(fonts[i]);
			if (fonts[i].equals(fontFamily)) {
				listFontFamily.select(i);

			}
		}
	}

	class FontFamilyItemListener implements ItemListener {
		@Override
		public void itemStateChanged(ItemEvent e) {
			owner.setFontFamily(listFontFamily.getSelectedItem());
		}
	}

	class FontSizeItemListener implements ItemListener {
		@Override
		public void itemStateChanged(ItemEvent e) {
			owner.setFontSize(Integer.parseInt(listFontSize.getSelectedItem()));
		}
	}

	class TextColorItemListener implements ItemListener {
		@Override
		public void itemStateChanged(ItemEvent e) {
			owner.setTextColor(Colors.valueOf(listTextColor.getSelectedItem()));
		}
	}

	class BackgroundColorItemListener implements ItemListener {
		@Override
		public void itemStateChanged(ItemEvent e) {
			owner.setBackgroundColor(Colors.valueOf(listBackColor
					.getSelectedItem()));
		}
	}

	class OKActionListner implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			close();
		}
	}

	class CancelActionListner implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			setPrevious();
			close();
		}
	}

	class CloseActionListner extends WindowAdapter {
		public void windowClosing(WindowEvent e) {
			setPrevious();
			close();
		}
	}

	private void setPrevious() {
		owner.setFontFamily(previous.getFontFamily());
		owner.setFontSize(previous.getFontSize());
		owner.setTextColor(previous.getTextColor());
		owner.setBackgroundColor(previous.getBackgroundColor());
	}

	private void close() {
		dispose();
		owner.clearDialog();
	}
}

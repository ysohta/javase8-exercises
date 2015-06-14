package ch01.ex05;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.font.TextAttribute;
import java.text.AttributedString;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Display extends Canvas {

	public static final int MARGIN = 30;
	private Component parent;
	private Color textColor = Color.BLACK;
	private Image buf;

	private Graphics backGraphics;
	private Dimension desiredDimension = new Dimension(1, 1);

	/**
	 * �R���X�g���N�^
	 * */
	public Display(Component parent) {
		this.parent = parent;
		init();
	}

	public Color getTextColor() {
		return textColor;
	}

	public void setTextColor(Color textColor) {
		this.textColor = textColor;
	}

	public void init() {
		buf = createImage(desiredDimension.width, desiredDimension.height);
		setSize(desiredDimension); // �����\���̂��߂ɕK�v
	}

	private void drawTime(Graphics g) {

		// �������擾
		Calendar rightNow = Calendar.getInstance();
		Date dateNow = rightNow.getTime();
		SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm:ss");

		// �����𕶎���Ŏ擾
		String str = sdf1.format(dateNow);

		// �t�H���g������������ɕϊ�
		AttributedString attrStr = new AttributedString(str);
		Font font = getFont();
		attrStr.addAttribute(TextAttribute.FONT, font);
		attrStr.addAttribute(TextAttribute.FOREGROUND, textColor);
		FontMetrics fm = g.getFontMetrics(font);

		// �t���[���̑傫�������肷�邽�߂̃f�t�H���g�̕���
		String strForSize = "00:00:00";

		// �t�H���g������������̑傫�����擾
		int strWidth = fm.stringWidth(strForSize);
		int strHeight = fm.getHeight();

		// �t���[���̑傫����ݒ�
		desiredDimension.setSize(strWidth + MARGIN * 2, strHeight + MARGIN * 2);

		setSize(desiredDimension);
		parent.setSize(desiredDimension);

		// �������z�u����ʒu
		int x = (desiredDimension.width - fm.stringWidth(str)) / 2;
		int y = (fm.getAscent() + (desiredDimension.height - (fm.getAscent() + fm
				.getDescent())) / 2)
				- MARGIN;

		// ������`��
		g.drawString(attrStr.getIterator(), x, y);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		init();
		backGraphics = buf.getGraphics();
		drawTime(backGraphics);

		g.drawImage(buf, 0, 0, this);

	}
}

package ch09.ex12;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This is sample applet class using {@link ch09.ex12.LabeledPoint}.
 * Created by yukiohta on 2015/11/17.
 */
public class MyApplet extends Applet implements ActionListener {
    private static final String INITIAL_TEXT = "Hello World!";
    private static final int INITIAL_X = 30;
    private static final int INITIAL_Y = 30;

    private TextField[] textAreas = new TextField[3];
    private MyCanvas canvas;

    @Override
    public void init() {
        setSize(400, 250);
        setBackground(Color.white);
        setLayout(new FlowLayout(FlowLayout.LEFT));

        // left
        Panel left = new Panel();
        add(left);
        left.setLayout(new GridLayout(4, 2));

        // X
        left.add(new Label("x:"));
        textAreas[0] = new TextField(10);
        textAreas[0].setText(Integer.toString(INITIAL_X));
        left.add(textAreas[0]);

        // Y
        left.add(new Label("y:"));
        textAreas[1] = new TextField(10);
        textAreas[1].setText(Integer.toString(INITIAL_Y));
        left.add(textAreas[1]);

        // text
        left.add(new Label("text:"));
        textAreas[2] = new TextField(10);
        textAreas[2].setText(INITIAL_TEXT);
        left.add(textAreas[2]);

        // Button
        Button btn = new Button("Commit");
        btn.addActionListener(this);
        left.add(btn);

        // right
        this.canvas = new MyCanvas();
        add(canvas);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int x = 0;
        int y = 0;
        String text;
        try {
            x = Integer.parseInt(textAreas[0].getText());
        } catch (NumberFormatException ex) {
            textAreas[0].setText("0");
        }

        try {
            y = Integer.parseInt(textAreas[1].getText());
        } catch (NumberFormatException ex) {
            textAreas[1].setText("0");
        }

        text = textAreas[2].getText();

        canvas.setPoint(new LabeledPoint(text, x, y));
        canvas.repaint();
    }

    class MyCanvas extends Canvas {
        private LabeledPoint point;

        public MyCanvas() {
            point = new LabeledPoint(INITIAL_TEXT, INITIAL_X, INITIAL_Y);
            setSize(200, 200);
            setBackground(Color.gray);
        }

        public LabeledPoint getPoint() {
            return point;
        }

        public void setPoint(LabeledPoint point) {
            this.point = point;
        }

        @Override
        public void paint(Graphics g) {
            g.drawString(point.getLabel(), point.getX(), point.getY());
        }
    }
}

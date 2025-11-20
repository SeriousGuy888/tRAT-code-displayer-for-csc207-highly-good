package io.github.seriousguy888.view;

import io.github.seriousguy888.interface_adapter.PresentationViewModel;

import javax.swing.*;
import java.awt.*;

// mostly vibecoded this class :(
public class PresentationView extends JPanel {
    private final PresentationViewModel presentationViewModel;
    private Image backgroundImage;

    // Flashing animation state
    private float alpha = 1.0f;      // current opacity of text
    private float delta = -0.02f;    // how fast the opacity changes each frame
    private float lowerBound = 0.5f;
    private float upperBound = 1f;

    public PresentationView(PresentationViewModel presentationViewModel) {
        this.presentationViewModel = presentationViewModel;

        setOpaque(false);
        setLayout(null); // We draw everything manually

        // Timer fires ~60fps: adjust alpha each tick
        Timer timer = new Timer(16, e -> {
            alpha += delta;

            // Reverse direction when hitting 0 or 1
            if (alpha <= lowerBound) {
                alpha = lowerBound;
                delta = -delta;
            } else if (alpha >= upperBound) {
                alpha = upperBound;
                delta = -delta;
            }

            repaint();
        });

        timer.start();
    }


    public void setBackgroundImage(Image image) {
        this.backgroundImage = image;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        String text = presentationViewModel.getState();

        Graphics2D g2 = (Graphics2D) g.create();

        // Draw background image
        if (backgroundImage != null) {
            g2.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }

        // Draw flashing text
        if (text != null && !text.isEmpty()) {
            g2.setFont(getFont().deriveFont(Font.BOLD, 128));

            // Apply alpha via composite
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
            g2.setColor(Color.BLACK);

            FontMetrics fm = g2.getFontMetrics();
            int textWidth = fm.stringWidth(text);
            int textHeight = fm.getAscent();

            int x = (getWidth() - textWidth) / 2;
            int y = (getHeight() + textHeight) / 2;

            g2.drawString(text, x, y);
        }

        g2.dispose();
    }

    public String getViewName() {
        return presentationViewModel.getViewName();
    }
}
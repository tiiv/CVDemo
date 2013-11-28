import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

import javax.swing.JComponent;

public class CalendarView extends JComponent {

    protected final CalendarDataSource source;

    public CalendarView(CalendarDataSource source) {
        this.source = source;
    }

    @Override
    public Dimension getMinimumSize() {
        return this.getPreferredSize();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(640, 480);
    }

    @Override
    public void paintComponent(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                             RenderingHints.VALUE_ANTIALIAS_ON);

        g.setColor(Color.WHITE);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());

        int width = this.getWidth() / 7;
        int height = this.getHeight() / 7;

        g.setColor(Color.BLACK);
        g.setFont(new Font("Monospace", Font.BOLD, 20));
        FontMetrics metrics = g.getFontMetrics();

        int column = 0;
        int row = 0;
        String day = "Mon";
        int offset = column * width;
        int padding = (width - metrics.stringWidth(day)) / 2;
        int x = offset + padding;
        offset = (row + 1) * height;
        padding = (width - metrics.getHeight()) / 2 - metrics.getMaxDescent();
        int y = offset - padding;
        g.drawString(day, x, y);

        column++;
        day = "Tue";
        x = column * width + (width - metrics.stringWidth(day)) / 2;
        g.drawString(day, x, y);

        column++;
        day = "Wed";
        x = column * width + (width - metrics.stringWidth(day)) / 2;
        g.drawString(day, x, y);

        column++;
        day = "Thu";
        x = column * width + (width - metrics.stringWidth(day)) / 2;
        g.drawString(day, x, y);

        column++;
        day = "Fri";
        x = column * width + (width - metrics.stringWidth(day)) / 2;
        g.drawString(day, x, y);

        column++;
        day = "Sat";
        x = column * width + (width - metrics.stringWidth(day)) / 2;
        g.drawString(day, x, y);

        column++;
        day = "Sun";
        x = column * width + (width - metrics.stringWidth(day)) / 2;
        g.drawString(day, x, y);

        row++;

        boolean newline = false;
        int maxDays = this.source.getNumberOfDays();
        for (int i = 1; i <= maxDays; i++) {

            if (newline) {
                row++;
                newline = false;
            }

            int weekday = this.source.getWeekDay(i);
            if (this.source.getDay() == i) {
                g.setFont(new Font("Monospace", Font.BOLD, 20));
                metrics = g.getFontMetrics();
                int diameter = 40;
                x = (weekday - 1) * width + width / 2 - diameter / 2;
                offset  = (row + 1) * height;
                padding = (height - metrics.getHeight()) / 2
                        + metrics.getMaxDescent()
                        + metrics.getMaxAscent() / 2
                        - 2
                        + diameter / 2;
                y = offset - padding;
                g.setColor(new Color(255, 50, 50));
                g.fillOval(x, y, diameter, diameter);
                g.setColor(Color.WHITE);
            } else {
                g.setColor(Color.BLACK);
                g.setFont(new Font("Monospace", Font.PLAIN, 20));
                metrics = g.getFontMetrics();
            }

            offset = (weekday - 1) * width;
            padding = (width - metrics.stringWidth(String.valueOf(i))) / 2;
            x = offset + padding;

            offset = (row + 1) * height;
            padding = (height - metrics.getHeight()) / 2 + metrics.getMaxDescent();
            y = offset - padding;

            g.drawString(String.valueOf(i), x, y);

            if (weekday == 7) {
                newline = true;
            }
        }
    }
}

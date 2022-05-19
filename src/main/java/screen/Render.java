package screen;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class Render extends Canvas {

    public Render() {
        // Configuration of the Canvas
        setFocusable(true);
        setVisible(true);
        setBackground(Color.BLACK);
    }

    public void start() {
        createBufferStrategy(3);
        render();
    }

    public void render() {
        BufferStrategy bufferStrategy = this.getBufferStrategy();
        Graphics graphics = bufferStrategy.getDrawGraphics();

        // Show
        bufferStrategy.show();
        graphics.dispose();
    }
}

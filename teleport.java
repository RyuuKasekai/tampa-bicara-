import java.awt.*;
import javax.swing.*;

public class Teleport extends JPanel {

    private String[] lyrics = {
            "Kasih maafkan diri ini",
            "Menyakiti dirimu",
            "Akupun menyesal berpaling darimu oohh",
            "Kata maafpun tak bisa ",
            "milikimu apalagi",
            "namun boleh kanku simpan cerita ini sendiri",

    };

    private int[] delays = {
            1000, 3000, 5000, 3000, 200, 
    };

    private int currentIndex = 0;
    private String currentLine = "";
    private int currentCharIndex = 0;
    private ImageIcon backgroundGif;

    public Teleport() {
        setPreferredSize(new Dimension(800, 600));
        backgroundGif = new ImageIcon("space.gif");
        new Thread(() -> {
            try {
                while (currentIndex < lyrics.length) {
                    if (currentCharIndex < lyrics[currentIndex].length()) {
                        currentLine += lyrics[currentIndex].charAt(currentCharIndex);
                        currentCharIndex++;
                        repaint();
                        Thread.sleep(130);
                    } else {
                        Thread.sleep(delays[currentIndex]);
                        currentIndex++;
                        currentLine = "";
                        currentCharIndex = 0;
                        repaint();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundGif != null) {
            g.drawImage(backgroundGif.getImage(), 0, 0, getWidth(), getHeight(), this);
        }
        g.setFont(new Font("Serif", Font.PLAIN, 24));
        g.setColor(Color.WHITE);

        int y = (getHeight() - lyrics.length * 30) / 2;

        int stringWidth = g.getFontMetrics().stringWidth(currentLine);
        int x = (getWidth() - stringWidth) / 2;
        g.drawString(currentLine, x, y + currentIndex * 30);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Lyrics");
        Teleport panel = new Teleport();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

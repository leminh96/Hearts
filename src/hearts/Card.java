package hearts;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Card extends GUI.GameElement {

    private final ArrayList<myCard> cards;
    private static Image backgroundImage;
    private boolean mouseHover = false;

    private Card() {
        this.cards = new ArrayList();
    }

    public static Card getInstance() {
        return CardHolder.instance;
    }

    private static class CardHolder {

        private static final Card instance = new Card();
    }

    public class myCard {

        private final int id;
        private final String name;
        private int x;
        private int y;

        public myCard(int id, String name, int x, int y) {
            this.id = id;
            this.name = name;
            this.x = x;
            this.y = y;
        }
    }

    public void initCard() {
        int n = 1;
        for (int i = 1; i < 14; i++) {
            for (int j = 0; j < 4; j++) {
                String temp = Integer.toString(i);
                switch (j) {
                    case 0: {
                        temp += "S";
                        break;
                    }
                    case 1: {
                        temp += "C";
                        break;
                    }
                    case 2: {
                        temp += "D";
                        break;
                    }
                    case 3: {
                        temp += "H";
                        break;
                    }
                }
                temp += ".png";
                myCard c = new myCard(n, temp, 0, 0);
                n++;
                cards.add(c);
            }
        }
    }

    public String getCardsName(int i) {
        return this.cards.get(i).name;
    }
    
    public int getXCard(int i)
    {
        return cards.get(i).x;
    }
    
    public void setXCard(int i, int x)
    {
        cards.get(i).x = x;
    }
    
    public int getYCard(int i)
    {
        return cards.get(i).y;
    }
    
    public void setYCard(int i, int y)
    {
        cards.get(i).y = y;
    }

    @Override
    public void paint(Graphics g) {
        try {
            backgroundImage = ImageIO.read(new File("images/background.jpg"));
        } catch (IOException ex) {
        }
        Graphics2D gg = (Graphics2D) g;
        gg.drawImage(backgroundImage, 0, 0, null);
        for (int i = 0; i < 13; i++) {
            Card.getInstance().initCard();
            Image im = new ImageIcon("images/" + Card.getInstance().getCardsName(i)).getImage();
            gg.drawImage(im, 350 + i * 25, 600, null);
            cards.get(i).x = 350 + i * 25;
            cards.get(i).y = 600;
            
            Image imb = new ImageIcon("images/back.png").getImage();
            AffineTransform transform = new AffineTransform();

            transform.setToTranslation(250, 200 + 25 * i);
            transform.rotate(Math.PI / 2);
            gg.drawImage(imb, transform, null);

            transform.setToTranslation(800, 270 + 25 * i);
            transform.rotate(-Math.PI / 2);
            gg.drawImage(imb, transform, null);

            transform.setToTranslation(420 + i * 25, 150);
            transform.rotate(Math.PI);
            gg.drawImage(imb, transform, null);
        }
        
        if (mouseHover) {
            gg.setStroke(new BasicStroke(2));
            gg.setColor(Color.yellow);
            gg.draw(new Rectangle2D.Float(350, 600, 70, 95));
        }
    }

    public void setMouseHover() {
        this.mouseHover = true;
    }
}

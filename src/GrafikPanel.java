import javax.swing.*;
import java.awt.*;

public class GrafikPanel extends JPanel
{
    public int squareSize = 10;

    public GrafikPanel(int input){
        squareSize = input;
    }

    public void paintComponent(Graphics g)
    {

        // Herunder referer g til et Graphics-objekt man kan tegne med
        super.paintComponent(g);                // tegn først baggrunden på panelet

        boolean test = true;

        for (int j = 0; j < 8; j++) {
            for (int i = 0; i < 8; i++) {
                if(test){
                    g.setColor(Color.white);

                    g.fillRect(i*squareSize,j*squareSize,squareSize,squareSize);
                    test = false;
                } else {
                    g.setColor(Color.black);
                    g.fillRect(i*squareSize,j*squareSize,squareSize,squareSize);
                    test = true;
                }
            }
            test = !test;
        }
    }
}
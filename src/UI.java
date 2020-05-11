import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UI {


    public boolean muteon = true;
    public int temp1, temp2;
    public BoardLogic boardLogic;

    public UI() {
        musicSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                System.out.println(musicSlider.getValue());
                boardLogic.sounds.setMusicvolume(musicSlider.getValue());
            }
        });
        muteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Mute!");
                if (muteon) {
                    System.out.println("unmute");
                    muteButton.setText("Unmute All");
                    temp1 = sfxSlider.getValue();
                    temp2 = musicSlider.getValue();
                    boardLogic.sounds.setMusicvolume(0);
                    boardLogic.sounds.setSfxvolume(0);
                    sfxSlider.setValue(0);
                    musicSlider.setValue(0);
                    muteon = !muteon;
                } else {
                    sfxSlider.setValue(temp1);
                    musicSlider.setValue(temp2);
                    boardLogic.sounds.setMusicvolume(temp2);
                    boardLogic.sounds.setSfxvolume(temp1);
                    System.out.println("mute now");
                    muteButton.setText("Mute All");
                    muteon = !muteon;
                }
            }
        });
        newGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Object[] options = {"Yes, please",
                        "No, cancel"};
                int n = JOptionPane.showOptionDialog(JOptionPane.getRootFrame(),
                        "Want to create a new game?",
                        "Warning",
                        JOptionPane.YES_NO_CANCEL_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,
                        options[1]);

                System.out.println("New game! " + n);

                if (n == 0) {
                    boardLogic.newGame();
                    roundsLabel.setText("Total moves: 0");
                    turnLabel.setText("White, make a move!");
                }
                /*String spm = "Want to create a new game?";
                String svar = JOptionPane.showInputDialog(spm, "ja");*/

            }
        });
        sfxSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                System.out.println(sfxSlider.getValue());
                boardLogic.sounds.setSfxvolume(sfxSlider.getValue());
            }
        });
        undo1MoveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Undo was pressed");
                boardLogic.Undo(boardLogic.undoFromX, boardLogic.undoFromY, boardLogic.undoToX, boardLogic.undoToY, boardLogic.boardTracking, boardLogic.pieceLogic);
            }
        });
    }

    public void upDate(boolean whiteTurn, int moves) {
        roundsLabel.setText("Total moves: " + moves);
        if (whiteTurn) {
            turnLabel.setText("White, make a move!");
        } else {
            turnLabel.setText("Black, make a move!");
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("UI");
        frame.setContentPane(new UI().UI_Panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private JButton playButton;
    private JButton stopButton;
    private JSlider musicSlider;
    private JButton muteButton;
    private JPanel UI_Panel;
    private JButton newGameButton;
    private JSlider sfxSlider;
    private JLabel turnLabel;
    private JLabel roundsLabel;
    private JButton undo1MoveButton;

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        UI_Panel = new JPanel();
        UI_Panel.setLayout(new GridBagLayout());
        final JLabel label1 = new JLabel();
        Font label1Font = this.$$$getFont$$$("Arial Black", Font.BOLD, 16, label1.getFont());
        if (label1Font != null) label1.setFont(label1Font);
        label1.setText("Sound Options");
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.WEST;
        UI_Panel.add(label1, gbc);
        final JPanel spacer1 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        UI_Panel.add(spacer1, gbc);
        final JPanel spacer2 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 15;
        gbc.fill = GridBagConstraints.VERTICAL;
        UI_Panel.add(spacer2, gbc);
        musicSlider = new JSlider();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        UI_Panel.add(musicSlider, gbc);
        final JLabel label2 = new JLabel();
        label2.setText("Music Volume");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.anchor = GridBagConstraints.WEST;
        UI_Panel.add(label2, gbc);
        muteButton = new JButton();
        muteButton.setText("Mute All");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 10;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        UI_Panel.add(muteButton, gbc);
        final JPanel spacer3 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 11;
        gbc.fill = GridBagConstraints.VERTICAL;
        UI_Panel.add(spacer3, gbc);
        final JLabel label3 = new JLabel();
        Font label3Font = this.$$$getFont$$$("Arial Black", Font.BOLD, 16, label3.getFont());
        if (label3Font != null) label3.setFont(label3Font);
        label3.setText("Game Options");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 12;
        gbc.anchor = GridBagConstraints.WEST;
        UI_Panel.add(label3, gbc);
        newGameButton = new JButton();
        newGameButton.setText("New Game");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 13;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        UI_Panel.add(newGameButton, gbc);
        sfxSlider = new JSlider();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 9;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        UI_Panel.add(sfxSlider, gbc);
        final JLabel label4 = new JLabel();
        label4.setText("SFX Volume");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.anchor = GridBagConstraints.WEST;
        UI_Panel.add(label4, gbc);
        final JLabel label5 = new JLabel();
        Font label5Font = this.$$$getFont$$$("Arial Black", Font.BOLD, 16, label5.getFont());
        if (label5Font != null) label5.setFont(label5Font);
        label5.setText("Game Info");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        UI_Panel.add(label5, gbc);
        final JPanel spacer4 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.VERTICAL;
        UI_Panel.add(spacer4, gbc);
        turnLabel = new JLabel();
        turnLabel.setText("White, make a move");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        UI_Panel.add(turnLabel, gbc);
        roundsLabel = new JLabel();
        roundsLabel.setText("Total moves: 0");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        UI_Panel.add(roundsLabel, gbc);
        final JPanel spacer5 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.VERTICAL;
        UI_Panel.add(spacer5, gbc);
        undo1MoveButton = new JButton();
        undo1MoveButton.setText("Undo 1 Move");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 14;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        UI_Panel.add(undo1MoveButton, gbc);
    }

    /**
     * @noinspection ALL
     */
    private Font $$$getFont$$$(String fontName, int style, int size, Font currentFont) {
        if (currentFont == null) return null;
        String resultName;
        if (fontName == null) {
            resultName = currentFont.getName();
        } else {
            Font testFont = new Font(fontName, Font.PLAIN, 10);
            if (testFont.canDisplay('a') && testFont.canDisplay('1')) {
                resultName = fontName;
            } else {
                resultName = currentFont.getName();
            }
        }
        return new Font(resultName, style >= 0 ? style : currentFont.getStyle(), size >= 0 ? size : currentFont.getSize());
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return UI_Panel;
    }

}

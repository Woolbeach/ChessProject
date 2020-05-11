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
                boardLogic.Undo(boardLogic.boardTracking, boardLogic.pieceLogic);
                upDate();
            }
        });

        save1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boardLogic.filehandler.saveGame(1, boardLogic.numberOfTurns);
                System.out.println("save1");
            }
        });
        loadSave1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boardLogic.filehandler.loadGame(1);
                System.out.println("load save 1");
                upDate();
            }
        });
        save2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boardLogic.filehandler.saveGame(2, boardLogic.numberOfTurns);
                System.out.println("save2");
            }
        });
        loadSave2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boardLogic.filehandler.loadGame(2);
                System.out.println("load save 2");
                upDate();
            }
        });
        save3Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boardLogic.filehandler.saveGame(3, boardLogic.numberOfTurns);
                System.out.println("save3");
            }
        });
        loadSave3Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boardLogic.filehandler.loadGame(3);
                System.out.println("load save 3");
                upDate();
            }
        });
    }

    public void upDate() {
        roundsLabel.setText("Total moves: " + boardLogic.numberOfTurns);
        if (boardLogic.whitesTurn) {
            turnLabel.setText("White, make a move!");
        } else {
            turnLabel.setText("Black, make a move!");
        }
    }

    public void whoWon(boolean WhiteOrBlack) {
        if (WhiteOrBlack) {
            turnLabel.setText("Black won!");
        } else {
            turnLabel.setText("White won!");
        }
    }

    //tada
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
    private JButton saveGameToLogfileButton;
    private JButton save1Button;
    private JButton loadSave1Button;
    private JButton save2Button;
    private JButton loadSave2Button;
    private JButton save3Button;
    private JButton loadSave3Button;

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
        label1.setHorizontalAlignment(0);
        label1.setHorizontalTextPosition(0);
        label1.setText("Sound Options");
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 5;
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
        UI_Panel.add(label4, gbc);
        final JLabel label5 = new JLabel();
        Font label5Font = this.$$$getFont$$$("Arial Black", Font.BOLD, 16, label5.getFont());
        if (label5Font != null) label5.setFont(label5Font);
        label5.setText("Game Info");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
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
        UI_Panel.add(turnLabel, gbc);
        roundsLabel = new JLabel();
        roundsLabel.setText("Total moves: 0");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
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
        final JLabel label6 = new JLabel();
        Font label6Font = this.$$$getFont$$$("Arial Black", Font.BOLD, 16, label6.getFont());
        if (label6Font != null) label6.setFont(label6Font);
        label6.setText("Game Saves");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 16;
        UI_Panel.add(label6, gbc);
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 17;
        gbc.fill = GridBagConstraints.BOTH;
        UI_Panel.add(panel1, gbc);
        save1Button = new JButton();
        save1Button.setText("Save 1");
        panel1.add(save1Button);
        loadSave1Button = new JButton();
        loadSave1Button.setText("Load Save 1");
        panel1.add(loadSave1Button);
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 18;
        gbc.fill = GridBagConstraints.BOTH;
        UI_Panel.add(panel2, gbc);
        save2Button = new JButton();
        save2Button.setText("Save 2");
        panel2.add(save2Button);
        loadSave2Button = new JButton();
        loadSave2Button.setText("Load Save 2");
        panel2.add(loadSave2Button);
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 19;
        gbc.fill = GridBagConstraints.BOTH;
        UI_Panel.add(panel3, gbc);
        save3Button = new JButton();
        save3Button.setText("Save 3");
        panel3.add(save3Button);
        loadSave3Button = new JButton();
        loadSave3Button.setText("Load Save 3");
        panel3.add(loadSave3Button);
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

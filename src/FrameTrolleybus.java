import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class FrameTrolleybus extends JFrame {
    private DrawingBus drawingBus;
    private AbstractStrategy abstractStrategy;
    private JComboBox<String> comboBoxStrategy;
    private final JComponent pictureBoxTrolleybus;
    public FrameTrolleybus() throws IOException {
        super("Троллейбус");
        setSize(new Dimension(900,500));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pictureBoxTrolleybus = new JComponent(){
            public void paintComponent(Graphics graphics){
                super.paintComponent(graphics);
                Graphics2D graphics2D = (Graphics2D) graphics;
                if (drawingBus != null) drawingBus.drawTransport(graphics2D);
                super.repaint();
            }
        };
        comboBoxStrategy = new JComboBox<>(new String[]{"к центру", "к границе"});
        JButton stepButton = new JButton("Шаг");
        JButton createBusButton = new JButton("Создать автобус");
        JButton createTrolleybusButton = new JButton("Создать троллейбус");
        JButton rightButton = new JButton(new ImageIcon(ImageIO.read(new File("img/right.png"))));
        JButton leftButton = new JButton(new ImageIcon(ImageIO.read(new File("img/left.png"))));
        JButton upButton = new JButton(new ImageIcon(ImageIO.read(new File("img/up.png"))));
        JButton downButton = new JButton(new ImageIcon(ImageIO.read(new File("img/down.png"))));
        pictureBoxTrolleybus.setBounds( 0, 0, getContentPane().getWidth(), getContentPane().getHeight());
        createBusButton.addActionListener(e -> buttonCreateBusClick());
        createTrolleybusButton.addActionListener(e -> buttonCreateTrolleybusClick());
        stepButton.addActionListener(e -> buttonStepClick());
        rightButton.setActionCommand("right");
        rightButton.addActionListener(this::buttonMoveClick);
        leftButton.setActionCommand("left");
        leftButton.addActionListener(this::buttonMoveClick);
        upButton.setActionCommand("up");
        upButton.addActionListener(this::buttonMoveClick);
        downButton.setActionCommand("down");
        downButton.addActionListener(this::buttonMoveClick);
        setLayout(new BorderLayout());
        JPanel panelTrolleybus = new JPanel(new BorderLayout());
        JPanel createPanel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        createPanel.add(createBusButton, constraints);
        constraints.gridx = 1;
        constraints.gridy = 0;
        createPanel.add(createTrolleybusButton, constraints);
        JPanel movementPanel = new JPanel(new GridBagLayout());
        JPanel rightPanel = new JPanel(new BorderLayout());
        JPanel leftPanel = new JPanel(new BorderLayout());
        rightPanel.add(movementPanel, BorderLayout.SOUTH);
        rightButton.setPreferredSize(new Dimension(30,30));
        constraints.gridx = 2;
        constraints.gridy = 1;
        constraints.insets.left = constraints.insets.top = constraints.insets.bottom = constraints.insets.right = 2;
        movementPanel.add(rightButton, constraints);
        leftButton.setPreferredSize(new Dimension(30,30));
        constraints.gridx = 0;
        constraints.gridy = 1;
        movementPanel.add(leftButton, constraints);
        upButton.setPreferredSize(new Dimension(30,30));
        constraints.gridx = 1;
        constraints.gridy = 0;
        movementPanel.add(upButton, constraints);
        downButton.setPreferredSize(new Dimension(30,30));
        constraints.gridx = 1;
        constraints.gridy = 1;
        movementPanel.add(downButton, constraints);
        JPanel stepPanel = new JPanel(new GridBagLayout());
        constraints.gridx = 0;
        constraints.gridy = 0;
        stepPanel.add(comboBoxStrategy, constraints);
        constraints.gridx = 0;
        constraints.gridy = 1;
        stepPanel.add(stepButton, constraints);
        add(pictureBoxTrolleybus);
        rightPanel.add(stepPanel, BorderLayout.NORTH);
        leftPanel.add(createPanel, BorderLayout.SOUTH);
        panelTrolleybus.add(rightPanel, BorderLayout.EAST);
        panelTrolleybus.add(leftPanel, BorderLayout.WEST);
        add(panelTrolleybus,BorderLayout.CENTER);
        setVisible(true);
    }
    private void buttonCreateTrolleybusClick() {
        Random random = new Random();
        pictureBoxTrolleybus.setBounds(0,0,getContentPane().getWidth(),getContentPane().getHeight());
        drawingBus = new DrawingTrolleybus(random.nextInt(200) + 100, random.nextInt(2000) + 1000,
                new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256)),
                new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256)),
                random.nextBoolean(), random.nextBoolean(), pictureBoxTrolleybus.getWidth(), pictureBoxTrolleybus.getHeight(),
                (random.nextInt(3)+1)*2, random.nextInt(3));
        drawingBus.setPosition(random.nextInt(90) + 10, random.nextInt(90) + 10);
        draw();
    }
    private void buttonCreateBusClick(){
        Random random = new Random();
        pictureBoxTrolleybus.setBounds(0,0,getContentPane().getWidth(),getContentPane().getHeight());
        drawingBus = new DrawingBus(
                random.nextInt(200) + 100,
                random.nextInt(2000) + 1000,
                new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256)),
                pictureBoxTrolleybus.getWidth(),
                pictureBoxTrolleybus.getHeight(),
                (random.nextInt(3)+1)*2,
                random.nextInt(3));
        drawingBus.setPosition(random.nextInt(90) + 10, random.nextInt(90) + 10);
        draw();
    }
    private void buttonStepClick(){
        if (drawingBus == null) {
            return;
        }
        if (comboBoxStrategy.isEnabled()) {
            switch(comboBoxStrategy.getSelectedIndex()) {
                case 0:
                    abstractStrategy = new MoveToCenter();
                    break;
                case 1:
                    abstractStrategy = new MoveToBorder();
                    break;
                default:
                    abstractStrategy = null;
                    break;
            }
            if (abstractStrategy == null) {
                return;
            }
            abstractStrategy.setData(new DrawingObjectBus(drawingBus), pictureBoxTrolleybus.getWidth(), pictureBoxTrolleybus.getHeight());
            comboBoxStrategy.setEnabled(false);
        }
        if (abstractStrategy == null) {
            return;
        }
        abstractStrategy.makeStep();
        draw();
        if (abstractStrategy.getStatus() == Status.FINISH)
        {
            comboBoxStrategy.setEnabled(true);
            abstractStrategy = null;
        }
    }
    private void buttonMoveClick(ActionEvent event) {
        if(drawingBus == null || drawingBus.getEntityBus() == null)
            return;
        switch (event.getActionCommand())
        {
            case "left":
                drawingBus.moveTransport(DirectionType.LEFT);
                break;
            case "right":
                drawingBus.moveTransport(DirectionType.RIGHT);
                break;
            case "up":
                drawingBus.moveTransport(DirectionType.UP);
                break;
            case "down":
                drawingBus.moveTransport(DirectionType.DOWN);
                break;
        }
        draw();
    }
    private void draw() {
        if (drawingBus == null)
        {
            return;
        }
        pictureBoxTrolleybus.repaint();
    }
}
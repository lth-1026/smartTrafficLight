import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class TrafficLight extends JFrame {
    //sec
    private int redTime;
    private int greenTime;
    private int rightGreenTime;
    private int yellowTime;

    private boolean turnOn;

    private ImageIcon offImage = new ImageIcon(getClass().getResource("images/off.png"));
    private ImageIcon redImage = new ImageIcon(getClass().getResource("images/red.png"));
    private ImageIcon greenImage = new ImageIcon(getClass().getResource("images/green.png"));
    private ImageIcon rightImage = new ImageIcon(getClass().getResource("images/right.png"));
    private ImageIcon yellowImage = new ImageIcon(getClass().getResource("images/yellow.png"));

    public static void main(String[] args) {
        TrafficLight a = new TrafficLight();
    }

    public TrafficLight() {
        redTime = 10;
        greenTime = 10;
        rightGreenTime = 3;
        yellowTime = 1;

        GUI gui = new GUI();
        gui.start();
    }

    public void makeGUI() {
        setTitle("신호등");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container c = getContentPane();
        c.setLayout(new BoxLayout(c, BoxLayout.Y_AXIS));

        JPanel guiTrafficLight = new JPanel();
        guiTrafficLight.setSize(400, 200);
        guiTrafficLight.setLayout(new BoxLayout(guiTrafficLight, BoxLayout.X_AXIS));
        guiTrafficLight.setVisible(true);
        JLabel red = new JLabel(offImage);
        JLabel yellow = new JLabel(offImage);
        JLabel right = new JLabel(offImage);
        JLabel green = new JLabel(offImage);
        guiTrafficLight.add(red);
        guiTrafficLight.add(yellow);
        guiTrafficLight.add(right);
        guiTrafficLight.add(green);

        JPanel guiButton = new JPanel();
        JButton b1 = new JButton("실행");
        JButton b2 = new JButton("정지");
        JButton b3 = new JButton("점등");
        JButton b4 = new JButton("점멸");
        final exeTrafficLight[] et = new exeTrafficLight[1];
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exeTrafficLight etl = new exeTrafficLight(red, yellow, right, green);
                et[0] = etl;
                et[0].start();
            }
        });
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                et[0].interrupt();
            }
        });
        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                red.setIcon(redImage);
                yellow.setIcon(yellowImage);
                right.setIcon(rightImage);
                green.setIcon(greenImage);
            }
        });
        b4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                red.setIcon(offImage);
                yellow.setIcon(offImage);
                right.setIcon(offImage);
                green.setIcon(offImage);
            }
        });
        guiButton.add(b1);
        guiButton.add(b2);
        guiButton.add(b3);
        guiButton.add(b4);

        JPanel guiText = new JPanel();
        guiText.setLayout(new BorderLayout());

        JPanel redInput = new JPanel();
        redInput.setLayout(new BoxLayout(redInput, BoxLayout.X_AXIS));
        redInput.add(new JLabel(" red"));
        JTextField redText = new JTextField(Integer.toString(redTime));
        redInput.add(redText);
        redInput.add(new JLabel("초 "));

        JPanel yellowInput = new JPanel();
        yellowInput.setLayout(new BoxLayout(yellowInput, BoxLayout.X_AXIS));
        yellowInput.add(new JLabel(" yellow"));
        JTextField yellowText = new JTextField(Integer.toString(yellowTime));
        yellowInput.add(yellowText);
        yellowInput.add(new JLabel("초 "));

        JPanel rightInput = new JPanel();
        rightInput.setLayout(new BoxLayout(rightInput, BoxLayout.X_AXIS));
        rightInput.add(new JLabel(" right"));
        JTextField rightText = new JTextField(Integer.toString(rightGreenTime));
        rightInput.add(rightText);
        rightInput.add(new JLabel("초 "));

        JPanel greenInput = new JPanel();
        greenInput.setLayout(new BoxLayout(greenInput, BoxLayout.X_AXIS));
        greenInput.add(new JLabel(" green"));
        JTextField greenText = new JTextField(Integer.toString(greenTime));
        greenInput.add(greenText);
        greenInput.add(new JLabel("초 "));

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
        inputPanel.setSize(300, 150);
        inputPanel.add(redInput);
        inputPanel.add(yellowInput);
        inputPanel.add(rightInput);
        inputPanel.add(greenInput);

        JButton inputButton = new JButton("입력");
        inputButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                redTime = Integer.parseInt(redText.getText());
                yellowTime = Integer.parseInt(yellowText.getText());
                rightGreenTime = Integer.parseInt(rightText.getText());
                greenTime = Integer.parseInt(greenText.getText());
            }
        });

        guiText.add(inputPanel, BorderLayout.CENTER);
        guiText.add(inputButton, BorderLayout.EAST);

        c.add(guiTrafficLight);
        c.add(guiButton);
        c.add(guiText);

        setSize(400,450);
        setVisible(true);
    }

    class GUI extends Thread {
        public void run() {
            makeGUI();
        }
    }

    class exeTrafficLight extends Thread {
        private JLabel red;
        private JLabel yellow;
        private JLabel right;
        private JLabel green;


        public exeTrafficLight(JLabel a, JLabel b, JLabel c, JLabel d) {
            red = a;
            yellow = b;
            right = c;
            green = d;
        }

        public void run() {
            turnOn = true;
            while (true) {
                if(turnOn == false) return;
                exeRed(red);
                if(turnOn == false) return;
                exeRightGreen(red, right);
                if(turnOn == false) return;
                exeYellow(yellow);
                if(turnOn == false) return;
                exeGreen(green);
                if(turnOn == false) return;
                exeYellow(yellow);
            }
        }
    }

    public void exeRed(JLabel red) {
        red.setIcon(redImage);
        System.out.println("turnOn Red Light");
        try {
            Thread.sleep(redTime * 1000);
        } catch (InterruptedException e) {
            System.out.println("Interrupt!!");
            red.setIcon(offImage);
            turnOn = false;
            return;
        }
        red.setIcon(offImage);
        System.out.println("turnOff Red Light");
    }

    public void exeGreen(JLabel green) {
        green.setIcon(greenImage);
        System.out.println("turnOn Green Light");
        try {
            Thread.sleep(greenTime * 1000);
        } catch (InterruptedException e) {
            System.out.println("Interrupt!!");
            green.setIcon(offImage);
            turnOn = false;
            return;
        }
        green.setIcon(offImage);
        System.out.println("turnOff Green Light");
    }



    public void exeRightGreen(JLabel red, JLabel right) {
        red.setIcon(redImage);
        right.setIcon(rightImage);
        System.out.println("turnOn RightGreen Light");
        try {
            Thread.sleep(rightGreenTime * 1000);
        } catch (InterruptedException e) {
            System.out.println("Interrupt!!");
            right.setIcon(offImage);
            red.setIcon(offImage);
            turnOn = false;
            return;
        }
        right.setIcon(offImage);
        red.setIcon(offImage);
        System.out.println("turnOff RightGreen Light");
    }

    public void exeYellow(JLabel yellow) {
        yellow.setIcon(yellowImage);
        System.out.println("turnOn Yellow Light");
        try {
            Thread.sleep(yellowTime * 1000);
        } catch (InterruptedException e) {
            System.out.println("Interrupt!!");
            yellow.setIcon(offImage);
            turnOn = false;
            return;
        }
        yellow.setIcon(offImage);
        System.out.println("turnOff Yellow Light");
    }

    public void setRedTime(int time) {
        redTime = time;
    }

    public void setGreenTime(int time) {
        greenTime = time;
    }

    public void setRightGreenTime(int time) {
        rightGreenTime = time;
    }

    public void setYellowTime(int time) {
        yellowTime = time;
    }
}

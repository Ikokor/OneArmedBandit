import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Timer;
import java.util.*;

class UseBandit extends JFrame implements ActionListener {

    /* Variables */

	private static final long serialVersionUID = 1L;
	private int credit;
    private float balance;
    private float winnings;
    private int gameNumber;
    private int col1 = 2;
    private int col2 = 2;
    private int col3 = 2;
    private int col1Row1 = 1;
    private int col1Row2 = 2;
    private int col1Row3 = 3;
    private int col2Row1 = 1;
    private int col2Row2 = 2;
    private int col2Row3 = 3;
    private int col3Row1 = 1;
    private int col3Row2 = 2;
    private int col3Row3 = 3;
    private int initialCol1;
    private int initialCol2;
    private int initialCol3;
    private int initialCol1Spins;
    private int initialCol2Spins;
    private int initialCol3Spins;
    private int randomNumber1;
    private int randomNumber2;
    private int randomNumber3;
    private int col1Spins;
    private int col2Spins;
    private int col3Spins;
    private int nudgeUsed;
    private int numberOfGames;
    private static int replayGameNumber;
    private boolean win;
    private boolean col1Hold;
    private boolean col2Hold;
    private boolean col3Hold;
    private boolean mute;
    private Timer column1;
    private Timer column2;
    private Timer column3;
    private static Point location;

    private ArrayList<Integer> list = new ArrayList<>();
    private String[] selector = new String[1];

    public static void main(String[] args) {
        UseBandit frame = new UseBandit();
        frame.setVisible(true);

    }

    /* Create GUI */

    private JButton btnSpin;
    private JButton btnAddCredit;
    private JButton btnCashIn;
    private JButton btnReCredit;
    private JButton btnHoldCol1;
    private JButton btnHoldCol2;
    private JButton btnHoldCol3;
    private JButton btnNudgeCol1;
    private JButton btnNudgeCol2;
    private JButton btnNudgeCol3;
    private JButton btnReplay;
    private JButton btnMute;
    private JLabel lblBalance;
    private JLabel lblCredit;
    private JLabel lblWinnings;
    private JLabel lblWin;
    private JLabel lblDateTime;
    private JLabel lblIconCol1Row1;
    private JLabel lblIconCol1Row2;
    private JLabel lblIconCol1Row3;
    private JLabel lblIconCol2Row1;
    private JLabel lblIconCol2Row2;
    private JLabel lblIconCol2Row3;
    private JLabel lblIconCol3Row1;
    private JLabel lblIconCol3Row2;
    private JLabel lblIconCol3Row3;
    private JComboBox<String> cbReplayPicker;

    private JFrame frame = new JFrame();


        private UseBandit() {
        setTitle("One Armed Bandit");
        setIconImage(Toolkit.getDefaultToolkit().getImage(UseBandit.class.getResource("/Icons/SlotMachine.png")));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(100, 100, 620, 656);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        balance = Money.balance();

        /* Column 1 */

        lblIconCol1Row1 = new JLabel("");
        lblIconCol1Row1.setIcon(new ImageIcon(UseBandit.class.getResource("/Icons/" + col1Row1 + ".png")));
        lblIconCol1Row1.setBounds(184, 180, 64, 64);
        contentPane.add(lblIconCol1Row1);

        lblIconCol1Row2 = new JLabel("");
        lblIconCol1Row2.setIcon(new ImageIcon(UseBandit.class.getResource("/Icons/" + col1Row2 + ".png")));
        lblIconCol1Row2.setBounds(184, 264, 64, 64);
        contentPane.add(lblIconCol1Row2);

        lblIconCol1Row3 = new JLabel("");
        lblIconCol1Row3.setIcon(new ImageIcon(UseBandit.class.getResource("/Icons/" + col1Row3 + ".png")));
        lblIconCol1Row3.setBounds(184, 348, 64, 64);
        contentPane.add(lblIconCol1Row3);

        /* Column 2 */

        lblIconCol2Row1 = new JLabel("");
        lblIconCol2Row1.setIcon(new ImageIcon(UseBandit.class.getResource("/Icons/" + col2Row1 + ".png")));
        lblIconCol2Row1.setBounds(268, 180, 64, 64);
        contentPane.add(lblIconCol2Row1);

        lblIconCol2Row2 = new JLabel("");
        lblIconCol2Row2.setIcon(new ImageIcon(UseBandit.class.getResource("/Icons/" + col2Row2 + ".png")));
        lblIconCol2Row2.setBounds(268, 264, 64, 64);
        contentPane.add(lblIconCol2Row2);

        lblIconCol2Row3 = new JLabel("");
        lblIconCol2Row3.setIcon(new ImageIcon(UseBandit.class.getResource("/Icons/" + col2Row3 + ".png")));
        lblIconCol2Row3.setBounds(268, 348, 64, 64);
        contentPane.add(lblIconCol2Row3);

        /* Column 3 */

        lblIconCol3Row1 = new JLabel("");
        lblIconCol3Row1.setIcon(new ImageIcon(UseBandit.class.getResource("/Icons/" + col3Row1 + ".png")));
        lblIconCol3Row1.setBounds(352, 180, 64, 64);
        contentPane.add(lblIconCol3Row1);

        lblIconCol3Row2 = new JLabel("");
        lblIconCol3Row2.setIcon(new ImageIcon(UseBandit.class.getResource("/Icons/" + col3Row2 + ".png")));
        lblIconCol3Row2.setBounds(352, 264, 64, 64);
        contentPane.add(lblIconCol3Row2);

        lblIconCol3Row3 = new JLabel("");
        lblIconCol3Row3.setIcon(new ImageIcon(UseBandit.class.getResource("/Icons/" + col3Row3 + ".png")));
        lblIconCol3Row3.setBounds(352, 348, 64, 64);
        contentPane.add(lblIconCol3Row3);

        /* Buttons */

        btnAddCredit = new JButton("Add £1");
        btnAddCredit.setBounds(500, 10, 90, 20);
        contentPane.add(btnAddCredit);
        btnAddCredit.addActionListener(this);

        btnSpin = new JButton ("Spin");
        btnSpin.setBounds(500, 40,90, 20);
        contentPane.add(btnSpin);
        btnSpin.addActionListener(this);

        btnCashIn = new JButton("Cash In");
        btnCashIn.setBounds(500,70,90,20);
        contentPane.add(btnCashIn);
        btnCashIn.addActionListener(this);

        btnReCredit = new JButton("ReCredit");
        btnReCredit.setBounds(500,100,90,20);
        contentPane.add(btnReCredit);
        btnReCredit.addActionListener(this);

        btnHoldCol1 = new JButton("Hold");
        btnHoldCol1.setBounds(176,150,80,20);
        contentPane.add(btnHoldCol1);
        btnHoldCol1.addActionListener(this);

        btnHoldCol2 = new JButton("Hold");
        btnHoldCol2.setBounds(260,150,80,20);
        contentPane.add(btnHoldCol2);
        btnHoldCol2.addActionListener(this);

        btnHoldCol3 = new JButton("Hold");
        btnHoldCol3.setBounds(344,150,80,20);
        contentPane.add(btnHoldCol3);
        btnHoldCol3.addActionListener(this);

        btnNudgeCol1 = new JButton("Nudge");
        btnNudgeCol1.setBounds(176,442,80,20);
        contentPane.add(btnNudgeCol1);
        btnNudgeCol1.addActionListener(this);
        btnNudgeCol1.setEnabled(false);

        btnNudgeCol2 = new JButton("Nudge");
        btnNudgeCol2.setBounds(260,442,80,20);
        contentPane.add(btnNudgeCol2);
        btnNudgeCol2.addActionListener(this);
        btnNudgeCol2.setEnabled(false);

        btnNudgeCol3 = new JButton("Nudge");
        btnNudgeCol3.setBounds(344,442,80,20);
        contentPane.add(btnNudgeCol3);
        btnNudgeCol3.addActionListener(this);
        btnNudgeCol3.setEnabled(false);

        btnMute = new JButton("Mute");
        btnMute.setBounds(500,586,90,20);
        contentPane.add(btnMute);
        btnMute.addActionListener(this);

        /* Labels */

        lblBalance = new JLabel("Balance: £" + balance);
        lblBalance.setBounds(10,10,100,20);
        contentPane.add(lblBalance);

        lblCredit = new JLabel("Credit: " + credit);
        lblCredit.setBounds(10, 40, 100, 20);
        contentPane.add(lblCredit);

        lblWinnings = new JLabel("Winnings: £" + winnings);
        lblWinnings.setBounds(10,70,100,20);
        contentPane.add(lblWinnings);

        lblWin = new JLabel("");
        lblWin.setBounds(270,130,100,20);
        contentPane.add(lblWin);

        lblDateTime = new JLabel("");
        lblDateTime.setBounds(240,10,120,20);
        contentPane.add(lblDateTime);
        DateTimer();

        /* Replay System */

            JLabel lblReplay = new JLabel("Replay");
        lblReplay.setBounds(500,160,90,20);
        contentPane.add(lblReplay);

        btnReplay = new JButton("Replay");
        btnReplay.setBounds(500,220,90,20);
        contentPane.add(btnReplay);
        btnReplay.addActionListener(this);
        btnReplay.setEnabled(false);

        cbReplayPicker = new JComboBox<>(selector);
        cbReplayPicker.setBounds(500,190,90,20);
        contentPane.add(cbReplayPicker);
        cbReplayPicker.addActionListener(this);


        if(balance >= 1){
            btnAddCredit.setEnabled(true);
        }
        else{
            btnAddCredit.setEnabled(false);
        }

        if(credit >= 1){
            btnSpin.setEnabled(true);
        }
        else{
            btnSpin.setEnabled(false);
        }

        if(winnings > 0){
            btnCashIn.setEnabled(true);
            btnReCredit.setEnabled(true);
        }
        else{
            btnCashIn.setEnabled(false);
            btnReCredit.setEnabled(false);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnAddCredit){
                Money.addCredit();
                balance = Money.balance();
                credit = Money.credit();
                lblCredit.setText("Credit: " + credit);
                lblBalance.setText("Balance: £" + round(balance));
                btnSpin.setEnabled(true);
                if(balance < 1){
                    btnAddCredit.setEnabled(false);
                }
        }

        if(e.getSource() == btnSpin){
                if (credit >= 1) {
                    gameNumber++;
                    list.clear();
                    Money.spin();
                    credit = Money.credit();
                    lblCredit.setText("Credit: " + credit);
                    col2Spins = randomNumber2;
                    RandomNumbers();
                    Column1Timer();
                    Column2Timer();
                    Column3Timer();
                    InitialCol();
                    DisableHold();
                    playSound();
                    lblWin.setText("");
                    win = false;
                    btnSpin.setEnabled(false);
                    nudgeUsed = 0;
                                }
        }

        if(e.getSource() == btnCashIn){
            if(winnings > 0){
                Money.cashIn();
                winnings = Money.winnings();
                balance = Money.balance();
                lblBalance.setText("Balance: £" + round(balance));
                lblWinnings.setText("Winnings: £" + round(winnings));
                if(winnings == 0){
                    btnCashIn.setEnabled(false);
                    btnReCredit.setEnabled(false);
                }
                if(!btnAddCredit.isEnabled() && balance >= 1){
                    btnAddCredit.setEnabled(true);
                }
            }
        }

        if(e.getSource() == btnReCredit){
            if(winnings >= 0.2){
                Money.reCredit();
                winnings = Money.winnings();
                credit = Money.credit();
                lblCredit.setText("Credit: " + credit);
                lblWinnings.setText("Winnings: £" + round(winnings));
                if(winnings < 0.2){
                    btnReCredit.setEnabled(false);
                }
                if(winnings == 0){
                    btnCashIn.setEnabled(false);
                }
                if(!btnSpin.isEnabled()){
                    btnSpin.setEnabled(true);
                }
            }
        }

        if(e.getSource() == btnHoldCol1){
            if (col1Hold) {
                col1Hold = false;
                btnHoldCol1.setText("Hold");
                btnHoldCol2.setEnabled(true);
                btnHoldCol3.setEnabled(true);
            }
            else {
                    col1Hold = true;
                    btnHoldCol1.setText("Holding");
                    btnHoldCol2.setEnabled(false);
                    btnHoldCol3.setEnabled(false);
            }
        }

        if(e.getSource() == btnHoldCol2){
            if (col2Hold) {
                col2Hold = false;
                btnHoldCol2.setText("Hold");
                btnHoldCol1.setEnabled(true);
                btnHoldCol3.setEnabled(true);
            }
            else {
                    col2Hold = true;
                    btnHoldCol2.setText("Holding");
                    btnHoldCol1.setEnabled(false);
                    btnHoldCol3.setEnabled(false);
            }
        }

        if(e.getSource() == btnHoldCol3){
            if (col3Hold) {
                col3Hold = false;
                btnHoldCol3.setText("Hold");
                btnHoldCol1.setEnabled(true);
                btnHoldCol2.setEnabled(true);
            }
            else {
                    col3Hold = true;
                    btnHoldCol3.setText("Holding");
                    btnHoldCol1.setEnabled(false);
                    btnHoldCol2.setEnabled(false);
            }
        }

        if (e.getSource() == btnNudgeCol1) {
            Nudge1();
        }

        if (e.getSource() == btnNudgeCol2) {
                Nudge2();
        }

        if (e.getSource() == btnNudgeCol3) {
                Nudge3();
        }

        if (e.getSource() == btnReplay) {
            String stringReplayGameNumber = (String) cbReplayPicker.getSelectedItem();
            assert stringReplayGameNumber != null;
            replayGameNumber = Integer.parseInt(stringReplayGameNumber);
            location = getLocation();
            Location();
            Replay replay = new Replay();
            replay.setVisible(true);
        }

        if (e.getSource() == cbReplayPicker) {
                btnReplay.setEnabled(true);
        }

        if (e.getSource() == btnMute){
            if(!mute){
                mute = true;
                btnMute.setText("UnMute");
            }
            else{
                mute = false;
                btnMute.setText("Mute");
            }
        }
    }

    private void RandomNumbers() {
        Random rand = new Random();

        int randomNum1 = rand.nextInt((10) + 1);
        int randomNum2 = rand.nextInt((10) + 1);
        int randomNum3 = rand.nextInt((10) + 1);
        randomNumber1 = randomNum1 + 10;
        randomNumber2 = randomNum2 + 20;
        randomNumber3 = randomNum3 + 30;
        SetSpins();
        InitialSpins();
    }

    private void SetSpins(){
        col1Spins = randomNumber1;
        col2Spins = randomNumber2;
        col3Spins = randomNumber3;
    }

    private void DateTimer(){
        Timer datetime = new Timer();
        datetime.schedule(new DateTimeTask(),
        100, 100);
    }

    class DateTimeTask extends TimerTask{
        public void run(){
            lblDateTime.setText(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss").format(LocalDateTime.now()));
        }
    }

    private void Column1Timer() {
        if (!col1Hold) { //HOLD FEATURE
        column1 = new Timer();
        column1.schedule(new Column1Task(),
                100,        //initial delay
                100);  //subsequent rate
        }
    }


    class Column1Task extends TimerTask {
        public void run() {
            if (col1Spins >= 1) {
                col1++;
                if (col1 >= 11) {
                    col1 = 1;
                }
                if (col1 <= 1){
                    col1Row3 = 10;
                }
                if (col1 > 1){
                    col1Row3 = col1 - 1;
                }
                col1Row2 = col1;
                if (col1 >=10){
                    col1Row1 = 1;
                }
                if (col1 < 10){
                    col1Row1 = col1 + 1;
                }
                lblIconCol1Row1.setIcon(new ImageIcon(UseBandit.class.getResource("/Icons/" + col1Row1 + ".png")));
                lblIconCol1Row2.setIcon(new ImageIcon(UseBandit.class.getResource("/Icons/" + col1Row2 + ".png")));
                lblIconCol1Row3.setIcon(new ImageIcon(UseBandit.class.getResource("/Icons/" + col1Row3 + ".png")));

                col1Spins --;
            }
            else {
                column1.cancel();
            }
        }
    }

    private void Column2Timer() {
        if (!col2Hold) { //HOLD FEATURE
            column2 = new Timer();
            column2.schedule(new Column2Task(),
                    100,        //initial delay
                    100);  //subsequent rate
        }
    }


    class Column2Task extends TimerTask {
        public void run() {
            if (col2Spins >= 1) {
                col2++;
                if (col2 >= 11) {
                    col2 = 1;
                }
                if (col2 <= 1){
                    col2Row3 = 10;
                }
                if (col2 > 1){
                    col2Row3 = col2 - 1;
                }
                col2Row2 = col2;
                if (col2 >=10){
                    col2Row1 = 1;
                }
                if (col2 < 10){
                    col2Row1 = col2 + 1;
                }
                lblIconCol2Row1.setIcon(new ImageIcon(UseBandit.class.getResource("/Icons/" + col2Row1 + ".png")));
                lblIconCol2Row2.setIcon(new ImageIcon(UseBandit.class.getResource("/Icons/" + col2Row2 + ".png")));
                lblIconCol2Row3.setIcon(new ImageIcon(UseBandit.class.getResource("/Icons/" + col2Row3 + ".png")));

                col2Spins --;
            }
            else {
                column2.cancel();
            }
        }
    }

    private void Column3Timer() {
        column3 = new Timer();
        column3.schedule(new Column3Task(),
                100,        //initial delay
                100);  //subsequent rate
    }


    class Column3Task extends TimerTask {
        public void run() {
            if (col3Spins >= 1) {
                if(!col3Hold) { //HOLD FEATURE
                    col3++;
                    if (col3 >= 11) {
                        col3 = 1;
                    }
                    if (col3 <= 1) {
                        col3Row3 = 10;
                    }
                    if (col3 > 1) {
                        col3Row3 = col3 - 1;
                    }
                    col3Row2 = col3;
                    if (col3 >= 10) {
                        col3Row1 = 1;
                    }
                    if (col3 < 10) {
                        col3Row1 = col3 + 1;
                    }
                    lblIconCol3Row1.setIcon(new ImageIcon(UseBandit.class.getResource("/Icons/" + col3Row1 + ".png")));
                    lblIconCol3Row2.setIcon(new ImageIcon(UseBandit.class.getResource("/Icons/" + col3Row2 + ".png")));
                    lblIconCol3Row3.setIcon(new ImageIcon(UseBandit.class.getResource("/Icons/" + col3Row3 + ".png")));
                }
                col3Spins --;
            }
            else {
                if(credit >= 1) {
                    btnSpin.setEnabled(true);
                }
                clip.close();
                numberOfGames++;
                selector = new String[numberOfGames];
                CreateArray();
                logGame("");
                SaveReplay();
                CreateReplayArray();
                col1Hold = false;
                col2Hold = false;
                col3Hold = false;
                btnHoldCol1.setText("Hold");
                btnHoldCol2.setText("Hold");
                btnHoldCol3.setText("Hold");
                btnHoldCol1.setEnabled(true);
                btnHoldCol2.setEnabled(true);
                btnHoldCol3.setEnabled(true);
                column3.cancel();
                btnNudgeCol1.setEnabled(true);
                btnNudgeCol2.setEnabled(true);
                btnNudgeCol3.setEnabled(true);
                MatchCheck();
                cbReplayPicker.removeItem(null);
            }
        }
    }

    private void Nudge1() {
        win = false;
        col1++;
        if (col1 >= 11) {
            col1 = 1;
        }
        if (col1 <= 1) {
            col1Row3 = 10;
        }
        if (col1 > 1) {
            col1Row3 = col1 - 1;
        }
        col1Row2 = col1;
        if (col1 >= 10) {
            col1Row1 = 1;
        }
        if (col1 < 10) {
            col1Row1 = col1 + 1;
        }
        DisableNudge();
        lblIconCol1Row1.setIcon(new ImageIcon(UseBandit.class.getResource("/Icons/" + col1Row1 + ".png")));
        lblIconCol1Row2.setIcon(new ImageIcon(UseBandit.class.getResource("/Icons/" + col1Row2 + ".png")));
        lblIconCol1Row3.setIcon(new ImageIcon(UseBandit.class.getResource("/Icons/" + col1Row3 + ".png")));
        Nudge1MatchCheck();
        list.clear();
        CreateArray();
        logGame("Nudge 1");
        nudgeUsed = 1;
        SaveNudge();
    }

    private void Nudge2() {
        win = false;
        col2++;
        if (col2 >= 11) {
            col2 = 1;
        }
        if (col2 <= 1) {
            col2Row3 = 10;
        }
        if (col2 > 1) {
            col2Row3 = col2 - 1;
        }
        col2Row2 = col2;
        if (col2 >= 10) {
            col2Row1 = 1;
        }
        if (col2 < 10) {
            col2Row1 = col2 + 1;
        }
        DisableNudge();
        lblIconCol2Row1.setIcon(new ImageIcon(UseBandit.class.getResource("/Icons/" + col2Row1 + ".png")));
        lblIconCol2Row2.setIcon(new ImageIcon(UseBandit.class.getResource("/Icons/" + col2Row2 + ".png")));
        lblIconCol2Row3.setIcon(new ImageIcon(UseBandit.class.getResource("/Icons/" + col2Row3 + ".png")));
        Nudge2MatchCheck();
        list.clear();
        CreateArray();
        logGame("Nudge 2");
        nudgeUsed = 2;
        SaveNudge();
    }

    private void Nudge3() {
        win = false;
        col3++;
        if (col3 >= 11) {
            col3 = 1;
        }
        if (col3 <= 1) {
            col3Row3 = 10;
        }
        if (col3 > 1) {
            col3Row3 = col3 - 1;
        }
        col3Row2 = col3;
        if (col3 >= 10) {
            col3Row1 = 1;
        }
        if (col3 < 10) {
            col3Row1 = col3 + 1;
        }
        DisableNudge();
        lblIconCol3Row1.setIcon(new ImageIcon(UseBandit.class.getResource("/Icons/" + col3Row1 + ".png")));
        lblIconCol3Row2.setIcon(new ImageIcon(UseBandit.class.getResource("/Icons/" + col3Row2 + ".png")));
        lblIconCol3Row3.setIcon(new ImageIcon(UseBandit.class.getResource("/Icons/" + col3Row3 + ".png")));
        Nudge3MatchCheck();
        list.clear();
        CreateArray();
        logGame("Nudge 3");
        nudgeUsed = 3;
        SaveNudge();
    }

    private static float round(float value) {

        long factor = (long) Math.pow(10, 2);
        value = value * factor;
        long tmp = Math.round(value);
        return (float) tmp / factor;
    }

    private void DisableNudge(){
        btnNudgeCol1.setEnabled(false);
        btnNudgeCol2.setEnabled(false);
        btnNudgeCol3.setEnabled(false);
    }

    private void DisableHold(){
            btnHoldCol1.setEnabled(false);
            btnHoldCol2.setEnabled(false);
            btnHoldCol3.setEnabled(false);
    }

    private void MatchCheck() {
    	if ((col1Row2 == 2) && (col2Row2 == 2) && (col3Row2 == 2)) {
    		if(!win) {
    			Money.triple7();
    			winnings = Money.winnings();
    			lblWinnings.setText("Winnings: £" + round(winnings));
    			lblWin.setText("WINNER!!!");
                JOptionPane.showMessageDialog(frame, "£2 was awarded for matching 3 7s");
                win = true;
                if(!btnCashIn.isEnabled()) {
                    btnCashIn.setEnabled(true);
                }
                if(!btnReCredit.isEnabled()){
                    btnReCredit.setEnabled(true);
                }
    		}
    	}
    	if ((col1Row2 == 7) && (col2Row2 == 7) && (col3Row2 == 7)) {
    		if(!win) {
    			Money.tripleDiamond();
    			winnings = Money.winnings();
    			lblWinnings.setText("Winnings: £" + round(winnings));
    			lblWin.setText("WINNER!!!");
                JOptionPane.showMessageDialog(frame, "£1.50 was awarded for matching 3 Diamonds");
                win = true;
                if(!btnCashIn.isEnabled()) {
                    btnCashIn.setEnabled(true);
                }
                if(!btnReCredit.isEnabled()){
                    btnReCredit.setEnabled(true);
                }
    		}
    	}
        if ((col1Row2 == col2Row2) && (col1Row2 == col3Row2)){
            if (!win){
                Money.tripleWin();
                winnings = Money.winnings();
                lblWinnings.setText("Winnings: £" + round(winnings));
                lblWin.setText("WINNER!!!");
                JOptionPane.showMessageDialog(frame, "£1 was awarded for matching 3 shapes");
                win = true;
                if(!btnCashIn.isEnabled()) {
                    btnCashIn.setEnabled(true);
                }
                if(!btnReCredit.isEnabled()){
                    btnReCredit.setEnabled(true);
                }
            }
        }
        if ((col1Row2 == col2Row2) || (col1Row2 == col3Row2) || (col2Row2 == col3Row2)){
            if (!win){
                Money.doubleWin();
                winnings = Money.winnings();
                lblWinnings.setText("Winnings: £" + round(winnings));
                lblWin.setText("WINNER!!!");
                JOptionPane.showMessageDialog(frame, "50p was awarded for matching 2 shapes");
                win = true;
                if(!btnCashIn.isEnabled()) {
                    btnCashIn.setEnabled(true);
                }
                if(!btnReCredit.isEnabled()){
                    btnReCredit.setEnabled(true);
                }
            }
        }
        if (col1Row2 == 7) {
            if (!win) {
                Money.singleWin();
                winnings = Money.winnings();
                lblWinnings.setText("Winnings: £" + round(winnings));
                lblWin.setText("WINNER!!!");
                JOptionPane.showMessageDialog(frame, "20p was awarded for a special shape");
                win = true;
                if(!btnCashIn.isEnabled()) {
                    btnCashIn.setEnabled(true);
                }
                if(!btnReCredit.isEnabled()){
                    btnReCredit.setEnabled(true);
                }
            }
        }
        if (col2Row2 == 7) {
            if (!win) {
                Money.singleWin();
                winnings = Money.winnings();
                lblWinnings.setText("Winnings: £" + round(winnings));
                lblWin.setText("WINNER!!!");
                JOptionPane.showMessageDialog(frame, "20p was awarded for a special shape");
                win = true;
                if(!btnCashIn.isEnabled()) {
                    btnCashIn.setEnabled(true);
                }
                if(!btnReCredit.isEnabled()){
                    btnReCredit.setEnabled(true);
                }
            }
        }
        if (col3Row2 == 7) {
            if (!win) {
                Money.singleWin();
                winnings = Money.winnings();
                lblWinnings.setText("Winnings: £" + round(winnings));
                lblWin.setText("WINNER!!!");
                JOptionPane.showMessageDialog(frame, "20p was awarded for a special shape");
                win = true;
                if(!btnCashIn.isEnabled()) {
                    btnCashIn.setEnabled(true);
                }
                if(!btnReCredit.isEnabled()){
                    btnReCredit.setEnabled(true);
                }
            }
        }
    }

    private void Nudge1MatchCheck(){
    	if ((col1Row2 == 2) && (col2Row2 == 2) && (col3Row2 == 2)) {
    		if(!win) {
    			Money.triple7();
    			winnings = Money.winnings();
    			lblWinnings.setText("Winnings: £" + round(winnings));
    			lblWin.setText("WINNER!!!");
                JOptionPane.showMessageDialog(frame, "£2 was awarded for matching 3 7s");
                win = true;
                if(!btnCashIn.isEnabled()) {
                    btnCashIn.setEnabled(true);
                }
                if(!btnReCredit.isEnabled()){
                    btnReCredit.setEnabled(true);
                }
    		}
    	}
    	if ((col1Row2 == 7) && (col2Row2 == 7) && (col3Row2 == 7)) {
    		if(!win) {
    			Money.tripleDiamond();
    			winnings = Money.winnings();
    			lblWinnings.setText("Winnings: £" + round(winnings));
    			lblWin.setText("WINNER!!!");
                JOptionPane.showMessageDialog(frame, "£1.50 was awarded for matching 3 Diamonds");
                win = true;
                if(!btnCashIn.isEnabled()) {
                    btnCashIn.setEnabled(true);
                }
                if(!btnReCredit.isEnabled()){
                    btnReCredit.setEnabled(true);
                }
    		}
    	}
        if ((col1Row2 == col2Row2) && (col1Row2 == col3Row2)){
            if (!win){
                Money.tripleWin();
                winnings = Money.winnings();
                lblWinnings.setText("Winnings: £" + round(winnings));
                lblWin.setText("WINNER!!!");
                JOptionPane.showMessageDialog(frame, "£1 was awarded for matching 3 shapes");
                win = true;
                if(!btnCashIn.isEnabled()) {
                    btnCashIn.setEnabled(true);
                }
                if(!btnReCredit.isEnabled()){
                    btnReCredit.setEnabled(true);
                }
            }
        }
        if ((col1Row2 == col2Row2) || (col1Row2 == col3Row2)){
            if (!win){
                Money.doubleWin();
                winnings = Money.winnings();
                lblWinnings.setText("Winnings: £" + round(winnings));
                lblWin.setText("WINNER!!!");
                JOptionPane.showMessageDialog(frame, "50p was awarded for matching 2 shapes");
                win = true;
                if(!btnCashIn.isEnabled()) {
                    btnCashIn.setEnabled(true);
                }
                if(!btnReCredit.isEnabled()){
                    btnReCredit.setEnabled(true);
                }
            }
        }
        if (col1Row2 == 7) {
            if (!win) {
                Money.singleWin();
                winnings = Money.winnings();
                lblWinnings.setText("Winnings: £" + round(winnings));
                lblWin.setText("WINNER!!!");
                JOptionPane.showMessageDialog(frame, "20p was awarded for a special shape");
                win = true;
                if(!btnCashIn.isEnabled()) {
                    btnCashIn.setEnabled(true);
                }
                if(!btnReCredit.isEnabled()){
                    btnReCredit.setEnabled(true);
                }
            }
        }
    }

    private void Nudge2MatchCheck(){
    	if ((col1Row2 == 2) && (col2Row2 == 2) && (col3Row2 == 2)) {
    		if(!win) {
    			Money.triple7();
    			winnings = Money.winnings();
    			lblWinnings.setText("Winnings: £" + round(winnings));
    			lblWin.setText("WINNER!!!");
                JOptionPane.showMessageDialog(frame, "£2 was awarded for matching 3 7s");
                win = true;
                if(!btnCashIn.isEnabled()) {
                    btnCashIn.setEnabled(true);
                }
                if(!btnReCredit.isEnabled()){
                    btnReCredit.setEnabled(true);
                }
    		}
    	}
    	if ((col1Row2 == 7) && (col2Row2 == 7) && (col3Row2 == 7)) {
    		if(!win) {
    			Money.tripleDiamond();
    			winnings = Money.winnings();
    			lblWinnings.setText("Winnings: £" + round(winnings));
    			lblWin.setText("WINNER!!!");
                JOptionPane.showMessageDialog(frame, "£1.50 was awarded for matching 3 Diamonds");
                win = true;
                if(!btnCashIn.isEnabled()) {
                    btnCashIn.setEnabled(true);
                }
                if(!btnReCredit.isEnabled()){
                    btnReCredit.setEnabled(true);
                }
    		}
    	}
        if ((col1Row2 == col2Row2) && (col1Row2 == col3Row2)){
            if (!win){
                Money.tripleWin();
                winnings = Money.winnings();
                lblWinnings.setText("Winnings: £" + round(winnings));
                lblWin.setText("WINNER!!!");
                JOptionPane.showMessageDialog(frame, "£1 was awarded for matching 3 shapes");
                win = true;
                if(!btnCashIn.isEnabled()) {
                    btnCashIn.setEnabled(true);
                }
                if(!btnReCredit.isEnabled()){
                    btnReCredit.setEnabled(true);
                }
            }
        }
        if ((col1Row2 == col2Row2) || (col2Row2 == col3Row2)){
            if (!win){
                Money.doubleWin();
                winnings = Money.winnings();
                lblWinnings.setText("Winnings: £" + round(winnings));
                lblWin.setText("WINNER!!!");
                JOptionPane.showMessageDialog(frame, "50p was awarded for matching 2 shapes");
                win = true;
                if(!btnCashIn.isEnabled()) {
                    btnCashIn.setEnabled(true);
                }
                if(!btnReCredit.isEnabled()){
                    btnReCredit.setEnabled(true);
                }
            }
        }
        if (col2Row2 == 7) {
            if (!win) {
                Money.singleWin();
                winnings = Money.winnings();
                lblWinnings.setText("Winnings: £" + round(winnings));
                lblWin.setText("WINNER!!!");
                JOptionPane.showMessageDialog(frame, "20p was awarded for a special shape");
                win = true;
                if(!btnCashIn.isEnabled()) {
                    btnCashIn.setEnabled(true);
                }
                if(!btnReCredit.isEnabled()){
                    btnReCredit.setEnabled(true);
                }
            }
        }
    }

    private void Nudge3MatchCheck(){
    	if ((col1Row2 == 2) && (col2Row2 == 2) && (col3Row2 == 2)) {
    		if(!win) {
    			Money.triple7();
    			winnings = Money.winnings();
    			lblWinnings.setText("Winnings: £" + round(winnings));
    			lblWin.setText("WINNER!!!");
                JOptionPane.showMessageDialog(frame, "£2 was awarded for matching 3 7s");
                win = true;
                if(!btnCashIn.isEnabled()) {
                    btnCashIn.setEnabled(true);
                }
                if(!btnReCredit.isEnabled()){
                    btnReCredit.setEnabled(true);
                }
    		}
    	}
    	if ((col1Row2 == 7) && (col2Row2 == 7) && (col3Row2 == 7)) {
    		if(!win) {
    			Money.tripleDiamond();
    			winnings = Money.winnings();
    			lblWinnings.setText("Winnings: £" + round(winnings));
    			lblWin.setText("WINNER!!!");
                JOptionPane.showMessageDialog(frame, "£1.50 was awarded for matching 3 Diamonds");
                win = true;
                if(!btnCashIn.isEnabled()) {
                    btnCashIn.setEnabled(true);
                }
                if(!btnReCredit.isEnabled()){
                    btnReCredit.setEnabled(true);
                }
    		}
    	}
        if ((col1Row2 == col2Row2) && (col1Row2 == col3Row2)){
            if (!win){
                Money.tripleWin();
                winnings = Money.winnings();
                lblWinnings.setText("Winnings: £" + round(winnings));
                lblWin.setText("WINNER!!!");
                JOptionPane.showMessageDialog(frame, "£1 was awarded for matching 3 shapes");
                win = true;
                if(!btnCashIn.isEnabled()) {
                    btnCashIn.setEnabled(true);
                }
                if(!btnReCredit.isEnabled()){
                    btnReCredit.setEnabled(true);
                }
            }
        }
        if ((col1Row2 == col3Row2) || (col2Row2 == col3Row2)){
            if (!win){
                Money.doubleWin();
                winnings = Money.winnings();
                lblWinnings.setText("Winnings: £" + round(winnings));
                lblWin.setText("WINNER!!!");
                JOptionPane.showMessageDialog(frame, "50p was awarded for matching 2 shapes");
                win = true;
                if(!btnCashIn.isEnabled()) {
                    btnCashIn.setEnabled(true);
                }
                if(!btnReCredit.isEnabled()){
                    btnReCredit.setEnabled(true);
                }
            }
        }
        if (col3Row2 == 7) {
            if (!win) {
                Money.singleWin();
                winnings = Money.winnings();
                lblWinnings.setText("Winnings: £" + round(winnings));
                lblWin.setText("WINNER!!!");
                JOptionPane.showMessageDialog(frame, "20p was awarded for a special shape");
                win = true;
                if(!btnCashIn.isEnabled()) {
                    btnCashIn.setEnabled(true);
                }
                if(!btnReCredit.isEnabled()){
                    btnReCredit.setEnabled(true);
                }
            }
        }
    }

    private void CreateArray(){
        list.add(gameNumber);
        list.add(col1Row2);
        list.add(col2Row2);
        list.add(col3Row2);
        System.out.println(Collections.singletonList(list));
    }

    private void logGame(String s) {

        File log = new File("log.txt");
        try{
            if(!log.exists()){
                System.out.println("We had to make a new file.");
                log.createNewFile();
            }
            PrintWriter out = new PrintWriter(new FileWriter(log, true));
            out.append(s).append(" ").append(String.valueOf(Collections.singletonList(list))).append(System.lineSeparator());
            out.close();
        }catch(IOException e){
            System.out.println("COULD NOT LOG!!");
        }
    }

    private void CreateReplayArray(){
        for (int i = gameNumber - 1; i < numberOfGames; i++){
            selector[i] = "" + gameNumber;
            System.out.println("test "+ selector[i]);
            cbReplayPicker.addItem(selector[i]);
        }
    }

    private void InitialCol(){
        initialCol1 = col1;
        initialCol2 = col2;
        initialCol3 = col3;
    }

    private void InitialSpins(){
        initialCol1Spins = randomNumber1;
        initialCol2Spins = randomNumber2;
        initialCol3Spins = randomNumber3;
    }

    private void SaveReplay(){
        File Replay = new File("Replay_" + gameNumber + ".txt");
        try {
            if (!Replay.exists()) {
                Replay.createNewFile();
            }
            PrintWriter out = new PrintWriter(new FileWriter(Replay, false));
            out.print(initialCol1 + " ");
            out.print(initialCol2 + " ");
            out.print(initialCol3 + " ");
            out.print(initialCol1Spins + " ");
            out.print(initialCol2Spins + " ");
            out.print(initialCol3Spins + " ");
            out.print(col1Hold + " ");
            out.print(col2Hold + " ");
            out.print(col3Hold + " ");
            out.close();

        }catch (IOException e){
            System.out.println("COULD NOT SAVE REPLAY");
        }
    }

    private void SaveNudge(){
        File Replay = new File("Replay_" + gameNumber + ".txt");
        try{
            PrintWriter out = new PrintWriter(new FileWriter(Replay, true));
            out.println(nudgeUsed);
            out.close();
        }catch (IOException e){
            System.out.println("COULD NOT SAVE NUDGE");
        }
    }

    static int GameNumber(){
            return replayGameNumber;
    }

    static Point Location(){
            return location;
    }

    private Clip clip;

    {
        try {
            clip = AudioSystem.getClip();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    private void playSound() {
        if(!mute) {
            try {
                AudioInputStream inputStream = AudioSystem.getAudioInputStream(
                        UseBandit.class.getResourceAsStream("/Sound/spin.wav"));
                clip.open(inputStream);
                clip.start();
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }
}
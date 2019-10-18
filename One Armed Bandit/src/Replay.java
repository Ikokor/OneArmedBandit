import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class Replay extends JFrame implements ActionListener {


	private static final long serialVersionUID = 1L;
	private int col1;
    private int col2;
    private int col3;
    private int col1Spins;
    private int col2Spins;
    private int col3Spins;
    private boolean col1Hold;
    private boolean col2Hold;
    private boolean col3Hold;
    private int nudgeUsed;
    private int gameNumber;
    private int col1Row1;
    private int col1Row2;
    private int col1Row3;
    private int col2Row1;
    private int col2Row2;
    private int col2Row3;
    private int col3Row1;
    private int col3Row2 ;
    private int col3Row3;

    private JLabel lblIconCol1Row1;
    private JLabel lblIconCol1Row2;
    private JLabel lblIconCol1Row3;
    private JLabel lblIconCol2Row1;
    private JLabel lblIconCol2Row2;
    private JLabel lblIconCol2Row3;
    private JLabel lblIconCol3Row1;
    private JLabel lblIconCol3Row2;
    private JLabel lblIconCol3Row3;
    private JButton btnStartReplay;
    private Timer column1;
    private Timer column2;
    private Timer column3;
    private Timer nudge;

    Replay() {
        setTitle("Replay");
        setIconImage(Toolkit.getDefaultToolkit().getImage(UseBandit.class.getResource("/Icons/SlotMachine.png")));
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        Point location = UseBandit.Location();
        location.translate(100, 100);
        setLocation(location);
        setSize(420, 456);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        gameNumber = UseBandit.GameNumber();
        System.out.println(gameNumber);
        LoadReplay();
        System.out.println("test");

        /* Column 1 */

        lblIconCol1Row1 = new JLabel("");
        lblIconCol1Row1.setIcon(new ImageIcon(UseBandit.class.getResource("/Icons/" + col1Row1 + ".png")));
        lblIconCol1Row1.setBounds(84, 80, 64, 64);
        contentPane.add(lblIconCol1Row1);

        lblIconCol1Row2 = new JLabel("");
        lblIconCol1Row2.setIcon(new ImageIcon(UseBandit.class.getResource("/Icons/" + col1Row2 + ".png")));
        lblIconCol1Row2.setBounds(84, 164, 64, 64);
        contentPane.add(lblIconCol1Row2);

        lblIconCol1Row3 = new JLabel("");
        lblIconCol1Row3.setIcon(new ImageIcon(UseBandit.class.getResource("/Icons/" + col1Row3 + ".png")));
        lblIconCol1Row3.setBounds(84, 248, 64, 64);
        contentPane.add(lblIconCol1Row3);

        /* Column 2 */

        lblIconCol2Row1 = new JLabel("");
        lblIconCol2Row1.setIcon(new ImageIcon(UseBandit.class.getResource("/Icons/" + col2Row1 + ".png")));
        lblIconCol2Row1.setBounds(168, 80, 64, 64);
        contentPane.add(lblIconCol2Row1);

        lblIconCol2Row2 = new JLabel("");
        lblIconCol2Row2.setIcon(new ImageIcon(UseBandit.class.getResource("/Icons/" + col2Row2 + ".png")));
        lblIconCol2Row2.setBounds(168, 164, 64, 64);
        contentPane.add(lblIconCol2Row2);

        lblIconCol2Row3 = new JLabel("");
        lblIconCol2Row3.setIcon(new ImageIcon(UseBandit.class.getResource("/Icons/" + col2Row3 + ".png")));
        lblIconCol2Row3.setBounds(168, 248, 64, 64);
        contentPane.add(lblIconCol2Row3);

        /* Column 3 */

        lblIconCol3Row1 = new JLabel("");
        lblIconCol3Row1.setIcon(new ImageIcon(UseBandit.class.getResource("/Icons/" + col3Row1 + ".png")));
        lblIconCol3Row1.setBounds(252, 80, 64, 64);
        contentPane.add(lblIconCol3Row1);

        lblIconCol3Row2 = new JLabel("");
        lblIconCol3Row2.setIcon(new ImageIcon(UseBandit.class.getResource("/Icons/" + col3Row2 + ".png")));
        lblIconCol3Row2.setBounds(252, 164, 64, 64);
        contentPane.add(lblIconCol3Row2);

        lblIconCol3Row3 = new JLabel("");
        lblIconCol3Row3.setIcon(new ImageIcon(UseBandit.class.getResource("/Icons/" + col3Row3 + ".png")));
        lblIconCol3Row3.setBounds(252, 248, 64, 64);
        contentPane.add(lblIconCol3Row3);

        /* Buttons */
        btnStartReplay = new JButton("Start Replay");
        btnStartReplay.setBounds(145,342,110,20);
        btnStartReplay.addActionListener(this);
        contentPane.add(btnStartReplay);
    }

    private void LoadReplay(){
        Scanner fScn = null;
        try {
            fScn = new Scanner(new File("Replay_" + gameNumber + ".txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String data;

        assert fScn != null;
        while( fScn.hasNextLine() ){
            data = fScn.nextLine();

            String[] token = data.split(" ");
            col1 = Integer.parseInt(token[0]);
            col2 = Integer.parseInt(token[1]);
            col3 = Integer.parseInt(token[2]);
            col1Spins = Integer.parseInt(token[3]);
            col2Spins = Integer.parseInt(token[4]);
            col3Spins = Integer.parseInt(token[5]);
            col1Hold = Boolean.parseBoolean(token[6]);
            col2Hold = Boolean.parseBoolean(token[7]);
            col3Hold = Boolean.parseBoolean(token[8]);
            try {
                nudgeUsed = Integer.parseInt(token[9]);
            }catch(Exception e){
                System.out.println("No Nudge");
            }
        }
        fScn.close();
        SetupValues();
    }

    private void SetupValues() {
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
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnStartReplay){
            Column1Timer();
            Column2Timer();
            Column3Timer();
        }
    }

    private void Column1Timer() {
        if (!col1Hold) { //HOLD FEATURE
            column1 = new Timer();
            column1.schedule(new Replay.Column1Task(),
                    100,        //initial delay
                    300);  //subsequent rate
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
            column2.schedule(new Replay.Column2Task(),
                    100,        //initial delay
                    300);  //subsequent rate
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
        column3.schedule(new Replay.Column3Task(),
                100,        //initial delay
                300);  //subsequent rate
    }


    class Column3Task extends TimerTask {
        public void run() {
            if (col3Spins >= 1) {
                if (!col3Hold) { //HOLD FEATURE
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
                col3Spins--;
            } else {
                column3.cancel();
                NudgeTimer();
            }
        }
    }

    private void NudgeTimer(){
        nudge = new Timer();
        nudge.schedule(new Replay.NudgeTask(),
                500,
                100);
    }

    class NudgeTask extends TimerTask{
        int nudgeWait = 3;
        public void run(){
            if (nudgeWait >= 1){
                nudgeWait --;
            }else{
                nudge.cancel();
                if (nudgeUsed == 1){
                    Nudge1();
                }
                if (nudgeUsed == 2){
                    Nudge2();
                }
                if (nudgeUsed ==3){
                    Nudge3();
                }
            }

        }
    }

    private void Nudge1() {
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

        lblIconCol1Row1.setIcon(new ImageIcon(UseBandit.class.getResource("/Icons/" + col1Row1 + ".png")));
        lblIconCol1Row2.setIcon(new ImageIcon(UseBandit.class.getResource("/Icons/" + col1Row2 + ".png")));
        lblIconCol1Row3.setIcon(new ImageIcon(UseBandit.class.getResource("/Icons/" + col1Row3 + ".png")));
    }

    private void Nudge2() {
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
        lblIconCol2Row1.setIcon(new ImageIcon(UseBandit.class.getResource("/Icons/" + col2Row1 + ".png")));
        lblIconCol2Row2.setIcon(new ImageIcon(UseBandit.class.getResource("/Icons/" + col2Row2 + ".png")));
        lblIconCol2Row3.setIcon(new ImageIcon(UseBandit.class.getResource("/Icons/" + col2Row3 + ".png")));
    }

    private void Nudge3() {
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
}
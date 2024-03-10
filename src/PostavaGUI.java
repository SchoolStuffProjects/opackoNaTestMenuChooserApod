import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PostavaGUI extends JFrame{
    private List<Postava> postavaList = new ArrayList<>();
    private JTextField charName;
    private JRadioButton costRB1;
    private JRadioButton costRB2;
    private JRadioButton costRB3;
    private JRadioButton costRB4;
    private JRadioButton costRB5;
    private JCheckBox headlinerBox;
    private JRadioButton starRB1;
    private JRadioButton starRB2;
    private JRadioButton starRB3;
    private JPanel panel;
    private JButton prevBtn;
    private JButton nextBtn;
    private int index = 0;

    public Postava getPostavaList(int i){
        return postavaList.get(i);
    }
    public PostavaGUI() {
        setContentPane(panel);
        setSize(500,500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        initMenu();
        prevBtn.addActionListener(e -> prev());
        nextBtn.addActionListener(e -> next());

        ButtonGroup costGrp = new ButtonGroup();
        costGrp.add(costRB1);
        costGrp.add(costRB2);
        costGrp.add(costRB3);
        costGrp.add(costRB4);
        costGrp.add(costRB5);

        ButtonGroup starGrp = new ButtonGroup();
        starGrp.add(starRB1);
        starGrp.add(starRB2);
        starGrp.add(starRB3);
    }

    public void prev(){
        if (index > 0){
            index--;
            display(getPostavaList(index));
        }
    }
    public void next(){
        if (index < postavaList.size()-1){
            index++;
            display(getPostavaList(index));
        }
    }

    public void display(Postava postava){
        charName.setText(postava.getName());
        switch (postava.getCost()){
            case 1 -> costRB1.setSelected(true);
            case 2 -> costRB2.setSelected(true);
            case 3 -> costRB3.setSelected(true);
            case 4 -> costRB4.setSelected(true);
            case 5 -> costRB5.setSelected(true);
        }
        switch (postava.getStar()){
            case 1 -> starRB1.setSelected(true);
            case 2 -> starRB2.setSelected(true);
            case 3 -> starRB3.setSelected(true);
        }
        headlinerBox.setSelected(postava.isHeadLiner());
    }

    public void  readBro(File selectedFile){
        index = 0;
        postavaList.clear();
        try(Scanner sc = new Scanner(new BufferedReader(new FileReader(selectedFile)))){
            while(sc.hasNextLine()){
                String line = sc.nextLine();
                String[] parts = line.split(";");
                String name = (parts[0]);
                int cost = Integer.parseInt(parts[1]);
                int star = Integer.parseInt(parts[2]);
                boolean headliner = parts[3].equals("yes");
                postavaList.add(new Postava(name, cost, star, headliner));
                display(postavaList.get(index));
            }
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(this, "File not found: " + e.getLocalizedMessage());
        } catch (NumberFormatException e ){
            JOptionPane.showMessageDialog(this, "Kura neumíš psát čísla: " + e.getLocalizedMessage());
        }
    }

    public void chooseBro(){
        JFileChooser fc = new JFileChooser(".");
        fc.setFileFilter(new FileNameExtensionFilter("Nigger", "txt"));
        int result = fc.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION){
            File selectedFile = fc.getSelectedFile();
            readBro(selectedFile);
        }
    }

    public void initMenu(){
        JMenuBar jmb = new JMenuBar();
        setJMenuBar(jmb);

        JMenu jm = new JMenu("Actions");
        jmb.add(jm);

        JMenuItem addItem = new JMenuItem("Add");
        jm.add(addItem);
        addItem.addActionListener(e-> {});

        JMenuItem chooseItem = new JMenuItem("Choose bro");
        jm.add(chooseItem);
        chooseItem.addActionListener(e-> chooseBro());
    }
    public static void main(String[] args) {
        PostavaGUI p = new PostavaGUI();
        p.setVisible(true);
    }
}

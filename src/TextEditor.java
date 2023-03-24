import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

import static java.awt.event.KeyEvent.*;

public class TextEditor implements ActionListener {

    JFrame frame;
    JMenuBar menuBar;
    JMenu file,edit,exit, utility;
    JMenuItem newFile, openFile, saveFile, cut,copy, paste, selectAll,close, calculator,browser;
    JTextArea textArea;
    TextEditor(){
        frame = new JFrame();
        menuBar = new JMenuBar();
        textArea = new JTextArea();

        // Initialize menus
        file = new JMenu("File");
        edit = new JMenu("Edit");
        exit = new JMenu("Exit");
        utility = new JMenu("Utility");



        frame.setJMenuBar(menuBar);
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(5,5,5,5));
        panel.setLayout(new BorderLayout(0,0));
        panel.add(textArea,BorderLayout.CENTER);

        JScrollPane scrollPane = new JScrollPane(textArea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        panel.add(scrollPane);
        frame.add(panel);

        // Initialize file menu Item
        newFile = new JMenuItem("New Window");
        openFile = new JMenuItem("Open File");
        saveFile = new JMenuItem("Save File");

        newFile.setMnemonic('N');
        newFile.setAccelerator(KeyStroke.getKeyStroke(VK_N, ActionEvent.CTRL_MASK));
        openFile.setMnemonic('O');
        openFile.setAccelerator(KeyStroke.getKeyStroke(VK_O,ActionEvent.CTRL_MASK));
        saveFile.setMnemonic('S');
        saveFile.setAccelerator(KeyStroke.getKeyStroke(VK_S,ActionEvent.CTRL_MASK));
        //add actionlisener
        newFile.addActionListener(this);
        openFile.addActionListener(this);
        saveFile.addActionListener(this);

        //add menu item to file menu
        file.add(newFile);
        file.add(openFile);
        file.add(saveFile);

        //initialize edit menu item
        cut = new JMenuItem("Cut");
        copy = new JMenuItem("Copy");
        paste = new JMenuItem("Paste");
        selectAll = new JMenuItem("Select All");

        cut.setMnemonic('K');
        cut.setAccelerator(KeyStroke.getKeyStroke(VK_K,ActionEvent.CTRL_MASK));
        copy.setMnemonic('C');
        copy.setAccelerator(KeyStroke.getKeyStroke(VK_C,ActionEvent.CTRL_MASK));
        paste.setMnemonic('P');
        paste.setAccelerator(KeyStroke.getKeyStroke(VK_P,ActionEvent.CTRL_MASK));
        selectAll.setMnemonic('A');
        selectAll.setAccelerator(KeyStroke.getKeyStroke(VK_A,ActionEvent.CTRL_MASK));

        //add actionlisener
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);


        //add item in edit menu
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectAll);

        calculator = new JMenuItem("Calculator");
        browser = new JMenuItem("Browser");

        calculator.setMnemonic('C');
        calculator.setAccelerator(KeyStroke.getKeyStroke(VK_C,ActionEvent.SHIFT_MASK));
        browser.setMnemonic('B');
        browser.setAccelerator(KeyStroke.getKeyStroke(VK_B,ActionEvent.CTRL_MASK));
        calculator.addActionListener(this);
        browser.addActionListener(this);

        //add item in utality
        utility.add(calculator);
        utility.add(browser);


        close = new JMenuItem("Close Window");
        close.setMnemonic('X');
        close.setAccelerator(KeyStroke.getKeyStroke(VK_X,ActionEvent.CTRL_MASK));
        close.addActionListener(this);
        exit.add(close);

        // add menu to menubar
        menuBar.add(file);
        menuBar.add(edit);
        menuBar.add(utility);
        menuBar.add(exit);

        frame.setBounds(250,150, 900,550);
        frame.setTitle("Text Editor");
        frame.setVisible(true);
        frame.setLayout(null);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent){
        if(actionEvent.getSource() == cut){
            textArea.cut();
        }
        if(actionEvent.getSource() == copy){
            textArea.copy();
        }
        if(actionEvent.getSource() == paste){
            textArea.paste();
        }
        if(actionEvent.getSource() == selectAll){
            textArea.selectAll();
        }
        if(actionEvent.getSource() == close){
            System.exit(0);
        }
        if(actionEvent.getSource() == calculator){
            try {
                Runtime.getRuntime().exec("calc.exe");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        if(actionEvent.getSource() == browser){
            try {
                Runtime.getRuntime().exec("C:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        if(actionEvent.getSource() == openFile){
            //Open a file chooser
            JFileChooser fileChooser =new JFileChooser("C:");
            int chooseOption = fileChooser.showOpenDialog(null);
            //If we have clicked on Open button
            if(chooseOption== JFileChooser.APPROVE_OPTION){
                // getting selected file
                File file = fileChooser.getSelectedFile();
                // get the path of selected file
                String filePath = file.getPath();
                try{
                    //Initialize File reader
                    FileReader fileReader = new FileReader(filePath);
                    //Initialize Buffer Reader
                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                    String intermediate="", output="";
                    while((intermediate = bufferedReader.readLine()) != null){
                        output += intermediate+"\n";
                    }
                    textArea.setText(output);
                }catch (IOException fileNotFoundException){
                    fileNotFoundException.printStackTrace();
                }

            }
        }
        if(actionEvent.getSource() == saveFile){
            //Initialize the file picker
            JFileChooser fileChooser = new JFileChooser("C:");
            // get choose option from file chooser
            int chooseOption  = fileChooser.showSaveDialog(null);
            // if we click save button
            if(chooseOption == JFileChooser.APPROVE_OPTION){
                File file = new File(fileChooser.getSelectedFile().getAbsoluteFile()+".txt");
                try{
                    // initialize file writer
                    FileWriter fileWriter = new FileWriter(file);
                    //Initialize buufer writter
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    textArea.write(bufferedWriter);
                    bufferedWriter.close();
                }catch(IOException ioException){
                    ioException.printStackTrace();
                }
            }
        }
        if(actionEvent.getSource() == newFile){
            TextEditor newTextEditor = new TextEditor();
        }
    }
    public static void main(String[] args) {
        TextEditor textEditor = new TextEditor();
    }
}
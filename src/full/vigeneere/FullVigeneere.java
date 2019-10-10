/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package full.vigeneere;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author shark
 */
public class FullVigeneere extends JFrame {

    private ImageIcon plain = new ImageIcon("/home/shark/Pictures/plain.png");
    private ImageIcon Key = new ImageIcon("/home/shark/Pictures/key.png");
    private ImageIcon cipher = new ImageIcon("/home/shark/Pictures/cipher.png");
    private ImageIcon encrypt = new ImageIcon("/home/shark/Pictures/encrypt.png");
    private JLabel PlainText = new JLabel("Plain Text");
    private JLabel Encrypted = new JLabel("Encrypted text");
    private JButton Encrypte = new JButton(encrypt);
    private JTextField text1 = new JTextField(50);
    private JTextField text2 = new JTextField(7);
    private JTextField text3 = new JTextField(50);

    private ImageIcon plain2 = new ImageIcon("/home/shark/Pictures/plain.png");
    private ImageIcon Key2 = new ImageIcon("/home/shark/Pictures/key.png");
    private ImageIcon cipher2 = new ImageIcon("/home/shark/Pictures/cipher.png");
    private ImageIcon decrypt = new ImageIcon("/home/shark/Pictures/decrypt.png");
    private JLabel PlainText2 = new JLabel("Plain Text");
    private JLabel Encrypted2 = new JLabel("Encrypted text");
    private JButton DEncrypte = new JButton(decrypt);
    private JTextField text11 = new JTextField(200);
    private JTextField text22 = new JTextField(50);
    private JTextField text33 = new JTextField(200);

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //JFrame mem =new JFrame();
        // mem.setLayout(new GridLayout(5,2,3,3));
        FullVigeneere frame = new FullVigeneere();
        // frame.setLayout(new GridLayout(5,2,3,3));
        frame.setSize(800, 700);
        frame.setTitle("FullVigeneere");
        frame.setLocationRelativeTo(null); // Center the frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }

    public FullVigeneere() {
        setLayout(new GridLayout(8, 1, 5, 2));
        JPanel jpTextField = new JPanel();
        jpTextField.setLayout(new BorderLayout(5, 0));
        jpTextField.add(new JLabel(plain), BorderLayout.WEST);
        jpTextField.add(text1, BorderLayout.CENTER);
        text1.setHorizontalAlignment(JTextField.LEFT);
        add(jpTextField, BorderLayout.NORTH);
        jpTextField.setLocation(0, 0);
        jpTextField.setSize(500, 50);
        //////////////////////////////////////////////////
        JPanel jpTextField2 = new JPanel();
        jpTextField2.setLayout(new BorderLayout(5, 0));
        jpTextField2.add(new JLabel(Key), BorderLayout.WEST);
        jpTextField2.add(text2, BorderLayout.CENTER);
        text2.setHorizontalAlignment(JTextField.LEFT);
        add(jpTextField2, BorderLayout.CENTER);
        jpTextField2.setSize(500, 50);
        //////////////////////////////////////////////////
        JPanel jpTextField3 = new JPanel();
        jpTextField3.setLayout(new BorderLayout(5, 0));
        jpTextField3.add(new JLabel(cipher), BorderLayout.WEST);
        jpTextField3.add(text3, BorderLayout.CENTER);
        text3.setHorizontalAlignment(JTextField.LEFT);
        add(jpTextField3, BorderLayout.SOUTH);
        jpTextField3.setSize(500, 50);
        //////////////////////////////////////////////////
        add(Encrypte, FlowLayout.LEADING);
        //////////////////////////////////////////////////////////////////////////////////////////////////
        JPanel jpTextField4 = new JPanel();
        jpTextField4.setLayout(new BorderLayout(5, 0));
        jpTextField4.add(new JLabel(cipher2), BorderLayout.WEST);
        jpTextField4.add(text11, BorderLayout.CENTER);
        text11.setHorizontalAlignment(JTextField.LEFT);
        add(jpTextField4, BorderLayout.NORTH);
        jpTextField4.setSize(500, 50);

        JPanel jpTextField5 = new JPanel();
        jpTextField5.setLayout(new BorderLayout(5, 0));
        jpTextField5.add(new JLabel(Key2), BorderLayout.WEST);
        jpTextField5.add(text22, BorderLayout.CENTER);
        text22.setHorizontalAlignment(JTextField.LEFT);
        add(jpTextField5, BorderLayout.NORTH);

        JPanel jpTextField6 = new JPanel();
        jpTextField6.setLayout(new BorderLayout(5, 0));
        jpTextField6.add(new JLabel(plain2), BorderLayout.WEST);
        jpTextField6.add(text33, BorderLayout.CENTER);
        text33.setHorizontalAlignment(JTextField.LEFT);
        add(jpTextField6, BorderLayout.NORTH);

        add(DEncrypte, BorderLayout.SOUTH);
        /////////////////////////////////////////////////////////////////////////////
        Encrypte.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = text1.getText();
                String key = text2.getText();
                text3.setText(vigenere_cipher(text, key, true));
            }
        });

        DEncrypte.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = text11.getText();
                String key = text22.getText();
                text33.setText(vigenere_cipher(text, key, false));
            }
        });

    }
    public static String vigenere_cipher(String plaintext, String key, boolean encrypt) {

        String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ ,.-_"; // including some special chars
        int alphabetSize = alphabet.length();
        int textSize = plaintext.length();
        int keySize = key.length();
        StringBuilder encryptedText = new StringBuilder(textSize);

        for (int i = 0; i < textSize; i++) {
            char plainChar = plaintext.charAt(i); // get the current character to be encrypted
            char keyChar = key.charAt(i % keySize); // use key again if the end is reached...
            int plainPos = alphabet.indexOf(plainChar); // plain character's position in alphabet string
            if (plainPos == -1) { // if character not in alphabet just append  to the result text
                encryptedText.append(plainChar);
            } else { // if character is in alphabet encrypt it and append the new character to the result text
                int keyPos = alphabet.indexOf(keyChar); // key character's position in alphabet string
                if (encrypt) { // encrypt the input text
                    encryptedText.append(alphabet.charAt((plainPos + keyPos) % alphabetSize));
                } else { // decrypt the input text
                    int shiftedPos = plainPos - keyPos;
                    if (shiftedPos < 0) { // negative numbers cannot be handled with modulo
                        shiftedPos += alphabetSize;
                    }
                    encryptedText.append(alphabet.charAt(shiftedPos));
                }
            }
        }
        return encryptedText.toString();
    }
}
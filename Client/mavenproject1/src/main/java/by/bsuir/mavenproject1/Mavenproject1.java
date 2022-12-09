package by.bsuir.mavenproject1;


import by.bsuir.mavenproject1.form.SingInForm;
import javax.swing.JFrame;

public class Mavenproject1 {
    
public static void main(String[] args) {   
        SingInForm sin = new SingInForm();
        sin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        sin.setLocationRelativeTo(null);
        sin.setVisible(true);       
    }  
}


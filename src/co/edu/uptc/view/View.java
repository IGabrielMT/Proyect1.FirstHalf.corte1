package co.edu.uptc.view;

import java.util.Scanner;

public class View {
    Scanner sc = new Scanner(System.in);
    public void showMessage(String message){
        System.out.println(message);
    }
    public String askInfo(String message){
        System.out.println(message);
        return sc.next();
    }
    public void menu(String[] menu){
        for (int i = 0; i < menu.length; i++) {
            System.out.println(i+1 + "." + " " + menu[i]);
        }
    }
}

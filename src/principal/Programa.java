package principal;

import util.Session;
import banco.BancoDeDados;
import menus.Menus;
import java.util.Scanner;
import modelos.*;

public class Programa {
	public static void main(String[] args) {
		Session.banco = new BancoDeDados();
		Session.show = new Show();
		Scanner sc = new Scanner(System.in);
		
		Menus.menuPrincipal(sc);
		
		sc.close();
	}
}

package ba.bitcamp.homework15.task03;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

//Sve je okay osim kao sto rekoh ne preferiram ovo pisanje koda posto nakon nekog vremena neces znati sta i kako radi klasa,
//tj, licno mislim da treba izvlaciti metode, praviti male klase i dati pokoji komentar u kodu :) 
public class Task03 extends JFrame {

	/*
	 * Napraviti GUI aplikaciju koja ima jedan JLabel i jedan JButton. JLabel je
	 * na poèetku prazan, ali nakon svake sekunde dobija jedno po jedno slovo
	 * abecede, pa tako nakon tri sekunde JLabel æe imati u sebi „abc“, a nakon
	 * pet æe imati „abcde“. Klikom na dugme se zaustavi taj proces, ali ne i
	 * aplikacija. Koristiti vlastite Thread-ove za ovaj zadatak! Nipošto ne
	 * koristiti glavni Thread za JLabel!
	 */
	
	private String text = "";
	private JLabel label = new JLabel();
	private JButton button = new JButton("Stop");

	public Task03 () {
		
		setLayout(new BorderLayout());
		add(label, BorderLayout.CENTER);
		add(button, BorderLayout.SOUTH);
		label.setHorizontalAlignment(JLabel.CENTER);
		
		final Thread t = new Thread(new MyThread());
		t.start();
		
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				t.interrupt();
				try {
					t.sleep(10000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		setSize(300, 300);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);

	
	}
	
	class MyThread implements Runnable {

		@Override
		public void run() {
			int num = 97;
			while (true) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					System.out.println("Interrupted!");
				}
				text += (char)num;
				label.setText(text);
				num++;
				if (num == 123) {
					break;
				}
			}
			
		}
		
	}

	public static void main(String[] args) {
		new Task03();

	}

}

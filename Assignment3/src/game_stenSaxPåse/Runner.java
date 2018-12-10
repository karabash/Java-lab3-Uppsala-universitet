package game_stenSaxPåse;
import java.util.Random;
import java.util.Scanner;

public class Runner {


		public static void main(String[] args) {
			
			Scanner scanner = new Scanner(System.in);
			String inputString = "";
			int[] score;
			String[] alternatives = {"sten", "sax", "påse"};
			String[] methods = {"krossar", "klipper", "fångar"};
			boolean quit = false, isWinner;
			
			while(!quit) {
				
				System.out.println("** VÄLKOMMEN TILL STEN-SAX-PÅSE **");
				System.out.println("Spelet där sten krossar sax, sax klipper påse och påse fångar sten. Bäst av fem vinner.");
				System.out.println("Avsluta programmet genom att skriva Q.");
				
				isWinner = false;
				score = new int[2];	
				
				while(!quit && !isWinner) {
					System.out.println("Gör ditt val (sten, sax eller påse): ");
					
					inputString = scanner.nextLine().trim();
					
					if(inputString.equalsIgnoreCase("Q")) {
						System.out.println("Hej då!");
						quit = true;
					}
					
					else {

						int input = -1;
						
						for(int i = 0; i < alternatives.length; i ++) {
							if(alternatives[i].equalsIgnoreCase(inputString)) {
								input = i;
							}
						}	
						
						if(input == -1) {
							System.out.println("Ogiltigt värde. Giltiga värden är sten, sax eller påse.");
						}
						
						else {										
							//here
						int computerChoice = new Random().nextInt(3);
							
							System.out.println("Datorn väljer " + alternatives[computerChoice] + ".");
						//	alternatives[2]-1                        2+2 = 4
							if((computerChoice - 1) == input || (computerChoice + 2) == input) {
								score[0] ++;
								System.out.println("Din " + alternatives[input] + " " + methods[input] + " datorns " + 
										alternatives[computerChoice] + " (" + score[0] + " - " + score[1] + ").");
							}
							
							else if(computerChoice == input) {
								System.out.println("Samma val, ingen vinner.");
							}
							
							else {
								
								score[1] ++;
								System.out.println("Datorns " + alternatives[computerChoice] + " " + methods[computerChoice] + 
										" din " + alternatives[input] + " (" + score[0] + " - " + score[1] + ").");
							}
							
							if(score[0] == 3 || score[1] == 3) {
								
								String winner = "Datorn";
								
								if(score[0] == 3) {
									winner = "Du";
								}
								
								System.out.println(winner + " har vunnit (" + score[0] + " - " + score[1] + ")!");
								isWinner = true;
							}
						}					
					}
				}			
			}	
			
			scanner.close();		
		}
	}




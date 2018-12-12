package game_stenSaxPåse;
import java.util.List;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class GameFile {
	private final String location = "C:/eclipse/score.txt";

	// default inner class (only package access)
	class ScoreWritter{

		public String writeScore( String text) throws IOException {
			// det creates an instance of FilewWriter and
			// takes 2 arguments String (file location) and boolean
			// the boolean arguments helps to write end of the file rather than beginning
			FileWriter fw = new FileWriter(GameFile.this.location, true); 

			// the write method takes one argument String to write a text
			// the  inside the write(String str) method 
			//void java.io.Writer.write(String str, int off, int len) throws IOException
			fw.write(text);
			fw.flush(); //flush before closing
			fw.close(); // close file when done
			// return String class
			return text;
		}
	}

	// default inner class (only package access)
	class ScoreReader{

		private int playerTotalScore=0; // instance variable 
		private int computerScore=0; // instance variable 
		private int tie = 0; // instance variable 

		// getter
		public int getPlayerTotalScore() {
			return playerTotalScore;
		}
		//setter
		private void setPlayerTotalScore(int playerTotalScore) {
			this.playerTotalScore = playerTotalScore;
		}
		//getter 
		public int getComputerScore() {
			return computerScore;
		}
		//setter 
		private void setComputerScore(int computerScore) {
			this.computerScore = computerScore;
		}
		// getter
		public int getTie() {
			return tie;
		}
		//setter
		private void setTie(int tie) {
			this.tie = tie;
		}

		// this method takes an argument to takse user name
		// to and that on score
		void reader(String userName) {
			//the filepath refrence variable represents file path 
			final Path filePath = Paths.get("C:/eclipse/score.txt");

			//the  charset refrence variable arguments as string
			// to request charset and this method returns charset object
			final Charset charset = Charset.forName("ISO-8859-1");

			// try
			try {
				//readAlllines method return local object lines (List<String>)
				List<String> lines = Files.readAllLines(filePath, charset);

				//every lines objects assigned it's value to line to through Enhanced for loop 
				//Line by line
				for(String line : lines) {

					// if score.text contains "Det är Jämnt!" 
					//  returns true otherwise false
					if(line.contains("Det är Jämnt!")) {
						//instance variable tie increase by 1 and
						this.setTie(++tie);
					}
					//// if score.text contains ""Datorn score är 1!" or
					//"Datorn score är 2 or "Datorn score är 3 or
					//"Datorn score är 4 or "Datorn score är 5
					//  returns true otherwise false
					else  if(line.contains("Datorn score är 1") 
							| line.contains("Datorn score är 2") | line.contains("Datorn score är 3") 
							|  line.contains("Datorn score är 4" ) | line.contains("Datorn score är 5") )  {
						//instance variable computerScore increase by 1 and
						this.setComputerScore(++computerScore);		
					}
					else {
						//if the readen line does not contain 
						// "Datorn score är 0"  returns true otherwise false
						if(!line.contains("Datorn score är 0"))
							//instance variable computerScore increase by 1 and
							this.setPlayerTotalScore(++playerTotalScore);		
					}

				}
				// prints out total score
				System.out.println("Total ställning: "+ userName +" "+ playerTotalScore +
						" Datorn score "+ computerScore + " Tie " + tie);
				// reset all scores 
				computerScore=0;
				playerTotalScore=0;
				tie=0;
				// catch run time exception
			}catch (IOException e) {
				// If any run time exception will catch prints out
				// info for user
				System.out.println("Score files on process! Play another time");
				// exit
				System.exit(0);
			}

		}

	}

}







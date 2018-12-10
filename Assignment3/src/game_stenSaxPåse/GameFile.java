package game_stenSaxPåse;
import java.util.List;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;


public class GameFile {


	private final String location = "C:/eclipse/score.txt";

	// default inner class (only package access)
	class ScoreWritter{

		public String writeScore( String text) throws IOException {
			FileWriter fw = new FileWriter(GameFile.this.location, true); 
			fw.write(text);
			fw.flush(); //flush before closing
			fw.close(); // close file when done
			return text;
		}
	}

	// default inner class (only package access)
	class ScoreReader{
		private int playerTotalScore=0;
		private int computerScore=0;
		private int tie = 0;


		public int getPlayerTotalScore() {
			return playerTotalScore;
		}

		private void setPlayerTotalScore(int playerTotalScore) {
			this.playerTotalScore = playerTotalScore;
		}

		public int getComputerScore() {
			return computerScore;
		}

		private void setComputerScore(int computerScore) {
			this.computerScore = computerScore;
		}

		public int getTie() {
			return tie;
		}

		private void setTie(int tie) {
			this.tie = tie;
		}

		void reader(String userName) {
			boolean isItFirstLoading = true;
			boolean isItLastreading = true;

			final Path filePath = Paths.get("C:/eclipse/score.txt");
			final Charset charset = Charset.forName("ISO-8859-1");
			try {
				List<String> lines = Files.readAllLines(filePath, charset);
				for(String line : lines) {

					if(line.contains("Det är Jämnt!")) {
						this.setTie(++tie);
					}
					else  if(line.contains("Datorn score är 1") 
							| line.contains("Datorn score är 2") | line.contains("Datorn score är 3") 
							|  line.contains("Datorn score är 4" ) | line.contains("Datorn score är 5") )  {
						this.setComputerScore(++computerScore);					}
					else {
						if(!line.contains("Datorn score är 0"))
							this.setPlayerTotalScore(++playerTotalScore);		
					}

				}
				System.out.println("Total ställning: "+ userName +" "+ playerTotalScore +
						" Datorn score "+ computerScore + " Tie " + tie);
				computerScore=0;
				playerTotalScore=0;
				tie=0;
			}catch (IOException e) {
				System.out.println("Score files on process! Play another time");
				System.exit(0);
			}

		}

	}

}







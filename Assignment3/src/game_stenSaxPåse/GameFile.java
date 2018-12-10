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
		private StringBuilder sb = new StringBuilder();
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

			final Path filePath = Paths.get("C:/eclipse/score.txt");
			final Charset charset = Charset.forName("ISO-8859-1");
			try {
				List<String> lines = Files.readAllLines(filePath, charset);
				if(lines.isEmpty()) {
					System.out.println(this.messageOfFileReader(userName));

				}
				else {
					boolean isItFirstLoading = true;
					boolean isItLastreading = false;

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
				if(isItFirstLoading) {
					System.out.println(this.messageOfFileReader(userName).substring(76));
					isItFirstLoading =false;
					tie=playerTotalScore=computerScore=0;
					}
				else if(isItLastreading) {
					System.out.println(this.messageOfFileReader(userName).substring(76));
					isItFirstLoading =false;
					}
				isItFirstLoading= true;
				}
			}catch (IOException e) {
				System.out.println("Score files on process! Play another time");
				System.exit(0);
			}

		}
		StringBuilder messageOfFileReader(String name) {
			this.sb.append("\nDu är första spelare som ska spela med mig. Det finns ingen fusk!\nSå kolla Total ställning: ");
			sb.append(name).append("(").append(this.getPlayerTotalScore()).append(") VS Datorn (");
			sb.append(this.getComputerScore()).append(") and Tie(").append(this.getTie()).append(")");
			//sb.append("\nSom du ser vi är jämna just nu....\n");
			return sb;
		}

	}

}







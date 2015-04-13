/****

	Player.java

 ****/
 
 public class Player
 {
 	String username;
 	int wins, losses, ties, score;
 
 	Player(String username)
	{
 		this.username = username;
 		this.wins = 0;
 		this.losses = 0;
 		this.ties = 0;
 		this.score = 0;
 	}
 
 	Player(String username, int wins, int losses, int ties)
	{
 		this.username = username;
 		this.wins = wins;
 		this.losses = losses;
 		this.ties = ties;
 		this.score = 0;
 	}
 
 	public boolean makeMove(Location loc){
 	
 		if(false){	/* Add code for Location */
 			
 			
 			return true;
 		}
 		else
 			return false;
 	}
 	
 	public String getUsername(){
 		return username;
 	}
 	
 	public int getWins(){
 		return wins;
 	}
 	
 	public int getLosses(){
 		return losses;
 	}
 	
 	public int getTies(){
 		return ties;
 	}
 	
 	public int getScore(){
 		return score;
 	}
 	
 	public void increaseWins(){
 		wins += 1;
 	}
 	
 	public void increaseLosses(){
 		losses += 1;
 	}
 	
 	public void increaseTies(){
 		ties += 1;
 	}
 	
 	public void setWins(int wins){
 		this.wins = wins;
 	}
 	
 	public void setLosses(int losses){
 		this.losses = losses;
 	}
 	
 	public void setTies(int ties){
 		this.ties = ties;
 	}
 	
 	public void setScore(int score){
 		this.score = score;
 	}
 	
 	public void clearScore(){
 		score = 0;
 	}	
 }
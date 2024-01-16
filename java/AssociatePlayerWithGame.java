/*
 * Created on Apr 11, 2003
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package newuniv;
import java.io.Serializable;
/**
 * @author JSIRAGHER
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class AssociatePlayerWithGame implements Serializable {

	String 	Player; // playeraccount:UID
	int 	GameID; // GameDefinition:GameID
	String	Status; // player; admin



	public String stmtCreateTable =
		"CREATE TABLE AssociatePlayerWithGame "
			+ " (Player VARCHAR(24) NOT NULL ,"
			+ " GameID INTEGER," 
			+ " Status VARCHAR(24) NOT NULL )" ;



	/**
	 * 
	 */
	public AssociatePlayerWithGame() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return int
	 */
	public int getGameID() {
		return GameID;
	}

	/**
	 * @return String
	 */
	public String getPlayer() {
		return Player;
	}

	/**
	 * @return String
	 */
	public String getStatus() {
		return Status;
	}

	/**
	 * @return String
	 */
	public String getStmtCreateTable() {
		return stmtCreateTable;
	}

	/**
	 * Sets the gameID.
	 * @param gameID The gameID to set
	 */
	public void setGameID(int gameID) {
		GameID = gameID;
	}

	/**
	 * Sets the player.
	 * @param player The player to set
	 */
	public void setPlayer(String player) {
		Player = player;
	}

	/**
	 * Sets the status.
	 * @param status The status to set
	 */
	public void setStatus(String status) {
		Status = status;
	}

	/**
	 * Sets the stmtCreateTable.
	 * @param stmtCreateTable The stmtCreateTable to set
	 */
	public void setStmtCreateTable(String stmtCreateTable) {
		this.stmtCreateTable = stmtCreateTable;
	}

}

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
public class GameDefinition implements Serializable {


	int GameID;
	String GameName;
	String GameType;
	String GamePassword;
	int MaxPlayers;
	int StartTech;
	int MaxTech;
	
	

	public String stmtCreateTable =
		"CREATE TABLE GameDefinition " 
			+ "( GameID INTEGER,"
			+ " GameType VARCHAR(8) NOT NULL ,"
			+ " GamePassword VARCHAR(8) NOT NULL ,"
			+ " MaxPlayers INTEGER,"
			+ " StartTech INTEGER,"
			+ " MaxTech INTEGER )";	
	
	/**
	 * 
	 */
	public GameDefinition() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Insert the method's description here.
	 * Creation date: (11/21/2001 2:06:05 PM)
	 */
	public void print() {

		System.out.println(
			" GameID:"
				+ GameID
				+ " GameName:"
				+ GameName
				+ " GameType:"
				+ GameType
				+ " GamePassword:"
				+ GamePassword
				+ " MaxPlayers:"
				+ MaxPlayers
				+ " StartTech:"
				+ StartTech
				+ " MaxTech:"
				+ MaxTech
				
				);

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
	public String getGameName() {
		return GameName;
	}

	/**
	 * @return String
	 */
	public String getGamePassword() {
		return GamePassword;
	}

	/**
	 * @return String
	 */
	public String getGameType() {
		return GameType;
	}

	/**
	 * @return int
	 */
	public int getMaxPlayers() {
		return MaxPlayers;
	}

	/**
	 * @return int
	 */
	public int getMaxTech() {
		return MaxTech;
	}

	/**
	 * @return int
	 */
	public int getStartTech() {
		return StartTech;
	}

	/**
	 * Sets the gameID.
	 * @param gameID The gameID to set
	 */
	public void setGameID(int gameID) {
		GameID = gameID;
	}

	/**
	 * Sets the gameName.
	 * @param gameName The gameName to set
	 */
	public void setGameName(String gameName) {
		GameName = gameName;
	}

	/**
	 * Sets the gamePassword.
	 * @param gamePassword The gamePassword to set
	 */
	public void setGamePassword(String gamePassword) {
		GamePassword = gamePassword;
	}

	/**
	 * Sets the gameType.
	 * @param gameType The gameType to set
	 */
	public void setGameType(String gameType) {
		GameType = gameType;
	}

	/**
	 * Sets the maxPlayers.
	 * @param maxPlayers The maxPlayers to set
	 */
	public void setMaxPlayers(int maxPlayers) {
		MaxPlayers = maxPlayers;
	}

	/**
	 * Sets the maxTech.
	 * @param maxTech The maxTech to set
	 */
	public void setMaxTech(int maxTech) {
		MaxTech = maxTech;
	}

	/**
	 * Sets the startTech.
	 * @param startTech The startTech to set
	 */
	public void setStartTech(int startTech) {
		StartTech = startTech;
	}

}

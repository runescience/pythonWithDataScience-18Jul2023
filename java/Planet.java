/*
 * Created on Apr 10, 2003
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package newuniv;


/**
 * @author JSIRAGHER
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class Planet implements java.io.Serializable {

	String Itemtag;
	String Owner;
	String GameID;
	String Name; //planetname
	int Size;
	int Atmosphere;
	String NativeRace;
	String Oretype;
	Boolean isCaptured;

	String stmtCreateTable =
		"CREATE TABLE Planet "
			+ " (ItemTag VARCHAR(16) NOT NULL ,"
			+ " Owner VARCHAR(16) NOT NULL ,"
			+ " GameID VARCHAR(12) NOT NULL ,"
			+ " Name VARCHAR(16) NOT NULL ,"
			+ " Size Integer NOT NULL, "
			+ " Atmosphere Integer NOT NULL, "
			+ " NativeRace VARCHAR(4) NOT NULL ,"
			+ " Oretype VARCHAR(4) NOT NULL ,"
			+ " Bonus VARCHAR(24) NOT NULL ," 			+ " isCaptured Integer NOT NULL) ";

	/**
	 * 
	 */
	public Planet() {
		super();
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
	}

	/**
	 * @return int
	 */
	public int getAtmosphere() {
		return Atmosphere;
	}

	/**
	 * @return String
	 */
	public String getGameID() {
		return GameID.toUpperCase();
	}

	/**
	 * @return Boolean
	 */
	public Boolean getIsCaptured() {
		return isCaptured;
	}

	/**
	 * @return String
	 */
	public String getItemtag() {
		return Itemtag.toUpperCase();
	}

	/**
	 * @return String
	 */
	public String getName() {
		return Name;
	}

	/**
	 * @return String
	 */
	public String getNativeRace() {
		return NativeRace;
	}

	/**
	 * @return String
	 */
	public String getOretype() {
		return Oretype;
	}

	/**
	 * @return String
	 */
	public String getOwner() {
		return Owner.toUpperCase();
	}

	/**
	 * @return int
	 */
	public int getSize() {
		return Size;
	}

	/**
	 * Sets the atmosphere.
	 * @param atmosphere The atmosphere to set
	 */
	public void setAtmosphere(int atmosphere) {
		Atmosphere = atmosphere;
	}

	/**
	 * Sets the gameID.
	 * @param gameID The gameID to set
	 */
	public void setGameID(String gameID) {
		GameID = gameID;
	}

	/**
	 * Sets the isCaptured.
	 * @param isCaptured The isCaptured to set
	 */
	public void setIsCaptured(Boolean isCaptured) {
		this.isCaptured = isCaptured;
	}

	/**
	 * Sets the itemtag.
	 * @param itemtag The itemtag to set
	 */
	public void setItemtag(String itemtag) {
		Itemtag = itemtag;
	}

	/**
	 * Sets the name.
	 * @param name The name to set
	 */
	public void setName(String name) {
		Name = name;
	}

	/**
	 * Sets the nativeRace.
	 * @param nativeRace The nativeRace to set
	 */
	public void setNativeRace(String nativeRace) {
		NativeRace = nativeRace;
	}

	/**
	 * Sets the oretype.
	 * @param oretype The oretype to set
	 */
	public void setOretype(String oretype) {
		Oretype = oretype;
	}

	/**
	 * Sets the owner.
	 * @param owner The owner to set
	 */
	public void setOwner(String owner) {
		Owner = owner;
	}

	/**
	 * Sets the size.
	 * @param size The size to set
	 */
	public void setSize(int size) {
		Size = size;
	}

}

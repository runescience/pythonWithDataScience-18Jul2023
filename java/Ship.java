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
public class Ship implements Serializable {

	String 	Owner; 	// SHIP0034
	String 	Shipname;	// blackbird
	String 	Race;		// human
	String 	Alliances;	// stored as tilde(^) separated. yano^blackflight^sperrywil
	double Energy;		// stored in a status item.
	int 	Funds;		// gold will be stored in a status item 



	String stmtCreateTable =
		"CREATE TABLE Ship "
			+ " (Owner VARCHAR(16) NOT NULL ,"
			+ " Shipname VARCHAR(16) NOT NULL ,"
			+ " Race VARCHAR(12) NOT NULL ,"
			+ " Alliances VARCHAR(16) NOT NULL ,"
			+ " Energy float NOT NULL ,"
			+ " Funds Integer NOT NULL) ";


	public void print() {
	
		System.out.println(
			" Playername:"
				+ " Owner:"
				+ Owner 
				+ " Shipname:"
				+ Shipname
				+ " Race:"
				+ Race
				+ " Alliances:"
				+ Alliances
				+ " Funds:"
				+ Funds

				+ " Energy:"
				+ Energy);
	
	}
	/**
	 * @return String
	 */
	public String getAlliances() {
		return Alliances;
	}

	/**
	 * @return double
	 */
	public double getEnergy() {
		return Energy;
	}

	/**
	 * @return int
	 */
	public int getFunds() {
		return Funds;
	}

	/**
	 * @return String
	 */
	public String getOwner() {
		return Owner;
	}

	/**
	 * @return String
	 */
	public String getRace() {
		return Race;
	}

	/**
	 * @return String
	 */
	public String getShipname() {
		return Shipname;
	}

	/**
	 * Sets the alliances.
	 * @param alliances The alliances to set
	 */
	public void setAlliances(String alliances) {
		Alliances = alliances;
	}

	/**
	 * Sets the energy.
	 * @param energy The energy to set
	 */
	public void setEnergy(double energy) {
		Energy = energy;
	}

	/**
	 * Sets the funds.
	 * @param funds The funds to set
	 */
	public void setFunds(int funds) {
		Funds = funds;
	}

	/**
	 * Sets the owner.
	 * @param owner The owner to set
	 */
	public void setOwner(String owner) {
		Owner = owner;
	}

	/**
	 * Sets the race.
	 * @param race The race to set
	 */
	public void setRace(String race) {
		Race = race;
	}

	/**
	 * Sets the shipname.
	 * @param shipname The shipname to set
	 */
	public void setShipname(String shipname) {
		Shipname = shipname;
	}

}

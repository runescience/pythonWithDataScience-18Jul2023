
package newuniv;

import java.io.Serializable;

public class WormHole implements Serializable {

	String Owner;
	String Password;
	String OneWay;
	String PortalID;
	String PortalTo;

	String stmtCreateTable =
		"CREATE TABLE Wormhole "
			+ " (Owner VARCHAR(16) NOT NULL ,"
			+ " TaskForceTag VARCHAR(16) NOT NULL ,"
			+ " Password VARCHAR(12) NOT NULL ,"
			+ " PortalID VARCHAR(16) NOT NULL ,"
			+ " PortalTo VARCHAR(12) NOT NULL ) ";

	/**
	 * 
	 */
	public WormHole() {

			// TODO Auto-generated constructor stub
	}

	public void print() {

		System.out.println(
			"Owner:"
				+ Owner
				+ " Password:"
				+ Password
				+ " OneWay:"
				+ OneWay
				+ " PortalID:"
				+ PortalID
				+ " PortalTo:"
				+ PortalTo);
	}

	WormHole(
		String owner,
		String pwd,
		String oneway,
		String name,
		String destination) {

		WormHole wh = null;

		wh.Owner = owner;
		wh.Password = pwd;
		wh.OneWay = oneway;
		wh.PortalID = name;

		if (destination.length() == 0 || destination == null)
			wh.PortalTo = "";
		else
			wh.PortalTo = destination;

	}

	/**
	 * @return String
	 */
	public String getOneWay() {
		return OneWay;
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
	public String getPassword() {
		return Password;
	}

	/**
	 * @return String
	 */
	public String getPortalID() {
		return PortalID;
	}

	/**
	 * @return String
	 */
	public String getPortalTo() {
		return PortalTo;
	}

	/**
	 * Sets the oneWay.
	 * @param oneWay The oneWay to set
	 */
	public void setOneWay(String oneWay) {
		OneWay = oneWay;
	}

	/**
	 * Sets the owner.
	 * @param owner The owner to set
	 */
	public void setOwner(String owner) {
		Owner = owner;
	}

	/**
	 * Sets the password.
	 * @param password The password to set
	 */
	public void setPassword(String password) {
		Password = password;
	}

	/**
	 * Sets the portalID.
	 * @param portalID The portalID to set
	 */
	public void setPortalID(String portalID) {
		PortalID = portalID;
	}

	/**
	 * Sets the portalTo.
	 * @param portalTo The portalTo to set
	 */
	public void setPortalTo(String portalTo) {
		PortalTo = portalTo;
	}

}
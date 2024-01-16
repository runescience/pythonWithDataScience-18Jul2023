// This program uses TextFields to get information from the
// user at the keyboard and writes the information to a
// sequential file.
package newuniv;
import java.io.Serializable;

public class Cache implements Serializable {
	// Connect print stream to the output stream

	//static DataOutputStream output; // enables output of data to file
	//static DataInputStream input; // file from which data is read

	/**
	 * 
	 */
	public Cache() {

		// TODO Auto-generated constructor stub
	}
	
	private String Owner;
	private String TaskForceTag; // aka  ItemTag, in this case "CACH0009"
	private String Shortname;
	private int Quantity;


	public String stmtCreateTable =
		"CREATE TABLE Cache "
			+ " (Owner VARCHAR(24) NOT NULL ,"
			+ " TaskForceTag VARCHAR(24) NOT NULL ,"
			+ " Shortname INTEGER,"
			+ " Quantity INTEGER )";




	/**
	 * @param Owner
	 * @param TaskForceTag
	 * @param Shortname
	 * @param Quantity
	 */
	/* **********************************************
	
	Owner		TaskForceTag	Shortname	Quantity
	SHIP0003,	TASK0014,		PE,			100
	SHIP0003,	TASK0014,		GO,			1000
	SHIP0003,	TASK0014,		WE,			20
	
	
	
	************************************************ */

	public Cache(
		String Owner,
		String TaskForceTag,
		String Shortname,
		int Quantity) {
		setOwner(Owner);
		setTaskForceTag(TaskForceTag);
		setShortname(Shortname);
		setQuantity(Quantity);
	}
	public void print() {

		//unit.print();
		System.out.println(
			" Owner:"
				+ getOwner()
				+ " TaskForceTag:"
				+ getTaskForceTag()
				+ " Shortname:"
				+ getShortname()
				+ " Quantity:"
				+ getQuantity());

	}
	public void setQuantity(int quantity) {
		Quantity = quantity;
	}

	int getQuantity() {
		return Quantity;
	}

	public void setOwner(String owner) {
		Owner = owner;
	}

	public String getOwner() {
		return Owner;
	}

	public void setShortname(String shortname) {
		Shortname = shortname;
	}

	/**
	 * @return String
	 */
	String getShortname() {
		return Shortname;
	}

	public void setTaskForceTag(String taskForceTag) {
		TaskForceTag = taskForceTag;
	}

	String getTaskForceTag() {
		return TaskForceTag;
	}

}

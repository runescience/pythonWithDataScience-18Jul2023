package newuniv;

import java.io.Serializable;

/** 
*
*
*
********************/
public class Status implements Serializable{


	String Owner;
	String TaskForceTag;
	String Shortname;
	
	int Quantity;
	int Damage;
	int Active;

	/* **************************************************

	100,	0,	1,	SHIP0003,	TASK1234,	GO,
	200,	0,	1,	SHIP0003,	TASK1234,	WE,
	100,	0,	1,	SHIP0003,	TASK1234,	CA,
	
	************************************************* */	
	
	/** 
	*
	*
	*
	********************/
	//  constructor
	public Status() {
		//super( "Create Status File" );
		System.out.println("Status() constructor");
		//vList = new Vector();
		//this.readStatusSeqData();
	}
	/** 
	*
	*
	*
	********************/
	public Status(
		int quan,
		int Damage,
		int Active,
		String TaskForceTag,
		String sname) {

		System.out.println("Status(int quan, int Damage, int Active, String TargetForce, string shrtname)");
		this.Quantity = quan;
		this.Damage = Damage;
		this.Active = Active;
		this.Owner = TaskForceTag;
		this.TaskForceTag = TaskForceTag;
		this.Shortname = sname;

	}
	/** 
	*
	*
	*
	********************/
	public Status( String Owner,
					String TaskForceTag,
					String sname,
					int quan,
					int Damage,
					int Active) {
			
			
		System.out.println("Status(int, int, int, String, string)");
		this.Quantity = quan;
		this.Damage = Damage;
		this.Active = Active;
		this.Owner = Owner;
		this.TaskForceTag = TaskForceTag;
		this.Shortname = sname;
	}

	/** 
	*
	*
	*
	********************/
	public Status(Status inp) {
		// copy contructor
		//System.out.println("Status(inp)");
		this.Quantity = inp.Quantity;
		this.Damage = inp.Damage;
		this.Active = inp.Active;
		this.Owner = inp.Owner;
		this.TaskForceTag = inp.TaskForceTag;
		this.Shortname = inp.Shortname;
	}
/** 
*
*
*
********************/
public void print() {
	//System.out.print("printStatus()status:");
	if (this == null)
		return;
	System.out.println(
		" Quantity:"
			+ this.Quantity
			+ " Damage:"
			+ this.Damage
			+ " Active:"
			+ this.Active
			+ " Owner:"
			+ this.Owner
			+ " TaskForceTag:"
			+ this.TaskForceTag
			+ " Shortname:"
			+ this.Shortname);

}
	/**
	 * @return
	 */
	public int getActive() {
		return Active;
	}

	/**
	 * @return
	 */
	public int getDamage() {
		return Damage;
	}

	/**
	 * @return
	 */
	public String getOwner() {
		return Owner;
	}

	/**
	 * @return
	 */
	public int getQuantity() {
		return Quantity;
	}

	/**
	 * @return
	 */
	public String getShortname() {
		return Shortname;
	}

	/**
	 * @return
	 */
	public String getTaskForceTag() {
		return TaskForceTag;
	}

	/**
	 * @param i
	 */
	public void setActive(int i) {
		Active = i;
	}

	/**
	 * @param i
	 */
	public void setDamage(int i) {
		Damage = i;
	}

	/**
	 * @param string
	 */
	public void setOwner(String string) {
		Owner = string;
	}

	/**
	 * @param i
	 */
	public void setQuantity(int i) {
		Quantity = i;
	}

	/**
	 * @param string
	 */
	public void setShortname(String string) {
		Shortname = string;
	}

	/**
	 * @param string
	 */
	public void setTaskForceTag(String string) {
		TaskForceTag = string;
	}

}
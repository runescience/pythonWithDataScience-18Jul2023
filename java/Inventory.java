// This program uses TextFields to get information from the
// user at the keyboard and writes the information to a
// sequential file.
package newuniv;
import java.io.Serializable;

public class Inventory implements Serializable {

	/**
		 * 
		 */
	public Inventory() {

		// TODO Auto-generated constructor stub
	}

	int availableSpaces;
	int Cost;
	int Energy;
	int Tech;
	int Advancedgame;
	int Max;
	int Startquan;
	String Name;
	String Abbrev;
	String Comment;
	String Group;
	String Bonus;

	String stmtCreateTable =
	   "CREATE TABLE Inventory "
		   + " (SpacesAvail INTEGER NOT NULL ,"
		   + " Cost Integer NOT NULL, "
		   + " Energy Integer NOT NULL, "
		   + " Tech Integer NOT NULL, "
		   + " Advancedgame Integer NOT NULL, "
		   + " Max Integer NOT NULL, "
		   + " Startquan Integer NOT NULL, "
		   + " Name VARCHAR(16) NOT NULL ,"
		   + " Abbrev VARCHAR(24) NOT NULL ,"
		   + " Comment VARCHAR(16) NOT NULL ,"
		   + " GroupTag VARCHAR(4) NOT NULL ,"
		   + " Bonus VARCHAR(24) ) ";


	// This class is for the fantasy game. 
	// The PartsCatalogue class is for the Scifi game.

	/************************************
	
	
	//SZ	COST	ENERGY	TECHLV	ADVGAM	MAX		STARTQN	LNGNAME		SHRTNM	COMMENT
	1,		1,		0,		1,		0,		65000,	25,		Food,  		fo,		consumed 1 gold/25peeps/turn
	1,		2,		0,		1,		0,		65000,	25, 	Weapon,		we,		peasants and troops
	1,	3,	0,	1,	0,	65000,	20, 	Cattle,		ca,	1 cattle yields 5 food.
	1,	1,	0,	1,	0,	65000,	100, 	Peasants,	pe,	peasants
	1,	1,	0,	1,	0,	65000,	100, 	Gold,		go,	Gold
	1,	5,	0,	1,	0,	65000,	0, 	Mage,		ma,	Mage
	1,	3,	0,	1,	0,	65000,	0, 	Alchemist,	al,	Alchemist
	
	
	END
	this.Size = Integer.parseInt(cmd.nextToken().trim()); //sz
	this.Cost = Integer.parseInt(cmd.nextToken().trim()); //co            
	this.Energy = Double.parseDouble(cmd.nextToken().trim()); //en
	this.TechLevel = Integer.parseInt(cmd.nextToken().trim()); //tc
	this.AdvGameInd = Integer.parseInt(cmd.nextToken().trim()); //ad
	this.Max = Integer.parseInt(cmd.nextToken().trim()); //max
	this.StartQuan = Integer.parseInt(cmd.nextToken().trim()); //st
	this.Longname = cmd.nextToken().trim(); //shortname
	this.Shortname = cmd.nextToken().trim(); //longname
	this.Comment = cmd.nextToken().trim(); //comments
	
	
	
	//NAME		ABR	 GROUP		COST BONUS MAX STARTQUAN
	
	ALCHEMIST,   AL, SPECIALIST,  0,   0,   0,   0
	ASSASSIN,    AS, SPECIALIST,  0,   0,   0,   0
	BATTLESPUR,  BA, NONE,        10,  0,   0,   0
	CATTLE,      CT, NONE,        10,  0,   0,   15   
	CHOCOLATE,   CH, NONE,        0,   0,   0,   0
	CLAIRVPOTION,CL, NONE,        10,  0,   0,   0
	COAL,        C0, NONE,        0,   0,   0,   0
	COFFEE,      CF, NONE,        0,   0,   0,   0
	COPPER,      CP, NONE,        0,   0,   0,   0
	DAGIS,       DG, NONE,        0,   0,   0,   0
	FOOD,        FO, NONE,        0,   0,   0,   5
	GINSENG,     GI, NONE,        0,   0,   0,   0
	GOLD,        GO, NONE,        1,   0,   0,   0
	HUNTER,      HU, SPECIALIST,  0,   0,   0,   0
	IRON,        IR, NONE,        0,   0,   0,   0
	INVISPOTION, IN, NONE,        10,  0,   0,   0
	LEADER,      LE, NONE,        0,   0,   0,   0
	MAGE,        MG, SPECIALIST,  0,   0,   0,   0
	MANCRYSTALS, MA, NONE,        0,   0,   0,   0
	MINER,       MI, SPECIALIST,  0,   0,   0,   0
	NONE,        NO, NONE,        0,   0,   0,   0
	PARSELY,     PA, NONE,        0,   0,   0,   0
	PEASANT,     PE, NONE,        0,   0,   0,   0
	PELT,        PT, NONE,        0,   0,   0,   0
	PRIEST,      PR, SPECIALIST,  0,   0,   0,   0
	SPICES,      SP, NONE,        0,   0,   0,   0
	SIEGEENGINE, SE, NONE,        0,   0,   0,   0
	SHIELDS,     SH, NONE,        0,   0,   0,   0
	SULPHUR,     SU, NONE,        0,   0,   0,   0
	THEIF,       TH, SPECIALIST,  0,   0,   0,   0
	VILLAGE,     VI, NONE,        0,   0,   0,   0
	WALLS,       WA, NONE,        0,   0,   0,   0
	WARRIOR,     WA, NONE,        0,   0,   0,   0
	WEAPONS,     WE, NONE,        0,   0,   0,   0
	WEAPONSMITH, WS, SPECIALIST,  0,   0,   0,   0
	
	**********************************/
	// constructor
	public Inventory(
		int Size,
		int Cost,
		int Energy,
		int Tech,
		int Advancedgame,
		int Max,
		int Startquan,
		String Name,
		String Abbrev,
		String Comment,
		String Group,
		String Bonus) {
		System.out.println("Inventory(): copy constructor");

		this.availableSpaces = Size;
		this.Cost = Cost;
		this.Energy = Energy;
		this.Tech = Tech;
		this.Advancedgame = Advancedgame;
		this.Max = Max;
		this.Startquan = Startquan;
		this.Name = Name;
		this.Abbrev = Abbrev;
		this.Comment = Comment;
		this.Group = Group;
		this.Bonus = Bonus;

	}

	// constructor
	public Inventory(
		int Size,
		int Cost,
		int Energy,
		int Tech,
		int Advancedgame,
		int Max,
		int Startquan,
		String Name,
		String Abbrev,
		String Comment) {
		System.out.println("Inventory(): copy constructor");

		this.availableSpaces = Size;
		this.Cost = Cost;
		this.Energy = Energy;
		this.Tech = Tech;
		this.Advancedgame = Advancedgame;
		this.Max = Max;
		this.Startquan = Startquan;
		this.Name = Name;
		this.Abbrev = Abbrev;
		this.Comment = Comment;

	}
	// copy contructor
	public Inventory(Inventory inp) {
		System.out.println("Inventory(): copy constructor");

		this.availableSpaces = inp.availableSpaces;
		this.Cost = inp.Cost;
		this.Energy = inp.Energy;
		this.Tech = inp.Tech;
		this.Advancedgame = inp.Advancedgame;
		this.Max = inp.Max;
		this.Startquan = inp.Startquan;
		this.Name = inp.Name;
		this.Abbrev = inp.Abbrev;
		this.Comment = inp.Comment;
		this.Group = inp.Group;
		this.Bonus = inp.Bonus;

	}

	public void print() {

		if (this == null) {
			System.out.println("print(): null occurance");

		} else
			System.out.println(
				"Size:"
					+ this.availableSpaces
					+ "Cost:"
					+ this.Cost
					+ "Energy:"
					+ this.Energy
					+ "Tech:"
					+ this.Tech
					+ "Advancedgame:"
					+ this.Advancedgame
					+ "Max:"
					+ this.Max
					+ "Startquan:"
					+ this.Startquan
					+ "Name:"
					+ this.Name
					+ "Abbrev:"
					+ this.Abbrev
					+ "Comment:"
					+ this.Comment
					+ "Group:"
					+ this.Group
					+ "Bonus:"
					+ this.Bonus);
	}

	/**
	 * @return
	 */
	public String getAbbrev() {
		return Abbrev;
	}

	/**
	 * @return
	 */
	public int getAdvancedgame() {
		return Advancedgame;
	}

	/**
	 * @return
	 */
	public String getBonus() {
		return Bonus;
	}

	/**
	 * @return
	 */
	public String getComment() {
		return Comment;
	}

	/**
	 * @return
	 */
	public int getCost() {
		return Cost;
	}

	/**
	 * @return
	 */
	public int getEnergy() {
		return Energy;
	}

	/**
	 * @return
	 */
	public String getGroup() {
		return Group;
	}

	/**
	 * @return
	 */
	public int getMax() {
		return Max;
	}

	/**
	 * @return
	 */
	public String getName() {
		return Name;
	}

	/**
	 * @return
	 */
	public int getSize() {
		return availableSpaces;
	}

	/**
	 * @return
	 */
	public int getStartquan() {
		return Startquan;
	}

	/**
	 * @return
	 */
	public int getTech() {
		return Tech;
	}

	/**
	 * @param string
	 */
	public void setAbbrev(String string) {
		Abbrev = string;
	}

	/**
	 * @param i
	 */
	public void setAdvancedgame(int i) {
		Advancedgame = i;
	}

	/**
	 * @param string
	 */
	public void setBonus(String string) {
		Bonus = string;
	}

	/**
	 * @param string
	 */
	public void setComment(String string) {
		Comment = string;
	}

	/**
	 * @param i
	 */
	public void setCost(int i) {
		Cost = i;
	}

	/**
	 * @param i
	 */
	public void setEnergy(int i) {
		Energy = i;
	}

	/**
	 * @param string
	 */
	public void setGroup(String string) {
		Group = string;
	}

	/**
	 * @param i
	 */
	public void setMax(int i) {
		Max = i;
	}

	/**
	 * @param string
	 */
	public void setName(String string) {
		Name = string;
	}

	/**
	 * @param i
	 */
	public void setSize(int i) {
		availableSpaces = i;
	}
	public void setAvailableSpaces(int i) {
		availableSpaces = i;
	}

	/**
	 * @param i
	 */
	public void setStartquan(int i) {
		Startquan = i;
	}

	/**
	 * @param i
	 */
	public void setTech(int i) {
		Tech = i;
	}

	/**
	 * @return int
	 */
	public int getAvailableSpaces() {
		return availableSpaces;
	}

	/**
	 * @return String
	 */
	public String getStmtCreateTable() {
		return stmtCreateTable;
	}

	/**
	 * Sets the stmtCreateTable.
	 * @param stmtCreateTable The stmtCreateTable to set
	 */
	public void setStmtCreateTable(String stmtCreateTable) {
		this.stmtCreateTable = stmtCreateTable;
	}

}

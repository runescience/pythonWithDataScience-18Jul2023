// This program uses TextFields to get information from the
// user at the keyboard and writes the information to a
// sequential file.
package newuniv.rules;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.StringTokenizer;

import newuniv.Inventory;
import newuniv.Utils;

public class InventoryImpl extends Utils {

	transient static String sFilename = null;
	transient static java.util.Vector vList = new java.util.Vector();

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
	
	
	//SZ	COST	ENERGY	TECHLV	ADVGAM	MAX	STARTQN	LNGNAME		SHRTNM	COMMENT
	1,	1,	0,	1,	0,	65000,	25,	Food,  		fo,	consumed 1 gold/25peeps/turn
	1,	2,	0,	1,	0,	65000,	25, 	Weapon,		we,	peasants and troops
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

	//  constructor
	public InventoryImpl(String sFilename) {
		super(vList, sFilename);
		System.out.println("Constructor InventoryImpl(sFilename);");
		readSeqData(sFilename);
	}
	public int getCostFromName(String sName) {
		for (int x = 0; x < vList.size(); x++) {
			Inventory c = (Inventory) vList.elementAt(x);
			if (c.getName().equals(sName)) {
				return c.getCost();
			}
		}
		return 0;
	}
	public Inventory getInventoryImplFromName(String sName) {

		for (int x = 0; x < vList.size(); x++) {
			Inventory c = (Inventory) vList.elementAt(x);
			if (c.getName().equals(sName)) {
				return c;
			}
		}
		System.out.println(sName + " not an index into InventoryImplList.");
		return null;
	}
	public int getMaxFromName(String sName) {
		for (int x = 0; x < vList.size(); x++) {
			Inventory c = (Inventory) vList.elementAt(x);
			if (c.getName().equals(sName)) {
				return c.getMax();
			}
		}
		return 0;
	}
	public int ListAllOnScreen() {

		System.out.println("ListAllOnScreen size of list:" + vList.size());

		for (int xx = 0; xx < vList.size(); xx++) {
			Inventory ci = (Inventory) vList.elementAt(xx);
			ci.print();
		}

		return 0;
	}
	public static void main(String args[]) {

		InventoryImpl ci = new InventoryImpl("inventory.txt");

		int iCreate = 0;

		if (iCreate == 1) {
			Inventory ci1 =
				new Inventory(
					1,
					1,
					0,
					1,
					0,
					65000,
					25,
					"Food",
					"fo",
					"consumed 1 gold/25peeps/turn");

			// sets up internal list
			vList.add(ci1);
			Inventory ci2 =
				new Inventory(
					1,
					2,
					0,
					1,
					0,
					65000,
					25,
					"Weapon",
					"we",
					"peasants and troops");

			// sets up internal list
			vList.add(ci2);

			//ci.readUniverseFileIntoVector();

			System.out.println("==================");
			System.out.println("Un-serializing");

			ci.SerializeOut();
			//vList.clear();
			//ci1 = null;
		}

		ci.SerializeIn();

		ci.ListAllOnScreen();

		System.out.println("==================");
		System.out.println("closing");

		System.exit(17);
	}

	void readSeqData(String sFileName) {

		File fProp;

		String sFolderPropFileName = "";

		if (sFolderPropFileName != null)
			fProp = new File(sFileName);
		else {
			return;

		}

		System.out.println("trying to open " + System.getProperty("user.dir")+"\\" +sFileName );		
		

		if (fProp.exists() == false) {
			String sMessage =
				new Date().toString()
					+ "   "
					+ "Problem opening "+ sFileName +" in " + System.getProperty("user.dir") ;
			System.out.println(sMessage);
			System.exit(0);

		} else
			try {

				BufferedReader in =
					new BufferedReader(new FileReader(sFileName));

				// Continue to read lines while there are still some left to read
				for (;;) {

					try {

						// Print file line to screen
						String line;

						// Print file line to screen
						line = in.readLine();

						if (line == null || line.equals("END")) {
							//System.err.println("End of Batch file ");
							break;
						}

						//System.out.println ("\nread in the line:"+line);
						if (line.length() < 3)
							continue;

						line = line.toUpperCase();
						StringTokenizer cmd = new StringTokenizer(line, ",");

						Inventory inv = new Inventory();
						
						
						inv.setSize(Integer.parseInt(cmd.nextToken().trim()));
						inv.setCost(Integer.parseInt(cmd.nextToken().trim()));
						inv.setEnergy(Integer.parseInt(cmd.nextToken().trim()));
						inv.setTech(Integer.parseInt(cmd.nextToken().trim()));
						inv.setAdvancedgame(Integer.parseInt(cmd.nextToken().trim()));
						inv.setMax(Integer.parseInt(cmd.nextToken().trim()));
						inv.setStartquan(Integer.parseInt(cmd.nextToken().trim()));
						inv.setName(cmd.nextToken().trim());
						inv.setAbbrev(cmd.nextToken().trim());
						inv.setComment(cmd.nextToken().trim());
						
						//inv.setGroup(cmd.nextToken().trim());
						//inv.setBonus(cmd.nextToken().trim());

						//System.err.println("===>> line="+line);
						vList.addElement((Inventory) inv);

					} catch (Exception e) {
						System.err.println("Exception from try:" + e);
					}

				}

			} catch (IOException e) {
				System.err.println("IOException from try:" + e);
			}

	}

	/**
	 * @return String
	 */
	public String getStmtCreateTable() {
		return stmtCreateTable;
	}

}

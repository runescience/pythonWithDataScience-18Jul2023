package newuniv.rules;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.StringTokenizer;

import newuniv.Ship;
import newuniv.Utils;

public class ShipImpl extends Utils {

	transient static String sFilename = null;
	transient static java.util.Vector vList = new java.util.Vector();

	/*******************************************************************************
	***************************************************************/

	String stmtCreateTable =
		"CREATE TABLE Ship "
			+ " (Owner VARCHAR(16) NOT NULL ,"
			+ " Shipname VARCHAR(16) NOT NULL ,"
			+ " Race VARCHAR(12) NOT NULL ,"
			+ " Alliances VARCHAR(16) NOT NULL ,"
			+ " Energy float NOT NULL ,"
			+ " Funds Integer NOT NULL) ";

	ShipImpl(String sFilename) {

		super(vList, sFilename);

		setSFilename(sFilename);
		System.out.println("Constructor ShipImpl(sFilename);");
	}

	public int ListAllOnScreen() {
		System.out.println("Size of Race list:" + vList.size());

		for (int xx = 0; xx < vList.size(); xx++) {
			Ship pp = (Ship) vList.elementAt(xx);
			pp.print();
		}

		return 0;
	}

	public static void main(String args[]) {

		ShipImpl ci = new ShipImpl("d:\\jabber3\\Ship.dat");

		System.out.println("main");

		int iCreate = 0;

		if (iCreate == 1) {

			/*******************************************
			Hazard ci1 = new CombatInfo(3, 5, 3, 1);
			
			// sets up internal list
			vList.add(ci1);
			CombatInfo ci2 = new CombatInfo(3, 5, 3, 1);
			
			// sets up internal list
			vList.add(ci2);
			
			//ci.readUniverseFileIntoVector();
			
			System.out.println("==================");
			System.out.println("Un-serializing");
			
			ci.SerializeOut();
			//vList.clear();
			//ci1 = null;
			************************************/

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

						String setOwner; // SHIP0034
						String setShipname; // blackbird
						String setRace; // human
						String setAlliances;
						// stored as tilde(^) separated. yano^blackflight^sperrywil
						double setEnergy; // stored in a status item.
						int setFunds; // gold will be stored in a status item 

						Ship sh = new Ship();
						sh.setOwner(cmd.nextToken().trim()); //String
						sh.setShipname(cmd.nextToken().trim()); //String
						sh.setRace(cmd.nextToken().trim()); // String
						sh.setAlliances(cmd.nextToken().trim()); //String
						sh.setEnergy(
							Double.parseDouble(cmd.nextToken().trim()));
						sh.setFunds(Integer.parseInt(cmd.nextToken().trim()));

						//System.err.println("===>> line="+line);
						vList.addElement((Ship) sh);

					} catch (Exception e) {
						System.err.println("Exception from try:" + e);
					}

				}

			} catch (IOException e) {
				System.err.println("IOException from try:" + e);
			}

	} // end of readInventSeqData

	/**
	 * @return String
	 */
	public String getStmtCreateTable() {
		return stmtCreateTable;
	}

}
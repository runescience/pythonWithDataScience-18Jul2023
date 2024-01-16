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

import newuniv.Utils;
import newuniv.WormHole;

public class WormHoleImpl extends Utils {

	
	static int counter = 0;
	static String sFilename = null;
	static java.util.Vector vList = new java.util.Vector();
	
	/* **********************************************								
	String Owner;
	String Password;
	String OneWay;
	String PortalID;
	String PortalTo;
	
	Owner		Password	OneWay		PortalID		PortalTo
	SHIP0003,	KRAKEN,		0,			WORM0046		WORM0047
	SHIP0003,	WYRDSTORM,	1,			WORM0047		WORM0048
	SHIP0003,	ORKHORDE,	0,			WORM0048		WORM0046
	************************************************ */

	String stmtCreateTable =
		"CREATE TABLE Wormhole "
			+ " (Owner VARCHAR(16) NOT NULL ,"
			+ " TaskForceTag VARCHAR(16) NOT NULL ,"
			+ " Password VARCHAR(12) NOT NULL ,"
			+ " PortalID VARCHAR(16) NOT NULL ,"
			+ " PortalTo VARCHAR(12) NOT NULL ) ";


		WormHoleImpl(String sFilename) {
	
			super(vList, sFilename);

			System.out.println("Constructor WormHoleImpl(sFilename);");
		}
		
	public WormHole getWormHoleByOwner(String Owner) {

		for (int x = 0; x < vList.size(); x++) {
			WormHole c = (WormHole) vList.elementAt(x);
			if (c.getOwner().equals(Owner)) {
				return c;
			}
		}
		System.out.println(Owner + " doesnot in PlanetImpl.vList");
		return null;
	}		
		
	public WormHole getWormHoleExits(String Owner) {

		for (int x = 0; x < vList.size(); x++) {
			WormHole c = (WormHole) vList.elementAt(x);
			if (c.getOwner().equals(Owner)) {
				return c;
			}
		}
		System.out.println(Owner + " doesnot in PlanetImpl.vList");
		return null;
	}		
		
	
		/* *****************************************************
	
		SHIP0003,	PWD,	TWOWAY,		portal1, 	portal2
		SHIP0003,	PWD,	TWOWAY,		portal2,	portal1
	
		***************************************************** */
	
	
		public int ListAllOnScreen() {
			System.out.println("Size of Race list:" + vList.size());
	
			for (int xx = 0; xx < vList.size(); xx++) {
				WormHole pp = (WormHole) vList.elementAt(xx);
				pp.print();
			}
	
			return 0;
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
	
							WormHole wh = new WormHole();
							wh.setOwner ( cmd.nextToken().trim()); //comments
							wh.setPassword ( cmd.nextToken().trim()); //comments
							wh.setOneWay ( cmd.nextToken().trim()); //comments
							wh.setPortalID ( cmd.nextToken().trim()); //comments
							wh.setPortalTo ( cmd.nextToken().trim()); //comments
	
							//System.err.println("===>> line="+line));
							vList.addElement((WormHole) wh);
	
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


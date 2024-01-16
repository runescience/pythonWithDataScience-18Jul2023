// This program uses TextFields to get information from the
// user at the keyboard and writes the information to a
// sequential file.

//This could either be fantasy or ftc

package newuniv.rules;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.StringTokenizer;

import newuniv.Hazard;
import newuniv.Utils;

public class HazardImpl extends Utils {

	static String sFilename = null;
	static java.util.Vector vList = new java.util.Vector();

	public String stmtCreateTable =
		"CREATE TABLE Hazard "
			+ " (Owner VARCHAR(24) NOT NULL ,"
			+ " ItemTag VARCHAR(24) NOT NULL ,"
			+ " Longname VARCHAR(16) NOT NULL ,"
			+ " Shortname VARCHAR(4) NOT NULL ,"
			+ " Comment VARCHAR(24) ,"
			+ " Rating INTEGER ) ";
	/* **********************************************								
	Owner		Longname	Shortname	Comment
	SHIP0003,	KRAKEN,		KE,			KRAKEN
	SHIP0003,	WYRDSTORM,	WS,			MAGIC STORM
	SHIP0003,	ORKHORDE,	OH,			ORK HORDE
	************************************************ */

	HazardImpl() {
		super(vList, "stuff");
		System.out.println("in HazardImpl()");
	}

	HazardImpl(String sFilename) {

		super(vList, sFilename);

		// setSFilename( sFilename);
		System.out.println("Constructor HazardImpl(sFilename);");
	}
	public int ListAllOnScreenxx() {

		System.out.println("ListAllOnScreen size of list:" + vList.size());

		for (int xx = 0; xx < vList.size(); xx++) {
			Hazard hh = (Hazard) vList.elementAt(xx);
			System.out.println(hh.toString());
		}

		return 0;
	}

	public static void main(String args[]) {

		HazardImpl ci = new HazardImpl(".\\combatinfo.dat");

		System.out.println("main");

		int iCreate = 1;

		if (iCreate == 1) {

			/*******************************************
			Hazard ci1 = new Hazard(3, 5, 3, 1);
			
			// sets up internal list
			vList.add(ci1);
			CombatInfo ci2 = new Hazard(3, 5, 3, 1);
			
			// sets up internal list
			vList.add(ci2);
			*********************/

			HazardImpl haz = new HazardImpl();

			haz.readSeqData("E:\\newestjabber\\hazard.txt");
			//readSeqData(String sFileName)
			System.out.println("Exited readSeqData");
			haz.ListAllOnScreen();

			/***********************
			ci.readUniverseFileIntoVector(); //readSeqData(String sFileName) 
			System.out.println("==================");
			System.out.println("Un-serializing");
			
			ci.SerializeOut();
			//vList.clear();
			//ci1 = null;
			************************************/

		} else {
			ci.SerializeIn();
			//ci.ListAllOnScreen();	
		}

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

		System.out.println(
			"trying to open "
				+ System.getProperty("user.dir")
				+ "\\"
				+ sFileName);

		if (fProp.exists() == false) {
			String sMessage =
				new Date().toString()
					+ "   "
					+ "Problem opening "
					+ sFileName
					+ " in "
					+ System.getProperty("user.dir");
			System.out.println(sMessage);
			System.exit(0);

		} else
			try {

				BufferedReader in =
					new BufferedReader(new FileReader(sFileName));
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
						Hazard haz = new Hazard();

						haz.setOwner(cmd.nextToken().trim());
						haz.setItemTag(cmd.nextToken().trim());
						haz.setLongname(cmd.nextToken().trim());
						haz.setShortname(cmd.nextToken().trim());
						haz.setComment(cmd.nextToken().trim());
						haz.setRating(Integer.parseInt(cmd.nextToken().trim()));

						//System.err.println("===>> line="+line);
						vList.addElement((Hazard) haz);

					} catch (Exception e) {
						System.err.println("Exception from try:" + e);
					}

				}

			} catch (IOException e) {
				System.err.println("IOException from try:" + e);
			}
		//stem.out.println("getlistcount:"+getListCount());

	}

	/**
	 * @return String
	 */
	public String getStmtCreateTable() {
		return stmtCreateTable;
	}

}

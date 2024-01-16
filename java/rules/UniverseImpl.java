package newuniv.rules;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.StringTokenizer;
import java.util.Vector;

import newuniv.Universe;
import newuniv.Utils;

public class UniverseImpl extends Utils {

	//final static int OBJLEN = 9;
	
	String stmtCreateTable =
		"CREATE TABLE Universe "
			+ " (Owner VARCHAR(16) NOT NULL ,"
			+ " xx Integer NOT NULL,"
			+ " yy Integer NOT NULL,"
			+ " xy Integer NOT NULL) ";
	
	transient static String sFilename = null;
	transient static Vector vList = new Vector();

	//	constructor
	public UniverseImpl(String sFilename) {

		super(vList, sFilename);

		setSFilename(sFilename);
		System.out.println("Constructor UniverseImpl(sFilename);");
	}

	UniverseImpl() {
		super(vList, "stuff");
		System.out.println("in UniverseImpl()");
	}

	static public String customFormat(String pattern, int value) {
		java.text.DecimalFormat myFormatter =
			new java.text.DecimalFormat(pattern);

		String output = myFormatter.format(value);

		return output;
	}
	public boolean findUnivFromObject(String obj, Universe univ) {
		boolean found = false;
		for (int xx = 0; xx < vList.size(); xx++) {
			Universe pp = (Universe) vList.elementAt(xx);
			if (pp.getOwner().equals(obj)) {
				pp.print();
				univ = pp;
				found = true;
				break;
			}
		}
		if (found == false)
			univ = null;

		return found;
	}
	public int ListUniverseImplOnScreen() {
		System.out.println(
			"ListUniverseImplOnScreen  UniverseImplList.size:" + vList.size());

		for (int xx = 0; xx < vList.size(); xx++) {
			Universe pp = (Universe) vList.elementAt(xx);
			pp.print();
		}
		return 0;
	}
	// Instantiate a CreateSeqFile object and start the program
	public static void main(String args[]) {
		System.out.println("main");

		String pathstring =
			"." + System.getProperty("file.separator") + "univ.dat";
		System.err.println("main UniverseImpl pathstring:" + pathstring);

		UniverseImpl univ = new UniverseImpl(pathstring);

		// sets up internal list
		int xx = 99;
		int yy = 6;
		int shipnum = 4;

		int iCreate = 0;

		if (iCreate == 1) {

			//		System.out.println("==================");
			//		System.out.println("Serializing");
			//
			//		String sCustFormed = customFormat("0000", shipnum);
			//		String sObject = "SHIP" + sCustFormed;
			//
			//		Universe univ1 = new UniverseImpl(sObject, xx, yy, (long) (xx >> 8 + yy));
			//		univ1.addItemtoVector();
			//
			//		sObject = "SHIP0002";
			//		Universe univ2 = new UniverseImpl(sObject, xx, yy, (long) (xx >> 8 + yy));
			//		univ2.addItemtoVector();
			//
			//		sObject = "SHIP0003";
			//		Universe univ3 = new UniverseImpl(sObject, xx, yy, (long) (xx >> 8 + yy));
			//		univ3.addItemtoVector();
			//
			//		univ.ListUniverseImplOnScreen();
			//		univ.SerializeOut();

		} else {

			UniverseImpl haz = new UniverseImpl();

			haz.readSeqData("E:\\newestjabber\\Universe.txt");
			//readSeqData(String sFileName)
			System.out.println("Exited readSeqData");
			haz.ListAllOnScreen();

		}

		System.out.println("==================");
		System.out.println("Un-serializing");

		univ.SerializeIn();
		univ.ListUniverseImplOnScreen();

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
						Universe haz = new Universe();

						haz.setOwner(cmd.nextToken().trim()); // String
						haz.setXx(Integer.parseInt(cmd.nextToken().trim()));
						//String
						haz.setYy(Integer.parseInt(cmd.nextToken().trim()));
						//String

						long xy = (haz.getXx()) + haz.getYy();
						haz.setXy(xy); //int 

						//System.err.println("===>> line="+line);
						vList.addElement((Universe) haz);

					} catch (Exception e) {
						System.err.println("Exception from try:" + e);
					}

				}

			} catch (IOException e) {
				System.err.println("IOException from try:" + e);
			}
		//stem.out.println("getlistcount:"+getListCount());

	}

	public String getUniverseItemsByOwner(String Owner, Vector vCollection) {

		for (int x = 0; x < vList.size(); x++) {
			Universe cc = (Universe) vList.elementAt(x);
			if (cc.getOwner().equals(Owner)) {
				vCollection.add(cc);
			}
		}
		System.out.println(Owner + " doesnot in PlanetImpl.vList");
		return null;
	}

	public boolean showLocationContent(int xx, int yy, Vector vCollection) {

		//System.out.print("showLocationContent");
		boolean found = false;
		for (int cntr = 0; cntr < vList.size(); cntr++) {
			Universe pp = (Universe) vList.elementAt(cntr);
			if (pp.getXx() == xx && pp.getYy() == yy) {
				pp.print();
				vCollection.add(pp);
				found = true;
			}
		}
		return found;

	}

	/**
	 * @return String
	 */
	public String getStmtCreateTable() {
		return stmtCreateTable;
	}

}
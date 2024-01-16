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
import java.util.Vector;

import newuniv.Cache;
import newuniv.Utils;

public class CacheImpl extends Utils {

	static String sFilename = null;
	static java.util.Vector vList = new java.util.Vector();

	public String stmtCreateTable =
		"CREATE TABLE Cache "
			+ " (Owner VARCHAR(24) NOT NULL ,"
			+ " TaskForceTag VARCHAR(24) NOT NULL ,"
			+ " Shortname INTEGER,"
			+ " Quantity INTEGER )";	
	
	/* **********************************************
	
	Owner		TaskForceTag	Shortname	Quantity
	SHIP0003,	TASK0014,		PE,			100
	SHIP0003,	TASK0014,		GO,			1000
	SHIP0003,	TASK0014,		WE,			20

	************************************************ */
	
		CacheImpl(String sFilename) {
			super(vList, sFilename);
			System.out.println("Constructor CacheImpl(sFilename);");	

		}

	/**
	 * @param v
	 * @param sfname
	 */
	CacheImpl(Vector v, String sfname) {
		super(v, sfname);
		// TODO Auto-generated constructor stub
	}
	
	public int  ListAllOnScreen() {
		System.out.println("ListCacheOnScreen size of list:" + vList.size());

		for (int xx = 0; xx < vList.size(); xx++) {
			Cache pp = (Cache) vList.elementAt(xx);
			pp.print();
		}
		return 0;
	}
	
	

	public boolean getCacheByOwner(String Owner, Vector vCollection) {
		boolean found = false;
		for (int xx = 0; xx < vList.size(); xx++) {
			Cache ss = (Cache) vList.elementAt(xx);

			if (ss.getOwner().equals(Owner)) {
				ss.print();
				vCollection.add(ss);
				found = true;
			}
		}
		return found;
	}
public static void main(String args[]) {

	CacheImpl army = new CacheImpl("d:\\jabber3\\army.dat");

	System.out.println("main");

	int iCreate = 1;

	
	if (iCreate == 1) {
		Cache army1 = new Cache("SHIP0001", "TFTG0003", "GO", 16); // sets up internal list
		vList.add(army1);
		
		Cache cache = new Cache("SHIP0009", "TFTG0006", "SW", 25); // sets up internal list
		vList.add(cache);

		//army.readUniverseFileIntoVector();

		System.out.println("==================");
		System.out.println("Un-serializing");

		//Cache.SerializeOut();
		//vList.clear();
		//army1 = null;
	}
	
	//Cache.SerializeIn();

	//Cache.ListAllOnScreen();

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
						
					Cache ca = new Cache();
					
					ca.setOwner ( cmd.nextToken().trim()); // String
					ca.setTaskForceTag(cmd.nextToken().trim()); //String
					ca.setShortname ( cmd.nextToken().trim()); //String
					ca.setQuantity ( Integer.parseInt(cmd.nextToken().trim())); //int 
											
					//System.err.println("===>> line="+line);
					vList.addElement((Cache) ca);

				} catch (Exception e) {
					System.err.println("Exception from try:" + e);
				}

			}

		} catch (IOException e) {
			System.err.println("IOException from try:" + e);
		}

} // end of readInventSeqData	
	/**
	 * @return java.util.Vector
	 */
	public static java.util.Vector getVList() {
		return vList;
	}


	/**
	 * Sets the vList.
	 * @param vList The vList to set
	 */
	//public static void setVList(java.util.Vector vList) {
	//	Cache.vList = vList;
	//}



	/**
	 * @return String
	 */
	public String getStmtCreateTable() {
		return stmtCreateTable;
	}

}

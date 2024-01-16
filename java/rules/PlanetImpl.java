/*
 * Created on Apr 10, 2003
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package newuniv.rules;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.StringTokenizer;

import newuniv.Planet;
import newuniv.Utils;

/**
 * @author JSIRAGHER
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class PlanetImpl extends Utils {
	// end of readInventSeqData	
	
	String stmtCreateTable =
		"CREATE TABLE Planet "
			+ " (ItemTag VARCHAR(16) NOT NULL ,"
			+ " Owner VARCHAR(16) NOT NULL ,"
			+ " GameID VARCHAR(12) NOT NULL ,"
			+ " Name VARCHAR(16) NOT NULL ,"
			+ " Size Integer NOT NULL, "
			+ " Atmosphere Integer NOT NULL, "
			+ " NativeRace VARCHAR(4) NOT NULL ,"
			+ " Oretype VARCHAR(4) NOT NULL ,"
			+ " Bonus VARCHAR(24) NOT NULL ," 
			+ " isCaptured Integer NOT NULL) ";
	
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
						Planet haz = new Planet();
				
						haz.setItemtag(	cmd.nextToken().trim()); // String
						haz.setOwner(	cmd.nextToken().trim()); // String
						haz.setGameID(	cmd.nextToken().trim()); // String		
						haz.setName(	cmd.nextToken().trim()); // String
						haz.setSize(	Integer.parseInt(cmd.nextToken().trim()) ); //int 
						haz.setAtmosphere(Integer.parseInt(cmd.nextToken().trim()) ); //int 
						haz.setNativeRace(cmd.nextToken().trim()); // String
						haz.setOretype(	cmd.nextToken().trim()); // String
						//Boolean isCaptured
						//Boolean isInhabitable
						int someval = Integer.parseInt(cmd.nextToken().trim());
						if (someval == 0)
						  haz.setIsCaptured(Boolean.FALSE);
						else
						  haz.setIsCaptured(Boolean.TRUE);				
						
//						someval = Integer.parseInt(cmd.nextToken().trim());
//						if (someval == 0)
//						  haz.setIsInhabitable(Boolean.FALSE);
//						else
//						  haz.setIsInhabitable(Boolean.TRUE);
										
												
						//System.err.println("===>> line="+line);
						vList.addElement((Planet) haz);
	
					} catch (Exception e) {
						System.err.println("Exception from try:" + e);
					}
	
				}
	
			} catch (IOException e) {
				System.err.println("IOException from try:" + e);
			}
			//stem.out.println("getlistcount:"+getListCount());
			
	
	} // end of readInventSeqData		/**


	static String sFilename = null;
	static java.util.Vector vList = new java.util.Vector();
	
/* **********************************************								
Owner		Longname	Shortname	Comment
SHIP0003,	KRAKEN,		KE,			KRAKEN
SHIP0003,	WYRDSTORM,	WS,			MAGIC STORM
SHIP0003,	ORKHORDE,	OH,			ORK HORDE
************************************************ */

	PlanetImpl()
	{
		super (vList, "stuff");
		System.out.println("in PlanetImpl()" );
	}
	
	PlanetImpl(String sFilename) {

		super(vList, sFilename);

		// setSFilename( sFilename);
		System.out.println("Constructor PlanetImpl(sFilename);");
	}
	
	
	public int ListAllOnScreenxx() {

		System.out.println("ListAllOnScreen size of list:" + vList.size());

		for (int xx = 0; xx < vList.size(); xx++) {
			Planet hh = (Planet) vList.elementAt(xx);
			System.out.println(hh.toString());
		}

		return 0;
	}
	
	public Planet getPlanetsByOwner(String Owner) {

		for (int x = 0; x < vList.size(); x++) {
			Planet c = (Planet) vList.elementAt(x);
			if (c.getOwner().equals(Owner)) {
				return c;
			}
		}
		System.out.println(Owner + " doesnot in PlanetImpl.vList");
		return null;
	}

	public static void main(String[] args) {
	}
	
	
	/**
	 * @return String
	 */
	public String getStmtCreateTable() {
		return stmtCreateTable;
	}

}

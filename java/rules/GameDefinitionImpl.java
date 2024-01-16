/*
 * Created on Apr 11, 2003
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
import java.util.Vector;

import newuniv.GameDefinition;
import newuniv.Ship;
import newuniv.Utils;

/**
 * @author JSIRAGHER
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */

public class GameDefinitionImpl extends Utils {

	transient static String sFilename = null;
	transient static java.util.Vector vList = new java.util.Vector();

	public String stmtCreateTable =
		"CREATE TABLE GameDefinition " 
			+ "( GameID INTEGER,"
			+ " GameType VARCHAR(8) NOT NULL ,"
			+ " GamePassword VARCHAR(8) NOT NULL ,"
			+ " MaxPlayers INTEGER,"
			+ " StartTech INTEGER,"
			+ " MaxTech INTEGER )";	

	/**
	 * @param v
	 * @param sfname
	 */
	public GameDefinitionImpl(Vector v, String sfname) {
		super(v, sfname);
		// TODO Auto-generated constructor stub
	}




		/*******************************************************************************
		Shipname,	User,	Password,	OwnerID,	Shipname, 	Race,	Alliances,	Funds,xx,yy,Energy
	
		JSiragher,	jodasi,	dungeon,	SHIP0003,	Blackbird,	Human,	none,		10000,22,44,200
		CRaige,		ghost1,	dungeon,	SHIP0004,	kissina,	Human,	none,		10000,22,44,122
		***************************************************************/

		GameDefinitionImpl(String sFilename) {

			super(vList, sFilename);

			setSFilename(sFilename);
			System.out.println("Constructor GameDefinitionImpl(sFilename);");
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
						
//							int GameID;
//							String GameName;
//							String GameType;
//							String GamePassword;
//							int MaxPlayers;
//							int StartTech;
//							int MaxTech;
	


							GameDefinition sh= new GameDefinition();
							sh.setGameID ( Integer.parseInt(cmd.nextToken().trim()));
							sh.setGameName( cmd.nextToken().trim()); //String
							sh.setGameType ( cmd.nextToken().trim()); //String
							sh.setGamePassword ( cmd.nextToken().trim()); // String
							sh.setMaxPlayers ( Integer.parseInt(cmd.nextToken().trim()));
							sh.setStartTech ( Integer.parseInt(cmd.nextToken().trim()));
							sh.setMaxTech ( Integer.parseInt(cmd.nextToken().trim()));

							//System.err.println("===>> line="+line);
							vList.addElement((GameDefinition) sh);

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

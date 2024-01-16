package newuniv.rules;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.StringTokenizer;

import newuniv.PlayerAccount;
import newuniv.Utils;

public class PlayerAccountImpl extends Utils {

	transient static String sFilename = null;
	transient static java.util.Vector vList = new java.util.Vector();

	/*******************************************************************************
	PlayerAccountname,	User,	Password,	OwnerID,	Shipname, 	Race,	Alliances,	Funds,xx,yy,Energy
	
	JSiragher,	jodasi,	dungeon,	SHIP0003,	Blackbird,	Human,	none,		10000,22,44,200
	CRaige,		ghost1,	dungeon,	SHIP0004,	kissina,	Human,	none,		10000,22,44,122
	***************************************************************/

	String stmtCreateTable =
		"CREATE TABLE PlayerAccount "
			+ " (Firstname VARCHAR(16) NOT NULL ,"
			+ " Lastname VARCHAR(16) NOT NULL ,"
			+ " Email VARCHAR(12) NOT NULL ,"
			+ " Password VARCHAR(16) NOT NULL ,"
			+ " SecretAnswer VARCHAR(16) NOT NULL ,"
			+ " SecretQuestion Integer NOT NULL) ";


	PlayerAccountImpl(String sFilename) {

		super(vList, sFilename);

		setSFilename(sFilename);
		System.out.println("Constructor PlayerAccountImpl(sFilename);");
	}

	public int ListAllOnScreen() {
		System.out.println("Size of Race list:" + vList.size());

		for (int xx = 0; xx < vList.size(); xx++) {
			PlayerAccount pp = (PlayerAccount) vList.elementAt(xx);
			pp.print();
		}

		return 0;
	}

	public static void main(String args[]) {

		PlayerAccountImpl ci =
			new PlayerAccountImpl("d:\\jabber3\\PlayerAccount.dat");

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

						//						String 	Firstname;	// joel
						//						String 	Lastname;	// siragher
						//						String	Email;		// runescience@yahoo.com
						//						String	UID;		// jodasi
						//						String 	Password;	// ????????
						//						String 	Owner; 		// SHIP0034

						PlayerAccount pl = new PlayerAccount();

						pl.setFirstname(cmd.nextToken().trim()); //String
						pl.setLastname(cmd.nextToken().trim()); // String
						pl.setEmail(cmd.nextToken().trim()); //String
						pl.setUID(cmd.nextToken().trim()); // String
						pl.setPassword(cmd.nextToken().trim()); //String
						pl.setSecretAnswer(cmd.nextToken().trim()); // String
						pl.setSecretQuestion(
							Integer.parseInt(cmd.nextToken().trim()));
						// String

						//PlayerAccount			user	passw		ownerid		shipname	race	alliance 	funds	xx	yy	energy
						//joel siragher,	jodasi,	dungeon,	ship0003,	blackbird,	human,	noalliance,	5000,	23,	44,	2e1.43
						//joel siragher,	jodasi,	dungeon,	ship0003,	blackbird,	human,	noalliance,	5000,	23,	44,	2e1.43
						//joel siragher,	jodasi,	dungeon,	ship0003,	blackbird,	human,	noalliance,	5000,	23,	44,	2e1.43

						//System.err.println("===>> line="+line);
						vList.addElement((PlayerAccount) pl);

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
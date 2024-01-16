package newuniv.rules;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Date;
import java.util.StringTokenizer;
import java.util.Vector;

import newuniv.Status;
import newuniv.Utils;

/** 
*
*
*
********************/
public class StatusImpl extends Utils {

	final static int OBJLEN = 9;

	static RandomAccessFile output; // enables output of data to file
	static DataInputStream input; // file from which data is read

	static int counter = 0;

	transient static String sFilename = null;
	transient static java.util.Vector vList = new java.util.Vector();

	/* **************************************************
	
	100,	0,	1,	SHIP0003,	TASK1234,	GO,
	200,	0,	1,	SHIP0003,	TASK1234,	WE,
	100,	0,	1,	SHIP0003,	TASK1234,	CA,
	
	************************************************* */

	/** 
	*
	*
	*
	********************/
	//  constructor
	public StatusImpl() {
		super(vList, "stuff");
		System.out.println("Constructor StatusImpl(sFilename)");
	}

	/** 
	*
	*
	*
	********************/
	//  constructor
	public StatusImpl(String sFilename) {

		super(vList, sFilename);

		setSFilename(sFilename);
		System.out.println("Constructor Status(sFilename);");
	}

	/** 
	*
	*
	*
	********************/

	public void addRecToVectorAndSeqFile(
		int quan,
		int dam,
		int act,
		String obj,
		String sname) {
		//System.out.println("addRecToVector(int,int,int,String,String");

		Status myStatus = new Status();
		myStatus.setQuantity(quan);
		myStatus.setDamage(dam);
		myStatus.setActive(act);
		myStatus.setOwner(obj);
		myStatus.setTaskForceTag(obj);
		myStatus.setShortname(sname);
		myStatus.print();
		addRecToVectorAndSeqFile(myStatus);
	}
	/** 
	*
	*
	*
	********************/

	public void addRecToVectorAndSeqFile(Status istatus) {
		System.out.print("addRecToVector(istatus)+");
		try {
			output = new RandomAccessFile("seq_status.dat", "rw");
			output.seek(output.length());
		} catch (IOException e) {
			System.err.println(
				"status File not opened properly" + e.toString());
			System.exit(1);
		}
		// output the values to the file
		try {

			output.writeInt(istatus.getQuantity());
			output.writeInt(istatus.getDamage());
			output.writeInt(istatus.getActive());
			output.writeUTF(istatus.getOwner());
			output.writeUTF(istatus.getTaskForceTag());
			output.writeUTF(istatus.getShortname());
			//istatus.printStatus();
			vList.addElement(new Status(istatus));
		} catch (IOException e) {
			System.err.println(
				"Error during write to status file\n" + e.toString());
			System.exit(1);
		}
		try {
			output.close();
		} catch (IOException e) {
			System.err.println(
				"status.dat not closed properly\n" + e.toString());
			System.exit(1);
		}
	}
	/** 
	*
	*
	*
	********************/

	public int getDamageOnPart(String TaskForceTag, String sname) {
		System.out.println(
			"getDamageOnPart(String TaskForceTag, String sname)");
		int dmgcntr = 0;
		for (int xx = 0; xx < vList.size(); xx++) {
			Status ss = (Status) vList.elementAt(xx);
			if (ss.getTaskForceTag().equals(TaskForceTag)
				&& ss.getShortname().equals(sname))
				dmgcntr += ss.getDamage();
		}
		return dmgcntr;
	}
	/** 
	*
	*
	*
	********************/

	public boolean getPartsByTaskForceTag(String TaskForceTag) {
		boolean found = false;
		for (int xx = 0; xx < vList.size(); xx++) {
			Status ss = (Status) vList.elementAt(xx);

			if (ss.getTaskForceTag().equals(TaskForceTag)) {
				ss.print();
				found = true;
			}
		}
		return found;
	}

	/** 
	*
	*
	*
	********************/

	public boolean getPartsByOwner(String Owner) {
		boolean found = false;
		for (int xx = 0; xx < vList.size(); xx++) {
			Status ss = (Status) vList.elementAt(xx);

			if (ss.getOwner().equals(Owner)) {
				ss.print();
				found = true;
			}
		}
		return found;
	}
	public Status getStatusFromShortname(String sname) {

		for (int x = 0; x < vList.size(); x++) {
			Status c = (Status) vList.elementAt(x);
			if (c.getShortname().equals(sname)) {
				return c;
			}
		}
		System.out.println(sname + " doesnot in Status.vList");
		return null;
	}
	/** 
	*
	*
	*
	********************/

	public boolean isPartActive(String TaskForceTag, String sname) {
		System.out.println("isPartActive(String TaskForceTag, String sname)");
		for (int xx = 0; xx < vList.size(); xx++) {
			Status ss = (Status) vList.elementAt(xx);
			if (ss.getTaskForceTag().equals(TaskForceTag)
				&& ss.getShortname().equals(sname)) {
				if (ss.getActive() == 1)
					return true;
				else
					return false;
			}
		}
		return false;
	}
	/** 
	*
	*
	*
	********************/

	public int ListAllOnScreen() {
		System.out.println("ListStatusOnScreen.size=" + vList.size());
		for (int xx = 0; xx < vList.size(); xx++) {
			Status ss = (Status) vList.elementAt(xx);
			ss.print();
		}

		return 0;
	}
	/** 
	*
	*
	*
	********************/
	// Instantiate a CreateSeqFile TaskForceTag and start the program
	public static void main(String args[]) {

		StatusImpl statusimpl = new StatusImpl("e:\\newjabber\\status.dat");

		System.out.println("main");

		int iCreate = 0;

		if (iCreate == 1) {

			//			String Owner;
			//			String TaskForceTag;
			//			String Shortname;
			//	
			//			int Quantity;
			//			int Damage;
			//			int Active;

			System.out.println(
				"Status(int quan, int Damage, int Active, "
					+ "String TargetForce, string shrtname)");

			Status ci1 = new Status("SHIP0345", "", "PE", 3, 0, 1);

			// sets up internal list
			vList.add(ci1);

			//NAME		ABR	 GROUP		COST BONUS MAX STARTQUAN
			Status ci2 = new Status("SHIP0345", "", "GO", 3, 0, 1);

			// sets up internal list
			vList.add(ci2);

			//statusimpl.readStatusFileIntoVector();

			System.out.println("==================");
			System.out.println("Un-serializing");

			statusimpl.SerializeOut();
			//vList.clear();
			//ci1 = null;

		}

		statusimpl.SerializeIn();

		statusimpl.ListAllOnScreen();

		System.out.println("==================");
		System.out.println("closing");

		System.exit(0);
	}

	/** 
	*
	*
	*
	********************/

	public int QuantityOnHand(String Owner, String sname) {
		System.out.println("QuantityOnHand(TaskForceTag, sname)");
		;
		int partcount = 0;
		for (int xx = 0; xx < vList.size(); xx++) {
			Status ss = (Status) vList.elementAt(xx);
			if (ss.getOwner().equals(Owner) && ss.getShortname().equals(sname))
				partcount += ss.getQuantity();
		}
		return partcount;
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
						Double d;

						Status myStatus = new Status();

						myStatus.setOwner(cmd.nextToken().trim()); //comments
						myStatus.setTaskForceTag(cmd.nextToken().trim());
						//comments
						myStatus.setShortname(cmd.nextToken().trim());
						//longname

						myStatus.setQuantity(
							Integer.parseInt(cmd.nextToken().trim()));
						//sz
						myStatus.setDamage(
							Integer.parseInt(cmd.nextToken().trim()));
						//co           
						myStatus.setActive(
							Integer.parseInt(cmd.nextToken().trim()));
						//longname

						//System.err.println("===>> line="+line);
						vList.addElement((Status) myStatus);

					} catch (Exception e) {
						System.err.println("Exception from try:" + e);
					}

				}

			} catch (IOException e) {
				System.err.println("IOException from try:" + e);
			}

	} // end of readInventSeqData

	public void readInventSeqData(Vector List) {
		// Open the file
		DataInputStream input = null;
		try {
			input = new DataInputStream(new FileInputStream("invent.dat"));
		} catch (IOException e) {
			System.err.println(
				"invent.dat not opened properly\n" + e.toString());
			System.exit(1);
		}

		try {
			while (input.available() != 0) {

				Status sta = new Status();

				sta.setOwner(input.readUTF());
				sta.setTaskForceTag(input.readUTF());
				sta.setShortname(input.readUTF());

				sta.setQuantity(input.readInt());
				sta.setDamage(input.readInt());
				sta.setActive(input.readInt());

				//System.out.println("++++++++ counter="+ ++counter  );
				vList.addElement(new Status(sta));
			}
		} catch (IOException e) {
			System.err.println("Error during read from file\n" + e.toString());
			System.exit(1);
		}
		try {
			input.close();
		} catch (IOException e) {
			System.err.println("Error during input.close\n" + e.toString());
			System.exit(1);
		}

	}
	/** 
	*
	*
	*
	********************/
	//  public void printStatus(Status inp){
	//    System.out.print("printStatus(inp)");
	// 
	//    if ( inp == null)
	//      return;
	//    System.out.println( inp.TaskForceTag  + ":" + inp.Shortname + ":" +
	//       inp.Active + ":" + inp.Quantity + ":" + inp.Damage );
	//  }

	/** 
	*
	*
	*
	********************/
	public boolean readStatusSeqData() {
		System.out.println("readStatusSeqData()");

		File statustxt = new File("status.dat");

		if (statustxt.exists() == false) {
			return false;
		}

		try {
			input = new DataInputStream(new FileInputStream("status.dat"));
		} catch (IOException e) {
			System.err.println(
				"status.dat not opened properly:" + e.toString());
			return false;
		}
		try {
			while (input.available() != 0) {

				//				String Owner;
				//				String TaskForceTag;
				//				String Shortname;
				//				int Quantity;
				//				int Damage;
				//				int Active;

				Status myStatus = new Status();

				myStatus.setOwner(input.readUTF());
				myStatus.setTaskForceTag(input.readUTF());
				myStatus.setShortname(input.readUTF());

				myStatus.setQuantity(input.readInt());
				myStatus.setDamage(input.readInt());
				myStatus.setActive(input.readInt());
				vList.addElement(new Status(myStatus));
				myStatus.print();
			}
		} catch (IOException e) {
			System.err.println("Error during read status.dat" + e.toString());
			System.exit(1);
		}
		try {
			input.close();
		} catch (IOException e) {
			System.err.println("Error during close status.dat:" + e.toString());
			System.exit(1);
		}

		return true;
	}

}
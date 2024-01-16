/*
 * Created on Apr 8, 2003
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package newuniv;

/**
 * @author JSIRAGHER
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Vector;


public class Utils {
	Vector vList = null;
	String sFilename = null;
	String 	 stmtCreateTable ;
	
	protected Utils(Vector v, String sfname){
		vList = v;
		sFilename =sfname;
		System.out.println("directory:" + 
			System.getProperty("user.dir") );

		
	};
	
	public void SerializeOut() {
		System.out.println("SerializeOut() using:"+ sFilename);
		// Open the file

		//file IO initiation
		java.io.File file = new java.io.File(sFilename);
		
		ObjectOutputStream writeThreadObject = null;

		int listLen = 0;

		if (vList == null)
		{
			System.out.println("return because vList is null");
			return;
		}
		
		if ((listLen = vList.size()) == 0)
			return;

		try {

			writeThreadObject =
				new ObjectOutputStream(new FileOutputStream(sFilename));

			//tos.setStatus(ThreadObjectSerializer.ITEMCREATED);

			for (int ii = 0; ii < listLen; ii++) {
				//Unit tos = (Unit) vList.elementAt(ii);
				
				try {
					writeThreadObject.writeObject(vList.elementAt(ii)); //vList.elementAt(ii));
					writeThreadObject.flush();
				} catch (IOException e) {

					String sMessage = " SerializeIn for:" + sFilename;
					break;
				}
				

			}

			writeThreadObject.close();

		} catch (IOException e) {
			String sErrorString = "ObjectOutputStream failed:" + sFilename;
			return;

		}
	}
	public void SerializeIn() {

		System.out.println("SerializeIn() using:"+ sFilename);

		// Open the file
		//file IO initiation
		java.io.File file = new java.io.File(sFilename);

		if (!file.exists())
		{
			System.out.println("returning because File:"+sFilename+" does not exists");
			return;
		}

		if (vList == null)
		{
			System.out.println("returning vList == null");
			return;
		}

		//Unit tos = new Unit();

		ObjectInputStream input = null;
		

		//Army tos;
		
		try {
			
			input = new ObjectInputStream(new FileInputStream(sFilename));

			System.out.println("about to do while...");

			while (true) {

				try
				{
					vList.addElement( input.readObject());								
				
				}
				catch (Exception e)
				{
					System.out.println("Exception:"+e.getMessage());
					
					break;
				} 
			
			}

		} catch (Exception e) {
			System.out.println(sFilename+" not opened properly\n" + e.toString());
			System.exit(1);
		}

		try {
			input.close();
		} catch (java.io.IOException e) {
			System.out.println("Error during close " + sFilename + ":" + e.toString());
			System.exit(1);
		}
	}
	public void addItemtoVector() {
		vList.addElement(this);
	}
	public void addItemtoVector(Object obj) {
		vList.addElement(obj);
	}
	public void addItemtoVector(Vector vList) {
		vList.addElement(this);
	}
	void ClearVector() {
		vList.clear();
	}
	
	static public String customFormat(String pattern, int value) {
		java.text.DecimalFormat myFormatter = new java.text.DecimalFormat(pattern);

		String output = myFormatter.format(value);

		return output;
	}
	
	public int getListCount() {
		return vList.size();
		
	}
		public int ListAllOnScreen() {

//			System.out.println(
//				"Utils.ListAllOnScreen size of list:" + vList.size());

			for (int xx = 0; xx < vList.size(); xx++) {

				Object hh = vList.elementAt(xx);

				Class cll = hh.getClass();

				System.out.println("\nfor Class:" + cll.getName());

				Method[] m = cll.getMethods();

				//System.out.println("m.length:" + m.length);

				for (int ii = 0; ii < m.length; ii++) {

					Method methodname = m[ii];
					
					if (methodname
						.getName()
						.substring(0, 3)
						.equalsIgnoreCase("get") ) {
						
						// first getter is for the classname
						//   scary :)
						if (methodname
						.getName()
						.substring(0, 8)
						.equalsIgnoreCase("getclass") )
							continue;

						try {

							//Object oo = m[ii].invoke(hh, null);
							Object oo = methodname.invoke(hh, null);
							System.out.println("invoked getter on fld " + 
									methodname.getName().substring(3) +":" + oo);

						} catch (IllegalArgumentException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IllegalAccessException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (InvocationTargetException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}
				}

			}
return 0;
		}


	
	/**
	 * @return
	 */
	public String getSFilename() {
		return sFilename;
	}

	/**
	 * @param string
	 */
	public void setSFilename(String string) {
		sFilename = string;
	}

}

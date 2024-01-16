package newuniv;

import java.io.Serializable;

public class Universe implements Serializable {

	//final static int OBJLEN = 9;

	String Owner; //TaskForceTag
	int xx;
	int yy;
	long xy;

	String stmtCreateTable =
		"CREATE TABLE Universe "
			+ " (Owner VARCHAR(16) NOT NULL ,"
			+ " xx Integer NOT NULL,"
			+ " yy Integer NOT NULL,"
			+ " xy Integer NOT NULL) ";

	//  constructor
	public Universe() {

	}

	// contructor
	public Universe(String taskforce, int xx, int yy, long xy) {

		this.Owner = taskforce;
		this.xx = xx;
		this.yy = yy;
		this.xy = xy;

	}
	// copy contructor
	public Universe(Universe inp) {
		//System.out.print(" Universe(Universe inp)");
		this.Owner = inp.Owner;
		this.xx = inp.xx;
		this.yy = inp.yy;
		this.xy = inp.xy;
	}
	static public String customFormat(String pattern, int value) {
		java.text.DecimalFormat myFormatter =
			new java.text.DecimalFormat(pattern);

		String output = myFormatter.format(value);

		return output;
	}

	public void print() {
		//System.out.println("printUniverse()");
		if (this == null)
			return;

		System.out.println(
			this.Owner
				+ " xx:"
				+ this.xx
				+ " yy:"
				+ this.yy
				+ " xy:"
				+ this.xy
				+ "\n");

	}
	public void print(Universe inp) {
		//System.out.println("printUniverse(Universe inp)");
		if (inp == null)
			return;

		System.out.println(
			inp.Owner
				+ " xx:"
				+ inp.xx
				+ " yy:"
				+ inp.yy
				+ " xy:"
				+ inp.xy
				+ "\n");

	}
	/**
	 * @return String
	 */
	public String getOwner() {
		return Owner;
	}

	/**
	 * @return int
	 */
	public int getXx() {
		return xx;
	}

	/**
	 * @return long
	 */
	public long getXy() {
		return xy;
	}

	/**
	 * @return int
	 */
	public int getYy() {
		return yy;
	}

	/**
	 * Sets the taskForceTag.
	 * @param taskForceTag The taskForceTag to set
	 */
	public void setOwner(String taskForceTag) {
		Owner = taskForceTag;
	}

	/**
	 * Sets the xx.
	 * @param xx The xx to set
	 */
	public void setXx(int xx) {
		this.xx = xx;
	}

	/**
	 * Sets the xy.
	 * @param xy The xy to set
	 */
	public void setXy(long xy) {
		this.xy = xy;
	}

	/**
	 * Sets the yy.
	 * @param yy The yy to set
	 */
	public void setYy(int yy) {
		this.yy = yy;
	}

}
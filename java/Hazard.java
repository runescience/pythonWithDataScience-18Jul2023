// This program uses TextFields to get information from the
// user at the keyboard and writes the information to a
// sequential file.

//This could either be fantasy or ftc

package newuniv;

public class Hazard implements java.io.Serializable {

	String Owner;
	String ItemTag;
	String Longname;
	String Shortname;
	String Comment;
	int Rating;

	public String stmtCreateTable =
		"CREATE TABLE Hazard "
			+ " (Owner VARCHAR(24) NOT NULL ,"
			+ " ItemTag VARCHAR(24) NOT NULL ,"
			+ " Longname VARCHAR(16) NOT NULL ,"
			+ " Shortname VARCHAR(4) NOT NULL ,"
			+ " Comment VARCHAR(24) ,"
			+ " Rating INTEGER ) ";

	/**
	 * 
	 */
	public Hazard() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return String
	 */
	public String getComment() {
		return Comment;
	}

	/**
	 * @return String
	 */
	public String getLongname() {
		return Longname;
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
	public int getRating() {
		return Rating;
	}

	/**
	 * @return String
	 */
	public String getShortname() {
		return Shortname;
	}

	/**
	 * Sets the comment.
	 * @param comment The comment to set
	 */
	public void setComment(String comment) {
		Comment = comment;
	}

	/**
	 * Sets the longname.
	 * @param longname The longname to set
	 */
	public void setLongname(String longname) {
		Longname = longname;
	}

	/**
	 * Sets the owner.
	 * @param owner The owner to set
	 */
	public void setOwner(String owner) {
		Owner = owner;
	}

	/**
	 * Sets the rating.
	 * @param rating The rating to set
	 */
	public void setRating(int rating) {
		Rating = rating;
	}

	/**
	 * Sets the shortname.
	 * @param shortname The shortname to set
	 */
	public void setShortname(String shortname) {
		Shortname = shortname;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		String mystring =
			getOwner()
				+ ":"
				+ getShortname()
				+ ":"
				+ getLongname()
				+ ":"
				+ getRating()
				+ ":"
				+ getComment();
		System.out.println("mystring:" + mystring);
		return mystring;

	}

	/**
	 * @return
	 */
	public String getItemTag() {
		return ItemTag;
	}

	/**
	 * @param string
	 */
	public void setItemTag(String string) {
		ItemTag = string;
	}

}

package newuniv;

import java.io.Serializable;
public class PlayerAccount implements Serializable {

	String Firstname; // joel
	String Lastname; // siragher
	String Email; // runescience@yahoo.com
	String UID; // jodasi
	String Password; // ????????
	String SecretAnswer;
	int SecretQuestion;

	String stmtCreateTable =
		"CREATE TABLE PlayerAccount "
			+ " (Firstname VARCHAR(16) NOT NULL ,"
			+ " Lastname VARCHAR(16) NOT NULL ,"
			+ " Email VARCHAR(12) NOT NULL ,"
			+ " Password VARCHAR(16) NOT NULL ,"
			+ " SecretAnswer VARCHAR(16) NOT NULL ,"
			+ " SecretQuestion Integer NOT NULL) ";

	/**
	 * Insert the method's description here.
	 * Creation date: (11/21/2001 2:06:05 PM)
	 */
	public void print() {

		System.out.println(
			" Firstname:"
				+ Firstname
				+ " Lastname:"
				+ Lastname
				+ " UID:"
				+ UID
				+ " Password:"
				+ Password
				+ " SecretAnswer:"
				+ SecretAnswer
				+ " SecretQuestion:"
				+ SecretQuestion);

	}
	//	Player(String UID, String Password)
	//	{
	//		this.UID = UID;
	//		this.Password = Password;
	//	}
	/**
	 * Insert the method's description here.
	 * Creation date: (11/21/2001 12:33:48 PM)
	 */
	public String getPassword() {
		return Password;
	}
	/**
	 * Insert the method's description here.
	 * Creation date: (11/21/2001 12:33:48 PM)
	 */
	public String getUID() {
		return UID;
	}
	/**
	 * @return String
	 */
	public String getEmail() {
		return Email;
	}

	/**
	 * @return String
	 */
	public String getFirstname() {
		return Firstname;
	}

	/**
	 * @return String
	 */
	public String getLastname() {
		return Lastname;
	}

	/**
	 * Sets the email.
	 * @param email The email to set
	 */
	public void setEmail(String email) {
		Email = email;
	}

	/**
	 * Sets the firstname.
	 * @param firstname The firstname to set
	 */
	public void setFirstname(String firstname) {
		Firstname = firstname;
	}

	/**
	 * Sets the lastname.
	 * @param lastname The lastname to set
	 */
	public void setLastname(String lastname) {
		Lastname = lastname;
	}

	/**
	 * Sets the password.
	 * @param password The password to set
	 */
	public void setPassword(String password) {
		Password = password;
	}

	/**
	 * Sets the uID.
	 * @param uID The uID to set
	 */
	public void setUID(String uID) {
		UID = uID;
	}

	/**
	 * @return String
	 */
	public String getSecretAnswer() {
		return SecretAnswer;
	}

	/**
	 * @return int
	 */
	public int getSecretQuestion() {
		return SecretQuestion;
	}

	/**
	 * Sets the secretAnswer.
	 * @param secretAnswer The secretAnswer to set
	 */
	public void setSecretAnswer(String secretAnswer) {
		SecretAnswer = secretAnswer;
	}

	/**
	 * Sets the secretQuestion.
	 * @param secretQuestion The secretQuestion to set
	 */
	public void setSecretQuestion(int secretQuestion) {
		SecretQuestion = secretQuestion;
	}

}

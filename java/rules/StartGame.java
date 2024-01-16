/*
 * Created on Apr 12, 2003
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package newuniv.rules;

/**
 * @author jds
 *
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class StartGame implements ActionListener {

	private static String DBUrl = "jdbc:mysql:///test";
	private Connection conn = null;
	protected ResultSet rs = null;
	public Statement stmt = null;
	protected static String dbUrl = "jdbc:mysql:///test";

	AssociatePlayerWithGameImpl ap = new AssociatePlayerWithGameImpl("");
	InventoryImpl ii = new InventoryImpl("Inventory.txt");
	CacheImpl ci = new CacheImpl("cache.txt");
	GameDefinitionImpl gdi = new GameDefinitionImpl("");
	HazardImpl hi = new HazardImpl("");
	PlanetImpl pp = new PlanetImpl("");
	PlayerAccountImpl pa = new PlayerAccountImpl("");
	ShipImpl si = new ShipImpl("");
	PartStatusImpl st = new PartStatusImpl("");
	UniverseImpl ui = new UniverseImpl("");
	WormHoleImpl wh = new WormHoleImpl("");

	public static void main(String[] args) {
		System.out.println("Here we go!!!");
		StartGame sg = new StartGame();
		//sg.goBuildTheTables();
		//sg.goBuildTheTables();
		sg.show_menu();
		//sg.deleteTables();

	}

	StartGame() {

		try {

			Class.forName("org.gjt.mm.mysql.Driver").newInstance();

			conn = DriverManager.getConnection(DBUrl);
			stmt =
				conn.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			System.out.println("just connected!!!");
			System.out.println("conn.toSTring:" + conn.toString());

			//			stmt.execute("select * from inventoryuniv order by cost desc");
			//			rs = stmt.getResultSet();
			//			
			//			rs.afterLast();
			//			while (rs.previous()) {
			//				String name = rs.getString("xx");
			//				int n = rs.getInt("Cost");
			//				System.out.println(name+":"+n);
			//			}

			//			while (rs.next()) {
			//				//System.out.println(rs.getString(1));
			//				
			//				String s = rs.getString("shortname");
			//				int n = rs.getInt("cost");
			//				System.out.println(s + "   " + n);
			//				rs.previous();		
			//			}

			//
			//			stmt.executeUpdate("CREATE TABLE COFFEES " +
			//				"(COF_NAME VARCHAR(32), SUP_ID INTEGER, PRICE FLOAT, " +
			//				"SALES INTEGER, TOTAL INTEGER)");

			//			stmt.executeUpdate(
			//				"CREATE TABLE xx_inventoryuniv "
			//					+ " (Shortname VARCHAR(4) NOT NULL ,"
			//					+ " Longname VARCHAR(32) NOT NULL ,"
			//					+ " Cost INTEGER ,"
			//					+ " Techlev INTEGER NOT NULL ,"
			//					+ " StartQuantity INTEGER,"
			//					+ " Comment varchar(32) )"
			//					);

			//
			//			String stmtCreateTable =
			//				"CREATE TABLE Wormhole "
			//					+ " (Owner VARCHAR(16) NOT NULL ,"
			//					+ " TaskForceTag VARCHAR(16) NOT NULL ,"
			//					+ " Password VARCHAR(12) NOT NULL ,"
			//					+ " PortalID VARCHAR(16) NOT NULL ,"
			//					+ " PortalTo VARCHAR(12) NOT NULL ) ";
			//					
			//			stmt.executeUpdate(stmtCreateTable);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(" all done!!!");

	}

	void show_menu() {
		JFrame fr = new JFrame();
		//		JTextField tx1 = new JTextField("myjtext");
		//		JTextField tx2 = new JTextField("myjtext");
		//		JTextField tx3 = new JTextField("myjtext");
		JButton jb1 = new JButton("Add Player");
		JButton jb2 = new JButton("Delete Player");
		JButton jb3 = new JButton("Auto Processing ");
		JButton jb4 = new JButton("Play Turn");
		JButton jb5 = new JButton("Buy");
		JButton jb6 = new JButton("Sell");
		JButton jb7 = new JButton("Transfer to Planet");
		JButton jb8 = new JButton("Build Tables");
		JButton jb9 = new JButton("Delete Tables");
		JButton jb10 = new JButton("Close");

		jb1.addActionListener(this);
		jb2.addActionListener(this);
		jb3.addActionListener(this);
		jb4.addActionListener(this);
		jb5.addActionListener(this);
		jb6.addActionListener(this);
		jb7.addActionListener(this);
		jb8.addActionListener(this);
		jb9.addActionListener(this);
		jb10.addActionListener(this);

	fr.setTitle("Heinz' Frame");
	fr.getContentPane().setLayout(new BorderLayout());

	JPanel rightpanel = new JPanel(new GridLayout(8, 1));
	rightpanel.add(jb1);
	rightpanel.add(jb2);
	rightpanel.add(jb3);
	rightpanel.add(jb4);
	rightpanel.add(jb5);
	rightpanel.add(jb6);
	rightpanel.add(jb7);
	rightpanel.add(jb8);
	rightpanel.add(jb9);
	rightpanel.add(jb10);
	
	
	//fr.getContentPane().add(rightpanel, BorderLayout.EAST);
	fr.getContentPane().add(rightpanel, BorderLayout.CENTER);

	fr.resize(new Dimension(300, 600));
	fr.addWindowListener(new WindowAdapter() {
		public void windowClosing(WindowEvent e) {
			System.exit(0);
		}
	});
	fr.pack();
	fr.setVisible(true);	
	fr.show();

}

void deleteTables() {

	try {

		stmt.executeUpdate("drop table associateplayerwithgame");
		stmt.executeUpdate("drop table cache");
		stmt.executeUpdate("drop table gamedefinition");
		stmt.executeUpdate("drop table hazard");
		stmt.executeUpdate("drop table inventory");
		stmt.executeUpdate("drop table partstatus");
		stmt.executeUpdate("drop table planet");
		stmt.executeUpdate("drop table playeraccount");
		stmt.executeUpdate("drop table ship");
		stmt.executeUpdate("drop table universe");
		stmt.executeUpdate("drop table wormhole");

	} catch (SQLException e) {
		// TODO Auto-generated catch block
		System.out.println("SQLException e:" + e.getMessage());
		//e.printStackTrace();
	}

	System.out.println(" all done!!!");

}

void goBuildTheTables() {

	try {

		stmt.executeUpdate(ap.getStmtCreateTable());
		System.out.println(ap.getStmtCreateTable());

		stmt.executeUpdate(ci.getStmtCreateTable());
		System.out.println(ci.getStmtCreateTable());

		stmt.executeUpdate(gdi.getStmtCreateTable());
		System.out.println(gdi.getStmtCreateTable());

		stmt.executeUpdate(hi.getStmtCreateTable());
		System.out.println(hi.getStmtCreateTable());

		stmt.executeUpdate(ii.getStmtCreateTable());
		System.out.println(ii.getStmtCreateTable());

		stmt.executeUpdate(pp.getStmtCreateTable());
		System.out.println(pp.getStmtCreateTable());

		stmt.executeUpdate(pa.getStmtCreateTable());
		System.out.println(pa.getStmtCreateTable());

		stmt.executeUpdate(si.getStmtCreateTable());
		System.out.println(si.getStmtCreateTable());

		stmt.executeUpdate(st.getStmtCreateTable());
		System.out.println(st.getStmtCreateTable());

		stmt.executeUpdate(ui.getStmtCreateTable());
		System.out.println(ui.getStmtCreateTable());

		stmt.executeUpdate(wh.getStmtCreateTable());
		System.out.println(wh.getStmtCreateTable());

	} catch (SQLException e) {
		// TODO Auto-generated catch block
		System.out.println("SQLException e:" + e.getMessage());
		//e.printStackTrace();
	}

	System.out.println(" all done!!!");

}

/* (non-Javadoc)
 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
 */
 
public void actionPerformed(ActionEvent e) {
	
	// TODO Auto-generated method stub
	if ((e.getActionCommand()).equals("Add Player")) {
		System.out.println("Add Player");
	} else if ((e.getActionCommand()).equals("Delete Player")) {
		System.out.println("Delete Player");
	} else if ((e.getActionCommand()).equals("Auto Processing")) {
		System.out.println("Auto Processing");
	} else if ((e.getActionCommand()).equals("Play Turn")) {
		System.out.println("Play Turn");
	} else if ((e.getActionCommand()).equals("Buy")) {
		System.out.println("Buy");
	} else if ((e.getActionCommand()).equals("Sell")) {
		System.out.println("Sell");
	} else if ((e.getActionCommand()).equals("Transfer to Planet")) {
		System.out.println("Transfer to Planet");
	} else if ((e.getActionCommand()).equals("Build Tables")) {
		System.out.println("Build Player");
	} else if ((e.getActionCommand()).equals("Delete Tables")) {
		System.out.println("Delete Tables");
	} else if ((e.getActionCommand()).equals("Close")) {
		System.exit(0);
	}

}

class myAdapter extends MouseAdapter {
	public void mouseClicked(MouseEvent e) { //	   blah
	} // end mouseClicked
}

}
package no.ntnu.fp.integrationTests;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import junit.framework.TestCase;
import no.ntnu.fp.gui.FactoryProjectPanel;
import no.ntnu.fp.gui.NewVehiclePanel;
import no.ntnu.fp.model.Ecu;
import no.ntnu.fp.model.FactoryProject;
import no.ntnu.fp.model.Vehicle;
import no.ntnu.fp.storage.InsertTestDataFactoryDb;
import no.ntnu.fp.storage.VehicleDbStorage;
import no.ntnu.fp.model.XMLServer;

public class SoftwareDbStorage_Neighbourhood extends TestCase {
	public void setUp() throws Exception {
		super.setUp();
		System.out.println("Setting Up...");
		InsertTestDataFactoryDb.main(null);
	}
	
	public void testNewestEcuFalse() {
		// Prepare
		Vehicle v = new Vehicle();
		v.setVehicleID("1");
		v.setHistoryLog("test history log");
		v.setSeries("123");
		Ecu e1 = new Ecu(1, 1, 1);
		v.addEcu(e1);
		
		// Execute
		XMLServer s = new XMLServer();
		String res = s.vehicleObjToXML(v);
		
		// Assert
		assertEquals(res, "<?xml version=\"1.0\"?>\n<vehicle><vehicleId>1</vehicleId><series>123</series><historyLog>test history log</historyLog><ecus><ecu><ecuId>1</ecuId><swId>1</swId><subSwId>1</subSwId><newestSw>false</newestSw><newestSub>2</newestSub></ecu></ecus></vehicle>\n");
	}
	
	public void testNewestEcuTrue() {
		// Prepare
		Vehicle v = new Vehicle();
		v.setVehicleID("1");
		v.setHistoryLog("test history log");
		v.setSeries("123");
		Ecu e1 = new Ecu(1);
		v.addEcu(e1);
		
		// Execute
		XMLServer s = new XMLServer();
		String res = s.vehicleObjToXML(v);
		
		// Assert
		assertEquals(res, "<?xml version=\"1.0\"?>\n<vehicle><vehicleId>1</vehicleId><series>123</series><historyLog>test history log</historyLog><ecus><ecu><ecuId>1</ecuId><swId>0</swId><subSwId>0</subSwId><newestSw>true</newestSw><newestSub>0</newestSub></ecu></ecus></vehicle>\n");
	}
	
	public void testAddEdu() {
		// Prepare
		VehicleDbStorage s = new VehicleDbStorage();
		Vehicle v = new Vehicle();
		v.setVehicleID("1");
		v.setHistoryLog("test history log");
		v.setSeries("123");
		Ecu e = new Ecu(1, 10, 10);
		v.addEcu(e);
		FactoryProjectPanel fPanel = new FactoryProjectPanel();
		NewVehiclePanel panel = new NewVehiclePanel(fPanel);
		panel.setModel(v);
		
		// Execute
		ArrayList<String> res = s.addEcus(v,  panel); //was var
		
		// Assert
		ArrayList<Ecu> e2 = getEcus(1);
		assertEquals(4, e2.size());
		assertEquals(1, e2.get(3).getEcuId());
		assertEquals(0, e2.get(3).getSubSwId());
		assertEquals(10, e2.get(3).getSwId());
	}
	
	private ArrayList<Ecu> getEcus(int vehID) {

		ArrayList<Ecu> eculist = new ArrayList<Ecu>();
		try {
			Connection connection = getConnection();
			Statement statement = connection.createStatement();
			String sql = "SELECT ecu_no,sw_version,sub_version FROM installed_ecus "
					+ "WHERE vehicle_id = " + vehID;

			ResultSet rs = statement.executeQuery(sql);

			while (rs.next()) {

				eculist.add(new Ecu(rs.getInt(1), rs.getInt(2), rs.getInt(3)));
			}
			rs.close();
			statement.close();
			connection.close();
		} catch (SQLException e) {
			System.err.println("SQL-klikk i getEcus: "+ e);
		}
		return eculist;
	}
	
	private Connection getConnection() throws SQLException {
		Connection connection = null;
        try {
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
			String dbURL = "jdbc:derby:FactoryDB;";
			 connection = DriverManager.getConnection(dbURL);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return connection;
	}
}
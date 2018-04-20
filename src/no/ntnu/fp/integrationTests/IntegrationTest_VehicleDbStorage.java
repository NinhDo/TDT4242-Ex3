package no.ntnu.fp.integrationTests;

import junit.framework.TestCase;
import no.ntnu.fp.gui.FactoryProjectPanel;
import no.ntnu.fp.gui.NewVehiclePanel;
import no.ntnu.fp.model.Vehicle;
import no.ntnu.fp.storage.VehicleDbStorage;

import java.util.ArrayList;

public class IntegrationTest_VehicleDbStorage extends TestCase {
	private VehicleDbStorage dbStorage;
	private Vehicle vehicle;
	private String dummyId;
	private NewVehiclePanel panel;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		dbStorage = new VehicleDbStorage();
		dummyId = "" + (Integer.parseInt(dbStorage.openVehicles().get(dbStorage.openVehicles().size() - 1).getVehicleID()) + 1);
		vehicle = new Vehicle(dummyId, "Created", new ArrayList(), dummyId);
		panel = new NewVehiclePanel(new FactoryProjectPanel());
		panel.setModel(vehicle);
	}

	public void testAddVehicleToDb() {
		assertEquals(dummyId, vehicle.getVehicleID());
		assertEquals("Created", vehicle.getHistoryLog());
		assertEquals(dummyId, vehicle.getSeries());

		dbStorage.addVehicle(vehicle, panel);
		ArrayList<Vehicle> list = dbStorage.openVehicles();
		assertEquals(vehicle.getVehicleID(), list.get(list.size() - 1).getVehicleID());
		assertEquals(vehicle.getHistoryLog(), list.get(list.size() - 1).getHistoryLog());
		assertEquals(vehicle.getSeries(), list.get(list.size() - 1).getSeries());
	}
}

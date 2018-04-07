package no.ntnu.fp.model;

import junit.framework.TestCase;

public class FactoryProjectTest extends TestCase {

	public void testGetVehicleCount() {
		FactoryProject factoryProject = new FactoryProject();
		for(int i = 0; i < 10; i++) {
			Vehicle vehicle = new Vehicle();
			factoryProject.addVehicle(vehicle);
		}
		assertEquals(10, factoryProject.getVehicleCount());
	}

	public void testGetSoftwareCount() {
		FactoryProject factoryProject = new FactoryProject();
		for(int i = 0; i < 10; i++) {
			Software software = new Software();
			factoryProject.addSoftware(software);
		}
		assertEquals(10, factoryProject.getSoftwareCount());
	}

	public void testGetVehicle() {
		FactoryProject factoryProject = new FactoryProject();
		Vehicle vehicle = new Vehicle();
		factoryProject.addVehicle(vehicle);
		assertEquals(vehicle, factoryProject.getVehicle(0));
	}

	public void testGetSoftware() {
		FactoryProject factoryProject = new FactoryProject();
		Software software = new Software();
		factoryProject.addSoftware(software);
		assertEquals(software, factoryProject.getSoftware(0));
	}

	public void testGetEcuCount() {
		FactoryProject factoryProject = new FactoryProject();
		for(int i = 0; i < 10; i++) {
			SimpleEcu ecu = new SimpleEcu(i);
			factoryProject.addEcu(ecu);
		}
		assertEquals(10, factoryProject.getEcuCount());
	}

	public void testGetLatestSoftware() {
		FactoryProject factoryProject = new FactoryProject();
		Software software = new Software();
		factoryProject.addSoftware(software);
		assertEquals(software, factoryProject.getLatestSoftware());
	}

	public void testGetLatestVehicle() {
		FactoryProject factoryProject = new FactoryProject();
		Vehicle vehicle = new Vehicle();
		factoryProject.addVehicle(vehicle);
		assertEquals(vehicle, factoryProject.getLatestVehicle());
	}

	public void testGetLatestEcu() {
		FactoryProject factoryProject = new FactoryProject();
		SimpleEcu ecu = new SimpleEcu();
		factoryProject.addEcu(ecu);
		assertEquals(ecu, factoryProject.getLatestEcu());
	}

	public void testGetSoftwareIndex() {
		FactoryProject factoryProject = new FactoryProject();
		Software firstSoftware = new Software();
		Software secondSoftware = new Software();
		Software thirdSoftware = new Software();
		factoryProject.addSoftware(firstSoftware);
		factoryProject.addSoftware(secondSoftware);
		factoryProject.addSoftware(thirdSoftware);
		assertEquals(1, factoryProject.getSoftwareIndex(secondSoftware));
	}

	public void testGetEcu() {
		FactoryProject factoryProject = new FactoryProject();
		SimpleEcu firstEcu = new SimpleEcu();
		SimpleEcu secondEcu = new SimpleEcu();
		SimpleEcu thirdEcu = new SimpleEcu();
		factoryProject.addEcu(firstEcu);
		factoryProject.addEcu(secondEcu);
		factoryProject.addEcu(thirdEcu);
		assertEquals(secondEcu, factoryProject.getEcu(1));
	}

	public void testGetVehicleIndex() {
		FactoryProject factoryProject = new FactoryProject();
		Vehicle firstVehicle = new Vehicle();
		firstVehicle.setVehicleID("first");
		Vehicle secondVehicle = new Vehicle();
		secondVehicle.setVehicleID("second");
		Vehicle thirdVehicle = new Vehicle();
		thirdVehicle.setVehicleID("third");
		factoryProject.addVehicle(firstVehicle);
		factoryProject.addVehicle(secondVehicle);
		factoryProject.addVehicle(thirdVehicle);
		assertEquals(1, factoryProject.getVehicleIndex("second"));
	}

	public void testIndexOf() {
		FactoryProject factoryProject = new FactoryProject();
		Vehicle firstVehicle = new Vehicle();
		Vehicle secondVehicle = new Vehicle();
		Vehicle thirdVehicle = new Vehicle();
		factoryProject.addVehicle(firstVehicle);
		factoryProject.addVehicle(secondVehicle);
		factoryProject.addVehicle(thirdVehicle);
		assertEquals(1, factoryProject.indexOf(secondVehicle));
	}

	public void testIterator() {

	}

	public void testAddVehicle() {
		FactoryProject factoryProject = new FactoryProject();
		Vehicle vehicle = new Vehicle();
		factoryProject.addVehicle(vehicle);
		assertEquals(1, factoryProject.getVehicleCount());
	}

	public void testAddSoftware() {
		FactoryProject factoryProject = new FactoryProject();
		Software software = new Software();
		factoryProject.addSoftware(software);
		assertEquals(1, factoryProject.getSoftwareCount());
	}

	public void testAddEcu() {
		FactoryProject factoryProject = new FactoryProject();
		SimpleEcu ecu = new SimpleEcu();
		factoryProject.addEcu(ecu);
		assertEquals(1, factoryProject.getEcuCount());
	}

	public void testRemoveVehicle() {

		FactoryProject factoryProject = new FactoryProject();
		Vehicle vehicle = new Vehicle();
		factoryProject.addVehicle(vehicle);
		assertEquals(1, factoryProject.getVehicleCount());
		factoryProject.removeVehicle(vehicle);
		assertEquals(0, factoryProject.getVehicleCount());
	}

	public void testAddPropertyChangeListener() {
	}

	public void testRemovePropertyChangeListener() {
	}

	public void testPropertyChange() {
	}

	@SuppressWarnings({"SimplifiableJUnitAssertion", "EqualsWithItself", "EqualsBetweenInconvertibleTypes"})
	public void testEquals() {
		FactoryProject factoryProject = new FactoryProject();
		FactoryProject otherFactoryProject = new FactoryProject();
		factoryProject.addVehicle(new Vehicle());
		otherFactoryProject.addVehicle(new Vehicle());
		assertFalse(factoryProject.equals(otherFactoryProject));
		assertTrue(factoryProject.equals(factoryProject));
		assertFalse(factoryProject.equals(new Vehicle()));

		assertTrue(factoryProject.equals(factoryProject));
	}

	public void testToString() {
	}
}
package no.ntnu.fp.model;

import junit.framework.TestCase;

import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Iterator;

public class FactoryProjectTest extends TestCase {
	private FactoryProject factoryProject;

	public void setUp() {
		System.out.println("Setting Up...");
		factoryProject = new FactoryProject();
	}

	public void tearDown() {
		System.out.println("Tearing Down...");
		factoryProject = null;
	}

	public void testFactoryProject() {
			factoryProject = new FactoryProject(new ArrayList(), new ArrayList(), new ArrayList<>());
			assertNotNull(factoryProject);
	}

	public void testVehicleCount() {
		for(int i = 0; i < 10; i++) {
			Vehicle vehicle = new Vehicle();
			factoryProject.addVehicle(vehicle);
			assertEquals(i+1, factoryProject.getVehicleCount());
		}
		assertEquals(10, factoryProject.getVehicleCount());
	}

	public void testShouldHaveTenSoftwares() {
		for(int i = 0; i < 10; i++) {
			Software software = new Software();
			factoryProject.addSoftware(software);
			assertEquals(i+1, factoryProject.getSoftwareCount());
		}
		assertEquals(10, factoryProject.getSoftwareCount());
	}

	public void testShouldGetVehicle() {
		Vehicle vehicle = new Vehicle();
		factoryProject.addVehicle(vehicle);
		assertEquals(vehicle, factoryProject.getVehicle(0));
	}

	public void testShouldGetSoftware() {
		Software software = new Software();
		factoryProject.addSoftware(software);
		assertEquals(software, factoryProject.getSoftware(0));
	}

	public void testShouldHaveTenEcus() {
		for(int i = 0; i < 10; i++) {
			SimpleEcu ecu = new SimpleEcu(i);
			factoryProject.addEcu(ecu);
			assertEquals(i+1, factoryProject.getEcuCount());
		}
		assertEquals(10, factoryProject.getEcuCount());
	}

	public void testShouldGetLatestSoftware() {
		Software oldSoftware = new Software();
		Software newSoftware = new Software();
		factoryProject.addSoftware(oldSoftware);
		factoryProject.addSoftware(newSoftware);
		assertEquals(newSoftware, factoryProject.getLatestSoftware());
	}

	public void testShouldGetLatestVehicle() {
		Vehicle oldVehicle = new Vehicle();
		Vehicle newVehicle = new Vehicle();
		factoryProject.addVehicle(oldVehicle);
		factoryProject.addVehicle(newVehicle);
		assertEquals(newVehicle, factoryProject.getLatestVehicle());
	}

	public void testShouldGetLatestEcu() {
		SimpleEcu oldEcu = new SimpleEcu();
		SimpleEcu newEcu = new SimpleEcu();
		factoryProject.addEcu(oldEcu);
		factoryProject.addEcu(newEcu);
		assertEquals(newEcu, factoryProject.getLatestEcu());
	}

	public void testShouldGetCorrectSoftwareIndex() {
		Software firstSoftware = new Software();
		Software secondSoftware = new Software();
		Software thirdSoftware = new Software();
		factoryProject.addSoftware(firstSoftware);
		factoryProject.addSoftware(secondSoftware);
		factoryProject.addSoftware(thirdSoftware);
		assertEquals(1, factoryProject.getSoftwareIndex(secondSoftware));
	}

	public void testShouldGetCorrectEcu() {
		SimpleEcu firstEcu = new SimpleEcu();
		SimpleEcu secondEcu = new SimpleEcu();
		SimpleEcu thirdEcu = new SimpleEcu();
		factoryProject.addEcu(firstEcu);
		factoryProject.addEcu(secondEcu);
		factoryProject.addEcu(thirdEcu);
		assertEquals(secondEcu, factoryProject.getEcu(1));
	}

	public void testShouldGetCorrectVehicleIndex() {
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
		assertEquals(-1, factoryProject.getVehicleIndex("fourth"));
	}

	public void testShouldGetCorrectIndexOfVehicle() {
		Vehicle firstVehicle = new Vehicle();
		Vehicle secondVehicle = new Vehicle();
		Vehicle thirdVehicle = new Vehicle();
		factoryProject.addVehicle(firstVehicle);
		factoryProject.addVehicle(secondVehicle);
		factoryProject.addVehicle(thirdVehicle);
		assertEquals(1, factoryProject.indexOf(secondVehicle));
	}

	public void testIteratorShouldNotHaveHasNext() {
		Iterator iterator = factoryProject.iterator();
		assertFalse(iterator.hasNext());
	}

	public void testIteratorShouldNotHaveNext() {
		Iterator iterator = factoryProject.iterator();
		try {
			iterator.next();
			fail(); // Should not get here
		} catch (Exception e) {
			// Exception is expected.
		}
	}

	public void testIteratorShouldHaveHasNext() {
		Iterator iterator = factoryProject.iterator();
		factoryProject.addVehicle(new Vehicle());
		assertTrue(iterator.hasNext());
		assertTrue(iterator.hasNext());
		assertTrue(iterator.hasNext());
	}

	public void testIteratorShouldHaveNext() {
		Vehicle vehicle = new Vehicle();
		factoryProject.addVehicle(vehicle);
		Iterator iterator = factoryProject.iterator();
		try {
			iterator.next();
		} catch (Exception e) {
			fail();
		}
	}


	public void testAddVehicle() {
		Vehicle vehicle = new Vehicle();
		factoryProject.addVehicle(vehicle);
		assertEquals(1, factoryProject.getVehicleCount());
	}

	public void testAddSoftware() {
		Software software = new Software();
		factoryProject.addSoftware(software);
		assertEquals(1, factoryProject.getSoftwareCount());
	}

	public void testAddEcu() {
		SimpleEcu ecu = new SimpleEcu();
		factoryProject.addEcu(ecu);
		assertEquals(1, factoryProject.getEcuCount());
	}

	public void testRemoveVehicle() {
		Vehicle vehicle = new Vehicle();
		factoryProject.addVehicle(vehicle);
		assertEquals(1, factoryProject.getVehicleCount());
		factoryProject.removeVehicle(vehicle);
		assertEquals(0, factoryProject.getVehicleCount());
	}

	public void testAddPropertyChangeListener() {
		final JButton button = new JButton("For testing");
		button.setEnabled(false);
		PropertyChangeListener listener = evt -> button.setEnabled(true);
		assertNotNull(listener);
		factoryProject.addPropertyChangeListener(listener);
		factoryProject.addVehicle(new Vehicle());
		assertTrue(button.isEnabled());
	}

	public void testRemovePropertyChangeListener() {
		final JButton button = new JButton("For testing");
		button.setEnabled(false);
		PropertyChangeListener listener = evt -> button.setEnabled(!button.isEnabled());
		assertNotNull(listener);
		factoryProject.addPropertyChangeListener(listener);
		factoryProject.addVehicle(new Vehicle()); // Should be true
		factoryProject.removePropertyChangeListener(listener);
		factoryProject.addVehicle(new Vehicle()); // If listener was still attached, it should be false.
		assertTrue(button.isEnabled()); // Should still be true
	}

	public void testPropertyChange() {
		Vehicle v = new Vehicle();
		v.setVehicleID("oldId");
		PropertyChangeListener listener = evt -> v.setVehicleID((String) evt.getNewValue());
		assertNotNull(listener);
		factoryProject.addPropertyChangeListener(listener);
		assertEquals("oldId", v.getVehicleID());
		factoryProject.propertyChange(new PropertyChangeEvent(this, "vehicleId", v.getVehicleID(), "newId"));
		assertEquals("newId", v.getVehicleID());
	}

	@SuppressWarnings({"SimplifiableJUnitAssertion", "EqualsWithItself", "EqualsBetweenInconvertibleTypes"})
	public void testEquals() {
		FactoryProject otherFactoryProject = new FactoryProject();
		factoryProject.addVehicle(new Vehicle());
		otherFactoryProject.addVehicle(new Vehicle());
		assertFalse(factoryProject.equals(otherFactoryProject));
		assertTrue(factoryProject.equals(factoryProject));
		assertFalse(factoryProject.equals(new Vehicle()));
		assertTrue(factoryProject.equals(factoryProject));
		otherFactoryProject.addVehicle(new Vehicle());
		assertFalse(factoryProject.equals(otherFactoryProject));

		factoryProject = new FactoryProject();
		otherFactoryProject = new FactoryProject();
		Vehicle v = new Vehicle();
		factoryProject.addVehicle(v);
		otherFactoryProject.addVehicle(v);
		assertTrue(factoryProject.equals(otherFactoryProject));
	}

	public void testToString() {
		Vehicle v1 = new Vehicle();
		assertNotNull(v1);
		factoryProject.addVehicle(v1);
		assertEquals(factoryProject.toString(), "project:\n" + v1.toString() + "\n");
	}
}
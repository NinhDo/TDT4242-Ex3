package no.ntnu.fp.model;

import junit.framework.TestCase;

import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class VehicleTest extends TestCase {
	private Vehicle vehicle;

	public void setUp() throws Exception {
		super.setUp();
		System.out.println("Setting Up...");
		vehicle = new Vehicle();
	}

	public void tearDown() throws Exception {
		super.tearDown();
		System.out.println("Tearing Down...");
		vehicle = null;
	}

	public void testVehicleConstructor() {
		ArrayList arrayList = new ArrayList();
		vehicle = new Vehicle("vehicleId", "historyLog", arrayList, "series");
		assertNotNull(vehicle);
		assertEquals("vehicleId", vehicle.getVehicleID());
		assertEquals("historyLog", vehicle.getHistoryLog());
		assertEquals(arrayList, vehicle.getEcus());
		assertEquals("series", vehicle.getSeries());
	}

	public void testGetEcuCount() {
		assertEquals(0, vehicle.getEcuCount());
		vehicle.addEcu(new Ecu(0));
		assertEquals(1, vehicle.getEcuCount());
	}

	public void testGetEcu() {
		Ecu ecu1 = new Ecu(0);
		Ecu ecu2 = new Ecu(1);
		vehicle.addEcu(ecu1);
		vehicle.addEcu(ecu2);
		assertEquals(ecu1, vehicle.getEcu(0));
	}

	public void testAddEcu() {
		Ecu ecu1 = new Ecu(0);
		Ecu ecu2 = new Ecu(1);
		vehicle.addEcu(ecu1);
		assertEquals(1, vehicle.getEcuCount());
		vehicle.addEcu(ecu2);
		assertEquals(2, vehicle.getEcuCount());
	}

	public void testRemoveEcu() {
		Ecu ecu1 = new Ecu(0);
		Ecu ecu2 = new Ecu(1);
		vehicle.addEcu(ecu1);
		assertEquals(1, vehicle.getEcuCount());
		vehicle.addEcu(ecu2);
		assertEquals(2, vehicle.getEcuCount());
		vehicle.removeEcu(ecu1);
		assertEquals(1, vehicle.getEcuCount());
		vehicle.removeEcu(ecu2);
		assertEquals(0, vehicle.getEcuCount());
	}

	public void testGetLargestEcuId() {
		assertEquals(0, vehicle.getLargestEcuId());
		Ecu ecu1 = new Ecu(0);
		Ecu ecu2 = new Ecu(1);
		Ecu ecu3 = new Ecu(2);
		vehicle.addEcu(ecu1);
		vehicle.addEcu(ecu2);
		vehicle.addEcu(ecu3);
		assertEquals(2, vehicle.getLargestEcuId());
	}

	public void testContains() {
		Ecu ecu1 = new Ecu(0);
		Ecu ecu2 = new Ecu(1);
		vehicle.addEcu(ecu1);
		assertTrue(vehicle.contains(ecu1));
		assertFalse(vehicle.contains(ecu2));
	}

	public void testAddPropertyChangeListener() {
		final JButton button = new JButton("For testing");
		button.setEnabled(false);
		PropertyChangeListener listener = evt -> button.setEnabled(true);
		vehicle.addPropertyChangeListener(listener);
		assertFalse(button.isEnabled());
		vehicle.setVehicleID("1");
		assertTrue(button.isEnabled());
	}

	public void testRemovePropertyChangeListener() {
		final JButton button = new JButton("For testing");
		button.setEnabled(false);
		PropertyChangeListener listener = evt -> button.setEnabled(true);
		vehicle.addPropertyChangeListener(listener);
		assertFalse(button.isEnabled());
		vehicle.setVehicleID("1");
		assertTrue(button.isEnabled()); // should be true
		vehicle.removePropertyChangeListener(listener);
		vehicle.setVehicleID("1");
		assertTrue(button.isEnabled()); // should still be true
	}

	public void testPropertyChange() {
		vehicle.setVehicleID("oldId");
		PropertyChangeListener listener = evt -> vehicle.setVehicleID((String)evt.getNewValue());
		vehicle.addPropertyChangeListener(listener);
		PropertyChangeEvent event = new PropertyChangeEvent(this, "vehicleId", vehicle.getVehicleID(), "newId");
		vehicle.propertyChange(event);
		assertEquals("newId", vehicle.getVehicleID());

	}

	public void testGetVehicleID() {
		vehicle.setVehicleID("id");
		assertEquals("id", vehicle.getVehicleID());
	}

	public void testFindDot() {
		String text = "123.abc";
		String text2 = "123abc";
		String text3 = "123.abc.456";
		assertEquals(3, vehicle.findDot(text));
		assertEquals(-1, vehicle.findDot(text2));
		assertEquals(3, vehicle.findDot(text3));
	}

	public void testSetVehicleID() {
		vehicle.setVehicleID("id");
		assertEquals("id", vehicle.getVehicleID());
	}

	public void testGetHistoryLog() {
		vehicle.setHistoryLog("NewHistoryLog");
		assertEquals("NewHistoryLog", vehicle.getHistoryLog());
	}

	public void testSetHistoryLog() {
		vehicle.setHistoryLog("NewHistoryLog");
		assertEquals("NewHistoryLog", vehicle.getHistoryLog());
		vehicle.setHistoryLog("NewerHistoryLog");
		assertEquals("NewerHistoryLog", vehicle.getHistoryLog());
	}

	public void testGetEcus() {
		Ecu ecu1 = new Ecu(0);
		Ecu ecu2 = new Ecu(1);
		Ecu ecu3 = new Ecu(2);
		Ecu ecu4 = new Ecu(3);
		vehicle.addEcu(ecu1);
		vehicle.addEcu(ecu2);
		vehicle.addEcu(ecu3);
		vehicle.addEcu(ecu4);
		ArrayList<Ecu> arrayList = new ArrayList<>();
		arrayList.add(ecu1);
		arrayList.add(ecu2);
		arrayList.add(ecu3);
		arrayList.add(ecu4);
		assertEquals(arrayList, vehicle.getEcus());
	}

	public void testSetEcus() {
		Ecu ecu1 = new Ecu(0);
		Ecu ecu2 = new Ecu(1);
		Ecu ecu3 = new Ecu(2);
		Ecu ecu4 = new Ecu(3);
		ArrayList<Ecu> arrayList = new ArrayList<>();
		arrayList.add(ecu1);
		arrayList.add(ecu2);
		arrayList.add(ecu3);
		arrayList.add(ecu4);
		vehicle.setEcus(arrayList);
		assertEquals(arrayList, vehicle.getEcus());
	}

	public void testIndexOf() {
		Ecu ecu1 = new Ecu(0);
		Ecu ecu2 = new Ecu(1);
		Ecu ecu3 = new Ecu(2);
		Ecu ecu4 = new Ecu(3);
		ArrayList<Ecu> arrayList = new ArrayList<>();
		arrayList.add(ecu1);
		arrayList.add(ecu2);
		arrayList.add(ecu3);
		arrayList.add(ecu4);
		vehicle.setEcus(arrayList);
		assertEquals(0, vehicle.indexOf(ecu1));
		assertEquals(1, vehicle.indexOf(ecu2));
		assertEquals(2, vehicle.indexOf(ecu3));
		assertEquals(3, vehicle.indexOf(ecu4));
	}

	public void testIteratorShouldNotHaveHasNext() {
		Iterator iterator = vehicle.iterator();
		assertFalse(iterator.hasNext());
	}

	public void testIteratorShouldNotHaveNext() {
		Iterator iterator = vehicle.iterator();
		try {
			assertNull(iterator.next());
		} catch (NoSuchElementException ignored) {
		}
	}

	public void testIteratorShouldHaveHasNext() {
		vehicle.addEcu(new Ecu(0));
		Iterator iterator = vehicle.iterator();
		assertTrue(iterator.hasNext());
		assertTrue(iterator.hasNext());
		assertTrue(iterator.hasNext());
	}

	public void testIteratorShouldHaveNext() {
		vehicle.addEcu(new Ecu(0));
		Iterator iterator = vehicle.iterator();

		try {
			assertNotNull(iterator.next());
		} catch (Exception e) {
			fail();
		}
	}

	public void testGetSeries() {
		vehicle.setSeries("series");
		assertEquals("series", vehicle.getSeries());
	}

	public void testSetSeries() {
		vehicle.setSeries("series");
		assertEquals("series", vehicle.getSeries());
		vehicle.setSeries("newSeries");
		assertEquals("newSeries", vehicle.getSeries());
	}
}
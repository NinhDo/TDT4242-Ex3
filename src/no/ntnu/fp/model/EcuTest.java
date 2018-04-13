package no.ntnu.fp.model;

import junit.framework.TestCase;

import javax.swing.*;
import java.beans.PropertyChangeListener;

public class EcuTest extends TestCase {
	private Ecu ecu;

	public void setUp() {
		System.out.println("Setting Up...");
		ecu = new Ecu(1);
	}

	public void tearDown() {
		System.out.println("Tearing Down...");
		ecu = null;
	}

	public void testAddPropertyChangeListener() {
		final JButton button1 = new JButton("for testing");

		PropertyChangeListener listener = event -> button1.setEnabled(false);

		ecu.addPropertyChangeListener(listener);
		ecu.setSwId(2);

		assert (!button1.isEnabled());
	}

	public void testRemovePropertyChangeListener() {
		final JButton button1 = new JButton("for testing");

		PropertyChangeListener listener = event -> button1.setEnabled(!button1.isEnabled());

		ecu.addPropertyChangeListener(listener);
		ecu.setSwId(2);
		assert (!button1.isEnabled());
		button1.setEnabled(true);
		ecu.removePropertyChangeListener(listener);
		ecu.setSwId(2);
		assert (button1.isEnabled());
	}

	public void testGetEcuId() {
		assertEquals(1, ecu.getEcuId());
	}

	public void testSetEcuId() {
		ecu.setEcuId(2);
		assertEquals(2, ecu.getEcuId());
	}

	public void testGetSwId() {
		Ecu ecu = new Ecu(1, 1, 1);
		assertNotNull(ecu);
	}

	public void testSetSwId() {
		Ecu ecu = new Ecu(1, 1, 1);
		ecu.setSwId(2);
		assertEquals(2, ecu.getSwId());
	}

	public void testGetSubSwId() {
		Ecu ecu = new Ecu(1, 1, 1);
		assertEquals(1, ecu.getSubSwId());
	}

	public void testSetSubSwId() {
		Ecu ecu = new Ecu(1, 1, 1);
		ecu.setSubSwId(2);
		assertEquals(2, ecu.getSubSwId());
	}

	public void testIsNewest() {
		ecu.setNewest(true);
		assertTrue(ecu.isNewest());
	}

	public void testSetNewest() {
		ecu.setNewest(false);
		assertFalse(ecu.isNewest());
	}

	public void testGetECUID_PROPERTY_NAME() {
		assertEquals("ecuId", Ecu.getECUID_PROPERTY_NAME());
	}

	public void testGetNewestSub() {
		Ecu ecu = new Ecu(1, 1, 1, true, 1);
		assertEquals(1, ecu.getNewestSub());
	}

	public void testSetNewestSub() {
		Ecu ecu = new Ecu(1, 1, 1, true, 1);
		ecu.setNewestSub(2);
		assertEquals(2, ecu.getNewestSub());
	}
}
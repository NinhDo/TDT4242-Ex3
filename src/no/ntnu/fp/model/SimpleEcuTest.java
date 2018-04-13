package no.ntnu.fp.model;

import junit.framework.TestCase;

import javax.swing.*;
import java.beans.PropertyChangeListener;

public class SimpleEcuTest extends TestCase {
	SimpleEcu ecu;

	public void setUp() throws Exception {
		super.setUp();
		System.out.println("Setting Up...");
		ecu = new SimpleEcu();
	}

	public void tearDown() {
		System.out.println("Tearing Down...");
		ecu = null;
	}

	public void testSimpleEcuConstructorOne() {
		ecu = new SimpleEcu(1);
		assertNotNull(ecu);
		assertEquals(1, ecu.getEcuId());
	}

	public void testSimpleEcuConstructorTwo() {
		ecu = new SimpleEcu(1, 1);
		assertNotNull(ecu);
		assertEquals(1, ecu.getEcuId());
		assertEquals(1, ecu.getSwId());
	}

	public void testAddPropertyChangeListener() {
		JButton button = new JButton("For Testing");
		button.setEnabled(false);
		assertFalse(button.isEnabled());
		PropertyChangeListener listener = evt -> button.setEnabled(true);
		assertNotNull(listener);
		ecu.addPropertyChangeListener(listener);
		ecu.setSwId(1);
		assertTrue(button.isEnabled());
	}

	public void testRemovePropertyChangeListener() {
		JButton button = new JButton("For Testing");
		button.setEnabled(false);
		assertFalse(button.isEnabled());
		PropertyChangeListener listener = evt -> button.setEnabled(!button.isEnabled());
		assertNotNull(listener);
		ecu.addPropertyChangeListener(listener);
		ecu.setSwId(1); // Button should be enabled
		ecu.removePropertyChangeListener(listener);
		ecu.setSwId(1); // Button should still be enabled since the listener is removed
		assertTrue(button.isEnabled());
	}

	public void testGetEcuId() {
		assertEquals(0, ecu.getEcuId());
	}

	public void testSetEcuId() {
		ecu.setEcuId(2);
		assertEquals(2, ecu.getEcuId());
	}

	public void testGetSwId() {
		assertEquals(0, ecu.getSwId());
	}

	public void testSetSwId() {
		ecu.setSwId(2);
		assertEquals(2, ecu.getSwId());
	}
}
package no.ntnu.fp.model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JButton;

import junit.framework.TestCase;

public class PersonTest extends TestCase {

	private Person guy;

	public void setUp() throws Exception {
		this.guy = new Person(1);
	}

	public void tearDown() {
		this.guy = null;
	}

	public void testSetName() {
		guy.setName("Juan");
		assertEquals(guy.getName(), "Juan");
	}

	public void testSetEmail() {
		guy.setEmail("email@email.com");
		assertEquals(guy.getEmail(), "email@email.com");
	}

	public void testSetStreet() {
		guy.setStreet("P. Sherman 42 Wallaby Way");
		assertEquals(guy.getStreet(), "P. Sherman 42 Wallaby Way");
	}

	public void testGetName() {
		assertEquals(guy.getName(), "");
		guy.setName("Juan");
		assertEquals(guy.getName(), "Juan");
	}

	public void testGetEmail() {
		assertEquals(guy.getEmail(), "");
		guy.setEmail("email@email.com");
		assertEquals(guy.getEmail(), "email@email.com");
	}

	public void testGetStreet() {
		assertEquals(guy.getStreet(), "");
		guy.setStreet("P. Sherman 42 Wallaby Way");
		assertEquals(guy.getStreet(), "P. Sherman 42 Wallaby Way");
	}

	public void testGetId() {
		assertNotNull(guy.getId());
		assert (guy.getId() <= System.currentTimeMillis());
	}

	public void testAddPropertyChangeListener() {
		final JButton button1 = new JButton("for testing");

		PropertyChangeListener listener = new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent event) {
				button1.setEnabled(false);
			};
		};

		guy.addPropertyChangeListener(listener);
		guy.setName("Pedro");

		assert (!button1.isEnabled());
	}

	public void testRemovePropertyChangeListener() {
		final JButton button1 = new JButton("for testing");

		PropertyChangeListener listener = new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent event) {
				button1.setEnabled(false);
			};
		};

		guy.addPropertyChangeListener(listener);
		guy.setName("Pedro");

		assert (!button1.isEnabled());
		button1.setEnabled(true);
		guy.removePropertyChangeListener(listener);
		guy.setName("Jose");
		assert (button1.isEnabled());
	}

	public void testEqualsObject() {
		assert (guy.equals(guy));
		assert (!guy.equals(true));

		Person guy2 = new Person(2);
		guy.setName("Juan");
		guy.setEmail("email@email.com");
		guy.setStreet("P. Sherman 42 Wallaby Way");
		assert (!guy.equals(guy2));

		guy2.setName("Juan");
		assert (!guy.equals(guy2));

		guy2.setEmail("email@email.com");
		assert (!guy.equals(guy2));

		guy2.setStreet("P. Sherman 42 Wallaby Way");
		assert (guy.equals(guy2));
	}

	public void testToString() {
		Person guy2 = new Person(2, "Juan", "email@email.com", "P. Sherman 42 Wallaby Way", "Santiago", "FASCDF");

		assertEquals(guy2.toString(), "Name: Juan; Email: email@email.com; Street: P. Sherman 42 Wallaby Way");
	}

	public void testGetCity() {
		assertEquals(guy.getCity(), "");
		guy.setCity("Santiago");
		assertEquals(guy.getCity(), "Santiago");
	}

	public void testSetCity() {
		guy.setCity("Santiago");
		assertEquals(guy.getCity(), "Santiago");
	}

	public void testGetVehicleID() {
		assertEquals(guy.getVehicleID(), "");
		guy.setVehicleID("FHXVSD");
		assertEquals(guy.getVehicleID(), "FHXVSD");
	}

	public void testSetVehicleID() {
		guy.setVehicleID("FHXVSD");
		assertEquals(guy.getVehicleID(), "FHXVSD");
	}

	public void testGetCustId() {
		assertEquals(guy.getCustId(), 1);
	}

	public void testSetCustId() {
		assertEquals(guy.getCustId(), 1);
		guy.setCustId(3);
		assertEquals(guy.getCustId(), 3);
	}

}

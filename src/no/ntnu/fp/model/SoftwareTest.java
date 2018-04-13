package no.ntnu.fp.model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JButton;

import junit.framework.TestCase;

public class SoftwareTest extends TestCase {

	private Software software;

	public void setUp() throws Exception {
		this.software = new Software();
	}

	public void tearDown() {
		this.software = null;
	}

	public void testSoftwareIntIntString() {
		Software software2 = new Software(1, 2, "www.google.cl");
		assertEquals(software2.getSwVersion(), 1);
		assertEquals(software2.getMinorVersion(), 2);
		assertEquals(software2.getUrl(), "www.google.cl");
	}

	public void testAddPropertyChangeListener() {
		final JButton button1 = new JButton("for testing");

		PropertyChangeListener listener = new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent event) {
				button1.setEnabled(false);
			};
		};

		software.addPropertyChangeListener(listener);
		software.setUrl("www.hola.com");
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

		software.addPropertyChangeListener(listener);
		software.setUrl("www.hola.com");
		assert (!button1.isEnabled());
		button1.setEnabled(true);
		software.removePropertyChangeListener(listener);
		software.setUrl("www.algo.cl");
		assert (button1.isEnabled());
	}

	public void testGetSetSwVersion() {
		assertEquals(software.getSwVersion(), 0);
		software.setSwVersion(1);
		assertEquals(software.getSwVersion(), 1);
	}

	public void testGetSetMinorVersion() {
		assertEquals(software.getMinorVersion(), 0);
		software.setMinorVersion(1);
		assertEquals(software.getMinorVersion(), 1);
	}

	public void testGetSetUrl() {
		assertEquals(software.getUrl(), "");
		software.setUrl("www.google.cl");
		assertEquals(software.getUrl(), "www.google.cl");
	}

}

package no.ntnu.fp.model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Iterator;

import javax.swing.JButton;

import junit.framework.TestCase;

public class ProjectTest extends TestCase {

	private Project project;

	public void setUp() throws Exception {
		this.project = new Project();
	}

	public void tearDown() {
		this.project = null;
	}

	public void testAddPerson() {
		Person guy = new Person(2, "Juan", "email@email.com", "P. Sherman 42 Wallaby Way", "Santiago", "FASCDF");
		project.addPerson(guy);
		assert (guy.equals(project.getPerson(0)));
	}

	public void testGetPersonCount() {
		Person guy = new Person(1);
		Person guy2 = new Person(2);

		project.addPerson(guy);
		assertEquals(project.getPersonCount(), 1);

		project.addPerson(guy2);
		assertEquals(project.getPersonCount(), 2);
	}

	public void testGetLargestCustId() {
		assertEquals(project.getLargestCustId(), 0);

		Person guy = new Person(1);
		Person guy2 = new Person(3);
		Person guy3 = new Person(2);

		project.addPerson(guy);
		project.addPerson(guy2);
		assertEquals(project.getLargestCustId(), 3);

		project.addPerson(guy3);
		assertEquals(project.getLargestCustId(), 3);
	}

	public void testGetPerson() {
		Person guy = new Person(1);
		Person guy2 = new Person(3);

		project.addPerson(guy);
		project.addPerson(guy2);
		assert (guy.equals(project.getPerson(0)));
		assert (guy2.equals(project.getPerson(1)));
	}

	public void testGetPersonIndexString() {
		assertEquals(project.getPersonIndex("Pedro").size(), 0);

		Person guy = new Person(2, "Juan", "email@email.com", "P. Sherman 42 Wallaby Way", "Santiago", "FASCDF");
		Person guy2 = new Person(3, "Pedro", "email@email.com", "P. Sherman 42 Wallaby Way", "Santiago", "FASCDF");

		project.addPerson(guy);
		project.addPerson(guy2);

		assertEquals(project.getPersonIndex("Pedro").get(0), (Integer) 1);
	}

	public void testGetPersonIndexInt() {
		assertEquals(project.getPersonIndex(2), -1);

		Person guy = new Person(2, "Juan", "email@email.com", "P. Sherman 42 Wallaby Way", "Santiago", "333");
		Person guy2 = new Person(3, "Pedro", "email@email.com", "P. Sherman 42 Wallaby Way", "Santiago", "444");

		project.addPerson(guy);
		project.addPerson(guy2);

		assertEquals(project.getPersonIndex(444), 1);
	}

	public void testIndexOf() {
		Person guy = new Person(2, "Juan", "email@email.com", "P. Sherman 42 Wallaby Way", "Santiago", "333");
		Person guy2 = new Person(3, "Pedro", "email@email.com", "P. Sherman 42 Wallaby Way", "Santiago", "444");
		assertEquals(project.indexOf(guy), -1);

		project.addPerson(guy);
		project.addPerson(guy2);
		assertEquals(project.indexOf(guy), 0);
		assertEquals(project.indexOf(guy2), 1);
	}

	public void testIteratorShouldNotHaveHasNext() {
		Iterator iterator = project.iterator();
		assertFalse(iterator.hasNext());
	}

	public void testIteratorShouldNotHaveNext() {
		Iterator iterator = project.iterator();
		try {
			iterator.next();
			fail(); // Should not get here
		} catch (Exception e) {
			// Exception is expected.
		}
	}

	public void testIteratorShouldHaveHasNext() {
		Iterator iterator = project.iterator();
		project.addPerson(new Person(1));
		assertTrue(iterator.hasNext());
		assertTrue(iterator.hasNext());
		assertTrue(iterator.hasNext());
	}

	public void testIteratorShouldHaveNext() {
		Person guy = new Person(1);
		project.addPerson(guy);
		Iterator iterator = project.iterator();
		try {
			iterator.next();
		} catch (Exception e) {
			fail();
		}
	}

	public void testContains() {
		Person guy = new Person(1);
		assert (!project.contains(guy));
		project.addPerson(guy);
		assert (project.contains(guy));
	}

	public void testRemovePerson() {
		Person guy = new Person(1);
		assert (!project.contains(guy));
		project.addPerson(guy);
		assert (project.contains(guy));
		project.removePerson(guy);
		assert (!project.contains(guy));
	}

	public void testAddPropertyChangeListener() {
		final JButton button1 = new JButton("for testing");

		PropertyChangeListener listener = new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent event) {
				button1.setEnabled(false);
			};
		};
		Person guy = new Person(1);

		project.addPropertyChangeListener(listener);
		project.addPerson(guy);
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
		Person guy = new Person(1);

		project.addPropertyChangeListener(listener);
		project.addPerson(guy);
		assert (!button1.isEnabled());
		button1.setEnabled(true);
		project.removePropertyChangeListener(listener);
		project.removePerson(guy);
		assert (button1.isEnabled());
	}

	public void testPropertyChange() {
		final JButton button1 = new JButton("for testing");

		PropertyChangeListener listener = new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent event) {
				button1.setEnabled(false);
			};
		};
		Person guy = new Person(1);

		project.addPropertyChangeListener(listener);
		project.propertyChange(new PropertyChangeEvent(this, "person", guy, 0));
		assert (!button1.isEnabled());
	}

	public void testEqualsObject() {
		assert (project.equals(project));
		assert (!project.equals(true));

		Project project2 = new Project();
		Person guy1 = new Person(2, "Juan", "email@email.com", "P. Sherman 42 Wallaby Way", "Santiago", "333");
		Person guy2 = new Person(3, "Pedro", "email@email.com", "P. Sherman 42 Wallaby Way", "Santiago", "444");

		project.addPerson(guy1);
		assert (!project.equals(project2));

		project2.addPerson(guy1);
		assert (project.equals(project2));

		project2.removePerson(guy1);
		project2.addPerson(guy2);
		assert (!project.equals(project2));
	}

	public void testToString() {
		assert (project.toString().equals("project:\n"));

		Person guy = new Person(1);
		project.addPerson(guy);
		String text = project.toString();

		assert (project.toString().equals("project:\nName: ; Email: ; Street: \n"));
	}

}

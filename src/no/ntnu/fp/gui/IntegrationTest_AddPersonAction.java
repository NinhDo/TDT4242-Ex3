package no.ntnu.fp.gui;

import junit.framework.TestCase;
import no.ntnu.fp.model.Project;

import java.awt.event.ActionEvent;

public class IntegrationTest_AddPersonAction extends TestCase {
	Project project;
	PersonListModel plm;
	AddPersonAction action;
	ProjectPanel projectPanel;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		project = new Project();
		plm = new PersonListModel(project, null);
		projectPanel = new ProjectPanel();
		projectPanel.setModel(plm);
		action = new AddPersonAction(projectPanel);

	}

	public void testAddNewCustomer() {
		int personCount = project.getPersonCount();
		action.actionPerformed(new ActionEvent(this, 1, "click"));
		assertEquals(personCount + 1, project.getPersonCount());
 	}
}

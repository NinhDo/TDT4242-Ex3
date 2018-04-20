package no.ntnu.fp.gui;

import junit.framework.TestCase;
import no.ntnu.fp.model.Vehicle;

import java.awt.event.ActionEvent;

public class IntegrationTest_AddEcuAction extends TestCase {
	private EcuListModel ecuListModel;
	private AddEcuAction addEcuAction;

	public IntegrationTest_AddEcuAction() {
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		NWPEcuPanel nwpEcuPanel = new NWPEcuPanel(new NewVehiclePanel(new FactoryProjectPanel()));
		ecuListModel = new EcuListModel(new Vehicle(), null);
		nwpEcuPanel.setModel(ecuListModel);
		addEcuAction = new AddEcuAction(nwpEcuPanel);
	}

	public void testAddEcuAction() {
		int ecuCount = ecuListModel.getProject().getEcuCount();
		addEcuAction.actionPerformed(new ActionEvent(this, 1, "click"));
		assertEquals(ecuCount + 1, ecuListModel.getProject().getEcuCount());
	}
}

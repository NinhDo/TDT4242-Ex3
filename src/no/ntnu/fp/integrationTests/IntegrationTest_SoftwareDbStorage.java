package no.ntnu.fp.integrationTests;

import junit.framework.TestCase;
import no.ntnu.fp.model.Software;
import no.ntnu.fp.storage.SoftwareDbStorage;

import java.util.ArrayList;

public class IntegrationTest_SoftwareDbStorage extends TestCase {

	Software software;
	SoftwareDbStorage dbStorage;

	public void setUp() throws Exception {
		System.out.println("Setting up...");
		super.setUp();
		dbStorage = new SoftwareDbStorage();
		software = new Software();
	}

	/**
	 * MS Requirement #1
	 * This test only works one time,
	 * because there is no way to remove a software from the archive
	 */
	public void testAddSoftwareToArchive() {
		int dummyId = dbStorage.openSoftware().get(dbStorage.openSoftware().size() - 1).getSwVersion() + 1;
		software.setSwVersion(dummyId);
		assertEquals(dummyId, software.getSwVersion());

		dbStorage.addSoftware(software, null);
		ArrayList<Software> list = dbStorage.openSoftware();
		assertEquals(dummyId, list.get(list.size() - 1).getSwVersion());
	}
}

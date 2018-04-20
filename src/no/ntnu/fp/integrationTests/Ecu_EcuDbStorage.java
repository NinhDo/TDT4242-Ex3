package no.ntnu.fp.integrationTests;

import junit.framework.TestCase;
import no.ntnu.fp.model.SimpleEcu;
import no.ntnu.fp.model.Software;
import no.ntnu.fp.storage.EcuDbStorage;
import no.ntnu.fp.storage.SoftwareDbStorage;

import java.util.ArrayList;

public class Ecu_EcuDbStorage extends TestCase {
	private EcuDbStorage dbStorage;
	private SimpleEcu ecu;
	private SoftwareDbStorage sDbStorage;

	public void setUp() throws Exception {
		super.setUp();
		dbStorage = new EcuDbStorage();
		sDbStorage = new SoftwareDbStorage();
		ecu = new SimpleEcu();
	}

	/**
	 * MS Requirement #2
	 */
	public void testEcuAddEcuToDb() {
		int dummyEcuId= dbStorage.openSimpleEcu().get(dbStorage.openSimpleEcu().size() - 1).getEcuId() + 1;
		int dummySwId= sDbStorage.openSoftware().get(sDbStorage.openSoftware().size() - 1).getSwVersion() + 1;
		sDbStorage.addSoftware(new Software(dummySwId, 0, ""), null);
		assertTrue(sDbStorage.swInSwArchive(dummySwId));
		ecu.setEcuId(dummyEcuId);
		ecu.setSwId(dummySwId);
		assertEquals(dummyEcuId, ecu.getEcuId());
		assertEquals(dummySwId, ecu.getSwId());

		String[] message = dbStorage.addEcu(ecu, null);
		assertEquals("Ecu added to database", message[0]);

		ArrayList<SimpleEcu> list = dbStorage.openSimpleEcu();
		assertEquals(ecu.getEcuId(), list.get(list.size() - 1).getEcuId());
		assertEquals(ecu.getSwId(), list.get(list.size() - 1).getSwId());
	}
}

package no.ntnu.fp.model;

import junit.framework.Assert;
import junit.framework.TestCase;


public class EcuTest extends TestCase {

	public void testAddPropertyChangeListener() {
	}

	public void testRemovePropertyChangeListener() {
	}

	public void testGetEcuId() {
		Ecu ecu = new Ecu(1);
		Assert.assertEquals(1, ecu.getEcuId());
	}

	public void testSetEcuId() {
		Ecu ecu = new Ecu(1);
		ecu.setEcuId(2);
		Assert.assertEquals(2, ecu.getEcuId());
	}

	public void testGetSwId() {
		Ecu ecu = new Ecu(1, 1, 1);
	}

	public void testSetSwId() {
		Ecu ecu = new Ecu(1, 1, 1);
		ecu.setSwId(2);
		Assert.assertEquals(2, ecu.getSwId());
	}

	public void testGetSubSwId() {
		Ecu ecu = new Ecu(1, 1, 1);
		Assert.assertEquals(1, ecu.getSubSwId());
	}

	public void testSetSubSwId() {
		Ecu ecu = new Ecu(1, 1, 1);
		ecu.setSubSwId(2);
		Assert.assertEquals(2, ecu.getSubSwId());
	}

	public void testIsNewest() {
		Ecu ecu = new Ecu(1);
		Assert.assertTrue(ecu.isNewest());
	}

	public void testSetNewest() {
		Ecu ecu = new Ecu(1);
		ecu.setNewest(false);
		Assert.assertFalse(ecu.isNewest());
	}

	public void testGetECUID_PROPERTY_NAME() {
		Assert.assertEquals("ecuId", Ecu.getECUID_PROPERTY_NAME());
	}

	public void testGetNewestSub() {
		Ecu ecu = new Ecu(1, 1, 1, true, 1);
		Assert.assertEquals(1, ecu.getNewestSub());
	}

	public void testSetNewestSub() {
		Ecu ecu = new Ecu(1, 1, 1, true, 1);
		ecu.setNewestSub(2);
		Assert.assertEquals(2, ecu.getNewestSub());
	}
}
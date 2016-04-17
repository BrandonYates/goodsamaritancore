package core;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;

import javax.annotation.Resource;
import java.util.*;

public class DeedServiceTest {

  static Date d;
  static DeedController dc;
  static Deed d1;
  static Deed d2;
  static Deed d3;

  @BeforeClass
  public static void globalSetup() {
    AbstractApplicationContext context = new ClassPathXmlApplicationContext("classpath:META-INF/test-context.xml");
    dc = context.getBean(DeedController.class);
    d = new Date();

  }

  @Before
  public void localSetup() {
    d1 = new Deed(UUID.randomUUID().toString(), "Description 1", d.getTime(), "1", new Location("1", 1.1, 1.2));
     d3 = new Deed(UUID.randomUUID().toString(),
      "Description 2",
      d.getTime(),
      "2",
      new Location("1", 2.1, 2.2));
  }

  @Test
  public void testCreateDeedObject() {
    Assert.assertNotNull(d1);
  }

  @Test
  public void testCreateDeedObjectCopy() {
    Assert.assertNotNull(d1);
    d2 = new Deed(d1);
    Assert.assertNotNull(d2);
    Assert.assertTrue(d1.toString() + "\n" + d2.toString(), d2.equals(d1));
  }

  @Test
  public void testCopyDeedObject() {
    d3.copy(d1);
    Assert.assertNotNull(d1);
    Assert.assertNotNull(d3);
    Assert.assertTrue(d1.equals(d3));
  }

  @Test
  public void testSetId() {
    d3.setId("123");
    Assert.assertTrue("123" == d3.getId());
  }

  @Test
  public void testSetDescription() {
    d3.setDescription("New Description");
    Assert.assertTrue("New Description" == d3.getDescription());
  }

  @Test
  public void testSetDateLong() {
    d3.setDate(0);
    Assert.assertTrue(d3.getDate(), d3.getDate().equals("Thu Jan 01 00:00:00 UTC 1970"));
  }

//  @Test
//  public void testSetDateString() {
//    Date date = new Date("1982-08-08");
//    d3.setDate(date.getTime());
//    Assert.assertTrue(d3.getDate().equals("1982-08-08"));
//  }

  @Test
  public void testSetRequestingUserId() {
    d3.setRequestingUserId("321");
    Assert.assertTrue("321" == d3.getRequestingUserId());
  }

  @Test
  public void testAddClaimedUserId() {
    d3.addClaimedUserId("456");
    d3.addClaimedUserId("789");
    Set<String> s = new HashSet();
    s.add("456");
    s.add("789");
    Assert.assertTrue(d3.getClaimedUserIds().size() == 2);
    Assert.assertTrue(d3.getClaimedUserIds().containsAll(s));
  }

  @Test
  public void testDeleteClaimedUserId() {
    d3.addClaimedUserId("456");
    d3.addClaimedUserId("789");
    Assert.assertTrue(d3.getClaimedUserIds().size() == 2);

    d3.deleteClaimedUserId("789");
    Assert.assertTrue(d3.getClaimedUserIds().size() == 1);
    Assert.assertTrue(d3.getClaimedUserIds().contains("456"));

    d3.deleteClaimedUserId("456");
    Assert.assertTrue(d3.getClaimedUserIds().size() == 0);
    Assert.assertFalse(d3.getClaimedUserIds().contains("456"));
  }

  @Test
  public void testSetLocation() {
    d3.setLocation(new Location("1", 3.3, 4.4));
    Location l = d3.getLocation();
    Assert.assertTrue(l.equals(new Location("1", 3.3, 4.4)));
  }

  @Test
  public void testNonEqualDeed() {
    Assert.assertFalse(d1.equals(d3));
  }

//  @Test
//  public void testRestCreate() {
//    dc.createDeed(d1);
//    Assert.assertNotNull(dc.findById(d1.getId()));
//  }

  @Test
  public void testRestFindByRequestingUserId() {
    Assert.assertNotNull(dc.findByRequestingUserId("1"));
  }

//  @Test
//  public void testRestUpdate() {
//    dc.update(d1.getId(), d3);
//    Deed d = dc.findById(d3.getId());
//    Assert.assertNotNull(d);
//  }

//  @Test
//  public void testRestDelete() {
//    dc.delete(d1.getId());
//    Assert.assertNull(dc.findById(d1.getId()));
//  }
}
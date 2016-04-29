package GLM;

import TAGLClient.TableHachage;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }
    
    public void testValueOfString(){
    	System.out.println("Value Of String :");
    	TableHachage t = new TableHachage();
    	String s = "1";
    	String s1 = "-1";
    	String s2 = "+1";
    	String s3 = "15697";
    	String s4 = "1.5465";
    	String s5 = "test";
    	String s6 = "125test";
    	String s7 = "12.5test";
    	assertEquals(0, t.valueOfString(s));
    	assertEquals(0, t.valueOfString(s1));
    	assertEquals(0, t.valueOfString(s2));
    	assertEquals(0, t.valueOfString(s3));
    	assertEquals(1, t.valueOfString(s4));
    	assertEquals(-1, t.valueOfString(s5));
    	assertEquals(-1, t.valueOfString(s6));
    	assertEquals(-1, t.valueOfString(s7));
    }
    
    public void testSetEtGet(){
    	System.out.println("Set et Get :");
    	TableHachage t = new TableHachage();
    	t.set("test1", "test1");
    	t.set("test2", "1");
    	t.set("test3", "1.5618");
    	t.set("test4", "15test15");
    	t.set("test5", "15618");
    	assertEquals("test1", t.get("test1"));
    	assertEquals(1, t.get("test2"));
    	assertEquals(1.5618,t.get("test3"));
    	assertEquals("15test15", t.get("test4"));
    	assertEquals(15618,t.get("test5"));
    	assertEquals(0, t.get("test6"));
    }
    
    public void testDel(){
    	System.out.println("Del :");
    	TableHachage t = new TableHachage();
    	t.set("test1", "test1");
    	t.set("test2", "1");
    	t.set("test3", "1.5618");
    	t.set("test4", "15test15");
    	t.set("test5", "15618");
    	t.del("test1");
    	t.del("test2");
    	t.del("test3");
    	assertEquals(0, t.get("test1"));
    	assertEquals(0, t.get("test2"));
    	assertEquals(0,t.get("test3"));
    	assertEquals("15test15", t.get("test4"));
    	assertEquals(15618,t.get("test5"));
    	assertEquals(0, t.get("test6"));
    }
    
    public void testIncr(){
    	System.out.println("Incr :");
    	TableHachage t = new TableHachage();
    	t.set("test1", "test1");
    	t.set("test2", "1");
    	t.set("test3", "1.5618");
    	t.set("test4", "15test15");
    	t.set("test5", "15618");
    	t.incr("test1");
    	t.incr("test2");
    	t.incr("test3");
    	t.incr("test4");
    	t.incr("test5");
    	assertEquals("test1", t.get("test1"));
    	assertEquals(2, t.get("test2"));
    	assertEquals(2.5618,t.get("test3"));
    	assertEquals("15test15", t.get("test4"));
    	assertEquals(15619,t.get("test5"));
    	assertEquals(0, t.get("test6"));
    }
    
    public void testRPush(){
    	System.out.println("RPush :");
    	TableHachage t = new TableHachage();
    	t.rPush("test1", "test1");
    	t.rPush("test1", "1");
    	t.rPush("test1", "1.5618");
    	assertEquals(3, t.lLen("test1"));
    	t.lRange("test1", 0, -1);
    }
    
    public void testLPush(){
    	System.out.println("LPush :");
    	TableHachage t = new TableHachage();
    	t.lPush("test1", "test1");
    	t.lPush("test1", "1");
    	t.lPush("test1", "1.5618");
    	assertEquals(3, t.lLen("test1"));
    	t.lRange("test1", 0, -1);
    }
    
    public void testRPop(){
    	System.out.println("RPop :");
    	TableHachage t = new TableHachage();
    	t.rPush("test1", "test1");
    	t.rPush("test1", "1");
    	t.rPush("test1", "1.5618");
    	t.rPop("test1");
    	assertEquals(2, t.lLen("test1"));
    	t.lRange("test1", 0, -1);
    }
    
    public void testLPop(){
    	System.out.println("LPop :");
    	TableHachage t = new TableHachage();
    	t.rPush("test1", "test1");
    	t.rPush("test1", "1");
    	t.rPush("test1", "1.5618");
    	t.lPop("test1");
    	assertEquals(2, t.lLen("test1"));
    	t.lRange("test1", 0, -1);
    }
    
    public void testLRangeEtLlen(){
    	System.out.println("LRange et Llen :");
    	TableHachage t = new TableHachage();
    	t.rPush("test1", "test1");
    	t.rPush("test1", "1");
    	t.rPush("test1", "1.5618");
    	t.lRange("test1", 0, -1);
    	System.out.println();
    	t.lRange("test1", 0, 1);
    	System.out.println();
    	t.lRange("test1", 0, 2);
    	System.out.println();
    	t.lRange("test1", 0, 3);
    	System.out.println();
    	assertEquals(3, t.lLen("test1"));
    	t.rPop("test1");
    	assertEquals(2, t.lLen("test1"));
    	t.rPop("test1");
    	assertEquals(1, t.lLen("test1"));
    	t.rPop("test1");
    	assertEquals(0, t.lLen("test1"));
    }
}

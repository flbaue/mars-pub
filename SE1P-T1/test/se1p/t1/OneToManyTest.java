/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se1p.t1;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author florianbauer
 */
public class OneToManyTest {
    
    public OneToManyTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of add method, of class OneToMany.
     */
    @Test
    public void testAdd() {
        
        System.out.println("test: add");
        
        OneToMany<String> otm = new OneToMany<String>();
        
        String test1 = "test";
        String test2 = test1;
        String test3 = "test";
        String test4 = "12345";
        int test5 = 0;
        
        assertEquals("Add Object: test1", true, otm.add(test1));
        assertEquals("Add Object: test2", false, otm.add(test2));
        assertEquals("Add Object: test3", false, otm.add(test3));
        assertEquals("Add Object: test4", true, otm.add(test4));
        //assertEquals("Add Object: test5", false, otm.add(test5)); // Compile Error
    }

    /**
     * Test of addAll method, of class OneToMany.
     */
    @Test
    public void testAddAll() {
        System.out.println("test: addAll");
        
        OneToManyInterface<String> otm = new OneToMany<String>();
        
        String test1 = "test1";
        String test2 = "test2";
        String test3 = "test3";
        
        OneToManyInterface<String> set = new OneToMany<String>();
        set.add(test1);
        set.add(test2);
        set.add(test3);
        
        assertEquals("Add Object: test1", true, otm.addAll(set));
        
        assertEquals("Add Object: test1", false, otm.add(test1));
        assertEquals("Add Object: test2", false, otm.add(test2));
        assertEquals("Add Object: test3", false, otm.add(test3));
    }

    /**
     * Test of clear method, of class OneToMany.
     */
    @Test
    public void testClear() {
        
        System.out.println("test: clear");
        
        OneToMany<String> otm = new OneToMany<String>();
        
        String test1 = "test1";
        String test2 = "test2";
        String test3 = "test3";
        
        otm.add(test1);
        otm.add(test2);
        otm.add(test3);
        
        assertEquals("Contains 3 Elements", 3, otm.size());
        
        otm.clear();
        
        assertEquals("Contains 0 Elements", 0, otm.size());
      
    }

    /**
     * Test of contains method, of class OneToMany.
     */
    @Test
    public void testContains() {
        System.out.println("test: contains");
        
        OneToMany<String> otm = new OneToMany<String>();
        
        String test1 = "test1";
        String test2 = "test2";
        String test3 = "test3";
        
        otm.add(test1);
        otm.add(test2);
        
        assertEquals("Contains Element 1", true, otm.contains(test1));
        assertEquals("Contains Element 2", true, otm.contains(test2));
        assertEquals("Contains Element 3", false, otm.contains(test3));
    }

    /**
     * Test of containsAll method, of class OneToMany.
     */
    @Test
    public void testContainsAll() {
        System.out.println("test: containsAll");
        
        OneToManyInterface<String> otm = new OneToMany<String>();
        
        String test1 = "test1";
        String test2 = "test2";
        String test3 = "test3";
        
        otm.add(test1);
        otm.add(test2);
        otm.add(test3);
        
        OneToManyInterface<String> set = new OneToMany<String>();
        set.add(test1);
        set.add(test2);
        set.add(test3);
        
        assertEquals("Contains all elements", true, otm.containsAll(set));
        
    }

    /**
     * Test of remove method, of class OneToMany.
     */
    @Test
    public void testRemove() {
        
        System.out.println("test: remove");
        
        OneToMany<String> otm = new OneToMany<String>();
        
        String test1 = "test1";
        String test2 = "test2";
        String test3 = "test3";
        
        otm.add(test1);
        otm.add(test2);
        otm.add(test3);
        
        assertEquals("Contains Element 1", true, otm.contains(test1));
        assertEquals("Contains Element 2", true, otm.contains(test2));
        assertEquals("Contains Element 3", true, otm.contains(test3));
        
        otm.remove(test2);
        
        assertEquals("Contains Element 1", true, otm.contains(test1));
        assertEquals("Contains Element 2", false, otm.contains(test2));
        assertEquals("Contains Element 3", true, otm.contains(test3));
       
    }

    /**
     * Test of size method, of class OneToMany.
     */
    @Test
    public void testSize() {
        System.out.println("test: size");
        
        OneToMany<String> otm = new OneToMany<String>();
        
        String test1 = "test1";
        String test2 = "test2";
        String test3 = "test3";
        
        otm.add(test1);
        otm.add(test2);
        otm.add(test3);
        
        assertEquals("Contains 3 Elements", 3, otm.size());
       
    }

    /**
     * Test of iterator method, of class OneToMany.
     */
    @Test
    public void testIterator() {
        System.out.println("test: iterator");
        
        OneToMany<String> otm = new OneToMany<String>();
        
        String test1 = "test1";
        String test2 = "test2";
        String test3 = "test3";
        
        otm.add(test1);
        otm.add(test2);
        otm.add(test3);
        
        Iterator i = otm.iterator();
        Object s = i.next();
        
        assertEquals("Next Element", test1, (String)s);
    }
}
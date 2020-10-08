package EventList;

import org.junit.*;


public class EventListTest {
	String str = "picnic"; 
	
	@Before
	public void setup() {
		System.out.println("before testing ...");
	}
	
	@After
	public void cleanup() {
		System.out.println("Finishing and cleaning up the test");
	}
	
	@Test
	public void testIsEventThere() {
		String[] arr = {"shopping", "tennis", "hackathon", "napping"};
		EventList eL = new EventList(arr);
		Assert.assertFalse(eL.isEventThere(str));
	}
	
	@Test
	public void testIsEventThere3() {
		String[] arr = {"shopping", "picnic", "hackathon", "napping"};
		EventList eL = new EventList(arr);
		Assert.assertTrue(eL.isEventThere(str));
	}
}

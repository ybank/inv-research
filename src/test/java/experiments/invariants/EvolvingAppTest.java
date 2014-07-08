package experiments.invariants;

import static org.junit.Assert.*;
import org.junit.Test;
import experiments.invariants.*;

/**
 * Unit tests for App.
 */
public class EvolvingAppTest 
{
	private EvolvingApp app = new EvolvingApp();
	
	@Test
	public void testIncrementAfterClearance() {
		app.clearCount();
		assertEquals(1, app.cycleIncrement());
	}
	
	@Test
	public void testIncrementCount1() {
		app.setCount(1);
		assertEquals(2, app.cycleIncrement());
	}
	
	@Test
	public void testIncrementCountArbitrary() {
		app.setCount(100);
		assertEquals(101, app.cycleIncrement());
	}	
	
	@Test
	public void testIncrementNegative() {
		app.setCount(-1);
		assertEquals(1, app.cycleIncrement());
	}
	
	@Test
	public void testIncrementArbitraryNegative() {
		app.setCount(-100);
		assertEquals(1, app.cycleIncrement());
	}
	
	@Test
	public void testMax() {
		app.setCount(app.MAX);
		assertEquals(1, app.cycleIncrement());
	}
	
	@Test
	public void testNearMax() {
		app.setCount(app.MAX-1);
		assertEquals(app.MAX, app.cycleIncrement());
	}
	
	@Test
	public void setCount0() {
		app.setCount(0);
		assertEquals(0, app.getCount());
	}
	
	@Test
	public void setCount1() {
		app.setCount(1);
		assertEquals(1, app.getCount());
	}
	
	@Test
	public void setCountMAX() {
		app.setCount(app.MAX);
		assertEquals(app.MAX, app.getCount());
	}
	
	@Test
	public void setCountArbitraryBig() {
		app.setCount(515);
		assertEquals(3, app.getCount());
	}
	
	@Test
	public void setCountArbitraryBig2() {
		app.setCount(509);
		assertEquals(125, app.getCount());
	}
	
	@Test
	public void setCountArbitrarySmall() {
		app.setCount(56);
		assertEquals(56, app.getCount());
	}
	
	@Test
	public void setCountNegtive() {
		app.setCount(-10);
		assertEquals(0, app.getCount());
	}
}

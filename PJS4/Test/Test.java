package Test;
import static org.junit.Assert.*;
import salle.Salle;


public class Test {

	@org.junit.Test
	public void testSalle() {
		Salle s= new Salle ("1",25);
		Salle s2 = new Salle("2",25);
		assertEquals(1,s.getNumSalle());
		assertEquals(25, s.getNbPlaces());
		assertEquals(false, s.isOccupee());
		assertEquals (null,s.getReservant());
		
		
		//test Revervations
		assertEquals(false, s.isReservee());
		s.ReserverSalle("Jacques");
		assertEquals("Jacques",s.getReservant() );
		assertEquals(true, s.isReservee());
		
		s2.setOccupe();
		assertEquals(true, s2.isOccupee());
		s2.ReserverSalle("François");
		assertEquals("François",s2.getReservant());
		assertEquals(false, s2.isReservee());
		s2.libererSalle();
		assertEquals(false, s2.isOccupee());
		assertEquals (null,s2.getReservant());
		
		
	}

}

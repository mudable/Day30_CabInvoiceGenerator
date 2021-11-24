import static org.junit.Assert.*;

import org.junit.Test;

import com.bridgelabz.cabinvoicegenerator.CabInvoiceGenerator;

import junit.framework.Assert;

public class TestCabInvoiceGenerator {
	@Test
	public void givenDistanceAndTime_ShouldReturnTotalFare() {
		CabInvoiceGenerator invoiceGenerator = new CabInvoiceGenerator();
		double distance = 2.0;
		int time = 5;
		double fare = invoiceGenerator.calculateFare(distance, time);
		Assert.assertEquals(25, fare, 0.0);
	}
}

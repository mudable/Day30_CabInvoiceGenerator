import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.bridgelabz.cabinvoicegenerator.CabInvoiceGenerator;
import com.bridgelabz.cabinvoicegenerator.InvoiceSummary;
import com.bridgelabz.cabinvoicegenerator.Ride;

import junit.framework.Assert;

public class TestCabInvoiceGenerator {
	// testCase to write Total fare
	@Test
	public void givenDistanceAndTime_ShouldReturnTotalFare() {
		CabInvoiceGenerator invoiceGenerator = new CabInvoiceGenerator();
		double distance = 2.0;
		int time = 5;
		double fare = invoiceGenerator.calculateFare(distance, time);
		Assert.assertEquals(25, fare, 0.0);
	}

	// testCase to calculate the Minimum Charges
	@Test
	public void givenLessDistanceAndTime_ShouldReturnMinFare() {
		CabInvoiceGenerator invoiceGenerator = new CabInvoiceGenerator();
		double distance = 0.1;
		int time = 1;
		double fare = invoiceGenerator.calculateFare(distance, time);
		Assert.assertEquals(5, fare, 0.0);
	}

	// invoice generator for multiple rides
	@Test
	public void givenMultipleRides_shouldReturnTotalFare() {
		CabInvoiceGenerator invoiceGenerator = new CabInvoiceGenerator();
		Ride[] rides = { new Ride(2.0, 5), new Ride(0.1, 1) };
		InvoiceSummary summary = invoiceGenerator.calculateFare(rides);
		InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(2, 30.0);
		Assert.assertEquals(expectedInvoiceSummary, summary);
	}

	// testCase to return Invoice summary using userID
	@Test
	public void givenUserId_shouldReturnInvoiceSummary() {
		InvoiceGenerator invoiceGenerator = new InvoiceGenerator();
		String userId = "rajendragund@gmailcom.com";
		Ride[] rides = { new Ride(2.0, 5), new Ride(0.1, 1) };
		invoiceGenerator.addRides(userId, rides);
		InvoiceSummary summary = invoiceGenerator.getInvoiceSummary(userId);
		InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(2, 30);
		Assert.assertEquals(expectedInvoiceSummary, summary);
	}

	// testCase to Normal rides and premium rides
	@Test
	public void givenUserIdAndRides_ShouldReturn_MultipleInvoiceSummary() {
		CabInvoiceGenerator invoiceGenerator = new CabInvoiceGenerator();
		String userId = "rajendragund@gmailcom.com";
		Ride[] rides = { new Ride(2.0, 5, CabInvoiceGenerator.RideMode.NORMAL),
				new Ride(0.1, 1, CabInvoiceGenerator.RideMode.NORMAL) };
		invoiceGenerator.addRides(userId, rides);
		Ride[] rides1 = { new Ride(2.0, 5, CabInvoiceGenerator.RideMode.PREMIUM),
				new Ride(0.1, 1, CabInvoiceGenerator.RideMode.PREMIUM) };
		invoiceGenerator.addRides(userId, rides1);
		Ride[] rides2 = { new Ride(2.0, 5, CabInvoiceGenerator.RideMode.NORMAL),
				new Ride(0.1, 1, CabInvoiceGenerator.RideMode.PREMIUM) };
		invoiceGenerator.addRides(userId, rides2);
		InvoiceSummary summary = invoiceGenerator.getInvoiceSummary(userId);
		InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(6, 125);
		Assert.assertEquals(expectedInvoiceSummary, summary);
	}
}
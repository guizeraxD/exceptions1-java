package model.entities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import model.exceptions.DomainException;

public class Reservation {

	private Integer roomNumber;
	private Date checkin;
	private Date checkout;
	
	//� static pois sera instanciado apenas 1, e n�o 1 para cada reservation instanciado
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	public Reservation(Integer roomNumber, Date checkin, Date checkout){
		if(!checkout.after(checkin)){
			throw new DomainException("Check-out date must be after check-in date");
		}
		
		this.roomNumber = roomNumber;
		this.checkin = checkin;
		this.checkout = checkout;
	}

	public Integer getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(Integer roomNumber) {
		this.roomNumber = roomNumber;
	}

	public Date getCheckin() {
		return checkin;
	}

	public Date getCheckout() {
		return checkout;
	}

	//como tem o metodo updateDates tira os setters
	
	public long duration() {
		long dif = checkout.getTime() - checkin.getTime(); 			//devolve a data em milliseconds
		return TimeUnit.DAYS.convert(dif, TimeUnit.MILLISECONDS);			//converte o valor de "dif" que estava em milliseconds para dias
		
	}
	
	public void updateDates(Date checkin, Date checkout){
		Date now= new Date();	
		if(checkin.before(now)||checkout.before(now)){
			throw new DomainException("Reservation dates for update must be future dates");
		}
		if(!checkout.after(checkin)){
			throw new DomainException("Check-out date must be after check-in date");
		}
		
		this.checkin = checkin;
		this.checkout = checkout;
	}
	
	@Override 			//toString sempre ter� override
	public String toString(){
		return "Room "
				+ roomNumber
				+", check-in: "
				+ sdf.format(checkin)
				+", check-out: "
				+ sdf.format(checkout)
				+", "
				+ duration()
				+ " nights";
	}
}

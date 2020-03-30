package model.entities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Reservation {

	private Integer roomNumber;
	private Date checkin;
	private Date checkout;
	
	//é static pois sera instanciado apenas 1, e não 1 para cada reservation instanciado
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	public Reservation(Integer roomNumber, Date checkin, Date checkout) {
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
	
	public String updateDates(Date checkin, Date checkout) {
		Date now= new Date();	
		if(checkin.before(now)||checkout.before(now)){
			return "Reservation dates for update must be future dates";
		}
		if(!checkout.after(checkin)){
			return "Check-out date must be after check-in date";
		}
		
		this.checkin = checkin;
		this.checkout = checkout;
		return null;
	}
	
	@Override 			//toString sempre terá override
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

package accom;

import java.sql.Timestamp;

public class AccDto {
	
	public int Roomno;
	public String Accomname;
	public String Address;
	public String PoolYN;
    public String Roomname;
    public int Price;
    public int Capacity;
    public String AnimalYN;
    public String PoolableYN;
    public String Around;
    public String Roomview;
    public String type;
    
    public String Checkin;
    public String Checkout;
    public int People;
    public String HG;
    
    public Timestamp ReserveDate;
    public int ReserveNo;
    
    public AccDto() {
    	
    }
    
	public AccDto(int roomno, String accomname, String address, String poolYN, String roomname, int price, int capacity,
			String animalYN, String poolableYN, String around, String roomview, String checkin, String checkout, int people, String hg) {
		super();
		this.Roomno = roomno;
		this.Accomname = accomname;
		this.Address = address;
		this.PoolYN = poolYN;
		this.Roomname = roomname;
		this.Price = price;
		this.Capacity = capacity;
		this.AnimalYN = animalYN;
		this.PoolableYN = poolableYN;
		this.Around = around;
		this.Roomview = roomview;
		this.Checkin = checkin;
		this.Checkout = checkout;
		this.People = people;
		this.HG = hg;
	}

	@Override
	public String toString() {
		return "no. " + Roomno + ", "+ Accomname + ", 주소: " + Address + ", 수영장" + PoolYN + ", "
				 + Roomname + ", 가격: " + Price + "원, 수용인원: " + Capacity + ", 반려동물동반" + AnimalYN
				+ ", 수영장사용여부" + PoolableYN + ", 숙소 주변: " + Around + ", 뷰:" + Roomview + "]";
	}
	
	public int getRoomno() {
		return Roomno;
	}
	public void setRoomno(int roomno) {
		Roomno = roomno;
	}
	public String getAccomname() {
		return Accomname;
	}
	public void setAccomname(String accomname) {
		Accomname = accomname;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	public String getPoolYN() {
		return PoolYN;
	}
	public void setPoolYN(String poolYN) {
		PoolYN = poolYN;
	}
	public String getRoomname() {
		return Roomname;
	}
	public void setRoomname(String roomname) {
		Roomname = roomname;
	}
	public int getPrice() {
		return Price;
	}
	public void setPrice(int price) {
		Price = price;
	}
	public int getCapacity() {
		return Capacity;
	}
	public void setCapacity(int capacity) {
		Capacity = capacity;
	}
	public String getAnimalYN() {
		return AnimalYN;
	}
	public void setAnimalYN(String animalYN) {
		AnimalYN = animalYN;
	}
	public String getPoolableYN() {
		return PoolableYN;
	}
	public void setPoolableYN(String poolableYN) {
		PoolableYN = poolableYN;
	}
	public String getAround() {
		return Around;
	}
	public void setAround(String around) {
		Around = around;
	}
	public String getRoomview() {
		return Roomview;
	}
	public void setRoomview(String roomview) {
		Roomview = roomview;
	}

	public String getCheckin() {
		return Checkin;
	}

	public void setCheckin(String checkin) {
		Checkin = checkin;
	}

	public String getCheckout() {
		return Checkout;
	}

	public void setCheckout(String checkout) {
		Checkout = checkout;
	}

	public int getPeople() {
		return People;
	}

	public void setPeople(int people) {
		People = people;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getHG() {
		return HG;
	}

	public void setHG(String hg) {
		HG = hg;
	}

	public Timestamp getReserveDate() {
		return ReserveDate;
	}

	public void setReserveDate(Timestamp reserveDate) {
		ReserveDate = reserveDate;
	}

	public int getReserveNo() {
		return ReserveNo;
	}

	public void setReserveNo(int reserveNo) {
		ReserveNo = reserveNo;
	}

	
	

	
    

}

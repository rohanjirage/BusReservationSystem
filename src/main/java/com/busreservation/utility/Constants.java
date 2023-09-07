package com.busreservation.utility;

public class Constants {
	
	public enum UserRole {
		ROLE_PASSENGER("PASSENGER"),
		ROLE_ADMIN("ADMIN");
		
		private String role;

	    private UserRole(String role) {
	      this.role = role;
	    }

	    public String value() {
	      return this.role;
	    }    
	}
	
	public enum UserStatus {
		ACTIVE("Active"),
		DEACTIVATED("Deactivated");
		
		
		private String status;

	    private UserStatus(String status) {
	      this.status = status;
	    }

	    public String value() {
	      return this.status;
	    }    
	}
	
	public enum JourneyStatus {
		SCHEDULED("Scheduled"),
		ON_TIME("On Time"),
		DELAYED("Delayed"),
		CANCELED("Cancelled"),
		COMPLETED("Completed");
		
		
		private String status;

	    private JourneyStatus(String status) {
	      this.status = status;
	    }

	    public String value() {
	      return this.status;
	    }    
	}
	
	public enum BusBookingStatus {
		CONFIRMED("Confirmed"),
		PENDING("Pending"),
		CANCELLED("Cancelled");
		
		private String status;

	    private BusBookingStatus(String status) {
	      this.status = status;
	    }

	    public String value() {
	      return this.status;
	    }    
	}
	
	public enum BusSeatType {
		FRONT("Front"),
		MIDDLE("Middle"),
		BACK("Back");
		
		private String type;

	    private BusSeatType(String type) {
	      this.type = type;
	    }

	    public String value() {
	      return this.type;
	    }    
	}
	
	public enum BusStatus {
		ACTIVE("Active"),
		DEACTIVATED("Deactivated");
		
		
		private String status;

	    private BusStatus(String status) {
	      this.status = status;
	    }

	    public String value() {
	      return this.status;
	    }    
	}

}

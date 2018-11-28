package edu.ucsd.eng.campr;

import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;

public class User {
	private String userID;
    private String DisplayName;
	private String emailAddress;
	private String phoneNumber;
	private String city;
	private String state;
	private Pet[] chosenPets;
	private Pet[] listedPets;
	private boolean contactInfoSet;
	
	public User(String userID, String firstName, String userName,
                String emailAddress, String phoneNumber, String city, String state, Pet[] chosenPets, Pet[] listedPets, boolean role) {
		this.userID = userID;
        this.DisplayName = firstName;
		this.emailAddress = emailAddress;
		this.phoneNumber = phoneNumber;
		this.city = city;
		this.state = state;
		this.chosenPets = chosenPets;
		this.listedPets = listedPets;
		this.contactInfoSet = true;
	}


	public User(FirebaseUser user) {
	    this.userID = user.getUid();
	    this.DisplayName = user.getDisplayName();
	    this.emailAddress = user.getEmail();
	    this.contactInfoSet = false;
    }

	public String getUserID() {
		return userID;
	}

    public String getDisplayName() {
		return DisplayName;
	}

	public void setDisplayName(String displayName) {
		this.DisplayName = displayName;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Pet[] getChosenPets() {
		return chosenPets;
	}

	public void setChosenPets(Pet[] chosenPets) {
		this.chosenPets = chosenPets;
	}

	public Pet[] getListedPets() {
		return listedPets;
	}

	public void setListedPets(Pet[] listedPets) {
		this.listedPets = listedPets;
	}

    @Override
	public String toString() {
		return "User [userID=" + userID + ", DisplayName=" + DisplayName + ", emailAddress=" + emailAddress + ", phoneNumber="
				+ phoneNumber + ", city=" + city + ", state=" + state + ", chosenPets=" + Arrays.toString(chosenPets)
				+ ", listedPets=" + Arrays.toString(listedPets) + "]";
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (userID == null) {
			if (other.userID != null)
				return false;
		} else if (!userID.equals(other.userID))
			return false;
		if (!Arrays.equals(chosenPets, other.chosenPets))
			return false;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (emailAddress == null) {
			if (other.emailAddress != null)
				return false;
		} else if (!emailAddress.equals(other.emailAddress))
			return false;
		if (DisplayName == null) {
			if (other.DisplayName != null)
				return false;
		} else if (!DisplayName.equals(other.DisplayName))
			return false;
		if (!Arrays.equals(listedPets, other.listedPets))
			return false;
		if (phoneNumber == null) {
			if (other.phoneNumber != null)
				return false;
		} else if (!phoneNumber.equals(other.phoneNumber))
			return false;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		return true;
	}
}

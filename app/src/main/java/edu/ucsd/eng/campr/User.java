package edu.ucsd.eng.campr;

import java.util.Arrays;

public class User {
	private String adopterID;
	private String listerID;
	private String firstName;
	private String lastName;
	private String userName;
	private String emailAddress;
	private String phoneNumber;
	private String city;
	private String state;
	private Pet[] chosenPets;
	private Pet[] listedPets;
	
	public User(String adopterID, String listerID, String firstName, String lastName, String userName,
			String emailAddress, String phoneNumber, String city, String state, Pet[] chosenPets, Pet[] listedPets) {
		this.adopterID = adopterID;
		this.listerID = listerID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.emailAddress = emailAddress;
		this.phoneNumber = phoneNumber;
		this.city = city;
		this.state = state;
		this.chosenPets = chosenPets;
		this.listedPets = listedPets;
	}

	public String getAdopterID() {
		return adopterID;
	}

	public void setAdopterID(String adopterID) {
		this.adopterID = adopterID;
	}

	public String getListerID() {
		return listerID;
	}

	public void setListerID(String listerID) {
		this.listerID = listerID;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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
		return "User [adopterID=" + adopterID + ", listerID=" + listerID + ", firstName=" + firstName + ", lastName="
				+ lastName + ", userName=" + userName + ", emailAddress=" + emailAddress + ", phoneNumber="
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
		if (adopterID == null) {
			if (other.adopterID != null)
				return false;
		} else if (!adopterID.equals(other.adopterID))
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
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (!Arrays.equals(listedPets, other.listedPets))
			return false;
		if (listerID == null) {
			if (other.listerID != null)
				return false;
		} else if (!listerID.equals(other.listerID))
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
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}
}

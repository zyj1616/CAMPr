package edu.ucsd.eng.campr;

import java.util.Arrays;

public class Pet {
	private String petID;
	private int species;
	private int size;
	private int age;
	private int color;
	private double fee;
	private String[] possibleAdopters;
	private String[] impossibleAdopters;
	
	public Pet(String petID, int species, int size, int age, int color, double fee, String[] possibleAdopters,
			String[] impossibleAdopters) {
		this.petID = petID;
		this.species = species;
		this.size = size;
		this.age = age;
		this.color = color;
		this.fee = fee;
		this.possibleAdopters = possibleAdopters;
		this.impossibleAdopters = impossibleAdopters;
	}

	public String getPetID() {
		return petID;
	}

	public void setPetID(String petID) {
		this.petID = petID;
	}

	public int getSpecies() {
		return species;
	}

	public void setSpecies(int species) {
		this.species = species;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}

	public double getFee() {
		return fee;
	}

	public void setFee(double fee) {
		this.fee = fee;
	}

	public String[] getPossibleAdopters() {
		return possibleAdopters;
	}

	public void setPossibleAdopters(String[] possibleAdopters) {
		this.possibleAdopters = possibleAdopters;
	}

	public String[] getImpossibleAdopters() {
		return impossibleAdopters;
	}

	public void setImpossibleAdopters(String[] impossibleAdopters) {
		this.impossibleAdopters = impossibleAdopters;
	}

	@Override
	public String toString() {
		return "Pet [petID=" + petID + ", species=" + species + ", size=" + size + ", age=" + age + ", color=" + color
				+ ", fee=" + fee + ", possibleAdopters=" + Arrays.toString(possibleAdopters) + ", impossibleAdopters="
				+ Arrays.toString(impossibleAdopters) + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pet other = (Pet) obj;
		if (age != other.age)
			return false;
		if (color != other.color)
			return false;
		if (Double.doubleToLongBits(fee) != Double.doubleToLongBits(other.fee))
			return false;
		if (!Arrays.equals(impossibleAdopters, other.impossibleAdopters))
			return false;
		if (petID == null) {
			if (other.petID != null)
				return false;
		} else if (!petID.equals(other.petID))
			return false;
		if (!Arrays.equals(possibleAdopters, other.possibleAdopters))
			return false;
		if (size != other.size)
			return false;
		if (species != other.species)
			return false;
		return true;
	}
}

package edu.ucsd.eng.campr;

public class Application {
	private String applicationID;
	private String listerID;
	private String adopterID;
	private String petID;
	private String applicantMessage;
	private boolean approved;
	private boolean contactAvailable;
	
	public Application(String applicationID, String listerID, String adopterID, String petID, String applicantMessage,
			boolean approved, boolean contactAvailable) {
		this.applicationID = applicationID;
		this.listerID = listerID;
		this.adopterID = adopterID;
		this.petID = petID;
		this.applicantMessage = applicantMessage;
		this.approved = approved;
		this.contactAvailable = contactAvailable;
	}

	public String getApplicationID() {
		return applicationID;
	}

	public void setApplicationID(String applicationID) {
		this.applicationID = applicationID;
	}

	public String getListerID() {
		return listerID;
	}

	public void setListerID(String listerID) {
		this.listerID = listerID;
	}

	public String getAdopterID() {
		return adopterID;
	}

	public void setAdopterID(String adopterID) {
		this.adopterID = adopterID;
	}

	public String getPetID() {
		return petID;
	}

	public void setPetID(String petID) {
		this.petID = petID;
	}

	public String getApplicantMessage() {
		return applicantMessage;
	}

	public void setApplicantMessage(String applicantMessage) {
		this.applicantMessage = applicantMessage;
	}

	public boolean isApproved() {
		return approved;
	}

	public void setApproved(boolean approved) {
		this.approved = approved;
	}

	public boolean isContactAvailable() {
		return contactAvailable;
	}

	public void setContactAvailable(boolean contactAvailable) {
		this.contactAvailable = contactAvailable;
	}

	@Override
	public String toString() {
		return "Application [applicationID=" + applicationID + ", listerID=" + listerID + ", adopterID=" + adopterID
				+ ", petID=" + petID + ", applicantMessage=" + applicantMessage + ", approved=" + approved
				+ ", contactAvailable=" + contactAvailable + "]";
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Application other = (Application) obj;
		if (adopterID == null) {
			if (other.adopterID != null)
				return false;
		} else if (!adopterID.equals(other.adopterID))
			return false;
		if (applicantMessage == null) {
			if (other.applicantMessage != null)
				return false;
		} else if (!applicantMessage.equals(other.applicantMessage))
			return false;
		if (applicationID == null) {
			if (other.applicationID != null)
				return false;
		} else if (!applicationID.equals(other.applicationID))
			return false;
		if (approved != other.approved)
			return false;
		if (contactAvailable != other.contactAvailable)
			return false;
		if (listerID == null) {
			if (other.listerID != null)
				return false;
		} else if (!listerID.equals(other.listerID))
			return false;
		if (petID == null) {
			if (other.petID != null)
				return false;
		} else if (!petID.equals(other.petID))
			return false;
		return true;
	}
}

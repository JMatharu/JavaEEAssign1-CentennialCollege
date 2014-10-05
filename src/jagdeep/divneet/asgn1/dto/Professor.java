package jagdeep.divneet.asgn1.dto;

import jagdeep.divneet.asgn1.exceptions.InvalidDataException;

;

public class Professor {
	String profName;

	public Professor(String profName) throws InvalidDataException {
		if (profName.equals(null) || profName.isEmpty()) {
			throw new InvalidDataException("You should enter Professor name");
		} else {
			setProfName(profName);
		}

	}

	public String getProfName() {
		return profName;
	}

	public void setProfName(String profName) throws InvalidDataException {
		this.profName = profName;
		/*if (profName.equals(null) || profName.isEmpty()) {
			throw new InvalidDataException("You should enter Professor name");
		}*/
	}

	public String toString() {
		return profName;

	}

}

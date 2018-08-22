// pair programming project with Darby O'Rear and Shontinique Uqdah

package co.grandcircus.proper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Property {
	private String name;
	private String location;
	private String type;
	private String amenities;
	private double price;
	private LocalDate dateAvailable;

	public Property(String name, String location, String type, String amenities, double price,
			LocalDate dateAvailable) {
		this.name = name;
		this.location = location;
		this.type = type;
		this.amenities = amenities;
		this.price = price;
		this.dateAvailable = dateAvailable;
	}

	public Property() {

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getAmenities() {
		return amenities;
	}

	public void setAmenities(String amenities) {
		this.amenities = amenities;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public LocalDate getDateAvailable() {
		return dateAvailable;
	}

	public void setDateAvailable(LocalDate dateAvailable) {
		this.dateAvailable = dateAvailable;
	}

	public String getFullDetails() {
		String format = "%-30s %-30s\n%-30s %-30s\n";
		String details = String.format(format, name, location, type,
				"Available starting " + dateAvailable.format(DateTimeFormatter.ofPattern("MM/dd/uuuu")));
		String[] allAmenities = amenities.split(" ");
		details += "\nThis property features ";

		for (String amenity : allAmenities) {

			if (!amenity.equals(allAmenities[allAmenities.length - 1])) {
				details += amenity + ", ";
			} else {
				details += "and " + amenity + ".";
			}
		}
		return details;
	}

	@Override
	public String toString() {
		return name + "," + location + "," + type + "," + amenities + "," + price + ","
				+ dateAvailable.format(DateTimeFormatter.ofPattern("MM/dd/uuuu"));
	}

}
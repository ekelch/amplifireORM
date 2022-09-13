package models;

import java.util.Objects;

import annotations.GeneratedId;
import annotations.GeneratedIdSetter;
import annotations.NonId;
import annotations.NonIdGetter;
import annotations.NonIdSetter;

public class Location {

	@GeneratedId(column=1)
	private int location_id;
	
	@NonId(column=2)
	private String location_name;

	public Location() {
		super();
	}

	public int getLocation_id() {
		return location_id;
	}

	@GeneratedIdSetter(column=1)
	public void setLocation_id(int location_id) {
		this.location_id = location_id;
	}

	@NonIdGetter(column=2)
	public String getLocation_name() {
		return location_name;
	}

	@NonIdSetter(column=2)
	public void setLocation_name(String location_name) {
		this.location_name = location_name;
	}

	@Override
	public String toString() {
		return "Location [location_id=" + location_id + ", location_name=" + location_name + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(location_id, location_name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Location other = (Location) obj;
		return location_id == other.location_id && Objects.equals(location_name, other.location_name);
	}
	
	
	
}

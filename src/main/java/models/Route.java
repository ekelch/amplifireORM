package models;

import java.util.Objects;

import annotations.GeneratedId;
import annotations.GeneratedIdSetter;
import annotations.NonId;
import annotations.NonIdGetter;
import annotations.NonIdSetter;

public class Route {

	@GeneratedId(column=1)
	private long routeId;
	
	@NonId(column=2)
	private String routeName;
	
	@NonId(column=3)
	private int location;
	
	@NonId(column=4)
	private String difficulty;
	
	@NonId(column=5)
	private int length;


	public Route() {
		super();
	}

	public long getRouteId() {
		return routeId;
	}

	@GeneratedIdSetter(column=1)
	public void setRouteId(long routeId) {
		this.routeId = routeId;
	}

	@NonIdGetter(column=2)
	public String getRouteName() {
		return routeName;
	}

	@NonIdSetter(column=2)
	public void setRouteName(String routeName) {
		this.routeName = routeName;
	}

	@NonIdGetter(column=3)
	public int getLocation() {
		return location;
	}

	@NonIdSetter(column=3)
	public void setLocation(int location) {
		this.location = location;
	}

	@NonIdGetter(column=4)
	public String getDifficulty() {
		return difficulty;
	}

	@NonIdSetter(column=4)
	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}

	@NonIdGetter(column=5)
	public int getLength() {
		return length;
	}

	@NonIdSetter(column=5)
	public void setLength(int length) {
		this.length = length;
	}

	@Override
	public int hashCode() {
		return Objects.hash(difficulty, length, location, routeId, routeName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Route other = (Route) obj;
		return Objects.equals(difficulty, other.difficulty) && length == other.length && location == other.location
				&& routeId == other.routeId && Objects.equals(routeName, other.routeName);
	}

	@Override
	public String toString() {
		return "Route [routeId=" + routeId + ", routeName=" + routeName + ", location=" + location + ", difficulty="
				+ difficulty + ", length=" + length + "]";
	}
	
	
	
	
	
}

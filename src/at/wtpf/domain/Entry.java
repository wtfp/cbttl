package at.wtpf.domain;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "entry")
public class Entry {
	
	//For QueryBuilder to be able to find the fields
	public static final String ID_FIELD_NAME = "id";
	public static final String VERSION_FIELD_NAME = "version";
	public static final String DESCRIPTION_FIELD_NAME = "description";
	
	@DatabaseField(generatedId = true)
	private int id;
	
	@DatabaseField
	private Integer version;
	
	@DatabaseField
	private String description;

	/**
	 * All persisted classes must define a no-arg constructor with at least package visibility
	 */
	public Entry() {
	}	
	
	public Entry(int id, String description) {
		this.id = id;
		this.description = description;
	}

	public Entry(int id, Integer version, String description) {
		this.id = id;
		this.version = version;
		this.description = description;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public Integer getVersion() {
		return version;
	}
	
	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

	@Override
	public int hashCode() {
		//TODO: gscheiter hash
		return id;
	}

	@Override
	public String toString() {
		StringBuilder b = new StringBuilder();
		b.append("id:[").append(id).append("], ");
		b.append("version:[").append(version).append("], ");
		b.append("").append(description).append("]");
		return b.toString();
	}
	
	
	
	

}

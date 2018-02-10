package github;

public class Project {
	private String fullName;
	private String description;
	
	public Project(String name, String descr) {
		this.setFullName(name);
		this.setDescription(descr);
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}

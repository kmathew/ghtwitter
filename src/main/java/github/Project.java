package github;

/**
 * GitHub Project Object
 * 
 * Only taking in the minimum required details and storing it. Additions can be made if necessary.
 * 
 * @author kevin mathew
 *
 */
public class Project {
  private String fullName;
  private String description;

  /**
   * Constructor.
   * 
   * @param name full name of GitHub project
   * @param descr description of GitHub project
   */
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

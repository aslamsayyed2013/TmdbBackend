package co.incubyte;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Actor {

  private final long id;
  private final String name;
  private final String profilePath;

  public Actor(
      @JsonProperty("id") long id,
      @JsonProperty("name") String name,
      @JsonProperty("profile_path") String profilePath) {
    this.id = id;
    this.name = name;
    this.profilePath = profilePath;
  }

  public long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getProfilePath() {
    return profilePath;
  }
}

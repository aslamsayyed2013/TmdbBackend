package co.incubyte;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;

public class Artist {

  private String id;
  private String name;
  private String image;
  private int age;
  private int gender;
  private String placeOfBirth;
  private LocalDate birthday;

  public Artist() {
  }

  public Artist(
      @JsonProperty("id") String id,
      @JsonProperty("name") String name,
      @JsonProperty("profile_path") String image,
      @JsonProperty("age") int age,
      @JsonProperty("gender") int gender,
      @JsonProperty("birthday") LocalDate birthday,
      @JsonProperty("place_of_birth") String placeOfBirth) {
    this.id = id;
    this.name = name;
    this.image = image;
    this.age = age;
    this.gender = gender;
    this.birthday = birthday;
    this.placeOfBirth = placeOfBirth;
  }

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getImage() {
    return image;
  }

  public int getAge() {
    return age;
  }

  public int getGender() {
    return gender;
  }

  public String getPlaceOfBirth() {
    return placeOfBirth;
  }

  public LocalDate getBirthday() {
    return birthday;
  }
}

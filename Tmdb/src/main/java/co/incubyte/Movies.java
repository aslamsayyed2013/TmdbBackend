package co.incubyte;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;

public class Movies extends CommonDto {

  private final String title;
  private final LocalDate releaseDate;

  public Movies(
      @JsonProperty("title") String title,
      @JsonProperty("release_date") LocalDate releaseDate) {
    this.title = title;
    this.releaseDate = releaseDate;
  }

  public String getTitle() {
    return title;
  }

  public LocalDate getReleaseDate() {
    return releaseDate;
  }
}

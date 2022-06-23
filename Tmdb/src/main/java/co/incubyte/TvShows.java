package co.incubyte;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;

public class TvShows extends CommonDto{

  private final String originalName;
  private final LocalDate firstAirDate;

  public TvShows(
      @JsonProperty("original_name") String originalName,
      @JsonProperty("first_air_date") LocalDate firstAirDate) {
    this.originalName = originalName;
    this.firstAirDate = firstAirDate;
  }

  @JsonProperty("original_name")
  public String getOriginalName() {
    return originalName;
  }

  @JsonProperty("first_air_date")
  public LocalDate getFirstAirDate() {
    return firstAirDate;
  }
}

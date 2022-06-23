package co.incubyte;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CombinedCredits {


  private final String originalName;
  private final String posterPath;
  private final String originalLanguage;
  private final int voteCount;

  public CombinedCredits(
      @JsonProperty("original_title") String originalName,
      @JsonProperty("poster_path") String posterPath,
      @JsonProperty("original_language") String originalLanguage,
      @JsonProperty("vote_count") int voteCount) {
    this.originalName = originalName;
    this.posterPath = posterPath;
    this.originalLanguage = originalLanguage;
    this.voteCount = voteCount;
  }

  public String getOriginalName() {
    return originalName;
  }

  public String getPosterPath() {
    return posterPath;
  }

  public String getOriginalLanguage() {
    return originalLanguage;
  }

  public int getVoteCount() {
    return voteCount;
  }
}

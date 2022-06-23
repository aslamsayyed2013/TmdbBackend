package co.incubyte;

import java.util.List;

public class MoviesWrapperDto {

  private List<Movies> cast;

  public List<Movies> getCast() {
    return cast;
  }

  public void setCast(List<Movies> cast) {
    this.cast = cast;
  }
}

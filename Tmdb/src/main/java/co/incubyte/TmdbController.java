package co.incubyte;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.QueryValue;
import java.util.List;
import java.util.Optional;

@Controller("tmdb")
public class TmdbController {

  TmdbService tmdbService;

  public TmdbController(TmdbService tmdbService) {
    this.tmdbService = tmdbService;
  }

  @Get("/{id}")
  public Optional<Artist> getByArtistId(Integer id) {
    return tmdbService.getArtistById(id);
  }

  @Get
  public Optional<List<Actor>> getByActorName(@QueryValue String query) {
    return tmdbService.getActorByName(query);
  }

  @Get("/{id}/movies")
  public Optional<List<Movies>> getMoviesByActorId(@PathVariable Integer id) {
    return tmdbService.getMoviesByActorId(id);
  }

  @Get("/{id}/tv_credits")
  public Optional<List<TvShows>> getTvShowsByActorId(@PathVariable Integer id) {
    return tmdbService.getTvShowsByActorId(id);
  }

  @Get("/{id}/combined_credits")
  public Optional<List<CombinedCredits>> getCombinedCreditsByActorId(@PathVariable Integer id) {
    return tmdbService.getCombinedCreditsByActorID(id);
  }
}

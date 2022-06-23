package co.incubyte;

import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.QueryValue;
import io.micronaut.http.client.annotation.Client;
import java.util.List;
import java.util.Optional;

@Client("/tmdb")
public interface TmdbTestOnlyClient {

  @Get
  Optional<List<Actor>> getByName(@QueryValue String query);

  @Get("/{id}")
  Optional<Artist> getById(Integer id);

  @Get("/{id}/movies")
  Optional<List<Movies>> getMoviesById(int id);

  @Get("/{id}/tv_credits")
  Optional<List<TvShows>> getTvShowsById(int id);

  @Get("/{id}/combined_credits")
  Optional<List<CombinedCredits>> getCombinedCredits(int id);
}

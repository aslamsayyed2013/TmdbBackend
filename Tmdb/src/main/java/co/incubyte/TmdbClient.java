package co.incubyte;

import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.QueryValue;
import io.micronaut.http.client.annotation.Client;
import java.util.Optional;


@Client("${tmdb.url}")
public interface TmdbClient {

  @Get("search/person{?query}{&api_key}")
  Optional<ActorDto> getActorByName(@QueryValue String query, @QueryValue("api_key") String apikey);

  @Get("person/{id}{?api_key}")
  Optional<Artist> getArtistById(Integer id, @QueryValue("api_key") String apikey);

  @Get("person/{id}/movie_credits{?api_key}")
  Optional<MoviesWrapperDto> getMoviesByActorId(Integer id, @QueryValue("api_key") String apikey);

  @Get("person/{id}/tv_credits{?api_key}")
  Optional<TvShowsWrapperDto> getTvShowsByActorId(Integer id, @QueryValue("api_key") String apikey);

  @Get("person/{id}/combined_credits{?api_key}")
  Optional<CombinedCreditsWrapperDto> getCombinedCreditsByActorId(Integer id,
      @QueryValue("api_key") String apikey);
}

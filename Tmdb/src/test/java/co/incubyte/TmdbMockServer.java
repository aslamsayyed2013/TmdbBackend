package co.incubyte;

import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.getRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.okJson;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.urlMatching;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.common.ConsoleNotifier;
import com.github.tomakehurst.wiremock.extension.responsetemplating.ResponseTemplateTransformer;

public class TmdbMockServer {

  private static final ObjectMapper mapper = new ObjectMapper();
  static WireMockServer wireMockServer;

  static {
    JavaTimeModule javaTimeModule = new JavaTimeModule();
    mapper.registerModule(javaTimeModule);
  }

  static void initWireMock() {
    wireMockServer =
        new WireMockServer(
            options()
                .port(3030)
                .notifier(new ConsoleNotifier(true))
                .extensions(new ResponseTemplateTransformer(false)));
    wireMockServer.start();
  }

  public static void startWithoutStubs() {
    if (wireMockServer == null || !wireMockServer.isRunning()) {
      initWireMock();
    }
  }

  public static void start() {
    if (wireMockServer == null || !wireMockServer.isRunning()) {
      startWithoutStubs();
    }
    addGetActorsByName();
    addGetActorsById();
    addMovieCreditsById();
    addTvCreditsById();
    addCombinedCreditsById();
  }

  private static void addGetActorsByName() {
    wireMockServer.stubFor(
        get(urlMatching("/search/person"))
            .withQueryParam("query",WireMock.equalTo("tom"))
            .withQueryParam("api_key", WireMock.equalTo("fd6bbf5b30d16dbe410178c8f6c2b0a8"))
            .willReturn(okJson(getTmdbActorBody()))
    );
  }

  public static void addGetActorsById() {
    wireMockServer.stubFor(
        get(urlMatching("/person/500"))
            .withQueryParam("api_key", WireMock.equalTo("fd6bbf5b30d16dbe410178c8f6c2b0a8"))
            .willReturn(okJson(getTmdbActorBody()))
    );
  }

  private static void addMovieCreditsById() {
    wireMockServer.stubFor(
        get(urlMatching("/person/500/movie_credits"))
            .withQueryParam("api_key", WireMock.equalTo("fd6bbf5b30d16dbe410178c8f6c2b0a8"))
            .willReturn(okJson(getTmdbMovieCreditBody()))
    );
  }

  private static void addTvCreditsById() {
    wireMockServer.stubFor(
        get(urlMatching("/person/500/tv_credits"))
            .withQueryParam("api_key", WireMock.equalTo("fd6bbf5b30d16dbe410178c8f6c2b0a8"))
            .willReturn(okJson(getTmdbTvCreditBody()))
    );
  }

  private static void addCombinedCreditsById() {
    wireMockServer.stubFor(
        get(urlMatching("/person/500/combined_credits"))
            .withQueryParam("api_key", WireMock.equalTo("fd6bbf5b30d16dbe410178c8f6c2b0a8"))
            .willReturn(okJson(getTmdbCombinedCreditBody()))
    );
  }

  private static String getTmdbCombinedCreditBody() {
    return "{\n"
        + "\t\"original_title\": \"War of the Worlds\",\n"
        + "\t\"poster_path\": \"/6Biy7R9LfumYshur3YKhpj56MpB.jpg\",\n"
        + "\t\"original_language\": \"en\",\n"
        + "\t\"vote_count\": \"6895\""
        + "}";
  }
  private static String getTmdbTvCreditBody() {
    return "{\n"
        + "\t\"noriginal_name\": \"The One Show\",\n"
        + "\t\"first_air_date\": \"2006,8,14\""
        + "}";
  }

  private static String getTmdbMovieCreditBody() {
    return "{\n"
        + "\t\"title\": \"Tom Cruise\",\n"
        + "\t\"release_date\": \"1992,12,11\""
        + "}";
  }

  private static String getTmdbActorBody() {
    return "{\n"
        + "\t\"name\": \"Tom Cruise\",\n"
        + "\t\"age\": \"0\",\n"
        + "\t\"gender\": \"2\",\n"
        + "\t\"profile_path\": \"/8qBylBsQf4llkGrWR3qAsOtOU8O.jpg\",\n"
        + "\t\"place_of_birth\": \"Syracuse, New York, USA\""
        + "}";
  }


  public static void verifyFindByActorsname(String query) {
    wireMockServer.verify(
        getRequestedFor(
            urlEqualTo(
                "/search/person/"))
            .withQueryParam("query",WireMock.equalTo(query))
            .withQueryParam("api_key", WireMock.equalTo("fd6bbf5b30d16dbe410178c8f6c2b0a8"))
    );
  }

  public static void verifyFindByIdActorsRequest(int id) {
    wireMockServer.verify(
        getRequestedFor(
            urlEqualTo(
                "/person/" + id + "?api_key=fd6bbf5b30d16dbe410178c8f6c2b0a8"))
            .withQueryParam("api_key", WireMock.equalTo("fd6bbf5b30d16dbe410178c8f6c2b0a8"))
    );
  }


  public static void verifyMovieCreditsByActorId(int id) {
    wireMockServer.verify(
        getRequestedFor(
            urlEqualTo(
                "/person/" + id + "/movie_credits?api_key=fd6bbf5b30d16dbe410178c8f6c2b0a8"))
            .withQueryParam("api_key", WireMock.equalTo("fd6bbf5b30d16dbe410178c8f6c2b0a8")));
  }

  public static void verifyTvCreditsByActorId(int id) {
    wireMockServer.verify(
        getRequestedFor(
            urlEqualTo(
                "/person/" + id + "/tv_credits?api_key=fd6bbf5b30d16dbe410178c8f6c2b0a8"))
            .withQueryParam("api_key", WireMock.equalTo("fd6bbf5b30d16dbe410178c8f6c2b0a8")));
  }

  public static void verifyCombinedCreditsByActorId(int id) {
    wireMockServer.verify(
        getRequestedFor(
            urlEqualTo(
                "/person/" + id + "/combined_credits?api_key=fd6bbf5b30d16dbe410178c8f6c2b0a8"))
            .withQueryParam("api_key", WireMock.equalTo("fd6bbf5b30d16dbe410178c8f6c2b0a8")));
  }

  public static void stop() {
    if (wireMockServer != null && wireMockServer.isRunning()) {
      wireMockServer.stop();
    }
  }
}

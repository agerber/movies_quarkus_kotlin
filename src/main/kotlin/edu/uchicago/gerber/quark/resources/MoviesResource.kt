package edu.uchicago.gerber.quark.resources

import com.github.javafaker.Faker
import edu.uchicago.gerber.quark.models.Movie
import edu.uchicago.gerber.quark.services.MovieService
import jakarta.enterprise.inject.Default
import jakarta.inject.Inject
import jakarta.ws.rs.*
import jakarta.ws.rs.core.MediaType
import java.util.stream.Collectors



@Path("/movies") @Produces(MediaType.APPLICATION_JSON) @Consumes(MediaType.APPLICATION_JSON)
class MoviesResource    constructor (){

    @field:Default
    @field:Inject
    lateinit  var  movieService: MovieService

    @get:GET
    val all: kotlin.collections.List<edu.uchicago.gerber.quark.models.Movie>
        get() {
            return movieService.findAll()
        }

    @POST
    fun add(movie: Movie?): kotlin.collections.List<Movie> {
        movieService.add(movie)
        return this.all
    }
    @GET @Path("{id}")    fun getFromId(@PathParam("id") id: kotlin.String): Movie {
        val movie: Movie? = movieService.get(id)
        if (null == movie){
            throw NotFoundException("The Movie with id " + id + " was not found")
        }
        return movie
    }
//    @GET @Path("/test")    fun testMe(): kotlin.collections.List<Movie> {
//        val faker: Faker = Faker()
//        return java.util.stream.Stream.generate<Movie>(java.util.function.Supplier<Movie> ({Movie(faker.chuckNorris().fact(), faker.beer().name(), faker.hashCode())})).limit(5).collect<kotlin.collections.List<Movie>, kotlin.Any>(Collectors.toList<Movie>())
//    }
    //https://www.technicalkeeda.com/java-mongodb-tutorials/java-mongodb-driver-3-3-0-pagination-example
//    @GET @Path("/paged/{page}")    fun paged(@PathParam("page") page: kotlin.Int): kotlin.collections.List<Movie> {
//        return movieService.paged(page)
//    }
}

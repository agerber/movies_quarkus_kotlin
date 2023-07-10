package edu.uchicago.gerber.quark.resources

import com.github.javafaker.Faker
import edu.uchicago.gerber.quark.models.Movie
import edu.uchicago.gerber.quark.services.MovieService
import jakarta.ws.rs.*
import jakarta.ws.rs.core.MediaType


@Path("/movies")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
class MoviesResource  (
    //use constructor injection in Kotlin instead of Dependency Injection in Java
    private val  movieService:MovieService) {


    @GET
    fun listAll(): List<Movie>{
        return movieService.listAll()
    }

    @POST
    fun add(movie: Movie?): List<Movie> {
        movieService.add(movie)
        return listAll()
    }
    @GET @Path("{id}")
    fun getFromId(@PathParam("id") id: String): Movie {
        val movie: Movie? = movieService.get(id)
        if (null == movie){
            throw NotFoundException("The Movie with id " + id + " was not found")
        }
        return movie
    }
    @GET @Path("/test")    fun testMe(): kotlin.collections.List<Movie> {
        val faker: Faker = Faker()
        val movies = mutableListOf<Movie>()
        var movie: Movie
        repeat(5) {
            //add some bogus movie using faker
            movie = Movie()
            movie.title = faker.beer().name()
            movie.year = faker.hashCode().toString()
            movies.add(movie)
        }
        return movies

    }
    //https://www.technicalkeeda.com/java-mongodb-tutorials/java-mongodb-driver-3-3-0-pagination-example
    @GET @Path("/paged/{page}")    fun paged(@PathParam("page") page: kotlin.Int): kotlin.collections.List<Movie> {
        return movieService.paged(page)
    }
}

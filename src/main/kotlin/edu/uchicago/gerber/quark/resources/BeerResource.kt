package edu.uchicago.gerber.quark.resources

import edu.uchicago.gerber.quark.models.Beer
import edu.uchicago.gerber.quark.services.BeerService
import jakarta.ws.rs.*
import jakarta.ws.rs.core.MediaType


@Path("/movies")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
class BeerResource  (
    //use constructor injection in Kotlin instead of Dependency Injection in Java
    private val  beerService:BeerService) {


    @GET
    fun listAll(): List<Beer>{
        return beerService.listAll()
    }

//    @POST
//    fun add(beer: Beer?): List<Beer> {
//        beerService.add(beer)
//        return listAll()
//    }
//    @GET @Path("{id}")
//    fun getFromId(@PathParam("id") id: String): Movie {
//        val movie: Movie? = beerService.get(id)
//        if (null == movie){
//            throw NotFoundException("The Movie with id " + id + " was not found")
//        }
//        return movie
//    }
    @GET @Path("/test")
     fun testMe(): kotlin.collections.List<Beer> {
           return beerService.gen5FakerBeers()
    }
    //https://www.technicalkeeda.com/java-mongodb-tutorials/java-mongodb-driver-3-3-0-pagination-example
//    @GET @Path("/paged/{page}")    fun paged(@PathParam("page") page: kotlin.Int): kotlin.collections.List<Movie> {
//        return beerService.paged(page)
//    }
}

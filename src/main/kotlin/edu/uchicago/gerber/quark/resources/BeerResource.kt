package edu.uchicago.gerber.quark.resources

import com.fasterxml.jackson.databind.BeanDescription
import edu.uchicago.gerber.quark.models.Beer
import edu.uchicago.gerber.quark.services.BeerService
import io.quarkus.mongodb.panache.kotlin.PanacheQuery
import jakarta.inject.Inject
import jakarta.ws.rs.*
import jakarta.ws.rs.core.MediaType


@Path("/beers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
class BeerResource
    //use constructor injection in Kotlin instead of Dependency Injection in Java)
{

    //add this import jakarta.transaction.Transactional to any thing that modifies the db

    @Inject
    lateinit var beerService:BeerService

    val TOTAL_PER_PAGE = 10

    @GET
    fun listAll(): List<Beer>{
        return beerService.listAll()
    }
    @GET
    @Path("/{page}")
    fun _paged(@PathParam("page") page: Int): List<Beer> {
        //this is lazy.
        val pagedBeers: PanacheQuery<Beer> = beerService.findAll()
        return pagedBeers.page(page, TOTAL_PER_PAGE).list()
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
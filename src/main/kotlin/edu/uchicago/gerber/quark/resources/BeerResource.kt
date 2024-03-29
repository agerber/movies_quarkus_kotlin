package edu.uchicago.gerber.quark.resources

import edu.uchicago.gerber.quark.models.Beer
import edu.uchicago.gerber.quark.models.Faked
import edu.uchicago.gerber.quark.services.BeerService
import io.quarkus.mongodb.panache.kotlin.PanacheQuery
import jakarta.inject.Inject
import jakarta.ws.rs.*
import jakarta.ws.rs.core.MediaType
import jakarta.transaction.Transactional
import jakarta.ws.rs.core.Response


@Path("/beers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
class BeerResource {

    @Inject
    lateinit var beerService:BeerService

    val TOTAL_PER_PAGE = 10

    //equivalent to CREATE
    @POST
    fun create(beer: Beer): List<Beer> {
        beerService.create(beer)
        return readAll()
    }


    //equivalent to READ

    @GET
    fun readAll(): List<Beer>{
        return beerService.readAll()
    }

    @GET
    @Path("{id}")
    fun readById(@PathParam("id") id: String): Beer {
       return beerService.readById(id)

    }

    @GET
    @Path("/paged/{page}")
    fun paged(@PathParam("page") page: Int): List<Beer> {
        //this is lazy.
        val pagedBeers: PanacheQuery<Beer>? = beerService.findAll()
        //the reason we use this if clause is to accommodate the SomeBeerRepository
        if (pagedBeers != null){
            return pagedBeers.page(page, TOTAL_PER_PAGE).list()
        } else {
            return Faked.genFakerBeers(TOTAL_PER_PAGE)
        }

    }

    //equivalent to UPDATE

    @PUT
    fun update(beer: Beer): List<Beer> {
        beerService.update(beer)
        return readAll()
    }


    //equivalent to DELETE

    @DELETE
    @Path("/{id}")
    fun deleteById(@PathParam("id")id: String): List<Beer> {
        beerService.deleteById(id)
        return readAll()
    }

    @DELETE
    fun deleteAll(){
        beerService.deleteAll()
    }

    //for TESTING

    @GET @Path("/test")
     fun testMe(): List<Beer> {
           return Faked.genFakerBeers(5)
    }

}

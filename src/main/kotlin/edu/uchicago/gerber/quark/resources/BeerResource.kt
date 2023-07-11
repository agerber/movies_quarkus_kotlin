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

    //add this import jakarta.transaction.Transactional to any thing that modifies the db

    @Inject
    lateinit var beerService:BeerService

    val TOTAL_PER_PAGE = 10

    //equivalent to CREATE
    @POST
    @Transactional
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

      //  return beerService.readById(id)
//        val beer: Beer
//        try {
//            beer = beerService.readById(id)
//            return  beer
//        } catch (e:Exception){
//            throw  WebApplicationException(Response.status(422).entity(e.message).build())
//        }

    }

    @GET
    @Path("/paged/{page}")
    fun paged(@PathParam("page") page: Int): List<Beer> {
        //this is lazy.
        val pagedBeers: PanacheQuery<Beer>? = beerService.findAll()
        if (pagedBeers != null){
            return pagedBeers.page(page, TOTAL_PER_PAGE).list()
        } else {
            return Faked.genFakerBeers(TOTAL_PER_PAGE)
        }

    }

    //equivalent to UPDATE

    @PUT
    @Transactional
    fun update(beer: Beer): List<Beer> {
        beerService.update(beer)
        return readAll()
    }


    //equivalent to DELETE

    @DELETE
    @Path("/{id}")
    @Transactional
    fun deleteById(@PathParam("id")id: String): List<Beer> {
        beerService.deleteById(id)
        return readAll()
    }

    @DELETE
    @Transactional
    fun deleteAll(){
        beerService.deleteAll()
    }

    //for TESTING

    @GET @Path("/test")
     fun testMe(): List<Beer> {
           return Faked.genFakerBeers(5)
    }

}

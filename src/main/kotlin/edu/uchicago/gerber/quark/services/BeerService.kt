package edu.uchicago.gerber.quark.services

import edu.uchicago.gerber.quark.models.Beer
import edu.uchicago.gerber.quark.models.Faked

import edu.uchicago.gerber.quark.repositories.MongoBeerRepository
import edu.uchicago.gerber.quark.repositories.SomeBeerRepository
import io.quarkus.mongodb.panache.kotlin.PanacheQuery
import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
import org.bson.types.ObjectId

@ApplicationScoped
//class MovieService (private val movieRepo: MovieMongodbRepo) {
    class BeerService  {

    @Inject
    lateinit var beerRepository: SomeBeerRepository


    fun create(beer: Beer){
        beerRepository._create(beer)
    }
    fun create(beers: List<Beer>){
        beerRepository._create(beers)
    }
    fun readById(id:String): Beer{
      return  beerRepository._readById(id)
    }
    fun readAll(): List<Beer>{
        return  beerRepository._readAll()
    }
    fun update(updatedBeer: Beer){
        return  beerRepository._update(updatedBeer)
    }
    fun deleteById(id:String){
        return  beerRepository._deleteById(id)
    }
    fun deleteById(id: ObjectId){
        return  beerRepository._deleteById(id)
    }
    fun deleteAll(){
        beerRepository._deleteAll()
    }
    fun count() : Long{
       return beerRepository._count()
    }
    fun findAll(): PanacheQuery<Beer>?{
        return beerRepository._findAll()

    }


//    fun listAll(): List<Beer>{
//        //just satisfy compiler
//        return beerRepository._readAll()
//    }
//
//    fun genFakerBeers(num: Int): List<Beer>{
//      return  Faked.genFakerBeers(num)
//    }
//
//    fun add( movie: Beer?): List<Beer>{
//        return listAll()
//    }
//
//    fun findAll(): PanacheQuery<Beer>?{
//
//      return beerRepository._findAll()
//
//
//    }
//
//    fun get(id:String): Beer? {
//        //just satisfy compiler
//        return null
//    }


}

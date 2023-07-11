package edu.uchicago.gerber.quark.services

import edu.uchicago.gerber.quark.models.Beer
import edu.uchicago.gerber.quark.models.Faked

import edu.uchicago.gerber.quark.repositories.MongoBeerRepository
import io.quarkus.mongodb.panache.kotlin.PanacheQuery
import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject

@ApplicationScoped
//class MovieService (private val movieRepo: MovieMongodbRepo) {
    class BeerService  {

    @Inject
    lateinit var beerRepository: MongoBeerRepository


    fun listAll(): List<Beer>{
        //just satisfy compiler
        return beerRepository._readAll()
    }

    fun gen5FakerBeers(): List<Beer>{
      return  Faked.genFakerBeers()
    }

    fun add( movie: Beer?): List<Beer>{
        return listAll()
    }

    fun findAll(): PanacheQuery<Beer>?{

      return beerRepository._findAll()


    }

    fun get(id:String): Beer? {
        //just satisfy compiler
        return null
    }


}

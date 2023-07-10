package edu.uchicago.gerber.quark.services

import edu.uchicago.gerber.quark.models.Beer
import edu.uchicago.gerber.quark.models.Movie
import edu.uchicago.gerber.quark.repositories.BeerRepository
import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject

@ApplicationScoped
//class MovieService (private val movieRepo: MovieMongodbRepo) {
    class BeerService  {

    @Inject
    lateinit var beerRepository: BeerRepository


    fun listAll(): List<Beer>{
        //just satisfy compiler
        return beerRepository.listAll()
    }

    fun generateTestData(): List<Beer>{
      return  beerRepository.genTestData()
    }

    fun add( movie: Beer?): List<Beer>{
        return listAll()
    }

    fun get(id:String): Beer? {
        //just satisfy compiler
        return null
    }
    fun paged(page: Int): List<Beer>{
      //  return movieRepo.paged(page)
        return emptyList<Beer>()
    }

}

package edu.uchicago.gerber.quark.services

import edu.uchicago.gerber.quark.models.Movie
import edu.uchicago.gerber.quark.repositories.MovieMongodbRepo
import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject

@ApplicationScoped
//class MovieService (private val movieRepo: MovieMongodbRepo) {
    class MovieService  {

    @Inject
    lateinit var movieRepo: MovieMongodbRepo


    fun listAll(): List<Movie>{
        //just satisfy compiler
        return movieRepo.listAll()
    }

    fun add( movie: Movie?): List<Movie>{
        return listAll()
    }

    fun get(id:String): Movie? {
        //just satisfy compiler
        return null
    }
    fun paged(page: Int): List<Movie>{
      //  return movieRepo.paged(page)
        return emptyList<Movie>()
    }

}

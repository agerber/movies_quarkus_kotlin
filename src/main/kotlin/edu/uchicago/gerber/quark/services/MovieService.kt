package edu.uchicago.gerber.quark.services

import edu.uchicago.gerber.quark.models.Movie
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
//class MovieService (private val movieRepo: MovieMongodbRepo) {
    class MovieService  {
    fun findAll(): List<Movie>{
        //just satisfy compiler
        return emptyList()
    }

    fun add( movie: Movie?): List<Movie>{
        return findAll()
    }

    fun get(id:String): Movie? {
        //just satisfy compiler
        return null
    }
    fun paged(page: Int): List<Movie>?{
      //  return movieRepo.paged(page)
        return null
    }

}

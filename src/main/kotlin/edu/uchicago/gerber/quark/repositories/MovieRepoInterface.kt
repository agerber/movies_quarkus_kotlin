package edu.uchicago.gerber.quark.repositories

import edu.uchicago.gerber.quark.models.Movie

interface MovieRepoInterface {
    fun findAll(): List<Movie>
    fun add(movie: Movie): List<Movie>
    fun get(id: String): Movie?
    fun paged(page: Int): List<Movie>
}

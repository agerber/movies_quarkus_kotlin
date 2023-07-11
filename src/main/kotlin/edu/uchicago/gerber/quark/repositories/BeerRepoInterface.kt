package edu.uchicago.gerber.quark.repositories

import edu.uchicago.gerber.quark.models.Beer
import org.bson.types.ObjectId

interface BeerRepoInterface {

    fun _create(beer: Beer)
    fun _create(beers: List<Beer>)
    fun _readById(id:String): Beer
    fun _readAll(): List<Beer>
    fun _update(updatedBeer: Beer)
    fun _deleteById(id:String)
    fun _deleteById(id: ObjectId)
    fun _deleteAll()
    fun _count() : Long

}

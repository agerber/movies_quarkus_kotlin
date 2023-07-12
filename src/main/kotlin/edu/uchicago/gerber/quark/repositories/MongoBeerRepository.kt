package edu.uchicago.gerber.quark.repositories

import edu.uchicago.gerber.quark.models.Beer
import edu.uchicago.gerber.quark.models.Faked
import io.quarkus.mongodb.panache.kotlin.PanacheMongoRepository
import io.quarkus.mongodb.panache.kotlin.PanacheQuery
import io.quarkus.runtime.StartupEvent
import jakarta.enterprise.context.ApplicationScoped
import jakarta.enterprise.event.Observes

import org.bson.types.ObjectId

@ApplicationScoped
class MongoBeerRepository: PanacheMongoRepository<Beer>, BeerRepoInterface {

    //this will get fired when the quarkus microservice starts
    fun onStart(@Observes ev: StartupEvent?) {
        if (count() == 0L){
            val list = mutableListOf<Beer>()
            repeat(23){ list.add(Faked.genRawEntity()) }
            persist(list)
        }


    }


    //todo remove unnessary dependencies
    //fix maven home and surefire.
    //add tests.


    //CREATE

    override fun _create(beer: Beer){
        this.persist(beer)
    }


    override fun _create(beers: List<Beer>){
        this.persist(beers)
    }
    //READ
    override fun _readById(id:String): Beer {
       val beerId = ObjectId(id)
       return this.findById(beerId) ?: throw Exception("No beer with that ID")
    }


    override fun _readAll(): List<Beer> {
        return  this.listAll()
        //we can also stream using this.streamAll()
    }

    //UPDATE

    override fun _update(updatedBeer: Beer) {
       this.update(updatedBeer);

    }

    //DELETE


    override fun _deleteById(id:String){
      val beerId = ObjectId(id)
      this.deleteById(beerId)
    }


    override fun _deleteAll(){
        this.deleteAll()
    }


    //COUNT
    override fun _count() : Long{
        return this.count()
    }

    //this returns a lazy query object
    override fun _findAll(): PanacheQuery<Beer>? {
        return this.findAll()
    }





}

package edu.uchicago.gerber.quark.models

import com.github.javafaker.Faker

object Faked {

    //automatically static public
     fun genRawEntity(): Beer{
        val faker = Faker()
        val fakerBeer = faker.beer()
        val beer = Beer()
        //we allow mongo to generate the id's for us
        //beer.id = ObjectId.get()
        beer.name = fakerBeer.name()
        beer.hop = fakerBeer.hop()
        beer.malt = fakerBeer.malt()
        beer.style = fakerBeer.style()
        beer.yeast = fakerBeer.yeast()
        return beer
    }

    fun genFakerBeers(num: Int): List<Beer>{
        val list = mutableListOf<Beer>()
        repeat(num){ list.add(genRawEntity()) }
        return list
    }
}

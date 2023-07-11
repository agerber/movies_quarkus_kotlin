package edu.uchicago.gerber.quark.models

import io.quarkus.mongodb.panache.kotlin.PanacheMongoEntity
import org.bson.types.ObjectId


//you will need this import jakarta.persistence.Entity;


class Beer{
    var id: ObjectId? = null // used by MongoDB for the _id field
    lateinit var name:String
    lateinit var hop:String
    lateinit var malt:String
    lateinit var style:String
    lateinit var yeast:String
}


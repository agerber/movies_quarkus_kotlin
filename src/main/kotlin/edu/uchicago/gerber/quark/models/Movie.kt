package edu.uchicago.gerber.quark.models

import org.bson.types.ObjectId

class Movie{
    var id: ObjectId? = null // used by MongoDB for the _id field
    lateinit var title:String
    lateinit var year:String //this will be a string representation of the year
}


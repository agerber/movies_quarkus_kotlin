package edu.uchicago.gerber.quark.models

import org.bson.types.ObjectId


class Beer{
    var id: ObjectId? = null // used by MongoDB for the _id field
    lateinit var name:String
    lateinit var hop:String
    lateinit var malt:String
    lateinit var style:String
    lateinit var yeast:String
}


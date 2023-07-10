package edu.uchicago.gerber.quark.repositories

import edu.uchicago.gerber.quark.models.Movie
import software.amazon.awssdk.services.dynamodb.model.AttributeValue
import software.amazon.awssdk.services.dynamodb.model.GetItemRequest
import software.amazon.awssdk.services.dynamodb.model.PutItemRequest
import software.amazon.awssdk.services.dynamodb.model.ScanRequest

abstract class AbstractDynamodbRepo {
    val tableName: String
        get() = "Movies"

    protected fun scanRequest(): ScanRequest {
        return ScanRequest.builder().tableName(tableName)
            .attributesToGet(MOVIE_ID_COL, MOVIE_TITLE_COL, MOVIE_YEAR_COL).build()
    }

    protected fun putRequest(movie: Movie): PutItemRequest {
        val item: MutableMap<String, AttributeValue> = HashMap()
        item[MOVIE_ID_COL] = AttributeValue.builder().s(movie.id).build()
        item[MOVIE_TITLE_COL] = AttributeValue.builder().s(movie.title).build()
        item[MOVIE_YEAR_COL] =
            AttributeValue.builder().n(movie.year.toString()).build()
        return PutItemRequest.builder()
            .tableName(tableName)
            .item(item)
            .build()
    }

    protected fun getRequest(id: String?): GetItemRequest {
        val key: MutableMap<String, AttributeValue> = HashMap()
        key[MOVIE_ID_COL] =
            AttributeValue.builder().s(id).build()
        return GetItemRequest.builder()
            .tableName(tableName)
            .key(key)
            .attributesToGet(MOVIE_ID_COL, MOVIE_TITLE_COL, MOVIE_YEAR_COL)
            .build()
    }

    companion object {
        const val MOVIE_ID_COL = "id"
        const val MOVIE_TITLE_COL = "title"
        const val MOVIE_YEAR_COL = "year"
    }
}

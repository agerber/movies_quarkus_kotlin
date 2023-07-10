package edu.uchicago.gerber.quark.repositories

import edu.uchicago.gerber.quark.models.Movie
import jakarta.enterprise.context.ApplicationScoped
import jakarta.enterprise.inject.Default
import jakarta.inject.Inject
import software.amazon.awssdk.services.dynamodb.DynamoDbClient
import software.amazon.awssdk.services.dynamodb.model.AttributeValue
import java.util.function.Function


@ApplicationScoped
class MovieDynamodbRepo : AbstractDynamodbRepo(), MovieRepoInterface {


//Dependency injection
//@field:Default
@field:Inject
lateinit  var  dynamoDB: DynamoDbClient

    override fun findAll(): List<Movie> {
        return dynamoDB.scanPaginator(scanRequest()).items().stream()
            .map<Movie>(Function<Map<String, AttributeValue>, Movie> { item: Map<String, AttributeValue>? ->
                transform(
                    item
                )
            })
            .collect<List<Movie>, Any>(Collectors.toList<Movie>())
    }

    override fun add(movie: Movie): List<Movie> {
        dynamoDB.putItem(putRequest(movie))
        return findAll()
    }

    override operator fun get(id: String): Movie {
        val item: Map<String, AttributeValue>
        item = dynamoDB.getItem(getRequest(id)).item()
        return if (null == item || item.size == 0) {
            null
        } else transform(item)
    }

    //for dynamodb
    private fun transform(item: Map<String, AttributeValue>?): Movie {
        val movie = Movie()
        if (item != null && !item.isEmpty()) {
            movie.id = item[MOVIE_ID_COL]!!.s()
            movie.title = item[MOVIE_TITLE_COL]!!.s()
            movie.year = item[MOVIE_YEAR_COL]!!.n().toInt()
        }
        return movie
    }

    override fun paged(page: Int): List<Movie> {
        //just return the entire recordset for Ddb for now. See mongoDB implementation of paged.
        return findAll()
    }
}

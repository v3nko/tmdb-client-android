package me.venko.tmdbclient.data.repo

import me.venko.tmdbclient.core.model.common.DataSourceException
import me.venko.tmdbclient.core.model.common.Result
import retrofit2.Response

/**
 * @author Victor Kosenko
 *
 */
abstract class BaseApiRepository {
    protected suspend fun <T : Any> performApiCall(
        errorHandler: ((Response<T>?, Throwable?) -> DataSourceException)? = null,
        call: suspend () -> Response<T>
    ): Result<T> {
        return try {
            val response = call.invoke()
            response.body().takeIf { response.isSuccessful }
                ?.let { Result.Success(it) }
                ?: Result.Error(errorHandler?.invoke(response, null) ?: DataSourceException())
        } catch (e: Exception) {
            Result.Error(errorHandler?.invoke(null, e) ?: DataSourceException(e.message, e))
        }
    }

}

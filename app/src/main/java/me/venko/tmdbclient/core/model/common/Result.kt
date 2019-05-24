package me.venko.tmdbclient.core.model.common

/**
 * @author Victor Kosenko
 *
 */
sealed class Result<out T : Any> {
    data class Success<out T : Any>(val data: T) : Result<T>()
    data class Error(val exception: DataSourceException) : Result<Nothing>()
}

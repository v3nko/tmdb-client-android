package me.venko.tmdbclient.core.model.common

/**
 * @author Victor Kosenko
 *
 */
class DataSourceException(
    message: String? = "Generic API error",
    cause: Throwable? = null
) : Exception(message, cause)

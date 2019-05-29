package me.venko.tmdbclient.presentation.common.glide

import android.net.Uri
import com.bumptech.glide.load.Options
import com.bumptech.glide.load.model.*
import com.bumptech.glide.load.model.stream.BaseGlideUrlLoader
import me.venko.tmdbclient.core.model.discovery.Movie
import java.io.InputStream

/**
 * @author Victor Kosenko
 *
 */
class TmdbImageLoader(
    concreteLoader: ModelLoader<GlideUrl, InputStream>,
    modelCache: ModelCache<TmdbImageModel, GlideUrl>?
) : BaseGlideUrlLoader<TmdbImageModel>(concreteLoader, modelCache) {

    override fun getUrl(model: TmdbImageModel?, width: Int, height: Int, options: Options?): String {
        return model?.imagePath?.let {
            Uri.Builder()
                .encodedPath(BASE_URL)
                .appendPath(getSizePath(model))
                .appendPath(it.replace("/", ""))
                .build()
                .toString()
        } ?: ""
    }

    private fun getSizePath(model: TmdbImageModel): String = when (model.imageType) {
        ImageType.POSTER -> SIZE_POSTER
        ImageType.BACKDROP -> SIZE_BACKDROP
    }

    override fun handles(model: TmdbImageModel) = true

    companion object {
        private const val BASE_URL = "https://image.tmdb.org/t/p/"

        private const val SIZE_BACKDROP = "w500"
        private const val SIZE_POSTER = "w185"
    }
}

class TmdbImageLoaderFactory : ModelLoaderFactory<TmdbImageModel, InputStream> {

    private val modelCache = ModelCache<TmdbImageModel, GlideUrl>(MODEL_CACHE_SIZE)

    override fun build(multiFactory: MultiModelLoaderFactory): ModelLoader<TmdbImageModel, InputStream> =
        TmdbImageLoader(multiFactory.build(GlideUrl::class.java, InputStream::class.java), modelCache)

    override fun teardown() = modelCache.clear()

    companion object {
        private const val MODEL_CACHE_SIZE = 500L
    }
}

data class TmdbImageModel(val imagePath: String?, val imageType: ImageType) {
    companion object {
        fun forPoster(movie: Movie): TmdbImageModel = TmdbImageModel(movie.posterPath, ImageType.POSTER)
        fun forBackdrop(movie: Movie): TmdbImageModel = TmdbImageModel(movie.backdropPath, ImageType.BACKDROP)
    }
}

enum class ImageType {
    POSTER, BACKDROP
}

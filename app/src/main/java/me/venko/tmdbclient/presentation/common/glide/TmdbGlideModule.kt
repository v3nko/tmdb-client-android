package me.venko.tmdbclient.presentation.common.glide

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.Registry
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule
import java.io.InputStream

/**
 * @author Victor Kosenko
 *
 */
@GlideModule
class TmdbGlideModule : AppGlideModule() {
    override fun registerComponents(context: Context, glide: Glide, registry: Registry) {
        super.registerComponents(context, glide, registry)
        registry.prepend(TmdbImageModel::class.java, InputStream::class.java, TmdbImageLoaderFactory())
    }
}

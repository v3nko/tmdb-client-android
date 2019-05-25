package me.venko.tmdbclient.presentation.discovery

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_movie.view.*
import me.venko.tmdbclient.R
import me.venko.tmdbclient.core.model.discovery.Movie
import me.venko.tmdbclient.presentation.common.glide.TmdbImageModel

/**
 * @author Victor Kosenko
 *
 */
class MoviesAdapter(val context: Context) : RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

    var items: MutableList<Movie> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        Glide.with(context)
            .load(TmdbImageModel.forPoster(item))
            .placeholder(R.drawable.img_default_placeholder)
            .into(holder.ivMoviePoster)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivMoviePoster: ImageView = itemView.ivMoviePoster
    }

}

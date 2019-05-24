package me.venko.tmdbclient.presentation.discovery

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_movie.view.*
import me.venko.tmdbclient.R
import me.venko.tmdbclient.core.model.discovery.Movie

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
        // TODO fetch image
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivMoviePoster: ImageView = itemView.ivMoviePoster
    }

}

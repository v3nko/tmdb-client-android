package me.venko.tmdbclient.presentation.discovery

import android.content.Context
import android.os.Bundle
import android.os.Parcelable
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.widget.ContentLoadingProgressBar
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_discovery_section.view.*
import me.venko.tmdbclient.R
import me.venko.tmdbclient.core.model.discovery.Movie
import me.venko.tmdbclient.presentation.common.recyclerview.HorizontalMarginItemDecoration
import me.venko.tmdbclient.presentation.common.view.ViewContentState


/**
 * @author Victor Kosenko
 *
 */
open class DiscoveryAdapter(
    val context: Context,
    private val lifecycleOwner: LifecycleOwner,
    private val sections: List<DiscoverySection>,
    private val onMovieClick: (Movie) -> Unit
) : RecyclerView.Adapter<ViewHolder>() {

    companion object {
        const val STATE_NESTED_STATES = "nestedStates"
    }

    // Set of ViewHolders that is already bound but have not yet saved their state through recycling
    private val stateSavePendingViewHolders = mutableSetOf<ViewHolder>()
    private var nestedStates = SparseArray<Parcelable?>()

    private val viewPool = RecyclerView.RecycledViewPool()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(
            R.layout.item_discovery_section,
            parent,
            false
        )
        return ViewHolder(v).apply {
            with(rvMovies) {
                layoutManager = LinearLayoutManager(context, LinearLayout.HORIZONTAL, false)
                addItemDecoration(
                    HorizontalMarginItemDecoration(resources.getDimension(R.dimen.view_margin_half_regular).toInt())
                )
            }
        }
    }

    override fun getItemCount() = sections.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val moviesAdapter = MoviesAdapter(context, onMovieClick)
        holder.rvMovies.apply {
            adapter = moviesAdapter
            nestedStates[holder.adapterPosition]?.let { layoutManager?.onRestoreInstanceState(it) }
            setRecycledViewPool(viewPool)
        }
        val item = sections[position]
        item.state.observe(lifecycleOwner, Observer {
            holder.rvMovies.visibility = if (it == ViewContentState.READY) View.VISIBLE else View.GONE
            if (it == ViewContentState.LOADING) {
                holder.pbLoading.show()
            } else {
                holder.pbLoading.hide()
            }
            with(holder.tvContentStatus) {
                when (it) {
                    ViewContentState.EMPTY -> {
                        visibility = View.VISIBLE
                        text = context.getString(R.string.tv_discovery_section_content_empty)
                    }
                    ViewContentState.ERROR -> {
                        visibility = View.VISIBLE
                        text = context.getString(R.string.tv_discovery_section_content_error)
                    }
                    else -> visibility = View.GONE
                }
            }
        })
        item.data.observe(lifecycleOwner, Observer {
            moviesAdapter.submitList(it)
        })

        stateSavePendingViewHolders.add(holder)
    }

    override fun onViewRecycled(holder: ViewHolder) {
        super.onViewRecycled(holder)
        saveViewState(holder)
        stateSavePendingViewHolders.remove(holder)
    }

    /**
     * Performs state saving for nested RecyclerViews
     *
     * @param outState Fragment's or Activity's state
     */
    fun onSaveInstanceState(outState: Bundle) {
        savePendingViewHolders()
        outState.putSparseParcelableArray(STATE_NESTED_STATES, nestedStates)
    }

    /**
     * Restores states of nested RecyclerViews. Should be called in Fragment's or Activity's onStart() callback
     *
     * @param pendingInstanceState Fragment's or Activity's state
     */
    fun onStart(pendingInstanceState: Bundle?) {
        pendingInstanceState?.let {
            nestedStates = it.getSparseParcelableArray(STATE_NESTED_STATES) ?: nestedStates
        }
    }

    /**
     * Performs state saving of pending RecyclerViews. It's necessary to call this method in Fragment's onStop()
     * callback for proper state restoring when host fragment is being restored from backstack
     */
    fun onStop() = savePendingViewHolders()

    private fun savePendingViewHolders() = stateSavePendingViewHolders.forEach { saveViewState(it) }

    private fun saveViewState(holder: ViewHolder) {
        nestedStates.put(holder.adapterPosition, holder.rvMovies.layoutManager?.onSaveInstanceState())
    }
}

class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val rvMovies: RecyclerView = itemView.rvMovies
    val pbLoading: ContentLoadingProgressBar = itemView.pbLoading
    val tvContentStatus: AppCompatTextView = itemView.tvContentStatus
}

data class DiscoverySection(
    val state: LiveData<ViewContentState>,
    val data: LiveData<PagedList<Movie>>
)

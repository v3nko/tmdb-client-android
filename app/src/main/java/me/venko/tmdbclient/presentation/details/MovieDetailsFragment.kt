package me.venko.tmdbclient.presentation.details

import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_movie_details.*
import me.venko.tmdbclient.R
import me.venko.tmdbclient.core.model.discovery.Movie
import me.venko.tmdbclient.presentation.common.formatter.DecimalFormatter
import me.venko.tmdbclient.presentation.common.fragment.BaseFragment
import me.venko.tmdbclient.presentation.common.glide.TmdbImageModel
import me.venko.tmdbclient.utils.withArguments

/**
 * @author Victor Kosenko
 *
 */
class MovieDetailsFragment : BaseFragment() {
    companion object {
        private const val ARG_MOVIE = "movie"

        fun newInstance(movie: Movie) = MovieDetailsFragment().withArguments(
            ARG_MOVIE to movie
        )
    }

    override val layoutId = R.layout.fragment_movie_details

    private val movie: Movie by lazy {
        arguments?.getParcelable(ARG_MOVIE) as Movie
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Glide.with(requireContext())
            .load(TmdbImageModel.forBackdrop(movie))
            .placeholder(R.drawable.img_default_placeholder)
            .into(ivBackdrop)
        tvMovieTitle.text = movie.title
        tvMovieRating.text = DecimalFormatter.formatRating(movie.voteAverage)
        tvMovieDescription.text = movie.overview
    }
}

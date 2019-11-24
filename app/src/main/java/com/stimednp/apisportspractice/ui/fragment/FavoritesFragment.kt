package com.stimednp.apisportspractice.ui.fragment


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.stimednp.apisportspractice.ui.anko.TeamDetailUI.Companion.swipeRefresh
import com.stimednp.apisportspractice.ui.fragment.TeamsFragment.Companion.listTeam
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.swipeRefreshLayout
import com.stimednp.apisportspractice.R.color.*
import com.stimednp.apisportspractice.data.Favorites
import com.stimednp.apisportspractice.data.db.database
import com.stimednp.apisportspractice.ui.activity.TeamDetailActivity
import com.stimednp.apisportspractice.ui.adapter.FavoriteTeamsAdapter
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select

/**
 * A simple [Fragment] subclass.
 */
class FavoritesFragment : Fragment() {
    private var favorites: MutableList<Favorites> = mutableListOf()
    private lateinit var adapter: FavoriteTeamsAdapter
    private lateinit var listTeam: RecyclerView
    private lateinit var swipeRefresh: SwipeRefreshLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return createView(AnkoContext.create(requireContext()))
    }

    private fun createView(ui: AnkoContext<Context>) = with(ui) {
        linearLayout {
            lparams(matchParent, wrapContent)
            topPadding = dip(16)
            leftPadding = dip(16)
            rightPadding = dip(16)

            swipeRefresh = swipeRefreshLayout {
                setColorSchemeResources(
                    colorAccent,
                    android.R.color.holo_green_light,
                    android.R.color.holo_orange_light,
                    android.R.color.holo_red_light
                )

                listTeam = recyclerView {
                    lparams(matchParent, wrapContent)
                    layoutManager = LinearLayoutManager(ctx)
                }
            }
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        adapter = FavoriteTeamsAdapter(favorites){
            context?.startActivity<TeamDetailActivity>("id" to "${it.teamId}")
        }
        listTeam.adapter = adapter
    }

    private fun showFavorite(){
        favorites.clear()
        context?.database?.use {
            swipeRefresh.isRefreshing = false
            val result = select(Favorites.TABLE_FAVORITE)
            val favorite = result.parseList(classParser<Favorites>())
            favorites.addAll(favorite)
            adapter.notifyDataSetChanged()
        }
    }

    override fun onResume() {
        super.onResume()
        showFavorite()
    }
}

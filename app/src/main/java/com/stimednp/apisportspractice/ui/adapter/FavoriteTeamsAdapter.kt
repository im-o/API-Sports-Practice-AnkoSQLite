package com.stimednp.apisportspractice.ui.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.stimednp.apisportspractice.R
import com.stimednp.apisportspractice.data.Favorites
import com.stimednp.apisportspractice.data.Teams
import com.stimednp.apisportspractice.ui.anko.TeamItemUI
import com.stimednp.apisportspractice.ui.fragment.TeamsFragment.Companion.teams
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.find

/**
 * Created by rivaldy on 11/23/2019.
 */

class FavoriteTeamsAdapter(
    private val favorite: List<Favorites>,
    private val listener: (Favorites) -> Unit
) :
    RecyclerView.Adapter<FavoriteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        return FavoriteViewHolder(
            TeamItemUI().createView(
                AnkoContext.Companion.create(
                    parent.context,
                    parent,
                    false
                )
            )
        )
    }

    override fun getItemCount() = favorite.size

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bindItem(favorite[position], listener)
    }
}

class FavoriteViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val teamBadge: ImageView = view.find(R.id.team_badge)
    private val teamName: TextView = view.find(R.id.team_name)
    fun bindItem(favorites: Favorites, listener: (Favorites) -> Unit) {
        Picasso.get().load(favorites.teamBadge).fit().into(teamBadge)
        teamName.text = favorites.teamName
        itemView.setOnClickListener{listener(favorites)}
    }
}

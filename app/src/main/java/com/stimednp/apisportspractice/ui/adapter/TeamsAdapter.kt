package com.stimednp.apisportspractice.ui.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.stimednp.apisportspractice.R
import com.stimednp.apisportspractice.data.Teams
import com.stimednp.apisportspractice.ui.anko.TeamItemUI
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.find

/**
 * Created by rivaldy on 11/7/2019.
 */

class TeamsAdapter(private val teams: List<Teams>, private val listener: (Teams) -> Unit): RecyclerView.Adapter<TeamsAdapter.TeamViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder {
        return TeamViewHolder(
            TeamItemUI().createView(
                AnkoContext.create(
                    parent.context,
                    parent
                )
            )
        )
    }

    override fun getItemCount() = teams.size

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {
        holder.bindItem(teams[position], listener)
    }

    class TeamViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val teamBadge: ImageView = view.find(R.id.team_badge)
        private val teamName: TextView = view.find(R.id.team_name)
        fun bindItem(teams: Teams, listener: (Teams) -> Unit) {
            Picasso.get().load(teams.teamBadge).fit().into(teamBadge)
            teamName.text = teams.teamName
            itemView.setOnClickListener{listener(teams)}
        }
    }

}

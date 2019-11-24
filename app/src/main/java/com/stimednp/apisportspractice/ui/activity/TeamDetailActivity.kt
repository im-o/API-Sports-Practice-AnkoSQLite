package com.stimednp.apisportspractice.ui.activity

import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import com.stimednp.apisportspractice.R.id.add_to_favorite
import com.stimednp.apisportspractice.R.menu.detail_menu
import com.stimednp.apisportspractice.api.ApiRepository
import com.stimednp.apisportspractice.api.TeamDetailPresenter
import com.stimednp.apisportspractice.data.Favorites
import com.stimednp.apisportspractice.data.Teams
import com.stimednp.apisportspractice.data.db.database
import com.stimednp.apisportspractice.invisible
import com.stimednp.apisportspractice.ui.ITeamDetailView
import com.stimednp.apisportspractice.ui.anko.TeamDetailUI
import com.stimednp.apisportspractice.ui.anko.TeamDetailUI.Companion.progressBar
import com.stimednp.apisportspractice.ui.anko.TeamDetailUI.Companion.swipeRefresh
import com.stimednp.apisportspractice.ui.anko.TeamDetailUI.Companion.teamBadge
import com.stimednp.apisportspractice.ui.anko.TeamDetailUI.Companion.teamDescription
import com.stimednp.apisportspractice.ui.anko.TeamDetailUI.Companion.teamFormedYear
import com.stimednp.apisportspractice.ui.anko.TeamDetailUI.Companion.teamName
import com.stimednp.apisportspractice.ui.anko.TeamDetailUI.Companion.teamStadium
import com.stimednp.apisportspractice.visible
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.setContentView
import org.jetbrains.anko.support.v4.onRefresh

class TeamDetailActivity : AppCompatActivity(), ITeamDetailView {
    private lateinit var presenter: TeamDetailPresenter
    private lateinit var teams: Teams
    private lateinit var id: String

    private var menuItem: Menu? = null
    private var isFavorite: Boolean? = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        TeamDetailUI().setContentView(this)
        supportActionBar?.title = "Team Detail"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val intent = intent
        id = intent?.getStringExtra("id")!!
        val request = ApiRepository()
        val gson = Gson()
        presenter = TeamDetailPresenter(this, request, gson)
        presenter.getDetailTeam(id)
        swipeRefresh.onRefresh {
            presenter.getDetailTeam(id)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(detail_menu, menu)
        menuItem = menu
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            add_to_favorite -> {
                addToFavorite()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun showLoading() {
        progressBar.visible()
    }

    private fun addToFavorite() {
        try {
            database.use {
                insert(
                    Favorites.TABLE_FAVORITE,
                    Favorites.TEAM_ID to teams.teamId,
                    Favorites.TEAM_NAME to teams.teamName,
                    Favorites.TEAM_BADGE to teams.teamBadge
                )
            }
            swipeRefresh.snackbar("Added to favorites").show()
        } catch (er: SQLiteConstraintException) {
            swipeRefresh.snackbar(er.localizedMessage).show()
        }
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showTeamDetail(data: List<Teams>) {
        teams = Teams(
            data[0].teamId,
            data[0].teamName,
            data[0].teamBadge
        )
        swipeRefresh.isRefreshing = false
        Picasso.get().load(data[0].teamBadge).into(teamBadge)
        teamName.text = data[0].teamName
        teamDescription.text = data[0].teamDescription
        teamFormedYear.text = data[0].teamFormedYear
        teamStadium.text = data[0].teamStadium
    }
}

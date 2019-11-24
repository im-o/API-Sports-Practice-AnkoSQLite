package com.stimednp.apisportspractice.ui.fragment


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.gson.Gson
import com.stimednp.apisportspractice.R
import com.stimednp.apisportspractice.api.ApiRepository
import com.stimednp.apisportspractice.api.TeamPresenter
import com.stimednp.apisportspractice.data.Teams
import com.stimednp.apisportspractice.invisible
import com.stimednp.apisportspractice.ui.adapter.TeamsAdapter
import com.stimednp.apisportspractice.ui.TeamsView
import com.stimednp.apisportspractice.ui.activity.TeamDetailActivity
import com.stimednp.apisportspractice.visible
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout
import org.jetbrains.anko.support.v4.toast

/**
 * A simple [Fragment] subclass.
 */
class TeamsFragment : Fragment(), AnkoComponent<Context>, TeamsView {
    companion object {

        val teams: MutableList<Teams> = mutableListOf()
        lateinit var presenter: TeamPresenter
        lateinit var adapter: TeamsAdapter
        lateinit var leagueName: String

        lateinit var listTeam: RecyclerView
        lateinit var progressBar: ProgressBar
        lateinit var swipeRefreshLayout: SwipeRefreshLayout
        lateinit var spinner: Spinner
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        adapter = TeamsAdapter(teams){
            context?.startActivity<TeamDetailActivity>("id" to "${it.teamId}")
        }
        listTeam.adapter = adapter

        val request = ApiRepository()
        val gson = Gson()
        presenter = TeamPresenter(this, request, gson)

        val spinnerItems = resources.getStringArray(R.array.league)
        val spinnerAdapter = ArrayAdapter(requireContext(), R.layout.spinner_item, spinnerItems)
        spinner.adapter = spinnerAdapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                leagueName = spinner.selectedItem.toString()
                presenter.getTeamList(
                    leagueName
                )
                presenter.getTeamList(
                    leagueName
                )
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }
        swipeRefreshLayout.onRefresh {
            presenter.getTeamList(
                leagueName
            )
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return createView(AnkoContext.Companion.create(requireContext()))
    }

    override fun createView(ui: AnkoContext<Context>): View {
        return with(ui) {
            linearLayout() {
                lparams(matchParent, wrapContent)
                orientation = LinearLayout.VERTICAL
                padding = dip(16)

                spinner = spinner() {
                    id = R.id.spinner
                }
                swipeRefreshLayout = swipeRefreshLayout {
                    setColorSchemeResources(
                        R.color.colorAccent,
                        R.color.colorPrimary,
                        R.color.colorPrimaryDark,
                        R.color.colorAccent
                    )
                    relativeLayout() {
                        lparams(matchParent, wrapContent)

                        listTeam = recyclerView() {
                            lparams(matchParent, wrapContent)
                            layoutManager = LinearLayoutManager(context)
                        }
                        progressBar = progressBar() {}.lparams { centerHorizontally() }
                    }
                }
            }
        }
    }
//
    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showTeamList(data: List<Teams>) {
        swipeRefreshLayout.isRefreshing = false
        teams.clear()
        teams.addAll(data)
        adapter.notifyDataSetChanged()
    }

}

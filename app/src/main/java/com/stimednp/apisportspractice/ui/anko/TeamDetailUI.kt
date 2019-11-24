package com.stimednp.apisportspractice.ui.anko

import android.graphics.Color
import android.view.Gravity
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat.getColor
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.stimednp.apisportspractice.ui.activity.TeamDetailActivity
import org.jetbrains.anko.*
import org.jetbrains.anko.support.v4.swipeRefreshLayout
import com.stimednp.apisportspractice.R.color.*

/**
 * Created by rivaldy on 11/23/2019.
 */

class TeamDetailUI : AnkoComponent<TeamDetailActivity> {
    companion object {
        lateinit var progressBar: ProgressBar
        lateinit var swipeRefresh: SwipeRefreshLayout

        lateinit var teamBadge: ImageView
        lateinit var teamName: TextView
        lateinit var teamFormedYear: TextView
        lateinit var teamStadium: TextView
        lateinit var teamDescription: TextView
    }

    override fun createView(ui: AnkoContext<TeamDetailActivity>) = with(ui) {
        linearLayout {
            lparams(matchParent, wrapContent)
            orientation = LinearLayout.VERTICAL
            backgroundColor = Color.WHITE

            swipeRefresh = swipeRefreshLayout(){
                setColorSchemeResources(colorAccent,
                    android.R.color.holo_green_light,
                    android.R.color.holo_orange_light,
                    android.R.color.holo_red_light)

                scrollView {
                    isVerticalScrollBarEnabled = false
                    relativeLayout {
                        lparams(matchParent, wrapContent)
                        linearLayout {
                            lparams(matchParent, wrapContent)
                            padding = dip(10)
                            orientation = LinearLayout.VERTICAL
                            gravity = Gravity.CENTER_HORIZONTAL

                            teamBadge = imageView {}.lparams(height = dip(75))
                            teamName = textView {
                                this.gravity = Gravity.CENTER
                                textSize = 20f
                                textColor = getColor(context, colorAccent)
                            }.lparams{
                                topMargin = dip(5)
                            }
                            teamFormedYear = textView { this.gravity = Gravity.CENTER }
                            teamStadium = textView {
                                this.gravity = Gravity.CENTER
                                textColor = getColor(context, colorPrimaryText)
                            }
                            teamDescription = textView().lparams{topMargin = dip(20)}
                        }
                        progressBar = progressBar().lparams{centerHorizontally()}
                    }
                }
            }

        }
    }
}
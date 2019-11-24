package com.stimednp.apisportspractice.ui.anko

import android.view.ViewGroup
import android.widget.LinearLayout
import com.stimednp.apisportspractice.R
import org.jetbrains.anko.*

/**
 * Created by rivaldy on 11/7/2019.
 */

class TeamItemUI: AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>) = with(ui) {
        linearLayout {
            lparams(matchParent, wrapContent)
            padding = dip(16)
            orientation = LinearLayout.HORIZONTAL

            imageView {
                id = R.id.team_badge
            }.lparams (dip(50), dip(50))

            textView {
                id = R.id.team_name
                textSize = 16f
            }.lparams{
                margin = dip(16)
            }
        }
    }
}
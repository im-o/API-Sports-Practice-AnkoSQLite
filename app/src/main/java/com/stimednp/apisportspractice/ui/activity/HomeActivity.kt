package com.stimednp.apisportspractice.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.stimednp.apisportspractice.R
import com.stimednp.apisportspractice.R.id.*
import com.stimednp.apisportspractice.ui.fragment.FavoritesFragment
import com.stimednp.apisportspractice.ui.fragment.TeamsFragment
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        initiasl(savedInstanceState)
    }

    private fun initiasl(savedInstanceState: Bundle?) {
        bottom_navigation.setOnNavigationItemSelectedListener { item ->
            when(item.itemId){
                teams_item -> {loadFragment(savedInstanceState, TeamsFragment())}
                favorites_item -> {loadFragment(savedInstanceState, FavoritesFragment())}
            }
            true
        }
        bottom_navigation.selectedItemId = teams_item
    }

    private fun loadFragment(savedInstanceState: Bundle?, fragment: Fragment) {
        if (savedInstanceState == null){
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_container, fragment, fragment::class.java.simpleName)
                .commit()
        }
    }
}

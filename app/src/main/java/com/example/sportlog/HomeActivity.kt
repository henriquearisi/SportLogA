package com.example.sportlog

import CalendarFragment
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

@Suppress("DEPRECATION")
class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val bottomNavigation: BottomNavigationView = findViewById(R.id.bottom_navigation)

        // Definir fragmento inicial
        replaceFragment(CalendarFragment())

        bottomNavigation.setOnNavigationItemSelectedListener { item ->
            var selectedFragment: Fragment = CalendarFragment()

            when (item.itemId) {
                R.id.nav_calendar -> selectedFragment = CalendarFragment()
                R.id.nav_performance -> selectedFragment = PerformanceFragment()
                R.id.nav_reports -> selectedFragment = ReportsFragment()
            }

            replaceFragment(selectedFragment)
            true
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}

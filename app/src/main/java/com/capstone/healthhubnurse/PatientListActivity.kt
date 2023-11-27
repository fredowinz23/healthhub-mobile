package com.capstone.healthhubnurse

import android.content.Intent
import android.os.Bundle
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.capstone.healthhubnurse.databinding.ActivityPatientListBinding

class PatientListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPatientListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPatientListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = binding.viewPager
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = binding.tabs
        tabs.setupWithViewPager(viewPager)
//        val fab: FloatingActionButton = binding.fab

        binding.ivBack.setOnClickListener {
            finish()
        }

        binding.fab.setOnClickListener { _ ->
            startActivity(Intent(this@PatientListActivity, PatientFormActivity::class.java))
        }
    }
}
package com.dam.viewpagertablayout

import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdaptador(activity: AppCompatActivity, var itemsCount: Int) :
    FragmentStateAdapter(activity) {

}
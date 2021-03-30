package com.example.gfood.screen.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import com.example.gfood.utils.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.ViewPager
import com.example.gfood.R
import com.example.gfood.screen.category.CategoryFragment
import com.example.gfood.screen.favourite.FavouriteFragment
import com.example.gfood.screen.search.SearchFragment
import com.example.gfood.utils.Constant
import kotlinx.android.synthetic.main.fragment_page.viewPagerLayout
import kotlinx.android.synthetic.main.fragment_page.bottomNavigationView

class PageFragment private constructor() : Fragment() {

    private val fragments = mutableListOf<Fragment>()
    private val categoryFragment = CategoryFragment.newInstance()
    private val searchFragment = SearchFragment.newInstance()
    private val favouriteFragment = FavouriteFragment.newInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initFragment()
        fragmentManager?.let {
            viewPagerLayout.adapter = ViewPagerContainerAdapter(it, fragments)
            viewPagerLayout.offscreenPageLimit = Constant.LIMIT_OFFSET
            initBottomItem()
        }
    }

    private fun initFragment() {
        fragments.apply {
            add(MenuItem.CATEGORY.ordinal, categoryFragment)
            add(MenuItem.SEARCH.ordinal, searchFragment)
            add(MenuItem.FAVOURITE.ordinal, favouriteFragment)
        }
    }

    private fun initBottomItem() {
        bottomNavigationView.setOnNavigationItemReselectedListener {
            when (it.itemId) {
                R.id.homeItem -> {
                    viewPagerLayout.currentItem = MenuItem.CATEGORY.ordinal
                    true
                }
                R.id.searchItem -> {
                    viewPagerLayout.currentItem = MenuItem.SEARCH.ordinal
                    true
                }
                R.id.favouriteItem -> {
                    viewPagerLayout.currentItem = MenuItem.FAVOURITE.ordinal
                    true
                }
                else -> false
            }
        }
        viewPagerLayout.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                bottomNavigationView.menu.getItem(position).isChecked = true
            }

            override fun onPageScrollStateChanged(position: Int) {
            }
        })
    }

    companion object {
        fun newInstance() = PageFragment()
    }
}

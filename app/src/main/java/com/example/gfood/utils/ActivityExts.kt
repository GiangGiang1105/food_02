package com.example.gfood.utils

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> Unit) {
    beginTransaction().apply() {
        func()
        commit()
    }
}

fun AppCompatActivity.addFragment(fragment: Fragment, id: Int) {
    supportFragmentManager.inTransaction {
        add(id, fragment)
    }
}

fun AppCompatActivity.replaceFragment(fragment: Fragment, id: Int) {
    supportFragmentManager.inTransaction {
        replace(id, fragment)
        addToBackStack(null)
    }
}

fun AppCompatActivity.removeFragment(fragment: Fragment) {
    supportFragmentManager.inTransaction { remove(fragment) }
}

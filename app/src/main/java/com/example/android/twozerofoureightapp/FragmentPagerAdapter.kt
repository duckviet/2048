package com.example.android.twozerofoureightapp

import android.content.Intent
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.twozerofoureight.R
import com.example.android.puzzle.MainPuzzleActivity
import com.example.android.twozerofoureightapp.MainActivity

class GamePagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> FirstGameFragment() // Change to your first game fragment
            1 -> SecondGameFragment() // Change to your second game fragment
            else -> throw IllegalArgumentException("Invalid position: $position")
        }
    }

    override fun getCount(): Int {
        return 2 // Number of games
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "First Game" // Title for first game tab
            1 -> "Second Game" // Title for second game tab
            else -> null
        }
    }
}
class FirstGameFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_first_game, container, false)
        val firstGameStartBtn: Button = rootView.findViewById(R.id.first_game_start_btn)
        firstGameStartBtn.setOnClickListener {
            val intent = Intent(activity, MainActivity::class.java)
            startActivity(intent)
        }
        return rootView
    }
}
class SecondGameFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_second_game, container, false)
        val secondGameStartBtn: Button = rootView.findViewById(R.id.second_game_start_btn)
        secondGameStartBtn.setOnClickListener {
            val intent = Intent(activity, MainPuzzleActivity::class.java)
            startActivity(intent)
        }
        return rootView
    }
}
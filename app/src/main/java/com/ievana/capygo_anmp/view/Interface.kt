package com.ievana.capygo_anmp.view

import android.view.View
import com.ievana.capygo_anmp.model.Team

interface ButtonClickListener{
    fun onButtonClick(v:View)
}
interface TeamClickListerner{
    fun teamClick(v:View)
}

interface AchieveClickListerner{
    fun achieveClick(v: View)
}
interface LikeTeamClickListener{
    fun likeTeamClick(team:Team)
}
interface SignUpClickListener{
    fun signUpClick(v: View)
}

interface DetailTeamClickListener{
    fun detailTeamClick(v:View)
}
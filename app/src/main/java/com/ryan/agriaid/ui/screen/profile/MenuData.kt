package com.ryan.agriaid.ui.screen.profile

import android.content.Context

data class MenuData(
   val icon: Int,
   val title: String,
   val develop: Boolean = false,
   val onClick: (context: Context) -> Unit
)
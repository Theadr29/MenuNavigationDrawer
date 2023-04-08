package com.AdrianoDev.ADS_Ecommerce.View.Menu

import android.graphics.drawable.Icon
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter


data class MenuItem(
        val id: String,
        val title: String,
        val contentDescription: String,
        val icon: ImageVector,
        val onClick: () -> Unit,
        val tint:Color
        )

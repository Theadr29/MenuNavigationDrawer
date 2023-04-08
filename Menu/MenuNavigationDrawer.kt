package com.AdrianoDev.ADS_Ecommerce.View

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.rememberScaffoldState

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.navOptions
import com.AdrianoDev.ADS_Ecommerce.R
import com.AdrianoDev.ADS_Ecommerce.View.Menu.MenuItem
import com.AdrianoDev.ADS_Ecommerce.View.Theme.Grena
import com.AdrianoDev.ADS_Ecommerce.View.Theme.InstaIcon
import com.AdrianoDev.ADS_Ecommerce.View.Theme.Purple700
import com.AdrianoDev.ADS_Ecommerce.ViewModel.MyViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun DrawerHeader() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 64.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Header", fontSize = 60.sp)
    }
}


@Composable
fun DrawerBody(
    items: List<MenuItem>,
    onItemClick: (MenuItem) -> Unit,
    modifier: Modifier = Modifier,
    itemTextStyle: TextStyle = TextStyle(fontSize = 18.sp)
) {
    val scope = rememberCoroutineScope()

    LazyColumn(modifier) {
        items(items) { item ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        onItemClick(item)
                    }
                    .padding(16.dp)
            ) {
                Icon(
                    imageVector = item.icon,
                    contentDescription = item.contentDescription,
                    tint = item.tint
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = item.title,
                    style = itemTextStyle,
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}
@Composable
fun Context(){
    val context = LocalContext.current

}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MenuNavigationDrawer(viewModel: MyViewModel, navController: NavController) {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                //TITULO O LOGO OU SEJA UMA IMAGEM!!
                title = {
                    Image(
                        painter = painterResource(id = R.drawable.adslogo),
                        contentDescription = "Logo do App",
                        modifier = Modifier
                            .width(230.dp)
                    )
                },
                backgroundColor = Purple700, // define a cor de fundo como azul

                //AÇÃO DO BOTÃO DO MENU!!
                navigationIcon = {
                    IconButton(onClick = {
                        //ELE ABRE!!
                        scope.launch {
                            scaffoldState.drawerState.open()
                        }
                    }) {
                        //ICONE DO MENU!!
                        Icon(
                            painterResource(id = R.drawable.grupo_3),
                            contentDescription = "MenuIcon",
                            tint = Color.Unspecified
                        )
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()

            )
        },
        drawerGesturesEnabled = scaffoldState.drawerState.isOpen,
        drawerContent = {
            Column(
                modifier = Modifier.fillMaxHeight() // adiciona o fillMaxHeight aqui
            ) {
                DrawerHeader()
                DrawerBody(
                    items = listOf(
                        MenuItem(
                            id = "insta",
                            title = "Instagram",
                            icon = InstaIcon,
                            tint = Grena,
                            contentDescription = "Insta",
                            onClick = { viewModel.navigateToHome(navController) }
                        ),
                        MenuItem(
                            id = "sobre",
                            title = "Settings",
                            icon = Icons.Default.Settings,
                            tint = Grena,
                            contentDescription = "Go to settings",
                            onClick = { viewModel.navigateToSobre(navController) },
                        ),
                        MenuItem(
                            id = "help",
                            title = "Help",
                            icon = Icons.Default.Info,
                            tint = Grena,
                            contentDescription = "Open help",
                            onClick = { Log.d("MyApp", "User clicked on Help menu item") },
                        )
                    ),

                    onItemClick = { item ->
                        CoroutineScope(Dispatchers.Main).launch {
                            delay(2000)

                            navController.navigate(
                                item.id,
                                navOptions {
                                    this.launchSingleTop = true
                                    this.restoreState = true
                                }
                            )
                        }
                    }
                )
            }
        }){

    }
}

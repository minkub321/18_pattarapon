package com.pattarapon.pscmenu.activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.pattarapon.pscmenu.R
import com.google.android.material.navigation.NavigationView
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    lateinit var navController: NavController
    lateinit var appBarConfiguration: AppBarConfiguration
    lateinit var navHostFragment: NavHostFragment
    lateinit var mainToolbar: Toolbar
    lateinit var mainDrawerLayout: DrawerLayout
    lateinit var mainNavigationView: NavigationView
    lateinit var mainBottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // เชื่อม View กับตัวแปร
        mainToolbar = findViewById(R.id.main_Toolbar)
        mainDrawerLayout = findViewById(R.id.drawerlayout)
        mainNavigationView = findViewById(R.id.main_navigation_view)
        mainBottomNavigationView = findViewById(R.id.bottomnavview)

        // เชื่อมกับ NavController
        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragment) as NavHostFragment
        navController = navHostFragment.navController

        // AppBar Configuration
        appBarConfiguration = AppBarConfiguration.Builder(
            R.id.homeFragment,
            R.id.productFragment,
            R.id.addproductFragment,
            R.id.notificationFragment,
            R.id.accountFragment
        ).setOpenableLayout(mainDrawerLayout).build()

        // Toolbar
        setSupportActionBar(mainToolbar)
        setupActionBarWithNavController(navController, appBarConfiguration)

        // Navigation Drawer
        mainNavigationView.setupWithNavController(navController)

        // Bottom Navigation
        mainBottomNavigationView.setupWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, appBarConfiguration)
    }
}

package com.pintu.kotlin_task

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.pintu.kotlin_task.frag_viewpager_tablayout.Frag_ViewPager
import com.pintu.kotlin_task.fragment.Fragment
import com.pintu.kotlin_task.view.Recycler.Recycler_Ex
import com.pintu.kotlin_task.view.note.MainNote

class MainActivity : AppCompatActivity(),NavigationView.OnNavigationItemSelectedListener {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView
    lateinit var toolbar: Toolbar

    private lateinit var headerImg : ImageView
    private lateinit var header_Name : TextView
    private lateinit var header_email : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        drawerLayout = findViewById(R.id.drawer_layout)
        navigationView = findViewById(R.id.nav_view)
        //Menu counter = navigationView.getMenu();
        //notification = (TextView) counter.findItem(R.id.tv_notification);
        //notification = navigationView.menu.findItem(R.id.nav_myCart).actionView as TextView

        val headerContainer = navigationView.getHeaderView(0)
        headerImg = headerContainer.findViewById(R.id.header_img)
        header_Name = headerContainer.findViewById(R.id.header_name)
        header_email = headerContainer.findViewById(R.id.header_email)

        toolbar = findViewById<Toolbar>(R.id.toolbar)

        setSupportActionBar(toolbar)


        /*----------------------Navigation Drawer Menu-------------------------*/

//        TextDrawable drawable = TextDrawable.builder().buildRect("A", Color.RED);
//        headerImg.setImageDrawable(drawable);


        /*----------------------Navigation Drawer Menu-------------------------*/
        navigationView.bringToFront()
        val toggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navigationView.setNavigationItemSelectedListener(this)
        navigationView.setCheckedItem(R.id.nav_fragment)

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_fragment -> {
                Toast.makeText(this, "Fragment ", Toast.LENGTH_SHORT).show()
                val intent = Intent(this,Fragment::class.java)
                startActivity(intent)
            }
            R.id.nav_frag_viewPager -> {
                Toast.makeText(this, "Frag_ViewPager", Toast.LENGTH_SHORT).show()
                val intent = Intent(this,Frag_ViewPager::class.java)
                startActivity(intent)
            }
            R.id.nav_recycler -> {
                Toast.makeText(this, "Recycler Example", Toast.LENGTH_SHORT).show()
                val intent = Intent(this,Recycler_Ex::class.java)
                startActivity(intent)
            }
            R.id.nav_room -> {
                Toast.makeText(this, "ROOM DATABASE", Toast.LENGTH_SHORT).show()
                val intent = Intent(this,MainNote::class.java)
                startActivity(intent)
            }

        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
}
package com.healtdiary.app

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.healtdiary.app.ui.data.DataFragmentDirections
import com.healtdiary.app.ui.data.DataViewModel
import com.healtdiary.app.ui.entry.EntryFragmentDirections

class MainActivity : AppCompatActivity() {

    val TAG = "MainActivity"

    private var navController: NavController? = null

    private lateinit var viewModel: DataViewModel

    private var editableEntry = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main_3)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val fab: FloatingActionButton = findViewById(R.id.fab)

        viewModel = ViewModelProvider(this)[DataViewModel::class.java]

        navController = findNavController(R.id.nav_host_fragment)

        fab.setOnClickListener {
            Log.d(TAG, "Fab click")

            Log.d("EDITMODE: ", viewModel.editStatus.value.toString())
            Log.d("HABECK ", navController!!.currentDestination!!.label.toString())

            // New Entry
            // Dairy entries

            if (viewModel.editStatus.value == false) {
                if (navController!!.currentDestination!!.label.toString() != "New Entry") {

                    navController!!.navigate(
                        DataFragmentDirections.actionNavigationHomeToNavigationEntry(
                            null
                        )
                    ) // new Entry
                    viewModel.setEditMode()
                } /*else {
                    navController!!.navigate(
                        EntryFragmentDirections.actionNavigationEntryToNavigationHome()
                    )
                    viewModel.setEditMode()
                }*/
            } else {
                // Save
                //navController!!.navigate(EntryFragmentDirections.actionNavigationEntryToNavigationHome())
                //viewModel.setEditMode()


                if (viewModel.newData.value?.mId == null) {
                    Log.d("SAVE", "NEW ENTRY")
                    saveNewEntry()
                    navController!!.navigate(EntryFragmentDirections.actionNavigationEntryToNavigationHome())
                } else {
                    Log.d("SAVE", "EDIT ENTRY")
                    saveEditEntry()
                    navController!!.navigate(EntryFragmentDirections.actionNavigationEntryToNavigationHome())
                }

            }

        }

        /*fab.setOnClickListener {
            Log.d(TAG, "Fab click")

            // 1 -> Enter new Entry
            // 2 -> Exit new Entry

            // 1 -> Enter edit Mode
            // 2 -> Exit edit Mode

            try {

                viewModel.setEditMode()
                Log.d("LAVO", viewModel.editStatus.value.toString())
                navController!!.navigate(
                    DataFragmentDirections.actionNavigationHomeToNavigationEntry(
                        null
                    )
                ) // new Entry
                //setFabButtonImage(it as FloatingActionButton, true)

            } catch (e: Exception) {

                showValues()
                closeKeyboard()

                Log.d("OVAL", viewModel.editStatus.value.toString())

                // Save new Entry

                if (viewModel.editStatus.value == true) {
                    saveEditEntry()
                    navController!!.navigate(EntryFragmentDirections.actionNavigationEntryToNavigationHome())
                } else {
                    saveNewEntry()
                    navController!!.navigate(EntryFragmentDirections.actionNavigationEntryToNavigationHome())
                }

                //setFabButtonImage(it as FloatingActionButton, false)
            }
        }*/

        viewModel.editStatus.observe(this, Observer {
            Log.d("VISIB", it.toString())
            setFabButtonImage(fab, it)
        })

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard
            )
        )
        setupActionBarWithNavController(navController!!, appBarConfiguration)
        navView.setupWithNavController(navController!!)

    }

    fun saveNewEntry() {
        viewModel.insert()
        viewModel.setEditMode()
        viewModel.updateEntries()
    }

    fun saveEditEntry() {
        viewModel.update()
        viewModel.setEditMode()
        viewModel.updateEntries()
    }

    fun showValues() {
        Log.d(TAG, viewModel.newData.value?.mGender.toString())
        Log.d(TAG, viewModel.newData.value?.mHeight.toString())
        Log.d(TAG, viewModel.newData.value?.mWeight.toString())
        Log.d(TAG, viewModel.newData.value?.mBloodSystol.toString())
        Log.d(TAG, viewModel.newData.value?.mBloodDiastol.toString())
        Log.d(TAG, viewModel.newData.value?.mPulse.toString())
        Log.d(TAG, viewModel.newData.value?.mBloodSugar.toString())
    }

    fun closeKeyboard() {
        val imm: InputMethodManager =
            getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0)
    }

    fun setFabButtonImage(fab: FloatingActionButton, isSaving: Boolean) {

        Log.d(TAG, "setFabButtonImage")
        Log.d(TAG, isSaving.toString())

        if (isSaving) {
            fab.setImageDrawable(
                ContextCompat.getDrawable(
                    applicationContext,
                    R.drawable.ic_save_button
                )
            )
        } else {
            Toast.makeText(
                applicationContext,
                resources.getText(R.string.status_save_button),
                Toast.LENGTH_LONG
            ).show()
            fab.setImageDrawable(
                ContextCompat.getDrawable(
                    applicationContext,
                    R.drawable.ic_google_cross
                )
            )
        }

    }

    override fun onBackPressed() {
        viewModel.setEditMode()
        super.onBackPressed()
    }

    override fun onSupportNavigateUp(): Boolean {
        viewModel.setEditMode()
        navController!!.navigateUp()
        return true
    }

}
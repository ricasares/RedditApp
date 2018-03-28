package apps.ricasares.com.myreddit

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import apps.ricasares.com.myreddit.fragments.ListingFragment

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        var listingFragment = supportFragmentManager.findFragmentByTag(ListingFragment.TAG)
        if (listingFragment == null) {
            listingFragment = ListingFragment.newInstance()
        }
        supportFragmentManager.beginTransaction()
                .add(R.id.content_main_layout, listingFragment, ListingFragment.TAG)
                .commit()
    }

}

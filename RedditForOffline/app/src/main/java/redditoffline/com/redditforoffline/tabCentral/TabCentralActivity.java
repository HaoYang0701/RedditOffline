package redditoffline.com.redditforoffline.tabCentral;

import android.accounts.AccountManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;
import redditoffline.com.redditforoffline.R;

public class TabCentralActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
  @BindView(R.id.toolbar) Toolbar toolbar;
  @BindView(R.id.fab) FloatingActionButton floatingActionButton;
  @BindView(R.id.drawer_layout) DrawerLayout drawerLayout;
  @BindView(R.id.nav_view) NavigationView navigationView;

  private TabCentralContract.Presenter presenter;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_tab_central);
    setSupportActionBar(toolbar);
    ButterKnife.bind(this);

    TabCentralFragment fragment = (TabCentralFragment) getSupportFragmentManager().findFragmentById(R.id.tabCentralRecyclerFragment);

    presenter = new TabCentralPresenter(fragment);

    floatingActionButton.setOnClickListener((View view) -> {
      presenter.onFabClicked();
    });

    ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
        this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
    drawerLayout.setDrawerListener(toggle);
    toggle.syncState();
    navigationView.setNavigationItemSelectedListener(this);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.tab_central, menu);
    return true;
  }


  @Override
  protected void onResume() {
    super.onResume();
    presenter.subscribe();
  }

  @Override
  protected void onPause() {
    super.onPause();
    presenter.unsubscribe();
  }

  @Override
  public void onBackPressed() {
    if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
      drawerLayout.closeDrawer(GravityCompat.START);
    } else {
      super.onBackPressed();
    }
  }

  @Override
  public boolean onNavigationItemSelected(@NonNull MenuItem item) {
    return presenter.onNavigationItemClicked(item);
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_settings) {
      return true;
    }

    return super.onOptionsItemSelected(item);
  }
}

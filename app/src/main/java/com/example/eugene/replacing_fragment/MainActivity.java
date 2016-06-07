package com.example.eugene.replacing_fragment;

//import android.content.res.Configuration;
//import android.support.design.widget.NavigationView;
//import android.support.design.widget.TabLayout;
//import android.support.v4.app.Fragment;
//import android.support.v4.app.FragmentManager;
//import android.support.v4.content.ContextCompat;
//import android.support.v4.view.GravityCompat;
//import android.support.v4.view.MenuItemCompat;
//import android.support.v4.view.ViewPager;
//import android.support.v4.widget.DrawerLayout;
//import android.support.v7.app.ActionBarDrawerToggle;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.support.v7.widget.SearchView;
//import android.view.Menu;
//import android.view.MenuItem;
//import android.support.v7.widget.Toolbar;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.text.InputType;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.widget.Toolbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Fragment fragment = null;
    //Navigation bar
    private DrawerLayout mDrawer;
    private Toolbar toolbar;
    private NavigationView nvDrawer;
    private ActionBarDrawerToggle drawerToggle;
    private static Context mContext;
    ArrayList<CityModel> City = new ArrayList<CityModel>();

    CharSequence[] Cities;

    String BACKGROUND_COLOR_ID = "backgroundColorId";
    //Tab Layout
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_toolbar);
        mContext = getApplicationContext();

        Window window = getWindow();
        //window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        //window.setStatusBarColor(Color.BLUE);

        // Set a Toolbar to replace the ActionBar.
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        //tabLayout = (TabLayout) findViewById(R.id.tabs);
        // viewPager = (ViewPager) findViewById(R.id.viewpager);



        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        TextView textView = (TextView) toolbar.findViewById(R.id.toolbar_title);
        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/font3.otf");
        textView.setTypeface(font);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new Fragment1()).commit();
        }


        //viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        //viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));


        // Find our drawer view
        //mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//
        // nvDrawer = (NavigationView) findViewById(R.id.nvView);
        // Setup drawer view
        //setupDrawerContent(nvDrawer);

//        drawerToggle.setDrawerIndicatorEnabled(true);

        //drawerToggle = setupDrawerToggle();
        //drawerToggle.setDrawerIndicatorEnabled(false);
        //drawerToggle.setHomeAsUpIndicator(R.drawable.ic_menu);


        // Tie DrawerLayout events to the ActionBarToggle
        //mDrawer.addDrawerListener(drawerToggle);

//        final TabLayout.Tab day = tabLayout.newTab();
//        final TabLayout.Tab hour = tabLayout.newTab();
//        final TabLayout.Tab city = tabLayout.newTab();
//
//        tabLayout.addTab(day, 0);
//        tabLayout.addTab(hour, 1);
//        tabLayout.addTab(city, 2);

        CityModel cityModel = new CityModel("Tỉnh An Giang", 1594446);
        CityModel cityModel1 = new CityModel("Tỉnh Bà Rịa-Vũng Tàu", 1584534);
        CityModel cityModel2 = new CityModel("Tỉnh Bắc Giang", 1905419);
        CityModel cityModel3 = new CityModel("Tỉnh Bắc Kạn", 1905669);
        CityModel cityModel4 = new CityModel("Tỉnh Bạc Liêu", 1905675);
        CityModel cityModel5 = new CityModel("Tỉnh Bắc Ninh", 1905412);
        CityModel cityModel6 = new CityModel("Tỉnh Bến Tre", 1587974);
        CityModel cityModel7 = new CityModel("Tỉnh Bình Ðịnh", 1587871);
        CityModel cityModel8 = new CityModel("Tỉnh Bình Dương", 1905475);
        CityModel cityModel9 = new CityModel("Tỉnh Bình Phước", 1905480);
        CityModel cityModel10 = new CityModel("Tỉnh Bình Thuận", 1581882);
        CityModel cityModel11 = new CityModel("Tỉnh Cà Mau", 1905678);
        CityModel cityModel12 = new CityModel("Tỉnh Cao Bằng", 1586182);
        CityModel cityModel13 = new CityModel("Tỉnh Ðắc Lắk", 1584169);
        CityModel cityModel16 = new CityModel("Tỉnh Ðồng Nai", 1582720);
        CityModel cityModel17 = new CityModel("Tỉnh Ðồng Tháp", 1582562);
        CityModel cityModel18 = new CityModel("Tỉnh Gia Lai", 1581088);
        CityModel cityModel19 = new CityModel("Tỉnh Hà Giang", 1581030);
        CityModel cityModel20 = new CityModel("Tỉnh Hà Nam", 1905637);
        CityModel cityModel21 = new CityModel("Tỉnh Hà Tĩnh", 1580700);
        CityModel cityModel22 = new CityModel("Tỉnh Hải Dương", 1905686);
        CityModel cityModel24 = new CityModel("Tỉnh Hòa Bình", 1572594);
        CityModel cityModel25 = new CityModel("Tỉnh Hưng Yên", 1905699);
        CityModel cityModel26 = new CityModel("Tỉnh Khánh Hòa", 1579634);
        CityModel cityModel27 = new CityModel("Tỉnh Kiến Giang", 1579008);
        CityModel cityModel28 = new CityModel("Tỉnh Kon Tum", 1565088);
        CityModel cityModel30 = new CityModel("Tỉnh Lâm Ðồng", 1577882);
        CityModel cityModel31 = new CityModel("Tỉnh Lạng Sơn", 1576632);
        CityModel cityModel32 = new CityModel("Tỉnh Lào Cai", 1562412);
        CityModel cityModel33 = new CityModel("Tỉnh Long An", 1575788);
        CityModel cityModel34 = new CityModel("Tỉnh Nam Ðịnh", 1905626);
        CityModel cityModel35 = new CityModel("Tỉnh Nghệ An", 1559969);
        CityModel cityModel36 = new CityModel("Tỉnh Ninh Bình", 1559970);
        CityModel cityModel37 = new CityModel("Tỉnh Ninh Thuận", 1559971);
        CityModel cityModel38 = new CityModel("Tỉnh Phú Thọ", 1905577);
        CityModel cityModel39 = new CityModel("Tỉnh Quảng Bình", 1568839);
        CityModel cityModel40 = new CityModel("Tỉnh Quảng Nam", 1905516);
        CityModel cityModel41 = new CityModel("Tỉnh Quảng Ngãi", 1568769);
        CityModel cityModel42 = new CityModel("Tỉnh Quảng Ninh", 1568758);
        CityModel cityModel43 = new CityModel("Tỉnh Quảng Trị", 1568733);
        CityModel cityModel44 = new CityModel("Tỉnh Sóc Trăng", 1559972);
        CityModel cityModel45 = new CityModel("Tỉnh Sơn La", 1567643);
        CityModel cityModel46 = new CityModel("Tỉnh Tây Ninh", 1566557);
        CityModel cityModel47 = new CityModel("Tỉnh Thái Bình", 1566338);
        CityModel cityModel48 = new CityModel("Tỉnh Thái Nguyên", 1905497);
        CityModel cityModel49 = new CityModel("Tỉnh Thanh Hóa", 1566165);
        CityModel cityModel50 = new CityModel("Tỉnh Thừa Thiên-Huế", 1565033);
        CityModel cityModel51 = new CityModel("Tỉnh Tiền Giang", 1564676);
        CityModel cityModel52 = new CityModel("Tỉnh Trà Vinh", 1559975);
        CityModel cityModel53 = new CityModel("Tỉnh Tuyên Quang", 1559976);
        CityModel cityModel54 = new CityModel("Tỉnh Vĩnh Long", 1559977);
        CityModel cityModel55 = new CityModel("Tỉnh Vĩnh Phúc", 1905856);
        CityModel cityModel56 = new CityModel("Tỉnh Yên Bái", 1559978);
        CityModel cityModel57 = new CityModel("Tỉnh Phú Yên", 1569805);
        CityModel cityModel58 = new CityModel("Tỉnh Cần Thơ", 1581188);
        CityModel cityModel59 = new CityModel("Tỉnh Ðà Nẵng", 1905468);
        CityModel cityModel60 = new CityModel("Thành Phố Hải Phòng", 1581297);
        CityModel cityModel61 = new CityModel("Thủ Ðô Hà Nội", 1581129);
        CityModel cityModel62 = new CityModel("Thành phố Hồ Chí Minh", 1580578);

        //showInputDialog();
        City.add(cityModel);
        City.add(cityModel1);
        City.add(cityModel2);
        City.add(cityModel3);
        City.add(cityModel4);
        City.add(cityModel5);
        City.add(cityModel6);
        City.add(cityModel7);
        City.add(cityModel8);
        City.add(cityModel9);
        City.add(cityModel10);
        City.add(cityModel11);
        City.add(cityModel12);
        City.add(cityModel13);
//        City.add(cityModel14);
//        City.add(cityModel15);
        City.add(cityModel16);
        City.add(cityModel17);
        City.add(cityModel18);
        City.add(cityModel19);
        City.add(cityModel20);
        City.add(cityModel21);
        City.add(cityModel22);
//        City.add(cityModel23);
        City.add(cityModel24);
        City.add(cityModel25);
        City.add(cityModel26);
        City.add(cityModel27);
        City.add(cityModel28);
//        City.add(cityModel29);
        City.add(cityModel30);
        City.add(cityModel31);
        City.add(cityModel32);
        City.add(cityModel33);
        City.add(cityModel34);
        City.add(cityModel35);
        City.add(cityModel36);
        City.add(cityModel37);
        City.add(cityModel38);
        City.add(cityModel39);
        City.add(cityModel40);
        City.add(cityModel41);
        City.add(cityModel42);
        City.add(cityModel43);
        City.add(cityModel44);
        City.add(cityModel45);
        City.add(cityModel46);
        City.add(cityModel47);
        City.add(cityModel48);
        City.add(cityModel49);
        City.add(cityModel50);
        City.add(cityModel51);
        City.add(cityModel52);
        City.add(cityModel53);
        City.add(cityModel54);
        City.add(cityModel55);
        City.add(cityModel56);
        City.add(cityModel57);
        City.add(cityModel58);
        City.add(cityModel59);
        City.add(cityModel60);
        City.add(cityModel61);
        City.add(cityModel62);


        List<String> list = new ArrayList<String>();
        String s = City.get(0).CityName;
        for (int i = 0; i < City.size(); i++) {
            list.add(City.get(i).CityName);
        }
        Cities = list.toArray(new String[list.size()]);
        // tabLayout.setSelectedTabIndicatorColor(ContextCompat.getColor(this, R.color.indicator));

        //viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
//        viewPager.setCurrentItem(1);

    }

    public static Context getContext() {
        return mContext;
    }

    private ActionBarDrawerToggle setupDrawerToggle() {
        return new ActionBarDrawerToggle(this, mDrawer, toolbar, R.string.drawer_open, R.string.drawer_close);
    }

//    private void setupDrawerContent(NavigationView navigationView) {
//        navigationView.setNavigationItemSelectedListener(
//                new NavigationView.OnNavigationItemSelectedListener() {
//                    @Override
//                    public boolean onNavigationItemSelected(MenuItem menuItem) {
//                        selectDrawerItem(menuItem);
//                        return true;
//                    }
//                });
//    }

//    public void selectDrawerItem(MenuItem menuItem) {
//        // Create a new fragment and specify the fragment to show based on nav item clicked
//
//        Class fragmentClass;
//        switch (menuItem.getItemId()) {
//            case R.id.nav_first_fragment:
//                fragmentClass = Fragment1.class;
//                break;
//            case R.id.nav_second_fragment:
//                fragmentClass = Fragment2.class;
//                break;
//            default:
//                fragmentClass = MainActivity.class;
//        }
//
//        try {
//            fragment = (Fragment) fragmentClass.newInstance();
//            fragment = Fragment3.class.newInstance();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        // Insert the fragment by replacing any existing fragment
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();
//
//        // Highlight the selected item has been done by NavigationView
//        menuItem.setChecked(true);
//        // Set action bar title
//        setTitle(menuItem.getTitle());
//        // Close the navigation drawer
//        mDrawer.closeDrawers();
//    }


    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu, menu);
//        MenuItem searchItem = menu.findItem(R.id.action_search);
//        searchItem.setVisible(false);
//        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
//        searchView.setMaxWidth(Integer.MAX_VALUE);
//        searchView.setOnQueryTextListener(this);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.change_city:
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
                dialogBuilder.setTitle("Choose location");
                dialogBuilder.setItems(Cities, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        String selectedText = Cities[item].toString();  //Selected item in listview
                        for (int i = 0; i < City.size(); i++) {
                            int id = City.get(i).getCityId();
                            if (selectedText == City.get(i).getCityName().toString()) {
                                changeCity(id);
                            }
                        }
                    }
                });
                //Create alert dialog object via builder
                AlertDialog alertDialogObject = dialogBuilder.create();
                //Show the dialog
                alertDialogObject.show();
                break;
            case R.id.hourly:
                Intent intent = new Intent(this, HourlyActivity.class);

                startActivity(intent);
                break;
            default:
                break;
        }
        return false;
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
//        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggles
        drawerToggle.onConfigurationChanged(newConfig);
    }

    private void showInputDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Change city");
        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);
        builder.setPositiveButton("Go", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                changeCity(Integer.parseInt(input.getText().toString()));
            }
        });
        builder.show();
    }

    public void changeCity(int city) {

        Fragment1 wf = (Fragment1) getSupportFragmentManager()
                .findFragmentById(R.id.container);
        wf.changeCity(city);
        new CityPreference(this).setCity(city);

    }


    public void setActionBarTitle(String title) {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        TextView toolbar_title = (TextView) toolbar.findViewById(R.id.toolbar_title);
        toolbar_title.setText(title);
    }

}
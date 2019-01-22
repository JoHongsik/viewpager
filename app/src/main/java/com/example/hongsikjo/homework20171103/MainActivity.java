package com.example.hongsikjo.homework20171103;


import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private android.support.v7.widget.Toolbar toolbar;
    private TabLayout tabLayout;
    public static ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.real_toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent = new Intent(getApplicationContext(), PlanNewView.class);
                //startActivityForResult(intent, 100); //100 -> RequestCode 라고 함
                viewPager.setCurrentItem(1);
                addTaskDialog();

            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        viewPager =  (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_home_black_24dp);
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_add_black_24dp);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_create_black_24dp);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera)
            viewPager.setCurrentItem(0);


        else if(id == R.id.nav_home)
            viewPager.setCurrentItem(1);

        else if(id==R.id.nav_memo)
            viewPager.setCurrentItem(2);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    protected void onActivityResult(int reqCode, int resultCode, Intent data) {

        super.onActivityResult(reqCode, resultCode, data);
        for (Fragment fragment : getSupportFragmentManager().getFragments()) {
            fragment.onActivityResult(reqCode, resultCode, data);
        }
    }


    protected void onClickAdd(View view) {
        // 입력할 액티비티를 띄워준다.
        Intent intent = new Intent(this, PlanNewView.class);
        startActivityForResult(intent, 100); //100 -> RequestCode 라고 함
    }

    private void setupViewPager(ViewPager viewPager){
        this.viewPager = viewPager;
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new TwoFragment(),"추가");
        adapter.addFragment(new OneFragment(),"홈");
        adapter.addFragment(new ThreeFragment(),"메모");
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(1);

    }

    class ViewPagerAdapter extends FragmentPagerAdapter{
        private final List<Fragment> fragList = new ArrayList<>();
        private final List<String> titleList = new ArrayList<>();


        public ViewPagerAdapter(FragmentManager manager){
            super(manager);
        }
        // fragment 객체 한개 리턴
        public Fragment getItem(int position) {
            return fragList.get(position);
        }


        @Override
        public int getCount() {
            return fragList.size();
        }

        public void addFragment(Fragment fragment,String title){
            fragList.add(fragment);
            titleList.add(title);
        }

        // 페이지 제목을 반환
        public CharSequence getPageTitle(int position){
            return titleList.get(position);
        }
    }

    private void addTaskDialog(){   //상품을 등록하는 다이얼로그를 띄우고 상품 등록 처리 하는 기능을 수행하는 메소드
        LayoutInflater inflater = LayoutInflater.from(this);
        View subView = inflater.inflate(R.layout.add_plan_layout, null);
        final EditText nameField = (EditText)subView.findViewById(R.id.enter_name);
        final EditText quantityField = (EditText)subView.findViewById(R.id.enter_quantity);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add new plan for today");
        builder.setView(subView);
        builder.create();
        builder.setPositiveButton("ADD plan for today", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                final String name = nameField.getText().toString();
                final String quantity = quantityField.getText().toString();
                if(TextUtils.isEmpty(name) || TextUtils.isEmpty(quantity)){
                    Toast.makeText(MainActivity.this, "Something went wrong. Check your input values", Toast.LENGTH_LONG).show();
                }
                else{
                    long now = System.currentTimeMillis();
                    Date date = new Date(now);
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일");
                    String getTime = sdf.format(date);
                    //OneFragment.PList.add(new PlanList("2017년 11월 03일", "안드로이드 과제 하기"));
                    OneFragment.setPlanDate(getTime,name,quantity);
                    OneFragment.adapter.notifyDataSetChanged();
                    //finish();
                    //startActivity(getIntent());
                }
            }
        });
        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "Task cancelled", Toast.LENGTH_LONG).show();
            }
        });
        builder.show();
    }

}

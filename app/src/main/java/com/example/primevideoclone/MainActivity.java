package com.example.primevideoclone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.primevideoclone.adapter.BannerMoviesPagerAdapter;
import com.example.primevideoclone.adapter.MainRecycleAdapter;
import com.example.primevideoclone.model.AllCategory;
import com.example.primevideoclone.model.BannerMovies;
import com.example.primevideoclone.model.CategoryItem;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    BannerMoviesPagerAdapter bannerMoviesPagerAdapter;
    TabLayout indicatorTab, categoryTab;
    ViewPager bannerMoviesViewPager;
    List<BannerMovies> homeBannerList;
    List<BannerMovies> tvShowBannerList;
    List<BannerMovies> movieBannerList;
    List<BannerMovies> kidsBannerList;
    Timer sliderTimer;

    MainRecycleAdapter mainRecycleAdapter;
    RecyclerView mainRecycler;
    List<AllCategory> allCategoryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        indicatorTab = findViewById(R.id.tab_indicator);
        categoryTab = findViewById(R.id.tabLayout);

        homeBannerList = new ArrayList<>();
        homeBannerList.add(new BannerMovies(1, "Hai Phuong", "https://i.postimg.cc/76Hzkv97/banner1.jpg", ""));
        homeBannerList.add(new BannerMovies(2, "Bo Gia", "https://i.postimg.cc/prgyy3fs/banner2.jpg", ""));
        homeBannerList.add(new BannerMovies(3, "Fast & Furious", "https://i.postimg.cc/hhRBStKt/banner3.jpg", ""));
        homeBannerList.add(new BannerMovies(4, "Doctor Strange", "https://i.postimg.cc/ydwdZB7w/banner4.jpg", ""));

        tvShowBannerList = new ArrayList<>();
        tvShowBannerList.add(new BannerMovies(1, "RAPVIET", "https://i.postimg.cc/LXVCq4ks/banner5.jpg", ""));
        tvShowBannerList.add(new BannerMovies(2, "On Gioi Cau Day Roi", "https://i.postimg.cc/7Z7NGYwy/banner6.jpg", ""));

        movieBannerList = new ArrayList<>();
        movieBannerList.add(new BannerMovies(1, "A Beautiful Day", "https://i.postimg.cc/tT5yNxGQ/banner7.jpg", ""));
        movieBannerList.add(new BannerMovies(2, "Crash Landing On You", "https://i.postimg.cc/mgMvSH7X/banner8.jpg", ""));

        kidsBannerList = new ArrayList<>();
        kidsBannerList.add(new BannerMovies(1, "BoBoiBoy", "https://i.postimg.cc/BnB00Ft8/banner9.jpg", ""));
        kidsBannerList.add(new BannerMovies(1, "Phineas and Ferb", "https://i.postimg.cc/CKvpZVGS/banner10.jpg", ""));

        //Default tab
       setBannerMoviesPagerAdapter(homeBannerList);

        //Selected category
        categoryTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 1:
                        setBannerMoviesPagerAdapter(tvShowBannerList);
                        return;
                    case 2:
                        setBannerMoviesPagerAdapter(movieBannerList);
                        return;
                    case 3:
                        setBannerMoviesPagerAdapter(kidsBannerList);
                        return;
                    default:
                        setBannerMoviesPagerAdapter(homeBannerList);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        List<CategoryItem> homeCatListItem1 = new ArrayList<>();
        homeCatListItem1.add(new CategoryItem(1, "Love & Other Drugs", "https://i.postimg.cc/qBXfSkd7/banner11.jpg", ""));
        homeCatListItem1.add(new CategoryItem(2, "Bewakoofiyaan", "https://i.postimg.cc/Fzh6NLQH/banner12.jpg", ""));
        homeCatListItem1.add(new CategoryItem(3, "Supernatural", "https://i.postimg.cc/43zwyxP7/banner13.jpg", ""));
        homeCatListItem1.add(new CategoryItem(4, "Sweet Home", "https://i.postimg.cc/3wHCsHhK/banner14.jpg", ""));

        List<CategoryItem> homeCatListItem2 = new ArrayList<>();
        homeCatListItem2.add(new CategoryItem(1, "Season 1", "https://i.postimg.cc/vmV86px6/banner15.jpg", ""));
        homeCatListItem2.add(new CategoryItem(2, "Season 2", "https://i.postimg.cc/50JJQjMZ/banner16.jpg", ""));
        homeCatListItem2.add(new CategoryItem(3, "Season 3", "https://i.postimg.cc/y87rR9Jh/banner17.jpg", ""));
        homeCatListItem2.add(new CategoryItem(4, "Season 4", "https://i.postimg.cc/D0dPfjP9/banner18.jpg", ""));

        allCategoryList = new ArrayList<>();
        allCategoryList.add(new AllCategory(1, "Watch next TV and movies", homeCatListItem1));
        allCategoryList.add(new AllCategory(2, "Friends series",homeCatListItem2));

        setMainRecycler(allCategoryList);

    }

    private void setBannerMoviesPagerAdapter(List<BannerMovies> bannerMoviesList) {
        bannerMoviesViewPager = findViewById(R.id.banner_viewPager);
        bannerMoviesPagerAdapter = new BannerMoviesPagerAdapter(this, bannerMoviesList);
        bannerMoviesViewPager.setAdapter(bannerMoviesPagerAdapter);
        indicatorTab.setupWithViewPager(bannerMoviesViewPager);

        sliderTimer = new Timer();
        sliderTimer.scheduleAtFixedRate(new AutoSlider(), 4000, 6000);
        indicatorTab.setupWithViewPager(bannerMoviesViewPager, true);
    }

    class AutoSlider extends TimerTask {
        @Override
        public void run() {

            MainActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(bannerMoviesViewPager.getCurrentItem() < homeBannerList.size() - 1){
                        bannerMoviesViewPager.setCurrentItem(bannerMoviesViewPager.getCurrentItem() + 1);
                    }
                    else{ bannerMoviesViewPager.setCurrentItem(0);}
                }
            });

        }
    }

    public void setMainRecycler(List<AllCategory> allCategoryList) {

        mainRecycler = findViewById(R.id.main_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        mainRecycler.setLayoutManager(layoutManager);
        mainRecycleAdapter = new MainRecycleAdapter(this, allCategoryList);
        mainRecycler.setAdapter(mainRecycleAdapter);

    }

}
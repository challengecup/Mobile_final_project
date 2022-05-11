package com.example.primevideoclone;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.primevideoclone.adapter.BannerMoviesPagerAdapter;
import com.example.primevideoclone.adapter.MainRecycleAdapter;
import com.example.primevideoclone.model.AllCategory;
import com.example.primevideoclone.model.BannerMovies;
import com.example.primevideoclone.model.CategoryItem;
import com.google.android.material.appbar.AppBarLayout;
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

    NestedScrollView nestedScrollView;
    AppBarLayout appBarLayout;

    MainRecycleAdapter mainRecycleAdapter;
    RecyclerView mainRecycler;
    List<AllCategory> allCategoryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        indicatorTab = findViewById(R.id.tab_indicator);
        categoryTab = findViewById(R.id.tabLayout);
        nestedScrollView = findViewById(R.id.nested_scroll);
        appBarLayout = findViewById(R.id.appbar);

        homeBannerList = new ArrayList<>();
        homeBannerList.add(new BannerMovies(1, "Hai Phuong", "https://i.postimg.cc/76Hzkv97/banner1.jpg", "https://videosstreaming18520726.000webhostapp.com/videos/url1.mp4"));
        homeBannerList.add(new BannerMovies(2, "Bo Gia", "https://i.postimg.cc/prgyy3fs/banner2.jpg", "https://videosstreaming18520726.000webhostapp.com/videos/url2.mp4"));
        homeBannerList.add(new BannerMovies(3, "Fast & Furious", "https://i.postimg.cc/hhRBStKt/banner3.jpg", "https://videosstreaming18520726.000webhostapp.com/videos/ur3.mp4"));
        homeBannerList.add(new BannerMovies(4, "Doctor Strange", "https://i.postimg.cc/ydwdZB7w/banner4.jpg", "https://videosstreaming18520726.000webhostapp.com/videos/ur4.mp4"));

        tvShowBannerList = new ArrayList<>();
        tvShowBannerList.add(new BannerMovies(1, "RAPVIET", "https://i.postimg.cc/LXVCq4ks/banner5.jpg", "https://rr7---sn-8pxuuxa-nbozr.googlevideo.com/videoplayback?expire=1652283326&ei=XoN7YqmCB9CW2_gPivKM6AE&ip=216.131.106.187&id=o-AAS4HK4ws0FF31t5d0kkZrXVGMk8ysz4VsP5DGhFIZQi&itag=18&source=youtube&requiressl=yes&spc=4ocVC-LIM1ROS6dEWOTmnacQE51J&vprv=1&mime=video%2Fmp4&ns=w06eN9-bueaJAoHN9vIbAEkG&gir=yes&clen=243234764&ratebypass=yes&dur=2659.799&lmt=1647419816252613&fexp=24001373,24007246&c=WEB&txp=5438434&n=93h7Z9EhN5GS4w&sparams=expire%2Cei%2Cip%2Cid%2Citag%2Csource%2Crequiressl%2Cspc%2Cvprv%2Cmime%2Cns%2Cgir%2Cclen%2Cratebypass%2Cdur%2Clmt&sig=AOq0QJ8wRAIgT-Yj9T2iqa3sXoHoPEvMjEhVGWo8yRn7ra2UP9yuj00CIEFeUeRzvUlpdwPC_WdCHjXaeaEshWKNf5zUTkad-Uy9&redirect_counter=1&rm=sn-vgqezl7s&req_id=6346d79205b0a3ee&cms_redirect=yes&cmsv=e&ipbypass=yes&mh=ID&mip=2402:800:6375:5e8:3d26:264c:95dd:a3a7&mm=31&mn=sn-8pxuuxa-nbozr&ms=au&mt=1652260889&mv=u&mvi=7&pl=48&lsparams=ipbypass,mh,mip,mm,mn,ms,mv,mvi,pl&lsig=AG3C_xAwRgIhAMTU817HG_1yLx5SapXRBO-vyzwHsKc7iqsrjPLPL8ZqAiEA0Ku34bzEsU4THG5HJIOdAQCjUVFry1od4RockG20m9Y%3D"));
        tvShowBannerList.add(new BannerMovies(2, "On Gioi Cau Day Roi", "https://i.postimg.cc/7Z7NGYwy/banner6.jpg", "https://videosstreaming18520726.000webhostapp.com/videos/url6.mp4"));

        movieBannerList = new ArrayList<>();
        movieBannerList.add(new BannerMovies(1, "A Beautiful Day", "https://i.postimg.cc/tT5yNxGQ/banner7.jpg", "https://videosstreaming18520726.000webhostapp.com/videos/url7.mp4"));
        movieBannerList.add(new BannerMovies(2, "Crash Landing On You", "https://i.postimg.cc/mgMvSH7X/banner8.jpg", "https://videosstreaming18520726.000webhostapp.com/videos/url8.mp4"));

        kidsBannerList = new ArrayList<>();
        kidsBannerList.add(new BannerMovies(1, "BoBoiBoy", "https://i.postimg.cc/BnB00Ft8/banner9.jpg", "https://videosstreaming18520726.000webhostapp.com/videos/url9.mp4"));
        kidsBannerList.add(new BannerMovies(1, "Phineas and Ferb", "https://i.postimg.cc/CKvpZVGS/banner10.jpg", "https://videosstreaming18520726.000webhostapp.com/videos/url10.mp4"));

        //Default tab
       setBannerMoviesPagerAdapter(homeBannerList);

        //Selected category
        categoryTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 1:
                        setScrollDefaultState();
                        setBannerMoviesPagerAdapter(tvShowBannerList);
                        return;
                    case 2:
                        setScrollDefaultState();
                        setBannerMoviesPagerAdapter(movieBannerList);
                        return;
                    case 3:
                        setScrollDefaultState();
                        setBannerMoviesPagerAdapter(kidsBannerList);
                        return;
                    default:
                        setScrollDefaultState();
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

        List<CategoryItem> homeCatListItem3 = new ArrayList<>();
        homeCatListItem3.add(new CategoryItem(1, "Dogs In Space", "https://i.postimg.cc/tRMrrTrP/banner19.png", ""));
        homeCatListItem3.add(new CategoryItem(2, "A Tale Dark & Grimm", "https://i.postimg.cc/8cJxyrMK/banner20.jpg", ""));
        homeCatListItem3.add(new CategoryItem(3, "Beat Bugs", "https://i.postimg.cc/65dBwD4H/banner21.png", ""));
        homeCatListItem3.add(new CategoryItem(4, "Boss Baby", "https://i.postimg.cc/h4s0wGpM/banner22.jpg", ""));

        List<CategoryItem> homeCatListItem4 = new ArrayList<>();
        homeCatListItem4.add(new CategoryItem(1, "Deadliest Catch", "https://i.postimg.cc/pdDLBH78/banner23.jpg", ""));
        homeCatListItem4.add(new CategoryItem(2, "Cal Fire", "https://i.postimg.cc/yN7wwCx0/banner24.jpg", ""));
        homeCatListItem4.add(new CategoryItem(3, "Man vs. Wild", "https://i.postimg.cc/vT5JJYrL/banner25.jpg", ""));
        homeCatListItem4.add(new CategoryItem(4, "How The Universe Works", "https://i.postimg.cc/RVXsNJZJ/banner26.jpg", ""));

        allCategoryList = new ArrayList<>();
        allCategoryList.add(new AllCategory(1, "Watch next TV and movies", homeCatListItem1));
        allCategoryList.add(new AllCategory(2, "Friends series",homeCatListItem2));
        allCategoryList.add(new AllCategory(3, "Kids movies",homeCatListItem3));
        allCategoryList.add(new AllCategory(4, "Discovery channel",homeCatListItem4));

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

    private void setScrollDefaultState(){

        nestedScrollView.fullScroll(View.FOCUS_UP);
        nestedScrollView.scrollTo(0,0);
        appBarLayout.setExpanded(true);

    }

}
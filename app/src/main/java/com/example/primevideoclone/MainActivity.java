package com.example.primevideoclone;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

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
import com.example.primevideoclone.retrofit.RetrofitClient;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

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
        tvShowBannerList = new ArrayList<>();
        movieBannerList = new ArrayList<>();
        kidsBannerList = new ArrayList<>();

        getBannerData();



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

    private void getBannerData(){

        CompositeDisposable compositeDisposable = new CompositeDisposable();

        compositeDisposable.add(RetrofitClient.getApi().getAllBanners()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<List<BannerMovies>>() {
                    @Override
                    public void onNext(List<BannerMovies> bannerMovies) {

                        for (int i = 0; i < bannerMovies.size(); i++){
                            if(bannerMovies.get(i).getBannerCategoryId().toString().equals("1")){
                                homeBannerList.add(bannerMovies.get(i));
                            }else if(bannerMovies.get(i).getBannerCategoryId().toString().equals("2")){
                                tvShowBannerList.add(bannerMovies.get(i));
                            }else if(bannerMovies.get(1).getBannerCategoryId().toString().equals("3")){
                                movieBannerList.add(bannerMovies.get(i));
                            }else if(bannerMovies.get(i).getBannerCategoryId().toString().equals("4")){
                                kidsBannerList.add(bannerMovies.get(i));
                            }else{

                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        //Default tab
                        setBannerMoviesPagerAdapter(homeBannerList);
                    }
                })
        );

    }

}
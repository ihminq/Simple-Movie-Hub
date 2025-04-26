package com.ihminq.movie_hub.presentation.home.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.ihminq.movie_hub.presentation.movies.ui.FirstTabFragment;
import com.ihminq.movie_hub.presentation.setting.ThirdTabFragment;
import com.ihminq.movie_hub.presentation.userprofile.FourthTabFragment;
import com.ihminq.movie_hub.presentation.watchlist.SecondTabFragment;

import java.util.Arrays;
import java.util.List;

public class HomeViewPagerAdapter extends FragmentStateAdapter {
    private final List<Fragment> fragmentList = Arrays.asList(
            new FirstTabFragment(),
            new SecondTabFragment(),
            new ThirdTabFragment(),
            new FourthTabFragment()
    );

    public HomeViewPagerAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getItemCount() {
        return fragmentList.size();
    }
}

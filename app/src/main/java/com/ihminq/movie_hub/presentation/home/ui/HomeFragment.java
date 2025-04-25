package com.ihminq.movie_hub.presentation.home.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.ihminq.movie_hub.R;
import com.ihminq.movie_hub.constant.ResConstants;
import com.ihminq.movie_hub.databinding.FragmentHomeBinding;
import com.ihminq.movie_hub.presentation.home.adapter.HomeViewPagerAdapter;
import com.ihminq.movie_hub.presentation.home.viewmodel.HomeViewModel;

public class HomeFragment extends Fragment {
    private static final String TAG = "HomeFragment";
    private FragmentHomeBinding mBinding;
    private HomeViewModel mHomeViewModel;
    private NavController mNavController;
    private ViewPager2 mViewPager;
    private TabLayout mTabLayout;

    public HomeFragment() {}


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //get NavController
        mNavController = NavHostFragment.findNavController(this);

        //get ViewModel
        mHomeViewModel = new ViewModelProvider(requireActivity()).get(HomeViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = FragmentHomeBinding.inflate(inflater, container, false);

        initViews();

        mHomeViewModel.getCurrentUserLive().observe(getViewLifecycleOwner(), user -> {
            if (user == null) {
                Toast.makeText(requireContext(), "Cannot get user info from Firebase Auth", Toast.LENGTH_LONG).show();
                //navigate back to Login if failed to get User from database
                navigateBackToLogin();
            }
            else {
                setupViewPagerTabLayout();
            }
        });
        return mBinding.getRoot();
    }

    private void setupViewPagerTabLayout() {
        HomeViewPagerAdapter mViewPagerAdapter = new HomeViewPagerAdapter(this);
        mViewPager.setAdapter(mViewPagerAdapter);

        new TabLayoutMediator(mTabLayout, mViewPager,
                (tab, position) -> {
                    switch (position) {
                        case 0:
                            tab.setIcon(ResConstants.IC_TAB_1_SELECTED);
                            break;
                        case 1:
                            tab.setIcon(ResConstants.IC_TAB_2);
                            break;
                        case 2:
                            tab.setIcon(ResConstants.IC_TAB_3);
                            break;
                        case 3:
                            tab.setIcon(ResConstants.IC_TAB_4);
                            break;
                    }
                }
        ).attach();

        initTabSelectionHandler();
    }

    private void initTabSelectionHandler() {
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        tab.setIcon(ResConstants.IC_TAB_1_SELECTED);
                        break;
                    case 1:
                        tab.setIcon(ResConstants.IC_TAB_2_SELECTED);
                        break;
                    case 2:
                        tab.setIcon(ResConstants.IC_TAB_3_SELECTED);
                        break;
                    case 3:
                        tab.setIcon(ResConstants.IC_TAB_4_SELECTED);
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        tab.setIcon(ResConstants.IC_TAB_1);
                        break;
                    case 1:
                        tab.setIcon(ResConstants.IC_TAB_2);
                        break;
                    case 2:
                        tab.setIcon(ResConstants.IC_TAB_3);
                        break;
                    case 3:
                        tab.setIcon(ResConstants.IC_TAB_4);
                        break;
                }
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });
    }

    private void navigateBackToLogin() {
        mNavController.navigate(R.id.action_homeFragment_to_loginFragment);
    }

    private void initViews() {
        mViewPager = mBinding.viewPager;
        mTabLayout = mBinding.tabLayout;
    }
}
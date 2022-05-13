package com.example.th2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.th2.adapter.HomePagerAdapter;
import com.example.th2.databinding.ActivityMainBinding;
import com.example.th2.fragment.DanhsachFragment;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity {
    HomePagerAdapter homePagerAdapter;
    ActivityMainBinding binding;
    ActivityResultLauncher<Intent> resultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    refreshFragments();
                }
            }
    );

    private void refreshFragments() {
        ((DanhsachFragment) homePagerAdapter.createFragment(0)).resetData();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setupView();
    }

    private void setupView() {
        homePagerAdapter = new HomePagerAdapter(this);
        binding.viewPager.setAdapter(homePagerAdapter);

        binding.viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                binding.navMenu.getMenu().getItem(position).setChecked(true);

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });

        new TabLayoutMediator(binding.tabLayout, binding.viewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position){
                    case 0:
                        tab.setText("Danh sách");
                        break;
                    case 1:
                        tab.setText("Thông tin");
                        break;
                    case 2:
                        tab.setText("Thống kê");
                        break;
                }
            }
        }).attach();

        binding.navMenu.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.danhsach:
                    binding.viewPager.setCurrentItem(0);
                    break;
                case R.id.thongtin:
                    binding.viewPager.setCurrentItem(1);
                    break;
                case R.id.timkiem:
                    binding.viewPager.setCurrentItem(2);
                    break;
            }
            return true;
        });

        binding.fab.setOnClickListener(v -> {
            Intent intent = new Intent(this, AddBookActivity.class);
            resultLauncher.launch(intent);
        });

    }
}
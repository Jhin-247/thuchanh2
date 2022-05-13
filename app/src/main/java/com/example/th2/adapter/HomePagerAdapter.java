package com.example.th2.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.th2.fragment.DanhsachFragment;
import com.example.th2.fragment.ThongKeFragment;
import com.example.th2.fragment.ThongTinFragment;

public class HomePagerAdapter extends FragmentStateAdapter {
    DanhsachFragment danhsachFragment;
    ThongTinFragment thongTinFragment;
    ThongKeFragment thongKeFragment;

    public HomePagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
        danhsachFragment = new DanhsachFragment();
        thongTinFragment = new ThongTinFragment();
        thongKeFragment = new ThongKeFragment();
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return danhsachFragment;
            case 1:
                return thongTinFragment;
            default:
                return thongKeFragment;
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}

package com.example.uasakb10119039;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

// Nama   : Diva Sabila Ramadhan
// NIM    : 10119039
// Kelas  : IF-1

public class ViewpagerActivity extends AppCompatActivity {

    ViewPager viewPager;
    Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager);

        // memasang adapter ke view pager
        setupViewPager();
    }

    private void setupViewPager() {
        adapter = new Adapter(this);
        viewPager = findViewById(R.id.viewpager);
        viewPager.setAdapter(adapter);
    }

    private class Adapter extends PagerAdapter {
        Context context;
        LayoutInflater inflater;

        public Adapter(Context context) {
            this.context = context;
        }

        // list img
        int[] list_img = {
                R.drawable.note1,
                R.drawable.note2
        };

        // list judul
        int[] list_judul = {
                R.string.tittle1,
                R.string.tittle2
        };

        // list deskripsi
        int[] list_desc = {
                R.string.desc1,
                R.string.desc2
        };

        // list color bg
        int[] list_color = {
                getResources().getColor(R.color.white),
                getResources().getColor(R.color.white)
        };

        @Override
        public int getCount() {
            return list_judul.length;
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return (view == object);
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            inflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.item_layout, container, false);
            LinearLayout linearLayout = view.findViewById(R.id.item_layout);
            ImageView imageView = view.findViewById(R.id.image);
            TextView judul = view.findViewById(R.id.tittle);
            TextView desc = view.findViewById(R.id.desc);

            linearLayout.setBackgroundColor(list_color[position]);
            imageView.setImageResource(list_img[position]);
            judul.setText(list_judul[position]);
            desc.setText(list_desc[position]);

            container.addView(view);
            return view;

        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((LinearLayout) object);
        }
    }

    public void GoToLogin(View view){
        Intent intent = new Intent(ViewpagerActivity.this, LoginActivity.class);
        startActivity(intent);
    }
}
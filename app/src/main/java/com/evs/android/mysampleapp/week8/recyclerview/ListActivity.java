package com.evs.android.mysampleapp.week8.recyclerview;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.evs.android.mysampleapp.R;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        ArrayList<Item> listData = new ArrayList<>();
        listData.add(new Item(1, "Coca Cola", "Beverages", 50,
                "https://d1yjjnpx0p53s8.cloudfront.net/styles/logo-thumbnail/s3/0016/9323/brand.gif"));
        listData.add(new Item(2, "Sprite", "Beverages", 50,
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRN5LGU9oDDvPirToSmpJd_Eb8Ny7J5-M1yuMe4rOqORF0sWYoF&s"));
        listData.add(new Item(3, "Fruita Vitals", "Beverages", 150,
                "https://www.nestle.pk/sites/g/files/pydnoa361/files/fruita-logo.jpg"));
        listData.add(new Item(4, "Milk Pak", "Dairy", 40,
                "https://www.brandsaward.com/application/assets/images/boya-winners/milkpak-logo.jpg"));
        listData.add(new Item(5, "Dalda", "Oil & Ghee", 210,
                "https://www.careerz360.com/cdn.careerz360.com/Content/UserData/empr/e8ded2f5-28a4-4abc-a280-57a693cd0f40/profile_pics/thumbnail_b0bae04d-4ead-41fd-a0c6-af9472add3d5.png"));
        listData.add(new Item(6, "Apple", "Fruit", 130,
                "https://sc01.alicdn.com/kf/ULB8w6e6KOaMiuJk43PTq6ySmXXaR/Fresh-red-delicious-apple-products.jpg_350x350.jpg"));
        listData.add(new Item(7, "Tez Dum", "Tea", 800,
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSJmCGLYcWkk7q0drkieVggol9az6h-ki4QDXIVyC0X_YBd8TD5&s"));
        listData.add(new Item(8, "Blue Band", "Margarine", 450,
                "http://gomart.pk/image/cache/data/incoming/img/p/1/3/9/8/blue-band-margarine-25g-gomart-pakistan-1182-500x500.jpg"));
        listData.add(new Item(9, "Maggi Noodles", "Pasta & Noodles", 40,
                "https://d1yjjnpx0p53s8.cloudfront.net/styles/logo-thumbnail/s3/0002/3783/brand.gif"));
        listData.add(new Item(10, "Tomato Ketchup", "Sauces", 350,
                "http://gomart.pk/image/cache/data/incoming/img/p/1/7/0/0/national-tomato-ketchup-1kg-gomart-pakistan-594-500x500.jpg"));

        initList(listData);
    }


    private void initList(ArrayList<Item> listData) {
        recyclerView = findViewById(R.id.rvList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        /*recyclerView.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL, true));*/
        MyRecyclerViewAdapter adapter = new MyRecyclerViewAdapter(this, listData);
        adapter.setOnListItemClickListener(new OnListItemClickListener() {
            @Override
            public void onItemClick(int position, Item item) {
                showToast(item.getName() + " has clicked", Toast.LENGTH_SHORT);
            }
        });
        recyclerView.setAdapter(adapter);
    }

    /*
    * UTILITY METHODS
    */

    Toast toast;

    private void showToast(String text, int duration) {
        if(toast != null) {
            toast.cancel();
            toast = Toast.makeText(ListActivity.this, text, duration);
            toast.show();
        } else {
            toast = Toast.makeText(ListActivity.this, text, duration);
            toast.show();
        }
    }
}

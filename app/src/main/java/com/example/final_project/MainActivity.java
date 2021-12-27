package com.example.final_project;
import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.Manifest;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private Button btn_all ,btn_meat,btn_vegetable ,btn_seafood,btn_soup;
    private Spinner spinner;
    SQLiteDataBaseHelper mDBHelper;

    //所有資料
    ArrayList<HashMap<String, String>> fullData = new ArrayList<>();
    //被選中的資料
    ArrayList<HashMap<String, String>> selectedData = new ArrayList<>();


    MainActivity.MyAdapter myAdapter;
    //存圖片
    class Data{
        int photo;
        String name;

    }

    /*public class MyAdapter extends BaseAdapter{
        private MainActivity.Data[] data;
        private int view;

        public  MyAdapter (MainActivity.Data[] data, int view){
            this.data = data;
            this.view = view;
        }
        @Override
        public int getCount () {
            return data.length;
        }
        @Override
        public  Object getItem (int position) {
            return data[position];
        }
        @Override
        public  long getItemId (int position) {
            return  0 ;
        }
        @Override
        public View getView (int position, View convertView, ViewGroup parent){
            convertView = getLayoutInflater().inflate(view,parent, false);
            TextView name = convertView.findViewById(R.id.name);
            name.setText(data[position].name);
            ImageView imageView = convertView.findViewById(R.id.imageView);
            imageView.setImageResource(data[position].photo);
            return convertView;
        }
    }*/


    @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //spinner
        String[] foreignArray = new String[]{"扠菜呷奔", "庫克廚房", "從零開始的異世界廚房"};
        int[] foreignPhotoArray = new int[]{R.drawable.gold, R.drawable.apple,
                R.drawable.re};
        Data[] foreignData = new Data[foreignArray.length];
        for (int i = 0; i < foreignData.length; i++) {
            foreignData[i] = new Data();
            foreignData[i].name = foreignArray[i];
            foreignData[i].photo = foreignPhotoArray[i];

        }

        //
            btn_all = findViewById(R.id.btn_all);
            btn_meat = findViewById(R.id.btn_meat);
            btn_vegetable = findViewById(R.id.btn_vegetable);




            mDBHelper.putData("從零從開始的異世界廚房","m","frog","活體丟進去煮");
            mDBHelper.putData("從零從開始的異世界廚房","m","dog","嘴吧纏住剁掉四肢分別煮");
            mDBHelper.putData("從零從開始的異世界廚房","m","cat","手工拔毛油炸");
            mDBHelper.putData("從零從開始的異世界廚房","v","lobo","直接啃就好吃菜還要煮?");
            mDBHelper.putData("從零從開始的異世界廚房","v","mushroom","挑最毒的吃");
            mDBHelper.putData("庫克廚房","m","福壽螺","榨汁");
            mDBHelper.putData("庫克廚房","v","義大利香料","佐義大利麵");
            mDBHelper.putData("擦菜大全","m","生鼠肉丼飯","頭部淋熱油開吃");
            mDBHelper.putData("擦菜大全","v","公園雜草","佐新鮮牛糞");



            btn_all.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                }
            });

    }

    private class MyAdapter extends RecyclerView.Adapter<MainActivity.MyAdapter.ViewHolder> {//設置Adapter
        @NonNull
        @Override
        public MainActivity.MyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(android.R.layout.simple_list_item_1, null);
            return new MainActivity.MyAdapter.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MainActivity.MyAdapter.ViewHolder holder, int position) {
            holder.tvTitle.setText("風格:"+ fullData.get(position).get("bookName")+
                    "\t種類:"+ fullData.get(position).get("")+
                    "\t品名:"+ fullData.get(position).get("press")+
                    "\t料理方式:"+ fullData.get(position).get("counter"));
            holder.itemView.setOnClickListener((v) -> {

                selectedData.clear();
                selectedData = mDBHelper.searchBySeriesName(fullData.get(position).get("id"));

            });
        }

        @Override
        public int getItemCount() {
            return fullData.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView tvTitle;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                tvTitle = itemView.findViewById(android.R.id.text1);
            }
        }
    }


}
package com.app.ned.kinmen;

import android.content.pm.ApplicationInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.ned.kinmen.model.News;
import com.yydcdut.sdlv.Menu;
import com.yydcdut.sdlv.MenuItem;
import com.yydcdut.sdlv.SlideAndDragListView;

import java.util.List;

public class NewsManagementActivity extends AppCompatActivity {

    private ListView swipeMenu;
    private List<News> newsList;
    private List<ApplicationInfo> mAppList;
    private BaseAdapter mAdapter;
    private SlideAndDragListView slideAndDragListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_management);

        this.processViews();

        mAppList = getPackageManager().getInstalledApplications(0);

        mAdapter = new BaseAdapter() {
            @Override
            public int getCount() {
                return mAppList.size();
            }

            @Override
            public Object getItem(int position) {
                return mAppList.get(position);
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                CustomViewHolder cvh;
                if (convertView == null) {
                    cvh = new CustomViewHolder();
                    convertView = LayoutInflater.from(NewsManagementActivity.this).inflate(R.layout.item_custom_btn, null);
                    cvh.imgLogo = (ImageView) convertView.findViewById(R.id.img_item_edit);
                    cvh.txtName = (TextView) convertView.findViewById(R.id.txt_item_edit);
                    cvh.btnClick = (Button) convertView.findViewById(R.id.btn_item_click);
                    cvh.btnClick.setOnClickListener(mOnClickListener);
                    convertView.setTag(cvh);
                } else {
                    cvh = (CustomViewHolder) convertView.getTag();
                }
                ApplicationInfo item = (ApplicationInfo) this.getItem(position);
                cvh.txtName.setText(item.loadLabel(getPackageManager()));
                cvh.imgLogo.setImageDrawable(item.loadIcon(getPackageManager()));
                cvh.btnClick.setText(position + "");
                cvh.btnClick.setTag(position);
                return convertView;
            }

            class CustomViewHolder {
                public ImageView imgLogo;
                public TextView txtName;
                public Button btnClick;
            }

            private View.OnClickListener mOnClickListener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Object o = v.getTag();
                    if (o instanceof Integer) {
                        Toast.makeText(NewsManagementActivity.this, "button click-->" + ((Integer) o), Toast.LENGTH_SHORT).show();
                    }
                }
            };
        };

        this.initSwipeMenuC();

        this.processControllers();
    }

    private void processControllers() {
        slideAndDragListView.setOnSlideListener(new SlideAndDragListView.OnSlideListener() {

            @Override
            public void onSlideOpen(View view, View parentView, int position, int direction) {

            }

            @Override
            public void onSlideClose(View view, View parentView, int position, int direction) {

            }
        });

        slideAndDragListView.setOnMenuItemClickListener(new SlideAndDragListView.OnMenuItemClickListener() {
            @Override
            public int onMenuItemClick(View v, int itemPosition, int buttonPosition, int direction) {
                switch (direction) {
                    case MenuItem.DIRECTION_LEFT:
                        switch (buttonPosition) {
                            case 0://One
                                Toast.makeText(NewsManagementActivity.this, "One", Toast.LENGTH_SHORT).show();
                                return Menu.ITEM_SCROLL_BACK;
                        }
                        break;
                    case MenuItem.DIRECTION_RIGHT:
                        switch (buttonPosition) {
                            case 0://icon
                                Toast.makeText(NewsManagementActivity.this, "icon", Toast.LENGTH_SHORT).show();
                                return Menu.ITEM_DELETE_FROM_BOTTOM_TO_TOP;
                        }
                        break;
                    default :
                        return Menu.ITEM_NOTHING;
                }
                return itemPosition;
            }
        });

        slideAndDragListView.setOnListItemClickListener(new SlideAndDragListView.OnListItemClickListener() {
            @Override
            public void onListItemClick(View v, int position) {
                Toast.makeText(NewsManagementActivity.this, "Click", Toast.LENGTH_SHORT).show();
            }
        });

        slideAndDragListView.setOnListItemLongClickListener(new SlideAndDragListView.OnListItemLongClickListener() {
            @Override
            public void onListItemLongClick(View view, int position) {
                Toast.makeText(NewsManagementActivity.this, "LongClick", Toast.LENGTH_SHORT).show();
            }
        });

        slideAndDragListView.setOnDragListener(new SlideAndDragListView.OnDragListener() {
            @Override
            public void onDragViewStart(int position) {

            }

            @Override
            public void onDragViewMoving(int position) {

            }

            @Override
            public void onDragViewDown(int position) {

            }
        }, mAppList);
    }



    private void processViews() {
        slideAndDragListView = (SlideAndDragListView) findViewById(R.id.slideanddrag_listview);
    }

    private void initSwipeMenuC() {
        Menu mMenu = new Menu(true, true);
        mMenu.addItem(new MenuItem.Builder().setWidth((int) getResources().getDimension(R.dimen.slv_item_bg_btn_width) * 2)
                .setBackground(Utils.getDrawable(this, R.drawable.btn_left0))
                .setText("One")
                .setTextColor(Color.GRAY)
                .setTextSize(14)
                .build());
        mMenu.addItem(new MenuItem.Builder().setWidth((int) getResources().getDimension(R.dimen.slv_item_bg_btn_width))
                .setBackground(Utils.getDrawable(this, R.drawable.btn_left1))
                .setText("Two")
                .setTextColor(Color.BLACK)
                .setTextSize((14))
                .build());
        mMenu.addItem(new MenuItem.Builder().setWidth((int) getResources().getDimension(R.dimen.slv_item_bg_btn_width) + 30)
                .setBackground(Utils.getDrawable(this, R.drawable.btn_right0))
                .setText("Three")
                .setDirection(MenuItem.DIRECTION_RIGHT)
                .setTextColor(Color.BLACK)
                .setTextSize(14)
                .build());
        mMenu.addItem(new MenuItem.Builder().setWidth((int) getResources().getDimension(R.dimen.slv_item_bg_btn_width_img))
                .setBackground(Utils.getDrawable(this, R.drawable.btn_right1))
                .setDirection(MenuItem.DIRECTION_RIGHT)
                .setIcon(getResources().getDrawable(R.mipmap.ic_launcher))
                .build());
//set in sdlv
        slideAndDragListView.setMenu(mMenu);

        slideAndDragListView.setAdapter(mAdapter);
    }

}

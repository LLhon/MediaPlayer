package meizu.mediaplayer;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.InjectView;
import meizu.mediaplayer.factory.FragmentFactory;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @InjectView(R.id.toolbar)
    Toolbar mToolbar;
    @InjectView(R.id.viewpager)
    ViewPager mViewPager;
    @InjectView(R.id.drawerlayout)
    DrawerLayout mDrawerLayout;
    @InjectView(R.id.menu_left)
    FrameLayout mMenuLeft;
    @InjectView(R.id.ib_top_play)
    ImageButton ib_play;
    @InjectView(R.id.ib_top_list)
    ImageButton ib_list;
    @InjectView(R.id.ib_top_lrc)
    ImageButton ib_lrc;
    @InjectView(R.id.ib_top_volumn)
    ImageButton ib_volumn;
    @InjectView(R.id.ib_bottom_model)
    ImageButton ib_bottom_model;
    @InjectView(R.id.ib_bottom_last)
    ImageButton ib_bottom_last;
    @InjectView(R.id.ib_bottom_play)
    ImageButton ib_bottom_play;
    @InjectView(R.id.ib_bottom_next)
    ImageButton ib_bottom_next;
    @InjectView(R.id.ib_bottom_update)
    ImageButton ib_bottom_update;

    private int[] tabs = {R.id.ib_top_play, R.id.ib_top_list, R.id.ib_top_lrc, R.id.ib_top_volumn};
    private int tabIndex = 0;
    private MyPagerAdapter mAdapter;
    public static final int INDEX_0 = 0;
    public static final int INDEX_1 = 1;
    public static final int INDEX_2 = 2;
    public static final int INDEX_3 = 3;
    private ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        initData();

        mAdapter = new MyPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mAdapter);
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                int i = tabs[position];
                selectTab(i);
                //colorChange(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /**
     * 界面颜色的更改
     * @param position
     */
    @SuppressLint("NewApi")
    private void colorChange(int position) {
        //用来提取颜色的Bitmap
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), FragmentFactory.getFragment(position).getId());
        System.out.println("fragmentId: " + FragmentFactory.getFragment(position).getId());
        System.out.println("Bitmap: " + bitmap);
        Palette.generateAsync(bitmap, new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
                Palette.Swatch vibrant = palette.getVibrantSwatch();
                mToolbar.setBackgroundColor(vibrant.getRgb());
            }
        });
    }

    /**
     * 切换Tab标签的选中样式
     * @param position
     */
    private void selectTab(int position) {
        ib_play.setSelected(false);
        ib_list.setSelected(false);
        ib_lrc.setSelected(false);
        ib_volumn.setSelected(false);

        switchImageButton(position);
    }

    /**
     * 初始化Tab标签的选中样式
     */
    public void initData() {
        ib_play.setSelected(true);
        ib_list.setSelected(false);
        ib_lrc.setSelected(false);
        ib_volumn.setSelected(false);

        ib_play.setOnClickListener(this);
        ib_list.setOnClickListener(this);
        ib_lrc.setOnClickListener(this);
        ib_volumn.setOnClickListener(this);

        initToolbar();
    }

    /**
     * 初始化Toolbar
     */
    public void initToolbar() {
        mToolbar.setTitle("天天动听");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.drawer_open, R.string.drawer_close);
        mDrawerToggle.syncState();
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_settings:
                        Toast.makeText(MainActivity.this, "action_settings", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.ab_search:
                        Toast.makeText(MainActivity.this, "action_search", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.action_share:
                        Toast.makeText(MainActivity.this, "action_share", Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });
    }

    @Override
    public void onClick(View v) {
        switchImageButton(v.getId());
        //设置当前默认的Pager页面
        mViewPager.setCurrentItem(tabIndex);
        //通知数据更新
        mAdapter.notifyDataSetChanged();
    }

    /**
     * 设置Tab标签的样式和索引
     * @param id
     */
    public void switchImageButton(int id) {
        switch (id) {
            case R.id.ib_top_play:
                ib_play.setSelected(true);
                tabIndex = INDEX_0;
                break;
            case R.id.ib_top_list:
                ib_list.setSelected(true);
                tabIndex = INDEX_1;
                break;
            case R.id.ib_top_lrc:
                ib_lrc.setSelected(true);
                tabIndex = INDEX_2;
                break;
            case R.id.ib_top_volumn:
                ib_volumn.setSelected(true);
                tabIndex = INDEX_3;
                break;
        }
    }

    class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return FragmentFactory.getFragment(position);
        }

        @Override
        public int getCount() {
            return tabs.length;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }
}

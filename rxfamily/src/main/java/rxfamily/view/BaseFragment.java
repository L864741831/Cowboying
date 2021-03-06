package rxfamily.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.trello.rxlifecycle.components.support.RxFragment;

import rxfamily.R;


public abstract class BaseFragment extends RxFragment {

    protected BaseActivity mActivity;
    private View view;

    private Toolbar toolbar;
    private TextView titleTv;
    private Boolean hasMenu = false;
    private int menuLayout;
    private TextView text_view;
    private ImageView image_view;
    public BaseFragment() {

    }

    @Override
    public void onResume() {
        super.onResume();
//        StatSDKService.onResume(getHoldingActivity(),"f680476896");
    }

    @Override
    public void onPause() {
        super.onPause();
//        StatSDKService.onPause(getHoldingActivity(),"f680476896");
    }

    /**
     * 初始化view
     * @param view
     * @param savedInstanceState
     */
    protected abstract void initView(View view, Bundle savedInstanceState);

    /**
     * 获取布局文件ID
     * @return
     */
    protected abstract int getLayoutId();

    /**
     * 下拉加载更多
     * @return
     */
    public View createHeaderView() {
        View headerView = LayoutInflater.from(mActivity)
                .inflate(R.layout.layout_head, null);
        text_view=headerView.findViewById(R.id.text_view);
        image_view=headerView.findViewById(R.id.image_view);
        text_view.setVisibility(View.VISIBLE);
        text_view.setText("正在加载，请稍后...");
        Glide.with(this).load(R.drawable.pushcows).into(image_view);
        return headerView;
    }

    /**
     * 下拉加载更多
     * @return
     */
    public View createHeaderViewTrasport() {
        View headerView = LayoutInflater.from(mActivity)
                .inflate(R.layout.layout_head_transport, null);
        text_view=headerView.findViewById(R.id.text_view);
        image_view=headerView.findViewById(R.id.image_view);
        text_view.setVisibility(View.VISIBLE);
        text_view.setText("正在加载，请稍后...");
        Glide.with(this).load(R.drawable.pushcows).into(image_view);
        return headerView;
    }
    /**
     * 获取宿主Activity
     * @return
     */
    protected BaseActivity getHoldingActivity() {
        return mActivity;
    }
    /**
     * 获取版本号
     * @return
     */
    public String getVersionCodes() {
        return "1.0";
    }
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if(view == null){
            view = inflater.inflate(getLayoutId(), null,false);
            initView(view, savedInstanceState);
        }
        //缓存的rootView需要判断是否已经被加过parent， 如果有parent需要从parent删除，要不然会发生这个rootview已经有parent的错误。
        ViewGroup parent = (ViewGroup) view.getParent();
        if (parent != null) {
            parent.removeView(view);
        }
        return view;
    }


    public void showToast(String txt) {
        try {
            Toast.makeText(getHoldingActivity(), txt, Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mActivity = (BaseActivity)context;
    }

    public void initToolbar(boolean hasTitle) {
        toolbar = view.findViewById(R.id.toolbar);
        mActivity.setSupportActionBar(toolbar);

        ActionBar actionBar = mActivity.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(false);
            //使能app bar的导航功能
            actionBar.setDisplayShowTitleEnabled(false);
            //必须设置
        }
        toolbar.setTitle("");

        if(hasTitle){
            titleTv = view.findViewById(R.id.toolbar_title);
        }
    }

    public Toolbar getToolbar() {
        return toolbar;
    }

    protected void setToolbarTitle(String title) {
        if(titleTv != null){
            titleTv.setText(title);
        }
    }

    protected void setHasMenu(Boolean show) {
        this.hasMenu = show;
        setHasOptionsMenu(true);
    }

    protected Boolean getHasMenu() {
        return this.hasMenu;
    }

    /**
     * 设置右侧菜单layout
     */
    protected void setMenuLayout(int layoutId){
        this.menuLayout = layoutId;
    }

    protected int getMenuLayout(){
        return this.menuLayout;
    }

    /**
     *  右侧菜单(可自定义菜单layout)
     */
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        if(getHasMenu()) {
            menu.clear();
            inflater.inflate(getMenuLayout(), menu);
            super.onCreateOptionsMenu(menu, inflater);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    protected void startActivity(Class<?> cls) {
        startActivity(new Intent(getHoldingActivity(), cls));
    }

    protected void startActivity(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(getActivity(), cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }
}

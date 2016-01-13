package com.jeanboy.viewdemo.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.jeanboy.viewdemo.R;

/**
 * Created by yule on 2016/1/12.
 */
public class QQListView extends ListView {

    private static final String TAG = QQListView.class.getSimpleName();

    private int touchSlop;//用户滑动的最小距离
    private boolean isSliding;//是否响应滑动
    private int xDown;//手指按下的x坐标
    private int yDown;//手指按下的y坐标
    private int xMove;//手指一动的x坐标
    private int yMove;//手指一动的y坐标
    private LayoutInflater mInflater;

    private PopupWindow mPopupWindow;
    private int mPopupWindowHeight;
    private int mPopupWindowWidth;

    private Button mDelBtn;

    private DelButtonClickListener mListener;

    private View mCurrentView;//手指当前触摸的View
    private int mCurrentViewPos;//手指当前触摸的位置


    public void setDelButtonClickListener(DelButtonClickListener listener) {
        mListener = listener;
    }

    public interface DelButtonClickListener {
        void clickHappend(int position);
    }

    public QQListView(Context context) {
        this(context, null);
    }

    public QQListView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public QQListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    public void  initView(Context context){
        mInflater = LayoutInflater.from(context);
        touchSlop = ViewConfiguration.get(context).getScaledTouchSlop();//获取用户滑动的最小距离

        View view = mInflater.inflate(R.layout.item_delete_btn, null);
        mDelBtn = (Button) view.findViewById(R.id.id_item_btn);
        mPopupWindow = new PopupWindow(view, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        /**
         * 先调用下measure,否则拿不到宽和高
         */
        mPopupWindow.getContentView().measure(0, 0);
        mPopupWindowHeight = mPopupWindow.getContentView().getMeasuredHeight();
        mPopupWindowWidth = mPopupWindow.getContentView().getMeasuredWidth();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        int x = (int) ev.getX();
        int y = (int) ev.getY();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                xDown = x;
                yDown = y;
                if (mPopupWindow.isShowing()) {//如果当前popupWindow显示，则直接隐藏，然后屏蔽ListView的touch事件的下传
                    dismissPopWindow();
                    return false;
                }
                mCurrentViewPos = pointToPosition(xDown, yDown);//获得当前手指按下的item的位置
                View view = getChildAt(mCurrentViewPos - getFirstVisiblePosition());// 获得当前手指按下时的item
                mCurrentView = view;
                break;
            case MotionEvent.ACTION_MOVE:
                xMove = x;
                yMove = y;
                int dx = xMove - xDown;
                int dy = yMove - yDown;
                if (xMove < xDown && Math.abs(dx) > touchSlop && Math.abs(dy) < touchSlop) {//判断是否是从右到左的滑动
//                     Log.e(TAG, "touchslop = " + touchSlop + " , dx = " + dx + " , dy = " + dy);
                    isSliding = true;
                }
                break;
        }

        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        if (isSliding) {//如果是从右到左的滑动才相应
            switch (action) {
                case MotionEvent.ACTION_MOVE:
                    int[] location = new int[2];
                    mCurrentView.getLocationOnScreen(location);//获取当前item的x与y
                    mPopupWindow.setAnimationStyle(R.style.popwindow_delete_btn_anim_style);
                    mPopupWindow.update();
                    mPopupWindow.showAtLocation(mCurrentView, Gravity.LEFT | Gravity.TOP,
                            location[0] + mCurrentView.getWidth(), location[1] + mCurrentView.getHeight() / 2
                                    - mPopupWindowHeight / 2);
                    // 设置删除按钮的回调
                    mDelBtn.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (mListener != null) {
                                mListener.clickHappend(mCurrentViewPos);
                                mPopupWindow.dismiss();
                            }
                        }
                    });
                    break;
                case MotionEvent.ACTION_UP:
                    isSliding = false;
//                    break;
            }
            return true;
        }

        return super.onTouchEvent(ev);
    }

    /**
     * 隐藏popupWindow
     */
    private void dismissPopWindow() {
        if (mPopupWindow != null && mPopupWindow.isShowing()) {
            mPopupWindow.dismiss();
        }
    }
}

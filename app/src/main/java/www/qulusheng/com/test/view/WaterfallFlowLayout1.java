package www.qulusheng.com.test.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class WaterfallFlowLayout1 extends ViewGroup {
    public WaterfallFlowLayout1(Context context) {
        super(context);
    }

    public WaterfallFlowLayout1(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public WaterfallFlowLayout1(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    private int totalHeight = 0;
    private View view = null ;
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        //开始布局
        //取得所有视图信息
        //当前待布局的view顶部高度和左部距离
        int curTop = 0 ;
        int curLeft = 0;
        //当前待布局的view上下左右的位置
        int top,bottom,left,right;

        int index = 0 ;
        for (int i = 0; i < lstLineView.size(); i++) {
            List<View> viewList = lstLineView.get(i) ;
            //遍历当前行的view
            for (int j = 0; j < viewList.size(); j++) {
                view = viewList.get(j) ;

                MarginLayoutParams layoutParams =(MarginLayoutParams) view.getLayoutParams() ;

                //当前行前面view的宽度+当前view的左边距，作为左起始点
                left = curLeft +layoutParams.leftMargin;
                //当前列前面view的高度+当前view的顶部边距，作为顶部起始点
                top = curTop + layoutParams.topMargin ;
                Log.e("lucian_top",top+"== index:"+j) ;
                //当前行当前view的左起始点+view内容的宽度作为右结束点
                right = left+view.getMeasuredWidth() ;
                //当前列当前view的顶部起始点加上view的高作为底部结束点
                bottom = top + view.getMeasuredHeight() ;
                Log.e("lucian_layout",left+"--"+top+"--"+right+"--"+bottom) ;
                //调用子view自己的layout进行布局
                view.layout(left, top, right, bottom);

                curLeft += view.getMeasuredWidth()+layoutParams.leftMargin+ layoutParams.rightMargin ;
            }
            //新行 清零缓存
            curLeft = 0;
            //新行高增加
            curTop += lstHeights.get(i) ;
            Log.e("lucian_curTop",curTop+"") ;

        }
        lstLineView.clear();
        lstHeights.clear();
    }

    //marginlayoutparams用到了
    //如果不重写，返回的layoutparams会报转换类型异常错误
    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }

    //控件内部每行的高度
    List<Integer> lstHeights = new ArrayList<>() ;
    //控件内部每行的子view视图
    List<List<View>> lstLineView = new ArrayList<>() ;

    //绘制流程最终会调用我们自己的onMeasure 和 onLayout
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //1、先完成自己的宽和高测量
        //需要得到mode，进行判断我的显示模式是怎么样的
        int widthMode = MeasureSpec.getMode(widthMeasureSpec) ;
        int heightMode = MeasureSpec.getMode(heightMeasureSpec) ;
        //得到父容器的宽高
        int widthSize = MeasureSpec.getSize(widthMeasureSpec) ;
        int heightSize = MeasureSpec.getSize(heightMeasureSpec) ;

        //控件的宽和高
        int onMeasureWidth = 0 ;
        int onMeasureHeight = 0 ;

        lstHeights.clear();
        lstLineView.clear();



        //控件宽和高需要测量,根据子view的大小确定自己的大小
        //获取子view的数量
        int childCount = getChildCount() ;
        //当前子view的宽和高
        int curChildWidth = 0 ;
        int curChildHeight = 0 ;
        //控件内部可能有多行，记录当前行的宽和高
        int curLineViewsAddWidth = 0 ;
        int curLineViewsMaxHeight = 0 ;

        //单行子view容器
        List<View> lineViewsList = new ArrayList<>() ;
        //循环测量子view
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            //1、对子view进行测量
            measureChild(childAt, widthMeasureSpec, heightMeasureSpec);
            //measureChildWithMargins(childAt,widthMeasureSpec,0,heightMeasureSpec,0);
            //2、对子view的margin进行获取
            MarginLayoutParams mParams = (MarginLayoutParams) childAt.getLayoutParams() ;
            //3、获取子view宽和高,
            // 宽：view的内容+左右margin边距
            // 高：view的内容+上下margin边距
            curChildWidth = childAt.getMeasuredWidth()+ mParams.leftMargin + mParams.rightMargin;
            curChildHeight = childAt.getMeasuredHeight()  + mParams.topMargin + mParams.bottomMargin;

            //当前子view的宽+累加的当前行的宽是否大于控件的宽，用来判断是否需要换行
            if (curChildWidth + curLineViewsAddWidth > widthSize) {
                //需要换行

                //保存当前行的信息
                //每次换行都判断控件宽度是用上一行的宽还是用其他行的最大宽，求最大宽
                onMeasureWidth = Math.max(onMeasureWidth, curLineViewsAddWidth) ;
                //控件的高度是每行的高度和
                onMeasureHeight += curLineViewsMaxHeight ;
                Log.e("lucian_curLineViewsMaxHeight",curLineViewsMaxHeight+"") ;
                lstHeights.add(curLineViewsMaxHeight) ;
                lstLineView.add(lineViewsList) ;

                //记录新行的信息,新行的信息为新行的第一个子view的宽和高
                curLineViewsAddWidth = curChildWidth ;
                curLineViewsMaxHeight = curChildHeight ;
                lineViewsList= new ArrayList<>();
                lineViewsList.add(childAt) ;

            }else {
                //不换行
                curLineViewsMaxHeight = Math.max(curLineViewsMaxHeight,curChildHeight) ;
                curLineViewsAddWidth += curChildWidth ;
                lineViewsList.add(childAt) ;

            }
            //6.如果正好是最后一行需要换行
            if(i == childCount - 1){
                //6.1.记录当前行的最大宽度，高度累加
                curLineViewsAddWidth = Math.max(onMeasureWidth,curLineViewsAddWidth);
                onMeasureHeight += curLineViewsMaxHeight;


                //6.2.将当前行的viewList添加至总的mViewsList，将行高添加至总的行高List
                Log.e("lucian_curLineViewsMaxHeight  i ",curLineViewsMaxHeight+"") ;
                lstLineView.add(lineViewsList);
                lstHeights.add(curLineViewsMaxHeight);

            }
        }

        if (widthMode == MeasureSpec.EXACTLY && heightMode ==MeasureSpec.EXACTLY) {
            //match_parent 宽和高跟随父类
            onMeasureWidth = widthSize ;
            onMeasureHeight = heightSize ;
        }else {

        }

        totalHeight = onMeasureHeight ;
        setMeasuredDimension(onMeasureWidth, onMeasureHeight);
    }


}
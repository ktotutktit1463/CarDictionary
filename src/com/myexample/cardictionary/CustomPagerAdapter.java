package com.myexample.cardictionary;

import java.util.ArrayList;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class CustomPagerAdapter extends PagerAdapter {
	 /** コンテキスト. */
    private Context mContext;
    private ArrayList<Integer> mList; // 画像ファイル名のリスト

    /**
     * コンストラクタ.
     */
    public CustomPagerAdapter(Context context) {
        mContext = context;
        mList = new ArrayList<Integer>();
    }

    /**
     * リストにアイテムを追加する.
     * @param item アイテム
     */
    public void add(Integer item) {
        mList.add(item);
    }

    // アイテムを追加した時に呼ばれる
    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        // リストから画像idを取得
        Integer item = mList.get(position);

        // View を生成
        ImageView imageView = new ImageView(mContext);
        imageView.setImageResource(item);

        // コンテナに追加
        container.addView(imageView);

        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // コンテナから View を削除
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        // リストのアイテム数を返す
        return mList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        // Object 内に View が存在するか判定する
        return view == (ImageView) object;
    }

}

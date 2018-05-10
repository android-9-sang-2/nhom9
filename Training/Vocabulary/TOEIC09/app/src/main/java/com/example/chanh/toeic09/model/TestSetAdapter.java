package com.example.chanh.toeic09.model;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.chanh.toeic09.R;
import com.example.chanh.toeic09.data.DBManager;

import java.io.File;


public class TestSetAdapter  extends BaseAdapter {
    private Context context;
    private TestSet[] testSets;

    public TestSetAdapter(Context context, TestSet[] testSets) {
        this.context = context;
        this.testSets = testSets;
    }
    @Override
    public int getCount() {
        return testSets.length;
    }

    @Override
    public Object getItem(int position) {
        return testSets[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final DBManager dbManager = new DBManager(this.context, "toeic81");
        Part part = dbManager.getPartDetail(testSets[i].getIndexPart());
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = layoutInflater.inflate(R.layout.activity_test_set_row,null);
        TextView tvTestName =(TextView) view.findViewById(R.id.tvTestSetName);
        ImageView ivTestLogo = (ImageView) view.findViewById(R.id.ivIcon);
        tvTestName.setText(part.getName() + " " + testSets[i].getIndexTestSet());
        File myImageFile = new File(part.getIcon());
        ivTestLogo.setImageDrawable(Drawable.createFromPath(myImageFile.toString()));
        return view;
    }
}

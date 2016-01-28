package com.st.advancedtopics;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;


public class CompaniesAdapter extends BaseAdapter {
    private Context ctx;
    private ArrayList<Company> companies;


    public CompaniesAdapter(Context ctx, ArrayList<Company> companies) {
        this.ctx = ctx;
        this.companies = companies;
    }

    public int getCount() {
        return companies.size();
    }

    public Object getItem(int position) {
        return companies.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(ctx);
            convertView = inflater.inflate(R.layout.company, null);
            Button buttonCall = (Button) convertView
                    .findViewById(R.id.buttonCall);
            Button buttonVisit = (Button) convertView
                    .findViewById(R.id.buttonVisit);

            final Company comp = companies.get(position);

            TextView textCompany = (TextView) convertView
                    .findViewById(R.id.textCompany);
            textCompany.setText(comp.getName());


            buttonCall.setOnClickListener(new OnClickListener() {

                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_CALL,
                            Uri.parse("tel:" + comp.getPhone()));
                    ctx.startActivity(intent);

                }

            });


            buttonVisit.setOnClickListener(new OnClickListener() {

                public void onClick(View v) {
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(comp.getUrl()));
                    ctx.startActivity(i);

                }

            });

        }
        return convertView;
    }

}

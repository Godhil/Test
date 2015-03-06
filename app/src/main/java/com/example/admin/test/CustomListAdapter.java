package com.example.admin.test;

/**
 * Created by admin on 05.03.2015.
 */
import android.app.Activity;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


public class CustomListAdapter extends ArrayAdapter<String>{
    private final Activity context;
    private final String[] name;
    private final String[] adress;
    private final Bitmap[] imageId;
    public CustomListAdapter(Activity context,
                      String[] name, String[] adress, Bitmap[] imageId) {
        super(context, R.layout.mylist, name);
        this.context = context;
        this.name = name;
        this.adress = adress;
        this.imageId = imageId;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.mylist, null, true);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.officeNames);
        TextView txtAdress = (TextView) rowView.findViewById(R.id.officeAdress);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.officeImg);
        txtTitle.setText(name[position]);
        txtAdress.setText(adress[position]);
        imageView.setImageBitmap(imageId[position]);
        return rowView;
    }
}
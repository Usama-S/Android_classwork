package com.example.customlistviewadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class ListViewAdapter extends ArrayAdapter<Product> {
    private ArrayList<Product> products;
    private Context context;
    public ListViewAdapter(@NonNull Context context, ArrayList<Product> products) {
        super(context, 0, products);

        this.context = context;
        this.products = products;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View item = LayoutInflater.from(context).inflate(R.layout.listview_item, parent, false);

        TextView title = item.findViewById(R.id.prodTitle),
                price = item.findViewById(R.id.prodPrice),
                description = item.findViewById(R.id.prodDescription);

        title.setText(products.get(position).getName());
        price.setText("Rs. " + products.get(position).getPrice() + "/-");
        description.setText(products.get(position).getDescription());

        return item;
    }
}

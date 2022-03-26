package com.example.batumicinema.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.batumicinema.R;
import com.example.batumicinema.ui.collections.IconItem;

import java.util.ArrayList;
import java.util.List;

public class IconAdapter extends ArrayAdapter<IconItem> {

    private List<IconItem> iconsList = new ArrayList<>();
    private int customLayoutId;

    public IconAdapter(@NonNull Context context, int resource, @NonNull List<IconItem> _iconsList){
        super(context, resource, _iconsList);
        iconsList = _iconsList;
        customLayoutId = resource;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if(v == null){
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(customLayoutId, null);
        }

        ImageView imageView = v.findViewById(R.id.img_icon);
        IconItem iconItem  =  iconsList.get(position);
        imageView.setImageResource(iconItem.getIcon_id());

        return v;
    }
}

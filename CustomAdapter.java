package com.ndphuc.dp.dbtt;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {
    Context context;
    ArrayList<Thoitiet> arrayList;

    public CustomAdapter(Context context, ArrayList<Thoitiet> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.dong_listview,null);

        Thoitiet thoitiet = arrayList.get(i);

        TextView txtDay = view.findViewById(R.id.txtNgay);
        TextView txtStatus = view.findViewById(R.id.txtTrangThai);
        TextView txtMaxTemp = view.findViewById(R.id.txtMaxTemp);
        TextView txtMinTemp  = view.findViewById(R.id.txtMinTemp);
        ImageView imgIcon = view.findViewById(R.id.imgTrangThai);

        txtDay.setText(thoitiet.Day);
        txtStatus.setText(thoitiet.Status);
        txtMaxTemp.setText(thoitiet.MaxTemp+"℃");
        txtMinTemp.setText(thoitiet.MinTemp+"℃");

        switch (thoitiet.Image) {
            case  "01d":
                imgIcon.setImageResource(R.drawable.a01d);
                break;
            case  "01n":
                imgIcon.setImageResource(R.drawable.a01n);
                break;
            case  "02d":
                imgIcon.setImageResource(R.drawable.a02d);
                break;
            case  "02n":
                imgIcon.setImageResource(R.drawable.a02n);
                break;
            case  "03d":
                imgIcon.setImageResource(R.drawable.a03d);
                break;
            case  "03n":
                imgIcon.setImageResource(R.drawable.a03n);
                break;
            case  "04d":
                imgIcon.setImageResource(R.drawable.a04d);
                break;
            case  "04n":
                imgIcon.setImageResource(R.drawable.a04n);
                break;
            case  "09d":
                imgIcon.setImageResource(R.drawable.a09d);
                break;
            case  "09n":
                imgIcon.setImageResource(R.drawable.a09n);
                break;
            case  "10d":
                imgIcon.setImageResource(R.drawable.a10d);
                break;
            case  "10n":
                imgIcon.setImageResource(R.drawable.a10n);
                break;
            case  "11d":
                imgIcon.setImageResource(R.drawable.a11d);
                break;
            case  "13d":
                imgIcon.setImageResource(R.drawable.a13d);
                break;
            case  "13n":
                imgIcon.setImageResource(R.drawable.a13n);
                break;
            case  "50d":
                imgIcon.setImageResource(R.drawable.a50d);
                break;
            case  "50n":
                imgIcon.setImageResource(R.drawable.a50d);
                break;

            default:
                imgIcon.setImageResource(R.drawable.may);
        }


        return view;
    }

}

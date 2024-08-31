package com.example.xuongsql.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.xuongsql.DTO.PhongBanDTO;
import com.example.xuongsql.R;

import java.util.ArrayList;

public class SpinerAdapter extends ArrayAdapter<PhongBanDTO> {
    private Context context;
    private ArrayList<PhongBanDTO> list;
    TextView txt_pb_sp;

    public SpinerAdapter(@NonNull Context context, @NonNull ArrayList<PhongBanDTO> list) {
        super(context, 0, list);
        this.context = context;
        this.list = list;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = View.inflate(context, R.layout.sp_layout, null);
        }
        final  PhongBanDTO phongBanDTO = list.get(position);
        if (phongBanDTO != null) {
            txt_pb_sp = view.findViewById(R.id.tx_tenpb_sp);
            txt_pb_sp.setText(phongBanDTO.getTenPhongBan());

        }
        return view;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = View.inflate(context, R.layout.sp_layout, null);
        }
        final  PhongBanDTO phongBanDTO = list.get(position);
        if (phongBanDTO != null) {
            txt_pb_sp = view.findViewById(R.id.tx_tenpb_sp);
            txt_pb_sp.setText(phongBanDTO.getTenPhongBan());

        }
        return view;
    }
}

package com.example.xuongsql.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.xuongsql.DTO.PhongBanDTO;
import com.example.xuongsql.DbHepler.MyDbHelper;

import java.util.ArrayList;
import java.util.List;

public class PhongBanDAO {

    MyDbHelper dbHelper;
    SQLiteDatabase dbPhongBan;

    public PhongBanDAO(Context context) {
        dbHelper = new MyDbHelper(context);
        dbPhongBan = dbHelper.getWritableDatabase();
    }

    public List<PhongBanDTO> getAllPhongBan() {
        String sql = "SELECT * FROM PhongBan";
        return getDate(sql);
    }

    public int addPhongBan(PhongBanDTO objPhongBan) {
        ContentValues values = new ContentValues();
        values.put("TenPhongBan", objPhongBan.getTenPhongBan());
        long result = dbPhongBan.insert("PhongBan", null, values);
        return (int) result;
    }

    public int Delete(int id) {
        int result = dbPhongBan.delete("PhongBan", "MaPhongBan=?", new String[]{String.valueOf(id)});
        return result;
    }

    public int UpdatePhongBan(PhongBanDTO objPhongBan) {
        ContentValues values = new ContentValues();
        values.put("TenPhongBan", objPhongBan.getTenPhongBan());
        int result = dbPhongBan.update("PhongBan", values, "MaPhongBan=?", new String[]{String.valueOf(objPhongBan.getMaPhongBan())});
        return result;
    }

    public List<PhongBanDTO> getDate(String sql, String... args) {
        List<PhongBanDTO> listPhongBan = new ArrayList<>();
        Cursor cursor = dbPhongBan.rawQuery(sql, args);
        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                PhongBanDTO phongBanDTO = new PhongBanDTO();
                phongBanDTO.setMaPhongBan(cursor.getInt(0));
                phongBanDTO.setTenPhongBan(cursor.getString(1));
                listPhongBan.add(phongBanDTO);
            } while (cursor.moveToNext());
            cursor.close(); // Close the cursor
        }
        return listPhongBan;
    }

    public PhongBanDTO getPhongBanById(int id) {
        String sql = "SELECT * FROM PhongBan WHERE MaPhongBan = ?";
        List<PhongBanDTO> list = getDate(sql, String.valueOf(id));
        return list.isEmpty() ? null : list.get(0);
    }


}

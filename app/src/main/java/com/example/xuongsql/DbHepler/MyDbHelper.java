package com.example.xuongsql.DbHepler;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MyDbHelper extends SQLiteOpenHelper {
    private Context context;
    private static final String DB_NAME = "qlnv";
    private static final int DB_VERSION = 1;


    public MyDbHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String tb_Phongban = "CREATE TABLE PhongBan (\n" +
                "    MaPhongBan  INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "    TenPhongBan TEXT    NOT NULL\n" +
                ");\n";
        db .execSQL(tb_Phongban);
        String tb_NhanVien = "CREATE TABLE NhanVien (\n" +
                "    MaNhanVien TEXT PRIMARY KEY ,\n" +
                "    MaPhongBan INTEGER REFERENCES PhongBan (MaPhongBan) DEFAULT 1,\n" +
                "    HoDem      TEXT    NOT NULL,\n" +
                "    Ten        TEXT    NOT NULL,\n" +
                "    Luong      INTEGER DEFAULT 0," +
                "    Tuoi       INTEGER DEFAULT 0,\n" +
                "    DiaChi     TEXT    DEFAULT '',\n" +
                "    ChucVu     TEXT    DEFAULT 'NhanVien'," +
                "    MatKhau TEXT NOT NULL" +
                ");\n";
        db.execSQL(tb_NhanVien);
        String insertpb = "INSERT INTO PhongBan VALUES (1,'HanhChinh')";
        db.execSQL(insertpb);
        String insertnv = "INSERT INTO NhanVien VALUES ('nv01',1,'Bui Duy','Ton',0,20,'Ha Noi','Admin','123')";
        db.execSQL(insertnv);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion) {
            db.execSQL("DROP TABLE IF EXISTS QuanLy");
            db.execSQL("DROP TABLE IF EXISTS PhongBan");
            db.execSQL("DROP TABLE IF EXISTS NhanVien");
            onCreate(db);
        }

    }
}

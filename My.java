package com.example.day15_shengji;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class My extends SQLiteOpenHelper{
	Context context;
	public My(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
		this.context = context;
	}

	//创建的时候   只走一次
	@Override
	public void onCreate(SQLiteDatabase db) {
		//初始化创表
		String sql = "create table demo(id integer primary key autoincrement,name text)";
		db.execSQL(sql);
		//初始化  数据
		
		ContentValues values = new ContentValues();
		values.put("name", "测试");
		
		db.insert("demo", null, values );
		
		
	}

	//数据库升级
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		Toast.makeText(context, "数据库要升级啦", Toast.LENGTH_LONG).show();
		
		//把原有的表  更名 (临时表)    编写sql   执行sql
		
		
		//创建新表     (删除不想要的字段    修改原有的字段    添加新的字段)
		
		
		// 把 原表的数据   导入    新表
		
		//删除原表
		
		/*//添加字段
		String sql  = "alter table demo add column sex text";
		db.execSQL(sql);*/
		
		//原表变为临时表
		String temp = "alter table test rename to temp";
		db.execSQL(temp);
		
		//创建新表
		String newtable = "create table demo(id integer primary key autoincrement,ceshi text)";
		db.execSQL(newtable);
		
		//旧表数据导入新表
		String insertslq = "insert into demo(ceshi) select name from temp";
		db.execSQL(insertslq);
		
		//删除旧表
		String del = " drop table temp";
		db.execSQL(del);
		
	}

}

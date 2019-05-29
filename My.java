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

	//������ʱ��   ֻ��һ��
	@Override
	public void onCreate(SQLiteDatabase db) {
		//��ʼ������
		String sql = "create table demo(id integer primary key autoincrement,name text)";
		db.execSQL(sql);
		//��ʼ��  ����
		
		ContentValues values = new ContentValues();
		values.put("name", "����");
		
		db.insert("demo", null, values );
		
		
	}

	//���ݿ�����
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		Toast.makeText(context, "���ݿ�Ҫ������", Toast.LENGTH_LONG).show();
		
		//��ԭ�еı�  ���� (��ʱ��)    ��дsql   ִ��sql
		
		
		//�����±�     (ɾ������Ҫ���ֶ�    �޸�ԭ�е��ֶ�    ����µ��ֶ�)
		
		
		// �� ԭ�������   ����    �±�
		
		//ɾ��ԭ��
		
		/*//����ֶ�
		String sql  = "alter table demo add column sex text";
		db.execSQL(sql);*/
		
		//ԭ���Ϊ��ʱ��
		String temp = "alter table test rename to temp";
		db.execSQL(temp);
		
		//�����±�
		String newtable = "create table demo(id integer primary key autoincrement,ceshi text)";
		db.execSQL(newtable);
		
		//�ɱ����ݵ����±�
		String insertslq = "insert into demo(ceshi) select name from temp";
		db.execSQL(insertslq);
		
		//ɾ���ɱ�
		String del = " drop table temp";
		db.execSQL(del);
		
	}

}

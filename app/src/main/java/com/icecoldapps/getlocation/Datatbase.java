package com.icecoldapps.getlocation;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Datatbase extends SQLiteOpenHelper {

	public Context myContext;
	public SQLiteDatabase myDatabase;

	public final String PKG_NAME = Datatbase.class.getPackage().getName();

	public final String DB_PATH = "data/data/" + PKG_NAME + "/";

	public final static String DB_NAME = "dbgps";
	public final static int DB_VERSION = 1;

	public Datatbase(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
		// TODO Auto-generated constructor stub

		myContext = context;
	}

	@Override
	public void onCreate(SQLiteDatabase arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}



	public void copyDbFile() throws IOException {

		byte[] buffer = new byte[1024];
		int length;

		InputStream in = myContext.getAssets().open(DB_NAME);

		OutputStream out = new FileOutputStream(DB_PATH + DB_NAME);

		while ((length = in.read(buffer)) > 0) {

			out.write(buffer, 0, length);

		}
		in.close();
		out.close();
		out.flush();
		in = null;
		out = null;

	}

	public void copyDatabase() {

		File dbFile = new File(DB_PATH + DB_NAME);

		if (!dbFile.exists()) {
			try {
				copyDbFile();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public void open() {
		myDatabase = SQLiteDatabase.openDatabase(DB_PATH + DB_NAME, null,
				myDatabase.OPEN_READWRITE);

	}

	public void close() {
		myDatabase.close();

	}


	public void Insert(double lat,double lon,double acc,String date)
	{
		ContentValues cv = new ContentValues();
		cv.put("lat", lat);
		cv.put("long", lon);
		cv.put("accuracy", acc);
		cv.put("date", date);

		myDatabase.insert("tb_gps", "lat", cv);
		cv.clear();
	}
	public String[] Select( String colName) {

		String[] tmp = null;

		String sql = "SELECT " + colName + " FROM  tb_gps";
		Cursor cu = myDatabase.rawQuery(sql, null);

		tmp = new String[cu.getCount()];

		if (cu.getCount() > 0) {

			cu.moveToFirst();

			for (int i = 0; i < tmp.length; i++) {

				tmp[i] = cu.getString(cu.getColumnIndex(colName));
				cu.moveToNext();

			}

		}

		return tmp;

	}

}

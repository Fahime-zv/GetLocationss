package com.icecoldapps.getlocation;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Datejalali {

	private class SolarCalendar {

		public String strWeekDay = "";
		public String strMonth = "";

		int date;
		int month;
		int year;

		public SolarCalendar(String str) {

			Date MiladiDate = null;
			SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
			try {
				MiladiDate = format.parse(str);

				System.out.println(date);

			} catch (java.text.ParseException e) {

				e.printStackTrace();
			}

		}

		public SolarCalendar() {
			Date MiladiDate = new Date();

			Log.i("PRS", "from solarcalender=" + MiladiDate);
			calcSolarCalendar(MiladiDate);
		}

		public SolarCalendar(Date MiladiDate) {
			calcSolarCalendar(MiladiDate);
		}

		private void calcSolarCalendar(Date MiladiDate) {

			int ld;

			int miladiYear = MiladiDate.getYear() + 1900;
			int miladiMonth = MiladiDate.getMonth() + 1;
			int miladiDate = MiladiDate.getDate();
			int WeekDay = MiladiDate.getDay();

			int[] buf1 = new int[12];
			int[] buf2 = new int[12];

			buf1[0] = 0;
			buf1[1] = 31;
			buf1[2] = 59;
			buf1[3] = 90;
			buf1[4] = 120;
			buf1[5] = 151;
			buf1[6] = 181;
			buf1[7] = 212;
			buf1[8] = 243;
			buf1[9] = 273;
			buf1[10] = 304;
			buf1[11] = 334;

			buf2[0] = 0;
			buf2[1] = 31;
			buf2[2] = 60;
			buf2[3] = 91;
			buf2[4] = 121;
			buf2[5] = 152;
			buf2[6] = 182;
			buf2[7] = 213;
			buf2[8] = 244;
			buf2[9] = 274;
			buf2[10] = 305;
			buf2[11] = 335;

			if ((miladiYear % 4) != 0) {
				date = buf1[miladiMonth - 1] + miladiDate;

				if (date > 79) {
					date = date - 79;
					if (date <= 186) {
						switch (date % 31) {
						case 0:
							month = date / 31;
							date = 31;
							break;
						default:
							month = (date / 31) + 1;
							date = (date % 31);
							break;
						}
						year = miladiYear - 621;
					} else {
						date = date - 186;

						switch (date % 30) {
						case 0:
							month = (date / 30) + 6;
							date = 30;
							break;
						default:
							month = (date / 30) + 7;
							date = (date % 30);
							break;
						}
						year = miladiYear - 621;
					}
				} else {
					if ((miladiYear > 1996) && (miladiYear % 4) == 1) {
						ld = 11;
					} else {
						ld = 10;
					}
					date = date + ld;

					switch (date % 30) {
					case 0:
						month = (date / 30) + 9;
						date = 30;
						break;
					default:
						month = (date / 30) + 10;
						date = (date % 30);
						break;
					}
					year = miladiYear - 622;
				}
			} else {
				date = buf2[miladiMonth - 1] + miladiDate;

				if (miladiYear >= 1996) {
					ld = 79;
				} else {
					ld = 80;
				}
				if (date > ld) {
					date = date - ld;

					if (date <= 186) {
						switch (date % 31) {
						case 0:
							month = (date / 31);
							date = 31;
							break;
						default:
							month = (date / 31) + 1;
							date = (date % 31);
							break;
						}
						year = miladiYear - 621;
					} else {
						date = date - 186;

						switch (date % 30) {
						case 0:
							month = (date / 30) + 6;
							date = 30;
							break;
						default:
							month = (date / 30) + 7;
							date = (date % 30);
							break;
						}
						year = miladiYear - 621;
					}
				}

				else {
					date = date + 10;

					switch (date % 30) {
					case 0:
						month = (date / 30) + 9;
						date = 30;
						break;
					default:
						month = (date / 30) + 10;
						date = (date % 30);
						break;
					}
					year = miladiYear - 622;
				}

			}

			switch (month) {
			case 1:
				strMonth = "فروردین";
				break;
			case 2:
				strMonth = "اردیبهشت";
				break;
			case 3:
				strMonth = "خرداد¯";
				break;
			case 4:
				strMonth = "تیر";
				break;
			case 5:
				strMonth = "دمردا";
				break;
			case 6:
				strMonth = "شهریور";
				break;
			case 7:
				strMonth = "مهر";
				break;
			case 8:
				strMonth = "آبان";
				break;
			case 9:
				strMonth = "آذر";
				break;
			case 10:
				strMonth = "دی";
				break;
			case 11:
				strMonth = "بهمن";
				break;
			case 12:
				strMonth = "اسفند";
				break;
			}

			switch (WeekDay) {

			case 6:
				strWeekDay = "0";
				break;
			case 0:
				strWeekDay = "1";
				break;
			case 1:
				strWeekDay = "2";
				break;
			case 2:
				strWeekDay = "3";
				break;
			case 3:
				strWeekDay = "4";
				break;
			case 4:
				strWeekDay = "5";
				break;
			case 5:
				strWeekDay = "6";
				break;
			}

		}

	}

	public static String getCurrentShamsidate() {
		Locale loc = new Locale("en_US");
		Datejalali shamsi = new Datejalali();
		SolarCalendar sc = shamsi.new SolarCalendar();
		return String.valueOf(sc.year) + "/"
				+ String.format(loc, "%02d", sc.month) + "/"
				+ String.format(loc, "%02d", sc.date);
	}

	public static String getday() {
		Locale loc = new Locale("en_US");
		Datejalali shamsi = new Datejalali();
		SolarCalendar sc = shamsi.new SolarCalendar();
		return String.format(loc, "%02d", sc.date);
	}

	public static String getmonth() {
		Locale loc = new Locale("en_US");
		Datejalali shamsi = new Datejalali();
		SolarCalendar sc = shamsi.new SolarCalendar();
		return String.format(loc, "%02d", sc.month);
	}

	public static String getyear() {
		Locale loc = new Locale("en_US");
		Datejalali shamsi = new Datejalali();
		SolarCalendar sc = shamsi.new SolarCalendar();
		return String.valueOf(sc.year);
	}

	public static String getdayweek() {
		Locale loc = new Locale("en_US");
		Datejalali shamsi = new Datejalali();
		SolarCalendar sc = shamsi.new SolarCalendar();
		return String.valueOf(sc.strWeekDay);
	}

	public static String getCurrentShamsidatef(String str) {
		Locale loc = new Locale("en_US");
		Datejalali shamsi = new Datejalali();
		SolarCalendar sc = shamsi.new SolarCalendar(str);
		Log.i("PRS", "mointh is=" + sc.month);
		return String.valueOf(sc.year) + "/"
				+ String.format(loc, "%02d", sc.month) + "/"
				+ String.format(loc, "%02d", sc.date);
	}

}

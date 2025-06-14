package nfore.android.bt.pbap;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;

/* loaded from: classes3.dex */
public class DbPbapHelper extends SQLiteOpenHelper {
    private static final String CALLHISTORY_CONTENT = "CallHistory";
    private static final String DATABASE_NAME = "db_pbap";
    private static final int DATABASE_VERSION = 1;
    private static final String PHONEBOOK_CONTENT = "PhoneBookContent";
    private static final String PHONENUMBER_DETAIL = "PhoneNumberDetail";
    private final boolean D;
    private final String SQL_DELETE_CONTACTER;
    private final String SQL_DELETE_PHONENUMBER;
    private final String SQL_DELETE_PHONENUMBER_BY_FULLNAME;
    private final String SQL_EXPRESS_TOTAL;
    private final String SQL_QUERY_CALLHISTORY_BY_ADDRESS_STORAGETYPE;
    private final String SQL_QUERY_CALLHISTORY_BY_SPECIFIED_COLUMNS;
    private final String SQL_QUERY_CONTACTER;
    private final String SQL_QUERY_CONTACTERS;
    private final String SQL_QUERY_FULLNAME_BY_PHONENUM_CELLPHONEADDRESS;
    private final String SQL_QUERY_PHONEBOOKCONTENT;
    private final String SQL_QUERY_PHONEBOOKCONTENT_BY_PHONENUM;
    private final String SQL_QUERY_PHONEDATA_BY_PAGE;
    private final String SQL_QUERY_PHONENUMBERDETAIL;
    private final String SQL_QUERY_PHONETYPE_NAME;
    private final String SQL_QUERY_PHONE_BY_CONTENT_ID;
    private String TAG;
    private Context m_context;
    public static final String[] PHONEBOOK_CONTENT_FIELD = {"_id", "FullName", "FirstName", "LastName", "First_StreetAddress", "First_CityNameAddress", "First_FederalStateAddress", "First_ZipCodeAddress", "First_CountryAddress", "Second_StreetAddress", "Second_CityNameAddress", "Second_FederalStateAddress", "Second_ZipCodeAddress", "Second_CountryAddress", "CellPhone_Address", "StorageType"};
    public static final String[] PHONENUMBER_DETAIL_FIELD = {"_id", "Content_ID", "Type", "Number"};
    public static final String[] PHONE_TYPE_FIELD = {"Type", "TypeName"};
    private static final String PHONE_TYPE = "PhoneType";
    public static final String[] CALLHISTORY_FIELD = {"_id", "FullName", "FirstName", "LastName", "CellPhone_Address", "StorageType", PHONE_TYPE, "PhoneNumber", "HistoryDate", "HistoryTime"};

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }

    public DbPbapHelper(Context context) {
        super(context, DATABASE_NAME, (SQLiteDatabase.CursorFactory) null, 1);
        this.D = true;
        this.TAG = "nfore DBHelper";
        this.SQL_QUERY_PHONEDATA_BY_PAGE = "select a._id, a.FullName, a.StorageType, b.Number, b.Type from PhoneBookContent a inner join PhoneNumberDetail b on a._id = b.Content_ID where a.FullName in (select FullName from PhoneBookContent where CellPhone_Address = ? and StorageType=? group by FullName limit 10 offset ?) and StorageType=? and CellPhone_Address = ? order by FullName";
        this.SQL_QUERY_CONTACTERS = "select a._id, a.FullName, a.StorageType, b.Number, b.Type from PhoneBookContent a inner join PhoneNumberDetail b on a._id = b.Content_ID where a.FullName in (select FullName from PhoneBookContent where CellPhone_Address = ? and StorageType=? group by FullName) and StorageType=? and CellPhone_Address = ? order by ";
        this.SQL_QUERY_CALLHISTORY_BY_ADDRESS_STORAGETYPE = "select a._id, a.FullName, a.StorageType, a.PhoneNumber, a.PhoneType, a.HistoryDate, a.HistoryTime from CallHistory a where a.CellPhone_Address = ? and a.StorageType=? order by ";
        this.SQL_QUERY_CALLHISTORY_BY_SPECIFIED_COLUMNS = "select * from CallHistory a where a.StorageType = ? and a.CellPhone_Address = ?";
        this.SQL_QUERY_PHONE_BY_CONTENT_ID = "select a.*, b.TypeName as TypeName from PhoneNumberDetail a inner join PhoneType b on a.Type = b.Type where a.Content_ID = ?";
        this.SQL_QUERY_PHONEBOOKCONTENT = "select * from PhoneBookContent where FullName like ? and StorageType = ? and CellPhone_Address = ?";
        this.SQL_QUERY_PHONEBOOKCONTENT_BY_PHONENUM = "select * from PhoneBookContent where _id in (select Content_ID from PhoneNumberDetail where Number like ?) and StorageType = ? and CellPhone_Address = ?";
        this.SQL_QUERY_FULLNAME_BY_PHONENUM_CELLPHONEADDRESS = "select FullName from PhoneBookContent where _id in (select Content_ID from PhoneNumberDetail where Number = ?) and CellPhone_Address = ?";
        this.SQL_QUERY_PHONENUMBERDETAIL = "select a.*, b.TypeName from PhoneNumberDetail.a inner join PhoneType b on a.Type = b.Type where Number = ?";
        this.SQL_DELETE_CONTACTER = "delete from PhoneBookContent where FullName = ?";
        this.SQL_DELETE_PHONENUMBER = "delete from PhoneNumberDetail where Number = ?";
        this.SQL_DELETE_PHONENUMBER_BY_FULLNAME = "delete from PhoneNumberDetail where Content_ID in (select _id from PhoneBookContent where FullName = ?)";
        this.SQL_QUERY_PHONETYPE_NAME = "select TypeName from PhoneType where Type = ? ";
        this.SQL_EXPRESS_TOTAL = "select a.FullName from PhoneBookContent a where a.CellPhone_Address=? and StorageType=? group by a.FullName";
        this.SQL_QUERY_CONTACTER = "select FullName from PhoneBookContent where _id = (select Content_ID from PhoneNumberDetail where Number like ? limit 1)";
        this.m_context = context;
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase sQLiteDatabase) throws SQLException {
        StringBuilder sb = new StringBuilder("CREATE TABLE IF NOT EXISTS PhoneBookContent (");
        String[] strArr = PHONEBOOK_CONTENT_FIELD;
        sQLiteDatabase.execSQL(sb.append(strArr[0]).append(" INTEGER primary key autoincrement, ").append(strArr[1]).append(" varchar(16), ").append(strArr[2]).append(" varchar(8), ").append(strArr[3]).append(" varchar(8), ").append(strArr[4]).append(" varchar(20), ").append(strArr[5]).append(" varchar(12), ").append(strArr[6]).append(" varchar(12), ").append(strArr[7]).append(" varchar(12), ").append(strArr[8]).append(" varchar(30), ").append(strArr[9]).append(" varchar(20), ").append(strArr[10]).append(" varchar(12), ").append(strArr[11]).append(" varchar(12), ").append(strArr[12]).append(" varchar(12), ").append(strArr[13]).append(" varchar(30), ").append(strArr[14]).append(" varchar(30), ").append(strArr[15]).append(" varchar(10) ").append(");").toString());
        StringBuilder sb2 = new StringBuilder("CREATE TABLE IF NOT EXISTS PhoneNumberDetail (");
        String[] strArr2 = PHONENUMBER_DETAIL_FIELD;
        sQLiteDatabase.execSQL(sb2.append(strArr2[0]).append(" INTEGER primary key autoincrement, ").append(strArr2[1]).append(" INTEGER, ").append(strArr2[2]).append(" nvarchar(5), ").append(strArr2[3]).append(" nvarchar(20),").append("FOREIGN KEY(").append(strArr2[1]).append(") REFERENCES ").append(PHONEBOOK_CONTENT).append("(").append(strArr[0]).append(") ").append(");").toString());
        StringBuilder sb3 = new StringBuilder("CREATE TABLE IF NOT EXISTS PhoneType (");
        String[] strArr3 = PHONE_TYPE_FIELD;
        sQLiteDatabase.execSQL(sb3.append(strArr3[0]).append(" nvarchar(5) , ").append(strArr3[1]).append(" nvarchar(26) );").toString());
        StringBuilder sb4 = new StringBuilder("CREATE TABLE IF NOT EXISTS CallHistory (");
        String[] strArr4 = CALLHISTORY_FIELD;
        sQLiteDatabase.execSQL(sb4.append(strArr4[0]).append(" INTEGER primary key autoincrement, ").append(strArr4[1]).append(" nvarchar(16), ").append(strArr4[2]).append(" nvarchar(8), ").append(strArr4[3]).append(" nvarchar(8), ").append(strArr4[4]).append(" nvarchar(30) not null, ").append(strArr4[5]).append(" nvarchar(10) not null, ").append(strArr4[6]).append(" nvarchar(5) not null, ").append(strArr4[7]).append(" nvarchar(20) not null, ").append(strArr4[8]).append(" nvarchar(8) not null, ").append(strArr4[9]).append(" nvarchar(6) not null);").toString());
        insertPhoneType(sQLiteDatabase);
    }

    public int insertNumberType(SQLiteDatabase sQLiteDatabase, ArrayList<HashMap<String, String>> arrayList) {
        long jInsert = -1;
        for (int i = 0; i < arrayList.size(); i++) {
            try {
                HashMap<String, String> map = arrayList.get(i);
                ContentValues contentValues = new ContentValues();
                contentValues.put("Type", map.get("Type"));
                contentValues.put("TypeName", map.get("TypeName"));
                jInsert = sQLiteDatabase.insert(PHONE_TYPE, null, contentValues);
            } catch (Exception e) {
                e.printStackTrace();
                Log.d(this.TAG, e.getMessage());
            }
        }
        return (int) jInsert;
    }

    public Cursor isEmptyPhoneType(SQLiteDatabase sQLiteDatabase) {
        return sQLiteDatabase.rawQuery("select count(*) as amount from PhoneType", null);
    }

    public void insertPhoneType(SQLiteDatabase sQLiteDatabase) {
        Log.e(this.TAG, "insertPhoneType");
        try {
            try {
                NodeList elementsByTagName = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(this.m_context.getResources().getAssets().open("phonetype.xml")).getDocumentElement().getElementsByTagName("phoneType");
                ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();
                for (int i = 0; i < elementsByTagName.getLength(); i++) {
                    HashMap<String, String> map = new HashMap<>();
                    NamedNodeMap attributes = elementsByTagName.item(i).getAttributes();
                    if (attributes.getNamedItem("Type").getTextContent().trim().length() > 0) {
                        map.put("Type", attributes.getNamedItem("Type").getTextContent());
                    }
                    if (attributes.getNamedItem("TypeName").getTextContent().trim().length() > 0) {
                        map.put("TypeName", attributes.getNamedItem("TypeName").getTextContent());
                    }
                    arrayList.add(map);
                }
                Cursor cursorIsEmptyPhoneType = isEmptyPhoneType(sQLiteDatabase);
                if (cursorIsEmptyPhoneType != null) {
                    cursorIsEmptyPhoneType.moveToNext();
                    if (cursorIsEmptyPhoneType.getInt(cursorIsEmptyPhoneType.getColumnIndex("amount")) <= 0) {
                        insertNumberType(sQLiteDatabase, arrayList);
                    }
                }
                cursorIsEmptyPhoneType.close();
            } catch (Exception e) {
                e.printStackTrace();
                Log.d(this.TAG, e.getMessage());
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public VCardList queryContacterInfo(SQLiteDatabase sQLiteDatabase, String str, String str2, int i, String str3) {
        boolean z = str != null && str.trim().length() > 0;
        boolean z2 = str2 != null && str2.trim().length() > 0;
        boolean z3 = z && z2;
        Cursor cursorQueryContacterByPhoneNum = null;
        if (z) {
            cursorQueryContacterByPhoneNum = queryContacterByFullName(sQLiteDatabase, str, i, str3);
        } else if (z2) {
            cursorQueryContacterByPhoneNum = queryContacterByPhoneNum(sQLiteDatabase, str2, i, str3);
        }
        ArrayList arrayList = new ArrayList();
        if (cursorQueryContacterByPhoneNum != null && cursorQueryContacterByPhoneNum.getCount() > 0) {
            while (cursorQueryContacterByPhoneNum.moveToNext()) {
                Cursor cursorQueryPhoneNumberByContentId = queryPhoneNumberByContentId(sQLiteDatabase, cursorQueryContacterByPhoneNum.getString(cursorQueryContacterByPhoneNum.getColumnIndex("_id")));
                int columnIndex = cursorQueryPhoneNumberByContentId.getColumnIndex("Number");
                int columnIndex2 = cursorQueryPhoneNumberByContentId.getColumnIndex("TypeName");
                HashSet hashSet = new HashSet();
                while (cursorQueryPhoneNumberByContentId.moveToNext() && (!z3 || cursorQueryPhoneNumberByContentId.getString(columnIndex).trim().indexOf(str2) != -1)) {
                    PhoneInfo phoneInfo = new PhoneInfo();
                    phoneInfo.setPhoneNumber(cursorQueryPhoneNumberByContentId.getString(columnIndex));
                    phoneInfo.setPhoneTypeName(cursorQueryPhoneNumberByContentId.getString(columnIndex2));
                    hashSet.add(phoneInfo);
                }
                if (hashSet.size() > 0) {
                    VCardPack vCardPack = new VCardPack(cursorQueryContacterByPhoneNum);
                    vCardPack.setPhoneNumbers(hashSet);
                    arrayList.add(vCardPack);
                }
            }
        }
        VCardList vCardList = new VCardList();
        vCardList.setVcardPacks(arrayList);
        return vCardList;
    }

    public Cursor queryPhoneTypeName(SQLiteDatabase sQLiteDatabase, String str) {
        if (str == null) {
            str = "C";
        }
        return sQLiteDatabase.rawQuery("select TypeName from PhoneType where Type = ? ", new String[]{str});
    }

    private Cursor queryContacterByFullName(SQLiteDatabase sQLiteDatabase, String str, int i, String str2) {
        return sQLiteDatabase.rawQuery("select * from PhoneBookContent where FullName like ? and StorageType = ? and CellPhone_Address = ?", new String[]{String.valueOf(str) + "%", new StringBuilder().append(i).toString(), str2});
    }

    private Cursor queryContacterByPhoneNum(SQLiteDatabase sQLiteDatabase, String str, int i, String str2) {
        return sQLiteDatabase.rawQuery("select * from PhoneBookContent where _id in (select Content_ID from PhoneNumberDetail where Number like ?) and StorageType = ? and CellPhone_Address = ?", new String[]{"%" + str + "%", new StringBuilder().append(i).toString(), str2});
    }

    public Cursor queryContacterByPhoneNumAndAddress(SQLiteDatabase sQLiteDatabase, String str, String str2) {
        return sQLiteDatabase.rawQuery("select FullName from PhoneBookContent where _id in (select Content_ID from PhoneNumberDetail where Number = ?) and CellPhone_Address = ?", new String[]{str, str2});
    }

    public Cursor queryNumberDetailByPhoneNumber(SQLiteDatabase sQLiteDatabase, String str) {
        return sQLiteDatabase.rawQuery("select a.*, b.TypeName from PhoneNumberDetail.a inner join PhoneType b on a.Type = b.Type where Number = ?", new String[]{str});
    }

    public Cursor queryPhoneNumberByContentId(SQLiteDatabase sQLiteDatabase, String str) {
        return sQLiteDatabase.rawQuery("select a.*, b.TypeName as TypeName from PhoneNumberDetail a inner join PhoneType b on a.Type = b.Type where a.Content_ID = ?", new String[]{str});
    }

    public void deleteContacterByFullName(SQLiteDatabase sQLiteDatabase, String str) throws SQLException {
        sQLiteDatabase.execSQL("delete from PhoneBookContent where FullName = ?", new String[]{str});
    }

    public void deletePhoneNumber(SQLiteDatabase sQLiteDatabase, String str) throws SQLException {
        sQLiteDatabase.execSQL("delete from PhoneNumberDetail where Number = ?", new String[]{str});
    }

    public void deletePhoneNumberByFullName(SQLiteDatabase sQLiteDatabase, String str) throws SQLException {
        sQLiteDatabase.execSQL("delete from PhoneNumberDetail where Content_ID in (select _id from PhoneBookContent where FullName = ?)", new String[]{str});
    }

    public int deleteVcardInfo(SQLiteDatabase sQLiteDatabase, String str, int i) {
        int iDelete;
        Cursor cursorQuery = sQLiteDatabase.query(PHONEBOOK_CONTENT, new String[]{"_id"}, "CellPhone_Address=? and StorageType=?", new String[]{str, String.valueOf(i)}, null, null, null);
        if (cursorQuery.getCount() <= 0 || !cursorQuery.moveToNext()) {
            iDelete = 0;
        } else {
            iDelete = 0;
            do {
                iDelete += sQLiteDatabase.delete(PHONENUMBER_DETAIL, "Content_ID=?", new String[]{cursorQuery.getString(0)});
            } while (cursorQuery.moveToNext());
        }
        int iDelete2 = iDelete >= 0 ? 0 + sQLiteDatabase.delete(PHONEBOOK_CONTENT, "CellPhone_Address=? and StorageType=?", new String[]{str, String.valueOf(i)}) : 0;
        cursorQuery.close();
        return iDelete2;
    }

    public int deleteCallHistoryInfo(SQLiteDatabase sQLiteDatabase, String str, int i) {
        return sQLiteDatabase.delete(CALLHISTORY_CONTENT, "CellPhone_Address=? and StorageType=?", new String[]{str, String.valueOf(i)});
    }

    public void insertVcardInfo(SQLiteDatabase sQLiteDatabase, VCardPack vCardPack, int i) {
        ContentValues contentValues = new ContentValues();
        if (vCardPack != null) {
            contentValues.put("FullName", vCardPack.getFullName());
            contentValues.put("FirstName", vCardPack.getFirstName());
            contentValues.put("LastName", vCardPack.getLastName());
            contentValues.put("First_StreetAddress", vCardPack.getFirst_StreetAddress());
            contentValues.put("First_CityNameAddress", vCardPack.getFirst_CityNameAddress());
            contentValues.put("First_FederalStateAddress", vCardPack.getFirst_FederalStateAddress());
            contentValues.put("First_ZipCodeAddress", vCardPack.getFirst_ZipCodeAddress());
            contentValues.put("First_CountryAddress", vCardPack.getFirst_CountryAddress());
            contentValues.put("Second_StreetAddress", vCardPack.getSecond_StreetAddress());
            contentValues.put("Second_CityNameAddress", vCardPack.getSecond_CityNameAddress());
            contentValues.put("Second_FederalStateAddress", vCardPack.getSecond_FederalStateAddress());
            contentValues.put("Second_ZipCodeAddress", vCardPack.getSecond_ZipCodeAddress());
            contentValues.put("Second_CountryAddress", vCardPack.getSecond_CountryAddress());
            contentValues.put("CellPhone_Address", vCardPack.getCellPhone_Address());
            contentValues.put("StorageType", Integer.valueOf(i));
            long jInsert = sQLiteDatabase.insert(PHONEBOOK_CONTENT, null, contentValues);
            for (PhoneInfo phoneInfo : vCardPack.getPhoneNumbers()) {
                ContentValues contentValues2 = new ContentValues();
                contentValues2.put("Content_ID", Long.valueOf(jInsert));
                contentValues2.put("Type", phoneInfo.getPhoneType());
                contentValues2.put("Number", phoneInfo.getPhoneNumber());
                sQLiteDatabase.insert(PHONENUMBER_DETAIL, null, contentValues2);
            }
        }
    }

    public void insertCallHistoryInfo(SQLiteDatabase sQLiteDatabase, VCardPack vCardPack, int i) {
        ContentValues contentValues = new ContentValues();
        if (vCardPack != null) {
            contentValues.put("FullName", vCardPack.getFullName());
            contentValues.put("FirstName", vCardPack.getFirstName());
            contentValues.put("LastName", vCardPack.getLastName());
            contentValues.put("CellPhone_Address", vCardPack.getCellPhone_Address());
            contentValues.put("StorageType", Integer.valueOf(i));
            for (PhoneInfo phoneInfo : vCardPack.getPhoneNumbers()) {
                contentValues.put(PHONE_TYPE, phoneInfo.getPhoneType());
                contentValues.put("PhoneNumber", phoneInfo.getPhoneNumber());
            }
            contentValues.put("HistoryDate", vCardPack.getHistoryDate());
            contentValues.put("HistoryTime", vCardPack.getHistoryTime());
            sQLiteDatabase.insert(CALLHISTORY_CONTENT, null, contentValues);
        }
    }

    public List<VCardPack> queryPhoneDataByPage(SQLiteDatabase sQLiteDatabase, String str, int i, int i2, String str2) throws NumberFormatException {
        Cursor cursorRawQuery = sQLiteDatabase.rawQuery("select a._id, a.FullName, a.StorageType, b.Number, b.Type from PhoneBookContent a inner join PhoneNumberDetail b on a._id = b.Content_ID where a.FullName in (select FullName from PhoneBookContent where CellPhone_Address = ? and StorageType=? group by FullName limit 10 offset ?) and StorageType=? and CellPhone_Address = ? order by FullName", new String[]{str, str2, String.valueOf(i * i2), str2, str});
        List<VCardPack> listCollectionData = collectionData(cursorRawQuery);
        cursorRawQuery.close();
        for (VCardPack vCardPack : listCollectionData) {
            new HashSet();
            for (int i3 = 0; i3 < vCardPack.getPhoneNumbers().size(); i3++) {
                for (PhoneInfo phoneInfo : vCardPack.getPhoneNumbers()) {
                    Cursor cursorQueryPhoneTypeName = queryPhoneTypeName(sQLiteDatabase, phoneInfo.getPhoneType());
                    if (cursorQueryPhoneTypeName.moveToNext()) {
                        phoneInfo.setPhoneTypeName(cursorQueryPhoneTypeName.getString(0));
                    }
                    cursorQueryPhoneTypeName.close();
                }
            }
        }
        return listCollectionData;
    }

    public List<VCardPack> queryContactersInfo(SQLiteDatabase sQLiteDatabase, String str, String str2, String str3) throws NumberFormatException {
        Cursor cursorRawQuery = sQLiteDatabase.rawQuery("select a._id, a.FullName, a.StorageType, b.Number, b.Type from PhoneBookContent a inner join PhoneNumberDetail b on a._id = b.Content_ID where a.FullName in (select FullName from PhoneBookContent where CellPhone_Address = ? and StorageType=? group by FullName) and StorageType=? and CellPhone_Address = ? order by " + str3, new String[]{str, str2, str2, str});
        List<VCardPack> listCollectionData = collectionData(cursorRawQuery);
        cursorRawQuery.close();
        for (VCardPack vCardPack : listCollectionData) {
            new HashSet();
            for (int i = 0; i < vCardPack.getPhoneNumbers().size(); i++) {
                for (PhoneInfo phoneInfo : vCardPack.getPhoneNumbers()) {
                    Cursor cursorQueryPhoneTypeName = queryPhoneTypeName(sQLiteDatabase, phoneInfo.getPhoneType());
                    if (cursorQueryPhoneTypeName.moveToNext()) {
                        phoneInfo.setPhoneTypeName(cursorQueryPhoneTypeName.getString(0));
                    }
                    cursorQueryPhoneTypeName.close();
                }
            }
        }
        return listCollectionData;
    }

    public VCardList callHistoryBySpecifiedColumns(SQLiteDatabase sQLiteDatabase, int i, String str, String str2, String str3, String str4) throws NumberFormatException {
        ArrayList arrayList = new ArrayList();
        arrayList.add(String.valueOf(i));
        arrayList.add(str);
        String str5 = "select * from CallHistory a where a.StorageType = ? and a.CellPhone_Address = ?";
        if (str2.trim().length() > 0) {
            str5 = "select * from CallHistory a where a.StorageType = ? and a.CellPhone_Address = ? and a.HistoryDate like ?";
            arrayList.add("%" + str2 + "%");
        }
        if (str3.trim().length() > 0) {
            str5 = String.valueOf(str5) + " and a.HistoryTime like ?";
            arrayList.add("%" + str3 + "%");
        }
        if (str4.trim().length() > 0) {
            str5 = String.valueOf(str5) + " and a.PhoneNumber like ?";
            arrayList.add("%" + str4 + "%");
        }
        Cursor cursorRawQuery = sQLiteDatabase.rawQuery(str5, (String[]) arrayList.toArray(new String[arrayList.size()]));
        List<VCardPack> listCollectionData = collectionData(cursorRawQuery);
        cursorRawQuery.close();
        VCardList vCardList = new VCardList();
        vCardList.setVcardPacks(listCollectionData);
        return vCardList;
    }

    public List<VCardPack> queryCallHistoryByAddressAndStorageType(SQLiteDatabase sQLiteDatabase, String str, String str2, String str3) throws NumberFormatException {
        Cursor cursorRawQuery = sQLiteDatabase.rawQuery("select a._id, a.FullName, a.StorageType, a.PhoneNumber, a.PhoneType, a.HistoryDate, a.HistoryTime from CallHistory a where a.CellPhone_Address = ? and a.StorageType=? order by " + str3, new String[]{str, str2});
        List<VCardPack> listCollectionData = collectionData(cursorRawQuery);
        cursorRawQuery.close();
        for (VCardPack vCardPack : listCollectionData) {
            new HashSet();
            for (int i = 0; i < vCardPack.getPhoneNumbers().size(); i++) {
                for (PhoneInfo phoneInfo : vCardPack.getPhoneNumbers()) {
                    Cursor cursorQueryPhoneTypeName = queryPhoneTypeName(sQLiteDatabase, phoneInfo.getPhoneType());
                    if (cursorQueryPhoneTypeName.moveToNext()) {
                        phoneInfo.setPhoneTypeName(cursorQueryPhoneTypeName.getString(0));
                    }
                    cursorQueryPhoneTypeName.close();
                }
            }
        }
        return listCollectionData;
    }

    private List<VCardPack> collectionData(Cursor cursor) throws NumberFormatException {
        ArrayList arrayList = new ArrayList();
        if (cursor.moveToFirst()) {
            do {
                VCardPack vCardPack = new VCardPack();
                vCardPack.setFullName(cursor.getString(cursor.getColumnIndex("FullName")));
                vCardPack.set_id(cursor.getInt(cursor.getColumnIndex("_id")));
                PhoneInfo phoneInfo = new PhoneInfo();
                HashSet hashSet = new HashSet();
                String string = cursor.getString(cursor.getColumnIndex("StorageType"));
                int i = Integer.parseInt(string);
                if (i < 3 && i > 0) {
                    phoneInfo.setPhoneNumber(cursor.getString(cursor.getColumnIndex("Number")));
                    phoneInfo.setPhoneType(cursor.getString(cursor.getColumnIndex("Type")));
                } else if (i >= 3 && i <= 5) {
                    phoneInfo.setPhoneNumber(cursor.getString(cursor.getColumnIndex("PhoneNumber")));
                    phoneInfo.setPhoneType(cursor.getString(cursor.getColumnIndex(PHONE_TYPE)));
                    vCardPack.setHistoryDate(cursor.getString(cursor.getColumnIndex("HistoryDate")));
                    vCardPack.setHistoryTime(cursor.getString(cursor.getColumnIndex("HistoryTime")));
                }
                hashSet.add(phoneInfo);
                vCardPack.setPhoneNumbers(hashSet);
                vCardPack.setStorageType(string);
                arrayList.add(vCardPack);
            } while (cursor.moveToNext());
        }
        return arrayList;
    }

    public void deleteContacterById(SQLiteDatabase sQLiteDatabase, int i) {
        sQLiteDatabase.delete(PHONENUMBER_DETAIL, "Content_ID=?", new String[]{new StringBuilder().append(i).toString()});
        sQLiteDatabase.delete(PHONEBOOK_CONTENT, "_id=?", new String[]{new StringBuilder().append(i).toString()});
    }

    public void deleteCallHistoryById(SQLiteDatabase sQLiteDatabase, int i) {
        sQLiteDatabase.delete(CALLHISTORY_CONTENT, "_id=?", new String[]{new StringBuilder().append(i).toString()});
    }

    public int queryTotalAmount(SQLiteDatabase sQLiteDatabase, String str, String str2) {
        Cursor cursorRawQuery = sQLiteDatabase.rawQuery("select a.FullName from PhoneBookContent a where a.CellPhone_Address=? and StorageType=? group by a.FullName", new String[]{str, str2});
        int count = cursorRawQuery.getCount();
        cursorRawQuery.close();
        return count;
    }

    public String queryNameByPhoneNum(String str) {
        SQLiteDatabase readableDatabase = getReadableDatabase();
        Cursor cursorRawQuery = readableDatabase.rawQuery("select FullName from PhoneBookContent where _id = (select Content_ID from PhoneNumberDetail where Number like ? limit 1)", new String[]{String.valueOf(str) + "%"});
        String string = (cursorRawQuery.getCount() <= 0 || !cursorRawQuery.moveToFirst()) ? "N/A" : cursorRawQuery.getString(0);
        cursorRawQuery.close();
        readableDatabase.close();
        return string;
    }

    public void deleteAllTableContent(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.delete(PHONENUMBER_DETAIL, null, null);
        sQLiteDatabase.delete(PHONEBOOK_CONTENT, null, null);
        sQLiteDatabase.delete(CALLHISTORY_CONTENT, null, null);
    }
}

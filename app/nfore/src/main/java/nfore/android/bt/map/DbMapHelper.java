package nfore.android.bt.map;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

import nfore.android.bt.res.NfDef;


public class DbMapHelper extends SQLiteOpenHelper {
    public static final String FOLDER = "folder";
    public static final String DATABASE_NAME = "db_map";
    private static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "MessageContent";
    private final String SQL_DELETE_All_MESSAGE;
    private final String SQL_DELETE_FOLDER;
    private final String SQL_DELETE_MESSAGE;
    private final String SQL_DELETE_MESSAGE_BY_FOLDER;
    private final String SQL_DELETE_ONE_MESSAGE;
    private final String SQL_MESSAGE;
    private final String SQL_SELECT_MESSAGE;
    private final String SQL_SELECT_MESSGE_BY_FOLDER_AND_HANDLE;
    private final String SQL_SELECT_ONE_MESSAGE;
    private String TAG;
    private String _id;
    private String datetime;
    private String folder;
    private String handle;
    private Object helper;
    private Context m_context;
    private String macAddress;
    private String message;
    private String read;
    private String recipient_addressing;
    private String sender_addressing;
    private String sender_name;
    private String size;
    private String subject;

    public void onUpdate(SQLiteDatabase sQLiteDatabase, String str) {
    }

    public DbMapHelper(Context context) {
        super(context, DATABASE_NAME, (SQLiteDatabase.CursorFactory) null, 1);
        this.TAG = "DbMapHelper";
        this._id = "_id";
        this.handle = "handle";
        this.subject = "subject";
        this.message = "message";
        this.datetime = "datetime";
        this.sender_name = "sender_name";
        this.sender_addressing = "sender_addressing";
        this.recipient_addressing = "recipient_addressing";
        this.size = "size";
        this.read = "read";
        this.macAddress = "macAddress";
        this.folder = FOLDER;
        this.SQL_MESSAGE = "select * from MessageContent where condition = ?";
        this.SQL_SELECT_MESSAGE = "select * from MessageContent where macAddress = ?";
        this.SQL_SELECT_ONE_MESSAGE = "select * from MessageContent where macAddress = ? and handle = ? and folder = ?";
        this.SQL_SELECT_MESSGE_BY_FOLDER_AND_HANDLE = "select * from MessageContent where folder = ? and handle = ? and macAddress = ?";
        this.SQL_DELETE_MESSAGE = "delete from MessageContent where macAddress = ?";
        this.SQL_DELETE_FOLDER = "delete from MessageContent where macAddress = ? and folder = ?";
        this.SQL_DELETE_ONE_MESSAGE = "delete from MessageContent where macAddress = ? and handle = ? and datetime=?";
        this.SQL_DELETE_All_MESSAGE = "delete from MessageContent";
        this.SQL_DELETE_MESSAGE_BY_FOLDER = "delete from MessageContent where folder = ? and handle = ? and macAddress = ?";
        this.m_context = context;
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase sQLiteDatabase) throws SQLException {
        Log.d(this.TAG, "onCreate");
        sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS MessageContent (" + this._id + " Integer primary key autoincrement, " + this.folder + " varchar(6), " + this.handle + " varchar(256), " + this.subject + " varchar(256), " + this.message + " varchar(256), " + this.datetime + " varchar(256), " + this.sender_name + " varchar(256), " + this.sender_addressing + " varchar(256), " + this.recipient_addressing + " varchar(256), " + this.size + " varchar(256), " + this.read + " varchar(3), " + this.macAddress + " varchar(17)) ");
    }

/*    public void insertMessageInfo(SQLiteDatabase sQLiteDatabase, MsgOutline msgOutline) {
        Log.e(this.TAG, "insertMessageInfo");
        ContentValues contentValues = new ContentValues();
        if (msgOutline != null) {
            Log.e(this.TAG, "insertMessageInfo " + msgOutline.getHandle());
            contentValues.put("Folder", msgOutline.getFolder());
            contentValues.put("Handle", msgOutline.getHandle());
            contentValues.put("Subject", msgOutline.getSubject());
            contentValues.put("Message", msgOutline.getMessage());
            contentValues.put("Datetime", msgOutline.getDatetime());
            contentValues.put("Sender_Name", msgOutline.getSender_name());
            contentValues.put("Sender_Addressing", msgOutline.getSender_addressing());
            contentValues.put("Recipient_Addressing", msgOutline.getRecipient_addressing());
            contentValues.put("Size", msgOutline.getSize());
            contentValues.put("Read", msgOutline.getRead());
            contentValues.put("macAddress", msgOutline.getMacAddress());
            sQLiteDatabase.insert(TABLE_NAME, null, contentValues);
        }
    }*/

    public ArrayList<String> queryMessageInfo(String str, SQLiteDatabase sQLiteDatabase) {
        Cursor cursorQuery = sQLiteDatabase.query(TABLE_NAME, new String[]{str}, null, null, null, null, null);
        int count = cursorQuery.getCount();
        ArrayList<String> arrayList = new ArrayList<>();
        Log.e(this.TAG, "Piggy Check rows counts : " + count);
        if (count != 0) {
            for (int i = 0; i < count; i++) {
                if (i == 0) {
                    cursorQuery.moveToFirst();
                } else {
                    cursorQuery.moveToNext();
                }
                arrayList.add(cursorQuery.getString(0));
            }
            cursorQuery.close();
        }
        return arrayList;
    }

    public Cursor queryMessage(SQLiteDatabase sQLiteDatabase, String str) {
        return sQLiteDatabase.rawQuery("select * from MessageContent where condition = ?", new String[]{str});
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) throws SQLException {
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS config");
        onCreate(sQLiteDatabase);
    }

    public void deleteAllTableContent(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.delete(TABLE_NAME, null, null);
    }

/*    public MsgOutline queryMessageByfolderAndHandle(SQLiteDatabase sQLiteDatabase, String str, String str2, String str3) {
        Cursor cursorRawQuery = sQLiteDatabase.rawQuery("select * from MessageContent where folder = ? and handle = ? and macAddress = ?", new String[]{str, str2, str3});
        MsgOutline msgOutline = new MsgOutline();
        cursorRawQuery.moveToFirst();
        msgOutline.setFolder(cursorRawQuery.getString(1));
        msgOutline.setHandle(cursorRawQuery.getString(2));
        msgOutline.setSubject(cursorRawQuery.getString(3));
        msgOutline.setMessage(cursorRawQuery.getString(4));
        msgOutline.setDatetime(cursorRawQuery.getString(5));
        msgOutline.setSender_name(cursorRawQuery.getString(6));
        msgOutline.setSender_addressing(cursorRawQuery.getString(7));
        msgOutline.setRecipient_addressing(cursorRawQuery.getString(8));
        msgOutline.setSize(cursorRawQuery.getString(9));
        msgOutline.setRead(cursorRawQuery.getString(10));
        cursorRawQuery.close();
        return msgOutline;
    }*/

    public Cursor queryMessageByMacAddress(SQLiteDatabase sQLiteDatabase, String str) {
        return sQLiteDatabase.rawQuery("select * from MessageContent where macAddress = ?", new String[]{str});
    }

    public Cursor queryOneMessageByMacAddress(SQLiteDatabase sQLiteDatabase, String str, String str2, String str3) {
        if (!str3.equals(NfDef.MAP_INBOX) && !str3.equals(NfDef.MAP_OUTBOX)) {
            Log.e(this.TAG, "folder parameter error !");
            return null;
        }
        return sQLiteDatabase.rawQuery("select * from MessageContent where macAddress = ? and handle = ? and folder = ?", new String[]{str, str2, str3});
    }

    @SuppressLint("Range")
    public boolean isMessageInDatabase(SQLiteDatabase sQLiteDatabase, String str, String str2, String str3) {
        Log.e(this.TAG, "Piggy Check isMessageInDatabase address : " + str + " handle : " + str2 + " folder : " + str3);
        if (!str3.equals(NfDef.MAP_INBOX) && !str3.equals(NfDef.MAP_OUTBOX)) {
            Log.e(this.TAG, "folder parameter error !");
            return false;
        }
        Cursor cursorRawQuery = sQLiteDatabase.rawQuery("select * from MessageContent where macAddress = ? and handle = ? and folder = ?", new String[]{str, str2, str3});
        Log.e(this.TAG, "Piggy Check isMessageInDatabase cursor count : " + cursorRawQuery.getCount());
        cursorRawQuery.moveToFirst();
        Log.e(this.TAG, "Piggy Check ID : " + cursorRawQuery.getString(cursorRawQuery.getColumnIndex("_id")) + " handle : " + cursorRawQuery.getString(cursorRawQuery.getColumnIndex("handle")) + " DateTime : " + cursorRawQuery.getString(cursorRawQuery.getColumnIndex("datetime")));
        if (cursorRawQuery.getCount() == 1) {
            return true;
        }
        if (cursorRawQuery.getCount() <= 1) {
            return false;
        }
        Log.e(this.TAG, "Piggy Check have same handle same folder in database. don't know why .");
        return true;
    }

    public void deleteAllMessage(SQLiteDatabase sQLiteDatabase, String str) throws SQLException {
        sQLiteDatabase.execSQL("delete from MessageContent", new String[]{str});
    }

    public void deleteMessageByMacAddress(SQLiteDatabase sQLiteDatabase, String str) throws SQLException {
        sQLiteDatabase.execSQL("delete from MessageContent where macAddress = ?", new String[]{str});
    }

    public void deleteMessageByMacAddressAndFolder(SQLiteDatabase sQLiteDatabase, String str, String str2) throws SQLException {
        sQLiteDatabase.execSQL("delete from MessageContent where macAddress = ? and folder = ?", new String[]{str, str2});
    }

    public void deleteOneMessageByMacAddress(SQLiteDatabase sQLiteDatabase, String str) throws SQLException {
        sQLiteDatabase.execSQL("delete from MessageContent where macAddress = ? and handle = ? and datetime=?", new String[]{str});
    }

    public void deleteMessageByFolderAndHandle(SQLiteDatabase sQLiteDatabase, String str, String str2, String str3) throws SQLException {
        sQLiteDatabase.execSQL("delete from MessageContent where folder = ? and handle = ? and macAddress = ?", new String[]{str, str2, str3});
    }
}

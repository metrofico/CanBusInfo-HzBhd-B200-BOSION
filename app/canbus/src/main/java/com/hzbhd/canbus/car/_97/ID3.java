package com.hzbhd.canbus.car._97;

/* compiled from: MsgMgr.java */
/* loaded from: classes2.dex */
class ID3 {
    private String charsetName;
    private int head;
    private int length;
    private String id3 = new String();
    private String record = new String();

    public ID3(int i, String str, int i2) {
        this.head = i;
        this.charsetName = str;
        this.length = i2;
    }

    public int getHead() {
        return this.head;
    }

    public String getId3() {
        return this.id3;
    }

    public void setId3(String str) {
        this.id3 = str;
    }

    public boolean isId3Change() {
        if (this.record.equals(this.id3)) {
            return false;
        }
        this.record = this.id3;
        return true;
    }

    public String getCharsetName() {
        return this.charsetName;
    }

    public int getLength() {
        return this.length;
    }
}

package com.hzbhd.canbus.util.amap;

import java.util.Objects;

/* loaded from: classes2.dex */
public class AMapEntity {
    private final int allDistance;
    private final int carDirection;
    private final int destinationDistance;
    private final int icon;
    private final int nextDistance;
    private final String nextWayName;
    private final String planTime;
    private final int surplusAllTime;
    private final String surplusAllTimeStr;
    private final int type;

    public AMapEntity(int i, int i2, int i3, int i4, int i5, int i6, int i7, String str, String str2, String str3) {
        this.type = i;
        this.allDistance = i2;
        this.nextDistance = i3;
        this.destinationDistance = i4;
        this.icon = i5;
        this.surplusAllTime = i6;
        this.carDirection = i7;
        this.surplusAllTimeStr = str;
        this.planTime = str2;
        this.nextWayName = str3;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        AMapEntity aMapEntity = (AMapEntity) obj;
        return this.type == aMapEntity.type && this.allDistance == aMapEntity.allDistance && this.nextDistance == aMapEntity.nextDistance && this.destinationDistance == aMapEntity.destinationDistance && this.icon == aMapEntity.icon && this.surplusAllTime == aMapEntity.surplusAllTime && this.carDirection == aMapEntity.carDirection && Objects.equals(this.surplusAllTimeStr, aMapEntity.surplusAllTimeStr) && Objects.equals(this.planTime, aMapEntity.planTime) && Objects.equals(this.nextWayName, aMapEntity.nextWayName);
    }

    public int hashCode() {
        return Objects.hash(Integer.valueOf(this.type), Integer.valueOf(this.allDistance), Integer.valueOf(this.nextDistance), Integer.valueOf(this.destinationDistance), Integer.valueOf(this.icon), Integer.valueOf(this.surplusAllTime), Integer.valueOf(this.carDirection), this.surplusAllTimeStr, this.planTime, this.nextWayName);
    }

    public String toString() {
        return "AMapEntity{type=" + this.type + ", allDistance=" + this.allDistance + ", nextDistance=" + this.nextDistance + ", destinationDistance=" + this.destinationDistance + ", icon=" + this.icon + ", surplusAllTime=" + this.surplusAllTime + ", carDirection=" + this.carDirection + ", surplusAllTimeStr='" + this.surplusAllTimeStr + "', planTime='" + this.planTime + "', nextWayName='" + this.nextWayName + "'}";
    }

    public int getType() {
        return this.type;
    }

    public int getAllDistance() {
        return this.allDistance;
    }

    public int getNextDistance() {
        return this.nextDistance;
    }

    public int getDestinationDistance() {
        return this.destinationDistance;
    }

    public int getIcon() {
        return this.icon;
    }

    public int getSurplusAllTime() {
        return this.surplusAllTime;
    }

    public int getCarDirection() {
        return this.carDirection;
    }

    public String getSurplusAllTimeStr() {
        return this.surplusAllTimeStr;
    }

    public String getPlanTime() {
        return this.planTime;
    }

    public String getNextWayName() {
        return this.nextWayName;
    }
}

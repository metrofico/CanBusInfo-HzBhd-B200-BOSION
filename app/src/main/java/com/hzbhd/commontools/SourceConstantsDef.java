package com.hzbhd.commontools;

import kotlinx.coroutines.scheduling.WorkQueueKt;

/* loaded from: classes2.dex */
public class SourceConstantsDef {
    public static final int NOT_DUCK = 128;

    public enum CAMERA_STATE {
        CLOSED,
        OPEN_REQUEST,
        OPENING,
        OPENED,
        CLOSING,
        DISCONNECTED,
        ERROR
    }

    public enum DISP_ID {
        disp_main,
        disp_ext1,
        disp_ext2
    }

    public enum NAVI_START_MODE {
        NO_START,
        LAST_FOREGROUND,
        LAST_BACKGROUND,
        ALLWAYS_START
    }

    public enum SOURCE_ACTION {
        PLAY,
        PAUSE,
        STOP
    }

    public static int getNaviStream(int i) {
        return i & (-129);
    }

    public static boolean isNotDuckNavi(int i) {
        return (i & 128) != 0;
    }

    public static int setNaviStream(int i, boolean z) {
        return z ? i | 128 : i;
    }

    public enum MODULE_ID {
        DAEMON(0),
        MIDWARE(1),
        SETTING(2),
        SHARE_DATA(3),
        KEY_DISPATCHER(4),
        SOURCE_MANAGER(5),
        HARDWARE(6),
        FRAMEWORK(7),
        MCU(8),
        AUDIO(9),
        CAMERA(10),
        MEDIA_SCANNER(11),
        MEDIA(12),
        BLUETOOTH(13),
        RADIO(14),
        NAVI(15),
        MISC(16),
        CANBUS(17),
        AVM(18),
        SYSTEMUI(19),
        CARPLAY(20),
        ANDROIDAUTO(21),
        SETTINGAPP(22),
        LOG(23),
        BTMUSIC(24),
        VIDEO(25),
        MUSIC(26),
        BTPHONE(27),
        SPEECH(28),
        INIT(29),
        REVERSE_DETECT(30),
        DAB(31),
        DISC(32),
        BHD_LYRA(34),
        BHD_MS_TXZ(35),
        LCD(36),
        DVR(37),
        GYROSCOPE(38),
        UPGRADE(39),
        SXM(40);

        private int mValue;

        MODULE_ID(int i) {
            this.mValue = i;
        }

        public int getValue() {
            return this.mValue;
        }

        public static MODULE_ID getType(int i) {
            for (MODULE_ID module_id : values()) {
                if (module_id.getValue() == i) {
                    return module_id;
                }
            }
            return null;
        }
    }

    public enum SOURCE_ID {
        NULL(0),
        SD(1),
        USB(2),
        USB1(3),
        USB2(4),
        USB3(5),
        USB4(6),
        USB5(7),
        MPEG(8),
        CDC(9),
        AUX(10),
        AUX1(11),
        AUX2(12),
        CARLINK(13),
        VOICE(14),
        NAVIAUDIO(15),
        APP(16),
        TTS(17),
        FM(18),
        TPMS(19),
        UITPMS(20),
        BTPHONE(21),
        BTAUDIO(22),
        BTUIPHONE(23),
        BTUIAUDIO(24),
        BTCONNECTION(25),
        IMODE(26),
        CMMB(27),
        G3PHONE(28),
        BACKCAMERA(29),
        FRONTCAMERA(30),
        DVR(31),
        RDVR(32),
        DVBT(33),
        ISDB(34),
        ATV(35),
        EQ(36),
        SETTINGS(37),
        UPGRADE(38),
        DAB(39),
        DABTA(40),
        SWC(41),
        REAR(42),
        MUSIC(43),
        VIDEO(44),
        PHOTO(45),
        HVAC(46),
        SXM(47),
        CLOSESOURCE(48),
        BLACKOUT(49),
        AUDIOPREVIEW(50),
        RDS(51),
        AVOFF(52),
        REARUSB(53),
        ANDROIDPLAYER(54),
        SLEEP(55),
        MPEGTEST(56),
        PTT(57),
        QRCODE(58),
        AC(59),
        CARPLAY(60),
        CARPLAYMUSIC(61),
        UICARPLAY(62),
        HDMI(63),
        CARPLAYPHONE(64),
        CARLIFE(65),
        ORIGAUX(66),
        ORIGUSB(67),
        ORIGPHONE(68),
        ORIGUIPHONE(69),
        FIBERIN(70),
        AVM(71),
        MPU(72),
        SECURITY(73),
        ECall(74),
        ICall(75),
        ECAR(76),
        ONLINE_MUSIC_UI(77),
        ONLINE_MUSIC(78),
        LINKUI(79),
        LINKNAVI(80),
        LINKTTS(81),
        LINKAUDIO(82),
        LINKBTUI(83),
        LINKBTPHONE(84),
        NAVISource(85),
        UIMUSIC(86),
        UIVIDEO(87),
        UPGRADEUI(88),
        CAN(89),
        MEDIA(90),
        AUTOTEST(91),
        BTSETTING(92),
        ONLINE_RADIO(93),
        ONLINE_VIDEO(94),
        RADIO(95),
        AM(96),
        CLOCK(97),
        LAUNCHER(98),
        THIRD(99),
        DAEMON(100),
        CONTROLCENTER(101),
        SHAREINFO(102),
        MEDIASCANNER(103),
        MCU(104),
        KEYDISPATCHER(105),
        BLUETOOTH(106),
        CAM(107),
        SOURCEMANAGER(108),
        SYSTEMCOMMMON(109),
        BCM(110),
        CarSettings(111),
        CarMonitor(112),
        ProjectAdapter(113),
        ANDROIDAUTONAVI(114),
        ANDROIDAUTOPHONE(115),
        ANDROIDAUTO(116),
        KEYBEEP(117),
        LINKRECORD(118),
        MUSICUI(119),
        VIDEOUI(120),
        RADIOUI(121),
        IPODUI(122),
        DTV(123),
        NORMAL_SOURCE(124),
        EC_ANDROID(125),
        EC_IPHONE(126),
        CANOFF(0x7F),
        DABTOFM(128),
        XIMALAYA(129),
        KUWO(130),
        CARPLAYRING(131),
        CARPLAYTTS(132);

        private int mValue;

        SOURCE_ID(int i) {
            this.mValue = i;
        }

        public static SOURCE_ID getType(int i) {
            for (SOURCE_ID source_id : values()) {
                if (source_id.getValue() == i) {
                    return source_id;
                }
            }
            return null;
        }

        public int getValue() {
            return this.mValue;
        }

        public static SOURCE_ID getType(String str) {
            for (SOURCE_ID source_id : values()) {
                if (source_id.name().equals(str)) {
                    return source_id;
                }
            }
            return null;
        }

        public static boolean isNaviSource(int i) {
            return i == NAVIAUDIO.getValue() || i == ANDROIDAUTONAVI.getValue();
        }
    }
}

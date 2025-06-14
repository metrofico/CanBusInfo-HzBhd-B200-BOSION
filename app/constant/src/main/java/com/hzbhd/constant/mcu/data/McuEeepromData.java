package com.hzbhd.constant.mcu.data;

import nfore.android.bt.res.NfDef;

public class McuEeepromData {

    public enum Key {
        EEPROM_ADD_CAR_TYPE((byte) 0, (byte) 0),
        EEPROM_ADD_ENCODER_STATE((byte) 0, (byte) 1),
        EEPROM_ADD_DIMMER_DAY((byte) 0, (byte) 2),
        EEPROM_ADD_LEVEL_ADDR0((byte) 0, (byte) 3),
        EEPROM_ADD_LEVEL_ADDR1((byte) 0, (byte) 4),
        EEPROM_ADD_LEVEL_ADDR2((byte) 0, (byte) 5),
        EEPROM_ADD_LEVEL_ADDR3((byte) 0, (byte) 6),
        EEPROM_ADD_LEVEL_ADDR4((byte) 0, (byte) 7),
        EEPROM_ADD_ACCOFF_DELAY((byte) 0, (byte) 8),
        EEPROM_ADD_FACTORY_SETTING0((byte) 0, (byte) 9),
        EEPROM_ADD_FACTORY_SETTING1((byte) 0, (byte) 10),
        EEPROM_ADD_FACTORY_SETTING2((byte) 0, (byte) 11),
        EEPROM_ADD_FACTORY_SETTING3((byte) 0, NfDef.AVRCP_EVENT_ID_UIDS_CHANGED),
        EEPROM_ADD_FACTORY_SETTING4((byte) 0, NfDef.AVRCP_EVENT_ID_VOLUME_CHANGED),
        EEPROM_ADD_FACTORY_SETTING5((byte) 0, (byte) 14),
        EEPROM_ADD_FACTORY_SETTING6((byte) 0, (byte) 15),
        EEPROM_ADD_FACTORY_SETTING7((byte) 0, (byte) 16),
        EEPROM_ADD_FACTORY_SETTING8((byte) 0, (byte) 17),
        EEPROM_ADD_FACTORY_SETTING9((byte) 0, (byte) 18),
        EEPROM_ADD_COLORLED_RED((byte) 0, (byte) 19),
        EEPROM_ADD_COLORLED_GREEN((byte) 0, (byte) 20),
        EEPROM_ADD_COLORLED_BLUE((byte) 0, (byte) 21),
        EEPROM_ADD_BT_AUTHORIZE((byte) 0, (byte) 22),
        EEPROM_ADD_TFT_VCOM((byte) 0, (byte) 23),
        EEPROM_ADD_CAN_BAUD_RATE((byte) 0, (byte) 24),
        EEPROM_ADD_LED_STATE((byte) 0, (byte) 25),
        EEPROM_ADD_CAN_MOUDLE((byte) 0, (byte) 26),
        EEPROM_ADD_CAN_TRANSMITE_TYPE((byte) 0, (byte) 27),
        EEPROM_ADD_DIMMER_NIGHT((byte) 0, (byte) 28),
        EEPROM_ADD_GENERAL_SETTING1((byte) 0, (byte) 29),
        EEPROM_ADD_GENERAL_SETTING1v1((byte) 0, (byte) 30),
        EEPROM_ADD_VIDEO_SETTING((byte) 0, (byte) 31),
        EEPROM_ADD_LOGO_SWITCH_ON_TAG((byte) 0, (byte) 32),
        EEPROM_ADD_LOGO_SWITCH_ON_TOTAL((byte) 0, (byte) 33),
        EEPROM_ADD_LOGO_SWITCH_ON_POWER((byte) 0, (byte) 34),
        EEPROM_ADD_DEBUG_ERROR((byte) 0, (byte) 35),
        EEPROM_ADDR_ACC_OFF_HOLD((byte) 0, (byte) 36),
        EEPROM_ADD_DIMMER_MODE((byte) 0, (byte) 37),
        EEPROM_ADDR_ACC_OFF_DELAY_DETECT((byte) 0, (byte) 38);

        private byte arg1;
        private byte arg2;

        Key(byte b, byte b2) {
            this.arg1 = b;
            this.arg2 = b2;
        }

        public byte getArg1() {
            return this.arg1;
        }

        public byte getArg2() {
            return this.arg2;
        }
    }
}

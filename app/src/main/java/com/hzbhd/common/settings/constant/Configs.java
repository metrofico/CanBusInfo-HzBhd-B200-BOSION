package com.hzbhd.common.settings.constant;

import com.hzbhd.common.settings.constant.data.audio.AudioBean;
import com.hzbhd.common.settings.constant.data.audio.AudioDeviceBean;
import com.hzbhd.common.settings.constant.data.audio.AudioGainBean;
import com.hzbhd.common.settings.constant.data.audio.AudioInputGainBean;
import com.hzbhd.common.settings.constant.data.audio.AudioMuteBean;
import com.hzbhd.common.settings.constant.data.audio.AudioNaviBean;
import com.hzbhd.common.settings.constant.data.audio.AudioReverseAudioBean;
import com.hzbhd.common.settings.constant.data.audio.AudioSoundFieldBean;
import com.hzbhd.common.settings.constant.data.audio.AudioSourceVolumeBean;
import com.hzbhd.common.settings.constant.data.audio.AudioSubwooferBean;
import com.hzbhd.common.settings.constant.data.avm.AvmBean;
import com.hzbhd.common.settings.constant.data.bt.BtSettingsBean;
import com.hzbhd.common.settings.constant.data.camera.CameraAc8257Bean;
import com.hzbhd.common.settings.constant.data.camera.CameraSourceBean;
import com.hzbhd.common.settings.constant.data.canbus.CanSettingsBean;
import com.hzbhd.common.settings.constant.data.dab.DabBean;
import com.hzbhd.common.settings.constant.data.disc.DiscSettingsBean;
import com.hzbhd.common.settings.constant.data.eq.EqBaseSettingsBean;
import com.hzbhd.common.settings.constant.data.hardware.HardWareRadioBean;
import com.hzbhd.common.settings.constant.data.hardware.HardwareReverseDetectBean;
import com.hzbhd.common.settings.constant.data.mcu.McuBrightnessBean;
import com.hzbhd.common.settings.constant.data.mcu.McuColorLedBean;
import com.hzbhd.common.settings.constant.data.mcu.McuFSettingBean;
import com.hzbhd.common.settings.constant.data.mcu.McuGeneralBean;
import com.hzbhd.common.settings.constant.data.mcu.McuPowerBean;
import com.hzbhd.common.settings.constant.data.mcu.McuQuickChargeBean;
import com.hzbhd.common.settings.constant.data.mcu.McuSwcLearnLevelBean;
import com.hzbhd.common.settings.constant.data.mcu.McuVcomBean;
import com.hzbhd.common.settings.constant.data.mcu.McuVideoBean;
import com.hzbhd.common.settings.constant.data.media.MediaBean;
import com.hzbhd.common.settings.constant.data.mediascanner.MediaScannerBean;
import com.hzbhd.common.settings.constant.data.misc.MiscReverseBean;
import com.hzbhd.common.settings.constant.data.setting.SettingCameraBean;
import com.hzbhd.common.settings.constant.data.setting.SettingColorLED;
import com.hzbhd.common.settings.constant.data.setting.SettingFAppBean;
import com.hzbhd.common.settings.constant.data.setting.SettingFModuleBean;
import com.hzbhd.common.settings.constant.data.setting.SettingFactoryBean;
import com.hzbhd.common.settings.constant.data.setting.SettingInfoBean;
import com.hzbhd.common.settings.constant.data.setting.SettingInfoDeviceBean;
import com.hzbhd.common.settings.constant.data.setting.SettingInitBean;
import com.hzbhd.common.settings.constant.data.setting.SettingItemHideBean;
import com.hzbhd.common.settings.constant.data.setting.SettingMainBean;
import com.hzbhd.common.settings.constant.data.source.SourceHideAppsBean;
import com.hzbhd.common.settings.constant.data.source.SourceNaviBean;
import com.hzbhd.common.settings.constant.data.system.SystemStatusBean;
import com.hzbhd.common.settings.constant.data.systemui.SystemUiBean;
import com.hzbhd.common.settings.constant.data.video.VideoAvmBean;
import com.hzbhd.common.settings.constant.data.video.VideoCamerasBean;
import com.hzbhd.common.settings.constant.data.video.VideoHardwareBean;
import com.hzbhd.common.settings.constant.data.video.VideoReverseBean;

/* loaded from: classes2.dex */
public class Configs {

    public enum Module {
        Setting_main("/bhd/appconfig/Settings/", "setting_main.json", SettingMainBean.class),
        Setting_item_hide("/bhd/appconfig/Settings/", "setting_hide_item.json", SettingItemHideBean.class),
        Setting_f_module("/bhd/appconfig/Settings/", "setting_f_module.json", SettingFModuleBean.class),
        Setting_f_app("/bhd/appconfig/Settings/", "setting_f_app.json", SettingFAppBean.class),
        Setting_customized("/bhd/appconfig/Settings/", "setting_customized.json", null),
        Setting_info("/bhd/appconfig/Settings/", "setting_info.json", SettingInfoBean.class),
        Setting_info_device("/bhd/appconfig/Settings/", "setting_info_device.json", SettingInfoDeviceBean.class),
        Setting_colorled("/bhd/appconfig/Settings/", "setting_colorled.json", SettingColorLED.class),
        Setting_init("/bhd/appconfig/Settings/", "setting_init.json", SettingInitBean.class),
        Setting_factory("/bhd/appconfig/Settings/", "setting_factory.json", SettingFactoryBean.class),
        Setting_camera("/bhd/appconfig/Settings/", "setting_camera.json5", SettingCameraBean.class),
        Video_videohardware("/bhd/appconfig/Video/", "video_hardware.json", VideoHardwareBean.class),
        Video_cameras("/bhd/appconfig/Video/", "Cameras.json", VideoCamerasBean.class),
        Video_reverse("/bhd/appconfig/Video/", "Reverse.json", VideoReverseBean.class),
        Video_avm("/bhd/appconfig/Video/", "AVM.json", VideoAvmBean.class),
        Mcu_color_led("/bhd/appconfig/McuService/", "ColorLed.json", McuColorLedBean.class),
        Mcu_swc_learn_level("/bhd/appconfig/McuService/", "SwcLearning.json", McuSwcLearnLevelBean.class),
        Mcu_video("/bhd/appconfig/McuService/", "Video.json", McuVideoBean.class),
        Mcu_brightness("/bhd/appconfig/McuService/", "Brightness.json", McuBrightnessBean.class),
        Mcu_power("/bhd/appconfig/McuService/", "Power.json", McuPowerBean.class),
        Mcu_vcom("/bhd/appconfig/McuService/", "Vcom.json", McuVcomBean.class),
        Mcu_f_setting("/bhd/appconfig/McuService/", "FactorySettings.json", McuFSettingBean.class),
        Mcu_quick_charge("/bhd/appconfig/McuService/", "QuickCharge.json", McuQuickChargeBean.class),
        Mcu_general("/bhd/appconfig/McuService/", "General.json", McuGeneralBean.class),
        System_status("/bhd/appconfig/System/", "SystemStatus.json", SystemStatusBean.class),
        Audio_audio("/bhd/appconfig/AudioService/", "Audio.json", AudioBean.class),
        Audio_gain("/bhd/appconfig/AudioService/", "AudioGain.json", AudioGainBean.class),
        Audio_input_gain_bu32107("/bhd/appconfig/AudioService/", "Bu32107_InputGain.json", AudioInputGainBean.class),
        Audio_input_gain_tda7719("/bhd/appconfig/AudioService/", "Tda7719_InputGain.json", AudioInputGainBean.class),
        Audio_input_gain_bu37534("/bhd/appconfig/AudioService/", "Bu37534_InputGain.json", AudioInputGainBean.class),
        Audio_device("/bhd/appconfig/AudioService/", "AudioDevice.json", AudioDeviceBean.class),
        Audio_navi_audio("/bhd/appconfig/AudioService/", "NaviAudio.json", AudioNaviBean.class),
        Audio_mute("/bhd/appconfig/AudioService/", "Mute.json", AudioMuteBean.class),
        Audio_source_volume("/bhd/appconfig/AudioService/", "SourceVolume.json", AudioSourceVolumeBean.class),
        Audio_reverse_audio("/bhd/appconfig/AudioService/", "ReverseAudio.json", AudioReverseAudioBean.class),
        Audio_sound_field("/bhd/appconfig/AudioService/", "SoundField.json", AudioSoundFieldBean.class),
        Audio_bu32107_subwoofer("/bhd/appconfig/AudioService/", "Bu32107_Subwoofer.json", AudioSubwooferBean.class),
        Audio_tda7719_subwoofer("/bhd/appconfig/AudioService/", "Tda7719_Subwoofer.json", AudioSubwooferBean.class),
        Audio_bu37534_subwoofer("/bhd/appconfig/AudioService/", "Bu37534_Subwoofer.json", AudioSubwooferBean.class),
        Hardware_radio("/bhd/appconfig/HardwareService/", "Radio.json", HardWareRadioBean.class),
        Hardware_reversedetect("/bhd/appconfig/HardwareService/", "ReverseDetect.json", HardwareReverseDetectBean.class),
        Can_settings("/bhd/appconfig/CanBus/", "can_settings.json", CanSettingsBean.class),
        Disc_settings("/bhd/appconfig/Disc/", "disc_setting.json", DiscSettingsBean.class),
        Camera_Ac8257("/bhd/appconfig/CameraService/", "CameraAc8257.json", CameraAc8257Bean.class),
        Camera_Source("/bhd/appconfig/CameraService/", "SourceCamera.json", CameraSourceBean.class),
        AVM_avm("/bhd/appconfig/AVM/", "avm.json", AvmBean.class),
        SystemUI_config("/bhd/appconfig/SystemUI/", "systemui.json", SystemUiBean.class),
        Source_navi("/bhd/appconfig/SourceService/", "NaviSource.json", SourceNaviBean.class),
        Source_hideapps("/bhd/appconfig/SourceService/", "HideApps.json", SourceHideAppsBean.class),
        Misc_Reverse("/bhd/appconfig/Misc/", "Reverse.json", MiscReverseBean.class),
        MediaScanner_config("/bhd/appconfig/MediaScanner/", "mediascanner.json", MediaScannerBean.class),
        Bt_settings("/bhd/appconfig/Bt/", "btSettings.json", BtSettingsBean.class),
        Eq_basesettings("/bhd/appconfig/EqApp/", "BaseSettings.json", EqBaseSettingsBean.class),
        Media_media("/bhd/appconfig/Media/", "media.json", MediaBean.class),
        Dab_dab("/bhd/appconfig/DAB/", "Dab.json", DabBean.class);

        private Class clzz;
        private String dirPath;
        private String fileName;

        Module(String str, String str2, Class cls) {
            this.dirPath = str;
            this.fileName = str2;
            this.clzz = cls;
        }

        public String getDirPath() {
            return this.dirPath;
        }

        public String getFileName() {
            return this.fileName;
        }

        public Class getClazz() {
            return this.clzz;
        }
    }
}

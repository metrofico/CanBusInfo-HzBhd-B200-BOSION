push:
	adb root
	adb remount
	adb push "$(subst \,/,$(CANBUS_APK_PATH))" /system/app/CanBusInfo/CanBusInfo.apk
	adb shell chmod 644 /system/app/CanBusInfo/CanBusInfo.apk
	adb shell chown root:root /system/app/CanBusInfo/CanBusInfo.apk
	adb shell sync
	adb shell reboot

# Define la ruta como una variable para mayor legibilidad
CANBUS_APK_PATH := C:\Users\joy.lino\StudioProjects\CanBusInfo-HzBhd-B200-BOSION\app\release\CanBusInfo.apk

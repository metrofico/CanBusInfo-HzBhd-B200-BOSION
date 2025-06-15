package com.hzbhd.config.use;


import java.util.Arrays;


public final class CanBusSpecialConfig {
    public static final CanBusSpecialConfig INSTANCE = new CanBusSpecialConfig();
    private static final int[] cameraHighConfigurationID = {451, 452};

    private CanBusSpecialConfig() {
    }

    public final boolean cameraSupportHighConfigurationID(int can_type) {
        return Arrays.stream(cameraHighConfigurationID)
                .anyMatch(id -> id == can_type);
    }
}

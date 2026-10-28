package com.prayerscout;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("prayerscout")
public interface PrayerScoutConfig extends Config
{
    @ConfigItem(keyName = "showUnknown", name = "Show unknown monsters", description = "Show a warning when no verified recommendation exists")
    default boolean showUnknown() { return true; }
}

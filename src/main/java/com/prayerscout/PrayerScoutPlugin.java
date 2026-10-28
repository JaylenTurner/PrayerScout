package com.prayerscout;

import com.google.inject.Provides;
import javax.inject.Inject;
import net.runelite.api.Client;
import net.runelite.api.NPC;
import net.runelite.api.events.GameTick;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;

@PluginDescriptor(name = "Prayer Scout", description = "Shows a cautious protection prayer recommendation for your current NPC target", tags = {"prayer", "combat", "npc", "pvm"})
public class PrayerScoutPlugin extends Plugin
{
    @Inject private Client client;
    @Inject private OverlayManager overlayManager;
    @Inject private PrayerScoutOverlay overlay;
    @Inject private PrayerScoutConfig config;

    private String targetName;

    @Provides
    PrayerScoutConfig provideConfig(ConfigManager manager)
    {
        return manager.getConfig(PrayerScoutConfig.class);
    }

    @Override
    protected void startUp()
    {
        overlayManager.add(overlay);
    }

    @Override
    protected void shutDown()
    {
        overlayManager.remove(overlay);
        targetName = null;
    }

    @Subscribe
    public void onGameTick(GameTick event)
    {
        if (client.getLocalPlayer() == null || !(client.getLocalPlayer().getInteracting() instanceof NPC))
        {
            targetName = null;
            return;
        }
        NPC npc = (NPC) client.getLocalPlayer().getInteracting();
        targetName = npc.getName();
    }

    String getTargetName() { return targetName; }
    boolean showUnknown() { return config.showUnknown(); }
}

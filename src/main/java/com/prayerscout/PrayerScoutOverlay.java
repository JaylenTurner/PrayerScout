package com.prayerscout;

import java.awt.Dimension;
import java.awt.Graphics2D;
import javax.inject.Inject;
import net.runelite.client.ui.overlay.OverlayPanel;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.components.LineComponent;
import net.runelite.client.ui.overlay.components.TitleComponent;

public class PrayerScoutOverlay extends OverlayPanel
{
    private final PrayerScoutPlugin plugin;

    @Inject
    public PrayerScoutOverlay(PrayerScoutPlugin plugin)
    {
        this.plugin = plugin;
        setPosition(OverlayPosition.TOP_LEFT);
    }

    @Override
    public Dimension render(Graphics2D graphics)
    {
        String name = plugin.getTargetName();
        if (name == null || name.isEmpty()) return null;
        PrayerScoutRecommendations.Result result = PrayerScoutRecommendations.lookup(name);
        if (!plugin.showUnknown() && !result.verified) return null;
        panelComponent.getChildren().clear();
        panelComponent.getChildren().add(TitleComponent.builder().text("Monster Guide").build());
        panelComponent.getChildren().add(LineComponent.builder().left("Monster").right(name).build());
        panelComponent.getChildren().add(LineComponent.builder().left("Attack styles").right(result.recommendation).build());
        panelComponent.getChildren().add(LineComponent.builder().left("Info").right(result.note).build());
        return super.render(graphics);
    }
}

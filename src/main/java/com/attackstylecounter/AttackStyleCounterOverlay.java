package com.attackstylecounter;

import javax.inject.Inject;
import java.awt.*;
import lombok.Getter;
import lombok.Setter;
import net.runelite.api.Client;
import net.runelite.api.Perspective;
import net.runelite.api.Point;
import net.runelite.api.coords.LocalPoint;
import net.runelite.client.ui.FontManager;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayUtil;
import net.runelite.client.ui.overlay.OverlayPosition;

public class AttackStyleCounterOverlay extends Overlay {
    private final AttackStyleCounterConfig config;
    private final AttackStyleCounterPlugin plugin;
    private final Client client;
    private final Font font = FontManager.getRunescapeFont();

    @Getter @Setter
    private int count = 0;

    @Inject
    public AttackStyleCounterOverlay(AttackStyleCounterPlugin plugin, AttackStyleCounterConfig config, Client client) {
        super(plugin);

        this.plugin = plugin;
        this.config = config;
        this.client = client;

        setPosition(OverlayPosition.DYNAMIC);
    }

    @Override
    public Dimension render(Graphics2D graphics) {
        graphics.setFont(font);

        FontMetrics fontMetrics = graphics.getFontMetrics();
        final String countAsString = String.valueOf(count);
        final int textWidth = fontMetrics.stringWidth(countAsString);
        Point playerPoint = getPlayerPoint(client, textWidth);

        OverlayUtil.renderTextLocation(graphics, playerPoint, countAsString, Color.WHITE);

        return null;
    }

    private Point getPlayerPoint(Client client, int textWidth) {
        final int height = client.getLocalPlayer().getLogicalHeight() + 20;
        final LocalPoint localLocation = client.getLocalPlayer().getLocalLocation().dx(textWidth);
        final int playerPlane = client.getTopLevelWorldView().getPlane();

        return Perspective.localToCanvas(client, localLocation, playerPlane, height);
    }
}

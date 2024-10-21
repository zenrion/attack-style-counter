package com.attackstylecounter;

import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.events.GameTick;
import net.runelite.client.callback.ClientThread;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;

@Slf4j
@PluginDescriptor(
	name = "Attack Style Counter"
)
public class AttackStyleCounterPlugin extends Plugin
{
	@Inject
	private Client client;

	@Inject
	private ClientThread clientThread;

	@Inject
	private AttackStyleCounterConfig config;

	@Inject
	private OverlayManager overlayManager;

	@Inject
	private AttackStyleCounterOverlay attackStyleCounterOverlay;

	@Override
	protected void startUp() throws Exception
	{
		start();
	}

	@Override
	protected void shutDown() throws Exception
	{
		overlayManager.remove(attackStyleCounterOverlay);
	}

	@Provides
	AttackStyleCounterConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(AttackStyleCounterConfig.class);
	}

	@Subscribe
	public void onGameTick(GameTick tick) {
		attackStyleCounterOverlay.setCount(attackStyleCounterOverlay.getCount() + 1);
	}

	private void start() {
		clientThread.invoke(() -> {
			overlayManager.add(attackStyleCounterOverlay);
		});
	}
}

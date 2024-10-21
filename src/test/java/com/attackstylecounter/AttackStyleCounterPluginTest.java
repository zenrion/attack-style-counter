package com.attackstylecounter;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class AttackStyleCounterPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(AttackStyleCounterPlugin.class);
		RuneLite.main(args);
	}
}
package com.example.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Pose;

public class FirstPersonBody {
	public static boolean isActive(Entity entity) {
		Minecraft mc = Minecraft.getInstance();
		LocalPlayer player = mc.player;
		if (player == null || entity != player) {
			return false;
		}
		if (!mc.options.getCameraType().isFirstPerson()) {
			return false;
		}
		if (player.isSpectator()) {
			return false;
		}
		Pose pose = player.getPose();
		return pose == Pose.STANDING || pose == Pose.CROUCHING;
	}
}

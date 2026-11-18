package com.example.mixin.client;

import com.example.client.FirstPersonBody;
import net.minecraft.client.Camera;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Camera.class)
public class CameraMixin {
	@Shadow
	private Vec3 position;
	@Shadow
	private float xRot;
	@Shadow
	private float yRot;

	@Inject(method = "setup", at = @At("RETURN"))
	private void realbody$shift(BlockGetter level, Entity entity, boolean detached,
			boolean thirdPersonReverse, float partialTick, CallbackInfo ci) {
		if (detached || !FirstPersonBody.isActive(entity)) {
			return;
		}
		float pitch = Math.max(0.0F, this.xRot);
		double offset = 0.10 + 0.20 * (pitch / 90.0);
		double yaw = Math.toRadians(this.yRot);
		this.position = this.position.add(-Math.sin(yaw) * offset, 0.0, Math.cos(yaw) * offset);
	}
}

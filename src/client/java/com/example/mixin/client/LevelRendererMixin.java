package com.example.mixin.client;

import com.example.client.FirstPersonBody;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.client.Camera;
import net.minecraft.client.renderer.LevelRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(LevelRenderer.class)
public class LevelRendererMixin {
	@WrapOperation(
			method = "renderLevel",
			at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Camera;isDetached()Z")
	)
	private boolean realbody$renderSelf(Camera camera, Operation<Boolean> original) {
		return original.call(camera) || FirstPersonBody.isActive(camera.getEntity());
	}
  }

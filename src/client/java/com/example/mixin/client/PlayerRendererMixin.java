package com.example.mixin.client;

import com.example.client.FirstPersonBody;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerRenderer.class)
public class PlayerRendererMixin {
	@Inject(method = "setModelProperties", at = @At("RETURN"))
	private void realbody$hideParts(AbstractClientPlayer player, CallbackInfo ci) {
		if (FirstPersonBody.isActive(player)) {
			PlayerModel<AbstractClientPlayer> model = ((PlayerRenderer) (Object) this).getModel();
			model.head.visible = false;
			model.hat.visible = false;
			model.leftArm.visible = false;
			model.rightArm.visible = false;
			model.leftSleeve.visible = false;
			model.rightSleeve.visible = false;
		}
	}
}

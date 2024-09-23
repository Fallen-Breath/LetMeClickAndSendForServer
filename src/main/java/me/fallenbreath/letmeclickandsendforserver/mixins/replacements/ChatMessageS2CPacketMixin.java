/*
 * This file is part of the Let Me Click And Send for Server project, licensed under the
 * GNU Lesser General Public License v3.0
 *
 * Copyright (C) 2024  Fallen_Breath and contributors
 *
 * Let Me Click And Send for Server is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Let Me Click And Send for Server is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Let Me Click And Send for Server.  If not, see <https://www.gnu.org/licenses/>.
 */

package me.fallenbreath.letmeclickandsendforserver.mixins.replacements;

import me.fallenbreath.letmeclickandsendforserver.TextClickEventReplacer;
import net.minecraft.network.packet.s2c.play.ChatMessageS2CPacket;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(ChatMessageS2CPacket.class)
public abstract class ChatMessageS2CPacketMixin
{
	@ModifyVariable(
			//#if MC >= 11900
			//$$ method = "<init>(Lnet/minecraft/text/Text;Z)V",
			//#elseif MC >= 11600
			//$$ method = "<init>(Lnet/minecraft/text/Text;Lnet/minecraft/network/MessageType;Ljava/util/UUID;)V",
			//#else
			method = "<init>(Lnet/minecraft/text/Text;Lnet/minecraft/network/MessageType;)V",
			//#endif
			at = @At("HEAD"),
			argsOnly = true
	)
	private static Text replaceClickEventInText(Text text)
	{
		TextClickEventReplacer.replaceInPlace(text);
		return text;
	}
}

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

package me.fallenbreath.letmeclickandsendforserver;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;

import static com.mojang.brigadier.arguments.StringArgumentType.getString;
import static com.mojang.brigadier.arguments.StringArgumentType.greedyString;
import static net.minecraft.server.command.CommandManager.argument;
import static net.minecraft.server.command.CommandManager.literal;

//#if MC >= 11600
//$$ import net.minecraft.network.MessageType;
//#endif

public class LmcasCommand
{
	public static void register(CommandDispatcher<ServerCommandSource> dispatcher)
	{
		dispatcher.register(literal("lmcas")
				.then(argument("message", greedyString())
						.executes(c -> mimicMessage(
								c.getSource().getMinecraftServer(),
								c.getSource().getPlayer(),
								getString(c, "message")
						))
				)
		);
	}

	/**
	 * Reference:
	 * - (mc1.15) {@link net.minecraft.server.network.ServerPlayNetworkHandler#onChatMessage}
	 * - (mc1.16) {@link net.minecraft.server.network.ServerPlayNetworkHandler#method_31286}
	 * - (mc1.17) {@link net.minecraft.server.network.ServerPlayNetworkHandler#handleMessage}
	 * - (mc1.19) {@link net.minecraft.server.network.ServerPlayNetworkHandler#handleDecoratedMessage}
	 * - {@link net.minecraft.server.PlayerManager#broadcastChatMessage}
	 */
	private static int mimicMessage(MinecraftServer server, ServerPlayerEntity player, String message)
	{
		Text text =
				//#if MC >= 11900
				//$$ Text.translatable
				//#else
				new TranslatableText
				//#endif
				("chat.type.text", player.getDisplayName(), message);

		server.getPlayerManager().broadcastChatMessage(
				//#if 11600 <= MC && MC < 11900
				//$$ text, MessageType.CHAT, player.getUuid()
				//#else
				text, false
				//#endif
		);
		return 1;
	}
}

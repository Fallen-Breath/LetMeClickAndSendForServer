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
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;

import static com.mojang.brigadier.arguments.StringArgumentType.getString;
import static com.mojang.brigadier.arguments.StringArgumentType.greedyString;
import static net.minecraft.commands.Commands.argument;
import static net.minecraft.commands.Commands.literal;

//#if MC >= 11600
//$$ import net.minecraft.network.chat.ChatType;
//#endif

public class LmcasCommand
{
	public static void register(CommandDispatcher<CommandSourceStack> dispatcher)
	{
		dispatcher.register(literal("lmcas")
				.then(argument("message", greedyString())
						.executes(c -> mimicMessage(
								c.getSource().getServer(),
								c.getSource().getPlayerOrException(),
								getString(c, "message")
						))
				)
		);
	}

	/**
	 * Reference:
	 *   MC < 1.19
	 *     {@link net.minecraft.server.network.ServerGamePacketListenerImpl#handleChat}
	 *     {@link net.minecraft.server.players.PlayerList#broadcastMessage}
	 *   MC >= 1.19
	 *     {@link net.minecraft.server.network.ServerGamePacketListenerImpl#broadcastChatMessage}
	 *     {@link net.minecraft.server.players.PlayerList#broadcastSystemMessage}
	 */
	private static int mimicMessage(MinecraftServer server, ServerPlayer player, String message)
	{
		Component text =
				//#if MC >= 11900
				//$$ Component.translatable
				//#else
				new TranslatableComponent
				//#endif
				("chat.type.text", player.getDisplayName(), message);

		//#if MC >= 11900
		//$$ server.getPlayerList().broadcastSystemMessage(text, false);
		//#else
		server.getPlayerList().broadcastMessage(
				//#if MC >= 11600
				//$$ text, ChatType.CHAT, player.getUUID()
				//#else
				text, false
				//#endif
		);
		//#endif
		return 1;
	}
}

/*
 * This file is part of the Let Me Click And Send for Server project, licensed under the
 * GNU Lesser General Public License v3.0
 *
 * Copyright (C) 2026  Fallen_Breath and contributors
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

package me.fallenbreath.letmeclickandsendforserver.mixins.accessors;

import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;

//#if MC >= 26.3
//$$ import org.slf4j.Logger;
//$$ import org.spongepowered.asm.mixin.gen.Accessor;
//#endif

@Mixin(MinecraftServer.class)
public interface MinecraftServerAccessor
{
	//#if MC >= 26.3
	//$$ @Accessor("LOGGER")
	//$$ static Logger getLogger()
	//$$ {
	//$$ 	throw new AssertionError();
	//$$ }
	//#endif
}

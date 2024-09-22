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

//#if MC >= 11802
//$$ import com.mojang.logging.LogUtils;
//$$ import org.slf4j.Logger;
//#else
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
//#endif

//#if FABRIC
import net.fabricmc.api.ModInitializer;
//#elseif FORGE
//$$ import net.minecraftforge.fml.common.Mod;
//#elseif NEOFORGE
//$$ import net.neoforged.fml.common.Mod;
//#endif

//#if FORGE_LIKE
//$$ @Mod("letmeclickandsendforserver")
//#endif
public class LetMeClickAndSendForServerMod
		//#if FABRIC
		implements ModInitializer
		//#endif
{
	public static final Logger LOGGER =
			//#if MC >= 11802
			//$$ LogUtils.getLogger();
			//#else
			LogManager.getLogger();
			//#endif

	//#if FABRIC
	@Override public void onInitialize()
	//#elseif FORGE_LIKE
	//$$ public LetMeClickAndSendForServerMod()
	//#endif
	{
		LOGGER.info("Let me click and send for server!");
	}
}

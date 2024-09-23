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

import com.google.common.collect.Queues;
import net.minecraft.text.ClickEvent;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.Queue;

//#if MC >= 11600
//$$ import net.minecraft.text.MutableText;
//#endif

public class TextClickEventReplacer
{
	private static final int MAX_QUEUE_ITERATIONS = 100000;

	public static void replaceInPlace(Text root)
	{
		Queue<Text> queue = Queues.newArrayDeque();
		queue.add(root);

		int cnt = 0;
		while (!queue.isEmpty())
		{
			if (++cnt >= MAX_QUEUE_ITERATIONS || queue.size() >= MAX_QUEUE_ITERATIONS)
			{
				LetMeClickAndSendForServerMod.LOGGER.warn("Max queue iteration {} exceeded, queue size {}", MAX_QUEUE_ITERATIONS, queue.size());
				break;
			}

			Text text = queue.poll();

			Style style = text.getStyle();

			//#if MC >= 11600
			//$$ if (text instanceof MutableText)
			//$$ {
			//$$ 	MutableText mutableText = (MutableText)text;
			//$$ 	replaceClickEvent(style.getClickEvent()).ifPresent(
			//$$ 			e -> mutableText.setStyle(style.withClickEvent(e))
			//$$ 	);
			//$$ }
			//#else
			replaceClickEvent(style.getClickEvent()).ifPresent(style::setClickEvent);
			//#endif

			queue.addAll(text.getSiblings());
		}
	}

	private static Optional<ClickEvent> replaceClickEvent(@Nullable ClickEvent event)
	{
		if (
				event != null
				&& event.getAction() == ClickEvent.Action.RUN_COMMAND
				&& !event.getValue().isEmpty()
				&& !event.getValue().startsWith("/")
		)
		{
			String newValue = "/lmcas " + event.getValue();
			return Optional.of(new ClickEvent(event.getAction(), newValue));
		}
		return Optional.empty();
	}
}

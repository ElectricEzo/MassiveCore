package com.massivecraft.massivecore.cmd.type;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.bukkit.command.CommandSender;

import com.massivecraft.massivecore.mixin.Mixin;

public class TypeWorldId extends TypeAbstractSelect<String> implements TypeAllAble<String>
{
	// -------------------------------------------- //
	// INSTANCE & CONSTRUCT
	// -------------------------------------------- //
	
	private static TypeWorldId i = new TypeWorldId();
	public static TypeWorldId get() { return i; }
	
	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //
	
	@Override
	public String getTypeName()
	{
		return "world";
	}

	@Override
	public String select(String arg, CommandSender sender)
	{
		List<String> visibleWorldIds = Mixin.getVisibleWorldIds(sender);
		
		for (String worldId : visibleWorldIds)
		{
			// This was already done above in Mixin.getVisibleWorldIds(sender);
			// if ( ! Mixin.canSeeWorld(sender, worldId)) continue;
			if (arg.equalsIgnoreCase(worldId)) return worldId;
		}
		
		for (String worldId : visibleWorldIds)
		{
			// This was already done above in Mixin.getVisibleWorldIds(sender);
			// if ( ! Mixin.canSeeWorld(sender, worldId)) continue;
			for (String worldAlias : Mixin.getWorldAliases(worldId))
			{
				if (arg.equalsIgnoreCase(worldAlias)) return worldId;
			}
		}
		
		return null;
	}

	@Override
	public List<String> altNames(CommandSender sender)
	{
		List<String> ret = new ArrayList<String>();
		for (String worldId : Mixin.getWorldIds())
		{
			if ( ! Mixin.canSeeWorld(sender, worldId)) continue;
			ret.add(Mixin.getWorldDisplayName(worldId));
		}
		return ret;
	}

	@Override
	public Collection<String> getTabList(CommandSender sender, String arg)
	{
		return Mixin.getVisibleWorldIds(sender);
	}

	@Override
	public Collection<String> getAll(CommandSender sender)
	{
		return Mixin.getVisibleWorldIds(sender);
	}
	
}
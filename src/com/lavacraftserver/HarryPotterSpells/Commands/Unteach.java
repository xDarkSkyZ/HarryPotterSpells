package com.lavacraftserver.HarryPotterSpells.Commands;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.lavacraftserver.HarryPotterSpells.HPS;
import com.lavacraftserver.HarryPotterSpells.Spells.Spell;

@HCommand(name="unteach", description="Makes a player forget a spell", usage="/unteach <spell> [player | me]")
public class Unteach implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (args.length > 2 || args.length == 0) {
			HPS.PM.dependantMessagingWarn(sender, "The correct usage is /unteach <spell> [player | me].");
			return true;
		}
			
		if (!HPS.SpellManager.isSpell(args[0])) {
			if (!(args[0].equalsIgnoreCase("all") || args[0].equalsIgnoreCase("*"))) {
				HPS.PM.dependantMessagingWarn(sender, "That spell was not recognized.");
				return true;
			}
		}
		
		Spell spell = HPS.SpellManager.getSpell(args[0]);
		Player unteachTo = null;
		
		if ((args.length == 1) || (args[1].equalsIgnoreCase("me"))) {
			if (sender instanceof Player) {
				unteachTo = (Player) sender;
			} else {
				HPS.PM.dependantMessagingWarn(sender, "Please specify a player.");
			}
		} else {
			unteachTo = Bukkit.getPlayer(args[1]);
		}

		if (unteachTo != null) {
			
			if (args[0].equalsIgnoreCase("all") || args[0].equalsIgnoreCase("*")) {
				ArrayList<Spell> spells = HPS.SpellManager.getSpells();
				String forgottenspells = null;
				for (Spell newSpell : spells) {
					if (newSpell.playerKnows(unteachTo)) {
						if (forgottenspells == null) {
							newSpell.unTeach(unteachTo);
							forgottenspells = unteachTo.getName() + " has forgotten the spells: " + newSpell.getName();
						} else {
							newSpell.unTeach(unteachTo);
							forgottenspells = forgottenspells.concat(", " + newSpell.getName());
						}
					}
				}
				if (forgottenspells == null) {
					forgottenspells = unteachTo.getName() + " doesn't know any spells.";
				}
				
				HPS.PM.dependantMessagingTell(sender, forgottenspells + ".");
				
			} else {
				
				if(spell.playerKnows(unteachTo)) {
					spell.unTeach(unteachTo);
					HPS.PM.dependantMessagingTell(sender, unteachTo.getName() + " has forgotten " + spell.getName() + ".");
				} else {
					HPS.PM.dependantMessagingWarn(sender, unteachTo.getName() + " doesn't know " + spell.getName() + ".");
				}
				
			}	
		} else {
			HPS.PM.dependantMessagingTell(sender, "The player was not found.");
		}
		return true;
	}
}
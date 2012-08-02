package com.lavacraftserver.HarryPotterSpells.Utils;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;

import com.lavacraftserver.HarryPotterSpells.Spells.Confringo;
import com.lavacraftserver.HarryPotterSpells.Spells.Confundus;
import com.lavacraftserver.HarryPotterSpells.Spells.TimeSpell;
import com.lavacraftserver.HarryPotterSpells.Spells.TreeSpell;
import com.lavacraftserver.HarryPotterSpells.Spells.WingardiumLeviosa;

public class SpellSender {
	
	public static void go(String spell, Player p, PlayerInteractEvent e) {
		if(spell.equalsIgnoreCase("confringo")) {Confringo.cast(p, e);}
		if(spell.equalsIgnoreCase("time")) {TimeSpell.cast(p, e);}
		if(spell.equalsIgnoreCase("tree")) {TreeSpell.cast(p);}
		if(spell.equalsIgnoreCase("confundus")) {Confundus.cast(p);}
		if(spell.equalsIgnoreCase("wingardiumleviosa")) {WingardiumLeviosa.cast(p);}
	}
}
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.text.DecimalFormat;

import org.rsbot.event.listeners.PaintListener;
import org.rsbot.script.Script;
import org.rsbot.script.ScriptManifest;
import org.rsbot.script.methods.Methods;
import org.rsbot.script.util.Filter;
import org.rsbot.script.wrappers.RSArea;
import org.rsbot.script.wrappers.RSGroundItem;
import org.rsbot.script.wrappers.RSItem;
import org.rsbot.script.wrappers.RSNPC;

@ScriptManifest(authors = { "Foulwerp" }, name = "Chickens", keywords = { "Combat" }, description = "Kills Chickens & Loots Feathers", version = 1.03)
public class Chickens extends Script implements PaintListener {

	private static final DecimalFormat k = new DecimalFormat("#.#");
	private static final int[] skill = { 0, 1, 2, 3, 4, 6 };
	private static final int[] drop = { 526, 1925, 1944, 2138 };
	private static final String[] skillNames = { "Attack", "Defence",
			"Strength", "Constitution", "Range", "Magic" };
	private static final Color[] skillColors = {
			new Color(145, 25, 25).brighter(), new Color(95, 115, 185),
			Color.GREEN.darker(), Color.WHITE.darker(),
			new Color(70, 95, 20).brighter(), new Color(95, 115, 230) };

	private long start;
	private int[] startXP;
	private RSArea area;
	private static final RSArea[] areas = { new RSArea(3014, 3282, 3020, 3298),
			new RSArea(3022, 3281, 3037, 3290),
			new RSArea(3225, 3295, 3236, 3301),
			new RSArea(3195, 2252, 3198, 3359) };

	private String format(final long time) {
		if (time <= 0) {
			return "--:--:--";
		}
		final StringBuilder t = new StringBuilder();
		final long TotalSec = time / 1000;
		final long TotalMin = TotalSec / 60;
		final long TotalHour = TotalMin / 60;
		final int second = (int) TotalSec % 60;
		final int minute = (int) TotalMin % 60;
		final int hour = (int) TotalHour;
		if (hour < 10) {
			t.append("0");
		}
		t.append(hour);
		t.append(":");
		if (minute < 10) {
			t.append("0");
		}
		t.append(minute);
		t.append(":");
		if (second < 10) {
			t.append("0");
		}
		t.append(second);
		return t.toString();
	}

	private RSNPC interactingNPC() {
		return npcs.getNearest(new Filter<RSNPC>() {
			@Override
			public boolean accept(final RSNPC n) {
				return n.getInteracting() != null
						&& n.getInteracting().equals(players.getMyPlayer())
						&& area.contains(n.getLocation());
			}
		});
	}

	@Override
	public int loop() {
		mouse.setSpeed(Methods.random(4, 7));
		if (!game.isLoggedIn()) {
			return 3000;
		}
		if (players.getMyPlayer().getInteracting() != null) {
			if (interfaces.canContinue()) {
				interfaces.clickContinue();
			}
			return Methods.random(900, 1100);
		}
		final RSItem junk = inventory.getItem(Chickens.drop);
		if (junk != null) {
			junk.doAction("Drop");
			return Methods.random(700, 900);
		}
		final RSGroundItem feather = pickup();
		if (feather != null) {
			if (players.getMyPlayer().isMoving()) {
				return Methods.random(400, 600);
			}
			if (!feather.isOnScreen()) {
				camera.turnTo(feather.getLocation(), 15);
				if (!feather.isOnScreen()) {
					walking.walkTileMM(walking.getClosestTileOnMap(feather.getLocation()));
					return Methods.random(900, 1200);
				}
			}
			feather.doAction("Take Feather");
			return Methods.random(900, 1100);
		}
		if (!walking.isRunEnabled() && walking.getEnergy() > 60) {
			walking.setRun(true);
			return Methods.random(750, 1000);
		}
		final RSNPC chicken = newNPC();
		if (chicken == null) {
			if (calc.distanceTo(area.getCentralTile()) > 3) {
				walking.walkTileMM(area.getCentralTile());
			}
			return Methods.random(800, 1000);
		}
		if (players.getMyPlayer().isMoving() && !chicken.isOnScreen()) {
			return Methods.random(400, 600);
		}
		if (!chicken.isOnScreen()) {
			turnTo(camera.getCharacterAngle(chicken), chicken);
			if (!chicken.isOnScreen()) {
				walking.walkTileMM(walking.getClosestTileOnMap(chicken.getLocation()));
				return Methods.random(800, 1000);
			}
		}
		chicken.doAction("Attack " + chicken.getName());
		return Methods.random(900, 1100);
	}

	private RSNPC newNPC() {
		final RSNPC interacting = interactingNPC();
		return interacting != null ? interacting
				: npcs.getNearest(new Filter<RSNPC>() {
					@Override
					public boolean accept(final RSNPC npc) {
						return npc.getName().equals("Chicken")
								&& npc.getHPPercent() > 0 && !npc.isInCombat()
								&& area.contains(npc.getLocation());
					}
				});
	}

	@Override
	public void onRepaint(final Graphics render) {
		int y = 365, z = 356;
		final int w = 196, x = 201;
		final Graphics2D g = (Graphics2D) render;
		final long runTime = System.currentTimeMillis() - start;
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		final Point m = mouse.getLocation();
		g.drawLine((int) m.getX() - 3, (int) m.getY(), (int) m.getX() + 3, (int) m.getY());
		g.drawLine((int) m.getX(), (int) m.getY() - 3, (int) m.getX(), (int) m.getY() + 3);
		g.setFont(new Font("Comic Sans MS", Font.PLAIN, 10));
		g.setColor(Color.BLACK);
		g.drawRect(w, 345, 300, 11);
		g.setColor(new Color(0, 0, 0, 220));
		g.fillRect(w, 345, 300, 11);
		g.setColor(Color.WHITE);
		g.drawString("Chickens - Run Time: " + format(runTime)
				+ " - Version 1.03", x, 354);
		double eph;
		int exp;
		long ttl;
		for (int i = 0; i < 6; i++) {
			exp = skills.getCurrentExp(Chickens.skill[i]) - startXP[i];
			if (exp > 0) {
				eph = exp * 3600000D / (System.currentTimeMillis() - start);
				ttl = (long) (skills.getExpToNextLevel(Chickens.skill[i]) * 3600000D / eph);
				g.setColor(Color.BLACK);
				g.drawRect(w, z, 300, 11);
				g.setColor(new Color(0, 0, 0, 220));
				g.fillRect(w, z, 300, 11);
				g.setColor(Chickens.skillColors[i]);
				g.drawString(Chickens.skillNames[i] + ": "
						+ Chickens.k.format(exp / 1000D) + " K Earned - "
						+ Chickens.k.format(eph / 1000) + " K P/H - "
						+ format(ttl) + " TTL", x, y);
				y += 11;
				z += 11;
			}
		}
	}

	@Override
	public boolean onStart() {
		for (int i = 0; i < Chickens.areas.length; i++) {
			if (Chickens.areas[i].contains(players.getMyPlayer().getLocation())) {
				area = Chickens.areas[i];
				switch (i) {
				case 0:
					log("Falador Chickens Inside Pen...");
					break;
				case 1:
					log("Falador Chickens Outside House...");
					break;
				case 2:
					log("Lumbridge Chickens...");
					break;
				case 3:
					log("Champions Guild Chickens...");
					break;
				}
			}
		}
		if (area == null) {
			log("Not In A Suppoted Chicken Location...");
			return false;
		}
		startXP = new int[6];
		for (int i = 0; i < Chickens.skill.length; i++) {
			startXP[i] = skills.getCurrentExp(Chickens.skill[i]);
		}
		start = System.currentTimeMillis();
		return true;
	}

	private RSGroundItem pickup() {
		return groundItems.getNearest(new Filter<RSGroundItem>() {
			@Override
			public boolean accept(final RSGroundItem g) {
				return g.getItem().getID() == 314
						&& area.contains(g.getLocation());
			}
		});
	}

	public void turnTo(int degrees, final RSNPC n) {
		final char left = 37;
		final char right = 39;
		char whichDir = left;
		int start = camera.getAngle();
		if (start < 180) {
			start += 360;
		}
		if (degrees < 180) {
			degrees += 360;
		}
		if (degrees > start) {
			if (degrees - 180 < start) {
				whichDir = right;
			}
		} else if (start > degrees) {
			if (start - 180 >= degrees) {
				whichDir = right;
			}
		}
		degrees %= 360;
		keyboard.pressKey(whichDir);
		int timeWaited = 0;
		while (!n.isOnScreen() && camera.getAngle() > degrees + 10
				|| !n.isOnScreen() && camera.getAngle() < degrees - 10) {
			Methods.sleep(10);
			timeWaited += 10;
			if (timeWaited > 500) {
				final int time = timeWaited - 500;
				if (time == 0) {
					keyboard.pressKey(whichDir);
				} else if (time % 40 == 0) {
					keyboard.pressKey(whichDir);
				}
			}
		}
		keyboard.releaseKey(whichDir);
	}
}

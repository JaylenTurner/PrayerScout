package com.prayerscout;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/** Displays general NPC attack-style information only; never recommends prayers or switches. */
public final class PrayerScoutRecommendations
{
    public static final class Result
    {
        public final String recommendation;
        public final String note;
        public final boolean verified;

        Result(String attackStyles, String note, boolean verified)
        {
            this.recommendation = attackStyles;
            this.note = note;
            this.verified = verified;
        }
    }

    private static final Map<String, Result> MONSTERS = new HashMap<>();

    static
    {
        MONSTERS.put("cow", new Result("Melee", "Basic monster", true));
        MONSTERS.put("chicken", new Result("Melee", "Basic monster", true));
        MONSTERS.put("giant rat", new Result("Melee", "Basic monster", true));
        MONSTERS.put("hill giant", new Result("Melee", "Basic monster", true));
        MONSTERS.put("moss giant", new Result("Melee", "Basic monster", true));
    }

    private PrayerScoutRecommendations() {}

    public static Result lookup(String npcName)
    {
        if (npcName == null) return new Result("Unknown", "No target", false);
        Result found = MONSTERS.get(npcName.toLowerCase(Locale.ROOT).trim());
        return found != null ? found : new Result("Unknown", "Consult OSRS Wiki", false);
    }
}

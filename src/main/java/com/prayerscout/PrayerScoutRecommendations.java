package com.prayerscout;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Conservative starter dataset. Do NOT assume an attack style based on monster appearance.
 * Recommendations must be verified against current OSRS mechanics before being added.
 * Multi-style encounters and prayer-bypassing attacks should remain explicitly marked.
 */
public final class PrayerScoutRecommendations
{
    public static final class Result
    {
        public final String recommendation;
        public final String note;
        public final boolean verified;

        Result(String recommendation, String note, boolean verified)
        {
            this.recommendation = recommendation;
            this.note = note;
            this.verified = verified;
        }
    }

    private static final Map<String, Result> MONSTERS = new HashMap<>();

    static
    {
        // Initial examples; validate individual NPC variants before broader rollout.
        MONSTERS.put("cow", new Result("Protect from Melee", "Melee attacks", true));
        MONSTERS.put("chicken", new Result("Protect from Melee", "Melee attacks", true));
        MONSTERS.put("giant rat", new Result("Protect from Melee", "Melee attacks", true));
        MONSTERS.put("hill giant", new Result("Protect from Melee", "Melee attacks", true));
        MONSTERS.put("moss giant", new Result("Protect from Melee", "Melee attacks", true));
    }

    private PrayerScoutRecommendations() {}

    public static Result lookup(String npcName)
    {
        if (npcName == null) return new Result("Unknown", "No target", false);
        Result found = MONSTERS.get(npcName.toLowerCase(Locale.ROOT).trim());
        return found != null ? found : new Result("Unknown / Special", "Check Wiki; no verified data", false);
    }
}

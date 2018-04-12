package seedu.address.commons.core;

import static seedu.address.commons.util.FetchUtil.parseFileToJsonObj;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import seedu.address.model.coin.Coin;

/**
 * Represents a list of existing coin codes that have a Subreddit.
 */
//@@author Eldon-Chung
public class CoinSubredditList {

    private static final Map<String, String> COIN_CODE_TO_SUBREDDIT_MAP = new HashMap<>();
    private static final String COIN_CODE_TO_SUBREDDIT_FILEPATH = "data/CoinCodeToSubreddit.json";

    public static boolean isRecognized(Coin coin) {
        return COIN_CODE_TO_SUBREDDIT_MAP.containsKey(coin.getCode().toString());
    }

    /**
     * Initialises the CoinToSubredditList to store existing Coin subreddits
     * @throws FileNotFoundException if the file missing
     */
    public static void initialize() throws FileNotFoundException {
        JsonArray jsonArray = parseFileToJsonObj(COIN_CODE_TO_SUBREDDIT_FILEPATH);
        JsonElement codeString;
        JsonElement subredditName;

        for (JsonElement jsonElement : jsonArray) {
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            codeString = jsonObject.get("code");
            subredditName = jsonObject.get("subreddit");
            if (subredditName == null || subredditName.toString().equals("null")) {
                continue;
            }
            COIN_CODE_TO_SUBREDDIT_MAP.put(codeString.getAsString(), subredditName.getAsString());
        }
    }
}

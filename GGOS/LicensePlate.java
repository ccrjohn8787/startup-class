package Google;

/**
 * Created by siyuzhan on 5/7/16.
 */
public class LicensePlate {
    /**
     * 給一個車牌號碼(美國的)，以及一個dictionary，請找出dictionary裡含有所有該車牌號碼裡的所有英文字母(case insensitive)的最短字串
     ex:
     車牌 RO 1287 ["rolling", "real", "WhaT", "rOad"] => "rOad"
     follow up:
     (1) 如果dictionary裡有上百萬個字，該如何加速
     (2) 如果dictionary有上百萬個字，然後給你上千個車牌號碼，要你回傳相對應的最短字串，該如何optimize?.
     */

    /**
     * int[] charMap = new int[26];
     * for each char in license plate:
     *      charMap[char - 'a'] ++;
     *
     * for each word in dict:
     *      int[] map = new int[26];
     *      for each char:
     *          map[char - 'a'] ++;
     *      compare map with charmap -> map[char] >= charMap[char]
     */
}

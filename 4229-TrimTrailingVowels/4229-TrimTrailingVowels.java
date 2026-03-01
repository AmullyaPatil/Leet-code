// Last updated: 01/03/2026, 21:10:31
class Solution {

    public String trimTrailingVowels(String s) {
        if (s == null || s.isEmpty()) return s;

        int i = s.length() - 1;
        while (i >= 0 && isVowel(s.charAt(i))) {
            i--;
        }
        return s.substring(0, i + 1);
    }

    private boolean isVowel(char c) {
        // Vowels: a, e, i, o, u
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }
}
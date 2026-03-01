// Last updated: 01/03/2026, 21:10:40
class Solution {
    public String makeLargestSpecial(String s) {
        // If the string is empty or too short, return as is
        if (s == null || s.length() <= 2) {
            return s;
        }

        // 1) Decompose s into its top-level special substrings
        // Each piece starts with '1' and ends when balance returns to 0
        List<String> pieces = new ArrayList<>();
        int balance = 0;
        int start = 0;
        for (int i = 0; i < s.length(); i++) {
            balance += (s.charAt(i) == '1') ? 1 : -1;

            if (balance == 0) {
                // we found a top-level piece from start to i
                String piece = s.substring(start, i + 1);
                // 2) Recursively solve the inner part of this piece (strip the outer 1 and last 0)
                // The inner part is piece.substring(1, piece.length() - 1)
                String inner = piece.substring(1, piece.length() - 1);
                String parsedInner = makeLargestSpecial(inner);
                // put back the outer wrappers around the solved inner part
                String rebuilt = "1" + parsedInner + "0";
                pieces.add(rebuilt);
                // prepare for next piece
                start = i + 1;
            }
        }

        // 3) Sort top-level pieces in descending lexicographic order to maximize
        pieces.sort(Comparator.reverseOrder());

        // 4) Concatenate and return
        StringBuilder sb = new StringBuilder();
        for (String p : pieces) sb.append(p);
        return sb.toString();
    }
}
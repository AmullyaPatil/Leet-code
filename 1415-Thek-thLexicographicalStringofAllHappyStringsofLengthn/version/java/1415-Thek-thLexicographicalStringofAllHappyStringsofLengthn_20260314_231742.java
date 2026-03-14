// Last updated: 14/03/2026, 23:17:42
1class Solution {
2    private static final char[] CHARS = {'a', 'b', 'c'};
3    private int n;
4    private int k;
5    private String result;
6    private boolean found;
7
8    public String getHappyString(int n, int k) {
9        this.n = n;
10        this.k = k;
11        this.result = "";
12        this.found = false;
13
14        StringBuilder sb = new StringBuilder();
15        dfs(0, sb);
16        return result;
17    }
18
19    private void dfs(int pos, StringBuilder sb) {
20        if (found) return;
21        if (pos == n) {
22            k--;
23            if (k == 0) {
24                result = sb.toString();
25                found = true;
26            }
27            return;
28        }
29
30        for (char c : CHARS) {
31            if (pos > 0 && sb.charAt(pos - 1) == c) continue;
32            sb.append(c);
33            dfs(pos + 1, sb);
34            sb.deleteCharAt(sb.length() - 1);
35            if (found) return;
36        }
37    }
38}
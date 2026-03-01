// Last updated: 01/03/2026, 21:10:38
class Solution {
public int concatenatedBinary(int n) {
final int MOD = 1_000_000_007;
long res = 0;
for (int i = 1; i <= n; i++) {
int L = 32 - Integer.numberOfLeadingZeros(i);
res = ((res << L) % MOD + i) % MOD;
}
return (int) res;
}
}
// Last updated: 01/03/2026, 21:10:49
public class Solution {
public boolean isPalindrome(int x) {
if (x < 0) return false;
int original = x;
long reversed = 0; // use long to avoid overflow
while (x > 0) {
reversed = reversed * 10 + x % 10;
x /= 10;
}
return original == reversed;
}
}
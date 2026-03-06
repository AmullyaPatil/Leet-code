class Solution(object):
    def checkOnesSegment(self, s):
        """
        :type s: str
        :rtype: bool
        """
        seen_one = False
        zero_after_one = False

        for ch in s:
            if ch == '1':
                if zero_after_one:
                    return False
                seen_one = True
            else:
                if seen_one:
                    zero_after_one = True

        return True

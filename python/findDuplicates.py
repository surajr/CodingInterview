class Solution(object):
    def findDuplicates(self, nums):
        """
        :type nums: List[int]
        :rtype: List[int]
        """
        result = []
        for x in nums:
            if nums[abs(x)-1] < 0:
                result.append(abs(x))
            nums[abs(x)-1] *= -1
        return result

class Solution(object):
    def findDuplicates(self, nums):
        """
        :type nums: List[int]
        :rtype: List[int]
        """
        map = collections.Counter(nums)
        result = []
        for k,v in map.iteritems():
            if v == 2:
                result.append(k)
        return result

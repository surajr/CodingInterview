/**
 * @param {number[]} nums
 * @param {number} target
 * @return {number[]}
 */
var twoSum = function(nums, target) {
    let map = {};
    for(let i=0; i<nums.length; i++){
        if (map[target-nums[i]] !== undefined)
            return [i, map[target-nums[i]]];
        else
            map[nums[i]]=i;
    }
    return [];
};

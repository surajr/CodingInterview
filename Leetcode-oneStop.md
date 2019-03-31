### [Lowest Common Ancestor](https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/)

```java
public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    TreeNode current = root;
    while (current != null){
        if (p.val < current.val && q.val < current.val)		// Both located in left side.
            current = current.left;
        else if (p.val > current.val && q.val > current.val)	// Both located in right side
            current = current.right;
        else
            return current;		// Seperate branches, therefore current is lca.
    }
    return null;
}
```



### [Count And Say](https://leetcode.com/problems/count-and-say/)

The updated version runs in 2ms and passes 96.85% submissions.

```java
public String countAndSay(int n) {
    String result = "1";		// initial result
    StringBuilder temp;			// to create intermediate strings efficiently.
    int len;					// length of the result string.
    for (int i = 1; i < n; ++i){	// We need to iterate n-1 times, because 1st result is 1
        int startIndex = 0;			// we will look at each index of result
        temp = new StringBuilder();	// and store freq,char in the builder
        len = result.length();
        while (startIndex < len){
            char ch = result.charAt(startIndex++);	// get the char at startIndex, and increment it, because we also want to look at the next character
            int count = 1;					// intialize it's count to 1, we just saw it.
            while (startIndex < len && ch == result.charAt(startIndex)){
                count++;			// If next also matches, increment count and startIndex
                startIndex++;
            }
            temp.append(count).append(ch);	// No more match, Add the freq and the char
        }
        result = temp.toString();	// Update result to generate the next cound-and-say
    }
    return result;
}
```



### [Maximum SubArray](https://leetcode.com/problems/maximum-subarray/)

```java
public int maxSubArray(int[] nums) {
    int localMax = nums[0];		// keeps track of max sum between the previous and current
    int globalMax = nums[0];	// keeps track of global max sum.

    /*
    The idea is as follows:
    If the current element is greater than the previous local max, then we found an element that is a better option then before.
    Then, if that localmax changed and is greater than our global max, update our global max.
    */

    for (int i = 1; i < nums.length; i++){
        localMax = Math.max(localMax + nums[i], nums[i]);
        globalMax = Math.max(localMax, globalMax);
    }

    return globalMax;
}
```



### [Plus One](https://leetcode.com/problems/plus-one/)

```java
public int[] plusOne(int[] digits)
{
    digits[digits.length-1]++;			// Add one to the last place.
    if (digits[digits.length-1] == 10)	// If it became 10,
    {
        for (int i = digits.length-1; i > 0; i--)	// Then add one to its previous place
        {
            if (digits[i] == 10){	// If that also results in 10, keep propogating that 1
                digits[i-1]++;		// upstream
                digits[i] = 0;
            }
        }
        if (digits[0] == 10){	// If the index 0 is 10, then the number is a multiple of 10.
            digits = new int[digits.length+1];
            digits[0] = 1;		// So increase length by 1 and set index 0 to 1.
        }
    }
    return digits;
}
```



### [Sqrt of X](https://leetcode.com/problems/sqrtx/)

```java
public int mySqrt(int x) {
    long x1 = 10 - (100 - x)/20;		// Using Newton's method of computing square roots.
    boolean done = false;
    while (!done)
    {
        long x2 = x1 - (x1*x1 - x)/(2*x1);
        if (x2 == x1)
            done = true;
        else
            x1 = x2;
    }
    return (int)x1-1;
}
```



### [Climbing Stairs](https://leetcode.com/problems/climbing-stairs/)

```java
public int climbStairs(int n) {
    if (n < 4)		// I chose n < 4 because climbStairs(0 <= n <= 3) = n
        return n;
    int[] dp = new int[n+1];
    for (int i = 0; i < 4; i++)
        dp[i] = i;
    //return naiveDP(n, dp);
    return efficientDP(n);
}

public int naiveDP(int n, int dp[]){
    if (dp[n] != 0)		// If already computed, return it.
        return dp[n];
    int ways =  naiveDP(n-1, dp) + naiveDP(n-2, dp);	// Just like Fibonacci.
    dp[n] = ways;		// Save it.
    return ways;
}

public int efficientDP(int n){
    if (n < 4)
        return n;
    int[] dp = new int[n+1];		// Initialize dp of length n+1 to store n'th way.
    for (int i = 0; i < 4; i++)
        dp[i] = i;					// climbStairs(0 <= n <= 3) = n
    for (int i = 3; i <= n; i++)	// climbStairs(n) = climbStairs(n-1) + climbstairs(n-2);
        dp[i] = dp[i-1] + dp[i-2];  // So fetch those values from the dp array.
    return dp[n];
}
```



### [Remove Duplicates from sorted list](https://leetcode.com/problems/remove-duplicates-from-sorted-list/)

```java
public ListNode deleteDuplicates(ListNode head){
    ListNode current = head;
    // while we haven't reached the tail
    while (current != null && current.next != null)
    {
        // if current's next is the same as current, skip and update its next
        while (current.next != null && current.val == current.next.val)
            current.next = current.next.next;
        current = current.next;
    }
    return head;
}
```



### [Same Tree](https://leetcode.com/problems/same-tree/)

```java
public boolean isSameTree(TreeNode p, TreeNode q)
{
    if (p == null && q == null)		// Two empty trees
        return true;
    // If one of the node is null, the two trees can't be equal.
    if ((p == null && q != null) || (p != null && q == null))
        return false;
    // If the values in the two nodes are same, compare its's left and right sub-tree.
    if (p.val == q.val)
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    return false;		// If nothing worked out, they can't be same.
}
```



### [Symmetric Tree](https://leetcode.com/problems/symmetric-tree/)

```java
public boolean isSymmetric(TreeNode root)
{
    return isSymmetricIterative(root);
}

public boolean isSymmetricIterative(TreeNode root)
{
    Queue<TreeNode> track = new LinkedList<>();
    track.add(root);		// Add the root twice so we can compare its left and right
    track.add(root);
    while (!track.isEmpty())
    {
        TreeNode x = track.poll();		// Remove 2 nodes
        TreeNode y = track.poll();

        if (x == null && y == null)		// If they are both null, skip it.
            continue;
        if (x == null || y == null || x.val != y.val)
            return false;				// If values don't match or one is null
        track.add(x.left);		// Otherwise add them in this order -> LRRL
        track.add(y.right);		// because we need to compare left most with the
        track.add(x.right);		// right most, then inner left with inner right.
        track.add(y.left);
    }
    return true;		// Everything's all right, so they must be symmetric.
}

public boolean isSymmetricRecursive(TreeNode root)
{
    return helperRecursive(root, root);
}

private boolean helperRecursive(TreeNode x, TreeNode y)
{
    if (x == null || y == null)		// Base Case: Both or one is null, so true
        return true;
    return (x.val == y.val && helperRecursive(x.left, y.right) && helperRecursive(x.right, y.left));
    // Check if values match and 1.left matches with the 2.right and 1.right matches with 2.left
}
```



### [Max Depth of Binary Tree](https://leetcode.com/problems/maximum-depth-of-binary-tree/)

```java
/*
If root is null, height is 0 else add 1 and find if the left or the right has a greater depth.
*/
public int maxDepth(TreeNode root) {
    return root == null ? 0 : 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
}
```



### [Convert Sorted Array to Binary Search Tree](https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/)

```java
public TreeNode sortedArrayToBST(int[] nums)
{
    return aux(nums, 0, nums.length-1);
}

private TreeNode aux(int[] n, int left, int right)
{
    if (left > right)					// Either empty, or return a null node
        return null;

    int mid = (left+right+1)/2;			// Create a node with the middle value
    TreeNode root = new TreeNode(n[mid]);
    root.left = aux(n, left, mid-1);	// Compute the left (which is the mid in left side)
    root.right = aux(n, mid+1, right);	// Compute the right (which is the mid in right side)
    return root;
}
```



### [Balanced Binary Tree](https://leetcode.com/problems/balanced-binary-tree/)

```java
public boolean isBalanced(TreeNode root)
{
    return isBalancedBottomUp(root);
}

public boolean isBalancedTopDown(TreeNode root)
{
    if (root == null)
        return true;
    // if difference between root's left and right is > 1, they're not balanced
    if (Math.abs((getHeight(root.left) - getHeight(root.right))) > 1)
        return false;
    // otherwise, we need to check if the left and right subtree are also balanced.
    return isBalanced(root.left) && isBalanced(root.right);
}

private int getHeight(TreeNode node)
{
    // Standard height of a binary tree calculator
    if (node == null)
        return 0;
    return 1 + Math.max(getHeight(node.left), getHeight(node.right));
}

public boolean isBalancedBottomUp(TreeNode root)
{
    return getHeight2(root) != -1;	// -1 means not balanced.
}

private int getHeight2(TreeNode node)
{
    if (node == null)
        return 0;

    int lHeight = getHeight2(node.left);	// Get the height of left and right tree
    int rHeight = getHeight2(node.right);

    // If at any point there was a height difference of more than 1 or previous node's leftheight || rightheight returned -1, return -1 to let the next node know there was an imbalance.
    if ((Math.abs(lHeight-rHeight) > 1) || lHeight == -1 || rHeight == -1)
        return -1;

    return 1 + Math.max(lHeight, rHeight); // Else carry on with the normal procedure
}

```



### [Minimum Depth of Binary Tree](https://leetcode.com/problems/minimum-depth-of-binary-tree/)

```java
public int minDepth(TreeNode root) {
    // Base case
    if (root == null) return 0;
    // Left is null, find minheight from right side
    if (root.left == null) return 1 + minDepth(root.right);
    // Right is null, find minheight from left side
    if (root.right == null) return 1 + minDepth(root.left);
    // Else, both are not null, so compute min height from the two sides.
    return 1 + Math.min(minDepth(root.left), minDepth(root.right));
}
```



### [Path Sum](https://leetcode.com/problems/path-sum/)

```java
public boolean hasPathSum(TreeNode root, int sum) {
    if (root == null)
        return false;	// No sum exist
    sum -= root.val;	// Sum decreases
    if (root.left == null && root.right == null)	// If we are at a leaf
        return sum == 0;	// Check if the sum is 0.
    return hasPathSum(root.left, sum) || hasPathSum(root.right, sum);
    // Otherwise look if you can make sum = 0 by exploring the left or right side.
}
```



### [Pascal's Triangle](https://leetcode.com/problems/pascals-triangle/)

```java
public List<List<Integer>> generate(int numRows) {
    List<List<Integer>> pt = new ArrayList<>();
    for (int i = 0; i < numRows; i++)	// Need to add all n rows
    {
        List<Integer> temp = new ArrayList<>();		// temp list to store values
        for (int j = 0; j <= i; j++)
        {
            if (j == 0 || i == j)		// First and last values are always 1.
                temp.add(1);
            else	// Else, get the previous row and surrounding two values and add them
                temp.add(pt.get(i-1).get(j-1) + pt.get(i-1).get(j));
        }
        pt.add(temp);		// Add it to pt.
    }
    return pt;
}
```



### [Valid Palindrome](https://leetcode.com/problems/valid-palindrome/)

```java
public boolean isPalindrome(String s) {
    if (s.length() > 0){		// Only do this is s is not empty
        s = s.toLowerCase();	// Convert it to lowercase
        int left = 0;			// Initialize left and right pointers
        int right = s.length()-1;
        while (left < right)	// continue while we haven't hit the middle of the string
        {
            // If char at left is not a letter or a number, skip it.
            if (!Character.isLetter(s.charAt(left)) && !Character.isDigit(s.charAt(left)))
                left++;
            // Same with char at right.
            else if (!Character.isLetter(s.charAt(right)) && !Character.isDigit(s.charAt(right)))
                right--;
            //Char's are now alphanumeric.
            else if (s.charAt(left) != s.charAt(right))	// If they don't match
                return false;	// return false
            else	// They matched, so try to match the inner string
            {
                left++;
                right--;
            }
        }
    }
    return true;	// No mismatch found, return true.
}
```



### [Pascal's Triangle II](https://leetcode.com/problems/pascals-triangle-ii/)

```java
public List<Integer> getRow(int rowIndex)
{
    ArrayList<Integer> row = new ArrayList<>();
    row.add(1);	// First is always 1.
	// Using the nth row formula to compute the coeeficients. You can google "nth row Pascal"
    for (int i = 0; i < rowIndex; i++)
        row.add((int)(1.0*row.get(i)*(rowIndex-i)/(i+1)));
    return row;
}
```



### [Best Time to Buy and Sell Stock](https://leetcode.com/problems/best-time-to-buy-and-sell-stock/)

```Java
/*
The general idea is that if the price you are looking at right now in the array minus the minimum observed so far is greater than the maximum profit you recorded, update the max.
*/
public int maxProfit(int[] prices) {
    if (prices.length == 0)		// Empty array
        return 0;
    int min = prices[0];

    int max = 0;
    for (int i = 1; i < prices.length; i++)
    {
        if (prices[i] < min)
            min = prices[i];
        else if (prices[i] - min > max)
            max = prices[i]-min;
    }
    return max;
}
```



### [Best Time to Buy and Sell Stock II](https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/)

```java
/*
The general idea is that the moment you observe a valley and consecutive peak, make the trade by buying the stock on the valley day and selling it on the peak day.
*/
public int maxProfit(int[] prices) {
    int sum = 0;
    for (int i = 0; i < prices.length-1; i++)
        if (prices[i+1] > prices[i])
            sum += (prices[i+1] - prices[i]);
    return sum;
}
```



### [Single Number](https://leetcode.com/problems/single-number/)

```java
/*
The general idea is that XOR of two same numbers returns 0 and XOR with 0 returns the same number. So if there is only one element that doesn't have a pair, all the remaining will XOR with themselves at one point and give 0 but not the singleton element.
*/
public int singleNumber(int[] nums) {
    int num = nums[0];
    for (int i = 1; i < nums.length; i++)
        num ^= nums[i];
    return num;
}
```



### [Linked List Cycle](https://leetcode.com/problems/linked-list-cycle/)

```java
// Using the slow-fast runner technique.
public boolean hasCycle(ListNode head) {
    if (head == null)
        return false;
    ListNode first = head;	// Slow runner
    ListNode second = first.next;		// Fast Runner
    // while second is not at the end or it isn't the tail
    while (second != null && second.next != null)
    {
        if (second == first)	// If fast made a full loop and met up with slow
            return true;		// We got a cycle
        first = first.next;		// Slow moves one step
        second = second.next.next;	// Second advances two.
    }
    return false;		// We don't have a cycle
}
```



### [Min Stack](https://leetcode.com/problems/min-stack/)

```Java
class MinStack {

    int min;
    Stack<Integer> stack;

    public MinStack() {
        min = Integer.MAX_VALUE;
        stack = new Stack<>();
    }

    public void push(int x) {
        stack.push(x);		// Push the value
        if (x < min)		// If that value is minimum than we have, update min
            min = x;
        stack.push(min);	// Push the minimum on top of the stack for constant time
    }						// minimum retrieval.

    public void pop() {
        stack.pop();		// Pop the minimum.
        stack.pop();		// Pop the actual element meant to be popped
        if (stack.isEmpty())	// If empty, min is Max int value
            min = Integer.MAX_VALUE;
        else
            min = stack.peek();	// Otherwise, min would be the top most element since we
    }							// always push the minimum on top of any element we push.

    public int top() {
        return stack.elementAt(stack.size()-2);	// Top element is actually at second last
    }				// index since the last element is the minimum.

    public int getMin() {
        return min;
    }
}
```



### [Intersection of Two Linked Lists](https://leetcode.com/problems/intersection-of-two-linked-lists/)

```java
/*
The general idea is that if you are done traversing any of the lists, make it's pointer point to the head of the other list and start iterating. The reasoning is that the second time they iterate, they will have traversed exactly the same distance (it's length plus the other list's head to the intersecting node) and will meet at the intersecting node.
*/
public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
    int count = 0;
    ListNode pA = headA;
    ListNode pB = headB;
    while (pA != pB){
        pA = pA == null ? headB : pA.next;
        pB = pB == null ? headA : pB.next;
    }
    return pA;
}
```



### [Two Sum II - Input array is sorted](https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/)

```java
public int[] twoSum(int[] numbers, int target) {
    int left = 0, right = numbers.length-1;
    while (left < right)	// Narrow down the window from both sides until they add up.
    {
        int sum = numbers[left] + numbers[right];
        if (sum > target)	// We overshot, so decrease the window from right
            right--;
        else if (sum < target)	// Undershot, increase windows from left so next sum is more
            left++;
        else
            break;				// Found the two numbers
    }
    return new int[] {left+1, right+1};	// +1 because LeetCode followed 1-n indexing.
}
```



### [Excel Sheet Column Title](https://leetcode.com/problems/excel-sheet-column-title/)

```java
public String convertToTitle(int n) {
    String res = "";
    while (n > 0)
    {
        /* 1 is A and 26 is Z, so n-1 to change it to 0-25 scheme. Then, % 26 to find how
        much it is off on a full alphabet cycle, add 65 (ASCII for A) and convert it to char
        */
        res = String.valueOf((char)(65+((n-1)%26))) + res;
        n = (n-1) / 26;	// Subtract 1 and divide by 26 to get prepare for the next character
    }
    return res;
}
```



### [Majority Element](https://leetcode.com/problems/majority-element/)

Uses [Moore's Algorithm](https://www.geeksforgeeks.org/majority-element/)

```java
// This is the implementation of Moore's Algorithm for O(n) complexity.
public int majorityElement(int[] nums) {
    int major = nums[0];
    int count = 1;

   for (int i = 0; i < nums.length; i++){
        if (major == nums[i])
            count++;
        else
            count--;
        if (count == 0){
            major = nums[i];
            count = 1;
        }
    }
    return major;
}
```



### [Excel Sheet Column Number](https://leetcode.com/problems/excel-sheet-column-number/)

```java
/*
Start from the end of String s, compute the ASCII for the char, +1 for 1-26 Alphabet-Scheme (hence -64 instead of -65) and multiply it to 26^{distance from the end of the string}
*/
public int titleToNumber(String s) {
    int length = s.length()-1;
    int total = 0;
    for (int i = length; i > -1; i--)
        total += (int)(s.charAt(i)-64) * Math.pow(26,length-i);
    return total;
}
```



### [Factorial Trailing Zeroes](https://leetcode.com/problems/factorial-trailing-zeroes/)

```java
/*
The general idea is that every factorial that has 5 as a multiple also has 2 to multiply to 10. So if we can count the number of times we can divide n by 5, should gives us the number of trailing zeroes. O(log(n) base 5) complexity.
*/
public int trailingZeroes(int n) {
    int res = 0;
    while (n > 4)
    {
        res += n / 5;
        n /= 5;
    }
    return res;
}
```



### [Combine Two Tables](https://leetcode.com/problems/combine-two-tables/)

```mysql
select FirstName, LastName, City, State
from Person left join Address on Address.personId = person.personId;
```



### [Second Highest Salary](https://leetcode.com/problems/second-highest-salary/)

```mysql
select max(salary) as SecondHighestSalary
from Employee
where salary not in (select max(salary) from employee);
```



### [Employees Earning More Than Their Managers](https://leetcode.com/problems/employees-earning-more-than-their-managers/)

```mysql
select emp.Name as Employee
from Employee emp, Employee man
where emp.managerId = man.Id and emp.salary > man.salary;
```



### [Duplicate Emails](https://leetcode.com/problems/duplicate-emails/)

```mysql
select email
from person
group by (email)
having count(*) > 1;
```



### [Customers Who Never Order](https://leetcode.com/problems/customers-who-never-order/)

```mysql
select name as Customers
from Customers
where customers.id not in (select customerId from orders);
```



### [Rotate Array](https://leetcode.com/problems/rotate-array/)

```java
public void rotate(int[] nums, int k) {
    k %= nums.length;		// k == nums.length ? Then it's a full rotation and no change
    if (k == 0)
        return;
    reverse(nums, 0 , nums.length-1);	// First reverse the full array
    reverse(nums, 0, k-1);				// Then reverse element from index 0 to k-1
    reverse(nums, k, nums.length-1);	// Then reverse all elements from k to end of Array
}

// Reverse function that reverses the array from specified indices.
public void reverse(int[] nums, int start, int end)
{
    while (start < end){
        int temp = nums[start];
        nums[start] = nums[end];
        nums[end] = temp;
        start++;
        end--;
    }
}
```



### [Delete Duplicate Emails](https://leetcode.com/problems/delete-duplicate-emails/)

```mysql
delete from Person
where Id not in (select min_id from
(select min(Id) as min_id from Person group by Email) as a)
```



### [Rising Temperature](https://leetcode.com/problems/rising-temperature/)

```mysql
select w2.id
from weather w1, weather w2
where Datediff(w2.recorddate, w1.recorddate) = 1 and w2.temperature > w1.temperature;
```



### [X of a Kind in a Deck of Cards](https://leetcode.com/problems/x-of-a-kind-in-a-deck-of-cards/)

```java
public boolean hasGroupsSizeX(int[] deck) {
    HashMap<Integer, Integer> freq = new HashMap<>();

    for (int i = 0; i < deck.length; i++)		// Record the frequencies
        freq.put(deck[i],freq.getOrDefault(deck[i],0)+1);

/*
deck = [1,1,2,2,2,2,3,3,3,3,3,3]
number 1 has len of 2, number 2 has len of 4, number 3 has len of 6, they share a Greatest common divisor of 2, which means diving them into group of size X = 2, will be valid. Thus we just have to ensure each length (of a number) shares a Greatest Common Divisor that's >= 2.
*/
    int hcf = 0;
    for (int i: freq.keySet())
        hcf = gcd(hcf, freq.get(i));

    return hcf > 1;
}

private static int gcd(int x, int y)
{
    int temp = 0;
    while (y != 0){
        temp = y;
        y = x % y;
        x = temp;
    }
    return x;
}
```



### [Reverse Integer](https://leetcode.com/problems/reverse-integer/solution/)

```Java
public int reverse(int x) {
    int sign = x < 0 ? -1 : 1;
    x = x * sign;							// Make x positive
    long n = 0;
    while (x > 0){
        n = n * 10 + x % 10;				// Start adding from the end.
        x /= 10;
    }
    return (int)n == n ? (int)n*sign : 0;	// Try converting to int from long, if no change,
}											// Return n * sign, else 0 cause overflow.
```



### [Add Two Numbers](https://leetcode.com/problems/add-two-numbers/submissions/)

```java
public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    int carry = 0;							// To record the carry
    int sum = 0;							// To record the total of two vals
    ListNode dummy = new ListNode(0);		// Dummy's next is the actual head
    ListNode curr = dummy;
    do{
        if (l1 == null)						// If one of the node is null, we set it to a
            l1 = new ListNode(0);			// dummy value of 0 so we can adjust for
        if (l2 == null)						// different length of the two lists.
            l2 = new ListNode(0);
        sum = l1.val + l2.val + carry;		// Add the two vals and the carry.
        carry = sum < 10 ? 0 : 1;			// Record the carry for the next iteration
        curr.next = new ListNode(sum % 10);	// next node's value is sum % 10.
        curr = curr.next;					// advance current, l1 and l2.
        l1 = l1.next;
        l2 = l2.next;
    } while(l1 != null || l2 != null);
    if (carry == 1)							// In the end, if carry is 1, it was from
        curr.next = new ListNode(carry);	// from adding last terms, so make next node 1

    return dummy.next;						// Return the actual head.
}
```



### [Longest Substring Without Repeating Characters](https://leetcode.com/problems/longest-substring-without-repeating-characters/)

```java
public int lengthOfLongestSubstring(String s) {
    if (s == null || s.length() == 0)
        return 0;

    int[] hash = new int[128];					// To store the occurence of characters
    int maxLength = 0;
    for (int i = 0, j = 0; j < s.length(); j++){
        i = Math.max(hash[s.charAt(j)], i);		// Check the most recent index of character.
        maxLength = Math.max(maxLength, j-i+1);	// That minus current pointer gives length
        hash[s.charAt(j)] = j+1;				// Record the index of the next character.
    }
    return maxLength;
}
```



### [House Robber](https://leetcode.com/problems/house-robber)

```java
/*
The basic idea is that if you are robbing house i, the maximum loot may come from by robbing the i-2th house or by robbing the i-3th house. Therefore rob both and then find the path that gave the maximum profit.
Example: loot = [1,9,3,8,4,3,6,4,3,5,7,6]
Profit DP = [1,9,4,17,13,20,23,24,26,29,33,35]
Here,
	dp[2] = loot[2] + loot[1]
	dp[4] = loot[4] + max(dp[2], dp[1])
	dp[5] = loot[5] + max(dp[3], dp[2]) and so on.
In the end, just compare the last two elements to check which path gave us the maximum profit.
Some people might not prefer modifying the original nums array. In that case, you can initialize another dp array of same length, initialize the first two elements as dp[0] = nums[0] and dp[1] = nums[1] and dp[3] = nums[0] + nums[2] and then performing the same loop. In that case, you would be using O(n) space.
*/
public int rob(int[] nums) {
    if (nums.length == 0 || nums == null)			// 3 Base Case
        return 0;
    if (nums.length == 1)
        return nums[0];
    else if (nums.length == 2)
        return Math.max(nums[0], nums[1]);
    else{
        nums[2] = nums[0] + nums[2];				// House 3 profit is rob House 1 and 3.
        for (int i = 3; i < nums.length; i++)
            nums[i] = nums[i] + Math.max(nums[i-2], nums[i-3]);
        return Math.max(nums[nums.length-1], nums[nums.length-2]);
    }
}
```



### [Happy Number](https://leetcode.com/problems/happy-number/submissions/)

```java
public boolean isHappy(int n) {
        return isHappyConstantSpace(n);		// Much faster than set method
        //return isHappySet(n);
    }

    private boolean isHappyConstantSpace(int n){
        int numSeenLessThan10 = 0;		// If I see 10 single digits, then it means that I am
        while (n != 1){					// now starting to see repititions.
            if (n < 10)					// Each time I see a num < 10, increment the counter
                numSeenLessThan10++;
            if (numSeenLessThan10 > 9)
                return false;
            n = getSquare(n);			// Get the total of square of its digits.
        }
        return true;
    }

/*
The general idea is that the moment you see a repition, it can't be a happy number, so keep track of digit square obtained so far. If they hit 1, well and good, otherwise there will be some repition, so return false.
*/
    private boolean isHappySet(int n){
        HashSet<Integer> seen = new HashSet<>();		// Keep track of numbers
        while (true){
            n = getSquare(n);							// Get the sum of digits square
            if (n == 1)									// If it's 1, it's a happy number
                return true;
            else if (seen.contains(n))					// If it's a repition of something
                return false;							// seen before, it's not a happy no.
            else
                seen.add(n);							// If not seen, add it.
        }
    }

    private int getSquare(int n){		// Add the squares of the digits.
        int total = 0;
        while (n != 0){
            int digit = n % 10;
            total += digit * digit;
            n /= 10;
        }
        return total;
    }
}
```



### [Remove Linked List Elements](https://leetcode.com/problems/remove-linked-list-elements/)

```java
public ListNode removeElements(ListNode head, int val) {
    while (head != null && head.val == val)				// While head contains the val, skip
        head = head.next;								// the head
    ListNode current = head;
    while (current != null && current.next != null){	// While we have something to iterate
        if (current.next.val == val)					// If current's val match, skip the
            current.next = current.next.next;			// next node.
        else
            current = current.next;						// Else advance to the next node.
    }
    return head;
}
```



### [Count Primes](https://leetcode.com/problems/count-primes/submissions/)

```java
public int countPrimes(int n) {
    if (n < 2)
        return 0;							// No prime numbers for numbers < 2
    boolean[] store = new boolean[n];		// Using Sieve of Eratosthenes
    for (int i = 2; i*i <= n; i++)			// Start from i = 2 to sqrt(n)
        if (!store[i])						// If store[i] = false, then mark all its
            for (int j = i*i; j < n; j += i)// multiples in the store as true
                store[j] = true;			// True = not a prime, false = prime
    int count = 0;
    for (int i = 2; i < n; i++)				// Loop through the array, count
        if (!store[i])
            count++;
    return count;
}
```



### [Isomorphic Strings](https://leetcode.com/problems/isomorphic-strings/submissions/)

```Java
public boolean isIsomorphic(String s, String t) {
    if (s.length() != t.length())			// Can't be isomorphic is string lengths do not
        return false;						// match
    char[] hashS = new char[128];			// To store String s' match
    char[] hashT = new char[128];			// To store String t's match
    for (int i = 0; i < s.length(); i++){
        char charS = s.charAt(i), charT = t.charAt(i);
        if (hashS[charS] != hashT[charT])	// If the values at respective characters index
            return false;					// do not match, return false
        hashS[charS] = (char)(i+1);			// Otherwise, mark those index with the same
        hashT[charT] = (char)(i+1);			// arbitrary value. I chose a simple (i+1) to
    }										// to mark both the hash with the same value.
    return true;							// Everything worked out, return true;
}
```



### [Reverse LinkedList](https://leetcode.com/problems/reverse-linked-list/solution/)

```java
// Recursive
public ListNode reverseList(ListNode head) {	// Very tricky. Refer to the demo below
    if (head == null || head.next == null)
        return head;
    ListNode node = reverseList(head.next);
    head.next.next = head;
    head.next = null;
    return node;
}

//Iterative
public ListNode reverseList(ListNode head) {
    if (head == null || head.next == null)
        return head;						// No point in reversing empty or 1-sized list
    ListNode curr = head, prev = null;
    ListNode nextNode;
    while (curr != null){					// While we haven't reached the tail
        nextNode = curr.next;				// Store the next node
        curr.next = prev;					// Current's next becomes it's previous
        prev = curr;						// Advance previous to current.
        curr = nextNode;					// Make current the actual next node
    }
    return prev;							// Current is at null, so it's previous is the
}											// new head.
```

![reverse Linked list](/Users/devkapupara/Desktop/Notes/dependencies/reverse Linked list.jpg)



### [Contains Duplicate](https://leetcode.com/problems/contains-duplicate/submissions/)

```java
public boolean containsDuplicate(int[] nums) {
    if (nums.length < 2)
        return false;							// There can't be any duplicates.
    HashSet<Integer> store = new HashSet<>();	// Store unique values.
    for (int n: nums){
        if (!store.add(n))						// Add func returns true if n was'nt present,
            return true;						// false if duplicate. Therefore if it was a
    }											// duplicate, return true.
    return false;								// No duplicates, so return false
}
```



### [Contains Duplicate II](https://leetcode.com/problems/contains-duplicate-ii/)

```java
public boolean containsNearbyDuplicate(int[] nums, int k) {
    if (nums.length < 2)
        return false;
    int left = 0, right = 0;
    HashSet<Integer> store = new HashSet<>();	// Use a rotating window of size k
    while (right < nums.length){				// While we haven't processed everything
        if (store.contains(nums[right]))		// If our current window contains duplicate
            return true;
        store.add(nums[right]);					// No duplicates in the window
        right++;								// Increase right to visit the new element
        if (right - left > k){					// If window becomes > k
            store.remove(nums[left]);			// remove the number on the left side of
            left++;								// the window and increase the left counter
        }										// for new window from the next index
    }
    return false;								// No duplicates found in any window.
}
```



### [Implement Stack Using Queues](https://leetcode.com/problems/implement-stack-using-queues/)

```java
class MyStack {
    Deque<Integer> stack;
    /** Initialize your data structure here. */
    public MyStack() {
        stack = new ArrayDeque<>();
    }

    /** Push element x onto stack. */
    public void push(int x) {
        stack.add(x);
    }

    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        return stack.removeLast();
    }

    /** Get the top element. */
    public int top() {
        return stack.peekLast();
    }

    /** Returns whether the stack is empty. */
    public boolean empty() {
        return stack.isEmpty();
    }
}
```



### [Invert Binary Tree](https://leetcode.com/problems/invert-binary-tree/)

```java
public TreeNode invertTree(TreeNode root) {
    if (root == null)
        return null;
    TreeNode temp = root.left;		// Swap the left and right nodes
    root.left = root.right;
    root.right = temp;
    invertTree(root.left);			// Then swap the subsequent trees of those nodes.
    invertTree(root.right);
    return root;					// Return the original root.
}
```



### [Fibonacci Number](https://leetcode.com/problems/fibonacci-number/)

```java
// Iterative
public int fib(int N) {
    if (N < 2)						// fib(0) = 0; fib(1) = 1
        return N;
    int f0 = 0, f1 = 1, fn = 0;
    for (int i = 2; i <= N; i++){
        fn = f0 + f1;				// fib(n) = fib(n-1) + fib(n-2)
        f0 = f1;					// f0 becomes f1
        f1 = fn;					// f1 becomes fn
    }
    return f1;
}

// Dynamic Programming
private int fibDP(int N){
    if (N < 2)
        return N;
    int[] dp = new int[N+1];		// To store intermediate result
    dp[1] = 1;						// fib(0) = 0; fib(1) = 1
    for (int i = 2; i <= N; i++)
        dp[i] = dp[i-1]+dp[i-2];	// fib(i) = fib(i-1) + fib(i-2)
    return dp[N];					// Return the last number in the array
}
```



### [kth Largest Element](https://leetcode.com/problems/kth-largest-element-in-an-array/)

1.	The minheap algorithm has $O(n lg n) $ complexity and $O(1)$ space. The idea here is that we use a minheap to keep only the k greatest elements. If size becomes more than k, we remove the smallest element at the top of the heap. Thereby, at the end, our kth largest element will be at the top.
2.	QuickSelect Algorithm performs in $O(n)$ best case, $O(n^2)$ worst case when the pivot chosen is always the largest, so we use a random pivot.


```java
// MinHeap Algorithm
public int kthLargest(int[] nums, int k){
    PriorityQueue<Integer> q = new PriorityQueue<>((n1,n2) -> n1 - n2);	// Initialize minheap
    for (int n: nums){
        q.add(n);				// Add number one by one
        if (q.size() > k)		// If size is greater than k
            q.poll();			// Remove the topmost element
    }
    return q.poll();			// The topmost element is our answer
}

// QuickSelect Algorithm - Hoare's Partition Scheme

private int[] arr;

public int kthLargest(int[] nums, int k){
	arr = nums;
	return quickselect(0, nums.length-1, nums.length-k);// kth largest is (n-k)th largest
}

private int quickselect(int left, int right, int k){
	if (left == right)					// Array contains only 1 element, that's the answer
  		return arr[left];
	Random rand = new Random();				// Choose a random pivot between left and right
	int pivotIndex = left + rand.nextInt(right-left);	// but not left
	pivotIndex = partition(left, right, pivotIndex);	// Partition, and find it's correct index
	if (k == pivotIndex)					// That index is equal to kth statistic
  		return arr[pivotIndex];
	else if (k < pivotIndex)			// If it's less than the index, our ans lies in the
  		return quickselect(left, pivotIndex-1, k);	// left side
	else
  		return quickselect(pivotIndex+1, right, k);	// Otherwise, it's on the right side.
}

private int partition(int left, int right, int pivotIndex){
	int pivot = arr[pivotIndex];			// Partition element
	swap(pivotIndex, right);				// Move that element to the end
	int wall = left - 1;					// wall is initially before everything
	for (int i = left; i < right; i++){
  		if (arr[i] < pivot)				// If the current element is < than the pivot, then
    		swap(i, ++wall);			// we need to swap it with the element next to wall.
	}
	swap(right, ++wall);					// Lastly, swap the element at wall and the end.
	return wall;
}

private void swap(int i, int j){
	int temp = arr[i];
	arr[i] = arr[j];
	arr[j] = temp;
}
```



### [Power Of Two](https://leetcode.com/problems/power-of-two/)

```java
public boolean isPowerOfTwo(int n) {
    if (n < 1)
        return false;		// n < 0 cannot be powers of 2
    while (n > 2){
        if (n % 2 != 0)		// If n is odd, it can't be a power of 2.
            return false;
        n = n / 2;			// It is a multiple of 2, so divide it by 2.
    }
    return true;			// n came out to be 1 which is a power of 2, so return true.
}
```



### [Valid Sudoku](https://leetcode.com/problems/valid-sudoku/)

```java
private char[][] board;
public boolean isValidSudoku(char[][] board){
this.board = board;
return rowCheck() && colCheck() && boxCheck();	// Check row first, then column and at
}												// last, boxes because they are time
                                                // consuming.
  
private boolean onePassCheck(){
  HashSet<Integer>[] rows = new HashSet[9];		// 1 HashSet for each row
  HashSet<Integer>[] columns = new HashSet[9];	// 1 HashSet for each column
  HashSet<Integer>[] boxes = new HashSet[9];	// 1 HashSet for each box.

  for (int i = 0; i < 9; i++){
      rows[i] = new HashSet<>();
      columns[i] = new HashSet<>();
      boxes[i] = new HashSet<>();
  }

  for (int i = 0; i < 9; i++){
      for (int j = 0; j < 9; j++){
          int n = (int)(board[i][j]);
          if (n != -2){							// -2 = '.'		
              int boxIndex = (i/3) * 3 + j/3;	// Calculate which box we are in.
              if (!rows[i].add(n) || !columns[j].add(n) || !boxes[boxIndex].add(n))
                  return false;					// If the row set or the column set or the
          }										// box set contains that val, return false.
      }
  }

  return true;
}

private boolean rowCheck(){						// Horizontal check
    boolean[] arr;
    for (char[] row: board){
      arr = new boolean[9];
      for (char c: row){
        int val = c-'0';
        if (val != -2){								// val = -2 means '.' in the board
          if (arr[val-1])							// If val already seen, invalid sudoku
            return false;
          arr[val-1] = true;						// else, Mark that index as seen.
        }
      }
    }
    return true;
  }

  private boolean colCheck(){						// Vertical Check.
    boolean[] arr;
    for (int col = 0; col < board.length; col++){
      arr = new boolean[9];
      for (int row = 0; row < board[0].length; row++){
        int val = board[row][col]-'0';
        if (val != -2){
          if (arr[val-1])
            return false;
          arr[val-1] = true;
        }
      }
    }
    return true;
  }

  private boolean boxCheck(){					// For the 9 sub boxes, let the single
    for (int i = 0; i < 9; i+=3){				// box checker check it's validity.
      for (int j = 0; j < 9; j+=3)				// If any of the subbox was invalid,
        if (!singleBoxCheck(i,j))				// we abort and return false.
          return false;
    }
    return true;
  }

  private boolean singleBoxCheck(int topRightRow, int topRightCol){
    boolean[] arr = new boolean[9];
    for (int i = 0; i < 3; i++){				// Each sub box has 3 rows and 3 columns
      for (int j = 0; j < 3; j++){
        int val = board[topRightRow+i][topRightCol+j]-'0';	// This gives us the value at 
        if (val != -2){							// each cell in the sub box and we fill the
          if (arr[val-1])						// arr with all values that are seen.
            return false;						// If seen twice, return false;
          arr[val-1] = true;
        }
      }
    }
    return true;
  }
}
```



### [Implement Queue Using Stack](https://leetcode.com/problems/implement-queue-using-stacks/submissions/)

```java
/*
Since we reverse stack1 into stack2, stack2 is basically our queue, so if stack2 isn't empty, then the topmost element is what we need when we pop or peek. If it is empty, then again fill it with whatever's there is stack1, and it again becomes the correct queue.
*/
Stack<Integer> stack1;
Stack<Integer> stack2;

public MyQueue() {
    stack1 = new Stack<>();
    stack2 = new Stack<>();
}

public void push(int x) {
    stack1.push(x);			// Push onto stack1
}

public int pop() {
    peek();					// First call the peek function, to make sure stack 2 isn't
    return stack2.pop();	// empty. Then, the topmost element of stack2 is what we want
}

/** Get the front element. */
public int peek() {
    if (stack2.isEmpty()){			
        while (!stack1.isEmpty())
            stack2.push(stack1.pop());
    }
    return stack2.peek();	// stack2 is basically the queue, so return whatever's on the top
}

/** Returns whether the queue is empty. */
public boolean empty() {
    return stack1.isEmpty() && stack2.isEmpty();
}
```



### [Palindrome LinkedList](https://leetcode.com/problems/palindrome-linked-list/submissions/)

```JAVA
public boolean isPalindrome(ListNode head) {
    if (head == null || head.next == null)		// Size 0 or 1 list, must be unique.
        return true;
    if (head.next.next == null)					// Size 2 list, compare the head and tail
        return head.val == head.next.val;		// values

    ListNode middleNode = head;					// Standard Rabbit-Tortoise pointers.
    ListNode fastPointer = head;				// Fast pointer jumps twice so by the time
												// it reaches the end of the list, middlenode
    ListNode curr = head;						// is at the middle of the linkedlist.
    ListNode prev = null;
    ListNode nextNode;							// These three nodes are for reversing the 
												// first half of the list
    while (fastPointer != null && fastPointer.next != null){
        middleNode = middleNode.next;			// Advance middle once, fastpointer twice
        fastPointer = fastPointer.next.next;

        nextNode = curr.next;					// Reverse the curr node, but first store the
        curr.next = prev;						// next newNode. By doing this, we would have
        prev = curr;							// reversed exactly half of the list because
        curr = nextNode;						// fastpointer advacnes at double the speed.
    }

    if (fastPointer != null)					// If faspointer isn't null, then we have an
        middleNode = middleNode.next;			// odd length list, so advance middle once,
												// List looks like 1->2->3->2->1 instead of
    while (middleNode != null){					// 1->2->3->3->2->1
        if (middleNode.val != prev.val)			// While middle isn't null, check middlenode
            return false;						// val and prev val. Prev is basically the
        middleNode = middleNode.next;			// the point where the list reverses.
        prev = prev.next;						// Advance middle and next.
    }
    return true;								// Values matched, so return true.
}												// Reversed list looks like this:
												// 1<-2<-3<-prev middle->3->2->1 in even len
												// 1<-2<-prev middle->2->1 in odd lengths.
```



### [Delete Node in a Linked List](https://leetcode.com/problems/delete-node-in-a-linked-list/submissions/)

```java
public void deleteNode(ListNode node) {
    node.val = node.next.val;		// Node's value becomes its next node's value
    node.next = node.next.next;  	// Node's next is it's next's next.
}
```



### [Is Anagram](https://leetcode.com/problems/valid-anagram/submissions/)

```java
public boolean isAnagram(String s, String t) {
    if (s.length() != t.length())			// Can't be anagram if size aren't the same
        return false;
    int[] store = new int[26];				// Acts like a hashmap
    for (int i = 0; i < s.length(); i++)	// Increment the count by 1 in the store for the
        store[s.charAt(i)-'a']++;			// index = position of char in the alphabet
    for (int i = 0; i < t.length(); i++){	// Loop throught the second string, decrement
        if (--store[t.charAt(i)-'a'] < 0)	// count of each character in store by 1, but if
            return false;					// it goes below 0, then it means that character
    }										// occurred more than it did in s. So false.
    return true;							// Everything matched, so return true.
}
```



### [Binary Tree Paths](https://leetcode.com/problems/binary-tree-paths/)

```java
List<String> paths = new ArrayList<>();
public List<String> binaryTreePaths(TreeNode root) {
    if (root == null)					// No paths
        return paths;
    String rootval = root.val + "";		// Converting int to string.
    traverse(root, rootval);
    return paths;
}

private void traverse(TreeNode root, String s){
    if (root.left == null && root.right == null)		// It's a leaf, and you found a path
        paths.add(s);									// so add it to the list
    if (root.left != null)								// Left side is traversable, so
        traverse(root.left, s + "->" + root.left.val);	// visit it and record its value.
    if (root.right != null)								// Same as above, but for right side.
        traverse(root.right, s + "->" + root.right.val);
}
```



### [Add Digits](https://leetcode.com/problems/add-digits/)

```java
private int constantTime(int n){
    if (n < 10)
        return n;			// Already a single digit
    int result = n % 9;
    if (result == 0)		// If perfectly divisible by 9, then sum will be 9.
        return 9;
    return result;			// Otherwise, the result is going to be n % 9.
}

private int iterative(int num){
    while (num > 9){				// While number isn't between 2-9
        num = sumOfDigits(num);		// make num = sum of it's digits.
    }
    return num;
}

private int sumOfDigits(int n){		// Standard method to add the digits of a number.
    int sum = 0;
    while (n != 0){
        sum += n % 10;				// Extract the last digit, add it to sum.
        n /= 10;					// Divide the num by 10.
    }
    return sum;
}
```



### [Largest Perimeter Triangle](https://leetcode.com/problems/largest-perimeter-triangle/)

```java
public int largestPerimeter(int[] A) {
    Arrays.sort(A);							// Sort so the largest sides are at the end.
    for (int i = A.length-3; i >= 0; --i)	// Triangle inequality Theorem : a + b > c
        if (A[i] + A[i+1] > A[i+2])			// If sum of last two is greater than the last
            return A[i] + A[i+1] + A[i+2];	// we found out max perimeter, otherwise
    return 0;								// decrease i by i, then check the next three
}											// triplets
											// In the end if nothing works out, we return 0.
```



### [Ugly Number](https://leetcode.com/problems/ugly-number/submissions/)

```java
public boolean isUgly(int num) {
    if (num < 1)
        return false;		// Negative numbers are automatically non ugly
    while (num % 2 == 0)	// Keep dividing number by 2 till it is divisible
        num /= 2;
    while (num % 3 == 0)	// Keep dividing by 3
        num /= 3;
    while (num % 5 == 0)	// and 5
        num /= 5;
    return num == 1;		// If num isn't 1, that means that there are other prime factors
}							// except 2,3 and 5.
```



### [Missing Number](https://leetcode.com/problems/missing-number/)

```java
public int missingNumber(int[] nums) {			// Since it's given that the array contains
    int nsum = (nums.length*(nums.length+1))/2;	// all numbers from 0-n, we use the formula
    int arraySum = nums[0];						// to compute sum of n numbers.
    for (int i = 1; i < nums.length; i++)		// Then we loop through the array to compute
        arraySum += nums[i];					// the sum of the array.
    return nsum - arraySum;						// Subtract the array sum from the required
}												// sum, and that gives us the missing number
```



### [Is Bad Version](https://leetcode.com/problems/first-bad-version/submissions/)

```java
public int firstBadVersion(int n) {		// Basic Binary Search Algorithm
    int low = 1, high = n;
    int mid;
    while (low < high){
        mid = low + (high - low)/2;		// high - low to prefent integer overflow.
        if (isBadVersion(mid))			// if the model at mid was bad version, then we
            high = mid;					// could possibly have a bad version before it
        else
            low = mid+1;				// If it wasn't, then our first bad version lies
    }									// beyond the middle element.
    return low;
}
```



### [Move Zeroes](https://leetcode.com/problems/move-zeroes/solution/)

```java
/*
The general idea is that we know the end of the array is going to contain zeroes. So first, iterate over the array, if you find any non-zero value, copy it down to the front of the array. Then we you are done, length of the array minus the last index where you copied the non-zero element is the number of zeroes you need to fill in. So iterate from that last non-zero index to the end of the array and fill in zeroes.
*/
public void moveZeroes(int[] nums) {
    int lastNonZeroIndex = 0;
    for (int i = 0; i < nums.length; i++)
        if (nums[i] != 0)
            nums[lastNonZeroIndex++] = nums[i];
    for (int i = lastNonZeroIndex; i < nums.length; i++)
        nums[i] = 0;
}

/*
This solution is an extension of the above, but a better one because we only swap elements when needed and do not do any unnecessary writes. Start from the beginning of the array, maintain the last position of non-zero value you saw, and the current element. If you see a non-zero value, swap the current value with the index just after the last non-zero index you have, and then increment the non-zero index by 1 because you just found a new non-zero value. This helps us prepare for the next non-zero value we find and copy it at this index+1. By doing so, we are basically partitioning the array into non-zeroes and zero values.
*/
public void moveZeroes(int[] nums) {
    for (int lastNonZeroIndex = 0, i = 0; i < nums.length; i++){
        if (nums[i] != 0)
            swap(nums, i , lastNonZeroIndex++);
    }
}

private void swap(int[] a, int i, int j){
    int temp = a[i];
    a[i] = a[j];
    a[j] = temp;
}
```



### [Word Pattern](https://leetcode.com/problems/word-pattern/)

```java
public boolean wordPattern(String pattern, String str) {
    String[] words = str.split(" ");		// Split str into words
    if (pattern.length() != words.length)	// If length of pattern and words mismatch
        return false;						// then pattern do not match
    HashMap<Character, String> patternStore = new HashMap<>();	// Map pattern char to word
    HashMap<String, Character> wordMap = new HashMap<>();		// Map word to pattern char
    for (int i = 0; i < words.length; i++){
        char c = pattern.charAt(i);					// Get the char
        patternStore.putIfAbsent(c, words[i]);		// Put it in patternStore if absent
        if (!patternStore.get(c).equals(words[i]))	// If it was already there and it doesn't
            return false;							// map to words[i], we have a violation
        wordMap.putIfAbsent(words[i], c);			// Now check the other way around. If
        if (wordMap.get(words[i]) != c)				// words is absent in the map, map it to
            return false;							// the char. If present, then fetch it's
    }												// mapping and check if both match to c.
    return true;							// No violation, so return true
}
```



### [Can Win Nim](https://leetcode.com/problems/nim-game/)

```java
public boolean canWinNim(int n) {
    return n % 4 != 0;			// You can always win the game if n is not divisible by 4.
}
```



### [Power Of Three](https://leetcode.com/problems/power-of-three/)

```java
public boolean isPowerOfThree(int n) {
    if (n < 1)				// If negative, it can't be a power of 3.
        return false;
    while (n % 3 == 0)		// While n is divisible by 3, keep dividing it.
        n /= 3;
    return n == 1;			// In the end, if it was a power of 3, then n should be 1.
}
```



### [Power of Four](https://leetcode.com/problems/power-of-four/submissions/)

```java
/*
You can also use the iterative method that I have used in Power of Two and Power of Three problems. I just wanted to try a different approach here. This is a constant time function.
*/
public boolean isPowerOfFour(int num) {
    double pow = Math.log(num)/Math.log(4);	// Calculate x in 4^x = num using logs.
    return pow == (int)pow;					// Making sure that x is an integer and not a
}											// fractional exponent.
```



### [Reverse String](https://leetcode.com/problems/reverse-string/)

```java
/*
1 Liner solution. Basically, create a StringBuilder of the string, the builder already has a reverse method, so reverse it and then return it's toString.
*/

public String reverseString(String s) {
    return new StringBuilder(s).reverse().toString();
}

/*
Golfing aside, here is how one is expected to solve it in an interview.
*/

public String reverseString(String s) {
	char[] array = s.toCharArray();		// Create a char array of the string
	int len = array.length;				// length of the array
	for (int i = 0; i < len/2; i++){	// We only need to iterate over half the array.
		char temp = array[i];			// Swap the 0th index element with (len-1)th,
		array[i] = array[len-i-1];		// 1st index element with (len-2)th, until you get
		array[len-i-1] = temp;			// to the middle element.
	}
	return new String(array);			// Return a new string with the reversed array.
}
```



### [Implement strStr()](https://leetcode.com/problems/implement-strstr/submissions/)

```java
/*
The basic idea here is that you only need to iterate haystack length - needle length, and then check the substring of size = needle length in haystack from each index. If you are successfully able to match each character of the needle in the corresponding substring in haystack, return the index you start from. 
*/
public int strStr(String haystack, String needle) {
    if (needle.length() > haystack.length())	// Needle length can't be > than haystack
        return -1;
    int hl = haystack.length();
    int nl = needle.length();
    if (nl == 0)								// Empty strings are always a match starting
        return 0;								// from 0.
    for (int i = 0; i <= hl-nl; i++){			// Iterate haystack length - needle length.
        for (int j = 0; j < nl && haystack.charAt(i+j) == needle.charAt(j); ++j)}
            if (j == nl-1)						// We are checking how far from i can we
                return i;						// match. If i matched with j, increment j
        }										// and then match the character i+1 to j.
    }											// If that matches, increment j and match i+2
    return -1;									// j == n-1 checked wether or not if we were
}												// able to match the full needle string, if
												// yes, then i is our index
												// in the end, nothing matched, so return -1
```



### [Reverse Vowels of a String](https://leetcode.com/problems/reverse-vowels-of-a-string/)

```java
public String reverseVowels(String s) {
    if (s.length() < 2)
        return s;					// No need to reverse a string of length 0 or 1
    char[] str = s.toCharArray();	// Get the char array

    int left = 0;
    int right = str.length-1;

    while (left < right){
        while (left < right && !isVowel(str[left]))		// While left is pointing to a
            left++;										// consonant, increment it/
        while (left < right && !isVowel(str[right]))	// While right is pointing to a
            right--;									// consonant, decrement it.

        char temp = str[left];							// Left and right are now pointing
        str[left] = str[right];							// to vowels, so swap it.
        str[right] = temp;								// And then increment left and
        left++;											// decrement right to process the
        right--;										// inner string
    }
    return new String(str);			// Return a string from the reveresed array.
}

private boolean isVowel(char c){	// Function to check if a character is a vowel.
    switch (c) {
        case 'a':
        case 'e':
        case 'i':
        case 'o':
        case 'u':
        case 'A':
        case 'E':
        case 'I':
        case 'O':
        case 'U':
            return true;
        default:
            return false;
    }
}
```



### [Intersection of two arrays](https://leetcode.com/problems/intersection-of-two-arrays/)

```java
public int[] intersection(int[] nums1, int[] nums2) {
    Set<Integer> set1 = new HashSet<Integer>();		// Record all unique values in set 1
    for (int i: nums1)
        set1.add(i);
    Set<Integer> intersect = new HashSet<>();		// We will use it to record intersection
    for (int i: nums2)								// For each value in nums2 array
        if (set1.contains(i))						// If set1 contains it, we found an
            intersect.add(i);						// intersecting element, so add it.
    int[] res = new int[intersect.size()];			// We will now convert the set to an
    int i = 0;										// array and then return the array.
    for (int n: intersect)
        res[i++] = n;
    return res;
}
```



### [Is Perfect Square](https://leetcode.com/problems/valid-perfect-square/)

```java
/**
The basic idea here is to close in on the square root using binary search algorithm. 
I handle 4 seperately because it's root is the only one where 4/3 < it's square root. 
All other numbers square root is greater than its value/3.
So we create a lowerBound of 1 and an upperBound of num/3. Then if the middle value's square
overshoots, we make upperBound = mid-1, otherwise increment lowerBound to mid+1. This way, we
close on the square root from both sides, and if the middle values is the square root, it's
square will yield num.
*/
public boolean isPerfectSquare(int num) {
    if (num < 2 || num == 4)
        return true;
    long lowerBound = 1;
    long upperBound = num/3;
    long mid;
    long square;
    while (lowerBound <= upperBound){
        mid = lowerBound + (upperBound-lowerBound)/2;
        square = mid*mid;
        if (square == num)
            return true;
        if (square > num)
            upperBound = mid-1;
        else
            lowerBound = mid+1;
    }
    return false;
}
```



### [Sum of Two Integers](https://leetcode.com/problems/sum-of-two-integers/)

I cannot explain it better than this [post](https://leetcode.com/problems/sum-of-two-integers/discuss/132479/Simple-explanation-on-how-to-arrive-at-the-solution).

```java
public int getSum(int a, int b) {
    if (a == 0)
        return b;
    if (b == 0)
        return a;
    int sum = a ^ b;
    int carry = a & b;
    if (carry == 0)
        return sum;
    return getSum(sum, carry << 1);
}
```



### [Guess Number Higher or Lower](https://leetcode.com/problems/guess-number-higher-or-lower/)

```java
public int guessNumber(int n) {				// Standard binary search algorithm
    int low = 1, high = n, result = -2;		// Arbitrary result, but not 0
    int mid = 0;
    while (result != 0){
        mid = low + (high-low)/2;			// Check the mid.
        result = guess(mid);				// Check if our guess is correct
        if (result == -1)					// If result == -1, then we overshot
            high = mid-1;					// So we can discard all values > mid
        else if (result == 1)				// If result == 1, we undershot
            low = mid+1;					// Need to discard all the values < mid
    }
    return mid;								// Result == 0, so return the mid.
}
```



### [Ransom Note](https://leetcode.com/problems/ransom-note/submissions/)

```java
public boolean canConstruct(String ransomNote, String magazine) {
    int[] store = new int[26];
    for (char c: magazine.toCharArray())		// First, fill the store with available
        store[c-'a']++;							// characters from the magazine
    for (char c: ransomNote.toCharArray())		// Then, scan through the note, decrement
        if (--store[c-'a'] < 0)					// each char's index by 1 because we used
            return false;						// it. If it's frequency drops below 0,
    return true;								// then it means that we need more chars
}												// than available. In the end, return
												// true if everything worked out.
```



### [First Unique Character in a String](https://leetcode.com/problems/first-unique-character-in-a-string/submissions/)

```java
public int firstUniqChar(String s) {
    int[] freq = new int[26];			// Preprocess freq array to maintain freq of each
    char[] chars = s.toCharArray();		// character in the string s
    for (char c: chars)
        ++freq[c-'a'];
    for (int i = 0; i < chars.length; i++)	// Make a second pass through the chars of the
        if (freq[chars[i]-'a'] == 1)		// string in order, and if any of the char's
            return i;						// frequency is 1, that's our unique char
    return -1;								// Otherwise, no unique character
}
```



### [Find the Difference](https://leetcode.com/problems/find-the-difference/)

```java
/**
The general idea here is same as the problem where we are required to find a unique int
in an array containing duplicates except one. We use the xor operator between each character
of the string s and t, and the ones that are duplicate will xor to give 0. XOR of any element
with 0 is the element itself, and XOR of two same elements gives 0. This way, since string s
and t basically has pairs of repeating characters except one, the unique element will XOR
with 0 and give us it's ASCII code. The only thing we need to take care of is to now shift it
up by 26, so we add 'a' and convert it to char.
*/
public char findTheDifference(String s, String t) {
    int xor = 0;
    for (char c: s.toCharArray())
        xor ^= c-'a';
    for (char c: t.toCharArray())
        xor ^= c-'a';
    return (char)(xor+'a');
}
```



### [Nth Digit](https://leetcode.com/problems/nth-digit/)

```java
/**
Notice that # of digits between 0-9 is 1*9, 10-99 is 2*90, 100-999 is 3*900. If we generalize
it, it is exactly equal to 9 * (num of digits in the number) * 10^{# of digits - 1}.
*/
public int findNthDigit(int n) {
    if (n < 10)
        return n;
    int pow = 1;				// First we need to figure out how many digits there are
    long upperBound = 9;		// in the number.
    while (n > upperBound){
        n -= upperBound;		// If n is a two digit number, subtract the 9 single digit
        ++pow;					// numbers, if 3 digit, subtract the first 189 digits.
        upperBound = (long)Math.pow(10, pow-1) * pow * 9;
    }							// pow allows us to track how many digits there are in num.

    int num = (int)Math.pow(10,pow-1) + (n-1)/pow;		// Calculate which number we want
    int position = pow - 1 - (n-1) % pow;				// Calculate which index we want
    for (int i = 0; i < position; i++)					// Divide num that many times
        num /= 10;
    return num % 10;									// num % 10 gives us that digit.
}
```



### [Sum of Left Leaves](https://leetcode.com/problems/sum-of-left-leaves/)

```java
public int sumOfLeftLeaves(TreeNode root) {
    if (root == null)		// Empty tree, therefore total is 0.
        return 0;
    int sum = 0;			// Initialize sum.
    // Look ahead and check. If left is not null but left is a leaf, then sum is the value of the left leaf.
    // But if left is null or left is an inner node, then we need to explore it, so sum is whatever the subtree from the left node returns.
    if (root.left != null && root.left.left == null && root.left.right == null)
        sum = root.left.val;
    else
        sum = sumOfLeftLeaves(root.left);
    // We computed the sum of the left side. Now we need to traverse the right side and fetch
    // the sum, so total sum is sum of the left side as computed above + sum returned by
    // traversing the right side.
    return sum + sumOfLeftLeaves(root.right);
}
```



### [Longest Palindrome](https://leetcode.com/problems/longest-palindrome/)

```java
public int longestPalindrome(String s) {
    int[] freq = new int[128];		// To record the frequency of each char
    for (char c: s.toCharArray())
        freq[c]++;					// Increment count by 1 for each character observed
    int len = 0;					// length of the longest palindrome
    boolean isOdd = false;			// Check if our palindrome length is odd
    for (int i = 0; i < 128; i++){	// Go through each character's index
        if (freq[i] != 0){			// Only if it has been observed atleast once
            int val = freq[i];		// Store it's frequency
            int used;				// Record how many of it's occurrences we will use
            if (val % 2 == 0)		// If a perfect multiple of 2, we will use all
                used = val;
            else{
                used = val-1;		// If odd occurrences, then the max we can use to form a
                isOdd = true;		// valid palindrome is val-1. It also tells us that the
            }						// palindrome is going to be of odd length.
            len += used;			// Finally, increment length by the number of chars used
        }
    }
    if (isOdd)						// If length is odd, we can always insert any single
        return len+1;				// character in the middle to keep the palindrome valid.
    return len;						// If the length is even, then we can't do anything.
}
```



### [Fizz Buzz](https://leetcode.com/problems/fizz-buzz/)

```java
public List<String> fizzBuzz(int n) {
    List<String> nums = new ArrayList<String>();
    for (int i = 1; i <= n; ++i){				// Loop from 1 to n
        if (i % 15 == 0)						// If i divisible by 15, add "FizzBuzz"
            nums.add("FizzBuzz");
        else if (i % 5 == 0)					// i's not a multiple of 15, check if it's a
            nums.add("Buzz");					// multiple of 5. If so, add "Buzz"
        else if (i % 3 == 0)					// i's not a multiple of 5, check if it's a
            nums.add("Fizz");					// multiple of 3, if so, add "Fizz"
        else
            nums.add(i+"");						// Otherwise, just add the String type of the
    }											// number
    return nums;
}
```



### [Third maximum Number](https://leetcode.com/problems/third-maximum-number/)

```java
public int thirdMax(int[] nums) {
    if (nums.length == 0)		// Empty array
        return 0;
    if (nums.length == 1)		// Size 1 array
        return nums[0];
    if (nums.length == 2)		// Size 2 array, check between 0th element or 1st element
        return nums[0] > nums[1] ? nums[0] : nums[1];
    long firstMax = Long.MIN_VALUE;		// Lowest values for all three
    long secondMax = Long.MIN_VALUE;
    long thirdMax = Long.MIN_VALUE;
    for (int i: nums){					// For each number in the array
        if (i > firstMax){				// If num > than the largest, then old largest
            thirdMax = secondMax;		// becomes second largest and second largest becomes
            secondMax = firstMax;		// first largest, then update the largest.
            firstMax = i;
        }
        else if (i > secondMax && i != firstMax){	// If num > second and num is not is the
            thirdMax = secondMax;					// same as first, first largets becomes
            secondMax = i;							// second largest and update the second
        }
        else if (i > thirdMax && i != secondMax && i != firstMax) // // If num > third, we
            	thirdMax = i;						// need to check that it is not the same
    }												// as the first and second largest.
    if (thirdMax == Long.MIN_VALUE)					// This check allows us to make sure that
        return (int)firstMax;						// we do indeed have a third max and is
    return (int)thirdMax;							// not what we initialized initially.
}
```



### [Add Two Strings](https://leetcode.com/problems/add-strings/)

```java
public String addStrings(String num1, String num2) {
    if (num1.equals("0"))
        return num2;
    if (num2.equals("0"))
        return num1;
    
    /** We use a char array to maintain the digit at each index. We want the array to be of
    the size of the largest string + 1 to handle carry bit if any at the end. We start
    adding each digit of the string from the end, and place it in it's correct index at the
    end of the sum array. This way, we avoid reversing it and return the answer in constant
    time. Take care to convert the digit you compute by adding '0'. Lastly, if the carry bit
    is 1, we need to make the 0th index as 1, and return the string by using the sum array.
    If it's not 1, then the sum array has a leading 0 which we don't want. So we use Java's
    String constructor that takes in the char array, startingIndex in that array and the
    number of elements of that array we want. So if the carry isn't 1, we technically want
    everything from index 1 and # of elements = sum.length - 1 because we discard 0 index.
    */
    char[] sum = new char[1 + Math.max(num1.length(), num2.length())];
    int index = sum.length-1, idx1 = num1.length()-1, idx2 = num2.length()-1, carry = 0, total = 0;
    int n1, n2;
    while (idx1 >= 0 || idx2 >= 0){
        n1 = idx1 < 0 ? 0 : num1.charAt(idx1--)-'0';
        n2 = idx2 < 0 ? 0 : num2.charAt(idx2--)-'0';
        total = n1 + n2 + carry;
        carry = total/10;
        sum[index--] = (char)(total % 10 + '0');
    }
    if (carry == 1){
        sum[0] = '1';
        return new String(sum);
    }
    return new String(sum, 1, sum.length-1);
}
```



### [Construct Quad Tree](https://leetcode.com/problems/construct-quad-tree/)

```java
private int[][] grid;					// Store it once, instead of passing it over & over.
public Node construct(int[][] _grid) {
    grid = _grid;
    return helper(0,0,grid.length);		// Ask helper to build the tree.
}

private Node helper(int top, int left, int len){
    if (len <= 0)						// Base case: if empty grid or if we are done
        return null;					// checking the full grid, return null
    int key = grid[top][left];			// Get the topleft value, and start checking the box
    for (int i = 0; i < len; ++i){		// of len*len. If at any point, the value doesn't
        for (int j = 0; j < len; ++j){	// match the key, we have found a breakpoint from
            if (grid[top+i][left+j] != key){	// where we need to break the grid into four
                int offset = len/2;		// grids, each of len = len/2. The topleft grid has
                return new Node(true, false, 	// the same top and left point, the topright
                                helper(top,left, offset),	// grid has left point shifted to
                                helper(top, left + offset, offset),	// the right by offset.
                                helper(top+offset, left, offset),	// The bottom left grid
                                helper(top+offset, left+offset, offset));	// is shifted
            }	// downwards by offset with the same left point. The bottom right grid will
        }		// have an index where it's top is shifted down by len/2 and left by left/2.
    }			// We know that the node will have a value = true if 1 else false and it won't be a leaf, so true, false, topleft, topright, bottomleft, bottomright.
    return new Node(key == 1, true, null, null, null, null);	// Everything passed, so we return a new Node whose value is true if key is 1, else false and it will be a leaf, with
// no children, so 4 nulls.
}
```



### [N-ary Tree Level Order Traversal](https://leetcode.com/problems/n-ary-tree-level-order-traversal/)

```java
public List<List<Integer>> levelOrder(Node root) {
    List<List<Integer>> res = new ArrayList<>();	// Result list
    if (root == null)								// If root is null, return empty list.
        return res;
    Queue<Node> q = new LinkedList<>();				// BFS Queue. Add the root.
    q.add(root);
    while (!q.isEmpty()){							// While q isn't empty
        int size = q.size();						// Check how many elements in that level
        List<Integer> level = new ArrayList<>(size);// level list to store elements.

        for (int i = 0; i < size; i++){				// Remove each node for whatever the size
            Node n = q.poll();						// Add that node's value and add all of
            level.add(n.val);						// its children to the queue.
            for (Node child: n.children)
                q.add(child);
        }
        res.add(level);								// Add the level array to the result
    }
    return res;										// Return the result list.
}
```



### [Number of Segments in a String](https://leetcode.com/problems/number-of-segments-in-a-string/)

```java
public int countSegments(String s) {
    if (s.length() == 0)					// Empty String
        return 0;

    int segments = 0;						// Record segments

    char prev = s.charAt(0);				// We will compare adjacent characters.
    for (int i = 1; i < s.length(); ++i){	// Start looking at chars from index 0
        char curr = s.charAt(i);			// Get the current char
        if (prev != ' ' && curr == ' ')		// If previous char wasn't a space but the
            ++segments;						// current char is, we found a segment.
        prev = curr;						// Make previous = current for next iteration
    }
/**
This line is important. If prev was an empty space, that means that all we have been looking
at was empty spaces towards the end. So return whatever segments we found in the beginning
of the string. But if prev wasn't a space, that means the char next to prev might have been
an empty space or just a normal character. In any case, we would want to include that last
segment, so we return segment+1.
*/
    return prev == ' ' ? segments : segments+1;
}
```



### [Binary Tree Level Order Traversal](https://leetcode.com/problems/binary-tree-level-order-traversal/)

```java
public List<List<Integer>> levelOrder(TreeNode root) {
    List<List<Integer>> result = new ArrayList<>();
    if (root == null)						// Empty Tree
        return result;
    Queue<TreeNode> q = new LinkedList<>();	// BFS Queue
    q.add(root);
    while (!q.isEmpty()){					// While we have something to process
        List<Integer> level = new ArrayList<>();
        int size = q.size();				// Check how many elements at the current level
        for (int i = 0; i < size; i++){
            TreeNode node = q.poll();		// Remove one element each time
            if (node != null){				// If not null, add it's val to the level list,
                level.add(node.val);		// and it's left and right children to the queue
                q.add(node.left);			// to process in order
                q.add(node.right);
            }
        }
        if (!level.isEmpty())				// If level list wasn't empty,
            result.add(level);				// add it to the result list.
    }
    return result;
}
```



### [Path Sum III](https://leetcode.com/problems/path-sum-iii/submissions/)

```java
HashMap<Integer, Integer> sumToWays;			// Record how many ways there are to form sum
int ways;										// Total number of ways.
public int pathSum(TreeNode root, int sum) {
    sumToWays = new HashMap<>();
    ways = 0;
    sumToWays.put(0,1);							// 1 way to form a sum of 0.
    helper(root, 0, sum);
    return ways;
}

/**
The idea here is as follows. Start with the root node, and keep a running total. We maintain
how many ways there to form a running sum. Then we check how many ways there are to form
(running sum) - (sum we are looking for). If there is a way to form it, then we increase the
number of ways to form sum. We then have to update the map to record how many ways can the
running sum be formed. If it's something we could form before, increment it, or else set it
to 1. Now, traverse the left side and then the right side. In the end, for each time we
incremented the count for a running sum, we need to decrement it because we are backtracking.
We are first going down, incrementing the count for runningSum, then we move up and decrement
it by 1 for each time we observed it. This is to maintain the Pre-Order traversal.
*/
private void helper(TreeNode node, int runningSum, int sum){
    if (node == null)
        return;
    runningSum += node.val;
    ways += sumToWays.getOrDefault(runningSum-sum, 0);
    sumToWays.put(runningSum, sumToWays.getOrDefault(runningSum, 0)+1);

    helper(node.left, runningSum, sum);
    helper(node.right, runningSum, sum);

    sumToWays.put(runningSum, sumToWays.get(runningSum)-1);
}
```



### [Find All Anagrams in a String](https://leetcode.com/problems/find-all-anagrams-in-a-string/)

```java
public List<Integer> findAnagrams(String s, String p) {
    List<Integer> result = new ArrayList<>();
    int start = 0, end = 0, slen = s.length(), plen = p.length();
    if (slen == 0 || slen < plen || plen == 0)
        return result;
    int[] freq = new int[26];				// Store the freq of chars in p
    for (char c: p.toCharArray())
        freq[c-'a']++;
    char[] sArr = s.toCharArray();			// Get the chars of the string s as an array
    while (end < slen){						// While everything is not processed
        if (--freq[sArr[end]-'a'] >= 0)		// decrease the freq of the char at index end
            plen--;							// if it's > 0, then we matched something in p
											// so decrease plen by 1.
        while (plen == 0){					// If plen goes to 0, we were able to match all
            if (end-start+1 == p.length())	// chars of p. If length of the matched chars is
                result.add(start);			// equal to length p, we found a start point.
            if (freq[sArr[start]-'a'] >= 0)	// Check if the freq of char at start index is
                plen++;						// >= 0. If it is, shift the window to the right
            ++freq[sArr[start++]-'a'];		// but first restore the frequency of the char
        }									// at the index start.

        end++;								// Get ready to inspect the new element
    }

    return result;							// Return the answer.
}
```



### [Arranging Coins](https://leetcode.com/problems/arranging-coins/)

The idea is as follows. Sum of first n numbers is given by $\frac{n^2+n}{2}​$. We need to find $n​$ such that sum of $n​$ numbers is closest to the number of coins we have. That is, $\frac{n^2+n}{2} = k​$ where $k​$ is the number of coins we have. So, everything boils down to solving the quadratic equation $n^2 + n - 2k = 0​$. We use the quadratic formula where for any quadratic equation $ax^2 -bx + c​$ is solved substituting for $a​$, $b​$ and $c​$ in $x = \frac{-b \pm \sqrt{b^2 - 4ac}}{2}​$. Here, $a​$ and $b​$ are always going to be 1, while $c​$ is always going to be $2k​$. Substitute those, and solve the equation.

```java
public int arrangeCoins(int n) {
    // return solveQuadratic(n);
    return iterative(n);
}

private int solveQuadratic(int n){
    return (int)(Math.sqrt(1 + 8*(long)n)-1)/2;
}

private int iterative(int n){
    int used = 1, level = 0;		// Coins used, and level completed.
    while (n > 0){					// While coins left are greater than 0.
        n-=used;					// Calculcate remaining coins.
        if (n > -1)					// If there are still some coins left,
            ++level;				// we were able to fill the level.
        ++used;						// Prepare used for the next level, which is plus 1.
    }
    return level;					// Return level
}
```



### [Hamming Distance](https://leetcode.com/problems/hamming-distance/)

```java
public int hammingDistance(int x, int y) {
    int diff = 0;				// Track differences
    while (x != 0 || y != 0) {	// While both of them aren't 0
        if (x % 2 != y % 2)		// Check the bit of x and y by mod 2. If they are unequal
            diff++;				// increment difference.
        x /= 2;					// Divide x and y by 2.
        y /= 2;
    }
    return diff;
}
```



### [String Compression](https://leetcode.com/problems/string-compression/)

```java
public int compress(char[] chars) {
    int len = chars.length;			// No need to reverse array of length 0 or 1
    if (len < 2)
        return len;
    int arrayIndex = 0;				// To maintain the length of new array.
    int start = 0;					// start index
    int end = 0;					// end index
    while (end < len){
        char first = chars[start];	// Record the char we are looking at.
        int count = 0;				// count is 0.
        while (end < len && chars[end] == first){	// while the char is the same
            ++end;					// increment end to check next char
            ++count;				// and increment the count.
        }
        start = end;				// shift start to end to check next sequence of chars
        chars[arrayIndex++] = first;	// our arrayIndex points to to the new array's 
        if (count != 1){				// indices. So copy the first char to arrayIndex.
            if (count > 1 && count < 10)	//Only if count isn't 1, if count is less than 10
                chars[arrayIndex++] = (char)(count+'0');	// then we simply convert count to char and write it next to the char we just overwrote.
            else						// Otherwise, it has many digits. So convert it to
                for (char c: String.valueOf(count).toCharArray()){	// string and add all it's digit to the array one by one while increment arrayIndex.
                    chars[arrayIndex++] = c;
            }
        }
    }
    return arrayIndex;			// Wherever arrayIndex is, is the new length for the array.
}
```



### [Number of Boomerangs](https://leetcode.com/problems/number-of-boomerangs/)

```java
public int numberOfBoomerangs(int[][] points) {
    int boomerangs = 0;
    HashMap<Double, Integer> map = new HashMap<>();	// To record points with same dist
    for (int[] i: points){		// Compute distance between one point and every other.
        map.clear()				// clear map before each relative distance computation
        for (int[] j: points){	// Compute distance with other points
            if (i == j)			// Don't compare the same two points.
                continue;
            double dist = Math.sqrt(Math.pow(i[0]-j[0],2) + Math.pow(i[1]-j[1],2));
            int prevCount = map.getOrDefault(dist, 0);	// Check how many points are equidistant from point i.
            boomerangs += prevCount * 2;	//  Number of boomerangs = whatever pairs there were before times 2, because you can form twice the number of different orders.
            map.put(dist, prevCount+1);	// Increase the count of points observed for that distance.
        }
    }
    return boomerangs;	// return number of boomerangs
}
```



### [Find All Numbers Disappeared in an Array](https://leetcode.com/problems/find-all-numbers-disappeared-in-an-array/)

```java
/**
The idea is simple. For each number in the array, since it's gauranteed that that the values
lie are inclusive [1,n], we can look at the index value-1. So check that index, and mark
that value as negative. That is why I take the absolute value. Check value at that index, if
negative, it means we have visited it via some other duplicate value. But if it's positive,
then we are seeing it for the first time, so make it's value negative. Make a second pass.
For values that are still positive, that means those indices were never visited, hence left
positive. So add 1 to them and add it to the set. Eg:
	Given array a = [4,3,2,7,8,2,3,1],
1.	val = 4 => idx = 3 & a[3] > 0, therefore, a[3] *= -1
	a = [4,3,2,-7,8,2,3,1]
2.	val = 3 => idx = 2 & a[2] > 0, therefore a[2] *= -1
	a = [4,3,-2,-7,8,2,3,1]
3.	val = -2 => idx = abs(-2)-1 = 1 & a[1] > 0, therefore a[1] *= -1
	a = [4,-3,-2,-7,8,2,3,1]
4.	val = -7 => idx = abs(-7)-1 = 6 & a[6] > 0, therfore a[6] *= -1
	a = [4,-3,-2,-7,8,2,-3,1]
5.	val = 8 => idx = abs(8)-1 = 7 & a[7] > 0, therfore a[7] *= -1
	a = [4,-3,-2,-7,8,2,-3,-1]
6.	val = 2 => idx = 1 but a[2] < 0. No change.
7.	val = -3 => idx = abs(-3)-1 = 2 but a[2] < 0. No change.
8.	val = -1 => idx = abs(-1)-1 = 0 & a[0] > 0, therefore a[0] *= -1
	a = [-4,-3,-2,-7,8,2,-3,-1]
Observation: Notice index 4 and 5 have positive values, since those values were never
encountered, so the values at those indexes never became negative. Hence missing values are
5 and 6.
*/
public List<Integer> findDisappearedNumbers(int[] nums) {
    List<Integer> result = new ArrayList<>();
    for (int i: nums){				// For each number in the array
        int idx = Math.abs(i)-1;	// Look at the index that the number corresponds to
        if (nums[idx] > 0)			// If val is -ve, then it means we have encountered it.
            nums[idx] *= -1;  		// If not, make it -ve.
    }
    for (int i = 0; i < nums.length; ++i)
        if (nums[i] > 0)			// Make another pass through the array, and the indices
            result.add(i+1);		// where value was positive, index+1 was missing from
    return result;					// the array
}
```



### [Assign Cookies](https://leetcode.com/problems/assign-cookies/)

```java
/**
We will employ a greedy algorithm where we first try to content children whose requirements
are small. We do this by sorting both the arrays, so we can match the child with least
requirement with the smallest cookie available.
*/
public int findContentChildren(int[] g, int[] s) {
    Arrays.sort(g);
    Arrays.sort(s);
    int satisfied = 0, i = 0, j = 0;
    while (i < g.length && j < s.length){	// While children are left and we have cookies,
        if (s[j] >= g[i]){		// Check if the cookie at index j >= child i's requirement
            satisfied++;		// If so, increment the number of content child and we will
            i++;				// process the next child.
        }						// If cookie j < child i's demand, check the next cookie by
        j++;					// incrementing j. If cookie j > child i's demand, we will
    }							// still need to increment j, hence outside the conditional.
    return satisfied;			// Return number of satisfied children
}
```



### [Poor Pigs](https://leetcode.com/problems/poor-pigs/)

[Link](https://leetcode.com/problems/poor-pigs/discuss/94305/1-line-solution-with-detailed-problem-clarification-and-math-proof-(please-read-if-you-really-want-to-know-what-this-problem-means)) to the solution explanation. This problem is phrased poorly and I had to read the comments by other users to understand what it required from me. The link I marked here explains the logic pretty good. But the simple logic is this: The number of rounds $r = \frac{Total Test Time}{Minutes To Die} +1$. Each pig has chances of dying in each round or staying alive till the end, so we plus 1. Now given the number of rounds $r$ and the number of samples $s$, how many volunteers $v$ will you need? $r^v = s$. Each round has some volunteers which in total at the end should be able to test out all the samples. Therefore, $v =\log_rs$.

```java
public int poorPigs(int buckets, int minutesToDie, int minutesToTest) {
    int base = minutesToTest/minutesToDie+1;		// How many rounds can you perform?
    return (int)Math.ceil(Math.log(buckets)/Math.log(base));
}
```



### [Find Pivot Index](https://leetcode.com/problems/find-pivot-index/)

```java
public int pivotIndex(int[] nums) {
    int sum = 0, leftSum = 0;		// We will test each index as a pivot by sliding it ->
    for (int i: nums)				// Precalculate the sum of the array
        sum += i;
    for (int i = 0; i < nums.length; ++i){	// Check if the sum of the leftSide of i is
        if (leftSum == sum - leftSum - nums[i])	// equal to totalSum - leftSideSum - pivot
            return i;							// which is i. If so, return i.
        leftSum += nums[i];						// Otherwise add nums[i] to the leftSum and
    }											// slide pivot to the ->.
    return -1;								// No pivot found. Return -1.
}
```



### [Squares of a Sorted Array](https://leetcode.com/problems/squares-of-a-sorted-array/)

```java
public int[] sortedSquares(int[] A) {
    int len = A.length;		// Length of array A
    int pivot = 0;			// Pivot is the index where values goes from -ve to +ve.
    while (pivot < len && A[pivot] < 0) // While values are -ve.
        ++pivot;			// increment pivot. We exit when we find a positive.
    int[] squares = new int[len];	// Result array
    int index = 0;			// Keeps track of where to where to put elements in result array
    if (pivot == 0)			// pivot = 0 means pivot didn't shift, there are only +ve values
        for (int i: A)		// So fill in the array with squares of numbers.
            squares[index++] = i*i;
    else{					// Otherwise we have a negative somewhere.
        int left = pivot-1;	// So we will compare values left and right of the pivot
        int right = pivot;	// and whichever's smaller fills up the array first.
        while (left > -1 && right < len){
            int lsquare = A[left] * A[left];
            int rsquare = A[right] * A[right];
            if (lsquare < rsquare){		// left < right, so add left square. decrement left
                squares[index++] = lsquare;
                --left;
            }
            else if (rsquare < lsquare){	// right < left, add right square and increment.
                squares[index++] = rsquare;
                ++right;
            }
            else{
                squares[index++] = lsquare;	// both are equal. add both square and
                squares[index++] = rsquare;	// decrement left, increment right.
                --left;						// Continue doing this until we hit either end
                ++right;					// of the array.
            }								// In the end we need to check if elements on
        }									// either side are left to be filled in.
        while (left > -1)					// Left side elements remain, so fill their
            squares[index++] = A[left] * A[left--]; // squares one by one till none left.
        while (right < len)					// Right side elements remain, so fill their
            squares[index++] = A[right] * A[right++];	// squares in
    }
    return squares;
}
```



### [Repeated Substring Pattern](https://leetcode.com/problems/repeated-substring-pattern/submissions/)

We use the [KMP Algorithm](https://www.geeksforgeeks.org/kmp-algorithm-for-pattern-searching/) that allows us to match a string 's' with another string 'p' to find the longest sequence of characters in 's' that match 'p'. We can use a Naive Pattern match where we start from the beginning of the string and start comparing the characters of 's' with 'p'. Initially, we keep the partition at index 0. If the character's match, we move partition to the right by 1 till we get to the end of the string. If something doesn't match, we don't move the partition but look at the next character to match. In the end, wherever the partition is, that's our longest length we could match with string 'p'. The complexity of that is <i>O(len(p)(len(s)-len(p)+1))</i>.

KMP fixes it by skipping characters that we know already match. In this problem, we aren't matching with any other string but itself. So, we start from index 1 of the string and compare it from the beginning. If they match, we increase j by 1, note it down in lps array and then increase i by 1 to check the next character. j basically measures the longest chain of characters we were able to match. If we couldn't match character at index i and if streak was greater than 0, then our new streak becomes whatever it was in the previous round of matching characters. If the streak is 0, then we simply note down at index i in our lps array 0, meaning longest length measured upto index i was 0. 

```java
public boolean repeatedSubstringPattern(String s) {
        int maxLength = lps(s);
        return maxLength > 0 && s.length() % (s.length() - maxLength) == 0;
    }

private int lps(String s){
    int len = s.length();
    int[] lps = new int[len];
    int i = 1;		// To match the string with itself.
    int j = 0;
    while (i < len){
        if (s.charAt(i) == s.charAt(j)){	// if the chars match
            lps[i] = ++j;					// we record that # of matches at index i was
            ++i;							// 1+j and increment i to check next character
        }
        else{								// character did not match
            if (j > 0)						// If our matching streak > 0
                j = lps[j-1];				// our new streak becomes the previous round's streak
            else							// Otherwise, streak is already 0.
                lps[i++] = 0;				// So we record that # of matches made at i is 0
        }									// We increment i to check next index.
    }
    return lps[len-1];						// Longest prefix length that was also a suffix
}											// is whatever was recorded at the end of array.
```



### [Island Perimeter](https://leetcode.com/problems/island-perimeter/)

The idea is simple. Count the number of cells with value 1 which denotes the land. Check towards the left and up to that cell and check if it shares any edge with another cell with value 1. If it does record that. In the end, the formula for perimeter is 4 * (the number of land cells) - 2 * (overlapping edges).

**Reasoning:** Perimeter of a square is 4 times the length of it's side. Here all squares are of length 1. So total perimeter is 4*(number of cells with value = 1). But we also need to account the edges that are common between two adjacent land cells. If one square shares an edge with another, we just lost one side from both the square, resulting in a loss of two sides. Therefore, we need to subtract twice the number of overlapping edges from the total perimeter to get  the total perimeter. 

```java
public int islandPerimeter(int[][] grid) {
    int land = 0;
    int overlap = 0;
    for (int row = 0; row < grid.length; ++row)
        for (int col = 0; col < grid[0].length; ++col){
            if (grid[row][col] == 1){
                ++land;
                if (row-1 > -1 && grid[row-1][col] == 1)	// Check above the current cell.
                    ++overlap;		// If it's a land, we need to record one overlap.
                if (col-1 > -1 && grid[row][col-1] == 1)	// Similarly, check to the left.
                    ++overlap;		// If it's a land, we need to increment overlap
            }
        }
    return 4*land - 2*overlap;		// Check the reasoning above.
}
```



### [Number Complement](https://leetcode.com/problems/number-complement/)

```java
public int findComplement(int num) {
    int pow2 = 1;				// Easily keep track of power of 2.
    int comp = 0;				// Complement number
    while (num != 0){			// Since num gets divided by 2, it will be 0 in the end.
        int bit = num % 2 == 0 ? 1 : 0;	// If bit is 0 then complement is 1 & vice versa.
        comp += bit * pow2;		// Multiply it by the appropriate power of 2 and add to comp
        pow2 *= 2;				// Update power of 2 for next iteration.
        num /= 2;				// Divide num by 2 to get the next bit.
    }
    return comp;				// Comp is now the complement.
}
```



### [Binary Watch](https://leetcode.com/problems/binary-watch/)

The idea is as follows. We have 10 lights. First 4 represent hours. Namely 1, 2, 4 and 8, which are the first four powers of 2. The next 6 lights, represent minutes. Those are 1, 2, 4, 8, 16 and 32. These are powers of 2 from 0-5. So if we iterate from 1 to 9, powers of numbers 1-3 gives us hours and powers of numbers 4-9 minus 4 gives us minutes. So, if we have, let's say 2 lights, we need to find every combination of 2 lights. So in our helper function, we iterate from 1-9 to check every hour and minute combination. We also need to keep a track of the lights that we used, so we don't use the same light again. If hours are > 11 or minutes are > 59, we have an invalid time and we can abort. If the number of lights are 0, that means we found a valid time and we should add it to the result. Now, if the lights are not 0, then we need to check every possible combination from the last light used to 9. If i < 4, then we are looking at an hourly combination, otherwise it's a minute combination. So we recurse with updated lights used, decrease the numOfLights since we used one, update respective hours or minutes until we hit base case.

```java
List<String> result;

public List<String> readBinaryWatch(int num) {
    result = new ArrayList<>();
    helper(0, num, 0, 0);
    return result;
}

private void helper(int lightsUsed, int numOfLights, int hrs, int min){
    if (hrs > 11 || min > 59)		// Base case. Invalid time
        return;

    if (numOfLights == 0){			// All lights used, so add time to the list.
        result.add(hrs + ":" + (min < 10 ? "0" + min : min));
        return;
    }
    for (int i = lightsUsed; i < 10; i++){	// Otherwise start recursing from number of prev
        if (i < 4)							// light used. i < 4 means hours
            helper(i+1, numOfLights-1, hrs + (int)Math.pow(2, i), min);
        else								// i = [4,9] means minute. So recurse.
            helper(i+1, numOfLights-1, hrs, min + (int)Math.pow(2,i-4));
    }
}
```



### [Minimum Moves to Equal Array Elements](https://leetcode.com/problems/minimum-moves-to-equal-array-elements/)

This was an interesting problem. But after working out a few examples by hand, you can notice that it is always a question of bringing the minimum element in par with everyone. So if you know the minimum of the array, we can check how many steps it will take to bring the minimum in par with other element by calculating the distance between them. For example,

> Let the array be [1,2,3]
>
> We can observe that the minimum here is 1. Let us list down all steps to make all elements equal.
>
> 1. [2,2,4], Keeping the second element fixed. Notice that distance between the element where 1 was and where 3 was is till the same.
> 2. [3,3,4], Keeping the last element fixed.
> 3. [4,4,4], Keeping last element fixed.
>
> Here, we first tried to make 1 equal to it's neighbor, which required us 1 step. Now, once it becomes equal to 1, the problem is how to make the last element in the original array, which is 3 equal to 1. It requires 2 steps, resulting in a total of of 3. The reason is that the moment you decide to increment the minimum element to match the next element, you fix the neighboring element and have to increment everything else. This will make the minimum and its neighbor the same, but it will also keep the distance between the minimum and all other elements the same because we just incremented everything. 
>
> So, the total number of moves required is the distance between the elements of the array and the minimum.

```java
public int minMoves(int[] nums){
    int min = nums[0];
    for (int i: nums)
        if (i < min)
            min = i;
    int moves = 0;
    for (int i: nums)
        moves += i-min;
    return moves;
}
```

> Now the above solution required two passes of the array. Can we do even better? Notice that in the end, all we are doing is finding the min and subtracting min from all the elements in the array. That means we are subtracting min *n* times where n is the length of the array. Why *n* times? Because there are *n* elements in the array. Shouldn't it be *(n-1)* times? No, because the distance of the min from min is 0. So we need to subtract min from itself too, so *n* times. We can achieve this by first calculating the total of the array while simultaneously keeping track of the minimum. Once done, all we need to do is subtract min *n* times from the sum, which is equivalent to subtracting min from each element. This results in a much overall better algorithm, requiring only 1 pass of the array.

```java
public int minMoves(int[] nums) {
    int sum = 0, min = nums[0];
    for (int i: nums){
        sum += i;
        if (i < min)
            min = i;
    }
    return sum - min*nums.length;
}
```



### [License Key Formatting](https://leetcode.com/problems/license-key-formatting/)

The idea is simple. 

1. I maintain a temporary array *s* that contains only the characters in string *S* after converting them to uppercase.
2.  I maintain a variable *length* that counts how many characters I found in the string *S*. If length is 0, that means it contains only dashes (-). 
3. Then I record the *offset*. *Offset* basically measures how many characters of the String *S* will be grouped unevenly in the beginning part of the string. I can check that by using the modulus operator and finding out the remainder. That many characters (of *length < K*) will be in the beginning part of the string.
4. Next step is to calculate how many dashes I will need. It's basically *length / K*.
5. Then I create the char array that will hold the characters of the formatted key. It's length will be number of characters + the dashes we will need. We need to take care of a special case here. If the *offset* is 0, meaning I was able to divide characters in equal group, I need to subtract 1. Eg, let's say we had 8 characters and *K* was 4.  dashes = 8 / 4 = 2. We can divide 8 characters equally into 2 groups using only 1 dash. But since dashes was 2, it is clearly off by 1. This is the case when *offset* is 0.
6. *kIndex* tracks where character is to be inserted in the key array.
7. *used* tracks how many characters of the array *s*, which indirectly holds the characters of String *S*, are used.
8. First I copy down the characters of length *offset*. Because those are the ones of uneven length. *kIndex* and *used* variables are updated.
9. Last thing to do is to use all the remaining characters in array *s*, but we take *K* characters at a time, because we know that the segments are going to be of equal length. We also need to insert '-' after each segment, but only if *kIndex* is not at the beginning or at the end of the key array, because inserting it at those points is invalid.
10. Create a new string and return it.

```java
public String licenseKeyFormatting(String S, int K) {
    char[] s = new char[S.length()];
    int length = 0;
    for (char c: S.toCharArray())
      if (c != '-')
        s[length++] = Character.toUpperCase(c);
    if (length == 0)
        return "";
    int offset = length % K;
    int dashes = length / K; 
    char[] key = new char[length + dashes + (offset == 0 ? -1 : 0)];
    int kIndex = 0;
    int used = 0;
    while (used < offset)
      key[kIndex++] = s[used++];
    while (used < index){
      if (kIndex > 0 && kIndex < key.length)
        key[kIndex++] = '-';
      for (int i = 0; i < K; ++i)
        key[kIndex++] = s[used++];
    }
    return new String(key);
}
```



### [Max Consecutive Ones](https://leetcode.com/problems/max-consecutive-ones/)

Solution 1: I came up with this solution initially. 4 ms runtime and passes 99.97% submissions.

```java
public int findMaxConsecutiveOnes(int[] nums) {
    int start = 0;					// Keep track of start of a streak, if any
    int max = 0;					// max length of the streak
    while (start < nums.length){	// While we are not at the end of the array
        if (nums[start] == 1){		// Check if we have a 1 at start, if so
            int streak = 0;			// initialize streak and check how long can we continue
            while (start < nums.length && nums[start] == 1){	// that streak.
                ++streak;			// Increment streak and left for each consecutive 1
                ++start;			// make sure you don't forget that start < nums.length
            }						// before checking nums[start] to prevent out-of-bounds
            if (streak > max)		// Check if the current streak is better than the
                max = streak;		// previous streak.
        }
        ++start;					// Increment start in either case to check for new
    }								// streaks.
    return max;
}
```



Solution 2: After analyzing the problem further, I noticed that 0 denotes the end of a streak. If we observe 1, we increment streak by 1. But if I see a 0, I reset my streak to 0. This solution too had a 4 ms runtime and passed 99.97% submissions.

```java
public int findMaxConsecutiveOnes(int[] nums) {
    int max = 0;				// Global max streak
    int streak = 0;				// Local max streak.
    for (int i: nums){			// For each number in nums
        if (i == 1){			// If we see a 1
            ++streak;			// increment our ongoing streak.
            if (streak > max)	// If the local streak > global max
                max = streak;	// update global max streak.
        }
        else					// otherwise we just saw a 0.
            streak = 0;			// So our streak resets to 0.
    }
    return max;					// return the global max streak.
}
```



### [Permutations](https://leetcode.com/problems/permutations/)

The idea is as follows. Given an array a = {1,2,3}, we want to generate all it's possible combinations. What we are trying to do here is that we first take the element at index 0, and find permutations of the remaining thing. When we do that, we insert the element at index 0 in front of the list to get 1 permutation. Similarly, we then take the element at index 1, and permute the remaining contents of the array and insert the element at index 1 in the beginning of the array to get another permutation and so on. In this problem, we are asked to return a list of list, so we first copy the numbers of the array into an ArrayList. Let's run this code for the above example.

> Given nums = {1,2,3}, our ArrayList will be the same, al = [1,2,3]. Our result list is empty, result = [] and index = 0.
>
> **helper([1,2,3], 0)**
>
> > swap (0, 0) → al = [1,2,3]
> >
> > **helper(1,2,3, 1)**
> >
> > > swap(1, 1) → al = [1,2,3]
> > >
> > > **helper([1,2,3], 2)**
> > >
> > > > swap(2, 2) → [1,2,3]
> > > >
> > > > **helper([1,2,3], 3)**
> > > >
> > > > > We update our result list now, because index == length. Therefore, result = [[1,2,3]]. Our recursive stack collapses and we move on to the next instruction, which is undo the step, al = [1,2,3].
> > >
> > > swap(1, 2) → al = [1,3,2]
> > >
> > > **helper([1,3,2], 3)**
> > >
> > > > Again, index == length, add it to the list. result = [[1,2,3], [1,3,2]]. Recursion stack collapses, we undo the swap, al = [1,2,3]
> >
> > swap(0, 1) → al = [2,1,3]
> >
> > **helper([2,1,3], 1)**
> >
> > > swap(1,1) → al = [2,1,3]
> > >
> > > **helper([2,1,3], 2)**
> > >
> > > > swap(2, 2) → al = [2,1,3]
> > > >
> > > > **helper([2,1,3], 3)**
> > > >
> > > > >index == length, add the current order to the list. result = [[1,2,3], [1,3,2], [2,1,3]]
> > >
> > > swap(1, 2) → al = [2,3,1]
> > >
> > > **helper([2,3,1], 3)**
> > >
> > > > index == length, add the order to the list. Result = [[1,2,3], [1,3,2], [2,1,3], [2,3,1]]
> >
> > swap(0, 2) → al = [3,2,1]
> >
> > **helper([3,2,1], 2)**
> >
> > > swap(2,2) → al = [3,2,1]
> > >
> > > **helper([3,2,1], 3)**
> > >
> > > > index == length, add the order to the list. Result = [[1,2,3], [1,3,2], [2,1,3], [2,3,1], [3,2,1]] 
> >
> > swap(1,2) → al = [3,1,2]
> >
> > **helper([3,1,2], 3)**
> >
> > > index == length, add the order to the list. Result = [[1,2,3], [1,3,2], [2,1,3], [2,3,1], [3,2,1], [3,1,2]]
>
> All branches have been explored now, since the iteration ends and we return the result list.

```java
int len;						// To store the length of the input array
List<List<Integer>> result;		// Result list

public List<List<Integer>> permute(int[] nums) {
    result = new ArrayList<>();
    List<Integer> numList = new ArrayList<>();	// Creating a copy of the nums array
    for (int i: nums)			// because it's easier to create a list from a list.
        numList.add(i);			// Add everything to the list.
    len = nums.length;
    helper(numList, 0);			// Call the aux function.
    return result;
}

private void helper(List<Integer> order, int index){
    if (index == len)			// If we have checked all the numbers in the array, add a
        result.add(new ArrayList<>(order));	// clone of the list to the array.
    for (int i = index; i < len; ++i){	// Otherwise from index to the end of the array,
        swap(order, i, index);	// take one element, swap it with itself, then the next and
        helper(order, index+1);	// so on. Recurse again, but on the next index we just swapped.
        swap(order, i, index);	// Undo the swap so that it helps us in generating the next
    }							// permutation.
}

private void swap(List<Integer> list, int i, int j){	// Swap elements in a list.
    int temp = list.get(i);
    list.set(i, list.get(j));
    list.set(j, temp);
}
```



### [Construct the Rectangle](https://leetcode.com/problems/construct-the-rectangle/)

The idea is very simple. We just need to iterate from width = sqrt(area) to 1 and check if area is perfectly divisible by width. If at any point, width is divisible, then that must be our minimum difference length and width, because we are diverging from the center on both sides. Width decreases while length keeps increasing. Think of it like this, for area = 24, we have many factors of 24, namely 1, 2, 3,4, 6, 8, 12, 24. It's sqrt when rounded down is 4. So we check for width = 4, is 24 perfectly divisible by 4? Yes, so divide it and whatever you get is going to be the minimal difference values. Suppose 4 and 6 weren't the factors for 24. In that case we decrease width by 1, which is 3. Check again, is 24 divisible by 3. Yes? Then that must be our answer. We are diverging away from the center on both sides equally, width to the left towards 1 and length to the right towards area . Therefore the moment we find one value that divides area perfectly, that's our required values.

```java
public int[] constructRectangle(int area) {
    int[] dimensions = {area, 1};		// We know that if nothing works out, n*1 is always
    boolean done = false;				// going to be the answer
    int width = (int)Math.sqrt(area);	// We only need to check width from sqrt(area)
    while (!done){						// While not done
        if (area % length == 0){		// check if area is perfectly divisible by width
            dimensions[0] = width;		// if so, we found our width and the length.
            dimensions[1] = area/width;
            done = true;				// mark done as false
        }
        --width;						// otherwise decrease the length
    }
    return dimensions;					// return the dimensions found.
```



### [Merge Intervals](https://leetcode.com/problems/merge-intervals/)

```java
public List<Interval> merge(List<Interval> intervals) {
    if (intervals == null || intervals.size() < 2)
        return intervals;
    Collections.sort(intervals, (a,b) -> a.start-b.start);	// Sort the list so we can 
													// compare adjacent intervals.
    List<Interval> merged = new ArrayList<>();
    merged.add(intervals.get(0));					// Add the initial interval.

    for (Interval i: intervals){					// For each interval
        Interval last = merged.get(merged.size()-1);// Get the last added time.
        if (i.start > last.end)						// If it's time is greater than the last
            merged.add(i);							// interval's end, it doesn't overlap
        else{										// otherwise it does.
            last.end = last.end > i.end ? last.end : i.end;	// So check which has greater end time, and make the last added interval's time equals that
            merged.set(merged.size()-1, last);		// And set it as the last added interval
        }
    }

    return merged;									// Return the merged list.
}
```



### [Merged sorted lists](https://leetcode.com/problems/merge-sorted-array/)

1. counterA keeps track of which element we are looking at in array 'a'. Same with counterB
2. counterK keeps track of where to insert the element in array 'a', since a has enough space. The problem states that it might have more than enough space, so we use only the spaces we need, which is the total of both their sizes. Since indexing in an array is 0-based, we subtract 1.
3. We insert elements from the end, since the end part of 'a' is empty. We can insert from the front, but then we would need to shift elements to the right after each insertion from 'b'.
4. If array values are equal, add them to the end, and decrease both their counter to check new values in the next iteration
5. If not equal, then check which one is greater, since the last part of the array should contain larger values. Whichever's greater, put it in 'a' at index 'counterA' and decrement the respective counter.
6. In the end, we might have some leftover elements either from 'a' or 'b' because we only process elements that are equal to the **min(size(a), size(b))**, until we run out of elements in one of the array. So, whichever array has elements pending, add it to the front of the array and return a.

```java
public int[] merge(int[] a, int sizeA, int[] b, int sizeB)
{
    int counterA = sizeA-1, counterB = sizeB-1, counterK = sizeA+sizeB-1;
    while (counterA > -1 && counterB > -1){
        if (a[counterA] == b[counterB]){
            a[counterK--] = a[counterA--];
            a[counterK--] = b[counterB--];
        }
        else
            a[counterK--] = a[counterA] > b[counterB] ? a[counterA--] : b[counterB--];
    }
    while (counterA > -1)
        a[counterK--] = a[counterA--];
    while (counterB > -1)
        a[counterK--] = b[counterB--];
    return a;
}
```



### [Next Greater Element I](https://leetcode.com/problems/next-greater-element-i/)

```java
public int[] nextGreaterElement(int[] nums1, int[] nums2) {
    HashMap<Integer, Integer> index = new HashMap<>();	// We use the hashmap to keep a
    for (int i = 0; i < nums2.length; ++i)				// track of the index of each value
        index.put(nums2[i], i);							// in nums 2. That way, when we want
								// to look for a value greater than a val in nums1, we know
    int[] result = new int[nums1.length];	// which index to start iterating from.

    for (int i = 0; i < nums1.length; ++i){	// So for each val in nums1
        int val = nums1[i];
        for (int j = index.get(val); j < nums2.length; ++j){	// Iterate from that value's
            if (nums2[j] > val){			// index in nums2 to the end, and see if you can
                result[i] = nums2[j];		// find any val > nums1[i]. If you do, save it
                break;						// in the result array and break the loop.
            }
        }
        if (result[i] == 0)		// Now if we didn't find any value, then result[i] would be
            result[i] = -1;		// 0, so we set that index to -1 in our result array.
    }
    return result;				// simply return the result array.
}
```



### [String Without AAA or BBB](https://leetcode.com/problems/string-without-aaa-or-bbb/)

```java
public String strWithout3a3b(int A, int B) {				
    char[] ch = new char[A+B];		// We create an char array to store string chars
    int index = 0;
    char max = A > B ? 'a' : 'b';	// record the most frequent occurring element
    char min = max == 'a' ? 'b' : 'a';	// and the least frequent occurring element
    while (A > 0 || B > 0){			// While we haven't added all of the elements
    // We check that if our current index > 1 and our previoud two characters in the array
    // are the same, then we must have written the max occurring char, so it's time to write
    // the minimum occurring element. We write it, and then decrement the specific A or B.
        if (index > 1 && max == ch[index-1] && max == ch[index-2]){
            ch[index++] = min;
            if (min == 'a')		// If the minimum freq element is 'a', decrement A
                A--;
            else
                B--;			// otherwise decrement B
        }
        else if (B > A){		// Otherwise, if B occurs more than A, then set char to B
            ch[index++] = 'b';	// decrement B and increment index
            B--;
        }
        else{					// A occurs more, so add A to the char array.
            ch[index++] = 'a';	// Increment index, decrement A count
            A--;
        }
    }

    return new String(ch);		// Create a string from the char array and return it.
}
```



### [Keyboard Row](https://leetcode.com/problems/keyboard-row/)

```java
// Maps each character to the row in the keyboard in which it occurs.
private int[] map = {2,3,3,2,1,2,2,2,1,2,2,2,3,3,1,1,1,1,2,1,1,3,1,3,1,3};

public String[] findWords(String[] words) {

    String[] w = new String[words.length];	// Store filtered words
    int index = 0;							// Where to insert the filtered words
    for (String s: words)					// for each word in words
        if (checkWord(s.toLowerCase()))		// convert it to lowercase and check if all char
            w[index++] = s;					// occurs in the same row, if it does, add it
    return Arrays.copyOfRange(w, 0, index);	// Simply return a copy of the array from 0
}											// index

private boolean checkWord(String word){		// Check if all chars in the word belong in the
    int row = map[word.charAt(0)-'a'];		// same row. Check first chars row
    for (char c: word.toCharArray()){		// For all the chars in the word
        if (map[c-'a'] != row)				// if that char belongs to a different row,
            return false;					// return false
    }
    return true;							// All chars in same row, return true.
}
```



### [Find Mode in Binary Search Tree](https://leetcode.com/problems/find-mode-in-binary-search-tree/)

```java
private TreeNode parent;		// Keep track of parent at each node
private int maxMode;			// maxMode we found
private int currentMode;		// mode recorded at each node
private Set<Integer> modes;		// keep distinct modes found

public int[] findMode(TreeNode root){
    if (root == null)			// node is null, so return empty array
        return new int[0];
    maxMode = 1;				// we have just seen the root, so maxMode so far is 1.
    currentMode = 1;			// so is the current mode
    modes = new HashSet<>();
    modes.add(root.val);		// add the root to our modes set
    traverse(root);				// start traversing it's left and right branches
    int[] result = new int[modes.size()];	// We have found all the modes
    int idx = 0;				// keep track of where to insert elements in result array
    for (int i: modes)			// add all the distinct modes one by one
        result[idx++] = i;
    return result;				// and return it.
}

private void traverse(TreeNode node){
    if (node == null)			// if node is null, stop
        return;					// otherwise traverse the left branch
    traverse(node.left);		// Once we hit the null, we start backtracking to the leaf
    updateMode(node);			// then we call updateMode with the node
    parent = node;				// once it's done, we update parent as the current node, so 
    traverse(node.right);		// when we backtrack, we can easily check that node and it's
}								// next node's value for similarity. Then traverse right.

private void updateMode(TreeNode node){
    if (parent != null && parent.val == node.val){	// If parent node isn't null and the
        ++currentMode;			// node's value is the same as parent, we update currentMode
        if (currentMode >= maxMode){	// If the currentMode is greater or equal to maxMode
            if (currentMode > maxMode)	// just check if it's greater. If it is, remove all 
                modes.clear();			// previously recorded modes
            modes.add(node.val);		// Add the current node to the set and update the 
            maxMode = currentMode;		// maxMode
        }
    }
    else{						// otherwise, value's aren't the same. so our currentMode
        currentMode = 1;		// becomes 1. If maxMode is also 1, then all we have been 
        if (maxMode == 1)		// seeing are distinct values, so add that node's value to
            modes.add(node.val);// to the mode's set.
    }
}
```



### [Base 7](https://leetcode.com/problems/base-7/)

Solution 1 without StringBuilder (Beats 100%, 7ms)

```java
    public String convertToBase7(int num) {
        if (num == 0)
            return "0";
        int len = (int)(Math.log(Math.abs(num))/Math.log(7))+1;	// Calculate # of bits
        int idx;		// where to start inserting from
        char[] digits;
        if (num < 0) {	// If num is negative
            num = -num;	// Make it positive
            digits = new char[len+1];	// We need one more space for -ve sign in the front
            digits[0] = '-';			// Put the -ve sign
            idx = len;					// and index is now len
        }
        else{
            digits = new char[len];		// otherwise we only need "len" spaces
            idx = len-1;				// index is len-1
        }
        while (num != 0) {				// While num != 0, calculate remainder and add it.
            digits[idx--] = (char)(num % 7 + '0');	// Divide number by 7
            num /= 7;
        }     
        return new String(digits);		// Just create a string and return it.
    }
```

Solution 2 with StringBuilder

```java
public String convertToBase7(int num) {
    StringBuilder sb = new StringBuilder();
    boolean isNegative = num < 0;	// Just so we can know if we need to add the "-" sign
    if (num < 0)					// Take the absolute value of num
        num = -num;
    while (num > 6) {				// Keep adding the remainder, and dividing num by 7.
        sb.append(num % 7);
        num /= 7;
    }
    sb.append(num);					// Add whatever is left at the end.
    if (isNegative)					// If num was negative, add the minus sign.
        sb.append('-');

    return sb.reverse().toString();	// Reverse the builder and return the toString()
}
```



### [Relative Ranks](https://leetcode.com/problems/relative-ranks/)

The idea employed here is simple. We need to store the relative ranks in sorted order. We can sort the array for that, but that is O(n log n). We can do better than that by finding the relative rank in linear time. First we find the maximum score in the array and create another array of length = maxScore + 1. We add 1 so that when we see the maxScore in the nums, we can assign it to maxScore index. Once we have done that, now we iterate over the nums array. Variable i keeps track of what rank to assign. We check a value in the array and at that index in our reverse sorted array, we put i+1, which basically marks it's rank based on it's position in the rankings. Some of then indexes would be default, that is a score of 0. We then check each value in the descend array and if it's not 0, we assign it a rank, but not if the ranks are 1, 2 or 3. In that case, we assign it a special value of Gold, SIlver or Bronze.

```java
public String[] findRelativeRanks(int[] nums) {
    int maxScore = nums[0];
    for (int n: nums)
        if (n > maxScore)
            maxScore = n;
    int[] descend = new int[maxScore+1];
    for (int i = 0; i < nums.length; ++i)
        descend[nums[i]] = i+1;
    String[] result = new String[nums.length];
    int rank = 1;
    for (int i = descend.length-1; i > -1; --i){
        int idx = descend[i];
        if (descend[i] != 0){
            if (rank == 1)
                result[idx-1] = "Gold Medal";
            else if (rank == 2)
                result[idx-1] = "Silver Medal";
            else if (rank == 3)
                result[idx-1] = "Bronze Medal";
            else
                result[idx-1] = rank + "";
            ++rank;
        }
    }
    return result;
}
```



### [Perfect Number](https://leetcode.com/problems/perfect-number/)

```java
public boolean checkPerfectNumber(int num) {
    if (num == 1)		// 1 is a special case, where it's only factor is itself.
        return false;
    int total = 1;		// We know our total will atleast be 1, 1 is everyone's factor
    for (int i = 2; i <= Math.sqrt(num); ++i)	// Only loop through num's sqrt
        if (num % i == 0){				// If i divides num perfectly
            int otherFactor = num/i;	// Calculate the other factor
            total += i + (otherFactor == i ? 0 : otherFactor);	// If i and other factor are
        }								// different, add them both, otherwise just i.
    return total == num;				// Check in the end if your total is the same as num
}
```



### [Detect Capital](https://leetcode.com/problems/detect-capital/)

```java
public boolean detectCapitalUse(String word) {
    int len = word.length();
    if (len < 2)			// Empty or size 1 words are ok.
        return true;
    char[] chars = word.toCharArray();	// Get the char array
    boolean isUpper = false;	// by default we let isUpper to false
    if (chars[0] >= 'A' && chars[0] <= 'Z')		// Check if first two letters are uppercase
        isUpper = chars[1] >= 'A' && chars[1] <= 'Z'; // If first was upper and second wasnt
    for (int i = 1; i < len; ++i){	// isUpper = false, otherwise true.
        boolean isAlsoUpper = chars[i] >= 'A' && chars[i] <= 'Z'; // We check onwards 1 char
        if (isUpper && !isAlsoUpper)	// If that char is lower and previous part was
            return false;				// not lower, invalid use.
        if (!isUpper && isAlsoUpper)	// Or if previous part was lower and current letter
            return false;				// is upper, we return false.
    }
    return true;				// Everything proceeded smoothly. So return true.
}
```


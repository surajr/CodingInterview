/*
// Employee info
class Employee {
    // It's the unique id of each node;
    // unique id of this employee
    public int id;
    // the importance value of this employee
    public int importance;
    // the id of direct subordinates
    public List<Integer> subordinates;
};
*/
class Solution {
    public int getImportance(List<Employee> employees, int id) {
        
        int importance = 0;
        
        HashMap<Integer, Employee> map = new HashMap<>();
        for(Employee employee : employees)
            map.put(employee.id, employee);
        
        Queue<Employee> queue = new LinkedList<>();
        queue.offer(map.get(id));
        
        while(!queue.isEmpty())
        {
            Employee node = queue.poll();
            importance += node.importance;
            for(int subor: node.subordinates)
                queue.offer(map.get(subor));
        }        
        return importance;        
    }
}

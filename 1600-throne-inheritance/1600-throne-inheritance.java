import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

class ThroneInheritance {

    private String king;
    // Map parent to their children in order of birth
    private Map<String, List<String>> familyTree;
    // Set to keep track of dead individuals
    private Set<String> dead;

    public ThroneInheritance(String kingName) {
        this.king = kingName;
        this.familyTree = new HashMap<>();
        this.dead = new HashSet<>();
    }
    
    public void birth(String parentName, String childName) {
        familyTree.putIfAbsent(parentName, new ArrayList<>());
        familyTree.get(parentName).add(childName);
    }
    
    public void death(String name) {
        dead.add(name);
    }
    
    public List<String> getInheritanceOrder() {
        List<String> order = new ArrayList<>();
        dfs(king, order);
        return order;
    }

    private void dfs(String current, List<String> order) {
        // Add to order if the person is alive
        if (!dead.contains(current)) {
            order.add(current);
        }
        
        // Traverse children in birth order (Pre-order Traversal)
        if (familyTree.containsKey(current)) {
            for (String child : familyTree.get(current)) {
                dfs(child, order);
            }
        }
    }
}
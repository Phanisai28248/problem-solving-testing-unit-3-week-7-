import java.util.ArrayList;
import java.util.List;

class BrowserHistory {

    private List<String> history;
    private int currentIndex;
    private int maxValidIndex;

    public BrowserHistory(String homepage) {
        history = new ArrayList<>();
        history.add(homepage);
        currentIndex = 0;
        maxValidIndex = 0;
    }
    
    public void visit(String url) {
        currentIndex++;
        // If we visited new URLs, overwrite/append at currentIndex
        if (currentIndex < history.size()) {
            history.set(currentIndex, url);
        } else {
            history.add(url);
        }
        // Visiting a new URL clears all forward history
        maxValidIndex = currentIndex;
    }
    
    public String back(int steps) {
        // Move back at most to index 0
        currentIndex = Math.max(0, currentIndex - steps);
        return history.get(currentIndex);
    }
    
    public String forward(int steps) {
        // Move forward at most to maxValidIndex
        currentIndex = Math.min(maxValidIndex, currentIndex + steps);
        return history.get(currentIndex);
    }
}

/**
 * Your BrowserHistory object will be instantiated and called as such:
 * BrowserHistory obj = new BrowserHistory(homepage);
 * obj.visit(url);
 * String param_2 = obj.back(steps);
 * String param_3 = obj.forward(steps);
 */

package statistics;

import statistics.matcher.*;


class QueryBuilder {
    private Matcher matcher;

    public QueryBuilder() {
        matcher = new All();
    }
    
    public Matcher build() {
        Matcher m = this.matcher;
        this.matcher = new All();
        return m;
    }
    
    public QueryBuilder and(Matcher... m) {
        this.matcher = new And(m);
        return this;
    }
    
    public QueryBuilder playsIn(String team) {
        and(this.matcher, new PlaysIn(team));
        return this;
    }
    
    public QueryBuilder hasAtLeast(int value, String category) {
        and(this.matcher, new HasAtLeast(value, category));
        return this;
    }
    
    public QueryBuilder hasFewerThan(int value, String category) {
        and(this.matcher, new HasFewerThan(value, category));
        return this;
    } 
    
    public QueryBuilder oneOf(Matcher... m) {
        this.matcher = new Or(m);
        return this;
    }

    
}

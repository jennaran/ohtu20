
package ohtu;

public class Player implements Comparable<Player> {
    private String name;
    private int goals;
    private int assists;
    private String team;
    private String nationality;

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getNationality() {
        return nationality;
    }

    public int getGoals() {
        return goals;
    }

    public int getAssists() {
        return assists;
    }

    public String getTeam() {
        return team;
    }

    public void setGoals(int goals) {
        this.goals = goals;
    }

    public void setAssists(int assists) {
        this.assists = assists;
    }

    public void setTeam(String team) {
        this.team = team;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    
    public int getSum() {
        return this.goals + this.assists;
    }
    
    @Override
    public String toString() {
    //    return name + " team " + this.team + " goals " + this.goals + " assists " + this.assists;
        return 
                toLeftFormat(this.name, 20) + 
                toLeftFormat(this.nationality, 5) + 
                toRightFormat(Integer.toString(goals), 3) +
                toRightFormat("+", 3) +
                toRightFormat(Integer.toString(assists), 3) +
                toRightFormat("=", 3) +
                toRightFormat(Integer.toString(getSum()), 3)
                ;
                
    }
    
    public String toLeftFormat( String s, Integer n ) {
        return String.format("%-" + n + "s", s);
    }
    
    public String toRightFormat( String s, Integer n ) {
        return String.format("%" + n + "s", s);
    }

    @Override
    public int compareTo(Player compareP) {
        int compareSum = compareP.getSum();
        return compareSum - this.getSum();
    }
      
}


public class TestingClass {
    private String name;
    private int id;
    public TestingClass(String name, int id) {
        this.name = name;
        this.id = id;
    }
    @Override
    public int hashCode() {
        int hash = 17;
        hash = hash * 31 + id;
        hash = hash * 31 + name.length();
        return hash;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof TestingClass)) return false;
        TestingClass other = (TestingClass) obj;
        return id == other.id && name.equals(other.name);
    }
    @Override
    public String toString() {
        return name + " " + id;
    }
}
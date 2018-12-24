package Entity;

public class Class {
    private String classID;

    public String getClassID() {
        return classID;
    }

    public void setClassID(String classID) {
        this.classID = classID;
    }

    @Override
    public String toString() {
        return "classID : " + this.classID + " " + super.toString();
    }

    public void sayWelcome(){
        System.out.println("Welcome to our class " + classID);
    }
}

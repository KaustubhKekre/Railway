package phantomorion.com.railway;

public class UserDetails {
    private String name, ID,age,department,gender;



    public UserDetails(){}
    public UserDetails(String name, String ID, String age, String department,String gender) {
        this.name = name;
        this.department=department;
        this.ID = ID;
        this.age = age;
        this.gender=gender;

    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
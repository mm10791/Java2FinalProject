import java.io.Serializable;

    public class Employee implements Serializable{
        String name;
        String id;
        String position;
        double salary;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPosition() {
            return position;
        }

        public void setPosition(String position) {
            this.position = position;
        }

        public double getBalance() {
            return salary;
        }

        public void setBalance(double salary) {
            this.salary = salary;
        }

        public Employee(String id, String name, String position, double salary){
            this.name = name;
            this.id = id;
            this.salary =salary;
            this.position = position;
        }
 
        public String toString(){
            return "Account[ID:" + id + ", Name: " + name +  ", position:" + position+ ", salary: "+ salary +"]";
        }
    }

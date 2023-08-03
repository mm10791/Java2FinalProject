import java.io.EOFException;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class SearchModel {
    private ArrayList<Employee> readEmployees(){
        ObjectInputStream input = null;
        ArrayList<Employee> myList = new ArrayList<Employee>();
        try {
            input = new ObjectInputStream(new FileInputStream("employees.dat"));
            while(true){
                Employee acc = (Employee)input.readObject();
                myList.add(acc);
            }   
        } catch(EOFException eof){
        }catch (Exception e) {
            System.out.println("Error reading objects " + e);
        }finally{
            try {
                input.close();
            } catch (Exception e) {
                System.out.println("Error closing");
            }
        }
        return myList;
    }

    public String searchResult(String id, String name, String position){
        ArrayList<Employee> myArrList = readEmployees();
        String result = "";
        
        for(int i = 0; i < myArrList.size(); i ++){
            if (name.equals(myArrList.get(i).name.toString()) || id.equals(myArrList.get(i).id) || position.equals(myArrList.get(i).position.toString())){
                result = myArrList.get(i).name + "," + myArrList.get(i).id 
                               + "," + myArrList.get(i).position;
            }
        }
        return result;
    }

    public void clearAll(String id, String name, String position, String result){
        
    }
    
}

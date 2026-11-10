/* Class: CSE 1322L
Section: J04
Term: Full Term
Instructor: Gar Lock
Name: Michael Pack
Assignment#: 5*/
import java.util.Scanner;
import java.util.ArrayList;

class Family {
    ArrayList<Person> family = new ArrayList<Person>();

    public void addPerson(String name, int parent1, int parent2) {
        //This method adds a person to the tree.
        family.add(new Person(name,parent1,parent2));
    }

    public int getIDFromName(String name) {
        //This method converts a name to an id
        for(Person x : family) {
            if(x.getName()==name) {
                return x.getID();
            }
        }
        return -1;
    }

    public String getNameFromID(int id) {
        //This method converts an id to a name.
        for(Person x : family) {
            if(x.getID()==id) {
                return x.getName();
            }
        }
        return "Unknown";
    }

    public String parentRelationship(String current) {
        //This method calculates the word for a parent, grandparent, great grandparent etc.
        if(current.equals("Self")) {
            return "Parent";
        }
        else if(current.equals("Parent")) {
            return "GrandParent";
        }
        else {
            return "Great "+current;
        }
    }

    public String childRelationship(String current) {
        //This method calculates the word for a child, grandchild, great grandchild etc.
        if(current.equals("Self")) {
            return "Child";
        }
        else if(current.equals("Child")) {
            return "GrandChild";
        }
        else {
            return "Great "+current;
        }
    }

    public String everyone() {
        String returnString="";
        for(Person x : family) {
            returnString+=x.getID()+") "+x.getName()+"\n";
        }
        return returnString;
    }

    public ArrayList<Integer> getChildrenIDs(int id) {
        ArrayList<Integer>childrenIDs = new ArrayList<>();
        for (Person person: family){
            if (person.getID()==id){
                childrenIDs.add(person.getID());
            }
        }
        return childrenIDs;
    }

    public int[] getParents(int ID) {
        int[]parentsID={-1,-1};
        for(Person person: family){
            if (person.getID()==ID){
                parentsID[0]=person.getParent1();
                parentsID[1]=person.getParent2();
                break;
            }
        }
        return parentsID;
    }

    public void printParents(int childID,String relationship) {
        if (childID==-1){
            return;
        }
        for (Person person: family){
            if (person.getID()==childID){
                System.out.println(relationship+": "+person.getName());
                printParents(person.getParent1(), parentRelationship(relationship));
                printParents(person.getParent2(), parentRelationship(relationship));
                break;
            }
        }
    }

    public void printChildren(int parentID,String relationship) {
        if (parentID==-1){
            return;
        }
        for (Person person: family){
            if (person.getParent1()==parentID||person.getParent2()==parentID){
                System.out.println(relationship+": "+person.getName());
                printChildren(person.getID(), childRelationship(relationship));
                break;
            }
        }
    }
}
class Person {
    private int id;
    public static int next_id=0;
    private String name;

    private int parent_id1;
    private int parent_id2;

    public Person(String name, int parent1, int parent2) {
        id=next_id++;
        this.name=name;
        parent_id1=parent1;
        parent_id2=parent2;
    }

    public int getParent1() {
        return parent_id1;
    }

    public int getParent2() {
        return parent_id2;
    }

    public int getID() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }

}

class Assignment5 {
    public static void main(String[] args) {
        Scanner myScan=new Scanner(System.in);
        Family myFamily=new Family();

        myFamily.addPerson("John Bissett",-1,-1);
        myFamily.addPerson("Wilhelm Busart",-1,-1);
        myFamily.addPerson("John Leach",-1,-1);
        myFamily.addPerson("Georgianna Morningstar",-1,-1);
        myFamily.addPerson("John Campana",-1,-1);
        myFamily.addPerson("Anthony Germanotta",-1,-1);
        myFamily.addPerson("Rosaria X",-1,-1);
        myFamily.addPerson("George Lindsey Bissett",0,1);
        myFamily.addPerson("Sarah Ann Leach Sally",2,3);
        myFamily.addPerson("James Ferrie",-1,-1);
        myFamily.addPerson("Minnie Campana",4,-1);
        myFamily.addPerson("Joseph Germanotta",5,6);
        myFamily.addPerson("Angeline C",-1,-1);
        myFamily.addPerson("Paul Douglas Bissett",7,8);
        myFamily.addPerson("Veronica R Ferrie",9,10);
        myFamily.addPerson("Joseph A Germanotta",11,12);
        myFamily.addPerson("Cynthia B. Bissett",13,14);
        myFamily.addPerson("Stephanie Joanne Germanotta",15,16);
        myFamily.addPerson("Natali Germanotta",15,16);

        boolean done=false;

        do {
            System.out.println("What would you like to do");
            System.out.println("1. Add a person to the tree");
            System.out.println("2. See a person's ancestors");
            System.out.println("3. See a person's descendants");
            System.out.println("4. Quit");

            int choice=Integer.parseInt(myScan.nextLine());
            String listOfPeople=myFamily.everyone();
            if(choice==1) {
                System.out.println("What is this person's name?");
                String name=myScan.nextLine();

                System.out.println("Who is their first parent");
                System.out.println("-1 Unknown");
                System.out.println(listOfPeople);
                int parent1=Integer.parseInt(myScan.nextLine());

                System.out.println("Who is their other parent");
                System.out.println("-1 Unknown");
                System.out.println(listOfPeople);
                int parent2=Integer.parseInt(myScan.nextLine());

                myFamily.addPerson(name,parent1,parent2);

            }
            else if(choice==2) {

                System.out.println("Who do you want to see ancestors of?");
                System.out.println(listOfPeople);
                int who=Integer.parseInt(myScan.nextLine());
                myFamily.printParents(who,"Self");
            }
            else if(choice==3) {

                System.out.println("Who do you want to see descendants of?");
                System.out.println(listOfPeople);
                int who=Integer.parseInt(myScan.nextLine());
                myFamily.printChildren(who,"Self");
            }
            else if(choice==4) {
                done=true;
            }

        }while(!done);
    }
}
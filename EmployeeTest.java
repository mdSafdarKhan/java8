import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.*;
import java.util.stream.Collectors;

class Employee {
    int id;

    String name;

    int age;

    String gender;

    String department;

    int yearOfJoining;

    double salary;

    public Employee(int id, String name, int age, String gender, String department, int yearOfJoining, double salary)
    {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.department = department;
        this.yearOfJoining = yearOfJoining;
        this.salary = salary;
    }

    public int getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public int getAge()
    {
        return age;
    }

    public String getGender()
    {
        return gender;
    }

    public String getDepartment()
    {
        return department;
    }

    public int getYearOfJoining()
    {
        return yearOfJoining;
    }

    public double getSalary()
    {
        return salary;
    }

    @Override
    public String toString()
    {
        return "Id : "+id
                +", Name : "+name
                +", age : "+age
                +", Gender : "+gender
                +", Department : "+department
                +", Year Of Joining : "+yearOfJoining
                +", Salary : "+salary;
    }
}

public class EmployeeTest{
    public static void main(String[] args) {

        List<Employee> employeeList = new ArrayList<>();

        employeeList.add(new Employee(111, "Jiya Brein", 32, "Female", "HR", 2011, 25000.0));
        employeeList.add(new Employee(122, "Paul Niksui", 25, "Male", "Sales And Marketing", 2015, 13500.0));
        employeeList.add(new Employee(133, "Martin Theron", 29, "Male", "Infrastructure", 2012, 18000.0));
        employeeList.add(new Employee(144, "Murali Gowda", 28, "Male", "Product Development", 2014, 32500.0));
        employeeList.add(new Employee(155, "Nima Roy", 27, "Female", "HR", 2013, 22700.0));
        employeeList.add(new Employee(166, "Iqbal Hussain", 43, "Male", "Security And Transport", 2016, 10500.0));
        employeeList.add(new Employee(177, "Manu Sharma", 35, "Male", "Account And Finance", 2010, 27000.0));
        employeeList.add(new Employee(188, "Wang Liu", 31, "Male", "Product Development", 2015, 34500.0));
        employeeList.add(new Employee(199, "Amelia Zoe", 24, "Female", "Sales And Marketing", 2016, 11500.0));
        employeeList.add(new Employee(200, "Jaden Dough", 38, "Male", "Security And Transport", 2015, 11000.5));
        employeeList.add(new Employee(211, "Jasna Kaur", 27, "Female", "Infrastructure", 2014, 15700.0));
        employeeList.add(new Employee(222, "Nitin Joshi", 25, "Male", "Product Development", 2016, 28200.0));
        employeeList.add(new Employee(233, "Jyothi Reddy", 27, "Female", "Account And Finance", 2013, 21300.0));
        employeeList.add(new Employee(244, "Nicolus Den", 24, "Male", "Sales And Marketing", 2017, 10700.5));
        employeeList.add(new Employee(255, "Ali Baig", 23, "Male", "Infrastructure", 2018, 12700.0));
        employeeList.add(new Employee(266, "Sanvi Pandey", 26, "Female", "Product Development", 2015, 28900.0));
        employeeList.add(new Employee(277, "Anuj Chettiar", 31, "Male", "Product Development", 2012, 35700.0));

        //Query 3.1 : How many male and female employees are there in the organization?
        Map<String, Long> genderBasedCount = employeeList.stream().collect(Collectors.groupingBy(Employee::getGender, Collectors.counting()));
        System.out.println("Gender Based Count: \n\t" + genderBasedCount);

        //Query 3.2 : Print the name of all departments in the organization?
        List<String> departmentNames = employeeList.stream().map(Employee::getDepartment).distinct().collect(Collectors.toList());
        System.out.println("Department Names: \n\t" + departmentNames);

        //Query 3.3 : What is the average age of male and female employees?
        Map<String, Double> avgAge = employeeList.stream().collect(Collectors.groupingBy(Employee::getGender, Collectors.averagingLong(Employee::getAge)));
        System.out.println("Average Age: \n\t" + avgAge);

        //Query 3.4 : Get the details of highest paid employee in the organization?
        Employee highestPaid = employeeList.stream().collect(Collectors.maxBy(Comparator.comparingDouble(Employee::getSalary))).get();
        System.out.println("Highest Paid: \n\t" + highestPaid);

        //Query 3.5 : Get the names of all employees who have joined after 2015?
        List<String> joiningAfter2015 = employeeList.stream().filter(employee -> employee.yearOfJoining > 2015).map(employee -> employee.name).collect(Collectors.toList());
        System.out.println("Joining After 2015: \n\t" + joiningAfter2015);

        //Query 3.6 : Count the number of employees in each department?
        Map<String, Long> empDeptWise  = employeeList.stream().collect(Collectors.groupingBy(Employee::getDepartment, Collectors.counting()));
        System.out.println("Employee Deptartment Wise: \n\t" + empDeptWise);

        //Query 3.7 : What is the average salary of each department?
        Map<String, Double> averageSalaryDeptWise = employeeList.stream().collect(Collectors.groupingBy(Employee::getDepartment, Collectors.averagingDouble(Employee::getSalary)));
        System.out.println("Average Salary Dept. Wise: \n\t" + averageSalaryDeptWise);

        //Query 3.8 : Get the details of youngest male employee in the product development department?
        Employee youngestMaleEmployeeInDevDept = employeeList.stream().filter(e -> (e.getDepartment() == "Product Development") && (e.getGender() == "Male")).min(Comparator.comparingInt(Employee::getAge)).get();
        System.out.println("Youngest Male Employee In Product Development Team: \n\t" + youngestMaleEmployeeInDevDept);

        //Query 3.9 : Who has the most working experience in the organization?
        Employee mostExperienced = employeeList.stream().sorted(Comparator.comparing(Employee::getYearOfJoining)).findFirst().get();
        System.out.println("Most Experienced Employee: \n\t" + mostExperienced);

        //Query 3.10 : How many male and female employees are there in the sales and marketing team?
        Map<String, Long> maleFemaleEmp = employeeList.stream().filter(e -> e.getDepartment() == "Sales And Marketing").collect(Collectors.groupingBy(Employee::getGender, Collectors.counting()));
        System.out.println("Male and Female in Sales and Marketing Dept. \n\t" + maleFemaleEmp);

        //Query 3.11 : What is the average salary of male and female employees?
        Map<String, Double> avgSalaryMandF = employeeList.stream().collect(Collectors.groupingBy(Employee::getGender, Collectors.averagingDouble(Employee::getSalary)));
        System.out.println("Avg. Salary of Male and Female Emp.: \n\t" + avgSalaryMandF);

        //Query 3.12 : List down the names of all employees in each department?
        Map<String, List<Employee>> deptWiseEmpNames = employeeList.stream().collect(Collectors.groupingBy(Employee::getDepartment));
        for(Map.Entry<String, List<Employee>> entry : deptWiseEmpNames.entrySet()){
            String deptName = entry.getKey();
            System.out.println("Dept Name: " + deptName);

            List<Employee> emps = entry.getValue();
            for(Employee emp : emps){
                System.out.print("\t" + emp.getName() + " ");
            }
            System.out.println();
        }

        //Query 3.13 : What is the average salary and total salary of the whole organization?
        DoubleSummaryStatistics dss = employeeList.stream().collect(Collectors.summarizingDouble(Employee::getSalary));
        System.out.println("Average Salary of Organization: " + dss.getAverage());
        System.out.println("Total Salary of Organization: " + dss.getSum());

        //Query 3.14 : Separate the employees who are younger or equal to 25 years from those employees who are older than 25 years.
        Map<Boolean, List<Employee>> youngAndOld25 = employeeList.stream().collect(Collectors.partitioningBy(e -> e.getAge() > 25));
        for(Map.Entry<Boolean, List<Employee>> entry: youngAndOld25.entrySet()){
            if(entry.getKey()){
                System.out.println("******* Older 25 Employee *******");
            }
            else{
                System.out.println("******* Younger 25 Employee *******");
            }

            List<Employee> emps = entry.getValue();
            for(Employee emp: emps){
                System.out.println("\t" + emp.getName());
            }
        }

        //Query 3.15 : Who is the oldest employee in the organization? What is his age and which department he belongs to?
        Employee oldestEmp = employeeList.stream().max(Comparator.comparing(Employee::getAge)).get();
        System.out.println("Oldest emp: " + oldestEmp.getName());
        System.out.println("Oldest emp age: " + oldestEmp.getAge());
        System.out.println("Oldest emp deptartment: " + oldestEmp.getDepartment());
    }
}

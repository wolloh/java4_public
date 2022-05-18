import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
 class Person{
    public int id;
    public String Name;
    public String Sex;
    public String Birthday;
    public int Salary;
    public Department department;
    public Person(int id,String Name,String Sex,String Birthday,int Salary,Department department){
        this.Birthday=Birthday;
        this.id=id;
        this.department=department;
        this.Name=Name;
        this.Salary=Salary;
        this.Sex=Sex;
    }
     public String toString(){
         return String.valueOf(id) + ' ' + Name + ' ' + Sex + ' ' + department.toString() + ' ' + String.valueOf(Salary) + ' ' + Birthday;
     }
}
class Department{
     public  int id;
     public String code;
     public Department(int id,String code){
         this.id=id;
         this.code=code;
     }
    public String toString(){
        return String.valueOf(id) + ' ' + code;
    }
}
public class Main {
    public static void main(String[] args) throws IOException, CsvValidationException {
        var fileName = "C:/Users/popov/IdeaProjects/untitled/src/main/resources/foreign_names_1.csv";
        Path myPath = Paths.get(fileName);

        CSVParser parser = new CSVParserBuilder().withSeparator(';').build();

        try (var br = Files.newBufferedReader(myPath, StandardCharsets.UTF_8);
             var reader = new CSVReaderBuilder(br).withCSVParser(parser)
                     .build()) {

            //List<String[]> rows = reader.readAll();
            List<Person> list=new ArrayList<>();
            String[] nextLine = reader.readNext();
            while ((nextLine = reader.readNext()) != null) {
                if (nextLine != null) {
                    list.add(new Person(Integer.parseInt(nextLine[0]), nextLine[1], nextLine[2], nextLine[3], Integer.parseInt(nextLine[5]), new Department(ReadDepartment(nextLine[4], list),nextLine[4])));
                }
            }
            for(var i:list){
                System.out.println(i.toString());
            }
        }

    }
    public static int ReadDepartment(String code,List<Person> list){
        int id=0;
        if(!list.isEmpty()){
            id=list.get(list.size()-1).department.id+1;
        }
        return id;
    }
}
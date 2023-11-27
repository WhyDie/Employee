import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EmployeeRepository {
    public List<Employee> employeeRepository = new ArrayList<>(
            List.of(
                    new Employee(1,"Igor","Vilka",Gender.MALE,32,9000,10,Speciality.FRONTEND_DEVELOPER),
                    new Employee(2,"Vasya","Bulkin",Gender.MALE,21,3000,2,Speciality.FRONTEND_DEVELOPER),
                    new Employee(3,"Maks","Gvildis",Gender.MALE,30,5000,4,Speciality.FRONTEND_DEVELOPER),
                    new Employee(4,"Olga","Vilka",Gender.FEMALE,31,8000,8,Speciality.FRONTEND_DEVELOPER),
                    new Employee(5,"Danya","Vishchun",Gender.MALE,41,15000,16,Speciality.BACKAND_DEVELOPER),
                    new Employee(6,"Illya","Ivanov",Gender.MALE,33,4000,7,Speciality.BACKAND_DEVELOPER),
                    new Employee(7,"Oleg","Maksimov",Gender.MALE,40,8000,8,Speciality.BACKAND_DEVELOPER),
                    new Employee(8,"Andrey","Bosnenko",Gender.MALE,37,4500,5,Speciality.BACKAND_DEVELOPER)
            )
    );

    public String[] getNameAndSurnameOfTopBySalary() {
        return employeeRepository.stream()
                .sorted(Comparator.comparing(Employee::getSalary).reversed())
                .limit(3)
                .map(employee -> employee.getName() + " " + employee.getLastName())
                .toArray(String[]::new);
    }

    public Map<Speciality, List<Employee>> groupBySpeciality() {
        return employeeRepository.stream()
                .collect(Collectors.groupingBy(Employee::getSpeciality, Collectors.toList()));
    }

    public int getTotalSalaryForQAFemalesWithLowExperience() {
        return employeeRepository.stream()
                .filter(e -> e.getSpeciality() == Speciality.QA && e.getWorkExperience() <= 5 && e.getGender() == Gender.FEMALE)
                .mapToInt(Employee::getSalary)
                .sum();
    }

    public boolean checkIfPresentEmployeeWithWorkExperienceMoreThanTwenty() {
        return employeeRepository.stream()
                .anyMatch(e -> e.getWorkExperience() > 20 &&
                        e.getSpeciality() == Speciality.BACKAND_DEVELOPER && e.getGender() == Gender.MALE);
    }

    public String getDescendingSalaryWithFullName() {
        return employeeRepository.stream()
                .sorted(Comparator.comparing(Employee::getSalary).reversed())
                .map(employee -> String.format("%s %s %d%n", employee.getName(), employee.getLastName(), employee.getSalary()))
                .collect(Collectors.joining(";"));
    }

    public Map<Speciality, Double> getAverageSalaryBySpeciality() {
        return employeeRepository.stream()
                .collect(Collectors.groupingBy(Employee::getSpeciality, Collectors.averagingDouble(Employee::getSalary)));
    }
}

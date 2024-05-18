package com.company;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.List;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Main.class, args);

        EmployeeRepository employeeRepo = context.getBean(EmployeeRepository.class);
        CompanyRepository companyRepo = context.getBean(CompanyRepository.class);
        ProjectRepository projectRepo = context.getBean(ProjectRepository.class);


        Company comp1 = new Company(null, "Adecco", "Madrid", "28001");
        Company comp2 = new Company(null, "CertiDevs", "Madrid", "28002");
        companyRepo.saveAll(List.of(comp1, comp2));

        Project proj1 = new Project(null, "PROJ1", true, null);
        Project proj2 = new Project(null, "PROJ2", true, null);
        Project proj3 = new Project(null, "PROJ3", false, null);
        Project proj4 = new Project(null, "PROJ4", true, null);
        projectRepo.saveAll(List.of(proj1, proj2, proj3, proj4));

        Employee emp1 = new Employee();
        emp1.setEmail("emp1@gmail.com");
        emp1.setSalary(2000.0);
        emp1.setCompany(comp1);
        emp1.getProjects().add(proj1);
        emp1.getProjects().add(proj3);

        Employee emp2 = new Employee();
        emp2.setEmail("emp2@gmail.com");
        emp2.setSalary(2000.0);
        emp2.setCompany(comp2);
        emp2.getProjects().add(proj1);
        emp2.getProjects().add(proj2);
        emp2.getProjects().add(proj4);

        employeeRepo.saveAll(List.of(emp1, emp2));

        System.out.println("findByCompany_Name");
        System.out.println(employeeRepo.findByCompany_Name("Adecco"));

        System.out.println("findByCompany_CityAndCompany_PostalCode");
        System.out.println(employeeRepo.findByCompany_CityAndCompany_PostalCode("Madrid", "28002"));


        // los projects son una asociación MANY, que es LAZY por defecto, por tanto no vienen los proyectos por defecto:
        // Error LazyInitializationException
        // System.out.println(employeeRepo.findByCompany_CityAndCompany_PostalCode("Madrid", "28002").getFirst().getProjects());

        // Solución: consulta QUERY para traer los empleados con sus respectivos proyectos en la misma query
        System.out.println(employeeRepo.findAllWithProjects().getFirst().getProjects());





        // employeeRepo.save()

//        BookRepository repo = context.getBean(BookRepository.class);
//
//        Book book1 = new Book(null, "book1", 100, 19.99, true, Status.DRAFT, LocalDate.of(2024, 1, 1));
//        Book book2 = new Book(null, "book2", 200, 532.10, true, Status.PUBLISHED, LocalDate.of(2023, 1, 1));
//        Book book3 = new Book(null, "El Carpintero", 300, 39.99, true, Status.CENSORED, LocalDate.of(2022, 1, 1));
//        Book book4 = new Book(null, "book4", 400, 49.99, true, Status.CENSORED, LocalDate.of(2021, 1, 1));
//        Book book5 = new Book(null, "El Carpintero de Notredam", 500, 59.99, false, Status.DISCONTINUED, LocalDate.of(2020, 1, 1));
//        Book book6 = new Book(null, "book2", 600, 69.99, false, Status.DISCONTINUED, LocalDate.of(2019, 1, 1));
//
//        // repo.save(book1);
//        List<Book> books = List.of(book1, book2, book3, book4, book5, book6);
//        repo.saveAll(books);
//
//        List<Book> booksToSave = new ArrayList<>();
//
//        for (int i = 7; i < 27; i++) {
//            int numPages = 100 + i;
//            double price = 20 + i;
//            Book another = new Book(null, "book" + i, numPages, price, false, Status.PUBLISHED, LocalDate.now());
//            // repo.save(another);
//            booksToSave.add(another);
//        }
//        repo.saveAll(booksToSave);

//        System.out.println("Libros de menos de 110 páginas: ");
//        for (Book book: repo.findByNumPagesLessThan(110)) System.out.println(book);
//
//        System.out.println("Libros entre 200 y 250 páginas: ");
//        for (Book book: repo.findByNumPagesBetween(200, 250)) System.out.println(book);
//
//        System.out.println("Libros con título que contiene ");
//        for(Book book: repo.findByTitleContains("carpintero")) System.out.println(book);
//
//        System.out.println("Libros disponibles");
//        for(Book book: repo.findByAvailableTrue()) System.out.println(book);

//        System.out.println("Libros con Status PUBLISHED");
//        for (Book book: repo.findByStatus(Status.PUBLISHED)) System.out.println(book);

//        System.out.println("Libros entre dos fechas");
//        LocalDate startDate = LocalDate.of(2020, 1, 1);
//        LocalDate endDate = LocalDate.of(2022, 1, 1);
//        for (Book book: repo.findByReleaseDateBetween(startDate, endDate)) System.out.println(book);

        // MÉTODOS FIND INSTACE: devuelven 1 solo libro
        // cuidado si hay más de un resultado puede dar excepción:
        // org.hibernate.NonUniqueResultException: Query did not return a unique result: 2 results were returned
//        System.out.println("findByTitleIgnoreCase: (devuelve Book) ");
//        System.out.println(repo.findByTitleIgnoreCase("El Carpintero"));
//
//        System.out.println("getByTitleIgnoreCase: (Devuelve Optional<Book>)");
//        System.out.println(repo.getByTitleIgnoreCase("El Carpintero"));

//        System.out.println("findFirstByTitle");
//        System.out.println(repo.findFirstByTitle("book2"));

//        System.out.println("El book más caro: findFirstByPriceNotNullOrderByPriceDesc");
//        System.out.println(repo.findFirstByPriceNotNullOrderByPriceDesc());

//        System.out.println("El book más barato: por defecto es asc");
//        System.out.println(repo.findFirstByPriceNotNullOrderByPrice());
//
//
//        System.out.println("cuantos libros hay en status PUBLISHED");
//        System.out.println(repo.countByStatus(Status.PUBLISHED));

//        System.out.println("Cuantos libros existen antes de 2024-02-14");
//        if(repo.existsByReleaseDateBefore(LocalDate.now())) {
//            System.out.println("sí existen libros de antes de 2024");
//        }
    }

}

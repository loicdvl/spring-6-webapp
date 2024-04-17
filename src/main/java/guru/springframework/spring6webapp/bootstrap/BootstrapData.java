package guru.springframework.spring6webapp.bootstrap;

import guru.springframework.spring6webapp.domain.Author;
import guru.springframework.spring6webapp.domain.Book;
import guru.springframework.spring6webapp.domain.Publisher;
import guru.springframework.spring6webapp.repositories.AuthorRepository;
import guru.springframework.spring6webapp.repositories.BookRepository;
import guru.springframework.spring6webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Author eric = new Author();
        eric.setFirstName("Eric");
        eric.setLastName("Evans");

        Book ddd = new Book();
        ddd.setTitle("Domain Driven Design");
        ddd.setIsbn("1234567890");

        Author savedEric = authorRepository.save(eric);
        Book savedDDD = bookRepository.save(ddd);

        Author rod = new Author();
        rod.setFirstName("Rod");
        rod.setLastName("Johnson");

        Book noEJB = new Book();
        noEJB.setTitle("J2EE development without EJB");
        noEJB.setIsbn("0987654321");

        Author savedRod = authorRepository.save(rod);
        Book savedNoEJB = bookRepository.save(noEJB);

        savedEric.getBooks().add(savedDDD);
        savedRod.getBooks().add(savedNoEJB);

        Publisher penguinRandomHouse = new Publisher();
        penguinRandomHouse.setPublisherName("Penguin Random House");
        penguinRandomHouse.setAddress("NYC");
        penguinRandomHouse.setCity("New York");
        penguinRandomHouse.setState("New York");
        penguinRandomHouse.setZip("10001");
        Publisher savedPubliser = publisherRepository.save(penguinRandomHouse);

        savedDDD.setPublisher(savedPubliser);
        savedNoEJB.setPublisher(savedPubliser);

        authorRepository.save(savedEric);
        authorRepository.save(savedRod);
        bookRepository.save(savedDDD);
        bookRepository.save(savedNoEJB);

        System.out.println("In BootStrap");
        System.out.println("Number of authors: " + authorRepository.count());
        System.out.println("Number of books: " + bookRepository.count());
        System.out.println("Number of publishers: " + publisherRepository.count());
    }
}
